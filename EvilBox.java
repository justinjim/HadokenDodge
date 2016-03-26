//Justin Jim
//EvilBox.java

//The EvilBox class. This class creates objects that help manage the hadokens. 
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
public class EvilBox{
	//The fields x,y are the coordinates of the hadoken
	private int x;
	private int y;
	//Drop value is the speed of which it travels.
	private int dropvalue;
	private int length;
	private int height;
	public Image hadoken;
	//EvilBox constructor makes sure the right information is set to the right field
	public EvilBox(int x1,int y1,int dropvalue1){
		Image ahadoken1= new ImageIcon("hadoken1.png").getImage();
   		Image ahadoken2= new ImageIcon("hadoken2.png").getImage();
   		Image ahadoken3= new ImageIcon("hadoken3.png").getImage();
   		Image ahadoken4= new ImageIcon("hadoken4.png").getImage();
   		Image ahadoken5= new ImageIcon("hadoken5.png").getImage();
   		Image ahadoken6= new ImageIcon("hadoken6.png").getImage();
   		Image ahadoken7= new ImageIcon("hadoken7.png").getImage();
   		Image ahadoken8= new ImageIcon("hadoken8.png").getImage();
   		Image ahadoken9= new ImageIcon("hadoken9.png").getImage();
   		Image ahadoken10= new ImageIcon("hadoken10.png").getImage(); 		
		x = x1;
		y = y1;
		//The drop value determines what kind of hadoken is produced
		dropvalue = dropvalue1;
		if (dropvalue==1){
			length = 38;
			height = 45;
			hadoken= ahadoken1.getScaledInstance(38, 45, Image.SCALE_SMOOTH);
		}
		if (dropvalue==2){
			length = 38;
			height = 60;
			hadoken= ahadoken2.getScaledInstance(38, 60, Image.SCALE_SMOOTH);
		}
		if (dropvalue==3){
			length = 38;
			height = 78;
			hadoken= ahadoken3.getScaledInstance(38, 78, Image.SCALE_SMOOTH);
		}
		if (dropvalue==4){
			length = 35;
			height = 82;
			hadoken= ahadoken4.getScaledInstance(35, 82, Image.SCALE_SMOOTH);
		}
		if (dropvalue==5){
			length = 42;
			height = 76;
			hadoken= ahadoken5.getScaledInstance(42, 76, Image.SCALE_SMOOTH);
		}
		if (dropvalue==6){
			length = 42;
			height = 74;
			hadoken= ahadoken6.getScaledInstance(42, 74, Image.SCALE_SMOOTH);
		}
		if (dropvalue==7){
			length = 42;
			height = 77;
			hadoken= ahadoken7.getScaledInstance(42, 77, Image.SCALE_SMOOTH);
		}
		if (dropvalue==8){
			length = 42;
			height = 80;
			hadoken= ahadoken8.getScaledInstance(42, 80, Image.SCALE_SMOOTH);
		}
		if (dropvalue==9){
			length = 45;
			height = 77;
			hadoken= ahadoken9.getScaledInstance(45, 77, Image.SCALE_SMOOTH);
		}
		if (dropvalue==10){
			length = 39;
			height = 76;
			hadoken= ahadoken10.getScaledInstance(39, 76, Image.SCALE_SMOOTH);
		}				
	}	
	//Drop method. When this method is called it increases the y coordinate of the hadoken, along with any extra value it may have. 
	public void drop(int extra){
		y = y+dropvalue + extra;
	}	
	//gety, getx, getlength, and getheight methods. Returns the y coordinate (used for the collision method in HadokenDodgeFrame). 
	public int gety(){
		return y;
	}
	public int getx(){
		return x;
	}	
	public int getlength(){
		return length;
	}	
	public int getheight(){
		return height;
	}	
	//getimage method used in the paint method to draw the hadoken. 
	public Image getimage(){
		return hadoken;
	}
	//reset method used to reset all the important fields of said hadoken. used once a hadoken reaches the end of the screen. 
	public void reset(){
		Image ahadoken1= new ImageIcon("hadoken1.png").getImage();
   		Image ahadoken2= new ImageIcon("hadoken2.png").getImage();
   		Image ahadoken3= new ImageIcon("hadoken3.png").getImage();
   		Image ahadoken4= new ImageIcon("hadoken4.png").getImage();
   		Image ahadoken5= new ImageIcon("hadoken5.png").getImage();
   		Image ahadoken6= new ImageIcon("hadoken6.png").getImage();
   		Image ahadoken7= new ImageIcon("hadoken7.png").getImage();
   		Image ahadoken8= new ImageIcon("hadoken8.png").getImage();
   		Image ahadoken9= new ImageIcon("hadoken9.png").getImage();
   		Image ahadoken10= new ImageIcon("hadoken10.png").getImage(); 
		y =-50;
		x = (int)(Math.random()*2400-800);
		dropvalue = (int)(Math.random()*10+1);
		if (dropvalue==1){
			length = 38;
			height = 45;
			hadoken= ahadoken1.getScaledInstance(38, 45, Image.SCALE_SMOOTH);
		}
		if (dropvalue==2){
			length = 38;
			height = 60;
			hadoken= ahadoken2.getScaledInstance(38, 60, Image.SCALE_SMOOTH);
		}
		if (dropvalue==3){
			length = 38;
			height = 78;
			hadoken= ahadoken3.getScaledInstance(38, 78, Image.SCALE_SMOOTH);
		}
		if (dropvalue==4){
			length = 35;
			height = 82;
			hadoken= ahadoken4.getScaledInstance(35, 82, Image.SCALE_SMOOTH);
		}
		if (dropvalue==5){
			length = 42;
			height = 76;
			hadoken= ahadoken5.getScaledInstance(42, 76, Image.SCALE_SMOOTH);
		}
		if (dropvalue==6){
			length = 42;
			height = 74;
			hadoken= ahadoken6.getScaledInstance(42, 74, Image.SCALE_SMOOTH);
		}
		if (dropvalue==7){
			length = 42;
			height = 77;
			hadoken= ahadoken7.getScaledInstance(42, 77, Image.SCALE_SMOOTH);
		}
		if (dropvalue==8){
			length = 42;
			height = 80;
			hadoken= ahadoken8.getScaledInstance(42, 80, Image.SCALE_SMOOTH);
		}
		if (dropvalue==9){
			length = 45;
			height = 77;
			hadoken= ahadoken9.getScaledInstance(45, 77, Image.SCALE_SMOOTH);
		}
		if (dropvalue==10){
			length = 39;
			height = 76;
			hadoken= ahadoken10.getScaledInstance(39, 76, Image.SCALE_SMOOTH);
		}			
	}	
	//right and left methods. used to move the hadoken right and left on the screen.
	public void right(){
		x += -5;
	}	
	public void left(){
		x += 5;
	}			
}
	