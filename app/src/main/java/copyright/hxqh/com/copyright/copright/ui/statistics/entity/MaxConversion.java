package copyright.hxqh.com.copyright.copright.ui.statistics.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lianjh on 2018\10\29 0029.
 * Current page
 */

public class MaxConversion implements Serializable {
    private Legend legend;
    private List<Serie> series;
    private Tooltips tooltip;
    private List<XAxis> xaxis;
    private List<YAxis> yaxis;

    public Legend getLegend() {
        return legend;
    }

    public void setLegend(Legend legend) {
        this.legend = legend;
    }

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

    public List<XAxis> getXaxis() {
        return xaxis;
    }

    public void setXaxis(List<XAxis> xaxis) {
        this.xaxis = xaxis;
    }

    public List<YAxis> getYaxis() {
        return yaxis;
    }

    public void setYaxis(List<YAxis> yaxis) {
        this.yaxis = yaxis;
    }
}
