package copyright.hxqh.com.copyright.copright.ui.statistics.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lianjh on 2018\10\29 0029.
 * Current page
 */

public class XAxis implements Serializable {
    private AxisLabel axisLabel;
    private List<String> data;
    private String type;

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

    public AxisLabel getAxisLabel() {
        return axisLabel;
    }

    public void setAxisLabel(AxisLabel axisLabel) {
        this.axisLabel = axisLabel;
    }
}
