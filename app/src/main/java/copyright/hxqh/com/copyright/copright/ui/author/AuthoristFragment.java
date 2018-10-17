package copyright.hxqh.com.copyright.copright.ui.author;

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

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.HttpManager.HttpConnect;
import copyright.hxqh.com.copyright.copright.ui.product.ProductDetail;
import copyright.hxqh.com.copyright.copright.ui.product.adapter.ProductAdapter2;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Product;

/**
 * Created by zzw on 2018/9/18.
 */

public class AuthoristFragment extends Fragment implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener {
    private ProductAdapter2 productAdapter;
    private JSONObject json;
    private int page;
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
        getData();
    }
    private  void initAdpter(){
        productAdapter = new ProductAdapter2(R.layout.product_list_item2);
        recyclerView.setAdapter(productAdapter);
        productAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                getData();
            }
        },recyclerView);
        productAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), ProductDetail.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("product", (Serializable) productAdapter.getItem(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    public void getData(){
        new AsyncTask<String,String,List<Product>>(){

            @Override
            protected List<Product> doInBackground(String... strings) {
                List<Product> products = (List<Product>) HttpConnect.getProductList(json,page);
                return products;
            }

            @Override
            protected void onPostExecute(List<Product> preoducts) {
                super.onPostExecute(preoducts);
                if (preoducts == null){
                    productAdapter.loadMoreFail();
                    return;
                }
                if (preoducts.isEmpty()){
                    nodatalayout.setVisibility(View.VISIBLE);
                    productAdapter.replaceData(new ArrayList<Product>());
                    productAdapter.loadMoreEnd();
                }else {
                    nodatalayout.setVisibility(View.GONE);
                    if (productAdapter.getData().isEmpty()){
                        productAdapter.addData(preoducts);
                    }else {
                        if (page==1){
                            productAdapter.setNewData(preoducts);
                            swipeRefreshLayout.setRefreshing(false);
                        }else {
                            productAdapter.addData(preoducts);
                        }
                    }
                    productAdapter.loadMoreComplete();
                }
                productAdapter.setEnableLoadMore(true);
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
        json = HttpConnect.getBasicJson(getActivity());
        page=1;
        getData();
    }

    @Override
    public void onClick(View view) {
    }
}
