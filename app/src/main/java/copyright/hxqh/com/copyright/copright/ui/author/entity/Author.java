package copyright.hxqh.com.copyright.copright.ui.author.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zzw on 2018/9/28.
 */

public class Author implements Serializable {
    private String authormobile;
    private String mail;
    private String fax;
    private String signname;
    private String bankno;
    private int pagenum;
    private String ahthortype;
    private String ahthorno;
    private String credcardno;
    private String prideexp;
    private String respersonmobile;
    private String wechatno;
    private int countnum;
    private String name;
    private String credtype;
    private String authorimg;
    private String bank;
    private String resperson;
    private String restype;
    private String isAuthor;
    private String nation,
            province,
            city;

    public String getAuthorimg() {
        return authorimg;
    }

    public void setAuthorimg(String authorimg) {
        this.authorimg = authorimg;
    }

    private List<RighterResRel> righterResRelList;

    public List<RighterResRel> getRighterResRelList() {
        return righterResRelList;
    }

    public void setRighterResRelList(List<RighterResRel> righterResRelList) {
        this.righterResRelList = righterResRelList;
    }

    public String getIsAuthor() {
        return isAuthor;
    }

    public void setIsAuthor(String isAuthor) {
        this.isAuthor = isAuthor;
    }

    private List<AuthorResRel> authorResRelList;

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

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFax() {
        return fax;
    }

    public void setSignname(String signname) {
        this.signname = signname;
    }

    public String getSignname() {
        return signname;
    }

    public void setBankno(String bankno) {
        this.bankno = bankno;
    }

    public String getBankno() {
        return bankno;
    }

    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }

    public int getPagenum() {
        return pagenum;
    }

    public void setAhthortype(String ahthortype) {
        this.ahthortype = ahthortype;
    }

    public String getAhthortype() {
        return ahthortype;
    }

    public void setAhthorno(String ahthorno) {
        this.ahthorno = ahthorno;
    }

    public String getAhthorno() {
        return ahthorno;
    }

    public void setCredcardno(String credcardno) {
        this.credcardno = credcardno;
    }

    public String getCredcardno() {
        return credcardno;
    }

    public void setPrideexp(String prideexp) {
        this.prideexp = prideexp;
    }

    public String getPrideexp() {
        return prideexp;
    }

    public void setRespersonmobile(String respersonmobile) {
        this.respersonmobile = respersonmobile;
    }

    public String getRespersonmobile() {
        return respersonmobile;
    }

    public void setWechatno(String wechatno) {
        this.wechatno = wechatno;
    }

    public String getWechatno() {
        return wechatno;
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

    public void setResperson(String resperson) {
        this.resperson = resperson;
    }

    public String getResperson() {
        return resperson;
    }

    public void setRestype(String restype) {
        this.restype = restype;
    }

    public String getRestype() {
        return restype;
    }

    public void setAuthorResRelList(List<AuthorResRel> authorResRelList) {
        this.authorResRelList = authorResRelList;
    }

    public List<AuthorResRel> getAuthorResRelList() {
        return authorResRelList;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
