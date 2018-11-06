package copyright.hxqh.com.copyright.copright.ui.statistics.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by lianjh on 2018\11\6 0006.
 * Current page
 */

public class ProductLegends implements Serializable {
    private String orient;
    private String right;
    private ArrayList<String> data;

    public ArrayList<String> getData() {
        return data;
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    public String getOrient() {
        return orient;
    }

    public void setOrient(String orient) {
        this.orient = orient;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }
}