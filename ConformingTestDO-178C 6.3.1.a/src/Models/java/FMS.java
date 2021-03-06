
/* Do not remove or modify this comment!  It is required for file identification!
DNL
platform:/resource/ConformingTestDO-178C%206.3.1.a/src/Models/dnl/FMS.dnl
-160433617
 Do not remove or modify this comment!  It is required for file identification! */
package Models.java;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.Serializable;
import java.util.ArrayList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.ms4systems.devs.core.message.Message;
import com.ms4systems.devs.core.message.MessageBag;
import com.ms4systems.devs.core.message.Port;
import com.ms4systems.devs.core.message.impl.MessageBagImpl;
import com.ms4systems.devs.core.model.impl.AtomicModelImpl;
import com.ms4systems.devs.core.simulation.Simulation;
import com.ms4systems.devs.core.simulation.Simulator;
import com.ms4systems.devs.extensions.PhaseBased;
import com.ms4systems.devs.extensions.StateVariableBased;
import com.ms4systems.devs.helpers.impl.SimulationOptionsImpl;
import com.ms4systems.devs.simviewer.standalone.SimViewer;

// Custom library code
//ID:LIB:0

	import Models.utils.types.CmdJoystick;
	import Models.utils.rollModes.FeedbackRoll;
	import Models.utils.rollModes.RollMode;

//ENDID
// End custom library code


