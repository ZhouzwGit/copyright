package copyright.hxqh.com.copyright.copright.ui.author;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
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
import copyright.hxqh.com.copyright.copright.ui.author.adpter.AuthorAdapter;
import copyright.hxqh.com.copyright.copright.ui.author.adpter.RoyaltyAdapter;
import copyright.hxqh.com.copyright.copright.ui.author.entity.Author;
import copyright.hxqh.com.copyright.copright.ui.author.entity.Royalty;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;

/**
 * Created by zzw on 2018/9/27.
 */

public class AuthorListActivity extends Activity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private TextView titletext, producttext, channeltext,badge1,badge2;
    private ImageView backimage, searchimage;
    private AuthorAdapter authorAdapter;
    private View tagview, listView;
    private JSONObject json;
    private int page = 1;
    public RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    private LinearLayout nodatalayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    List<Author> authors = new ArrayList<>();
    List<Author> authorsrol = new ArrayList<>();
    private boolean isAuthor = true;
    ProgressDialog progressDialog;
    private SearchView searchView;
    private int pageNo;
    private int countNum;
    View line1,line2;

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
        line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
        badge1 = findViewById(R.id.badge1);
        badge2 = findViewById(R.id.badge2);
    }

    public void initView() {
        badge1.setVisibility(View.GONE);
        badge2.setVisibility(View.GONE);
        titletext.setText("来源方管理");
        line1.setVisibility(View.VISIBLE);
        producttext.setTextColor(Color.parseColor("#1385f8"));
        producttext.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        listView.setVisibility(View.VISIBLE);
        backimage.setOnClickListener(this);
        channeltext.setOnClickListener(this);
        producttext.setOnClickListener(this);
        searchimage.setOnClickListener(this);
        producttext.setText("作者");
        channeltext.setText("权利人");
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
                authorAdapter.setEnableLoadMore(false);
                try {
                    json.put("authorName", s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                authors.clear();
                authorsrol.clear();
                searchView.setVisibility(View.GONE);
                searchimage.setVisibility(View.VISIBLE);
                titletext.setVisibility(View.VISIBLE);
                page = 1;
                AcountUtil.showProgressDialog(AuthorListActivity.this, "正在搜索");
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
                producttext.setTextColor(Color.parseColor("#1385f8"));
                producttext.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                channeltext.setTextColor(Color.BLACK);
                channeltext.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                line1.setVisibility(View.VISIBLE);
                line2.setVisibility(View.GONE);
                authorAdapter.setNewData(authors);
                recyclerView.scrollToPosition(0);
                isAuthor = true;
                break;
            case R.id.basictag_2:
                producttext.setTextColor(Color.BLACK);
                producttext.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                channeltext.setTextColor(Color.parseColor("#1385f8"));
                channeltext.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                line1.setVisibility(View.GONE);
                line2.setVisibility(View.VISIBLE);
                authorAdapter.setNewData(authorsrol);
                recyclerView.scrollToPosition(0);
                isAuthor = false;
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
        authorAdapter = new AuthorAdapter(R.layout.author_list_item);
        recyclerView.setAdapter(authorAdapter);
        authorAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                getData();
            }
        });
        authorAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(AuthorListActivity.this, AuthordetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("author", (Serializable) authorAdapter.getItem(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public void getData() {
        new AsyncTask<String, String, List<Author>>() {

            @Override
            protected List<Author> doInBackground(String... strings) {
                List<Author> products = (List<Author>) HttpConnect.getAuthorList(json, page);
                if (products == null) {
                    return null;
                }
                if (!products.isEmpty()){
                    pageNo = products.get(0).getPagenum();
                }
                List<Author> authorList1 = new ArrayList<>();
                List<Author> authorList2 = new ArrayList<>();
                authorList1.clear();
                authorList2.clear();
                for (Author author :
                        products) {
                    if (author.getIsAuthor() != null && author.getIsAuthor().contains("作者")) {
                        authorList1.add(author);
                    }
                    if (author.getIsAuthor() != null && author.getIsAuthor().contains("权利来源")) {
                        authorList2.add(author);
                    }
                }
                if (page <= pageNo) {
                    authors.addAll(authorList1);
                    authorsrol.addAll(authorList2);
                }
                if (isAuthor) {
                    return authorList1;
                } else {
                    return authorList2;
                }
            }

            @Override
            protected void onPostExecute(List<Author> preoducts) {
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
                        authorAdapter.replaceData(new ArrayList<Author>());
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
        authors.clear();
        authorsrol.clear();
        page = 1;
        getData();
    }
}
