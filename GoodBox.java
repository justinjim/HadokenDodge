//Justin Jim
//GoodBox.java

//The GoodBox class creates objects that help manage the powerups. 
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
public class GoodBox{
	private int x;
	private int y;
	private int length;
	private int height; 
	private int power; 
	public Image powerup;
	//GoodBox constructor makes sure the right fields get the right properties. 
	public GoodBox(int x1, int y1, int power1){
		x=x1;
		y=y1;
		//Depending on the type of power up that needs to be created, the powerup field gets assigned different images. 
		power=power1;
		Image thepower1= new ImageIcon("hpup.png").getImage();
		Image thepower2 = new ImageIcon("sake.png").getImage();
		Image thepower3 = new ImageIcon("wasabi.png").getImage();
		if (power == 1){
			length = 80;
			height = 49;
			powerup = thepower1.getScaledInstance(80, 49, Image.SCALE_SMOOTH);		
		}
		if (power == 2){
			length = 30;
			height = 87;
			powerup = thepower2.getScaledInstance(30,87, Image.SCALE_SMOOTH);
		}
		if (power == 3){
			length = 63;
			height = 61;
			powerup = thepower3.getScaledInstance(63,61,Image.SCALE_SMOOTH);
		}	
	}
	//right and left methods move the powerup right or left. 
	public void right(){
		x += - 5;
	}
	public void left(){
		x += 5;
	}
	//drop method inreases the y coordinate plus any additional value it may recieve (from the player moving up)
	public void drop(int increase){
		y += 3+increase;
		x += (int)(Math.random()*3-1);
	}
	//getpower method returns what type of powerup the current object is
	public int getpower(){
		return power; 
	}
	//getx, gety, getlength, and getheight methods are used for the collision method in HadokenDodgeFrame.
	public int getx(){
		return x;
	}
	public int gety(){
		return y;
	}
	public int getlength(){
		return length;
	}	
	public int getheight(){
		return height;
	}
	//getimage method used to draw the correct picture for the powerup. 
	public Image getimage(){
		return powerup;
	}
}