package chatbot;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import javax.swing.SwingUtilities;


public class Control implements Runnable
{

	State state;
	View view;
	private Chatbot myChatbot;
	
	Map<String, BufferedImage> countMap;
	
	public Control()
	{
		SwingUtilities.invokeLater(this);
	}
	
	public void run()
	{
		countMap = new TreeMap<String, BufferedImage>();
		
		this.myChatbot = new Chatbot("Monica");
		
//		state = new State();
		view = new View(this);
//		
//		state.startFrame();
//		state.finishFrame();
		
	}
	
	public BufferedImage getImage(String filename)
	{
		if(countMap.containsKey(filename))
		{
			BufferedImage newImage = countMap.get(filename);
			return newImage;
		}
		try
		{
			ClassLoader myLoader = this.getClass().getClassLoader();
			InputStream imageStream = myLoader.getResourceAsStream("resources/" + filename);
			BufferedImage image = javax.imageio.ImageIO.read(imageStream);
			return image;
		}
		catch(IOException e)
		{
			System.out.println("Could not find or load resources/ " + filename);
			System.exit(0);
			return null;
		}
	}
	
	//returns inputed response
	public String processChatbot(String text) 
	{
		String response = "";
		
		//how chatbot responds
		response += myChatbot.processText(text) + ("\n");
		
		return response;
	}
	
	public String interactWithChatbot(String text) 
	{
		String response = "";
		
		//the display set up of your text
		response += ("(You): ") + text + ("\n");
		
		return response;
	}
	
	//catching errors
	public void handleError(Exception error) 
	{
		String details = "Your error is: " + error.getMessage();
		System.out.println(details);
	}
	
	//saving files
	private void saveListAsText(ArrayList<String> responses, String filename) 
	{
		File saveFile = new File(filename);
		try(PrintWriter saveText = new PrintWriter(saveFile)) 
		{
			for(String content : responses) 
			{
				saveText.println(content);
			}
		}
		catch(IOException errorFromIO) 
		{
			handleError(errorFromIO);
		}
		catch (Exception genericError) 
		{
			handleError(genericError);
		}
	}
	
	//loading files
	private HashMap<String, String> loadTextToList(String filename) 
	{
		HashMap<String, String> fileContents = new HashMap<String, String>();
		
		File source = new File(filename);
		
		try(Scanner fileScanner = new Scanner(source)) 
		{
			while(fileScanner.hasNextLine()) 
			{
				fileContents.put(fileScanner.nextLine(), filename); //might have to change later, defaulted so error wouldnt be thrown
			}
		}
		catch(IOException fileError) 
		{
			handleError(fileError);
		}
		catch(Exception error) 
		{
			handleError(error);
		}
		
		return fileContents;
	}
	
}
