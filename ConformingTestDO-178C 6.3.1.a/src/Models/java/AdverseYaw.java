/* Do not remove or modify this comment!  It is required for file identification!
DNL
platform:/resource/ConformingTestDO-178C%206.3.1.a/src/Models/dnl/RollRateControl.dnl
 Do not remove or modify this comment!  It is required for file identification! */
package Models.java;

import java.io.Serializable;

public class AdverseYaw implements Serializable {
    private static final long serialVersionUID = 1L;

    //ID:VAR:AdverseYaw:0
    String value;

    //ENDIF
    public AdverseYaw() {
    }

    public AdverseYaw(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public String toString() {
        String str = "AdverseYaw";
        str += "\n\tvalue: " + this.value;
        return str;
    }
}
