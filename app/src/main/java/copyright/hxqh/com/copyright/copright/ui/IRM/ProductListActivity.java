package copyright.hxqh.com.copyright.copright.ui.IRM;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.HttpManager.HttpConnect;
import copyright.hxqh.com.copyright.copright.ui.IRM.adapter.AssetAdapter;
import copyright.hxqh.com.copyright.copright.ui.IRM.enity.Asset;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;

public class ProductListActivity extends AppCompatActivity implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener{
    private EditText searchText;
    private ImageView back;
    private AssetAdapter assetAdapter;

    public RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    private LinearLayout nodatalayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        findViewById();
        setSearchEdit();
        intView();
        back.setOnClickListener(this);
    }

    public void findViewById() {
        searchText = findViewById(R.id.search_edit);
        back = findViewById(R.id.ic_back);
            recyclerView = findViewById(R.id.recyclerView_id);
            swipeRefreshLayout = findViewById(R.id.swipe_container);
            nodatalayout = findViewById(R.id.have_not_data_id);
    }
    public void intView(){
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        intAdapter();
        getData();

    }
    public void setSearchEdit(){
        SpannableString spannableString = new SpannableString("xxxx");
        Drawable drawable = getResources().getDrawable(R.mipmap.search_image);
        spannableString.setSpan(new ImageSpan(drawable),0,4,SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        searchText.setText(spannableString);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ic_back:
                finish();
                break;
        }
    }
    public void intAdapter(){
        assetAdapter = new AssetAdapter(R.layout.fragment_item,new ArrayList<Asset>());
        recyclerView.setAdapter(assetAdapter);
        assetAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(ProductListActivity.this, ProductDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("asset", (Serializable) assetAdapter.getItem(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRefresh() {

    }
    public void getData(){
        new AsyncTask<String,String,List<Asset>>(){

            @Override
            protected List<Asset> doInBackground(String... strings) {
                List<Asset> assets = (List<Asset>) HttpConnect.getAssetList(page+"","20",AcountUtil.getUsername(ProductListActivity.this));
                return assets;
            }

            @Override
            protected void onPostExecute(List<Asset> assets) {
                super.onPostExecute(assets);
                if (assets.isEmpty()){
                    nodatalayout.setVisibility(View.VISIBLE);
                }else {
                    nodatalayout.setVisibility(View.GONE);
                    assetAdapter.addData(assets);
                }
            }
        }.execute();
    }
}
