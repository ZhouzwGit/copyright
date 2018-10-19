package copyright.hxqh.com.copyright.copright.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.HttpManager.HttpConnect;
import copyright.hxqh.com.copyright.copright.adpter.CollocationInformAdapter;
import copyright.hxqh.com.copyright.copright.entity.Collectinform;
import copyright.hxqh.com.copyright.copright.ui.product.ChannelDetailActivity;
import copyright.hxqh.com.copyright.copright.ui.product.adapter.ChannelAdapter;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Channel;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;

/**
 * Created by zzw on 2018/9/18.
 */

public class CollocationinformListFragment extends Fragment implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener {
    private CollocationInformAdapter collocationInformAdapter;
    private JSONObject json;
    private int page = 1;
    private int pageNum;
    public RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    private LinearLayout nodatalayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container,
                false);
        findViewById(view);
        initView();
        return view;
    }
    public void findViewById(View view){
        recyclerView = view.findViewById(R.id.recyclerView_id);
        swipeRefreshLayout = view.findViewById(R.id.swipe_container);
        nodatalayout = view.findViewById(R.id.have_not_data_id);
    }
    private void initView() {
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        json = HttpConnect.getBasicJson(getActivity());
        initAdpter();
        AcountUtil.showProgressDialog(getActivity(),"");
        getData();
    }
    private  void initAdpter(){
        collocationInformAdapter = new CollocationInformAdapter(R.layout.list_collectinform_card);
        recyclerView.setAdapter(collocationInformAdapter);
        collocationInformAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                getData();
            }
        },recyclerView);
    }
    public void getData(){
        new AsyncTask<String,String,List<Collectinform>>(){

            @Override
            protected List<Collectinform> doInBackground(String... strings) {
                List<Collectinform> products = (List<Collectinform>) HttpConnect.getCollectinformList(json,page);
                if (products == null){
                    return null;
                }
                return products;
            }

            @Override
            protected void onPostExecute(List<Collectinform> preoducts) {
                super.onPostExecute(preoducts);
                if (preoducts == null){
                    collocationInformAdapter.loadMoreFail();
                    if (collocationInformAdapter.getData().isEmpty()){
                        nodatalayout.setVisibility(View.VISIBLE);
                        TextView textView = (TextView) nodatalayout.getChildAt(0);
                        textView.setText("加载失败请检查网络");
                    }
                    AcountUtil.closeProgressDialog();
                    return;
                }
                if (preoducts.isEmpty()){
                    if (collocationInformAdapter.getData().isEmpty()){
                        AcountUtil.closeProgressDialog();
                        nodatalayout.setVisibility(View.VISIBLE);
                        collocationInformAdapter.replaceData(new ArrayList<Collectinform>());
                    }else {
                        collocationInformAdapter.loadMoreEnd();
                    }
                }else {
                    pageNum = preoducts.get(0).getPagenum();
                    nodatalayout.setVisibility(View.GONE);
                    if (collocationInformAdapter.getData().isEmpty()){
                        collocationInformAdapter.addData(preoducts);
                    }else {
                        if (page==1){
                            collocationInformAdapter.setNewData(preoducts);
                            swipeRefreshLayout.setRefreshing(false);
                        }else if(page <= pageNum){
                            collocationInformAdapter.addData(preoducts);
                            collocationInformAdapter.loadMoreComplete();
                        }else {
                            collocationInformAdapter.loadMoreEnd(true);
                        }
                    }
                }
                collocationInformAdapter.setEnableLoadMore(true);
                AcountUtil.closeProgressDialog();
            }

            @Override
            protected void finalize() throws Throwable {
                AcountUtil.closeProgressDialog();
                swipeRefreshLayout.setRefreshing(false);
                super.finalize();
            }
        }.execute();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        json = HttpConnect.getBasicJson(getActivity());
        page=1;
        getData();
    }

    @Override
    public void onClick(View view) {
    }
}
