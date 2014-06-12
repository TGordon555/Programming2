
public class Location {
	
	/**
	 * location is defined as the lowest tile a character or object has, in tiles
	 */
	
	private int _verticalLoc, _horizontalLoc;
	
	public Location(int verticalLoc, int horizontalLoc){
		_verticalLoc = verticalLoc;
		_horizontalLoc = horizontalLoc;
	}
	
	public int getVertical(){
		return _verticalLoc;
	}
	
	public int getHorizontal(){
		return _horizontalLoc;
	}
	
	public boolean equals(Location l){
		return l.getHorizontal() == _horizontalLoc && l.getVertical() == _verticalLoc;
	}
}
