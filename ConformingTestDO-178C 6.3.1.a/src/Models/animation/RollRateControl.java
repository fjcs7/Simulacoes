package Models.animation;
import com.ms4systems.devs.core.message.Port;
import com.ms4systems.devs.core.util.WriteAnimation;
import java.io.*;

public class RollRateControl extends WriteAnimation.GenericInputOutput{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final Port<Serializable> inCmdJoystick = addInputPort("inCmdJoystick",Serializable.class);
	public final Port<Serializable> outFeedbackRoll = addOutputPort("outFeedbackRoll",Serializable.class);
	public final Port<Serializable> outAngleRight = addOutputPort("outAngleRight",Serializable.class);
	public final Port<Serializable> outAngleLeft = addOutputPort("outAngleLeft",Serializable.class);
	public final Port<Serializable> inYawAngleLeft = addInputPort("inYawAngleLeft",Serializable.class);
	public final Port<Serializable> inYawAngleRight = addInputPort("inYawAngleRight",Serializable.class);
	public RollRateControl(){
		this("RollRateControl");
	}
	public RollRateControl(String nm) {
		super(nm);
	}
	public void initialize() {
		super.initialize();
		addInPortToOutPort(getInputPort("inCmdJoystick"),getOutputPort("outFeedbackRoll"));
		addInPortToOutPort(getInputPort("inCmdJoystick"),getOutputPort("outAngleRight"));
		addInPortToOutPort(getInputPort("inCmdJoystick"),getOutputPort("outAngleLeft"));
		addInPortToOutPort(getInputPort("inYawAngleLeft"),getOutputPort("outFeedbackRoll"));
		addInPortToOutPort(getInputPort("inYawAngleLeft"),getOutputPort("outAngleRight"));
		addInPortToOutPort(getInputPort("inYawAngleLeft"),getOutputPort("outAngleLeft"));
		addInPortToOutPort(getInputPort("inYawAngleRight"),getOutputPort("outFeedbackRoll"));
		addInPortToOutPort(getInputPort("inYawAngleRight"),getOutputPort("outAngleRight"));
		addInPortToOutPort(getInputPort("inYawAngleRight"),getOutputPort("outAngleLeft"));
	}
}