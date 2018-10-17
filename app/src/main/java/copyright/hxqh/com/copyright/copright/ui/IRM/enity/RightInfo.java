package copyright.hxqh.com.copyright.copright.ui.IRM.enity;

import java.io.Serializable;

/**
 * Created by zzw on 2018/9/19.
 */

public class RightInfo implements Serializable{
    private String rightattribute;
    private String languages;
    private String contractnum;
    private String resourceno;
    private String startdate;
    private String finishplacearea;
    private String resourcename;
    private String personalproduct;
    private String copyright;
    private String finishdate;
    private String gainway;
    private String note;

    public String getRightattribute() {
        return rightattribute;
    }

    public void setRightattribute(String rightattribute) {
        this.rightattribute = rightattribute;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getContractnum() {
        return contractnum;
    }

    public void setContractnum(String contractnum) {
        this.contractnum = contractnum;
    }

    public String getResourceno() {
        return resourceno;
    }

    public void setResourceno(String resourceno) {
        this.resourceno = resourceno;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getFinishplacearea() {
        return finishplacearea;
    }

    public void setFinishplacearea(String finishplacearea) {
        this.finishplacearea = finishplacearea;
    }

    public String getResourcename() {
        return resourcename;
    }

    public void setResourcename(String resourcename) {
        this.resourcename = resourcename;
    }

    public String getPersonalproduct() {
        return personalproduct;
    }

    public void setPersonalproduct(String personalproduct) {
        this.personalproduct = personalproduct;
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

    public String getGainway() {
        return gainway;
    }

    public void setGainway(String gainway) {
        this.gainway = gainway;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
