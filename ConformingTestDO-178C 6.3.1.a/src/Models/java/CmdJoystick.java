/* Do not remove or modify this comment!  It is required for file identification!
DNL
platform:/resource/ConformingTestDO-178C%206.3.1.a/src/Models/dnl/Pilot.dnl
 Do not remove or modify this comment!  It is required for file identification! */
package Models.java;

import java.io.Serializable;

public class CmdJoystick implements Serializable {
    private static final long serialVersionUID = 1L;

    //ID:VAR:CmdJoystick:0
    Integer value;

    //ENDIF
    public CmdJoystick() {
    }

    public CmdJoystick(Integer value) {
        this.value = value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

    public String toString() {
        String str = "CmdJoystick";
        str += "\n\tvalue: " + this.value;
        return str;
    }
}
