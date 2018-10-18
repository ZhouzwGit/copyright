package copyright.hxqh.com.copyright.copright.ui.IRM;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import copyright.hxqh.com.copyright.copright.ui.IRM.adapter.AssetAdapter;
import copyright.hxqh.com.copyright.copright.ui.IRM.enity.Asset;
import copyright.hxqh.com.copyright.copright.ui.author.AuthordetailActivity;
import copyright.hxqh.com.copyright.copright.ui.author.adpter.AuthorAdapter;
import copyright.hxqh.com.copyright.copright.ui.author.entity.Author;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;

/**
 * Created by zzw on 2018/9/27.
 */

public class AssetListActivity2 extends Activity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private TextView titletext, producttext, channeltext;
    private ImageView backimage, searchimage;
    private AssetAdapter authorAdapter;
    private View tagview, listView;
    private JSONObject json;
    private int page = 1;
    public RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    private LinearLayout nodatalayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    List<Asset> assets = new ArrayList<>();
    List<Asset> assets1 = new ArrayList<>();
    private boolean isAsset = true;
    private SearchView searchView;
    private int pageNo;
    private int assetCount;
    private int rescouceCount;

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
        recyclerView = findViewById(R.id.recyclerView_id);
        swipeRefreshLayout = findViewById(R.id.swipe_container);
        nodatalayout = findViewById(R.id.have_not_data_id);
        listView = findViewById(R.id.listshow_id);
        searchView = findViewById(R.id.search_bar);
    }

    public void initView() {
        titletext.setText("资产管理");
        listView.setVisibility(View.VISIBLE);
        backimage.setOnClickListener(this);
        channeltext.setOnClickListener(this);
        producttext.setOnClickListener(this);
        searchimage.setOnClickListener(this);
        producttext.setText("资产（0）个");
        channeltext.setText("资源（0）个");
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
                nodatalayout.setVisibility(View.GONE);
                authorAdapter.setEnableLoadMore(false);
                try {
                    json.put("resourceName", s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (isAsset){
                    assets.clear();
                }else {
                    assets1.clear();
                }
                authorAdapter.notifyDataSetChanged();
                searchView.setVisibility(View.GONE);
                searchimage.setVisibility(View.VISIBLE);
                titletext.setVisibility(View.VISIBLE);
                page = 1;
                AcountUtil.showProgressDialog(AssetListActivity2.this, "正在搜索");
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
            case R.id.basictag_1:
                producttext.setBackgroundColor(getResources().getColor(R.color.blue));
                channeltext.setBackgroundColor(getResources().getColor(R.color.white));
                authorAdapter.setNewData(assets);
                recyclerView.scrollToPosition(0);
                isAsset = true;
                break;
            case R.id.basictag_2:
                channeltext.setBackgroundColor(getResources().getColor(R.color.blue));
                producttext.setBackgroundColor(getResources().getColor(R.color.white));
                authorAdapter.setNewData(assets1);
                recyclerView.scrollToPosition(0);
                isAsset = false;
                break;
            case R.id.title_search:
                searchView.setVisibility(View.VISIBLE);
                searchimage.setVisibility(View.GONE);
                titletext.setVisibility(View.GONE);
                break;
        }
    }

    protected void onStart() {
        super.onStart();
        searchView.setVisibility(View.GONE);
        searchimage.setVisibility(View.VISIBLE);
        titletext.setVisibility(View.VISIBLE);
    }

    private void initAdpter() {
        authorAdapter = new AssetAdapter(R.layout.fragment_item);
        recyclerView.setAdapter(authorAdapter);
        authorAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
               if (isAsset){
                   if (assets.size() < assetCount){
                       getData();
                   }else {
                       authorAdapter.loadMoreEnd();
                   }
               }else {
                   if (assets1.size() < rescouceCount){
                       getData();
                   }else {
                       authorAdapter.loadMoreEnd();
                   }
               }

            }
        });
        authorAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(AssetListActivity2.this, AssetDetailActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("asset", (Serializable) authorAdapter.getItem(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public void getData() {
        new AsyncTask<String, String, List<Asset>>() {

            @Override
            protected List<Asset> doInBackground(String... strings) {
                List<Asset> products = (List<Asset>) HttpConnect.getAssetList(json, page);
                if (products == null) {
                    return null;
                }
                if(page == 1 && !products.isEmpty()){
                    pageNo = products.get(0).getPagenum();
                    Message message = new Message();
                    message.arg1 = products.get(0).getResourcenum();
                    message.arg2 = products.get(0).getAssetnum();
                    handler.sendMessage(message);
                }


                List<Asset> authorList1 = new ArrayList<>();
                List<Asset> authorList2 = new ArrayList<>();
                authorList1.clear();
                authorList2.clear();
                for (Asset author :
                        products) {
                    if (author.getIsassets().equals("1")) {
                        authorList1.add(author);
                    }
                    if (author.getIsassets().equals("0") ) {
                        authorList2.add(author);
                    }
                }
                if (page <= pageNo) {
                    assets.addAll(authorList1);
                    assets1.addAll(authorList2);
                }
                if (isAsset) {
                    return authorList1;
                } else {
                    return authorList2;
                }
            }

            @Override
            protected void onPostExecute(List<Asset> preoducts) {
                super.onPostExecute(preoducts);
                if (preoducts == null) {
                    authorAdapter.loadMoreFail();
                    if (authorAdapter.getData().isEmpty()) {
                        AcountUtil.closeProgressDialog();
                        nodatalayout.setVisibility(View.VISIBLE);
                        TextView textView = (TextView) nodatalayout.getChildAt(0);
                        textView.setText("加载失败请检查网络");
                        swipeRefreshLayout.setRefreshing(false);
                    }
                    return;
                }
                if (preoducts.isEmpty()) {
                    if (authorAdapter.getData().isEmpty()) {
                        nodatalayout.setVisibility(View.VISIBLE);
                        TextView textView = (TextView) nodatalayout.getChildAt(0);
                        textView.setText("暂无数据");
                        authorAdapter.replaceData(new ArrayList<Asset>());
                    } else {
                        authorAdapter.loadMoreEnd();
                    }
                } else {
                    nodatalayout.setVisibility(View.GONE);
                    if (authorAdapter.getData().isEmpty()) {
                        authorAdapter.addData(preoducts);
                    } else {
                        if (page == 1) {
                            authorAdapter.setNewData(preoducts);
                        } else if (page <= pageNo) {
                            authorAdapter.addData(preoducts);
                            authorAdapter.loadMoreComplete();
                        } else {
                            authorAdapter.loadMoreEnd();
                        }
                    }
                }
                swipeRefreshLayout.setRefreshing(false);
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
        json = HttpConnect.getBasicJson(this);
        assets.clear();
        assets1.clear();
        page = 1;
        getData();
    }
    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            assetCount= msg.arg2;
            rescouceCount = msg.arg1;
            producttext.setText("资产（"+msg.arg2+"）个");
            channeltext.setText("资源（"+msg.arg1+"）个");
        }
    };
}
