package copyright.hxqh.com.copyright.copright.ui.contract.entity;

import java.io.Serializable;

/**
 * Created by zzw on 2018/9/29.
 */

public class Cprel implements Serializable {
        private String otherrestrict;
        private String startdate;
        private String enddatee;
        private String rightrange;
        private String resourcename;
        private String territoryrestrict;
        private String deliverydate;
        private String license;
        private String languagerestrict;
        public void setOtherrestrict(String otherrestrict) {
            this.otherrestrict = otherrestrict;
        }
        public String getOtherrestrict() {
            return otherrestrict;
        }

        public void setStartdate(String startdate) {
            this.startdate = startdate;
        }
        public String getStartdate() {
            return startdate;
        }

        public void setEnddatee(String enddatee) {
            this.enddatee = enddatee;
        }
        public String getEnddatee() {
            return enddatee;
        }

        public void setRightrange(String rightrange) {
            this.rightrange = rightrange;
        }
        public String getRightrange() {
            return rightrange;
        }

        public void setResourcename(String resourcename) {
            this.resourcename = resourcename;
        }
        public String getResourcename() {
            return resourcename;
        }

        public void setTerritoryrestrict(String territoryrestrict) {
            this.territoryrestrict = territoryrestrict;
        }
        public String getTerritoryrestrict() {
            return territoryrestrict;
        }

        public void setDeliverydate(String deliverydate) {
            this.deliverydate = deliverydate;
        }
        public String getDeliverydate() {
            return deliverydate;
        }

        public void setLicense(String license) {
            this.license = license;
        }
        public String getLicense() {
            return license;
        }

        public void setLanguagerestrict(String languagerestrict) {
            this.languagerestrict = languagerestrict;
        }
        public String getLanguagerestrict() {
            return languagerestrict;
        }
}
