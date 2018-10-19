package copyright.hxqh.com.copyright.copright.ui.contract;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.contract.adpter.AccreditinfoAdapter;
import copyright.hxqh.com.copyright.copright.ui.contract.adpter.ConMatterDivideAdapter;
import copyright.hxqh.com.copyright.copright.ui.contract.adpter.ConRightDivideAdapter2;
import copyright.hxqh.com.copyright.copright.ui.contract.entity.Accreditinfo;
import copyright.hxqh.com.copyright.copright.ui.contract.entity.AuthContract;
import copyright.hxqh.com.copyright.copright.ui.contract.entity.ConMatterDivide;
import copyright.hxqh.com.copyright.copright.ui.contract.entity.ConRightDivide;
import copyright.hxqh.com.copyright.copright.ui.contract.entity.Cprel;
import copyright.hxqh.com.copyright.copright.view.MyListView;

public class AuthContractdetailActivity extends AppCompatActivity {
    private TextView
            contractname,
            contractnum,
            authorizedparty,
            memo,
            authorizedpartys,
            typeofpartyb,
            typeofpartya,
            signdate,
            status,
            attachment,
            sealattachment,
            pagenum,
            countnum,
            titletext;
    private ImageView backimage, searchimage;
    private AuthContract contract;
    private List<Cprel> cprelList;
    private List<ConRightDivide> conRightDivides;
    private List<ConMatterDivide> conMatterDivides;
    private ConRightDivideAdapter2 conRightDivideAdapter2;
    private AccreditinfoAdapter accreditinfoAdapter;
    private List<Accreditinfo> accreditinfos;
    private MyListView myListView;
    private ConMatterDivideAdapter conMatterDivideAdapter;
    @Bind(R.id.productname_id) //产品名称
            TextView productname;
    @Bind(R.id.productstatus_id) //产品状态
            TextView productstatus;
    @Bind(R.id.productno_id) //产品编号
            TextView productno;
    @Bind(R.id.productusage_id) //使用方式
            TextView productusage;
    @Bind(R.id.finishplacearea_id) //地域
            TextView finishplacearea;
    @Bind(R.id.lifetime_id) //使用期限
            TextView lifetime;
    @Bind(R.id.otherrestrict_id) //其他限制
            TextView other;
    @Bind(R.id.languages_id) //语言
            TextView languages;
    @Bind(R.id.list2)
    MyListView myListView2;
    @Bind(R.id.list3)
    ListView myListView3;
    @Bind(R.id.includereal_id)
    View view;
    @Bind(R.id.remark_id)
    TextView remark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contractdetailactivity2);
        ButterKnife.bind(this);
        getIntentData();
        findView();
        initView();
    }

    public void findView() {
        contractname = findViewById(R.id.contractname_id);
        contractnum = findViewById(R.id.contractnum_id);
        authorizedparty = findViewById(R.id.authorizedparty_id);
        memo = findViewById(R.id.memo_id);
        authorizedpartys = findViewById(R.id.authorizedpartys_id);
        signdate = findViewById(R.id.signdate_id);
        status = findViewById(R.id.status_id);
        attachment = findViewById(R.id.attachment_id);
        sealattachment = findViewById(R.id.sealattachment_id);
        titletext = findViewById(R.id.menu_title);
        backimage = findViewById(R.id.back_id);
        searchimage = findViewById(R.id.title_search);
        status = findViewById(R.id.status_id);
        myListView = findViewById(R.id.list);
    }

    private void initView() {
        if (!conMatterDivides.isEmpty()) {
            view.setVisibility(View.VISIBLE);
        }
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        searchimage.setVisibility(View.GONE);
        contractname.setText(contract.getContractname());
        contractnum.setText(contract.getContractnum());
        authorizedparty.setText(contract.getTypeofpartya());
        memo.setText(contract.getMemo());
        authorizedpartys.setText(contract.getTypeofpartyb());
        signdate.setText(contract.getSigndate());
        status.setText(contract.getStatus());
        //attachment.setText(contract.getAttachment());
        //sealattachment.setText(contract.getSealattachment());
        productname.setText(cprelList.get(0).getProductname());
        productno.setText(cprelList.get(0).getProductno());
        productstatus.setText(cprelList.get(0).getProductusage());
        lifetime.setText(cprelList.get(0).getLifetime() + "至" + cprelList.get(0).getLifetimeto());
        productstatus.setText(cprelList.get(0).getStatus());
        remark.setText(contract.getMemo());
        //languages.setText(cprelList.get(0).g);
        initAdpter();
    }

    private void getIntentData() {
        contract = (AuthContract) getIntent().getExtras().get("contract");
        cprelList = contract.getCprelList();
        conRightDivides = cprelList.get(0).getConRightDivideList();
        conMatterDivides = cprelList.get(0).getConMatterDivideList();
        accreditinfos = contract.getAccreditinfoList();
    }

    public void initAdpter() {
        accreditinfoAdapter = new AccreditinfoAdapter(this, R.layout.table_title_layout3, accreditinfos);
        myListView2.setAdapter(accreditinfoAdapter);
        conRightDivideAdapter2 = new ConRightDivideAdapter2(this, R.layout.conrightdivide_card, conRightDivides);
        myListView.setAdapter(conRightDivideAdapter2);
        conMatterDivideAdapter = new ConMatterDivideAdapter(this,R.layout.conrightdivide_card,conMatterDivides);
        myListView3.setAdapter(conMatterDivideAdapter);
    }
}
