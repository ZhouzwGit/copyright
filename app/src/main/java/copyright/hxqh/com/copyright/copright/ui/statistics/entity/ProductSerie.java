package copyright.hxqh.com.copyright.copright.ui.statistics.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lianjh on 2018\11\6 0006.
 * Current page
 */

public class ProductSerie implements Serializable {
    private ArrayList<Integer> data;
    private String type;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getData() {
        return data;
    }

    public void setData(ArrayList<Integer> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}