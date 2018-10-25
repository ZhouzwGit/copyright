package copyright.hxqh.com.copyright.copright.entity;

import java.io.Serializable;

/**
 * Created by lianjh on 2018\10\22 0022.
 * Current page
 */

public class Taskinfo implements Serializable {
    private String createTime;
    private String category;
    private String description;
    private String name;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
