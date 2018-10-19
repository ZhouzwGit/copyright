package copyright.hxqh.com.copyright.copright.ui.publicService.entity;

import java.io.Serializable;

/**
 * Created by lianjh on 2018\10\19 0019.
 * Current page
 */

public class Consult implements Serializable {
    private String serviceno;
    private String applyUser;
    private String phone;
    private String servicefile;
    private String status;
    private int countnum;
    private int pagenum;
    private String managementabqu;
    private String createDate;
    private String dealidea;

    public String getServiceno() {
        return serviceno;
    }

    public void setServiceno(String serviceno) {
        this.serviceno = serviceno;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getServicefile() {
        return servicefile;
    }

    public void setServicefile(String servicefile) {
        this.servicefile = servicefile;
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

    public int getPagenum() {
        return pagenum;
    }

    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }

    public String getManagementabqu() {
        return managementabqu;
    }

    public void setManagementabqu(String managementabqu) {
        this.managementabqu = managementabqu;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDealidea() {
        return dealidea;
    }

    public void setDealidea(String dealidea) {
        this.dealidea = dealidea;
    }
}
