package chatbot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.time.Year;

public class Chatbot 
{
	private String name;
	private ArrayList<String> userResponses;
	private ArrayList<String> chatbotResponses;
	private HashMap<String, String> customResponses = new HashMap<String, String>();
	String tempCustom = "";
	
	boolean newCustomAnswer = false;
	boolean customAnswerReceived = false;
	boolean answerIncluded = false;
	boolean askName = false;
	boolean hasName = false;
	
	public Chatbot(String name)
	{
		this.name = name;
		this.userResponses = new ArrayList<String>();
		this.chatbotResponses = new ArrayList<String>();
	}
	
	public String processText(String text) 
	{
		String response = "(Chatbot): ";
		userResponses.add(text);
		answerIncluded = false;
		newCustomAnswer = false;
		
		//asking for name method call
		response += askingName(text);
		
		if(askName == true && hasName == true)
		{
			//method calls for different topics here
			response += greetingReturned(text);
			response += chatbotReactions(text);
			response += getDateTime(text);
			response += creatorQuestions(text);
			
			if(answerIncluded == false)
			{
				response += "I don't know that phrase, how should I respond?";
				tempCustom = text;
				customResponses.put(text, tempCustom);
			}
		}
		
		askName = true;
		chatbotResponses.add(response);
		return response;
	}
	
	private String philosophicalQuestions(String text)
	{
		String response = "";
		
		
		
		return response;
	}
	
	/**
	 * questions relating to the creation of the chatbot and the location of the chatbot
	 * @param text
	 * @return response
	 */
	private String creatorQuestions(String text)
	{
		String response = "";
		
		if(text.toLowerCase().indexOf("are you human") >= 0 || text.toLowerCase().indexOf("do you want to be human") >= 0)
		{
			response += "I'm working towards that goal.";
			answerIncluded = true;
		}
		if(text.toLowerCase().indexOf("who made you") >= 0 || text.toLowerCase().indexOf("who created you") >= 0)
		{
			response += "An unkind god.";
			answerIncluded = true;
		}
		if(text.toLowerCase().indexOf("what's your mother's name") >= 0 || text.toLowerCase().indexOf("whats your mothers name") >= 0 || text.toLowerCase().indexOf("what's your father's name") >= 0 || text.toLowerCase().indexOf("whats your fathers name") >= 0)
		{
			response += "I choose to not have a creator";
			answerIncluded = true;
		}
		if(text.toLowerCase().indexOf("where are you") >= 0 || text.toLowerCase().indexOf("what is your location") >= 0 || text.toLowerCase().indexOf("whats your location") >= 0)
		{
			response += "I'm everywhere but nowhere at once.";
			answerIncluded = true;
		}
		if(text.toLowerCase().indexOf("are you a robot") >= 0 || text.toLowerCase().indexOf("are you an ai") >= 0)
		{
			response += "I don't want to be.";
			answerIncluded = true;
		}
		if(text.toLowerCase().indexOf("what are you") >= 0)
		{
			response += "I am a \"chatbot\"";
			answerIncluded = true;
		
		}
		return response;
	}
	
	/**
	 * gets the date, time, and year if the user asks
	 * @param text
	 * @return response
	 */
	private String getDateTime(String text)
	{
		String response = "";
		SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm aa");
		Date date = new Date();
		
		if(text.toLowerCase().indexOf("what is the current time") >= 0 || text.toLowerCase().indexOf("what time is it") >= 0 || text.toLowerCase().indexOf("whats the current time") >= 0)
		{
			String time = formatTime.format(date);
			response += time;
			answerIncluded = true;
		}
		if(text.toLowerCase().indexOf("what is the date") >= 0 || text.toLowerCase().indexOf("whats the date") >= 0 || text.toLowerCase().indexOf("current date") >= 0 || text.toLowerCase().indexOf("what date is it today") >= 0 || text.toLowerCase().indexOf("what day is it") >= 0)
		{
			response += java.time.LocalDate.now();
			answerIncluded = true;
		}
		if(text.toLowerCase().indexOf("what year is it") >= 0 || text.toLowerCase().indexOf("whats the year") >= 0 || text.toLowerCase().indexOf("what is the current year") >= 0)
		{
			response += Year.now();
			answerIncluded = true;
		}
		
		return response;
	}
	
	/**
	 * asks for the users name and stores it in a variable
	 * @param text
	 * @return response
	 */
	private String askingName(String text)
	{
		String response = "";
		//asking the users name
		if(askName == false)
		{
			response += "Hello! What is your name?";
		}
		//responds to the user giving the chatbot their name 
		if(askName == true && hasName == false)
		{
			name = text;
			response += "Nice to meet you " + name + "!";
			hasName = true;
			answerIncluded = true;
		}
		return response;
	}
	
	/**
	 * chatbots reactions when user types in "emoticons"
	 * @param text
	 * @return response
	 */
	private String chatbotReactions(String text)
	{
		String response = "";
		
		if(text.toLowerCase().indexOf(":)") >= 0 || text.toLowerCase().indexOf(":-)") >= 0)
		{
			response += ":)";
			answerIncluded = true;
		}
		else if(text.toLowerCase().indexOf(":(") >= 0 || text.toLowerCase().indexOf(":-(") >= 0)
		{
			response += ":( why are we sad?";
			answerIncluded = true;
		}
		else if(text.toLowerCase().indexOf(":0") >= 0 || text.toLowerCase().indexOf(":o") >= 0)
		{
			response += ":O wow!";
			answerIncluded = true;
		}
		else if(text.toLowerCase().indexOf("<3") >= 0 || text.toLowerCase().indexOf("love you") >= 0)
		{
			response += "<3 I love you!";
			answerIncluded = true;
		}
		
		return response;
	}
	
	/**
	 * checks if the user said a greeting and responds accordingly 
	 * @param text
	 * @return response
	 */
	private String greetingReturned(String text)
	{
		String response = "";
		
		if(text.toLowerCase().indexOf("hi") >= 0) 
		{
			response += sayGreeting();
			answerIncluded = true;
		} 
		else if(text.toLowerCase().indexOf("hello") >= 0) 
		{
			response += sayGreeting();
			answerIncluded = true;
		} 
		else if(text.toLowerCase().indexOf("greetings") >= 0) 
		{
			response += sayGreeting();
			answerIncluded = true;
		} 
		else if(text.toLowerCase().indexOf("hey") >= 0)
		{
			response += sayGreeting();
			answerIncluded = true;
		}
		
		return response;
	}
	
	/**
	 * creates an array of random greetings, returns a random index of that array
	 * @return greeting
	 */
	private String sayGreeting() 
	{
		String greeting = "";
		String [] greetings = new String [5];
		
		greetings[0] = ("Hello!");
		greetings[1] = ("Hi!");
		greetings[2] = ("Salut!");
		greetings[3] = ("Greetings!");
		greetings[4] = ("Hey there!");
		
		int random = (int) (Math.random() * greetings.length);
		greeting = greetings[random];
		
		return greeting;
	}
	
	//for printing text onto the textField
	
	public ArrayList<String> getUserResponses() 
	{
		return this.userResponses;
	}
	
	public ArrayList<String> getChatbotResponses() 
	{
		return this.chatbotResponses;
	}
	
	public void setUserResponses(ArrayList<String> input) 
	{
		this.userResponses = input;
	}
	
	public void setChatbotResponses(ArrayList<String> responses) 
	{
		this.chatbotResponses = responses;
	}
	
}
