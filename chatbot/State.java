/**
 * primary class thats responsible for the loading of frames
 * @author Annie de Bry
 * @Version December 2022
 */
package chatbot;

import java.util.List;
import java.util.ArrayList;

public class State 
{
	
	//creates the current and next frames for the game objects (stored in lists)
	List<ChatObject> currentFrameChatObjects;
	List<ChatObject> nextFrameChatObjects;
	
	public State()
	{
		currentFrameChatObjects = new ArrayList<ChatObject>();
	}
	
	/**
	 * getter for the current frame game objects list
	 * @return currentFrameGameObjects
	 */
	public List<ChatObject> getFrameObjects()
	{
		return currentFrameChatObjects;
	}
	
	/**
	 * creates a new array list and adds all values to the new array list that were stored in the nextFrameGameObjects array List
	 */
	public void startFrame()
	{
		nextFrameChatObjects = new ArrayList<ChatObject>();
		nextFrameChatObjects.addAll(currentFrameChatObjects);

		
	}
	
	/**
	 * checks if the frame/game object is expired and acts accordingly 
	 */
	public void finishFrame()
	{
		for(int index = 0; index < currentFrameChatObjects.size(); index++)
		{
			if(currentFrameChatObjects.get(index).isExpired() == true)
			{
				nextFrameChatObjects.remove(currentFrameChatObjects.get(index));
			}
		}
		
		currentFrameChatObjects = nextFrameChatObjects;
	}
	
	/**
	 * adds a game object to the game objects array list
	 * @param go
	 */
	public void addGameObject (ChatObject go)
	{
		nextFrameChatObjects.add(go);
	}
	
}
