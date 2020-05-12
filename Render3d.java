package game.main.graphics;



import java.util.Random;

import game.main.Game;
import game.main.Input.Controller;

public class Render3d extends Render{
    public double[] zBuffer;
    private double RenderDistance = 5000;
   private double farwardGlobal;
    private double right, farward, cosine, sine,up;
     
	public Render3d(int width, int height) {
		super(width, height);
		zBuffer = new double[width*height];
		
	}
	//double time=0;
	
	public void floor (Game game){
		 farward = game.controlle.z;
	    // farward = game.time%100/20.0;
		farwardGlobal = farward;
		 right =  game.controlle.x;
		double rotation =  Math.sin(game.time/40.0)*0.5; //game.controlle.rotation;  //Math.sin(game.time%1000.0/80);
		 cosine = Math.cos(rotation);
		 sine=Math.sin(rotation);
		double floorposition = 8;
		double ceilingposition = 8;
		 up = game.controlle.y;
		double walking = Math.sin(game.time/6.0)*0.5;
	
		if(Controller.runwalk){
			 walking = Math.sin(game.time/6.0)*0.8;
		}
		if(Controller.crouchwalk){
		 walking = Math.sin(game.time/6.0)*0.2;
	 }
		
		for(int y=0; y< height; y++){
			
			double ceiling = ((y+5) - height/2.0) /height;
			double z = (floorposition+ up)/ceiling;
			
			
			if(Controller.walk)
			{ 
				 z = (floorposition+ up+walking)/ceiling;
			}
			if(ceiling<0)
			{
				z = (ceilingposition- up) /-ceiling;
				if(Controller.walk){
					z = (ceilingposition- up-walking) /-ceiling;

					
				}
			}
			
			
			
			//time+=0.0005;
			for(int x=0; x< width; x++){
				double Depth = (x - width/2.0)/height;
				Depth *=z;
			
			double xx = Depth*cosine +z*sine  ;
			
			double yy= z*cosine -  Depth*sine ;
			int xPix=(int)(xx+right);
			int yPix=(int)(yy+farward);
			
			 zBuffer[x+y*width] = z;
				//pixels[x+y*width]= ((xPix & 15)*16) | ((yPix & 15)*16) << 8 ;
			    pixels[x+y*width] = texture.floor.pixels[(xPix & 7) + (yPix & 7)*8];
			//	System.out.println(xx);
			//	System.out.println(yy);
				if(z>100000 ){
					pixels[x+y*width]=0;
				}
			}
		}
		
		
		
		
	/*	pixels[11+10*width]= 0xfffff;
		pixels[12+10*width]= 0xfffff;
		
		pixels[13+10*width]= 0xfffff;
		pixels[14+10*width]= 0xfffff;
		pixels[15+10*width]= 0xfffff;
		
		pixels[16+10*width]= 0xfffff;
		*/
		
		
	}
	
	//-------------- m�thode consacrer pour contruire les murs---------
	
