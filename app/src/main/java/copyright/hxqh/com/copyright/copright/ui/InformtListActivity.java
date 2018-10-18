package copyright.hxqh.com.copyright.copright.ui;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.product.ChannelListFragment;
import copyright.hxqh.com.copyright.copright.ui.product.ProductListFragment;

/**
 * Created by zzw on 2018/9/27.
 */

public class InformtListActivity extends Fragment implements View.OnClickListener {
    private CollocationinformListFragment collocationinformListFragment;
    private PayinformListFragment payinformListFragment;
    private ExpiretipinformListFragment expiretipinformListFragment;
    private View titleView,nousetab,usetab;
    private LinearLayout tab1,tab2,tab3;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.basic_list, container,
                false);
        findViewById(view);
        initView();
        return view;
    }
    public void findViewById(View view) {
        titleView =view. findViewById(R.id.title_id);
        nousetab = view.findViewById(R.id.tagshow_id);
        usetab = view.findViewById(R.id.tab2_id);
        tab1 = view.findViewById(R.id.id_tab_expiretip);
        tab2 = view.findViewById(R.id.id_tab_collectinform);
        tab3 = view.findViewById(R.id.id_tab_payinform);
    }

    public void initView() {
        titleView.setVisibility(View.GONE);
        nousetab.setVisibility(View.GONE);
        usetab.setVisibility(View.VISIBLE);
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);
        tab1.performClick();
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        switch (view.getId()) {
            case R.id.id_tab_expiretip:
                tab1.setBackgroundResource(R.color.blue);
                tab2.setBackgroundResource(R.color.white);
                tab3.setBackgroundResource(R.color.white);
                if (expiretipinformListFragment == null) {
                    expiretipinformListFragment = new ExpiretipinformListFragment();
                }
                fragmentTransaction.replace(R.id.list_frame, expiretipinformListFragment).commit();
                break;
            case R.id.id_tab_payinform:
                tab1.setBackgroundResource(R.color.white);
                tab2.setBackgroundResource(R.color.white);
                tab3.setBackgroundResource(R.color.blue);
                if (payinformListFragment == null) {
                    payinformListFragment = new PayinformListFragment();
                }
                fragmentTransaction.replace(R.id.list_frame, payinformListFragment).commit();
                break;
            case R.id.id_tab_collectinform:
                tab1.setBackgroundResource(R.color.white);
                tab2.setBackgroundResource(R.color.blue);
                tab3.setBackgroundResource(R.color.white);
                if (collocationinformListFragment == null) {
                    collocationinformListFragment = new CollocationinformListFragment();
                }
                fragmentTransaction.replace(R.id.list_frame, collocationinformListFragment).commit();
                break;
        }
    }
}
