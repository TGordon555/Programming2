import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Character {
	
	/**
	 * height is in tiles
	 * location is in tiles
	 */
	private int _height; 
	private Location _characterLoc;
	private BufferedImage sprite;
	
	public Character(int height, Location loc){
		_height = height;
		_characterLoc = loc;
		try {
			sprite = ImageIO.read(new File( "character.png" ));
			System.out.println("found file");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't find file");
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
	
	public void setHeight(int height){
		_height = height;
	}
	
	public BufferedImage getSprite(){
		return sprite;
	}
	
}
