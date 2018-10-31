package copyright.hxqh.com.copyright.copright.ui.statistics.entity;

import java.io.Serializable;

/**
 * Created by lianjh on 2018\10\30 0030.
 * Current page
 */

public class RightTitle implements Serializable {
    private String subtext;
    private String text;

    public String getSubtext() {
        return subtext;
    }

    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