	public void walls(){

Random random = new Random(300);

		//premier careau � droite
		for(int i = 0; i<70000;  i++){
			
			
			  double xx= random.nextDouble();
			  double yy= random.nextDouble();
			  double zz= 1.5-farwardGlobal/5;
			  int xPixel = (int)(xx/zz*height/2 + width/2);
			  int yPixel = (int)(yy/zz*height/2 + height/2);
			  
			   if(xPixel >=0 && yPixel>=0 && xPixel<width && yPixel< height)
			       {
				    
				   pixels[xPixel+yPixel*width]= 0xfffff;
				   
				    }
				}
				
		//2 careau du mur
		for(int i = 0; i<70000;  i++){
			
		
	  double xx= random.nextDouble()-1;
	  double yy= random.nextDouble();
	  double zz= 1.5-farwardGlobal/5;
	  int xPixel = (int)(xx/zz*height/2 + width/2);
	  int yPixel = (int)(yy/zz*height/2 + height/2);
	  
	   if(xPixel >=0 && yPixel>=0 && xPixel<width && yPixel< height)
	       {
		    
		   pixels[xPixel+yPixel*width]= 0xfffff;
		   
		    }
		}
		//3�me carreau du mur 
		for(int i = 0; i<70000;  i++){
			
			
			  double xx= random.nextDouble()-1;
			  double yy= random.nextDouble()-1;
			  double zz= 1.5-farwardGlobal/5;
			  int xPixel = (int)(xx/zz*height/2 + width/2);
			  int yPixel = (int)(yy/zz*height/2 + height/2);
			  
			   if(xPixel >=0 && yPixel>=0 && xPixel<width && yPixel< height)
			       {
				    
				   pixels[xPixel+yPixel*width]= 0xfffff;
				   
				    }
				}
		//les murs de haut
		for(int i = 0; i<70000;  i++){
			
			
			  double xx= random.nextDouble();
			  double yy= random.nextDouble()-1;
			  double zz= 1.5-farwardGlobal/5;
			  int xPixel = (int)(xx/zz*height/2 + width/2);
			  int yPixel = (int)(yy/zz*height/2 + height/2);
			  
			   if(xPixel >=0 && yPixel>=0 && xPixel<width && yPixel< height)
			       {
				    
				   pixels[xPixel+yPixel*width]= 0xfffff;
				   
				    }
				}
				
		//----------deuxi�me mur--------------------------------------------------
		
		//premier careau � droite
		for(int i = 0; i<100000;  i++){
			
			
			  double xx= random.nextDouble();
			  double yy= random.nextDouble();
			  double zz= 3-farwardGlobal/5;
			  int xPixel = (int)(xx/zz*height/2 + width/2);
			  int yPixel = (int)(yy/zz*height/2 + height/2);
			  
			   if(xPixel >=0 && yPixel>=0 && xPixel<width && yPixel< height)
			       {
				    
				   pixels[xPixel+yPixel*width]= 0xfffff;
				   
				    }
				}
				
		//2 careau du mur
		for(int i = 0; i<100000;  i++){
			
		
	  double xx= random.nextDouble()-1;
	  double yy= random.nextDouble();
	  double zz= 3-farwardGlobal/5;
	  int xPixel = (int)(xx/zz*height/2 + width/2);
	  int yPixel = (int)(yy/zz*height/2 + height/2);
	  
	   if(xPixel >=0 && yPixel>=0 && xPixel<width && yPixel< height)
	       {
		    
		   pixels[xPixel+yPixel*width]= 0xfffff;
		   
		    }
		}
		//3�me carreau du mur 
		for(int i = 0; i<100000;  i++){
			
			
			  double xx= random.nextDouble()-1;
			  double yy= random.nextDouble()-1;
			  double zz= 3-farwardGlobal/5;
			  int xPixel = (int)(xx/zz*height/2 + width/2);
			  int yPixel = (int)(yy/zz*height/2 + height/2);
			  
			   if(xPixel >=0 && yPixel>=0 && xPixel<width && yPixel< height)
			       {
				    
				   pixels[xPixel+yPixel*width]= 0xfffff;
				   
				    }
				}
		//les murs de haut
		for(int i = 0; i<100000;  i++){
			
			
			  double xx= random.nextDouble();
			  double yy= random.nextDouble()-1;
			  double zz= 3-farwardGlobal/5;
			  int xPixel = (int)(xx/zz*height/2 + width/2);
			  int yPixel = (int)(yy/zz*height/2 + height/2);
			  
			   if(xPixel >=0 && yPixel>=0 && xPixel<width && yPixel< height)
			       {
				    
				   pixels[xPixel+yPixel*width]= 0xfffff;
				   
				    }
				}
				
	}
	
