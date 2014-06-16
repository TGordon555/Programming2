import java.applet.Applet;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;


public class Base extends Applet implements Runnable{

	private Thread t;
	private String _message = "";
	private Character _mainCharacter = new Character(1, new Location(1, 2), 3), _secondCharacter = new Character(1, new Location(1,4), 3, 1);
	private Level _level1 = new Level(15, "Sunshine Beach");
	private int _scale, _jumpTime = 500; //This time is in milliseconds
	private boolean _jumpingCharOne = false, _fallingCharOne = false, _jumpingCharTwo = false, _fallingCharTwo = false;
	private long _jumpStart, _timeSinceJump;
	private int _mainCharOrigHeight = 1;
	
	public void init(){
		_level1.addCharacter(_mainCharacter);
		_level1.addCharacter(_secondCharacter);
		t = new Thread(this);
		t.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			_scale = getHeight()/10;
			_level1.setScale(_scale);
			_level1.setWidth(getWidth());
			_timeSinceJump = System.currentTimeMillis() - _jumpStart;
			if(_jumpingCharOne){
				 if(_timeSinceJump >= _jumpTime){
					 _jumpingCharOne = false;
					 _fallingCharOne = false;
					 _level1.getCharacterAt(_mainCharacter.getLocation()).setHeight(_mainCharOrigHeight);
				 }else{
				//	 System.out.println(_timeSinceJump + " " + _jumpTime + "\n");
					 if(_timeSinceJump >= _jumpTime/2){
						 _fallingCharOne = true;
						 //System.out.println("falling");
					 }
				 }
			}
			if(_jumpingCharTwo){
				 if(_timeSinceJump >= _jumpTime){
					 _jumpingCharTwo = false;
					 _fallingCharTwo = false;
					 _level1.getCharacterAt(_secondCharacter.getLocation()).setHeight(_mainCharOrigHeight);
				 }else{
				//	 System.out.println(_timeSinceJump + " " + _jumpTime + "\n");
					 if(_timeSinceJump >= _jumpTime/2){
						 _fallingCharTwo = true;
						 //System.out.println("falling");
					 }
				 }
			}
			repaint();
			try{
                t.sleep(1000/30);
            }   catch (InterruptedException e) { ; }
		}
	}
	
	public boolean keyDown(Event e, int key){
		_message = "Value: " + key;
	//	System.out.println((_mainCharacter.getLocation().getHorizontal() - 1) * _scale);
		switch (key) {
		case 100:
			if((_mainCharacter.getLocation().getHorizontal() - 1) * _scale <= getWidth()){
				_level1.moveCharacterTo(_mainCharacter, new Location(_mainCharacter.getLocation().getVertical(), _mainCharacter.getLocation().getHorizontal() + 1));
			}
			break;

		case 97:
			if((_mainCharacter.getLocation().getHorizontal()) * _scale > -1){
				_level1.moveCharacterTo(_mainCharacter, new Location(_mainCharacter.getLocation().getVertical(), _mainCharacter.getLocation().getHorizontal() - 1));
			}
			break;
			
		case 1007:
			if((_secondCharacter.getLocation().getHorizontal() - 1) * _scale <= getWidth()){
				_level1.moveCharacterTo(_secondCharacter, new Location(_secondCharacter.getLocation().getVertical(), _secondCharacter.getLocation().getHorizontal() + 1));
			}
			break;
			
		case 1006:
			if((_secondCharacter.getLocation().getHorizontal()) * _scale > -1){
				_level1.moveCharacterTo(_secondCharacter, new Location(_secondCharacter.getLocation().getVertical(), _secondCharacter.getLocation().getHorizontal() - 1));
			}
			break;
			
		default:
			break;
		}
		
		return true;
	}
	public boolean keyUp(Event e, int key){
		if(key == 32){
			_jumpingCharOne  = true;
			_jumpStart = System.currentTimeMillis();
			_level1.getCharacterAt(_mainCharacter.getLocation()).setHeight(_mainCharOrigHeight + 1);
		}else{
			if(key == 1004){
				_jumpingCharTwo  = true;
				_jumpStart = System.currentTimeMillis();
				_level1.getCharacterAt(_secondCharacter.getLocation()).setHeight(_mainCharOrigHeight + 1);
			}
		}
		return true;
	}
	
	public void paint(Graphics g){
		g.drawString(_message, 10, 20);
		setBackground(Color.cyan);
		g.setColor(Color.blue);
		g.fillRect(0, getHeight() * 9 / 10, getWidth(), getHeight() / 10);
		for(Character c : _level1.getCharacterList()){
			//System.out.println("Have a character");
			//System.out.println(c.getLocation().getHorizontal()* _scale + " " + getWidth());
			//System.out.println(getHeight() - ((c.getLocation().getVertical() + c.getHeight()) * _scale) + " " + getHeight());
			//System.out.println(c.getSprite().getHeight(null) + " " + c.getSprite().getWidth(null));
			/**
			 * Jumping may be a little crazy, and not at all smooth
			 */
			if(_jumpingCharOne || _jumpingCharTwo){
				if(!_fallingCharOne || _fallingCharTwo){
					g.drawImage(c.getSprite(), c.getLocation().getHorizontal() * _scale,  (int) (getHeight() - ((c.getLocation().getVertical() + c.getHeight()) * _scale) - (_timeSinceJump / _jumpTime)* c.getJumpHeight() * _scale), null);
				}else{
					g.drawImage(c.getSprite(), c.getLocation().getHorizontal() * _scale,  (int) (getHeight() - ((c.getLocation().getVertical() + c.getHeight()) * _scale) - (_jumpTime / _timeSinceJump) * _scale), null);
				}
			}else{
				g.drawImage(c.getSprite(), c.getLocation().getHorizontal()* _scale, getHeight() * 9/10 - c.getSprite().getHeight(), null);
			}
			
		}
	}

}
