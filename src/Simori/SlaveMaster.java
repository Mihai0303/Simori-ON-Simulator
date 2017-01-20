package Simori;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SlaveMaster extends JFrame implements ActionListener {
	
	private final static int PORT = 20160;
	private final static String HOST = "127.0.0.1";
	
	/**
	 * Opens a server socket and waits for a connection from a client.
	 * Once the connection has been established sends over the output
	 * stream a boolean three dimensional array representing all the layers
	 * of the board.
	 */
	public static void master(){
		try{	
			ServerSocket ss = new ServerSocket(PORT);
			while(true){
				Socket s = ss.accept();
				Boolean[][][] newArray = new Boolean[16][16][16];
				for(int i=0;i<16;i++){
					newArray[i] = ChangeLayer.getLayer(i).contents;
				}
				OutputStream out = s.getOutputStream();
				ObjectOutputStream writer = new ObjectOutputStream(out);
				writer.writeObject(newArray);
				
				int[] instruments = new int[17];
				for(int j=0;j<17;j++){
					if(j==16){
						instruments[j] = ChangeLoopSpeed.getLoopSpeed();
					}else{
						instruments[j] = ChangeLayer.getLayer(j).getInstrument();
					}
				}
				writer.writeObject(instruments);
				break;
			}
			ss.close();	
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Connects to a server socket, reads a boolean three dimensional
	 * array representing the layers of the master board and turns them
	 * into its own layers
	 */
	public static void slave(){
		try{
			Socket s = new Socket(HOST, PORT);
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			ObjectInputStream reader = new ObjectInputStream(in);
			
			//Read the layers
			Boolean[][][] newArray = (Boolean[][][]) reader.readObject();
			for(int i=0;i<16;i++){
				ChangeLayer.Layers[i].contents = newArray[i];
			}
			
			//Read the instruments and the loop speed
			int[] instruments = (int[]) reader.readObject();
			for(int j=0;j<17;j++){
				if(j==16){
					ChangeLoopSpeed.setLoopSpeed(instruments[j]);
				}else{
					ChangeLayer.getLayer(j).setInstrument(instruments[j]);
				}
			}
			
			ChangeLayer.loadLayer(ChangeLayer.getCurrentLayer());
			s.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	  * Sets up the GUI to select if the user wants to be the slave or the master.
	  */
	private JRadioButton slaveButton = new JRadioButton("Slave");
	JRadioButton masterButton = new JRadioButton("Master");
	public SlaveMaster(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		JPanel central = new JPanel();
		
		JButton submit = new JButton("ok");
		submit.setBounds(140, 30, 50, 30);
		submit.addActionListener(this);
		slaveButton.setBounds(0, 0, 70, 30);
		
		masterButton.setBounds(0, 30, 70, 30);
		ButtonGroup group = new ButtonGroup();
		
		group.add(slaveButton);
		group.add(masterButton);
		this.add(slaveButton);
		this.add(masterButton);
		this.add(submit);
		this.setSize(200, 100);
		this.setResizable(false);
		this.setVisible(true);
		this.add(central);
		this.setLocationRelativeTo(null);
	}
	
	/**
	 * When a button is pressed on the selection interface
	 * the appropriate method is called (master or slave)
	 */
	public void actionPerformed(ActionEvent e) {
		if(slaveButton.isSelected()){
				slave();
				this.dispose();
		}else if(masterButton.isSelected()){
			master();
			 this.dispose();
		}else{
			JOptionPane.showMessageDialog(null, "Nothing selected");
		}
    }
	
	public static void main(String[] args){
		SlaveMaster sm = new SlaveMaster();		
	}
}
	