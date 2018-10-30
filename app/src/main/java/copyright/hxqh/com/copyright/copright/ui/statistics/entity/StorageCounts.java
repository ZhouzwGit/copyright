package copyright.hxqh.com.copyright.copright.ui.statistics.entity;

import java.io.Serializable;
import java.util.List;

import copyright.hxqh.com.copyright.copright.ui.publicService.entity.Obligeeinfo;

/**
 * Created by lianjh on 2018\10\26 0026.
 * Current page
 */

public class StorageCounts implements Serializable {
    private Legend legend;
    private List<Series> series;
    private Title title;
    private Tooltip tooltip;

    public Legend getLegend() {
        return legend;
    }

    public void setLegend(Legend legend) {
        this.legend = legend;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Tooltip getTooltip() {
        return tooltip;
    }

    public void setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
    }
}
