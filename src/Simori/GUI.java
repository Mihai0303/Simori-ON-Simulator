package Simori;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.sound.midi.Instrument;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.Timer;

public class GUI extends JFrame{


	//this two dimensional ToggleButton array represents  the display
	static JToggleButton[][] display = new JToggleButton[16][16];
	
	// the starting position for the clock hand
	private static int clockHandPosition = 0;
	
	//this ToggleButton array holds all the menu buttons (ON, L1-4, R1-4, OK)
	private static JToggleButton[] menuButtons = new JToggleButton[10];
	
	
	//the text field to display information at the bottom of the board
	static JTextField textField = new JTextField();
	 	 
	private final static ImageIcon DEFAULT = new ImageIcon("imgs//grid.jpg");
	private final static ImageIcon SCANNED = new ImageIcon("imgs//grids.jpg");
	private final static String[] keyboardArray = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
		       "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
		       "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
	private static ImageIcon[] keyboardIconArray = new ImageIcon[36];
	private static ImageIcon[] keyboardSelectedArray = new ImageIcon[36];
	 
	private static Synthesizer synthesizer = getSynthesizer();
	private static Soundbank bank = synthesizer.getDefaultSoundbank();
	static Instrument[] instruments = bank.getInstruments();
	 
	private static Timer timer;

	 
	 public static Modes currentMode = Modes.ONOFFMODE;
	 
	 /**
	  * Sets up the GUI for the board. Creates the 16x16 grid of buttons,
	  * the menu buttons and adds them to the board.
	  */
	public GUI(){
		super("Simori");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(215,215,215));
		this.setLayout(null);
		
		
		JPanel central = new JPanel();
		central.setSize(690,690);
		central.setLayout(new GridLayout(16,16));
		central.setBackground(new Color(215,215,215));
				
		ActionListener modeListener = new ChangeModeListener();
		ActionListener gridListener = new GridListener();

		
		for(int i = 0; i < display.length; i++){
			for(int j = 0; j < display[i].length; j++){
				JToggleButton button = new JToggleButton();
								
				button.setOpaque(true);
				button.setContentAreaFilled(true);
				button.setBorderPainted(false);
				button.setSelectedIcon(SCANNED);
				button.setIcon(DEFAULT);
				
				display[i][j] = button;							
				button.addActionListener(gridListener);						
				central.add(button);
			}
		}
		
		for(int i=0;i<36;i++){
			keyboardIconArray[i] = new ImageIcon("imgs//"+keyboardArray[i]+".jpg");
			keyboardSelectedArray[i] = new ImageIcon("imgs//"+keyboardArray[i]+"s.jpg");
		}
		this.add(central);
		central.setBounds(110, 70, 690, 690);
		
		ChangeLayer.Layers = new Layer[16];
		for(int i = 0; i < ChangeLayer.Layers.length; i++){
			ChangeLayer.Layers[i] = new Layer();
		}

		
		JToggleButton ON = new JToggleButton();
		ON.setToolTipText("ON");;
		menuButtons[0] = ON;
		
		JToggleButton L1 = new JToggleButton();
		L1.setToolTipText("L1");
		menuButtons[1] = L1;
		JToggleButton L2 = new JToggleButton();
		L2.setToolTipText("L2");
		menuButtons[2] = L2;
		JToggleButton L3 = new JToggleButton();
		L3.setToolTipText("L3");
		menuButtons[3] = L3;
		JToggleButton L4 = new JToggleButton();
		L4.setToolTipText("L4");
		menuButtons[4] = L4;
		
		JToggleButton R1 = new JToggleButton();
		R1.setToolTipText("R1");
		menuButtons[5] = R1;
		JToggleButton R2 = new JToggleButton();
		R2.setToolTipText("R2");
		menuButtons[6] = R2;
		JToggleButton R3 = new JToggleButton();
		R3.setToolTipText("R3");
		menuButtons[7] = R3;
		JToggleButton R4 = new JToggleButton();
		R4.setToolTipText("R4");
		menuButtons[8] = R4;
		
		JToggleButton OK = new JToggleButton();
		OK.setToolTipText("OK");
		menuButtons[9] = OK;
					
				
		this.add(ON);
		ON.setBounds(400, 5, 60, 60);
		ON.setIcon(new ImageIcon("imgs//ON.jpg"));
		ON.setSelectedIcon(new ImageIcon("imgs//ONs.jpg"));
		ON.setBorderPainted(false);
		ON.addActionListener(modeListener);
		
		this.add(L1);
		L1.setBounds(20, 100, 70, 70);
		L1.setIcon(new ImageIcon("imgs//L1.jpg"));
		L1.setSelectedIcon(new ImageIcon("imgs//L1s.jpg"));
		L1.setBorderPainted(false);
		L1.addActionListener(modeListener);
		this.add(L2);
		L2.setBounds(20, 250, 70, 70);
		L2.setIcon(new ImageIcon("imgs//L2.jpg"));
		L2.setSelectedIcon(new ImageIcon("imgs//L2s.jpg"));
		L2.setBorderPainted(false);
		L2.addActionListener(modeListener);
		this.add(L3);
		L3.setBounds(20, 400, 70, 70);
		L3.setIcon(new ImageIcon("imgs//L3.jpg"));
		L3.setSelectedIcon(new ImageIcon("imgs//L3s.jpg"));
		L3.setBorderPainted(false);
		L3.addActionListener(modeListener);
		this.add(L4);
		L4.setBounds(20, 550, 70, 70);
		L4.setIcon(new ImageIcon("imgs//L4.jpg"));
		L4.setSelectedIcon(new ImageIcon("imgs//L4s.jpg"));
		L4.setBorderPainted(false);
		L4.addActionListener(modeListener);
		
