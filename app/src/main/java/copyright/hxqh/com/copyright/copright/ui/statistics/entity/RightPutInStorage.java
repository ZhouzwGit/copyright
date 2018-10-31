package copyright.hxqh.com.copyright.copright.ui.statistics.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lianjh on 2018\10\30 0030.
 * Current page
 */

public class RightPutInStorage implements Serializable {
    private Grid grid;
    private List<RightSeries> series;
    private RightTitle title;
    private Tooltips tooltip;
    private List<RightXAxis> xaxis;
    private List<RightYAxis> yaxis;

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public List<RightSeries> getSeries() {
        return series;
    }

    public void setSeries(List<RightSeries> series) {
        this.series = series;
    }

    public RightTitle getTitle() {
        return title;
    }

    public void setTitle(RightTitle title) {
        this.title = title;
    }

    public Tooltips getTooltip() {
        return tooltip;
    }

    public void setTooltip(Tooltips tooltip) {
        this.tooltip = tooltip;
    }

    public List<RightXAxis> getXaxis() {
        return xaxis;
    }

    public void setXaxis(List<RightXAxis> xaxis) {
        this.xaxis = xaxis;
    }

    public List<RightYAxis> getYaxis() {
        return yaxis;
    }

    public void setYaxis(List<RightYAxis> yaxis) {
        this.yaxis = yaxis;
    }
}
