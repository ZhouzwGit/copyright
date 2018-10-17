package copyright.hxqh.com.copyright.copright.entity;

import java.io.Serializable;

/**
 * Created by zzw on 2018/9/28.
 */

public class UserInfo implements Serializable {
    private String office;
    private String phone;
    private String logindate;
    private String email;
    private String company;
    private String name;
    private String rolenames;
    private String remarks;
    private String loginip;
    private String mobile;
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getOffice() {
        return office;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setLogindate(String logindate) {
        this.logindate = logindate;
    }

    public String getLogindate() {
        return logindate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRolenames(String rolenames) {
        this.rolenames = rolenames;
    }

    public String getRolenames() {
        return rolenames;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip;
    }

    public String getLoginip() {
        return loginip;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }
}
