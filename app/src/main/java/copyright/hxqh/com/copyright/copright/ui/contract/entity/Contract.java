package copyright.hxqh.com.copyright.copright.ui.contract.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zzw on 2018/9/29.
 */

public class Contract implements Serializable {
        private String contractnum;
        private String memo;
        private String paymentcount;
        private int countnum;
        private String paymenttype;
        private List<Cprel> cprelList;
        private int pagenum;
        private String note;
        private String authorizedtoparty;
        private String authorizedparty;
        private String createdate;
        private String status;
        private String contracttype;

    public String getContracttype() {
        return contracttype;
    }

    public void setContracttype(String contracttype) {
        this.contracttype = contracttype;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthorizedparty() {
        return authorizedparty;
    }

    public void setAuthorizedparty(String authorizedparty) {
        this.authorizedparty = authorizedparty;
    }

    private String contractname;
        public void setContractnum(String contractnum) {
            this.contractnum = contractnum;
        }
        public String getContractnum() {
            return contractnum;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }
        public String getMemo() {
            return memo;
        }

        public void setPaymentcount(String paymentcount) {
            this.paymentcount = paymentcount;
        }
        public String getPaymentcount() {
            return paymentcount;
        }

        public void setCountnum(int countnum) {
            this.countnum = countnum;
        }
        public int getCountnum() {
            return countnum;
        }

        public void setPaymenttype(String paymenttype) {
            this.paymenttype = paymenttype;
        }
        public String getPaymenttype() {
            return paymenttype;
        }

        public void setCprelList(List<Cprel> cprelList) {
            this.cprelList = cprelList;
        }
        public List<Cprel> getCprelList() {
            return cprelList;
        }

        public void setPagenum(int pagenum) {
            this.pagenum = pagenum;
        }
        public int getPagenum() {
            return pagenum;
        }

        public void setNote(String note) {
            this.note = note;
        }
        public String getNote() {
            return note;
        }

        public void setAuthorizedtoparty(String authorizedtoparty) {
            this.authorizedtoparty = authorizedtoparty;
        }
        public String getAuthorizedtoparty() {
            return authorizedtoparty;
        }

        public void setContractname(String contractname) {
            this.contractname = contractname;
        }
        public String getContractname() {
            return contractname;
        }
}
