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
import copyright.hxqh.com.copyright.copright.ui.publicService.adapter.ConsultAdapter;
import copyright.hxqh.com.copyright.copright.ui.publicService.adapter.LawvindicateAdapter;
import copyright.hxqh.com.copyright.copright.ui.publicService.entity.Consult;
import copyright.hxqh.com.copyright.copright.ui.publicService.entity.RoyaltyEnity;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;

/**
 * Created by lianjh on 2018\10\19 0019.
 * Current page
 */

public class ConsultActivity extends AppCompatActivity implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener {
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

    private ConsultAdapter consultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        initView();
    }

    protected void initView() {
        titleTextView.setText(R.string.title_service_apply);

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
                AcountUtil.showProgressDialog(ConsultActivity.this, "正在搜索");
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
        new AsyncTask<String, String, List<Consult>>() {

            @Override
            protected List<Consult> doInBackground(String... strings) {
                List<Consult> products = (List<Consult>) HttpConnect.getConsultlist(json, page);
                return products;
            }

            @Override
            protected void onPostExecute(List<Consult> preoducts) {
                super.onPostExecute(preoducts);
                if (preoducts == null) {
                    consultAdapter.loadMoreFail();
                    if (consultAdapter.getData().isEmpty()) {
                        AcountUtil.closeProgressDialog();
                        nodatalayout.setVisibility(View.VISIBLE);
                        TextView textView = (TextView) nodatalayout.getChildAt(0);
                        textView.setText("加载失败请检查网络");
                    }
                    return;
                }
                if (preoducts.isEmpty()) {
                    if (consultAdapter.getData().isEmpty()) {
                        nodatalayout.setVisibility(View.VISIBLE);
                        TextView textView = (TextView) nodatalayout.getChildAt(0);
                        textView.setText("暂无数据");
                        consultAdapter.replaceData(new ArrayList<Consult>());
                    } else {
                        consultAdapter.loadMoreEnd();
                    }
                } else {
                    pageNum = preoducts.get(0).getPagenum();
                    nodatalayout.setVisibility(View.GONE);
                    if (consultAdapter.getData().isEmpty()) {
                        consultAdapter.addData(preoducts);
                    } else {
                        if (page == 1) {
                            consultAdapter.setNewData(preoducts);
                        } else if (page <= pageNum){
                            consultAdapter.addData(preoducts);
                            consultAdapter.loadMoreComplete();
                        }else {
                            consultAdapter.loadMoreEnd();
                        }
                    }
                }
                consultAdapter.setEnableLoadMore(true);
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
        consultAdapter = new ConsultAdapter(R.layout.consult_list_item);
        recyclerView.setAdapter(consultAdapter);
        consultAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                getData();
            }
        }, recyclerView);
        consultAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(ConsultActivity.this, ConsultDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("consult", (Serializable) consultAdapter.getItem(position));
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
