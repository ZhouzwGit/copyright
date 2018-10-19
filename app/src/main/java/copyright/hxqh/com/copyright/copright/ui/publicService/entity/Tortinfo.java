package copyright.hxqh.com.copyright.copright.ui.publicService.entity;

import java.io.Serializable;

/**
 * Created by lianjh on 2018\10\19 0019.
 * Current page
 */

public class Tortinfo  implements Serializable {
    private String tortname;
    private String torturl;
    private String tortnote;
    private String torttype;

    public String getTortname() {
        return tortname;
    }

    public void setTortname(String tortname) {
        this.tortname = tortname;
    }

    public String getTorturl() {
        return torturl;
    }

    public void setTorturl(String torturl) {
        this.torturl = torturl;
    }

    public String getTortnote() {
        return tortnote;
    }

    public void setTortnote(String tortnote) {
        this.tortnote = tortnote;
    }

    public String getTorttype() {
        return torttype;
    }

    public void setTorttype(String torttype) {
        this.torttype = torttype;
    }
}
