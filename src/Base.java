import java.applet.Applet;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;


public class Base extends Applet implements Runnable{

	private Thread t;
	private String _message = "";
	private Character _mainCharacter = new Character(1, new Location(1, 2));
	private Level _level1 = new Level(15, "Sunshine Beach");
	private int _scale;
	
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
			repaint();
			try{
                t.sleep(1000/30);
            }   catch (InterruptedException e) { ; }
		}
	}
	
	public boolean keyDown(Event e, int key){
		_message = "Value: " + key;
		if(key == 100){
			_level1.moveCharacterTo(_mainCharacter, new Location(_mainCharacter.getLocation().getVertical(), _mainCharacter.getLocation().getHorizontal() + 1));
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
			System.out.println(c.getSprite().getHeight(null) + " " + c.getSprite().getWidth(null));
			g.drawImage(c.getSprite(), c.getLocation().getHorizontal()* _scale, getHeight() - ((c.getLocation().getVertical() + c.getHeight()) * _scale), new ImageObserver() {
				
				@Override
				public boolean imageUpdate(Image img, int infoflags, int x, int y,
						int width, int height) {
					System.out.println("Update");
					// TODO Auto-generated method stub
					return false;
				}
			});
		}
	}

}
