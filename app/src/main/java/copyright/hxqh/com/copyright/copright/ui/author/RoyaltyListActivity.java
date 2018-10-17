package copyright.hxqh.com.copyright.copright.ui.author;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ImageView;
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
import copyright.hxqh.com.copyright.copright.ui.author.adpter.RoyaltyAdapter;
import copyright.hxqh.com.copyright.copright.ui.author.entity.Royalty;
import copyright.hxqh.com.copyright.copright.ui.contract.ContractListActivity;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Product;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;

/**
 * Created by zzw on 2018/9/27.
 */

public class RoyaltyListActivity extends Activity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private TextView titletext, producttext, channeltext;
    private ImageView backimage, searchimage;
    private View tagview, listView;
    private RoyaltyAdapter royaltyAdapter;
    private JSONObject json;
    private int page = 1;
    public RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    private LinearLayout nodatalayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SearchView searchView;
    private int pageNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_list);
        findViewById();
        initView();
    }

    public void findViewById() {
        titletext = findViewById(R.id.menu_title);
        backimage = findViewById(R.id.back_id);
        channeltext = findViewById(R.id.basictag_2);
        producttext = findViewById(R.id.basictag_1);
        searchimage = findViewById(R.id.title_search);
        tagview = findViewById(R.id.tagshow_id);
        listView = findViewById(R.id.listshow_id);
        recyclerView = findViewById(R.id.recyclerView_id);
        swipeRefreshLayout = findViewById(R.id.swipe_container);
        nodatalayout = findViewById(R.id.have_not_data_id);
        searchView = findViewById(R.id.search_bar);
    }

    public void initView() {
        tagview.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
        titletext.setText("版税管理");
        backimage.setOnClickListener(this);
        channeltext.setOnClickListener(this);
        producttext.setOnClickListener(this);
        searchimage.setOnClickListener(this);
        producttext.performClick();
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        json = HttpConnect.getBasicJson(this);
        initAdpter();
        AcountUtil.showProgressDialog(this, "");
        getData();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                try {
                    json.put("authorName", s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                searchView.setVisibility(View.GONE);
                searchimage.setVisibility(View.VISIBLE);
                titletext.setVisibility(View.VISIBLE);
                page = 1;
                AcountUtil.showProgressDialog(RoyaltyListActivity.this, "正在搜索");
                getData();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        searchView.setVisibility(View.GONE);
        searchimage.setVisibility(View.VISIBLE);
        titletext.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                if (searchView.getVisibility() == View.VISIBLE) {
                    searchView.setVisibility(View.GONE);
                    searchimage.setVisibility(View.VISIBLE);
                    titletext.setVisibility(View.VISIBLE);
                } else {
                    finish();
                }
                break;
            case R.id.title_search:
                searchView.setVisibility(View.VISIBLE);
                searchimage.setVisibility(View.GONE);
                titletext.setVisibility(View.GONE);
                break;
        }
    }

    private void initAdpter() {
        royaltyAdapter = new RoyaltyAdapter(R.layout.royalty_list_item);
        recyclerView.setAdapter(royaltyAdapter);
        royaltyAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                getData();
            }
        }, recyclerView);
        royaltyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(RoyaltyListActivity.this, RoyaltydetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("royalty", (Serializable) royaltyAdapter.getItem(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public void getData() {
        new AsyncTask<String, String, List<Royalty>>() {

            @Override
            protected List<Royalty> doInBackground(String... strings) {
                List<Royalty> products = (List<Royalty>) HttpConnect.getRoyaltyList(json, page);
                return products;
            }

            @Override
            protected void onPostExecute(List<Royalty> preoducts) {
                super.onPostExecute(preoducts);
                if (preoducts == null) {
                    royaltyAdapter.loadMoreFail();
                    if (royaltyAdapter.getData().isEmpty()) {
                        AcountUtil.closeProgressDialog();
                        nodatalayout.setVisibility(View.VISIBLE);
                        TextView textView = (TextView) nodatalayout.getChildAt(0);
                        textView.setText("加载失败请检查网络");
                    }
                    return;
                }
                if (preoducts.isEmpty()) {
                    if (royaltyAdapter.getData().isEmpty()) {
                        nodatalayout.setVisibility(View.VISIBLE);
                        TextView textView = (TextView) nodatalayout.getChildAt(0);
                        textView.setText("暂无数据");
                        royaltyAdapter.replaceData(new ArrayList<Royalty>());
                    } else {
                        royaltyAdapter.loadMoreEnd();
                    }
                } else {
                    pageNum = preoducts.get(0).getPagenum();
                    nodatalayout.setVisibility(View.GONE);
                    if (royaltyAdapter.getData().isEmpty()) {
                        royaltyAdapter.addData(preoducts);
                    } else {
                        if (page == 1) {
                            royaltyAdapter.setNewData(preoducts);
                        } else if (page <= pageNum){
                            royaltyAdapter.addData(preoducts);
                            royaltyAdapter.loadMoreComplete();
                        }else {
                            royaltyAdapter.loadMoreEnd();
                        }
                    }
                }
                royaltyAdapter.setEnableLoadMore(true);
                swipeRefreshLayout.setRefreshing(false);
                AcountUtil.closeProgressDialog();
            }

            @Override
            protected void finalize() throws Throwable {
                super.finalize();
            }
        }.execute();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        json = HttpConnect.getBasicJson(this);
        page = 1;
        getData();
    }
}
