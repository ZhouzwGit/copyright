package copyright.hxqh.com.copyright.copright.ui.product.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zzw on 2018/9/27.
 */

public class Channel implements Serializable {
        private String credibility;
        private String contractno;
        private String channelname;
        private String bankaccount;
        private String registeredaddress;
        private String ein;
        private int pagenum;
        private String thecontact;
        private String thecontactphone;
        private String channelnum;
        private String invoiceTitle;
        private String city;
        private String contractname;
        private String faxno;
        private String channeltype;
        private String titleType;
        private String citySel;
        private int serialVersionUID;
        private String provincesSel;
        private String channellevel;
        private String owncompany;
        private String officeid;
        private String makeinvoicetype;
        private String unitaddress;
        private String provinces;
        private String openaccountbank;
        private String num;
        private String legalrepresentative;
        private String email;
        private int countnum;
        private String createDate;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public List<ProductInfoCollect> getProductInfoCollectList() {
        return productInfoCollectList;
    }

    public void setProductInfoCollectList(List<ProductInfoCollect> productInfoCollectList) {
        this.productInfoCollectList = productInfoCollectList;
    }

    private List<ProductInfoCollect> productInfoCollectList;
        public void setCredibility(String credibility) {
            this.credibility = credibility;
        }
        public String getCredibility() {
            return credibility;
        }

        public void setContractno(String contractno) {
            this.contractno = contractno;
        }
        public String getContractno() {
            return contractno;
        }

        public void setChannelname(String channelname) {
            this.channelname = channelname;
        }
        public String getChannelname() {
            return channelname;
        }

        public void setBankaccount(String bankaccount) {
            this.bankaccount = bankaccount;
        }
        public String getBankaccount() {
            return bankaccount;
        }

        public void setRegisteredaddress(String registeredaddress) {
            this.registeredaddress = registeredaddress;
        }
        public String getRegisteredaddress() {
            return registeredaddress;
        }

        public void setEin(String ein) {
            this.ein = ein;
        }
        public String getEin() {
            return ein;
        }

        public void setPagenum(int pagenum) {
            this.pagenum = pagenum;
        }
        public int getPagenum() {
            return pagenum;
        }

        public void setThecontact(String thecontact) {
            this.thecontact = thecontact;
        }
        public String getThecontact() {
            return thecontact;
        }

        public void setThecontactphone(String thecontactphone) {
            this.thecontactphone = thecontactphone;
        }
        public String getThecontactphone() {
            return thecontactphone;
        }

        public void setChannelnum(String channelnum) {
            this.channelnum = channelnum;
        }
        public String getChannelnum() {
            return channelnum;
        }

        public void setInvoiceTitle(String invoiceTitle) {
            this.invoiceTitle = invoiceTitle;
        }
        public String getInvoiceTitle() {
            return invoiceTitle;
        }

        public void setCity(String city) {
            this.city = city;
        }
        public String getCity() {
            return city;
        }

        public void setContractname(String contractname) {
            this.contractname = contractname;
        }
        public String getContractname() {
            return contractname;
        }

        public void setFaxno(String faxno) {
            this.faxno = faxno;
        }
        public String getFaxno() {
            return faxno;
        }

        public void setChanneltype(String channeltype) {
            this.channeltype = channeltype;
        }
        public String getChanneltype() {
            return channeltype;
        }

        public void setTitleType(String titleType) {
            this.titleType = titleType;
        }
        public String getTitleType() {
            return titleType;
        }

        public void setCitySel(String citySel) {
            this.citySel = citySel;
        }
        public String getCitySel() {
            return citySel;
        }

        public void setSerialVersionUID(int serialVersionUID) {
            this.serialVersionUID = serialVersionUID;
        }
        public int getSerialVersionUID() {
            return serialVersionUID;
        }

        public void setProvincesSel(String provincesSel) {
            this.provincesSel = provincesSel;
        }
        public String getProvincesSel() {
            return provincesSel;
        }

        public void setChannellevel(String channellevel) {
            this.channellevel = channellevel;
        }
        public String getChannellevel() {
            return channellevel;
        }

        public void setOwncompany(String owncompany) {
            this.owncompany = owncompany;
        }
        public String getOwncompany() {
            return owncompany;
        }

        public void setOfficeid(String officeid) {
            this.officeid = officeid;
        }
        public String getOfficeid() {
            return officeid;
        }

        public void setMakeinvoicetype(String makeinvoicetype) {
            this.makeinvoicetype = makeinvoicetype;
        }
        public String getMakeinvoicetype() {
            return makeinvoicetype;
        }

        public void setUnitaddress(String unitaddress) {
            this.unitaddress = unitaddress;
        }
        public String getUnitaddress() {
            return unitaddress;
        }

        public void setProvinces(String provinces) {
            this.provinces = provinces;
        }
        public String getProvinces() {
            return provinces;
        }

        public void setOpenaccountbank(String openaccountbank) {
            this.openaccountbank = openaccountbank;
        }
        public String getOpenaccountbank() {
            return openaccountbank;
        }

        public void setNum(String num) {
            this.num = num;
        }
        public String getNum() {
            return num;
        }

        public void setLegalrepresentative(String legalrepresentative) {
            this.legalrepresentative = legalrepresentative;
        }
        public String getLegalrepresentative() {
            return legalrepresentative;
        }

        public void setEmail(String email) {
            this.email = email;
        }
        public String getEmail() {
            return email;
        }

        public void setCountnum(int countnum) {
            this.countnum = countnum;
        }
        public int getCountnum() {
            return countnum;
        }
}
