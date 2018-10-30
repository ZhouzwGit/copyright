package copyright.hxqh.com.copyright.copright.ui.statistics.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import copyright.hxqh.com.copyright.copright.ui.statistics.entity.StorageCounts;
import copyright.hxqh.com.copyright.copright.ui.statistics.fragment.StorageFragment;

/**
 * Created by lianjh on 2018\10\25 0025.
 * Current page
 */

public class WorksCountAdapter extends FragmentPagerAdapter {
    StorageCounts storageCounts;
    int count;
    public StorageCounts getStorageCounts() {
        return storageCounts;
    }

    public void setStorageCounts(StorageCounts storageCounts) {
        this.storageCounts = storageCounts;
        notifyDataSetChanged();
    }
    public void setCount(int count) {
        this.count = count;
    }
    public WorksCountAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public int getCount() {
        return count;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            default:
                return StorageFragment.newInstance(position,storageCounts);
        }
    }
}
