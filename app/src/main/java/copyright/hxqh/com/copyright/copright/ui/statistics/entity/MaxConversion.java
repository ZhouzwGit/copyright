package copyright.hxqh.com.copyright.copright.ui.statistics.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lianjh on 2018\10\29 0029.
 * Current page
 */

public class MaxConversion implements Serializable {
//    private Legend legend;
    private List<Serie> series;
    private Tooltips tooltip;
    private List<XAxis> xAxis;
    private List<YAxis> yAxis;

//    public Legend getLegend() {
//        return legend;
//    }
//
//    public void setLegend(Legend legend) {
//        this.legend = legend;
//    }

    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }

    public Tooltips getTooltip() {
        return tooltip;
    }

    public void setTooltip(Tooltips tooltip) {
        this.tooltip = tooltip;
    }

    public List<XAxis> getxAxis() {
        return xAxis;
    }

    public void setxAxis(List<XAxis> xAxis) {
        this.xAxis = xAxis;
    }

    public List<YAxis> getyAxis() {
        return yAxis;
    }

    public void setyAxis(List<YAxis> yAxis) {
        this.yAxis = yAxis;
    }
}