	//----------------------------rendering wall------------------------------
	
	
	
	
	public void renderwall(double xLeft, double xRight, double zDistance, double yHeight){
		// parametres of calculations for left
		double xcLeft = ((xLeft)-right)*2;
		double zcLeft = ((zDistance)-farward)*2;
		//--creation 4 corners 
		 //-------CornerLeftside
		double rotLeftSideX = xcLeft*cosine - zcLeft*sine;
		double yCornerTL = ((-yHeight)-up)*2;
		double yCornerBL = ((+0.5-yHeight)-up)*2;
		double rotLeftSideZ= zcLeft*cosine + xcLeft*sine;
		
		//parametres of calculations for  right
		
		double xcRight = ((xRight)-right)*2;
		double zcRight = ((zDistance)-farward)*2;
		//--creation 4 corners 
		 //-------CornerRightside
		double rotRightSideX = xcRight = xcRight*cosine - zcRight*sine;
		double yCornerTR = ((-yHeight)-up)*2;
		double yCornerBR = ((+0.5-yHeight)-up)*2;
		double rotRightSideZ= zcRight*cosine + xcRight*sine;
//---expresse all this values to pixels
		double xPixelLeft = (rotLeftSideX/rotLeftSideZ*height + width/2);
		double xPixelRight = (rotRightSideX/rotRightSideZ*height + width/2);
		//---organization for our screen pixels
		     if(xPixelLeft >= xPixelRight){
		    	 System.out.println("wrong");
			                                 return;
		                               }
		int xPixelsLeftInt = (int)(xPixelLeft);
		int xPixelsRightInt = (int)(xPixelRight);
		    if(xPixelsLeftInt < 0){
		                 	xPixelsLeftInt = 0;
		                          }
		
		    if(xPixelRight > width) {
			
		                    	xPixelsRightInt = width;
		                               }
		    //---variables for corners
		double yPixelsTopLeft = (int)(yCornerTL/rotLeftSideZ*height+height/2);
		double yPixelsBottomLeft = (int)(yCornerBL/rotLeftSideZ*height+height/2);
		double yPixelsTopRight = (int)(yCornerTR/rotRightSideZ*height+height/2);
		double yPixelsBottomRight = (int)(yCornerBR/rotRightSideZ*height+height/2);
		 
		
		for(int x= xPixelsLeftInt ; x < xPixelsRightInt; x++){
			 double PixelRotation = (x-xPixelLeft)/(xPixelRight-xPixelLeft);
			 double yTopPixel = yPixelsTopLeft+(yPixelsTopRight-yPixelsTopLeft)*PixelRotation;
			 double yBottomPixel = yPixelsBottomLeft+(yPixelsBottomRight-yPixelsBottomLeft)*PixelRotation;
			  
			 int yPixelTopInt = (int) (yTopPixel);
			 int yPixelBottomInt = (int) (yBottomPixel);
			                       if(yPixelTopInt < 0){
                                        	yPixelTopInt = 0;
                                                 }

                                  if(yTopPixel > height) {
	
                 	                          yPixelTopInt = height;
                                                           }
			 for(int y=yPixelTopInt; y<yPixelBottomInt ; y++){
				 try{
				 pixels[x+y*width]= 0xED2B85;
				 }
				 catch(ArrayIndexOutOfBoundsException e)
				 {
					 e.printStackTrace();
					 continue;
				 }
				 zBuffer[x + y*width] = 0;
			 }
			 
			 
		 }
		
		
	}
	
	
	
	
	
   public void RenderDistanceLimiter(){
	   for(int i=0; i <width*height;i++){
		    int colour = pixels[i];
		    int Brightness = (int)(RenderDistance /(zBuffer[i]));
		    if(Brightness<0)
		    {
		    	Brightness=0;
		    }
		    
		    if  (Brightness>255)
		    {
		    	Brightness=255;
		    }
		    
		    int r= (colour>>16) & 0xff;
		    int g= (colour>>8) & 0xff;
		    int b=(colour)& 0xff;
		     r=r*Brightness/255;
		     g=g*Brightness/255;
		     b=b*Brightness/255;
		     pixels[i]= r << 16 | g << 8 | b;
	   }
  
   
   }





}