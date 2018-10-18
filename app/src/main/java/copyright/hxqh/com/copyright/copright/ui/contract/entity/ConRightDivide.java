package copyright.hxqh.com.copyright.copright.ui.contract.entity;

import java.io.Serializable;

/**
 * Created by zzw on 2018/10/18.
 */

public class ConRightDivide implements Serializable {
        private String resourcename;
        private Double rtmoney;
        private String personalproduct;
        private String copyright;
        private Double rtratio;
        public void setResourcename(String resourcename) {
            this.resourcename = resourcename;
        }
        public String getResourcename() {
            return resourcename;
        }

        public void setRtmoney(Double rtmoney) {
            this.rtmoney = rtmoney;
        }
        public Double getRtmoney() {
            return rtmoney;
        }

        public void setPersonalproduct(String personalproduct) {
            this.personalproduct = personalproduct;
        }
        public String getPersonalproduct() {
            return personalproduct;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }
        public String getCopyright() {
            return copyright;
        }

        public void setRtratio(Double rtratio) {
            this.rtratio = rtratio;
        }
        public Double getRtratio() {
            return rtratio;
        }

}
