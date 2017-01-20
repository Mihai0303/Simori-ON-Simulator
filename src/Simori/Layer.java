package Simori;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class Layer implements Runnable {
	
	Boolean[][] contents;
	private int instrument = 22;
	private int velocity = 40;
	// determines when the thread stops
	private volatile boolean done = false;
	private int layerNo;
	Synthesizer synth;
	MidiChannel[] channels;
	
	
	/**
	 * Constructor for the Layer object. 
	 * Sets up a two dimensional array and initializes the
	 * synthesizer for playing the sounds on the board.
	 * Opens the synthesizer and sets up the array of 
	 * channels for the board.
	 */
	public Layer(){
		contents = new Boolean[16][16];		
		for(int i = 0; i < 16; i++){
			for(int j = 0; j < 16; j++){
				contents[i][j] = false;
			}
		}
		
		try {
			synth = MidiSystem.getSynthesizer();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
		try {
			synth.open();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
		channels = synth.getChannels();
	}
	
	/**
	 * Sets the value of done
	 * @param d True if the loop should finish playing
	 */
	public void setDone(boolean d){
		done = d;
	}
	
	/**
	 * Sets the velocity of the layer
	 * @param v The velocity to be set
	 */
	public void setVelocity(int v){
		velocity = v;
	}
	
	/**
	 * Returns the instrument that the current layer is
	 * playing
	 * @return The instrument currently playing
	 */
	public int getInstrument(){ 
		return instrument;
	}
	
	/**
	 * Sets the instrument for the current layer
	 * @param i The instrument for the current layer
	 */
	public void setInstrument(int i){
		instrument = i;
	}


	/**
	 * Run method for a Layer object
	 */
	@Override
	public void run() {
		playStart();
		while(!done){		
		for(int i = 0; i < contents.length; i++){
			
			for(int j = 0; j < contents[i].length; j++){
				
				int realCHP = GUI.getClockHandPosition();
				if(realCHP == 16) realCHP = 15;

				if(contents[realCHP][j]){
					channels[layerNo].programChange(instrument);
					channels[layerNo].noteOn( 60-j, velocity ); 
				}		
				
			}
		}
		
		if(GUI.getClockHandPosition() == ChangeLoopPoint.getLoopPoint()) 
			channels[layerNo].allNotesOff();
		}
		
		playStop();
		return;
	}
	
	/**
	 * Clears the entire contents of the layer.
	 * Sets all values to false
	 */
	public void clear(){
		for(int i = 0; i < contents.length; i++){			
			for(int j = 0; j < contents[i].length; j++){
				contents[i][j]=false;
			}
		}
	}
	
	/**
	 * Returns the value at the i, j point in the
	 * contents array
	 * @return The value at the i, j position in the
	 * array representing the layer
	 */
	public boolean getContents(int i, int j){
		return this.contents[i][j];
	}
	
	/**
	 * Sets the value for the i, j position in the array
	 * @param a The value to be set
	 */
	public void setContents(int i, int j, boolean a){
		this.contents[i][j] = a;
	}
	
	/**
	 * @return The array representing the contents of the layer
	 */
	public Boolean[][] getContentArray(){
		return this.contents;
	}
	
	public void setLayerNumber(int n){
		layerNo = n;
	}
	
	/**
	 * Plays a sound when the board is started
	 */
	private void playStart(){	
		channels[1].programChange(52);
		channels[1].noteOn(45, velocity+60);
		try {Thread.sleep(200);}
		catch (InterruptedException e) {e.printStackTrace();}
		channels[1].noteOn(50, velocity+60);
		try {Thread.sleep(200);}
		catch (InterruptedException e) {e.printStackTrace();}
		channels[1].noteOn(53, velocity+60);
		channels[1].allNotesOff();	
	}
	
	/**
	 * Plays a sound when the board is turned off
	 */
	private void playStop(){
		try {Thread.sleep(200);}
		catch (InterruptedException e) {e.printStackTrace();}
		channels[1].programChange(52);
		channels[1].noteOn(45, velocity+60);
		try {Thread.sleep(200);}
		catch (InterruptedException e) {e.printStackTrace();}
		channels[1].noteOn(50, velocity+60);
		try {Thread.sleep(200);}
		catch (InterruptedException e) {e.printStackTrace();}
		channels[1].noteOn(55, velocity+60);
		channels[1].allNotesOff();
	}
	
	/**
	 * Plays a sound when the user is denied a value in one of the modes
	 */
	public void playDenied(){
		try {Thread.sleep(200);}
		catch (InterruptedException e) {e.printStackTrace();}
		channels[1].programChange(52);
		channels[1].noteOn(55, velocity+80);
		channels[1].allNotesOff();
	}
	
	/**
	 * Play a sound when the user introduces a valid value
	 * in one of the modes
	 */
	public void playAccepted(){
		try {Thread.sleep(200);}
		catch (InterruptedException e) {e.printStackTrace();}
		channels[1].programChange(52);
		channels[1].noteOn(55, velocity+80);
		channels[1].allNotesOff();
	}

	public int getVelocity() {
		return velocity;
	}
}

