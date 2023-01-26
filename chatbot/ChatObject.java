package chatbot;

import java.awt.Graphics;

abstract public class ChatObject 
{
	protected boolean isVisible;
	protected boolean isExpired;
	
	public boolean isVisible() {return isVisible;}
	public boolean isExpired() {return isExpired;}
	
	abstract public void draw(Graphics g);
	abstract public void update(double elapsedTime);
}
