package copyright.hxqh.com.copyright.copright.custom;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by lianjh on 2018\11\1 0001.
 * Current page
 */

public class CustomLinearLayoutManager extends LinearLayoutManager {
    private boolean isScrollEnabled = true;

    public CustomLinearLayoutManager(Context context) {
        super(context);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }
}

