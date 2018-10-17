package copyright.hxqh.com.copyright.copright.ui.author.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zzw on 2018/9/28.
 */

public class Royalty implements Serializable {
        private String authormobile;
        private String mail;
        private String contractnum;
        private String bankno;
        private String status;
        private int pagenum;
        private String credcardno;
        private String authortype;
        private List<Payres> payresList;
        private int resourcecount;
        private int countnum;
        private String name;
        private String credtype;
        private String bank;
        private String field1;
        private String createDate;
        private String wechatno;
        private String fax;

    public String getWechatno() {
        return wechatno;
    }

    public void setWechatno(String wechatno) {
        this.wechatno = wechatno;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setAuthormobile(String authormobile) {
            this.authormobile = authormobile;
        }
        public String getAuthormobile() {
            return authormobile;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }
        public String getMail() {
            return mail;
        }

        public void setContractnum(String contractnum) {
            this.contractnum = contractnum;
        }
        public String getContractnum() {
            return contractnum;
        }

        public void setBankno(String bankno) {
            this.bankno = bankno;
        }
        public String getBankno() {
            return bankno;
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

        public void setCredcardno(String credcardno) {
            this.credcardno = credcardno;
        }
        public String getCredcardno() {
            return credcardno;
        }

        public void setAuthortype(String authortype) {
            this.authortype = authortype;
        }
        public String getAuthortype() {
            return authortype;
        }

        public void setPayresList(List<Payres> payresList) {
            this.payresList = payresList;
        }
        public List<Payres> getPayresList() {
            return payresList;
        }

        public void setResourcecount(int resourcecount) {
            this.resourcecount = resourcecount;
        }
        public int getResourcecount() {
            return resourcecount;
        }

        public void setCountnum(int countnum) {
            this.countnum = countnum;
        }
        public int getCountnum() {
            return countnum;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setCredtype(String credtype) {
            this.credtype = credtype;
        }
        public String getCredtype() {
            return credtype;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }
        public String getBank() {
            return bank;
        }

        public void setField1(String field1) {
            this.field1 = field1;
        }
        public String getField1() {
            return field1;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
        public String getCreateDate() {
            return createDate;
        }
}
