package Simori;
public class ChangeVelocity {
	
	 // the temporary and permanent values for the velocity
	 private static int velocity, tempVelocity;

	/**
	 * Sets the temporary value for the velocity of the playing notes
	 * based on the button pressed on the grid.The value represents the button number
	 * on the grid. Value should be smaller than 128.
	 * @param x The x coordinate of the button pressed - the x-th column
	 * @param y The y coordinate of the button pressed - the y-th row
	 */
	public static void setTempVelocity(int x, int y) {
		GUI.clearBoard();     
		String a;
		// Set the temporary velocity and change the display
		tempVelocity = x+y*16;
		GUI.textField.setText(String.valueOf(tempVelocity));
		if(x+y*16>128){
			 GUI.textField.setText("Not legal");
		 }
		
		// Sets the buttons on the same row and column to selected
		for(int i = 0; i < GUI.display.length; i++)
			for(int j = 0; j < GUI.display[i].length; j++)
				if(i*16+j==tempVelocity || j==x || i==y)
					GUI.display[i][j].setSelected(true);
	}
	
	/**
	 * @return The temporary value of the velocity
	 */
	public static int getTempVelocity() {
		return tempVelocity;
		
	}
	
	/**
	 * Sets the current velocity
	 * @param vl The current velocity
	 */
	public static void setVelocity(int vl) {
		if(vl<129) ChangeLayer.getLayer(ChangeLayer.getCurrentLayer()).setVelocity(vl);
	}
	
	public static int getVelocity(){
		return ChangeLayer.getLayer(ChangeLayer.getCurrentLayer()).getVelocity();
	}
	
}
