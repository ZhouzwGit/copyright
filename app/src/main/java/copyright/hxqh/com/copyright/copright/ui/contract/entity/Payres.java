package copyright.hxqh.com.copyright.copright.ui.contract.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zzw on 2018/10/18.
 */

public class Payres implements Serializable {
        private String paymethod;
        private String paycost;
        private String remarks;
        private List<Right> rightList;
        private String deadline;
        public void setPaymethod(String paymethod) {
            this.paymethod = paymethod;
        }
        public String getPaymethod() {
            return paymethod;
        }

        public void setPaycost(String paycost) {
            this.paycost = paycost;
        }
        public String getPaycost() {
            return paycost;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
        public String getRemarks() {
            return remarks;
        }

        public void setRightList(List<Right> rightList) {
            this.rightList = rightList;
        }
        public List<Right> getRightList() {
            return rightList;
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }
        public String getDeadline() {
            return deadline;
        }
        
}
