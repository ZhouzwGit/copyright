package copyright.hxqh.com.copyright.copright.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by lianjh on 2018\10\22 0022.
 * Current page
 */

public class ToDo implements Serializable{
    private String businessTable;
    private String procInsId;
    private String assignee;
    private String businessId;
    private int countnum;
    private int pagenum;
    private String remaindflag;
    private ArrayList<Taskinfo> task;

    public String getRemaindflag() {
        return remaindflag;
    }

    public void setRemaindflag(String remaindflag) {
        this.remaindflag = remaindflag;
    }

    public String getBusinessTable() {
        return businessTable;
    }

    public void setBusinessTable(String businessTable) {
        this.businessTable = businessTable;
    }

    public String getProcInsId() {
        return procInsId;
    }

    public void setProcInsId(String procInsId) {
        this.procInsId = procInsId;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public int getCountnum() {
        return countnum;
    }

    public void setCountnum(int countnum) {
        this.countnum = countnum;
    }

    public int getPagenum() {
        return pagenum;
    }

    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }

    public ArrayList<Taskinfo> getTask() {
        return task;
    }

    public void setTask(ArrayList<Taskinfo> task) {
        this.task = task;
    }
}
