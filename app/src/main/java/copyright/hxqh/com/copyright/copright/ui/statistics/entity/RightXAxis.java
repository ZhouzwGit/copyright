package copyright.hxqh.com.copyright.copright.ui.statistics.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lianjh on 2018\10\30 0030.
 * Current page
 */

public class RightXAxis implements Serializable {
    private AxisLabel axislabel;
    private AxisTick axistick;
    private List<String> data;
    private String type;

    public AxisLabel getAxisLabel() {
        return axislabel;
    }

    public void setAxisLabel(AxisLabel axisLabel) {
        this.axislabel = axisLabel;
    }

    public AxisTick getAxisTick() {
        return axistick;
    }

    public void setAxisTick(AxisTick axisTick) {
        this.axistick = axisTick;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
