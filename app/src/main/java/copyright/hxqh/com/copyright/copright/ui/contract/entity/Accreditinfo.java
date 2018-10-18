package copyright.hxqh.com.copyright.copright.ui.contract.entity;

import java.io.Serializable;

/**
 * Created by zzw on 2018/10/18.
 */

public class Accreditinfo implements Serializable {
        private String payment;
        private String remarks;
        private String cost;
        private String productname;
        public void setPayment(String payment) {
            this.payment = payment;
        }
        public String getPayment() {
            return payment;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
        public String getRemarks() {
            return remarks;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }
        public String getCost() {
            return cost;
        }

        public void setProductname(String productname) {
            this.productname = productname;
        }
        public String getProductname() {
            return productname;
        }

}
