
public class Character {
	
	/**
	 * height is in tiles
	 * location is in tiles
	 */
	int _height; 
	Location _characterLoc;
	
	public Character(int height, Location loc){
		_height = height;
		_characterLoc = loc;
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
	
}
