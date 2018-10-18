package copyright.hxqh.com.copyright.copright.ui.contract.entity;

import java.io.Serializable;

/**
 * Created by zzw on 2018/10/18.
 */

public class Right implements Serializable {

        private String rightrestrict;
        private String languages;
        private String rightattribute;
        private String startdate;
        private String finishplacearea;
        private String resourcename;
        private String copyright;
        private String enddate;
        private String gainway;
        private String rightcost;
        public void setRightrestrict(String rightrestrict) {
            this.rightrestrict = rightrestrict;
        }
        public String getRightrestrict() {
            return rightrestrict;
        }

        public void setLanguages(String languages) {
            this.languages = languages;
        }
        public String getLanguages() {
            return languages;
        }

        public void setRightattribute(String rightattribute) {
            this.rightattribute = rightattribute;
        }
        public String getRightattribute() {
            return rightattribute;
        }

        public void setStartdate(String startDate) {
            this.startdate = startdate;
        }
        public String getStartdate() {
            return startdate;
        }

        public void setFinishplacearea(String finishplacearea) {
            this.finishplacearea = finishplacearea;
        }
        public String getFinishplacearea() {
            return finishplacearea;
        }

        public void setResourcename(String resourcename) {
            this.resourcename = resourcename;
        }
        public String getResourcename() {
            return resourcename;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }
        public String getCopyright() {
            return copyright;
        }

        public void setEnddate(String enddate) {
            this.enddate = enddate;
        }
        public String getEnddate() {
            return enddate;
        }

        public void setGainway(String gainway) {
            this.gainway = gainway;
        }
        public String getGainway() {
            return gainway;
        }

        public void setRightcost(String rightcost) {
            this.rightcost = rightcost;
        }
        public String getRightcost() {
            return rightcost;
        }
        
}
