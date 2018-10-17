package copyright.hxqh.com.copyright.copright.ui.IRM.enity;

import java.io.Serializable;

/**
 * Created by zzw on 2018/10/16.
 */

public class Rchrrel implements Serializable {
    private String  startdate;
    private String  copyright;
    private String  finishdate;

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getFinishdate() {
        return finishdate;
    }

    public void setFinishdate(String finishdate) {
        this.finishdate = finishdate;
    }
}
