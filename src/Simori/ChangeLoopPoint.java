package Simori;
public class ChangeLoopPoint {
	
	 // the temporary and permanent value of the loop point
	 private static int loopPoint = 15;
	 private static int tempLoopPoint;

	/**
	 * Sets the temporary value of the loop point based on the column
	 * pressed 
	 * @param x The x coordinate of the button pressed
	 * @param y The y coordinate of the button pressed
	 */
	public static void setTempLoopPoint(int x, int y){
		GUI.clearBoard();
		tempLoopPoint = x;
		GUI.textField.setText(String.valueOf(tempLoopPoint));
		
		for(int i = 0; i < GUI.display[tempLoopPoint].length;i++){
			GUI.display[i][tempLoopPoint].setSelected(true);
		}
	}
	
	/**
	 * @return The temporary value of the loop point
	 */
	public static int getTempLoopPoint(){
		return tempLoopPoint;
	}
	
	/**
	 * @return the permanent value of the loop point
	 */
	public static int getLoopPoint(){
		return loopPoint;
	}
	
	/**
	 * Sets the loop point to the value given
	 */
	public static void setLoopPoint(int p){
		if(p<16)loopPoint = p;
	}
	
}
