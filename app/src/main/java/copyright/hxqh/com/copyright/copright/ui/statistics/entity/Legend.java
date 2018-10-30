package copyright.hxqh.com.copyright.copright.ui.statistics.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lianjh on 2018\10\26 0026.
 * Current page
 */

public class Legend implements Serializable {
    private int bottom;
    private List<String> data;
    private String orient;
    private int right;
    private int top;

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public String getOrient() {
        return orient;
    }

    public void setOrient(String orient) {
        this.orient = orient;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }
}
