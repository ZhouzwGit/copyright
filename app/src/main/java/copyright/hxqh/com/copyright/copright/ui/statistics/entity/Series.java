package copyright.hxqh.com.copyright.copright.ui.statistics.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lianjh on 2018\10\26 0026.
 * Current page
 */

public class Series implements Serializable {
    private List<String> center;
    private List<Series_data> data;
    private String name;
    private String radius;
    private String type;

    public List<String> getCenter() {
        return center;
    }

    public void setCenter(List<String> center) {
        this.center = center;
    }

    public List<Series_data> getData() {
        return data;
    }

    public void setData(List<Series_data> data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
