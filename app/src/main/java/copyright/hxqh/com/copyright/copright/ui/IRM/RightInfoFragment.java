package copyright.hxqh.com.copyright.copright.ui.IRM;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.IRM.adapter.RightAdapter;

/**
 * Created by zzw on 2018/9/19.
 */

public class RightInfoFragment extends Fragment {
    private RightAdapter rightAdapter;
    public RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    private LinearLayout nodatalayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list, container,
                false);
        findViewById(view);
        initView(view);
        return view;
    }
    public void findViewById(View view) {
        recyclerView = view.findViewById(R.id.recyclerView_id);
        nodatalayout = view.findViewById(R.id.have_not_data_id);
        swipeRefreshLayout = view.findViewById(R.id.swipe_container);
    }
    public void initView(View view){
        layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setEnabled(false);
        initAdpter();
    }
    public void initAdpter(){
        rightAdapter = (RightAdapter) getArguments().get("rightinfo");
        if (rightAdapter.getData().isEmpty()){
            nodatalayout.setVisibility(View.VISIBLE);
        }
        recyclerView.setAdapter(rightAdapter);
    }
}
