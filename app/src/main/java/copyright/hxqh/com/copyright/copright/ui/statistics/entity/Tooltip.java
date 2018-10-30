package copyright.hxqh.com.copyright.copright.ui.statistics.entity;

import java.io.Serializable;

/**
 * Created by lianjh on 2018\10\26 0026.
 * Current page
 */

public class Tooltip  implements Serializable {
    private String formatter;
    private String trigger;

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }
}
