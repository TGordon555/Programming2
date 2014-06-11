import java.applet.Applet;


public class Game extends Applet implements Runnable{

	Thread t;
	
	public void init(){
		t = new Thread(this);
		t.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//this is a test, please ignore
		while(true){
			repaint();
			try{
                t.sleep(1000/30);
            }   catch (InterruptedException e) { ; }
		}
	}

}
