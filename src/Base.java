import java.applet.Applet;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;


public class Base extends Applet implements Runnable{

	Thread t;
	String _message;
	Character _mainCharacter = new Character(1, new Location(1, 2));
	Level _level1 = new Level(15, "Sunnshine Beach");
	
	public void init(){
		_level1.addCharacter(_mainCharacter);
		t = new Thread(this);
		t.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			
			
			repaint();
			try{
                t.sleep(1000/30);
            }   catch (InterruptedException e) { ; }
		}
	}
	
	public boolean keyUp(Event e, int key){
		_message = "Value: " + key;
		if(key == 100){
			_level1.moveCharacterTo(_mainCharacter, new Location(_mainCharacter.getLocation()._verticalLoc, _mainCharacter.getLocation()._horizontalLoc + 1));
		}
		return true;
	}
	
	public void paint(Graphics g){
		g.drawString(_message, 10, 20);
		setBackground(Color.cyan);
		g.setColor(Color.blue);
		g.fillRect(0, getHeight() * 9 / 10, getWidth(), getHeight() / 10);
	}

}
