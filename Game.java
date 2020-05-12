package game.main;

import game.main.Input.Controller;

import com.sun.glass.events.KeyEvent;

public class Game {

	public int time;
	public Controller controlle;
	
	public Game(){
		controlle = new Controller();
		
		}
	
	public void tick(boolean[] key){
		time++;
		
		boolean farward = key[KeyEvent.VK_UP];
		boolean back = key[KeyEvent.VK_DOWN];
		boolean Left = key[KeyEvent.VK_LEFT];
		
		boolean right = key[KeyEvent.VK_RIGHT];
		boolean jump = key[KeyEvent.VK_SPACE];
        boolean crouch = key[KeyEvent.VK_CONTROL];
        boolean run = key[KeyEvent.VK_SHIFT];
		controlle.tick(farward, back, right, Left,jump,crouch,run);
		
		}
}
