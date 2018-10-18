package copyright.hxqh.com.copyright.copright.ui.publicService;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.HttpManager.HttpConnect;
import copyright.hxqh.com.copyright.copright.ui.author.RoyaltydetailActivity;
import copyright.hxqh.com.copyright.copright.ui.author.adpter.RoyaltyAdapter;
import copyright.hxqh.com.copyright.copright.ui.author.entity.Royalty;
import copyright.hxqh.com.copyright.copright.ui.publicService.adapter.LawvindicateAdapter;
import copyright.hxqh.com.copyright.copright.ui.publicService.entity.RoyaltyEnity;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;

/**
 * Created by lianjh on 2018\10\17 0017.
 * Current page
 */

public class RoyaltyActivity  extends AppCompatActivity implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.menu_title) //标题
     TextView titleTextView;
    @Bind(R.id.back_id)
    ImageView backButton; //返回
    @Bind(R.id.title_search)
    ImageView searchButton; //搜索

    @Bind(R.id.search_bar)
    SearchView searchView; //搜索bar

    LinearLayoutManager layoutManager;

    @Bind(R.id.recyclerView_id)//RecyclerView
    RecyclerView recyclerView;
    @Bind(R.id.have_not_data_id)
    LinearLayout nodatalayout; //暂无数据
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout refresh_layout;//界面刷新

    private int page = 1;
    private int pageNum;

    private JSONObject json;

    private LawvindicateAdapter lawvindicateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        initView();
    }

    protected void initView() {
        titleTextView.setText(R.string.title_law_apply);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        refresh_layout.setOnRefreshListener(this);

        backButton.setOnClickListener(this);
        searchButton.setOnClickListener(this);
        json = HttpConnect.getBasicJson(this);
        initAdpter();
        AcountUtil.showProgressDialog(this, "");
        getData();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                try {
                    json.put("username", s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                searchView.setVisibility(View.GONE);
                searchButton.setVisibility(View.VISIBLE);
                titleTextView.setVisibility(View.VISIBLE);
                page = 1;
                AcountUtil.showProgressDialog(RoyaltyActivity.this, "正在搜索");
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
    public void onRefresh() {
        refresh_layout.setRefreshing(true);
        json = HttpConnect.getBasicJson(this);
        page = 1;
        getData();
    }

    public void getData() {
        new AsyncTask<String, String, List<RoyaltyEnity>>() {

            @Override
            protected List<RoyaltyEnity> doInBackground(String... strings) {
                List<RoyaltyEnity> products = (List<RoyaltyEnity>) HttpConnect.getLawvindicatelist(json, page);
                return products;
            }

            @Override
            protected void onPostExecute(List<RoyaltyEnity> preoducts) {
                super.onPostExecute(preoducts);
                if (preoducts == null) {
                    lawvindicateAdapter.loadMoreFail();
                    if (lawvindicateAdapter.getData().isEmpty()) {
                        AcountUtil.closeProgressDialog();
                        nodatalayout.setVisibility(View.VISIBLE);
                        TextView textView = (TextView) nodatalayout.getChildAt(0);
                        textView.setText("加载失败请检查网络");
                    }
                    return;
                }
                if (preoducts.isEmpty()) {
                    if (lawvindicateAdapter.getData().isEmpty()) {
                        nodatalayout.setVisibility(View.VISIBLE);
                        TextView textView = (TextView) nodatalayout.getChildAt(0);
                        textView.setText("暂无数据");
                        lawvindicateAdapter.replaceData(new ArrayList<RoyaltyEnity>());
                    } else {
                        lawvindicateAdapter.loadMoreEnd();
                    }
                } else {
                    pageNum = preoducts.get(0).getPagenum();
                    nodatalayout.setVisibility(View.GONE);
                    if (lawvindicateAdapter.getData().isEmpty()) {
                        lawvindicateAdapter.addData(preoducts);
                    } else {
                        if (page == 1) {
                            lawvindicateAdapter.setNewData(preoducts);
                        } else if (page <= pageNum){
                            lawvindicateAdapter.addData(preoducts);
                            lawvindicateAdapter.loadMoreComplete();
                        }else {
                            lawvindicateAdapter.loadMoreEnd();
                        }
                    }
                }
                lawvindicateAdapter.setEnableLoadMore(true);
                refresh_layout.setRefreshing(false);
                AcountUtil.closeProgressDialog();
            }

            @Override
            protected void finalize() throws Throwable {
                super.finalize();
            }
        }.execute();
    }


    private void initAdpter() {
        lawvindicateAdapter = new LawvindicateAdapter(R.layout.lawvindicate_list_item);
        recyclerView.setAdapter(lawvindicateAdapter);
        lawvindicateAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                getData();
            }
        }, recyclerView);
        lawvindicateAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(RoyaltyActivity.this, LawVinDicateDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("royalty", (Serializable) lawvindicateAdapter.getItem(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_id:
                if (searchView.getVisibility() == View.VISIBLE){
                    searchView.setVisibility(View.GONE);
                    searchButton.setVisibility(View.VISIBLE);
                    titleTextView.setVisibility(View.VISIBLE);
                }else {
                    finish();
                }
                break;
            case R.id.title_search:
                searchView.setVisibility(View.VISIBLE);
                searchButton.setVisibility(View.GONE);
                titleTextView.setVisibility(View.GONE);
                break;
        }
    }
}
