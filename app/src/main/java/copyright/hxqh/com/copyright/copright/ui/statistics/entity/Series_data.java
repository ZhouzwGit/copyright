package copyright.hxqh.com.copyright.copright.ui.statistics.entity;

import java.io.Serializable;

/**
 * Created by lianjh on 2018\10\26 0026.
 * Current page
 */

public class Series_data implements Serializable {
    private String name;
    private int value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
