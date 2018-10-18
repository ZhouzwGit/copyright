package copyright.hxqh.com.copyright.copright.ui.contract.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zzw on 2018/10/18.
 */

public class AuthContract implements Serializable {
        private String contractnum;
        private String memo;
        private String status;
        private List<Cprel> cprelList;
        private int pagenum;
        private String attachment;
        private String signdate;
        private String authorizedtoparty;
        private String contractname;
        private String sealattachment;
        private String contracttype;
        private int countnum;
        private String typeofpartyb;
        private String typeofpartya;
        private List<Accreditinfo> accreditinfoList;
        private String issupplement;
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

        public void setStatus(String status) {
            this.status = status;
        }
        public String getStatus() {
            return status;
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

        public void setSealattachment(String sealattachment) {
            this.sealattachment = sealattachment;
        }
        public String getSealattachment() {
            return sealattachment;
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

        public void setAccreditinfoList(List<Accreditinfo> accreditinfoList) {
            this.accreditinfoList = accreditinfoList;
        }
        public List<Accreditinfo> getAccreditinfoList() {
            return accreditinfoList;
        }

        public void setIssupplement(String issupplement) {
            this.issupplement = issupplement;
        }
        public String getIssupplement() {
            return issupplement;
        }
}
