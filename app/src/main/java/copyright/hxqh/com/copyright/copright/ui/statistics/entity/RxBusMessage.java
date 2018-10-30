package copyright.hxqh.com.copyright.copright.ui.statistics.entity;

import java.util.List;

/**
 * Created by wxs on 2018/3/14.
 */

public class RxBusMessage {
    String message;
    List<StorageCounts> storageCounts;

    public List<StorageCounts> getStorageCounts() {
        return storageCounts;
    }

    public void setStorageCounts(List<StorageCounts> storageCounts) {
        this.storageCounts = storageCounts;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
