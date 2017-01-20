package Simori;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JToggleButton;

import java.io.FileNotFoundException;

public class ChangeModeListener implements ActionListener{
	 
	 /**
	  * Action performed when one of the menu buttons is
	  * pressed. Changes the mode of the board depending on the
	  * button. Disables every button other than the button
	  * pressed and the ON button.
	  */
    @Override
    public void actionPerformed(ActionEvent e) {
      try{  
    	JToggleButton button = (JToggleButton) e.getSource();
        if(GUI.currentMode != Modes.PERFORMANCEMODE) GUI.clearBoard();
        	
        
        if(button.isSelected()){
        	OnOff.disableMenuButtons(button);
        	ClockHand.setClockHandVisible(false);
        }
        else{
        	OnOff.enableMenuButtons();
        	ClockHand.setClockHandVisible(true);
        }
    
        
        
        switch(ButtonText.valueOf(button.getToolTipText())){
	    	case ON:
	    		if(button.isSelected()){
	    			OnOff.enableGridButtons();
	    			OnOff.enableMenuButtons();
	    			ClockHand.startClockHand();
	    			ClockHand.setClockHandVisible(true);
	    			OnOff.startThreads();
	    			GUI.currentMode = Modes.PERFORMANCEMODE;
	    		}
	    		else{
	    			OnOff.disableGridButtons();
	    			OnOff.disableMenuButtons();
	    			GUI.clearMenuButtons(null);
	    			GUI.eraseKeyboard();
	    			GUI.clearBoard();
	    			GUI.clearLayers();
	    			OnOff.stopThreads();
	    			ClockHand.setClockHandVisible(false);
	    			ChangeLayer.setCurrentLayer(0);
	    			GUI.currentMode = Modes.ONOFFMODE;
	    		}
	    		break;
	    	case L1:
	    		if(button.isSelected()){
	    			GUI.currentMode = Modes.CHANGEVOICEMODE;
	    			GUI.clearBoard();
	    		}
	    		else{
	    			GUI.currentMode = Modes.PERFORMANCEMODE;
	    			ChangeLayer.loadLayer(ChangeLayer.getCurrentLayer());
	    		}
	    		break;
	    	case L2:
	    		if(button.isSelected()){
	    			GUI.currentMode = Modes.CHANGEVELOCITYMODE;
	    			GUI.clearBoard();
	    		}
	    		else{
	    			GUI.currentMode = Modes.PERFORMANCEMODE;
	    			ChangeLayer.loadLayer(ChangeLayer.getCurrentLayer());
	    		}
	    		break;
	    	case L3:
	    		if(button.isSelected()){
	    			GUI.currentMode = Modes.CHANGELOOPSPEEDMODE;
	    			GUI.clearBoard();
	    		}
	    		else{
	    			GUI.currentMode = Modes.PERFORMANCEMODE;
	    			ChangeLayer.loadLayer(ChangeLayer.getCurrentLayer());
	    		}
	    		break;
	    	case L4:
	    		if(button.isSelected()){
	    			GUI.currentMode = Modes.CHANGELOOPPOINTMODE;
	    			GUI.clearBoard();
	    		}
	    		else{
	    			GUI.currentMode = Modes.PERFORMANCEMODE;
	    			ChangeLayer.loadLayer(ChangeLayer.getCurrentLayer());
	    		}
	    		break;
	    	case R1:
	    		if(button.isSelected()){
	    			GUI.currentMode = Modes.CHANGELAYERMODE;
	    			GUI.clearBoard();
	    		}
	    		else{
	    			GUI.currentMode = Modes.PERFORMANCEMODE;
	    			ChangeLayer.loadLayer(ChangeLayer.getCurrentLayer());
	    		}
	    		break;
	    	case R2:
	    		if(button.isSelected()){
	    			GUI.currentMode = Modes.SAVECONFIGURATIONMODE;
	    			GUI.textField.setText(null);
	    			GUI.clearBoard();
	    			GUI.displayKeyboard();
	    		}
	    		else{
	    			GUI.currentMode = Modes.PERFORMANCEMODE;
	    			GUI.eraseKeyboard();
	    			ChangeLayer.loadLayer(ChangeLayer.getCurrentLayer());
	    		}
	    		break;
	    	case R3:
	    		if(button.isSelected()){
	    			GUI.currentMode = Modes.LOADCONFIGURATIONMODE;
	    			GUI.textField.setText(null);
	    			GUI.clearBoard();
	    			GUI.displayKeyboard();	    			
	    		}
	    		else{
	    			GUI.currentMode = Modes.PERFORMANCEMODE;
	    			GUI.eraseKeyboard();
	    			ChangeLayer.loadLayer(ChangeLayer.getCurrentLayer());
	    		}
	    		break;
	    	case R4:
	    		if(button.isSelected()){
	    			GUI.currentMode = Modes.SLAVEMASTERMODE;
	    			SlaveMaster sm = new SlaveMaster();
	    		}
	    		else{
	    			GUI.currentMode = Modes.PERFORMANCEMODE;
	    			ChangeLayer.loadLayer(ChangeLayer.getCurrentLayer());
	    		}
	    		break;
	    	case OK:
	    		
	    		if(GUI.currentMode==Modes.SHOPBOYMODE){
                	GUI.currentMode = Modes.PERFORMANCEMODE;
                	GUI.clearBoard();
                	SaveLoad.load("empty");
                	OnOff.enableMenuButtons();
                	ShopBoy.stop();
                }
	    		
	    		else if(GUI.currentMode==Modes.PERFORMANCEMODE){
	    			GUI.currentMode = Modes.SHOPBOYMODE;
	    			ShopBoy.playShopBoy();
	    			ClockHand.setClockHandVisible(true);
	    			OnOff.disableMenuButtons();
	    			OnOff.enableMenuButton("OK");
	    		}
	    		
	    		else if(GUI.currentMode == Modes.CHANGEVOICEMODE){
                    if(ChangeVoiceMode.getTempInstrument()<128){
	    				ChangeVoiceMode.setInstrument(ChangeVoiceMode.getTempInstrument());
	    				OnOff.enableMenuButtons();
	    				ChangeLayer.Layers[ChangeLayer.getCurrentLayer()].playAccepted();
	                	GUI.currentMode = Modes.PERFORMANCEMODE;
	    			}else{
        				ChangeLayer.Layers[ChangeLayer.getCurrentLayer()].playDenied();
        				OnOff.enableMenuButton("L1");
        				OnOff.deselectOk();
        			}
                }
	    		else if(GUI.currentMode == Modes.CHANGEVELOCITYMODE){
                	if(ChangeVelocity.getTempVelocity()<128){
                		ChangeVelocity.setVelocity(ChangeVelocity.getTempVelocity());
                		OnOff.enableMenuButtons();
                		ChangeLayer.Layers[ChangeLayer.getCurrentLayer()].playAccepted();
                		GUI.currentMode = Modes.PERFORMANCEMODE;
                	}else{
                		ChangeLayer.Layers[ChangeLayer.getCurrentLayer()].playDenied();
                		OnOff.enableMenuButton("L2");
                		OnOff.deselectOk();
                	}
                }
	    		else if(GUI.currentMode == Modes.CHANGELOOPSPEEDMODE){
                    if(ChangeLoopSpeed.getTempLoopSpeed()<160){
                    	ChangeLoopSpeed.setLoopSpeed(ChangeLoopSpeed.getTempLoopSpeed());
                    	OnOff.enableMenuButtons();
                    	ChangeLayer.Layers[ChangeLayer.getCurrentLayer()].playAccepted();
                    	GUI.currentMode = Modes.PERFORMANCEMODE;
                    }else{
                    	ChangeLayer.Layers[ChangeLayer.getCurrentLayer()].playDenied();
                		OnOff.enableMenuButton("L3");
                		OnOff.deselectOk();
                    }
                }
	    		else if(GUI.currentMode == Modes.CHANGELOOPPOINTMODE){
                	ChangeLoopPoint.setLoopPoint(ChangeLoopPoint.getTempLoopPoint());
                	OnOff.enableMenuButtons();
                	GUI.currentMode = Modes.PERFORMANCEMODE;
                }
	    		else if(GUI.currentMode == Modes.CHANGELAYERMODE){
                	ChangeLayer.setCurrentLayer(ChangeLayer.getTempLayer());
                	ChangeLayer.loadLayer(ChangeLayer.getCurrentLayer());
                	OnOff.enableMenuButtons();
                	GUI.currentMode = Modes.PERFORMANCEMODE;
                }
	    		else if(GUI.currentMode == Modes.SAVECONFIGURATIONMODE){
                	SaveLoad.save();
                	GUI.eraseKeyboard();
                	OnOff.enableMenuButtons();
                	GUI.currentMode = Modes.PERFORMANCEMODE;
                }
	    		else if(GUI.currentMode == Modes.LOADCONFIGURATIONMODE){
                	try{	
                		SaveLoad.load();
                		GUI.eraseKeyboard();
                		ChangeLayer.Layers[ChangeLayer.getCurrentLayer()].playAccepted();
                    	ChangeLayer.loadLayer(ChangeLayer.getCurrentLayer());
                    	OnOff.enableMenuButtons();
                    	GUI.currentMode = Modes.PERFORMANCEMODE;
                	} catch (FileNotFoundException ex){
                		ex.printStackTrace();
                		ChangeLayer.Layers[ChangeLayer.getCurrentLayer()].playDenied();
            			GUI.displayKeyboard();
            			OnOff.enableMenuButton("R3");
                		OnOff.deselectOk();
            		}catch(IllegalArgumentException ex){
            			ChangeLayer.loadLayer(ChangeLayer.getCurrentLayer());
                    	OnOff.enableMenuButtons();
                    	GUI.currentMode = Modes.PERFORMANCEMODE;
            		}
                }
                
                if(GUI.currentMode == Modes.PERFORMANCEMODE){
                	GUI.clearMenuButtons(null);
                	ClockHand.setClockHandVisible(true);
                	ChangeLayer.loadLayer(ChangeLayer.getCurrentLayer());
                	
                }
                break;
        }	
      }catch(Exception exc){
    	  System.exit(0);
      }
    }
}