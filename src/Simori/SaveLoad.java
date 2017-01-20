package Simori;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveLoad {
	
	/**
	 * Saves the contents of the 16 arrays into a .song file
	 */
	public static void save() {		
		try{
			FileOutputStream fos = new FileOutputStream(GUI.textField.getText() + ".song");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			Boolean[][][] newArray = new Boolean[16][16][16];
			int[] instruments = new int[17];
			for(int i=0;i<16;i++){
						newArray[i] = ChangeLayer.getLayer(i).contents;
			}
			oos.writeObject(newArray);
			for(int j=0;j<17;j++){
				if(j==16){
					instruments[j] = ChangeLoopSpeed.getLoopSpeed();
					System.out.println(instruments[j]);
				}else{
					instruments[j] = ChangeLayer.getLayer(j).getInstrument();
				}
			}
			oos.writeObject(instruments);
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	/**
	 * Loads the contents of the specified file into the layers of the running
	 * system
	 */
	public static void load() throws Exception{
		Boolean[][][] newArray = new Boolean[16][16][16];
		int[] instruments = new int[17];
		FileInputStream fis = new FileInputStream(GUI.textField.getText() + ".song");
		ObjectInputStream iis = new ObjectInputStream(fis);
		newArray = (Boolean[][][]) iis.readObject();
		for(int i=0;i<16;i++){
			ChangeLayer.Layers[i].contents = newArray[i];
		}
		instruments = (int[]) iis.readObject();
		for(int j=0;j<17;j++){
			if(j==16){
				ChangeLoopSpeed.setRealLoopSpeed(instruments[j]);
			}else{
				ChangeLayer.getLayer(j).setInstrument(instruments[j]);
			}
		}
		ChangeLayer.loadLayer(ChangeLayer.getCurrentLayer());		
	}
	
	public static void load(String s) {
		Boolean[][][] newArray = new Boolean[16][16][16];
		int[] instruments = new int[17];
		try{
			FileInputStream fis = new FileInputStream(s + ".song");
			ObjectInputStream iis = new ObjectInputStream(fis);
			newArray = (Boolean[][][]) iis.readObject();
			for(int i=0;i<16;i++){
				ChangeLayer.Layers[i].contents = newArray[i];
			}
			instruments = (int[]) iis.readObject();
			GUI.clearBoard();
			for(int j=0;j<17;j++){
				if(j==16){
					System.out.println(instruments[j]);
					ChangeLoopSpeed.setRealLoopSpeed(instruments[j]);
				}else{
					ChangeLayer.getLayer(j).setInstrument(instruments[j]);
				}
			}
			ChangeLayer.loadLayer(ChangeLayer.getCurrentLayer());
		} catch (Exception e){
			e.printStackTrace();
		}		
	}
	
	/**
	 * Sets the display based on the button pressed
	 * @param x The x-th column of the board
	 * @param y The y-th row of the board
	 */
	public static void setFileName(int x, int y){
		GUI.deselectKeyboard();
		GUI.textField.setText(addCharToTempFileName(x, y));		
		for(int i = 0; i < 16; i++){
			for(int j = 0; j < 16; j++){
				if(j==x && i == y){
					GUI.display[i][j].setSelected(true);
				}
			}
		}
	}
	
	
	/**
	 * Adds a character to the name of the file to be saved or loaded
	 * based on the coordinates of the pressed button. The entire 4th row
	 * erases the last character.
	 * @param x The x-th column of the board
	 * @param y The y-th row of the board
	 * @return The previous text with the new character at the end
	 */
	public static String addCharToTempFileName(int x, int y){
		String tempFileName = GUI.textField.getText();
		
		if (tempFileName != null && tempFileName.length() > 0 && y == 3) {
		      tempFileName = tempFileName.substring(0, tempFileName.length()-1);
		}
		
		if(y==0){
			if(x==0){  tempFileName += '1';}
			if(x==1){  tempFileName += '2';}
			if(x==2){  tempFileName += '3';}
			if(x==3){  tempFileName += '4';}
			if(x==4){  tempFileName += '5';}
			if(x==5){  tempFileName += '6';}
			if(x==6){  tempFileName += '7';}
			if(x==7){  tempFileName += '8';}
			if(x==8){  tempFileName += '9';}
			if(x==9){  tempFileName += '0';}
			if(x==10){  tempFileName += 'a';}
			if(x==11){  tempFileName += 'b';}
			if(x==12){  tempFileName += 'c';}
			if(x==13){  tempFileName += 'd';}
			if(x==14){  tempFileName += 'e';}
			if(x==15){  tempFileName += 'f';}
		}
		if(y==1){			
			if(x==0){  tempFileName += 'g';}
			if(x==1){  tempFileName += 'h';}
			if(x==2){  tempFileName += 'i';}
			if(x==3){  tempFileName += 'j';}
			if(x==4){  tempFileName += 'k';}
			if(x==5){  tempFileName += 'l';}
			if(x==6){  tempFileName += 'm';}
			if(x==7){  tempFileName += 'n';}
			if(x==8){  tempFileName += 'o';}
			if(x==9){  tempFileName += 'p';}
			if(x==10){  tempFileName += 'q';}
			if(x==11){  tempFileName += 'r';}
			if(x==12){  tempFileName += 's';}
			if(x==13){  tempFileName += 't';}
			if(x==14){  tempFileName += 'u';}
			if(x==15){  tempFileName += 'v';}
		}
		if(y==2){
			if(x==0){  tempFileName += 'w';}
			if(x==1){  tempFileName += 'x';}
			if(x==2){  tempFileName += 'y';}
			if(x==3){  tempFileName += 'z';}
		}		
		return tempFileName;
		
	}

}
