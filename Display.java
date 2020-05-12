package game.main;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;






import javax.swing.JFrame;

import game.main.Input.Controller;
import game.main.Input.InputHandler;
import game.main.graphics.Screen;

public class Display extends Canvas implements Runnable  {
	
	
	private static final long serialVersionUID = 1L;
	public static final int Width = 800;
	public static final int Height = 600;
	public  static final String Title = "My First Game";
	
	
	private Thread thread;
	private Screen screen;
	private BufferedImage img;
	private Game game;
	private boolean running = false;
	private int[] pixels;
	private InputHandler input;
	private int OldX = 0;
	private int newX = 0;
	private int fps;
	
	
	public Display() {
		Dimension size = new Dimension(Width, Height);
		setPreferredSize(size);
		setMaximumSize(size);
		screen = new Screen(Width, Height);
		game = new Game();
		img = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
		input = new InputHandler();
	    addFocusListener(input);
		addKeyListener(input);
	addMouseListener(input);
		addMouseMotionListener(input);
	}
	
	public synchronized void start() {
		if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
		

	}
	
	public synchronized void stop() {
		if(!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public void run() {
		
		int frames = 0;
		double unprocessedSeconds = 0;
		long previousTime = System.nanoTime();
		double secondsPerTick = 1/60.0;
		int tickCount = 0;
		boolean ticked = false;
		
		while(running) {
			long currentTime = System.nanoTime();
			long passedTime = currentTime - previousTime;
			previousTime = currentTime;
			unprocessedSeconds += passedTime / 1000000000.0;
			 requestFocus();
			while(unprocessedSeconds > secondsPerTick) {
				tick();
				unprocessedSeconds -= secondsPerTick;
				ticked = true;
				tickCount++;
				if (tickCount % 60 == 0) {
					System.out.println(frames + "fps");
					fps = frames;
					previousTime += 1000;
					frames = 0;
					
				}
			}
			if(ticked) {
				render();
				frames++;
				
				
			}
			render();
			frames++;
			//System.out.println("X :"+InputHandler.MouseX + " Y: "+InputHandler.MouseY);
			 newX = InputHandler.MouseX;
			 
			if(newX > OldX)
			{
			 System.out.println("right : ");
			 Controller.turnright = true;
			}
			
			if(newX< OldX){
				System.out.println("Left : ");
				Controller.turnleft = true;
			}
			
			if(newX == OldX){
				System.out.println("still :");
				 Controller.turnright = false;
				 Controller.turnleft = false;
			}
			OldX = newX;
		}
	}
	
	private void tick() {
		game.tick(input.key);
	}
	
	private void render() {
		BufferStrategy bs =this.getBufferStrategy();
		if(bs==null) {
			createBufferStrategy(3);
			return;
			
		}
		
		screen.render(game);
		
		
		for(int i=0; i< Width * Height; i++) {
			pixels[i] = screen.pixels[i];
			
		}
			Graphics g = bs.getDrawGraphics();
			g.drawImage(img, 0, 0, Width+10, Height+10, null);
		    g.setFont(new Font("Times New Roman",0,50));
		    g.setColor(Color.LIGHT_GRAY);
		    g.drawString(fps+ " test ", 100, 100);
		
			g.dispose();
			bs.show();
		
		
	}
	
	public static void main(String[] args) {
		BufferedImage cursor = new BufferedImage(16,16, BufferedImage.TYPE_INT_ARGB);
		Cursor blank = Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(0,0), "blank") ; 
		Display game = new Display();
		JFrame frame = new JFrame();
		frame.add(game);
		frame.pack();
	    frame.getContentPane().setCursor(blank);
		frame.setTitle(Title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setSize(Width, Height);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
	
		System.out.println("Running...");
		
		game.start();
	} 

}
