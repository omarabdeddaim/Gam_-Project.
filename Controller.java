package game.main.Input;


public class Controller {
	public double y,x,z,rotation,xs,zs,rotations;
	public static boolean turnleft = false;
	public static boolean turnright = false;
	public static boolean walk = false;
	public static boolean crouchwalk = false;
	public static boolean runwalk = false;
    public void  tick (boolean farward, boolean back , boolean right, boolean left,boolean jump, boolean crouch, boolean run){
	               double rotationSpeed = 0.05;
	              double walkSpeed = 0.5;
	              double xMoove = 0;
  	              double zMoove = 0;
                  double JumpHeight = 0.5;
                 
                  
	  if(farward){
		  zMoove++;
		  walk = true;
	  }
	  
	  if(back){
		  zMoove--;
		  walk = true;
	  }
	  
	  if(left){
		  xMoove--;
		  walk = true;
	  }
	  
	  if(right){
		  xMoove++;
		  walk = true;

	  }
	  
	  if(turnleft){
		  rotations -=rotationSpeed;
		


	  }
	  
	  if(turnright){
		  rotations +=rotationSpeed; 
		

	  }
	  if(jump){
		  
		  y += JumpHeight;
		  run = false;
		 walkSpeed = 0;
		  walk = false;
		  

		  
	  }
	  if(crouch){
		  y -= JumpHeight;
		  run = false;
		  walk = false;
		  crouchwalk = true;
		  
		  walkSpeed = 0.2;

	  }
	  if(run){
		  walkSpeed = 0.9;
		  walk = true;
		  runwalk= true;

	  }
   if(!back && !farward && !left && !right ){
	  walk = false;
		  
		  
	  }
   if(!crouch){
	 
	  crouchwalk=false;
   }
   if(!run){
	   runwalk = false;
   }
	  
	  xs += (xMoove*Math.cos(rotation)+ zMoove*Math.sin(rotation))*walkSpeed;
	  zs += (zMoove*Math.cos(rotation)- xMoove*Math.sin(rotation))*walkSpeed;
	  x+=xs;
	  y *= 0.9;
	  z+=zs;
	  xs*=0.1;
	  zs*=0.1;
	  rotation += rotations;
	  rotations*=0.000008;
}
	

}
