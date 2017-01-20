/**
 * 
 */
package Simori;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopBoy {
	
	private static String[] songs={"smoke","chain", "money"};
	private static int counter = 0;
	private static Timer timer;
	private static ActionListener actionListener; 
	
	/**
	 * Initialises an action listener which loads the songs
	 * and sets a timer to switch between them every 30 seconds
	 */
	public static void playShopBoy(){
		 actionListener = new ActionListener() {
	            public void actionPerformed(ActionEvent actionEvent) {
	                SaveLoad.load(songs[counter]);
	                counter++;
	                if(counter==songs.length){counter=0;}
	            }
	        };
	        timer = new Timer( 30000, actionListener );
	        timer.setInitialDelay(0);
	        timer.start();
	        
	}
	
	/**
	 * Stops the timer for ShopBoy mode
	 * and removes the listener which loads the songs
	 */
	public static void stop(){
		timer.stop();
		timer.removeActionListener(actionListener);
	}
}
