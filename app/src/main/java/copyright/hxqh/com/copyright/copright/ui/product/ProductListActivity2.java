package copyright.hxqh.com.copyright.copright.ui.product;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;

/**
 * Created by zzw on 2018/9/27.
 */

public class ProductListActivity2 extends AppCompatActivity implements View.OnClickListener {
    private TextView titletext, producttext, channeltext;
    private ImageView backimage, searchimage;
    private ProductListFragment productListFragment;
    private ChannelListFragment channelListFragment;
    private View titleView;
    private SearchView searchView;
    private boolean  isProduct;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_list);
        findViewById();
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        searchView.setVisibility(View.GONE);
        searchimage.setVisibility(View.VISIBLE);
        titletext.setVisibility(View.VISIBLE);
    }
    /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_view, menu);
        //找到searchView
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        return onCreateOptionsMenu(menu);
    }*/

    public void findViewById() {
        titletext = findViewById(R.id.menu_title);
        backimage = findViewById(R.id.back_id);
        channeltext = findViewById(R.id.basictag_2);
        producttext = findViewById(R.id.basictag_1);
        searchimage = findViewById(R.id.title_search);
        titleView = findViewById(R.id.title_id);
        searchView = findViewById(R.id.search_bar);
    }

    public void initView() {
        titletext.setText("产品及运营");
        backimage.setOnClickListener(this);
        channeltext.setOnClickListener(this);
        producttext.setOnClickListener(this);
        searchimage.setOnClickListener(this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (isProduct){
                   productListFragment.setQueryString(query);
                }else {
                   channelListFragment.setQueryString(query);
                }
                searchView.setVisibility(View.GONE);
                searchimage.setVisibility(View.VISIBLE);
                titletext.setVisibility(View.VISIBLE);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        producttext.performClick();
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
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
                isProduct = true;
                producttext.setBackgroundColor(getResources().getColor(R.color.blue));
                channeltext.setBackgroundColor(getResources().getColor(R.color.white));
                if (productListFragment == null) {
                    productListFragment = new ProductListFragment();
                }
                fragmentTransaction.replace(R.id.list_frame, productListFragment).commit();
                break;
            case R.id.basictag_2:
                isProduct = false;
                channeltext.setBackgroundColor(getResources().getColor(R.color.blue));
                producttext.setBackgroundColor(getResources().getColor(R.color.white));
                if (channelListFragment == null) {
                    channelListFragment = new ChannelListFragment();
                }
                fragmentTransaction.replace(R.id.list_frame, channelListFragment).commit();
                break;
            case R.id.title_search:
                searchView.setVisibility(View.VISIBLE);
                searchimage.setVisibility(View.GONE);
                titletext.setVisibility(View.GONE);
                break;
        }
    }
}
