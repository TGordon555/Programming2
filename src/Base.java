import java.applet.Applet;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;


public class Base extends Applet implements Runnable{

	private Thread t;
	private String _message = "";
	private Character _mainCharacter = new Character(1, new Location(1, 2), 3);
	private Level _level1 = new Level(15, "Sunshine Beach");
	private int _scale, _jumpTime = 500; //This time is in milliseconds
	private boolean _jumping = false, _falling = false;
	private long _jumpStart, _timeSinceJump;
	
	public void init(){
		_level1.addCharacter(_mainCharacter);
		t = new Thread(this);
		t.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			_scale = getHeight()/10;
			_timeSinceJump = System.currentTimeMillis() - _jumpStart;
			if(_jumping){
				 if(_timeSinceJump >= _jumpTime){
					 _jumping = false;
					 _falling = false;
					_level1.getCharacterAt(_mainCharacter.getLocation()).setHeight(1);
				 }else{
					 System.out.println(_timeSinceJump + " " + _jumpTime + "\n");
					 if(_timeSinceJump >= _jumpTime/2){
						 _falling = true;
						 System.out.println("falling");
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
		System.out.println((_mainCharacter.getLocation().getHorizontal() - 1) * _scale);
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
			
		default:
			break;
		}
		
		return true;
	}
	public boolean keyUp(Event e, int key){
		if(key == 32){
			_jumping  = true;
			_jumpStart = System.currentTimeMillis();
			_level1.getCharacterAt(_mainCharacter.getLocation()).setHeight(2);
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
			if(_jumping){
				if(!_falling){
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
