package Simori;

import javax.swing.JToggleButton;
public class OnOff {

	/**
	 * Enables all the menu buttons on the board
	 */
	public static void enableMenuButtons() {
		for(JToggleButton menuButton : GUI.getMenuButtons()){
			menuButton.setEnabled(true);
		}
	}
	
	
	/**
	 * Enables one of the menu buttons/
	 * @param text The text of the enabled button
	 */
	public static void enableMenuButton(String text){
		for(JToggleButton menuButton : GUI.getMenuButtons()){
			if(menuButton.getToolTipText().equals(text))
				menuButton.setEnabled(true);
		}
	}
	
	/**
	 * Disables all the buttons on the board except for the
	 * ON button, OK button and the button passed as a parameter
	 * @param button A button that will not be disabled
	 */
	protected static void disableMenuButtons(JToggleButton button){
		// Does not disable the OK button - the last button in the array
		for(int i=1;i<GUI.getMenuButtons().length-1;i++){
			if(!GUI.getMenuButtons()[i].equals(button))
				GUI.getMenuButtons()[i].setEnabled(false);
		}	
	}
	
	public static void deselectOk(){
		for(JToggleButton menuButton : GUI.getMenuButtons()){
			if(menuButton.getToolTipText().equals("OK"))
				menuButton.setSelected(false);
		}
	}
	
	/**
	 * Disables all the menu buttons on the board other
	 * than the ON button and the OK button
	 */
	protected static void disableMenuButtons(){
		// Does not disable the OK button - the last button in the array
		for(int i=1;i<GUI.getMenuButtons().length;i++){
			GUI.getMenuButtons()[i].setEnabled(false);
		}
	
	}

	/**
	 * Enables all the buttons on the 16x16 grid
	 */
	public static void enableGridButtons() {
		for(int i = 0; i < GUI.display.length; i++){
			for(int j = 0; j < GUI.display[i].length; j++){
				GUI.display[i][j].setEnabled(true);
			}
		}
		
	}
	
	/**
	 * Disables all the buttons on the 16x16 grid
	 */
	public static void disableGridButtons() {
		for(int i = 0; i < GUI.display.length; i++){
			for(int j = 0; j < GUI.display[i].length; j++){
				GUI.display[i][j].setEnabled(false);
			}
		}
	}
	
	/**
	 * Starts running the 16 layers of the board in parallel
	 */
	public static void startThreads(){
		for(int i = 0; i < ChangeLayer.Layers.length; i++){
			ChangeLayer.Layers[i] = new Layer();
			ChangeLayer.Layers[i].setLayerNumber(i);
		}
		for(int i = 0; i < ChangeLayer.Layers.length; i++){
			new Thread(ChangeLayer.Layers[i]).start();
		}
		
		
	}
	
	/**
	 * Stops the threads that represent the 16 layers
	 */
	public static void stopThreads(){
		for(int i = 0; i < ChangeLayer.Layers.length; i++){
			ChangeLayer.Layers[i].setDone(true);
			GUI.setClockHandPosition(15);
			GUI.getTimer().stop();
		}
	}
	
	
	
}
