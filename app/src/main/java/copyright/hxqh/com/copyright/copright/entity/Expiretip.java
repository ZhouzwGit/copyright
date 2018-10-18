package copyright.hxqh.com.copyright.copright.entity;

import java.io.Serializable;

/**
 * Created by zzw on 2018/10/16.
 */

public class Expiretip implements Serializable {
    private String resourcename;
    private String finishdate;
    private String gainway;
    private String rightno;
    private String countnum;
    private int pagenum;
    private String copyright;

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getResourcename() {
        return resourcename;
    }

    public void setResourcename(String resourcename) {
        this.resourcename = resourcename;
    }

    public String getFinishdate() {
        return finishdate;
    }

    public void setFinishdate(String finishdate) {
        this.finishdate = finishdate;
    }

    public String getGainway() {
        return gainway;
    }

    public void setGainway(String gainway) {
        this.gainway = gainway;
    }

    public String getRightno() {
        return rightno;
    }

    public void setRightno(String rightno) {
        this.rightno = rightno;
    }

    public String getCountnum() {
        return countnum;
    }

    public void setCountnum(String countnum) {
        this.countnum = countnum;
    }

    public int getPagenum() {
        return pagenum;
    }

    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }
}
