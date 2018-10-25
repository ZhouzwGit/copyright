package copyright.hxqh.com.copyright.copright.entity;

import java.io.Serializable;

/**
 * Created by lianjh on 2018\10\22 0022.
 * Current page
 */

public class Acttype implements Serializable {
    private String actlabel;
    private String actvalue;

    public String getActlabel() {
        return actlabel;
    }

    public void setActlabel(String actlabel) {
        this.actlabel = actlabel;
    }

    public String getActvalue() {
        return actvalue;
    }

    public void setActvalue(String actvalue) {
        this.actvalue = actvalue;
    }
}
