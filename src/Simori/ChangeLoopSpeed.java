package Simori;
public class ChangeLoopSpeed {

	 // the temporary and permanent values of the loop speed
	 private static int loopSpeed = 100;
	 private static int tempLoopSpeed;

	/**
	 * Sets the temporary loop speed for the clock hand based on the
	 * button pressed on the grid. The value represents the button number
	 * on the grid. Value should be smaller than 160.
	 * @param x The x coordinate of the button - the x-th column
	 * @param y The y coordinate of the button - the y-th row
	 */
	public static void setTempLoopSpeed(int x, int y) {
		GUI.clearBoard();
		String a;
		 tempLoopSpeed = loopSpeed;
		 
		 tempLoopSpeed = x+y*16;
		 GUI.textField.setText(String.valueOf(tempLoopSpeed));
		 if(x+y*16>160){
			 GUI.textField.setText("Not legal");
		 }
		 for(int i=0;i<GUI.display.length;i++)
			 for(int j=0;j<GUI.display[i].length;j++)
				 if(j==x || i==y)
					 GUI.display[i][j].setSelected(true);
		
	}
	
	/**
	 * @return The temporary value of the loop speed
	 */
	public static int getTempLoopSpeed() {
		return tempLoopSpeed;
		
	}
	
	/**
	 * Sets the loop speed of the clock hand
	 * @param ls The loop speed
	 */
	public static void setLoopSpeed(int ls) {
		if(ls<160){
			int nls = 159 - ls;
			loopSpeed = nls*3;
			GUI.getTimer().setDelay(loopSpeed);
		}		
	}
	
	/**
	 * Sets the loop speed of the clock hand
	 * @param ls The loop speed
	 */
	public static void setRealLoopSpeed(int ls) {		
		if(ls<160) GUI.getTimer().setDelay(ls);		
	}
	
	/**
	 * Gets the loop speed
	 * @return Loop speed
	 */
	public static int getLoopSpeed(){
		return loopSpeed;
	}
	
}
