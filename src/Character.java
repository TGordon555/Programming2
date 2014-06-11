import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Character {
	
	/**
	 * height is in tiles
	 * location is in tiles
	 */
	int _height; 
	Location _characterLoc;
	BufferedImage sprite;
	
	public Character(int height, Location loc){
		_height = height;
		_characterLoc = loc;
		try {
			BufferedImage sprite = ImageIO.read(new File( "character.png" ));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getHeight(){
		return _height;
	}
	
	public Location getLocation(){
		return _characterLoc;
	}
	
	public void setLocation(Location newLoc){
		_characterLoc = newLoc;
	}
	
	public BufferedImage getSprite(){
		return sprite;
	}
	
}
