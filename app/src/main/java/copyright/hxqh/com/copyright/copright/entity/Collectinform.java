package copyright.hxqh.com.copyright.copright.entity;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by zzw on 2018/10/16.
 */

public class Collectinform implements Serializable {
    private String resourcename;
    private String channelname;
    private int countnum;
    private int collectioncount;
    private int pagenum;
    private String collectiondate;
    private String productname;
    public String getResourcename() {
        return resourcename;
    }

    public void setResourcename(String resourcename) {
        this.resourcename = resourcename;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
    }

    public String getChannelname() {
        return channelname;
    }

    public void setCountnum(int countnum) {
        this.countnum = countnum;
    }

    public int getCountnum() {
        return countnum;
    }

    public void setCollectioncount(int collectioncount) {
        this.collectioncount = collectioncount;
    }

    public int getCollectioncount() {
        return collectioncount;
    }

    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }

    public int getPagenum() {
        return pagenum;
    }

    public void setCollectiondate(String collectiondate) {
        this.collectiondate = collectiondate;
    }

    public String getCollectiondate() {
        return collectiondate;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductname() {
        return productname;
    }

}
