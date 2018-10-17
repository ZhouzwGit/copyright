package copyright.hxqh.com.copyright.copright.ui.author;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.author.adpter.AuthorResRelAdapter;
import copyright.hxqh.com.copyright.copright.ui.author.adpter.RighterResRelAdapter;
import copyright.hxqh.com.copyright.copright.ui.author.entity.Author;
import copyright.hxqh.com.copyright.copright.ui.author.entity.AuthorResRel;
import copyright.hxqh.com.copyright.copright.ui.author.entity.RighterResRel;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;
import copyright.hxqh.com.copyright.copright.view.MyListView;

public class AuthordetailActivity extends AppCompatActivity {
    private TextView
            name,
            ahthortype,
            restype,
            signname,
            resperson,
            ahthorno,
            respersonmobile,
            prideexp,
            credtype,
            credcardno,
            authormobile,
            mail,
            fax,
            wechatno,
            bank,
            bankno,
            isAuthor,
            district,
            titletext,
            resourceno1,
            resourcename1,
            resourcekind1,
            status1,
            resourceno2,
            resourcename2,
            resourcekind2,
            status2;
    private View table1, table2;
    private MyListView righterResRelList, authorResRelList;
    private Author author;
    private List<AuthorResRel> authorResRels;
    private List<RighterResRel> righterResRels;
    private AuthorResRelAdapter authorResRelAdapter;
    private RighterResRelAdapter righterResRelAdapter;
    private ImageView backimage,searchimage,userimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authordetail);
        getIntentData();
        findView();
        initView();
    }

    public void findView() {
        name = findViewById(R.id.name_id);
        ahthortype = findViewById(R.id.authortype_id);
        restype = findViewById(R.id.restype_id);
        signname = findViewById(R.id.signname);
        resperson = findViewById(R.id.resperson_id);
        ahthorno = findViewById(R.id.authornum_id);
        respersonmobile = findViewById(R.id.respersonmobile_id);
        prideexp = findViewById(R.id.prideexp_id);
        credtype = findViewById(R.id.credtype_id);
        credcardno = findViewById(R.id.credcardno_id);
        authormobile = findViewById(R.id.authormobile_id);
        mail = findViewById(R.id.email_id);
        fax = findViewById(R.id.faxno_id);
        wechatno = findViewById(R.id.wechatno);
        bank = findViewById(R.id.bank_id);
        bankno = findViewById(R.id.bankno_id);
        isAuthor = findViewById(R.id.isAuthor_id);
        district = findViewById(R.id.district_id);
        titletext = findViewById(R.id.menu_title);
        table1 = findViewById(R.id.table1);
        table2 = findViewById(R.id.table2);
        resourceno1 = table1.findViewById(R.id.assetnum_id);
        resourcename1 = table1.findViewById(R.id.producname_id);
        resourcekind1 = table1.findViewById(R.id.rightinfo_id);
        status1 = table1.findViewById(R.id.assetstatus_id);
        resourceno2 = table2.findViewById(R.id.assetnum_id);
        resourcename2 = table2.findViewById(R.id.producname_id);
        resourcekind2 = table2.findViewById(R.id.rightinfo_id);
        status2 = table2.findViewById(R.id.assetstatus_id);
        righterResRelList = findViewById(R.id.righterResRelList);
        authorResRelList = findViewById(R.id.authorResRelList);
        backimage = findViewById(R.id.back_id);
        searchimage = findViewById(R.id.title_search);
        userimage = findViewById(R.id.userimage_id);
    }

    private void initView() {
        AcountUtil.imageShow(userimage,author.getAuthorimg(),20);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        searchimage.setVisibility(View.GONE);
        name.setText(author.getName());
        ahthortype.setText(author.getAhthortype());
        restype.setText(author.getRestype());
        signname.setText(author.getSignname());
        resperson.setText(author.getResperson());
        ahthorno.setText(author.getAhthorno());
        respersonmobile.setText(author.getRespersonmobile());
        prideexp.setText(author.getPrideexp());
        credtype.setText(author.getCredtype());
        credcardno.setText(author.getCredcardno());
        authormobile.setText(author.getAuthormobile());
        mail.setText(author.getMail());
        fax.setText(author.getFax());
        wechatno.setText(author.getWechatno());
        bank.setText(author.getBank());
        bankno.setText(author.getBankno());
        isAuthor.setText(author.getIsAuthor());
        district.setText(author.getNation() + " " + author.getProvince() + "  " + author.getCity());
        titletext.setText(author.getName());
        resourceno1.setText("编号");
        resourcename1.setText("名称");
        resourcekind1.setText("类别");
        status1.setText("状态");
        resourceno2.setText("编号");
        resourcename2.setText("名称");
        resourcekind2.setText("类别");
        status2.setText("状态");
        initAdpter();
    }

    private void getIntentData() {
        author = (Author) getIntent().getExtras().get("author");
        authorResRels = author.getAuthorResRelList();
        righterResRels = author.getRighterResRelList();
    }
    public void initAdpter(){
        authorResRelAdapter = new AuthorResRelAdapter(this,R.layout.includeassetlayout,authorResRels);
        righterResRelAdapter = new RighterResRelAdapter(this,R.layout.includeassetlayout,righterResRels);
        authorResRelList.setAdapter(authorResRelAdapter);
        righterResRelList.setAdapter(righterResRelAdapter);
    }
}
