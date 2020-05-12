package game.main.graphics;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

//import com.sun.prism.Texture;

public class texture {
	public static Render floor = loadBitmap("/textures/floor.png");

public static Render loadBitmap(String filename) {
		try{
			BufferedImage image = ImageIO.read(texture.class.getResource(filename));
			int width = image.getWidth();
			int height = image.getHeight();
			Render result = new Render(width, height);
			image.getRGB(0, 0,width,height, result.pixels, 0, width);
			
			return result;
			
		}catch(Exception e){
			
			System.out.println("crach");
			
			throw new RuntimeException(e);
			
			
			}

	}
	
	
}
