package copyright.hxqh.com.copyright.copright.ui.statistics.entity;

import java.io.Serializable;

/**
 * Created by lianjh on 2018\10\30 0030.
 * Current page
 */

public class RightYAxis implements Serializable {
    private AxisTick axistick;
    private String name;
    private String type;

    public AxisTick getAxisTick() {
        return axistick;
    }

    public void setAxisTick(AxisTick axisTick) {
        this.axistick = axisTick;
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
