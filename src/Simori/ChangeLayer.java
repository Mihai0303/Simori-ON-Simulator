package Simori;
public class ChangeLayer{
	
	public static Layer[] Layers = new Layer[16];
	
	 // a variable for storing 
	 private static int tempLayer;
	 private static int currentLayer;

	/**
	 * Sets the temporary value of the current layer based on the
	 * button pressed on the grid. The value represents the button number
	 * on the grid.
	 * @param x The x coordinate of the button pressed - the x-th column
	 * @param y The y coordinate of the button pressed - the y-th column
	 */
	public static void setTempLayer(int x,int y){
		GUI.clearBoard();
		tempLayer = y;
		GUI.textField.setText(String.valueOf(tempLayer));
		
		for(int i = 0; i < GUI.display[tempLayer].length;i++){
			GUI.display[tempLayer][i].setSelected(true);
		}
	}
	
	/**
	 * @return The temporary value of the layer
	 */
	public static int getTempLayer(){
		return tempLayer;
	}
	
	/**
	 * @return the current layer of the board
	 */
	public static int getCurrentLayer(){
		return currentLayer;
	}
	
	/**
	 * Gets the given position in the
	 * array of layers for the board
	 * @param l The layer to return
	 * @return The layer for the position given
	 */
	public static Layer getLayer(int l){
		return Layers[l];
	}
	
	/**
	 * Sets the value of the current layer
	 * @param l The value of the layer
	 */
	public static void setCurrentLayer(int l){
		currentLayer = l;
	}
	
	/**
	 * Loads a layer on to the board
	 * @param l The layer to be loaded
	 */
	public static void loadLayer(int l){
		for(int i = 0; i < GUI.display.length; i++)
			for(int j = 0; j < GUI.display[i].length; j++)
				if(Layers[l].getContents(i,j))
					GUI.display[j][i].setSelected(true);
	}

	
}
