package copyright.hxqh.com.copyright.copright.ui.product;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.product.adapter.AcrelAdapter;
import copyright.hxqh.com.copyright.copright.ui.product.adapter.PrmtrelAdapter;
import copyright.hxqh.com.copyright.copright.ui.product.adapter.PrrelAdapter;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Acrel;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Prmtrel;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Product;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Prrel;

/**
 * Created by zzw on 2018/9/26.
 */

public class ProductDetail extends AppCompatActivity implements View.OnClickListener {
    private TextView productusage,
            lifetimeto,
            status,
            productname,
            titletext,
            havenodata1,
            havenodata2,
            tabletitle1,
            tabletitle2,
            tabletitle3;
    private View tabletitle;
    private ImageView backimage, searchimage;
    private Product product;
    private List<Prmtrel> list1 = new ArrayList<>();
    private List<Acrel> list3 = new ArrayList();
    private List<Prrel> list2 = new ArrayList();
    private View incloudView1;
    private PrmtrelAdapter prmtrelAdapter;
    private PrrelAdapter prrelAdapter;
    private AcrelAdapter acrelAdapter;
    private ListView listView1,listView2,listView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
        getIntentData();
        findViewById();
        initView();
    }

    public void findViewById() {
        backimage = findViewById(R.id.ic_back);
        incloudView1 = findViewById(R.id.incloud);
        tabletitle1 = incloudView1.findViewById(R.id.name_id);
        tabletitle2 = incloudView1.findViewById(R.id.copyright_id);
        tabletitle3 = incloudView1.findViewById(R.id.cost_id);
         productusage = findViewById(R.id.productusage_id);
        lifetimeto = findViewById(R.id.timelimit_id);
        status = findViewById(R.id.status_id);
        productname = findViewById(R.id.productname_id);
        titletext = findViewById(R.id.rightinfo_id);
        havenodata1 = findViewById(R.id.have_not_data_id);
        havenodata2 = findViewById(R.id.have_not_data_id2);
        listView1 = findViewById(R.id.list_1);
        listView2 = findViewById(R.id.list_2);
        listView3 = findViewById(R.id.list_3);
    }

    public void initView() {
        backimage.setOnClickListener(this);
        tabletitle1.setText("序号");
        tabletitle2.setText("作品编号");
        tabletitle3.setText("作品名称");
        if (list2.size()==0){
            havenodata1.setVisibility(View.VISIBLE);
        }
        if (list3.size()==0){
            havenodata2.setVisibility(View.VISIBLE);
        }
        productusage.setText(product.getProductusage());
        lifetimeto.setText(product.getLifetime() + "至" + product.getLifetimeto());
        status.setText(product.getStatus());
        productname.setText(product.getProductname());
        titletext.setText("产品详情");
        initAdpter();
    }

    @SuppressLint("ResourceAsColor")
    public void initAdpter() {
        prmtrelAdapter = new PrmtrelAdapter(this,R.layout.table_title_layout,list1);
        listView1.setAdapter(prmtrelAdapter);
        prrelAdapter = new PrrelAdapter(this,R.layout.productdetail_list1_item,list2);
        listView2.setAdapter(prrelAdapter);
        acrelAdapter = new AcrelAdapter(this,R.layout.productdetail_list2_item,list3);
        listView3.setAdapter(acrelAdapter);
    }

    public void getIntentData() {
        product = (Product) getIntent().getExtras().get("product");
        list3 = product.getAcrelList();
        list2 = product.getPrrelList();
        list1 = product.getPrmtrelList();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ic_back:
                finish();
                break;
        }
    }
}
