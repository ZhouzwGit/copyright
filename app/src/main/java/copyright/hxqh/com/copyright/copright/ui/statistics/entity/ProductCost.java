package copyright.hxqh.com.copyright.copright.ui.statistics.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lianjh on 2018\11\6 0006.
 * Current page
 */

public class ProductCost implements Serializable {
    private ProductLegends legend;
    private ArrayList<ProductSerie> series;
    private Title title;
    private Tooltips tooltip;
    private List<RightXAxis> xaxis;
    private List<RightYAxis> yaxis;

    public ProductLegends getLegend() {
        return legend;
    }

    public void setLegend(ProductLegends legend) {
        this.legend = legend;
    }

    public ArrayList<ProductSerie> getSeries() {
        return series;
    }

    public void setSeries(ArrayList<ProductSerie> series) {
        this.series = series;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
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
