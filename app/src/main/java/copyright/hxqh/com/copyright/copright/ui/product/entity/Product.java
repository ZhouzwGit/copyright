package copyright.hxqh.com.copyright.copright.ui.product.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by zzw on 2018/9/25.
 */

public class Product implements Serializable {
    private double productcost;
    private String lifetimeto;
    private int quantity;
    private int countnum;
    private String lifetime;
    private int pagenum;
    private List<Acrel> acrelList;
    private String productname;
    private List<Prrel> prrelList;
    private String productusage;
    private String productno;
    private String createDate;
    private String status;
    private List<Prmtrel> prmtrelList;

    public List<Prmtrel> getPrmtrelList() {
        return prmtrelList;
    }

    public void setPrmtrelList(List<Prmtrel> prmtrelList) {
        this.prmtrelList = prmtrelList;
    }

    public void setProductcost(double productcost) {
        this.productcost = productcost;
    }

    public double getProductcost() {
        return productcost;
    }

    public void setLifetimeto(String lifetimeto) {
        this.lifetimeto = lifetimeto;
    }

    public String getLifetimeto() {
        return lifetimeto;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setCountnum(int countnum) {
        this.countnum = countnum;
    }

    public int getCountnum() {
        return countnum;
    }

    public void setLifetime(String lifetime) {
        this.lifetime = lifetime;
    }

    public String getLifetime() {
        return lifetime;
    }

    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }

    public int getPagenum() {
        return pagenum;
    }

    public void setAcrelList(List<Acrel> acrelList) {
        this.acrelList = acrelList;
    }

    public List<Acrel> getAcrelList() {
        return acrelList;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductname() {
        return productname;
    }

    public void setPrrelList(List<Prrel> prrelList) {
        this.prrelList = prrelList;
    }

    public List<Prrel> getPrrelList() {
        return prrelList;
    }

    public void setProductusage(String productusage) {
        this.productusage = productusage;
    }

    public String getProductusage() {
        return productusage;
    }

    public void setProductno(String productno) {
        this.productno = productno;
    }

    public String getProductno() {
        return productno;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
