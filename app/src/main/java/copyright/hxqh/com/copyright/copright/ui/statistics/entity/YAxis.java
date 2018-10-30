package copyright.hxqh.com.copyright.copright.ui.statistics.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lianjh on 2018\10\29 0029.
 * Current page
 */

public class YAxis implements Serializable {
    private List<Integer> boundaryGap;
    private int max;
    private String name;
    private String type;

    public List<Integer> getBoundaryGap() {
        return boundaryGap;
    }

    public void setBoundaryGap(List<Integer> boundaryGap) {
        this.boundaryGap = boundaryGap;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
