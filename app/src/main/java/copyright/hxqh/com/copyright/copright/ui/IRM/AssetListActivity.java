package copyright.hxqh.com.copyright.copright.ui.IRM;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.HttpManager.HttpConnect;
import copyright.hxqh.com.copyright.copright.ui.IRM.adapter.AssetAdapter;
import copyright.hxqh.com.copyright.copright.ui.IRM.enity.Asset;

public class AssetListActivity extends AppCompatActivity implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener,FilterFragment.FragmentInteraction{
    private EditText searchText;
    private ImageView back;
    private AssetAdapter assetAdapter;
    public RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    private LinearLayout nodatalayout;
    private RelativeLayout sortbycost,sortbydate,sortcheck;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int page = 1;
    private DrawerLayout mDrawerLayout;
    private FrameLayout mDrawerContent;
    private JSONObject json;
    private ImageView costSort_desc,costSort_asc,createdateSort_desc,createdateSort_asc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_list);
        findViewById();
        setSearchEdit();
        intView();
    }

    public void findViewById() {
        searchText = findViewById(R.id.search_edit);
        back = findViewById(R.id.ic_back);
        recyclerView = findViewById(R.id.recyclerView_id);
        swipeRefreshLayout = findViewById(R.id.swipe_container);
        nodatalayout = findViewById(R.id.have_not_data_id);
        sortbycost = findViewById(R.id.sortbycost_id);
        sortbydate = findViewById(R.id.sortbydate_id);
        sortcheck  = findViewById(R.id.sort_id);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerContent = (FrameLayout) findViewById(R.id.drawer_content);
        costSort_desc = findViewById(R.id.costsort_desc_id);
        costSort_asc = findViewById(R.id.costsort_asc_id);
        createdateSort_desc = findViewById(R.id.createdate_desc_id);
        createdateSort_asc = findViewById(R.id.createdate_asc_id);
    }
    public void intView(){
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        sortbycost.setOnClickListener(this);
        sortbydate.setOnClickListener(this);
        sortcheck.setOnClickListener(this);
        back.setOnClickListener(this);
        json = HttpConnect.getBasicJson(this);
        intAdapter();
        getData();

    }
    public void setSearchEdit(){
        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    ((InputMethodManager) searchText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    AssetListActivity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    page = 1;
                    try {
                        json.put("resourceName",searchText.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    getData();
                    return  true;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        try {
        switch (view.getId()){
            case R.id.ic_back:
                finish();
                break;
            case R.id.sortbycost_id:
                if (!json.has("costSort")){
                    json.put("costSort","asc");
                    costSort_asc.setImageResource(R.mipmap.ic_sort3);
                }else {
                    if (json.get("costSort").toString().equals("asc")){
                        json.put("costSort","desc");
                        costSort_asc.setImageResource(R.mipmap.ic_sort5);
                        costSort_desc.setImageResource(R.mipmap.ic_sort1);
                    }else {
                        json.put("costSort","asc");
                        costSort_desc.setImageResource(R.mipmap.ic_sort2);
                        costSort_asc.setImageResource(R.mipmap.ic_sort3);
                    }
                }
                    page = 1;
                    getData();
                break;
            case R.id.sortbydate_id:
                if (!json.has("createdateSort")){
                    json.put("createdateSort","asc");
                    createdateSort_asc.setImageResource(R.mipmap.ic_sort3);
                }else {
                    if (json.get("createdateSort").toString().equals("asc")){
                        json.put("createdateSort","desc");
                        createdateSort_asc.setImageResource(R.mipmap.ic_sort5);
                        createdateSort_desc.setImageResource(R.mipmap.ic_sort1);
                    }else {
                        json.put("createdateSort","asc");
                        createdateSort_desc.setImageResource(R.mipmap.ic_sort2);
                        createdateSort_asc.setImageResource(R.mipmap.ic_sort3);
                    }
                }
                page = 1;
                getData();
                break;
            case R.id.sort_id:
                Fragment fragment = new FilterFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                Bundle bundle = new Bundle();
                fragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.drawer_content, fragment).commit();
                mDrawerLayout.openDrawer(mDrawerContent);
                break;
        }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void intAdapter(){
        assetAdapter = new AssetAdapter(R.layout.fragment_item,new ArrayList<Asset>());
        recyclerView.setAdapter(assetAdapter);
        assetAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                getData();
            }
        },recyclerView);
        assetAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(AssetListActivity.this, AssetDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("asset", (Serializable) assetAdapter.getItem(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        json = HttpConnect.getBasicJson(this);
        page=1;
        getData();
    }
    public void getData(){
        new AsyncTask<String,String,List<Asset>>(){

            @Override
            protected List<Asset> doInBackground(String... strings) {
                List<Asset> assets = (List<Asset>) HttpConnect.getAssetList(json,page);
                return assets;
            }

            @Override
            protected void onPostExecute(List<Asset> assets) {
                super.onPostExecute(assets);
                if (assets == null){
                    assetAdapter.loadMoreFail();
                    return;
                }
                if (assets.isEmpty()){
                    nodatalayout.setVisibility(View.VISIBLE);
                    assetAdapter.replaceData(new ArrayList<Asset>());
                    assetAdapter.loadMoreEnd();
                }else {
                    nodatalayout.setVisibility(View.GONE);
                    if (assetAdapter.getData().isEmpty()){
                        assetAdapter.addData(assets);
                    }else {
                        if (page==1){
                            assetAdapter.setNewData(assets);
                            swipeRefreshLayout.setRefreshing(false);
                        }else {
                            assetAdapter.addData(assets);
                        }
                    }
                    assetAdapter.loadMoreComplete();
                }
                assetAdapter.setEnableLoadMore(true);
            }

            @Override
            protected void finalize() throws Throwable {
                super.finalize();
            }
        }.execute();
    }

    @Override
    public void process(Map<String,String> map){
        try {
        page = 1;
        json = HttpConnect.getBasicJson(this);
        Iterator  iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            String key = (String) iterator.next();

                json.put(key,map.get(key));
        }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getData();
    }


}
