package chatbot;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;
import javax.swing.SwingWorker;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;

//key pressed imports
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class View extends JPanel
{
	Control control;
//	State state;
	
	private SpringLayout layout;
	private JScrollPane chatPane;
	private JTextField textField;
	private JLabel background;
	private JLabel brownBoarder;
	private JLabel lightPinkBackground;
	private JLabel brownBoarderLP;
	static JLabel chatbotSprite;
	private JTextPane displayTextPane;
	
	//emotion boolean statements
	boolean neutral = true;

	public View(Control control)
	{ 
		this.control = control;
//		this.state = state;
		
		//window dimensions
		Dimension d = new Dimension(1200, 1000);
		this.setMinimumSize(d);
		this.setPreferredSize(d);
		this.setMaximumSize(d);
		
		//layouts
		this.chatPane = new JScrollPane(displayTextPane);
		this.layout = new SpringLayout();
		this.setLayout(layout);
		
		//displayTextPane
		this.displayTextPane = new JTextPane();
		layout.putConstraint(SpringLayout.NORTH, displayTextPane, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, displayTextPane, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, displayTextPane, -117, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, displayTextPane, -964, SpringLayout.EAST, this);
		displayTextPane.setBackground(new Color(255, 240, 194));
		displayTextPane.setText("You are now chatting with chatbot, say hello!" + "\n" + "--------------------------------------------------------" + "\n");
		SimpleAttributeSet set = new SimpleAttributeSet();
		
		//setting the font
		StyleConstants.setItalic(set, true); //change later
		
		//document creation to add new strings
		StyledDocument doc = (StyledDocument) displayTextPane.getDocument();
		Style style = doc.addStyle("StyleName", null);
		
		//adding displayTextPane to the frame
		displayTextPane.setEditable(false); 
		add(displayTextPane);
		
		setupChatPane();
		
		//textField
		textField = new JTextField(50);
		textField.setBackground(new Color(255, 240, 194));
		layout.putConstraint(SpringLayout.NORTH, textField, 5, SpringLayout.SOUTH, displayTextPane);
		layout.putConstraint(SpringLayout.WEST, textField, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, textField, -964, SpringLayout.EAST, this);
		add(textField);
		textField.setColumns(10);
		
		setupChatbot();
		
		// background behind the physical chatbot sprite
		lightPinkBackground = new JLabel("");
		layout.putConstraint(SpringLayout.NORTH, lightPinkBackground, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, lightPinkBackground, 255, SpringLayout.WEST, this);
		lightPinkBackground.setIcon(new ImageIcon(View.class.getResource("/resources/light_pink_background.png")));
		layout.putConstraint(SpringLayout.SOUTH, lightPinkBackground, -93, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, lightPinkBackground, -15, SpringLayout.EAST, this);
		add(lightPinkBackground);
		
		
		//frame setup
		JFrame f = new JFrame("Horror Chatbot");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(this);
		
		//checking key presses
		textField.addKeyListener(new KeyAdapter() 
		{
			public void keyPressed(KeyEvent e)
			{
				int keyCode = e.getKeyCode();
				//key press for enter
				if(keyCode == KeyEvent.VK_ENTER)
				{
					//printing for user chat messages
					try 
					{
						StyleConstants.setForeground(style, Color.PINK);
						doc.insertString(doc.getLength(), control.interactWithChatbot(textField.getText()), style);
					} 
					catch (BadLocationException e2) 
					{
						System.out.println("process chatbot error");
					}
					
					//printing for chatbot chat messages
					try 
					{	
						StyleConstants.setForeground(style, Color.MAGENTA);
						doc.insertString(doc.getLength(), control.processChatbot(textField.getText()), style);
					} 
					catch (BadLocationException e1) 
					{
						System.out.println("interact with chatbot error");
					}
					
					//clears textField when user presses enter
					textField.setText("");
				}
			}
		});
		
		//brown boarder around the chat area
		brownBoarder = new JLabel("");
		layout.putConstraint(SpringLayout.NORTH, brownBoarder, 7, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, brownBoarder, 7, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, brownBoarder, -89, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, brownBoarder, -960, SpringLayout.EAST, this);
		brownBoarder.setIcon(new ImageIcon(View.class.getResource("/resources/brown_background.png")));
		add(brownBoarder);
		
		//brown boarder around the light pink background
		brownBoarderLP = new JLabel("");
		layout.putConstraint(SpringLayout.NORTH, brownBoarderLP, 7, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, brownBoarderLP, 251, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, brownBoarderLP, -89, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, brownBoarderLP, -11, SpringLayout.EAST, this);
		brownBoarderLP.setIcon(new ImageIcon(View.class.getResource("/resources/brown_boarder_LP.png")));
		add(brownBoarderLP);
		
		//background
		background = new JLabel("");
		layout.putConstraint(SpringLayout.WEST, background, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, background, 0, SpringLayout.EAST, this);
		background.setIcon(new ImageIcon(View.class.getResource("/resources/pink_background.png")));
		layout.putConstraint(SpringLayout.NORTH, background, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.SOUTH, background, 0, SpringLayout.SOUTH, this);
		add(background);
		
		SwingWorker<Void, Void> chatbotEmotions = new SwingWorker<Void, Void>() 
		{

			@Override
			protected Void doInBackground() throws Exception 
			{
				chatbotEmotions();
				return null;
			}
					
		};
		
		chatbotEmotions.execute();
		
		f.setLocationRelativeTo(null);
		f.pack();
		f.setVisible(true);
		
	}
	
	
	private void setupChatbot()
	{
		chatbotSprite = new JLabel("");
		layout.putConstraint(SpringLayout.NORTH, chatbotSprite, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, chatbotSprite, 260, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, chatbotSprite, -93, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, chatbotSprite, -15, SpringLayout.EAST, this);
		chatbotSprite.setPreferredSize(new Dimension(800, 900));
		add(chatbotSprite);
	}
	
	private void chatbotEmotions()
	{
		if(neutral == true)
		{
			chatbotSprite.setIcon(new ImageIcon(View.class.getResource("/resources/chatbotBlinkSprite.gif")));
		}
	}
	
	private void setupChatPane() 
	{
		add(chatPane);
		chatPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		chatPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	}
	
}
