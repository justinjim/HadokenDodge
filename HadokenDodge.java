//Justin Jim
//HadokenDodge.java

/*Hadoken Dodge is a game where you try to get as far as you can while dodging a bunch of hadokens on the screen. The score
 *system is measured by distance, so the further you go the higher your score will be. (Yeah, I spell it Hadoken and not Hadouken.)
 *
 *This game was made up of Mr.McKenzie's BaseFrame (which HadokenDodgeFrame is based on), an EvilBox class, a GoodBox class, and
 *the actual HadokenDodge class. 
 */
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
//This HadokenDodge class is where all the magic from everywhere else comes togeather to form a surge of awesome. 
public class HadokenDodge{
    public static void delay (long len)
    {
		try{
			Thread.sleep (len);
		}
		catch (InterruptedException ex){
			System.out.println(ex);
		}
    }
    public static void main(String[] args) {
		HadokenDodgeFrame frame = new HadokenDodgeFrame();	
		while(true){	
			
			if (frame.health>0 && frame.mode == 1){		
				frame.score();
				frame.crazypowers();
				frame.collision();
			}			
			frame.move();
			frame.update();	
			delay(10);			
		}
    }
}

//The HadokenDodgeFrame uses a bunch of black magic from Mr.McKenzie's own BaseFrame, which I would not touch
//with a ten foot pole because it scares me. For more information on BaseFrame, you should read his comments in the BaseFrame file. 
class HadokenDodgeFrame extends BaseFrame{
	//Defining all of my variables. Most of these are pretty self explanatory ('score' keeps track of the score), but I'll go into more
	//detail on the more interesting ones. 
	int score = 0;
	int value = 1;
	double health = 600;
	int chanceofpower;
	int mode = 0;
	int whichpower;
	double dropvalue = 0;
	int slowcounter = 0; 
	boolean slow = false; 
	boolean invincible = false;
	int invinciblecounter = 0;
	double currenthighlight = .85; 
	//These two ArrayList hold objects from EvilBox and GoodBox. EvilBox objects have all the information I need for a Hadoken
	//and GoodBox objects have all the information I need for any of the powerups. This will be explained further once I start
	//using them.  
	ArrayList<EvilBox>evilboxes;
	ArrayList<GoodBox>goodboxes;
	//These arrays hold images of the sprites for my ship, which lets me cycle through the images when I draw the ship
	//making it look animated. 
	Image[]ship;
	Image[]leftship;
	Image[]rightship;
	Image[][]supership;	
	Image explosiony = new ImageIcon("explosion.png").getImage();
	Image explosion = explosiony.getScaledInstance(33,29,Image.SCALE_SMOOTH);
	Image blanky = new ImageIcon("blank.bmp").getImage();
	Image blank = blanky.getScaledInstance(1,1,Image.SCALE_SMOOTH);
   	Image lolbackground= new ImageIcon("background.png").getImage();
   	Image background = lolbackground.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH); 	
   	Image lolship1 = new ImageIcon("ship1.png").getImage();
   	Image ship1 = lolship1.getScaledInstance(41, 37, Image.SCALE_SMOOTH); 	
   	Image lolship3 = new ImageIcon("ship2.png").getImage();
   	Image ship3 = lolship3.getScaledInstance(41, 45, Image.SCALE_SMOOTH);	
   	Image lolship2 = new ImageIcon("ship3.png").getImage();
   	Image ship2 = lolship2.getScaledInstance(41, 42, Image.SCALE_SMOOTH);  	
   	Image lolshipleft1 = new ImageIcon("ship10.png").getImage();
   	Image shipleft1 = lolshipleft1.getScaledInstance(32,37,Image.SCALE_SMOOTH);  	
   	Image lolshipleft2 = new ImageIcon("ship11.png").getImage();
   	Image shipleft2 = lolshipleft2.getScaledInstance(32,42,Image.SCALE_SMOOTH);  	
   	Image lolshipleft3 = new ImageIcon("ship12.png").getImage();
   	Image shipleft3 = lolshipleft3.getScaledInstance(32,45,Image.SCALE_SMOOTH);  	
   	Image lolshipright1 = new ImageIcon("ship20.png").getImage();
   	Image shipright1 = lolshipright1.getScaledInstance(32,37,Image.SCALE_SMOOTH);  	
   	Image lolshipright2 = new ImageIcon("ship21.png").getImage();
   	Image shipright2 = lolshipright2.getScaledInstance(32,42,Image.SCALE_SMOOTH); 	
   	Image lolshipright3 = new ImageIcon("ship22.png").getImage();
   	Image shipright3 = lolshipright3.getScaledInstance(32,45,Image.SCALE_SMOOTH); 	
   	Image thebackgroundfirst = new ImageIcon("background1.jpg").getImage();
   	Image thebackground1 = thebackgroundfirst.getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH);
   	//Keeps track of which animation of the ship I'm currently at
   	int frame = 0;
	int direction = 0;	// 0 - straight, 1 left, 2 right
	//Slows down the animation so it doesn't look like the ship is spazzing out
	int frameDelay= 0;   	
	Font scorefont = new Font("serif", Font.BOLD, 40);
	Font modes = new Font("serif", Font.BOLD, 40);
	//The constructor. Here I set values to my arrays so I can use them later on .
    public HadokenDodgeFrame() {
		super();
		evilboxes=new ArrayList<EvilBox>();
		goodboxes=new ArrayList<GoodBox>();
		ship = new Image[3];
		leftship = new Image[3];
		rightship = new Image[3];
		supership = new Image[3][];	
		ship[0] = ship1;
		ship[1] = ship2;
		ship[2] = ship3;		
		leftship[0] = shipleft1;
		leftship[1] = shipleft2;
		leftship[2] = shipleft3;	
		rightship[0] = shipright1;
		rightship[1] = shipright2;
		rightship[2] = shipright3;	
		supership[0] = ship;
		supership[1] = leftship;
		supership[2] = rightship;
		//This creates 74 EvilBox objects and adds them to this ArrayList (EvilBox objects hold information for Hadokens,
		//such as the picture, speed, coordinates, and lengthes. Without this information Hadokens could not have been created). 
		for (int i = 0; i <75; i++){
			evilboxes.add(new EvilBox((int)(Math.random()*2400-800),(int)(Math.random()*600-900),(int)(Math.random()*10)+1));
			}		
    }
    //The collision method. This checks if any of the Hadokens or powerups touch the ship. 
    public void collision(){	
    	//Checking every single EvilBox object in the evilboxes ArrayList to see if it collides with the ship. 
    	for(EvilBox eb : evilboxes){
			if (eb.getx()<=421 && eb.getx()+eb.getlength() >= 385 && eb.gety()+eb.getheight()>=525 && eb.gety()<=575 && invincible == false){
				//Player loses five health everytime around the loop it is caught touching a Hadoken. However, if the ship had ate some wasabi,
				//in the previous run in the loop, it gains immunity from the devistating health += -5. 
				health += -5;
				//This field 'hadoken' in EvilBox objects is the picture field. Setting this picture to explosion switches the picture
				//from a picture of a Hadoken to a picture of an explosion, making it look like the Hadoken is exploding off of the
				//ship. In order to do this, the 'hadoken' field had to be public. 
				eb.hadoken = explosion;
			}
		}
		//Checking every single GoodBox object in the goodboxes ArrayList to see if it collides with the ship. GoodBox objects
		//hold information for various powerups (sashimi, wasabi, and sake). 
		for(GoodBox gb : goodboxes){
			if (gb.getx()<=421 && gb.getx()+gb.getlength() >= 385 && gb.gety()+gb.getheight()>=525 && gb.gety()<=575){
				//The powerup field in GoodBox objects is the field that holds the picture. This sets that picture to a blank picture file
				//which makes the powerup disappear the second it collides with the ship. In order to do this, the powerup field had to be
				// public aswell. 
				gb.powerup = blank;
				//In order to avoid making too many things public (out of fear of losing marks) a getpower method was created to find out
				//which power the GoodBox object is suppose to represent. The field 'power' can either be 1, 2, or 3, which represent
				//sashimi, sake, and wasabi respectively. 
				if (gb.getpower() == 1 && health <=555){
					//Granting the player five health every time around the loop the ship is caught touching a sashimi. 
					health += 5;		
				}
				if (gb.getpower() == 2){
					//Stops all hadokens in their tracks (but not other power ups!) when a ship is caught touching sake. 
					slow = true;
					slowcounter = 0; 
				}
				if (gb.getpower()==3){
					//Makes the ship invincible from all the wasabi it ate. Originally, it was supposed to be a fire shield, but
					//things did not go as smoothly. 
					invincible = true;
					invinciblecounter = 0;
				}
				
			}
		}
		//In order to make sure this game was not too easy, the invincibility power only lasts 250 times around the loop. 
		if (invincible == true){
			invinciblecounter += 1;
			if (invinciblecounter == 250){
				invincible = false;
				invinciblecounter = 0;
			}
		}
		    	
    }  
    
    //The score method. This keeps track of the score, the value variable is to see if the player is pressing the UP arrow
    //key, since the score system is based on distance and not time. 
    public void score(){
    	score = (score+ value);
    } 
    //The move method. The ship never moves, but gives off the appearance of moving but having all other objects move. Although this 
    //appearance is kind of destroyed with the background (some people set the background as the absolute zero along with the ship,
    //making them think the hadokens are moving left to right and not the ship), some people still get it. I did not want to remove
    //the background because I'm personally attached to it. It looks cool. 
	public void move(){
		//The mode variable keeps track of what screen is currently being displayed. Mode 0 represents the menu screen, and mode 1 represents
		//the actual game. 
		//When the menu screen is being displayed, I need to keep track of what game mode is being considered (normal, hardcore, or R.I.P.). 
		//I do this with the currenthighlight variable and chose to make it a double because when it was an integer value, the selection
		//between modes would happen too quickly. There is probably a much better way of delaying the button press, but this was all I
		//could think of. 
		if (mode == 0){
		
			if (keys[DOWN] && (int)currenthighlight < 2){
				currenthighlight += .15;
			}
			if (keys[UP] && (int)currenthighlight > 0){
				currenthighlight += -.15;
			}
			//When the user selects a mode, it switches from displaying the menu to displaying the game. 
			//Normal Mode
			if (keys[SPACE] && (int)currenthighlight == 0){
				mode = 1;
			}
			//Hardcore Mode
			if (keys[SPACE] && (int)currenthighlight == 1){
				mode = 1;
				//49 additional Hadokens of fun!
				for (int i = 0; i <50; i++){
					evilboxes.add(new EvilBox((int)(Math.random()*2400-800),(int)(Math.random()*600-900),(int)(Math.random()*10)+1));
				}
			}
			//R.I.P. Mode
			if (keys[SPACE] && (int)currenthighlight == 2){
				mode = 1;
				//149 additional Hadokens. Good luck. (Unless you get really lucky with wasabi spawns, it's hard to get past 1000)
				for (int i = 0; i <150; i++){
					evilboxes.add(new EvilBox((int)(Math.random()*2400-800),(int)(Math.random()*600-900),(int)(Math.random()*10)+1));
				}
			}		
		}
		//The game mode! 
		if (mode == 1){
			//When the player is alive, it goes through every single EvilBox object and GoodBox object and moves them down the screen
			//(only if the player doesn't have the sake buff). 
			//If the player presses left or right, all of the objects move to the opposite direction to make it look like the ship is
			//dodging them. 
			if (health>0){
				for(EvilBox eb : evilboxes){
					if (keys[UP]){
						value = 2;
						frame = 2;
						dropvalue = 5;
					}
					else{
						value = 1;
						dropvalue = 1;
					}
					if (slow == false){
						eb.drop((int)dropvalue);
					}
					else{
						slowcounter += 1;
						if (slowcounter == 10000){
							slow = false;
						}
					}
					if (eb.gety()>=600){
						eb.reset();
					}
				}
				for (GoodBox gb : goodboxes){
					if (keys[UP] ){
						dropvalue = 5;
					}
					
					gb.drop((int)dropvalue);
				}
				if(keys[RIGHT]){
					for(EvilBox eb : evilboxes){
						eb.right();
						direction = 2;
					}
					for(GoodBox gb : goodboxes){
						gb.right();
						
					}
					
				}
				else if(keys[LEFT]){
					for(EvilBox eb : evilboxes){
						eb.left();
						direction = 1;
					}  
					for(GoodBox gb : goodboxes){
						gb.left();
						
					}
					
				}
			}
		}	
	}
	
	//Everytime around the loop, there is a 1/199 chance that a GoodBox object will be added to the goodboxes arraylist, which
	//would mean a powerup for the player. These powerups are then randomly selected (with the variable whichpower). 
	public void crazypowers(){
		chanceofpower = (int)(Math.random()*200);
		if (chanceofpower == 1){
			whichpower = (int)(Math.random()*3+1);
			goodboxes.add(new GoodBox((int)(Math.random()*800),(int)(Math.random()*600-900),whichpower));		
		}
	}
	
	
    public void paint(Graphics g){ 
    	//Drawing the menu  		
    	if (mode == 0){
    		g.drawImage(background,0,0,null);
    		g.setFont(modes);
    		g.setColor(Color.white);
    		g.drawString("Press SPACE to make your selection", 100,550);
    		//Makes sure the correct option is highlighted
    		if ((int)currenthighlight == 0 ){	
	    		g.setColor(Color.red);
	    		g.drawString("Normal",100,100);
	    		g.setColor(Color.white);
	    		g.drawString("Hardcore",100,200);
	    		g.drawString("R.I.P.",100,300);
    		}
    		if ((int)currenthighlight == 1 ){	
	    		g.setColor(Color.white);
	    		g.drawString("Normal",100,100);
	    		g.setColor(Color.red);
	    		g.drawString("Hardcore ",100,200);
	    		g.setColor(Color.white);
	    		g.drawString("R.I.P.",100,300);
    		}
    		if ((int)currenthighlight == 2 ){	
	    		g.setColor(Color.white);
	    		g.drawString("Normal",100,100);
	    		g.drawString("Hardcore ",100,200);
	    		g.setColor(Color.red);
	    		g.drawString("R.I.P.",100,300);
    		}    				
    	}
    	//Drawing the actual game
    	if (mode == 1){
	    	if (health>0){
	    		g.drawImage(thebackground1,0,0,null);
		    	g.drawImage(supership[direction][frame],380,525,null);	
		    	//Draws each Hadoken and possible power ups.
		    	for (EvilBox eb:evilboxes){
		    		g.drawImage(eb.getimage(),eb.getx(),eb.gety(),null);
		    	}
		    	for (GoodBox gb:goodboxes){
		    		g.drawImage(gb.getimage(),gb.getx(),gb.gety(),null);
		    	}
		    	if (invincible == true){
		    	
		    		g.setColor(Color.GREEN);
		    	}
		    	else{
		    		g.setColor(Color.RED);
		    	}
		    	g.fillRect(100,50,(int)health,25);    	
		    	direction = 0;
		    	frameDelay += 1;
		    	if (frameDelay==2){
					frame = (frame +1) % 3;
					frameDelay = 0;
		    	}	   
	    	}
	    	else{
	    		//The good game menu. Shows your score with an option to return back to the menu, which resets the main variables
	    		g.drawImage(thebackground1,0,0,null);
	    		g.setColor(Color.white);
	    		g.setFont(scorefont);
	    		g.drawString(""+score,380,300);
	    		g.drawString ("Press ESC to return",250,400);
	    		if (keys[ESC]){
	    			mode = 0;
	    			health = 600;
	    			score = 0;
	    			evilboxes.clear();
	    			goodboxes.clear();
	    			for (int i = 0; i <75; i++){
						evilboxes.add(new EvilBox((int)(Math.random()*2400-800),(int)(Math.random()*600-900),(int)(Math.random()*10)+1));
					}	
	    		}
	    	}	  
    	}
    }  
}