@SuppressWarnings("unused")
public class FMS extends AtomicModelImpl
        implements PhaseBased, StateVariableBased 
        {
    private static final long serialVersionUID = 1L;    
    
    // Declare state variables
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
//ID:SVAR:0
private static final int ID_SENDCOMMAND = 0;
protected CmdJoystick sendCommand
;
//ENDID
//ID:SVAR:1
private static final int ID_MEASUREFEEDBACK = 1;
protected FeedbackRoll measureFeedback
;
//ENDID
//ID:SVAR:2
private static final int ID_ISTURNEDON = 2;
protected Boolean isTurnedOn
;
//ENDID
//ID:SVAR:3
private static final int ID_ROLLMODE = 3;
protected RollMode rollMode
;
//ENDID
    String phase = "InitialState";
    String previousPhase = null;
    Double sigma = Double.POSITIVE_INFINITY;
    Double previousSigma = Double.NaN;
    
    
    
    // End state variables

    	
	
// Input ports
//ID:INP:0
public final Port<FeedbackRoll> inFeedbackRoll = addInputPort("inFeedbackRoll",FeedbackRoll.class);
//ENDID
//ID:INP:1
public final Port<CmdOnOff> inCmdOnOff = addInputPort("inCmdOnOff",CmdOnOff.class);
//ENDID
//ID:INP:2
public final Port<CmdJoystick> inCmdJoystick = addInputPort("inCmdJoystick",CmdJoystick.class);
//ENDID
    // End input ports
    
    // Output ports
//ID:OUTP:0
public final Port<CmdJoystick> outCmdJoystick = addOutputPort("outCmdJoystick",CmdJoystick.class);
//ENDID
//ID:OUTP:1
public final Port<FeedbackRoll> outFeedbackRoll = addOutputPort("outFeedbackRoll",FeedbackRoll.class);
//ENDID
    // End output ports

    
    

        
    
 protected SimulationOptionsImpl options = new SimulationOptionsImpl();
 protected double currentTime; 
 

	
public FMS(){ this("FMS"); }

    public FMS(String name){
        this(name,null);
    }
    
    public FMS(String name, Simulator simulator) {
        super(name,simulator);
    }
    

	
	
 public void initialize(){
        super.initialize();
        
        currentTime=0;
        

		passivateIn("InitialState");
        
        // Initialize Variables
        //ID:INIT
        
	sendCommand = new CmdJoystick();
	measureFeedback = new FeedbackRoll();
	isTurnedOn = false;
	rollMode = new RollMode();

        //ENDID
        // End initialize variables
        
        
        
    }
 

    
 @Override
    public void internalTransition() {
        currentTime += sigma;
        
        
        
		if (phaseIs("HandlingCmdOnOffReceived")) {
		    getSimulator().modelMessage("Internal transition from HandlingCmdOnOffReceived");
		     
			//ID:TRA:HandlingCmdOnOffReceived
			passivateIn("InitialState");
			//ENDID
			
		    return;
		}
		if (phaseIs("SendJoystickCommand")) {
		    getSimulator().modelMessage("Internal transition from SendJoystickCommand");
		     
			//ID:TRA:SendJoystickCommand
			passivateIn("InitialState");
			//ENDID
			
		    return;
		}
		if (phaseIs("MeasureFeedbackRoll")) {
		    getSimulator().modelMessage("Internal transition from MeasureFeedbackRoll");
		     
			//ID:TRA:MeasureFeedbackRoll
			passivateIn("InitialState");
			//ENDID
			
		    return;
		}

    
        //passivate();
    };
 

    
 @Override
    public void externalTransition(double timeElapsed, MessageBag input) {
        currentTime += timeElapsed;
        // Subtract time remaining until next internal transition (no effect if sigma == Infinity)
        sigma -= timeElapsed;
        
        // Store prior data
        previousPhase = phase;
        previousSigma = sigma;
        
        
        
        
        
        // Fire state transition functions
			if (phaseIs("InitialState")) {
                 
			     
				if (input.hasMessages(inCmdOnOff)){
					ArrayList<Message<CmdOnOff>> messageList = inCmdOnOff.getMessages(input);
                    
					holdIn("HandlingCmdOnOffReceived",0.0);
					// Fire state and port specific external transition functions
					//ID:EXT:InitialState:inCmdOnOff
					
	isTurnedOn = ((CmdOnOff)messageList.get(0).getData()).isValue();
	rollMode.setCmdFmsOnOff(isTurnedOn);

					//ENDID
					// End external event code
					
					
					                        
					return;
				}
				if (input.hasMessages(inCmdJoystick)){
					ArrayList<Message<CmdJoystick>> messageList = inCmdJoystick.getMessages(input);
                    
					holdIn("SendJoystickCommand",0.0);
					// Fire state and port specific external transition functions
					//ID:EXT:InitialState:inCmdJoystick
					
	sendCommand = rollMode.measureJoystickCommand((CmdJoystick)messageList.get(0).getData());

					//ENDID
					// End external event code
					
					
					                        
					return;
				}
				if (input.hasMessages(inFeedbackRoll)){
					ArrayList<Message<FeedbackRoll>> messageList = inFeedbackRoll.getMessages(input);
                    
					holdIn("MeasureFeedbackRoll",0.0);
					// Fire state and port specific external transition functions
					//ID:EXT:InitialState:inFeedbackRoll
					
	measureFeedback = (FeedbackRoll)messageList.get(0).getData();

					//ENDID
					// End external event code
					
					
					                        
					return;
				}
			}





        
        
    };
 
    
    
 @Override
    public void confluentTransition(MessageBag input) {
        // confluentTransition with internalTransition first (by default)
        internalTransition();
        externalTransition(0, input);
    }
 
    
    
     @Override
    public Double getTimeAdvance() {return sigma;};
 
 
	
    
 @Override
    public MessageBag getOutput() {
        MessageBag output = new MessageBagImpl();
        
        
		if (phaseIs("SendJoystickCommand")) {
// Output event code
//ID:OUT:SendJoystickCommand

	output.add(outCmdJoystick, sendCommand);		

//ENDID
// End output event code
		}
		if (phaseIs("MeasureFeedbackRoll")) {
// Output event code
//ID:OUT:MeasureFeedbackRoll

	measureFeedback.getRollMode().setCmdFmsOnOff(isTurnedOn);
	rollMode = measureFeedback.getRollMode();
	measureFeedback.getRollWarning().measureWarning();
	output.add(outFeedbackRoll, measureFeedback);		

//ENDID
// End output event code
		}
        return output;
    }
 
    
    
    // Custom function definitions
    
    // End custom function definitions
 	

    
 	
	
	
 public static void main(String[] args) {
    
        SimulationOptionsImpl options = new SimulationOptionsImpl(args,true);
        
        // Uncomment the following line to disable SimViewer for this model
        // options.setDisableViewer(true);

        // Uncomment the following line to disable plotting for this model
        // options.setDisablePlotting(true);
        
        FMS model = new FMS();
        model.options = options;
        
        if (options.isDisableViewer()) { // Command line output only
            Simulation sim = new com.ms4systems.devs.core.simulation.impl.SimulationImpl("FMS Simulation", model, options);
            sim.startSimulation(0);
            sim.simulateIterations(Long.MAX_VALUE);
        }
        else { // Use SimViewer
            SimViewer viewer = new SimViewer();
            viewer.open(model,options);
        }

    }
 
	
    

	 public void addPropertyChangeListener(String propertyName,
	      PropertyChangeListener listener) {
	    propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	  }
	
	  public void removePropertyChangeListener(PropertyChangeListener listener) {
	    propertyChangeSupport.removePropertyChangeListener(listener);
	  }

    
   // Getter/setter for sendCommand
    public void setSendCommand(CmdJoystick sendCommand) {
        propertyChangeSupport.firePropertyChange("sendCommand", this.sendCommand,this.sendCommand = sendCommand);
    }
    public CmdJoystick getSendCommand() {
        return this.sendCommand;
    }
    
	     
    // End getter/setter for sendCommand
    
   // Getter/setter for measureFeedback
    public void setMeasureFeedback(FeedbackRoll measureFeedback) {
        propertyChangeSupport.firePropertyChange("measureFeedback", this.measureFeedback,this.measureFeedback = measureFeedback);
    }
    public FeedbackRoll getMeasureFeedback() {
        return this.measureFeedback;
    }
    
	     
    // End getter/setter for measureFeedback
    
   // Getter/setter for isTurnedOn
    public void setIsTurnedOn(Boolean isTurnedOn) {
        propertyChangeSupport.firePropertyChange("isTurnedOn", this.isTurnedOn,this.isTurnedOn = isTurnedOn);
    }
    public Boolean getIsTurnedOn() {
        return this.isTurnedOn;
    }
    public Boolean isIsTurnedOn() {
        return this.isTurnedOn;
    }
	
	     
    // End getter/setter for isTurnedOn
    
   // Getter/setter for rollMode
    public void setRollMode(RollMode rollMode) {
        propertyChangeSupport.firePropertyChange("rollMode", this.rollMode,this.rollMode = rollMode);
    }
    public RollMode getRollMode() {
        return this.rollMode;
    }
    
	     
    // End getter/setter for rollMode
 
    // State variables
    public String[] getStateVariableNames() {
         return new String[] {
            "sendCommand","measureFeedback","isTurnedOn","rollMode"
        };
    };
    public Object[] getStateVariableValues() {
         return new Object[] {
            sendCommand,measureFeedback,isTurnedOn,rollMode
        };
    };
    
    public Class<?>[] getStateVariableTypes() {
    	return new Class<?>[] {
    		CmdJoystick.class,FeedbackRoll.class,Boolean.class,RollMode.class
    	};
    }
    
    
    public void setStateVariableValue(int index, Object value) {
    	switch(index) {
    		
    		case ID_SENDCOMMAND:
			    setSendCommand((CmdJoystick)value);
    			return;
			
    		case ID_MEASUREFEEDBACK:
			    setMeasureFeedback((FeedbackRoll)value);
    			return;
			
    		case ID_ISTURNEDON:
			    setIsTurnedOn((Boolean)value);
    			return;
			
    		case ID_ROLLMODE:
			    setRollMode((RollMode)value);
    			return;
			
			default:
			return;
    	}
    } 
 
    	
    
    // Convenience functions
    protected void passivate() { passivateIn("passive"); }
    
    protected void passivateIn(String phase) {
       holdIn(phase,Double.POSITIVE_INFINITY);
    }
    
    protected void holdIn(String phase, Double sigma) {
       this.phase = phase;
       this.sigma = sigma;
       getSimulator().modelMessage("Holding in phase " + phase + " for time " + sigma);
    }
    
    protected static File getModelsDirectory() {
        URI dirUri;
        File dir;
        try {
            dirUri = FMS.class.getResource(".").toURI();
            dir = new File(dirUri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not find Models directory. Invalid model URL: " + FMS.class.getResource(".").toString());
        }
        boolean foundModels = false;
        while (dir!=null && dir.getParentFile()!=null) {
            if (dir.getName().equalsIgnoreCase("java") && 
                    dir.getParentFile().getName().equalsIgnoreCase("models"))
                return dir.getParentFile();
            dir = dir.getParentFile();
        }
        throw new RuntimeException("Could not find Models directory from model path: " + dirUri.toASCIIString());
    }
    
    protected static File getDataFile(String fileName) {
        return getDataFile(fileName, "txt");
    }

    protected static File getDataFile(String fileName, String directoryName) {
        File modelDir = getModelsDirectory();
        File dir = new File(modelDir, directoryName);
        if (dir==null)
            throw new RuntimeException(
                    "Could not find '" + directoryName +"' directory from model path: " + modelDir.getAbsolutePath());
        File dataFile = new File(dir,fileName);
        if (dataFile==null)
            throw new RuntimeException(
                    "Could not find '" + fileName +"' file in directory: " + dir.getAbsolutePath());
        return dataFile;
    }
    
    protected void msg(String msg) {
        getSimulator().modelMessage(msg);
    }
 
        
    
     // Phase display
    public boolean phaseIs(String phase) {
        return this.phase.equals(phase);
    }
    public String getPhase() {
        return phase;
    }
    public String[] getPhaseNames() {
        return new String[] {
            "InitialState","HandlingCmdOnOffReceived","SendJoystickCommand","MeasureFeedbackRoll"
        };
    }
 
    
    
    

    // This variable is just here so we can use @SuppressWarnings("unused")
    private final int unusedIntVariableForWarnings = 0;
}
