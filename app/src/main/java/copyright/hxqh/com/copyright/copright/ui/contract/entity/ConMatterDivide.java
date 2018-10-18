package copyright.hxqh.com.copyright.copright.ui.contract.entity;

import java.io.Serializable;

/**
 * Created by zzw on 2018/10/18.
 */

public class ConMatterDivide implements Serializable {
     private String resourcename;
     private Double rtmoney;
     private Double rtratio;

    public String getResourcename() {
        return resourcename;
    }

    public void setResourcename(String resourcename) {
        this.resourcename = resourcename;
    }

    public Double getRtmoney() {
        return rtmoney;
    }

    public void setRtmoney(Double rtmoney) {
        this.rtmoney = rtmoney;
    }

    public Double getRtratio() {
        return rtratio;
    }

    public void setRtratio(Double rtratio) {
        this.rtratio = rtratio;
    }
}
