package copyright.hxqh.com.copyright.copright.ui.publicService.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lianjh on 2018\10\17 0017.
 * Current page
 */

public class RoyaltyEnity implements Serializable {
    private String phone;
    private String email;
    private String status;
    private int countnum;
    private String torturl;
    private List<Obligeeinfo> obligeeinfoList;
    private int pagenum;
    private String remarks;
    private String torttype;
    private String infringer;
    private String oblname;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCountnum() {
        return countnum;
    }

    public void setCountnum(int countnum) {
        this.countnum = countnum;
    }

    public String getTorturl() {
        return torturl;
    }

    public void setTorturl(String torturl) {
        this.torturl = torturl;
    }

    public List<Obligeeinfo> getObligeeinfoList() {
        return obligeeinfoList;
    }

    public void setObligeeinfoList(List<Obligeeinfo> obligeeinfoList) {
        this.obligeeinfoList = obligeeinfoList;
    }

    public int getPagenum() {
        return pagenum;
    }

    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTorttype() {
        return torttype;
    }

    public void setTorttype(String torttype) {
        this.torttype = torttype;
    }

    public String getInfringer() {
        return infringer;
    }

    public void setInfringer(String infringer) {
        this.infringer = infringer;
    }

    public String getOblname() {
        return oblname;
    }

    public void setOblname(String oblname) {
        this.oblname = oblname;
    }
}
