package copyright.hxqh.com.copyright.copright.ui.product.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zzw on 2018/9/25.
 */

public class Acrel implements Serializable{
        private String languagerestrict;
        private double cost;
        private String enddate;
        private String otherrestrict;
        private String territoryrestrict;
        private String payment;
        private String startdate;
        private String channelname;;
        private String remarks;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setLanguagerestrict(String languagerestrict) {
            this.languagerestrict = languagerestrict;
        }
        public String getLanguagerestrict() {
            return languagerestrict;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }
        public double getCost() {
            return cost;
        }

        public void setEnddate(String enddate) {
            this.enddate = enddate;
        }
        public String getEnddate() {
            return enddate;
        }

        public void setOtherrestrict(String otherrestrict) {
            this.otherrestrict = otherrestrict;
        }
        public String getOtherrestrict() {
            return otherrestrict;
        }

        public void setTerritoryrestrict(String territoryrestrict) {
            this.territoryrestrict = territoryrestrict;
        }
        public String getTerritoryrestrict() {
            return territoryrestrict;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }
        public String getPayment() {
            return payment;
        }

        public void setStartdate(String startdate) {
            this.startdate = startdate;
        }
        public String getStartdate() {
            return startdate;
        }

        public void setChannelname(String channelname) {
            this.channelname = channelname;
        }
        public String getChannelname() {
            return channelname;
        }

}
