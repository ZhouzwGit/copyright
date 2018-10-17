package copyright.hxqh.com.copyright.copright.ui.product;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.product.adapter.ProductSaleAdapter;
import copyright.hxqh.com.copyright.copright.ui.product.adapter.PrrelAdapter;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Acrel;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Channel;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Product;
import copyright.hxqh.com.copyright.copright.ui.product.entity.ProductInfoCollect;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Prrel;
import copyright.hxqh.com.copyright.copright.view.MyListView;

/**
 * Created by zzw on 2018/9/26.
 */

public class ChannelDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView credibility,channelname,bankaccount,registeredaddress,
    ein, thecontact,thecontactphone,channelnum,invoiceTitle,city,contractname,
    faxno,channeltype,titleType,citySel,provincesSel,channellevel,district,
    officeid,makeinvoicetype,unitaddress,provinces,openaccountbank,legalrepresentative,
    email,countnum,titletext,havenodata;
    private ImageView backImage,searcheImage;
    private Channel channel;
    private List<ProductInfoCollect> productInfoCollects;
    private ProductSaleAdapter productSaleAdapter;
    LinearLayout linearLayout;
    private MyListView myListView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channeldetailactivity);
        getIntentData();
        findViewById();
        initView();
    }
    public void findViewById() {
        credibility = findViewById(R.id.credibility_id);
        channelname = findViewById(R.id.channelname_id);
        bankaccount = findViewById(R.id.bankaccount_id);
        registeredaddress = findViewById(R.id.registeredaddress_id);
        ein = findViewById(R.id.ein_id);
        thecontact = findViewById(R.id.thecontact_id);
        thecontactphone = findViewById(R.id.thecontactphone_id);
        channelnum = findViewById(R.id.channelnum_id);
        faxno = findViewById(R.id.faxno_id);
        channeltype = findViewById(R.id.channelType_id);
        channellevel = findViewById(R.id.channellevel_id);
        makeinvoicetype = findViewById(R.id.makeinvoicetype_id);
        unitaddress = findViewById(R.id.unitaddress_id);
        openaccountbank = findViewById(R.id.openaccountbank_id);
        legalrepresentative = findViewById(R.id.legalrepresentative_id);
        email = findViewById(R.id.email_id);
        district = findViewById(R.id.district_id);
        titletext = findViewById(R.id.menu_title);
        backImage = findViewById(R.id.back_id);
        searcheImage = findViewById(R.id.title_search);
        myListView = findViewById(R.id.list);
        havenodata = findViewById(R.id.have_not_data_id);
    }
    public void initView(){
        if (productInfoCollects.size()==0){
            havenodata.setVisibility(View.VISIBLE);
        }
        titletext.setText("渠道详情");
        searcheImage.setVisibility(View.GONE);
        backImage.setOnClickListener(this);
        credibility.setText(channel.getCredibility());
        channelname.setText(channel.getChannelname());
        bankaccount.setText(channel.getBankaccount());
        registeredaddress.setText(channel.getRegisteredaddress());
        ein.setText(channel.getEin());
        thecontact.setText(channel.getThecontact());
        thecontactphone.setText(channel.getThecontactphone());
        channelnum.setText(channel.getChannelnum());
        faxno .setText(channel.getFaxno());
        channeltype.setText(channel.getChanneltype());
        channellevel.setText(channel.getChannellevel());
        makeinvoicetype.setText(channel.getMakeinvoicetype());
        unitaddress.setText(channel.getUnitaddress());
        openaccountbank.setText(channel.getOpenaccountbank());
        legalrepresentative.setText(channel.getLegalrepresentative());
        district.setText(channel.getProvincesSel() + "  " + channel.getCitySel());
        initAdpter();
    }
    @SuppressLint("ResourceAsColor")
    public void initAdpter(){
        productSaleAdapter = new ProductSaleAdapter(this,R.layout.productsale_list_item,productInfoCollects);
        myListView.setAdapter(productSaleAdapter);

    }

    public void getIntentData() {
        channel = (Channel) getIntent().getExtras().get("channel");
        productInfoCollects = channel.getProductInfoCollectList();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_id:
                finish();
                break;
        }
    }
}
