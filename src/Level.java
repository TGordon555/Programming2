import java.util.ArrayList;


public class Level {
	
	/**
	 * Tile length is how long the level is, in tiles. There are 10 tiles per screen height, so levels are measured in tiles, not pixels, 
	 * tile 0,0 is at the bottom left
	 * Name is the level name, may be shown to the player
	 * Levels store what characters are within it
	 */
	int _tileLength;
	String _name;
	
	ArrayList<Character> _characters;
	
	public Level(int tileLength, String name){
		_name = name;
		_tileLength = tileLength;
		_characters = new ArrayList<>();
	}
	
	/**
	 * 
	 * @param c character to be added
	 * @precondition characters aren't added on top of each other 
	 */
	public void addCharacter(Character c){
		_characters.add(c);
	}
	
	public String getName(){
		return _name;
	}
	
	public int getSize(){
		return _tileLength;
	}
	
	public ArrayList<Character> getCharacterList(){
		return _characters;
	}
	
	public Character getCharacterAt(Location loc){
		for(Character c : _characters){
			if(c.getLocation().equals(loc))
				return c;
		}
		return null;
	}
	
	public boolean isOccupied(Location newLoc){
		boolean occupied = false;
		for(Character c : _characters){
			//need to make case to take into account character height, need to decide where tile 0,0 is
			occupied = occupied || (c.getLocation().getHorizontal() == newLoc.getHorizontal() && (newLoc.getVertical() > c.getHeight() + c.getLocation().getVertical() || newLoc.getVertical() < c.getHeight()));
		}
		return occupied;
	}
	
	public void moveCharacterTo(Character c, Location newLoc){
		if(!isOccupied(newLoc))
			_characters.get(_characters.indexOf(c)).setLocation(newLoc);
	}
	
}
