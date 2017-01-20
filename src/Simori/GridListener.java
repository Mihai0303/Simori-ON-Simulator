package Simori;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;

public class GridListener implements ActionListener{
    
    /**
     * Method performed when an ActionEvent is fired by one of the
     * 256 grid buttons. If the board is in one of the specified modes
     * the method reacts appropriately (setting voice, velocity, loop speed
     * and so on).
     * @param e The Action Event fired
     */
    public void actionPerformed(ActionEvent e) {
        // Gets the button that was pressed
        JToggleButton buttonPressed = (JToggleButton) e.getSource();
        
        // Get the column and row of the pressed button
        int x = (buttonPressed.getX()-1)/43;
        int y = (buttonPressed.getY()-1)/43;
        
        
        switch(GUI.currentMode){
        	case ONOFFMODE:
        		break;
        	case PERFORMANCEMODE:
        		// sets the value of truth in the currently selected layer to the opposite
        		// if the button is selected changes it to true and if it is de-selected
        		// sets it to false
        		if(buttonPressed.isSelected()){
        			ChangeLayer.getLayer(ChangeLayer.getCurrentLayer()).setContents(x,y,true);
        		}
        		else
        			ChangeLayer.getLayer(ChangeLayer.getCurrentLayer()).setContents(x,y,false);
        		break;
        	case CHANGEVOICEMODE:
        			ChangeVoiceMode.setTempInstrument(x,y);
        		break;
        	case CHANGEVELOCITYMODE:
       			ChangeVelocity.setTempVelocity(x, y);
        		break;
        	case CHANGELOOPSPEEDMODE:
        		ChangeLoopSpeed.setTempLoopSpeed(x,y);
        		break;
        	case CHANGELOOPPOINTMODE:
        		ChangeLoopPoint.setTempLoopPoint(x,y);
        		break;
        	case CHANGELAYERMODE:
        		ChangeLayer.setTempLayer(x,y);
        		break;
        	case SAVECONFIGURATIONMODE:
        		SaveLoad.setFileName(x,y);
        		break;
        	case LOADCONFIGURATIONMODE:
        		SaveLoad.setFileName(x, y);
        		break;
        	case SLAVEMASTERMODE:
        		break;
        }
    }

}
