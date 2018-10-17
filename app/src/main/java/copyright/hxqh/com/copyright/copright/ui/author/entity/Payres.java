package copyright.hxqh.com.copyright.copright.ui.author.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zzw on 2018/9/29.
 */

public class Payres implements Serializable {
        private String memo;
        private int paycost;
        private List<Right> rightList;
        private String paymentmethod;
        private String deadline;
        public void setMemo(String memo) {
            this.memo = memo;
        }
        public String getMemo() {
            return memo;
        }

        public void setPaycost(int paycost) {
            this.paycost = paycost;
        }
        public int getPaycost() {
            return paycost;
        }

        public void setRightList(List<Right> rightList) {
            this.rightList = rightList;
        }
        public List<Right> getRightList() {
            return rightList;
        }

        public void setPaymentmethod(String paymentmethod) {
            this.paymentmethod = paymentmethod;
        }
        public String getPaymentmethod() {
            return paymentmethod;
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }
        public String getDeadline() {
            return deadline;
        }
}