		this.add(R1);
		R1.setBounds(820, 100, 70, 70);
		R1.setIcon(new ImageIcon("imgs//R1.jpg"));
		R1.setSelectedIcon(new ImageIcon("imgs//R1s.jpg"));
		R1.setBorderPainted(false);
		R1.addActionListener(modeListener);
		this.add(R2);
		R2.setBounds(820, 250, 70, 70);
		R2.setIcon(new ImageIcon("imgs//R2.jpg"));
		R2.setSelectedIcon(new ImageIcon("imgs//R2s.jpg"));
		R2.setBorderPainted(false);
		R2.addActionListener(modeListener);
		this.add(R3);
		R3.setBounds(820, 400, 70, 70);
		R3.setIcon(new ImageIcon("imgs//R3.jpg"));
		R3.setSelectedIcon(new ImageIcon("imgs//R3s.jpg"));
		R3.setBorderPainted(false);
		R3.addActionListener(modeListener);
		this.add(R4);
		R4.setBounds(820, 550, 70, 70);
		R4.setIcon(new ImageIcon("imgs//R4.jpg"));
		R4.setSelectedIcon(new ImageIcon("imgs//R4s.jpg"));
		R4.setBorderPainted(false);
		R4.addActionListener(modeListener);
		
		this.add(OK);
		OK.setBounds(550, 770, 70, 70);
		OK.setIcon(new ImageIcon("imgs//OK.jpg"));
		OK.setSelectedIcon(new ImageIcon("imgs//OKs.jpg"));
		OK.setBorderPainted(false);
		OK.addActionListener(modeListener);
		
		this.add(textField);
		textField.setEditable(false);
		textField.setText("LCD");
		textField.setBounds(120, 770, 280, 70);
		

		//sets the initial behaviour
		OnOff.disableMenuButtons();
		OnOff.disableGridButtons();
		
		this.setSize(915, 890);
		this.setResizable(false);
		this.setVisible(true);
		
	}

	/**
	 * Returns the synthesizer of the MidiSystem.
	 * @return The Synthesizer of the MidiSystem
	 */
	private static Synthesizer getSynthesizer(){
		try{
			return MidiSystem.getSynthesizer();
		}catch(Exception e){}
		return null;
	}
	
	/**
	 * Sets the current mode of the board
	 * @param mode The mode of the board
	 */
	public static void setCurrentMode(Modes mode){
		currentMode = mode;
	}
	
	/**
	 * Clears the entire 16x16 grid of the board
	 */
	public static void clearBoard() {
		for(int i = 0; i < display.length; i++){
			for(int j = 0; j < display[i].length; j++){
				display[i][j].setSelected(false);
				display[i][j].setIcon(DEFAULT);
			}
		}	
		
	}
	
	/**
	 * Sets all the menu buttons to unselected other than 
	 * the button passed as an argument
	 * @param button The menu button that will not be de-selected
	 */
	public static void clearMenuButtons(JToggleButton button) {		
		for(JToggleButton menuButton : menuButtons)
			if(!menuButton.equals(button) && !menuButton.getToolTipText().equals("ON"))
				menuButton.setSelected(false);		
	}
	
	/**
	 * Displays alphanumeric characters over the first 36 buttons of the
	 * board
	 */
	public static void displayKeyboard(){
		for(int i=0;i<16;i++)
			for(int j=0;j<16;j++)
				if(i*16+j<36){
					display[i][j].setIcon(keyboardIconArray[i*16+j]);
					display[i][j].setSelectedIcon(keyboardSelectedArray[i*16+j]);
				}	
	}
	
	/**
	 * Erases the text displayed over the first 36 buttons of the
	 * board
	 */
	public static void eraseKeyboard(){
		for(int i=0;i<16;i++)
			for(int j=0;j<16;j++){
				display[i][j].setIcon(DEFAULT);
				display[i][j].setSelectedIcon(SCANNED);
			}
	}
	
	public static void deselectKeyboard(){
		for(int i=0;i<16;i++)
			for(int j=0;j<16;j++)
				display[i][j].setSelected(false);
	}
	
	/**
	 * Clears the contents of all the layers
	 */
	public static void clearLayers(){
		for(int i=0;i<ChangeLayer.Layers.length;i++){
			ChangeLayer.Layers[i].clear();
			}
	}
	
	/**
	 * @return The position of the clock hand
	 */
	public static int getClockHandPosition(){
		return clockHandPosition;
	}
	
	/**
	 * Sets the position of the clock hand
	 * @param chp
	 */
	public static void setClockHandPosition(int chp){
		clockHandPosition = chp;
	}
	
	/**
	 * @return The array of menu buttons
	 */
	public static JToggleButton[] getMenuButtons(){
		return menuButtons;
	}
	
	/**
	 * @return The timer of the board
	 */
	public static Timer getTimer(){
		return timer;
	}
	
	/**
	 * @param t The new timer
	 */
	public static void setTimer(Timer t){
		timer = t;
	}
	
	/**
	 * Sets a button of the display selected or 
	 * unselected
	 * @param b True for selected and false for unselected
	 */
	public static void setButtonSelected(int x, int y, boolean b){
		display[x][y].setSelected(b);
	}
	
	/**
	 * Returns true if a button is selected
	 */
	public static Boolean getButtonSelected(int x, int y){
		return display[x][y].isSelected();
	}
}