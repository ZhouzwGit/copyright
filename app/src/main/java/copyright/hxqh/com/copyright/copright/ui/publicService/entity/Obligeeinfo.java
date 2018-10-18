package copyright.hxqh.com.copyright.copright.ui.publicService.entity;

import java.io.Serializable;

/**
 * Created by lianjh on 2018\10\17 0017.
 * Current page
 */

public class Obligeeinfo  implements Serializable {
    private String signatureattachment;
    private String identification;
    private String name;
    private String tortcategory;

    public String getSignatureattachment() {
        return signatureattachment;
    }

    public void setSignatureattachment(String signatureattachment) {
        this.signatureattachment = signatureattachment;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTortcategory() {
        return tortcategory;
    }

    public void setTortcategory(String tortcategory) {
        this.tortcategory = tortcategory;
    }
}
