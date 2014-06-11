import java.applet.Applet;
import java.awt.Event;
import java.awt.Graphics;


public class Base extends Applet implements Runnable{

	Thread t;
	String _message;
	
	public void init(){
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
		return true;
	}
	
	public void paint(Graphics g){
		g.drawString(_message, 10, 20);
	}

}
