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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.HttpManager.HttpConnect;
import copyright.hxqh.com.copyright.copright.ui.product.ChannelDetailActivity;
import copyright.hxqh.com.copyright.copright.ui.product.adapter.ChannelAdapter;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Channel;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;

/**
 * Created by zzw on 2018/9/18.
 */

public class PayinformListFragment extends Fragment implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener {
    private ChannelAdapter channelAdapter;
    private JSONObject json;
    private int page = 1;
    private int pageNum;
    public RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    private LinearLayout nodatalayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String queryString;
    public void setQueryString(String queryString) {
        this.queryString = queryString;
        if (queryString!=null){
            try {
                json.put("channelName",queryString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        page = 1;
        getData();
    }
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
        channelAdapter = new ChannelAdapter(R.layout.channel_list_item);
        recyclerView.setAdapter(channelAdapter);
        channelAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                getData();
            }
        },recyclerView);
        channelAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), ChannelDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("channel", (Serializable) channelAdapter.getItem(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    public void getData(){
        new AsyncTask<String,String,List<Channel>>(){

            @Override
            protected List<Channel> doInBackground(String... strings) {
                List<Channel> products = (List<Channel>) HttpConnect.getChannelList(json,page);

                return products;
            }

            @Override
            protected void onPostExecute(List<Channel> preoducts) {
                super.onPostExecute(preoducts);
                if (preoducts == null){
                    channelAdapter.loadMoreFail();
                    if (channelAdapter.getData().isEmpty()){
                        nodatalayout.setVisibility(View.VISIBLE);
                        TextView textView = (TextView) nodatalayout.getChildAt(0);
                        textView.setText("加载失败请检查网络");
                    }
                    return;
                }
                if (preoducts.isEmpty()){
                    if (channelAdapter.getData().isEmpty()){
                        AcountUtil.closeProgressDialog();
                        nodatalayout.setVisibility(View.VISIBLE);
                        channelAdapter.replaceData(new ArrayList<Channel>());
                    }else {
                        channelAdapter.loadMoreEnd();
                    }
                }else {
                    pageNum = preoducts.get(0).getPagenum();
                    nodatalayout.setVisibility(View.GONE);
                    if (channelAdapter.getData().isEmpty()){
                        channelAdapter.addData(preoducts);
                    }else {
                        if (page==1){
                            channelAdapter.setNewData(preoducts);
                            swipeRefreshLayout.setRefreshing(false);
                        }else if(page <= pageNum){
                            channelAdapter.addData(preoducts);
                            channelAdapter.loadMoreComplete();
                        }else {
                            channelAdapter.loadMoreEnd(true);
                        }
                    }
                }
                channelAdapter.setEnableLoadMore(true);
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
