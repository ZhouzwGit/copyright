package copyright.hxqh.com.copyright.copright.ui.statistics.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import copyright.hxqh.com.copyright.copright.ui.statistics.fragment.ConflictFragment;
import copyright.hxqh.com.copyright.copright.ui.statistics.fragment.ContractFragment;
import copyright.hxqh.com.copyright.copright.ui.statistics.fragment.DeadLineFragment;
import copyright.hxqh.com.copyright.copright.ui.statistics.fragment.DeadProjectFragment;
import copyright.hxqh.com.copyright.copright.ui.statistics.fragment.LanguageFragment;
import copyright.hxqh.com.copyright.copright.ui.statistics.fragment.OtherFragment;
import copyright.hxqh.com.copyright.copright.ui.statistics.fragment.RegionFragment;

/**
 * Created by lianjh on 2018\10\31 0031.
 * Current page
 */

public class ProductWarningAdapter  extends FragmentPagerAdapter {
    int count;
    public ProductWarningAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ConflictFragment.newInstance(position);
            case 1:
                return DeadLineFragment.newInstance(position);
            case 2:
                return ContractFragment.newInstance(position);
            case 3:
                return DeadProjectFragment.newInstance(position);
            case 4:
                return RegionFragment.newInstance(position);
            case 5:
                return OtherFragment.newInstance(position);
            case 6:
                return LanguageFragment.newInstance(position);
            default:
                return null;
        }
    }
}
