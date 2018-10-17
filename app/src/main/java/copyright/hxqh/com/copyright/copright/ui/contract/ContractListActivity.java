package copyright.hxqh.com.copyright.copright.ui.contract;

import android.app.Activity;
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
import copyright.hxqh.com.copyright.copright.ui.author.AuthordetailActivity;
import copyright.hxqh.com.copyright.copright.ui.author.entity.Author;
import copyright.hxqh.com.copyright.copright.ui.contract.adpter.ContractAdapter;
import copyright.hxqh.com.copyright.copright.ui.contract.entity.Contract;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;

/**
 * Created by zzw on 2018/9/27.
 */

public class ContractListActivity extends Activity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private TextView titletext, producttext, channeltext;
    private ImageView backimage, searchimage;
    private ContractAdapter contractAdapter;
    private View tagview, listView;
    private JSONObject json;
    private int page;
    public RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    private LinearLayout nodatalayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    //获权合同list
    List<Contract> contracttype1 = new ArrayList<>();
    //授权合同list
    List<Contract> contracttype2 = new ArrayList<>();
    //true 为获权； false 为授权
    private boolean contracttype = true;
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
        recyclerView = findViewById(R.id.recyclerView_id);
        swipeRefreshLayout = findViewById(R.id.swipe_container);
        nodatalayout = findViewById(R.id.have_not_data_id);
        listView = findViewById(R.id.listshow_id);
        searchView = findViewById(R.id.search_bar);
    }

    public void initView() {
        titletext.setText("合同列表");
        listView.setVisibility(View.VISIBLE);
        backimage.setOnClickListener(this);
        channeltext.setOnClickListener(this);
        producttext.setOnClickListener(this);
        producttext.setText("获权合同");
        channeltext.setText("授权合同");
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        json = HttpConnect.getBasicJson(this);
        searchimage.setOnClickListener(this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {

                    try {
                        json.put("contractName", s);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    contracttype1.clear();
                    contracttype2.clear();
                    searchView.setVisibility(View.GONE);
                    searchimage.setVisibility(View.VISIBLE);
                    titletext.setVisibility(View.VISIBLE);
                    page = 1;
                    AcountUtil.showProgressDialog(ContractListActivity.this, "正在搜索");
                    getData();
                    return true;
                }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        initAdpter();
        AcountUtil.showProgressDialog(this, "");
        getData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                if (searchView.getVisibility() == View.VISIBLE){
                    searchView.setVisibility(View.GONE);
                    searchimage.setVisibility(View.VISIBLE);
                    titletext.setVisibility(View.VISIBLE);
                }else {
                    finish();
                }
                break;
            case R.id.basictag_1:
                producttext.setBackgroundColor(getResources().getColor(R.color.blue));
                channeltext.setBackgroundColor(getResources().getColor(R.color.white));
                recyclerView.scrollToPosition(0);
                contractAdapter.setNewData(contracttype1);
                contracttype = true;
                break;
            case R.id.basictag_2:
                channeltext.setBackgroundColor(getResources().getColor(R.color.blue));
                producttext.setBackgroundColor(getResources().getColor(R.color.white));
                recyclerView.scrollToPosition(0);
                contractAdapter.setNewData(contracttype2);
                contracttype = false;
                break;
            case R.id.title_search:
                searchView.setVisibility(View.VISIBLE);
                searchimage.setVisibility(View.GONE);
                titletext.setVisibility(View.GONE);
                break;
        }
    }

    private void initAdpter() {
        contractAdapter = new ContractAdapter(R.layout.list_contract_card, new ArrayList<Contract>());
        contractAdapter.setEnableLoadMore(true);
        recyclerView.setAdapter(contractAdapter);
        contractAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                getData();
            }
        }, recyclerView);
        contractAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
               /* Intent intent = new Intent(ContractListActivity.this, AuthordetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("author", (Serializable) contractAdapter.getItem(position));
                intent.putExtras(bundle);
                startActivity(intent);*/
            }
        });
    }

    public void getData() {
        new AsyncTask<String, String, List<Contract>>() {

            @Override
            protected List<Contract> doInBackground(String... strings) {
                List<Contract> products = (List<Contract>) HttpConnect.getContractList(json, page);
                if (products == null) {
                    return null;
                }
                pageNum = products.get(0).getPagenum();
                List<Contract> contractList1 = new ArrayList<>();
                List<Contract> contractList2 = new ArrayList<>();
                contractList1.clear();
                contractList2.clear();
                for (Contract contract :
                        products) {
                    if (contract.getContracttype().contains("获权合同")) {
                        contractList1.add(contract);
                    }
                    if (contract.getContracttype().contains("授权合同")) {
                        contractList2.add(contract);
                    }
                }
                if (page <= pageNum){
                    contracttype1.addAll(contractList1);
                    contracttype2.addAll(contractList2);
                }
                if (contracttype) {
                    return contractList1;
                } else {

                    return contractList2;
                }
            }

            @Override
            protected void onPostExecute(List<Contract> preoducts) {
                super.onPostExecute(preoducts);
                if (preoducts == null) {
                    contractAdapter.loadMoreFail();
                    if (contractAdapter.getData().isEmpty()) {
                        nodatalayout.setVisibility(View.VISIBLE);
                        TextView textView = (TextView) nodatalayout.getChildAt(0);
                        textView.setText("加载失败请检查网络");
                    }
                    return;
                }
                if (preoducts.isEmpty()) {
                    if (contractAdapter.getData().isEmpty()) {
                        AcountUtil.closeProgressDialog();
                        nodatalayout.setVisibility(View.VISIBLE);
                        TextView textView = (TextView) nodatalayout.getChildAt(0);
                        textView.setText("暂无数据");
                        contractAdapter.replaceData(new ArrayList<Contract>());
                    }

                } else {
                    nodatalayout.setVisibility(View.GONE);
                    if (contractAdapter.getData().isEmpty()) {
                        contractAdapter.addData(preoducts);
                    } else {
                        if (page == 1) {
                            contractAdapter.setNewData(preoducts);
                            swipeRefreshLayout.setRefreshing(false);
                        } else if (page <= pageNum){
                            contractAdapter.addData(preoducts);
                            contractAdapter.loadMoreComplete();
                        }else {
                            contractAdapter.loadMoreEnd();
                        }
                    }

                }
                contractAdapter.setEnableLoadMore(true);
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
        page = 1;
        contracttype1.clear();
        contracttype2.clear();
        getData();
    }
}
