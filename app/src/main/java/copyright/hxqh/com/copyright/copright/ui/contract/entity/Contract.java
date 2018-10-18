package copyright.hxqh.com.copyright.copright.ui.contract.entity;

import java.io.Serializable;
import java.util.List;
/**
 * Created by zzw on 2018/9/29.
 */

public class Contract implements Serializable {
        private String contractnum;
        private String authorizedpartys;
        private String memo;
        private String status;
        private int pagenum;
        private String attachment;
        private String signdate;
        private String contractname;
        private String authorizedparty;
        private String sealattachment;
        private List<Payres> payresList;
        private String contracttype;
        private int countnum;
        private String typeofpartyb;
        private String typeofpartya;
        private String authorizedtopartys;
        public void setContractnum(String contractnum) {
            this.contractnum = contractnum;
        }
        public String getContractnum() {
            return contractnum;
        }

        public void setAuthorizedpartys(String authorizedpartys) {
            this.authorizedpartys = authorizedpartys;
        }
        public String getAuthorizedpartys() {
            return authorizedpartys;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }
        public String getMemo() {
            return memo;
        }

        public void setStatus(String status) {
            this.status = status;
        }
        public String getStatus() {
            return status;
        }

        public void setPagenum(int pagenum) {
            this.pagenum = pagenum;
        }
        public int getPagenum() {
            return pagenum;
        }

        public void setAttachment(String attachment) {
            this.attachment = attachment;
        }
        public String getAttachment() {
            return attachment;
        }

        public void setSigndate(String signdate) {
            this.signdate = signdate;
        }
        public String getSigndate() {
            return signdate;
        }

        public void setContractname(String contractname) {
            this.contractname = contractname;
        }
        public String getContractname() {
            return contractname;
        }

        public void setAuthorizedparty(String authorizedparty) {
            this.authorizedparty = authorizedparty;
        }
        public String getAuthorizedparty() {
            return authorizedparty;
        }

        public void setSealattachment(String sealattachment) {
            this.sealattachment = sealattachment;
        }
        public String getSealattachment() {
            return sealattachment;
        }

        public void setPayresList(List<Payres> payresList) {
            this.payresList = payresList;
        }
        public List<Payres> getPayresList() {
            return payresList;
        }

        public void setContracttype(String contracttype) {
            this.contracttype = contracttype;
        }
        public String getContracttype() {
            return contracttype;
        }

        public void setCountnum(int countnum) {
            this.countnum = countnum;
        }
        public int getCountnum() {
            return countnum;
        }

        public void setTypeofpartyb(String typeofpartyb) {
            this.typeofpartyb = typeofpartyb;
        }
        public String getTypeofpartyb() {
            return typeofpartyb;
        }

        public void setTypeofpartya(String typeofpartya) {
            this.typeofpartya = typeofpartya;
        }
        public String getTypeofpartya() {
            return typeofpartya;
        }

        public void setAuthorizedtopartys(String authorizedtopartys) {
            this.authorizedtopartys = authorizedtopartys;
        }
        public String getAuthorizedtopartys() {
            return authorizedtopartys;
        }

}
