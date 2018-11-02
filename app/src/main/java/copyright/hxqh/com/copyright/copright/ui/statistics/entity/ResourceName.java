package copyright.hxqh.com.copyright.copright.ui.statistics.entity;

import java.io.Serializable;

/**
 * Created by lianjh on 2018\11\2 0002.
 * Current page
 */

public class ResourceName implements Serializable {
    private String resid;
    private String resname;

    public String getResid() {
        return resid;
    }

    public void setResid(String resid) {
        this.resid = resid;
    }

    public String getResname() {
        return resname;
    }

    public void setResname(String resname) {
        this.resname = resname;
    }
}
