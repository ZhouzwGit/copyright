package copyright.hxqh.com.copyright.copright.ui.author;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.author.adpter.AuthorResRelAdapter;
import copyright.hxqh.com.copyright.copright.ui.author.adpter.PayresAdapter;
import copyright.hxqh.com.copyright.copright.ui.author.adpter.RighterResRelAdapter;
import copyright.hxqh.com.copyright.copright.ui.author.entity.Author;
import copyright.hxqh.com.copyright.copright.ui.author.entity.AuthorResRel;
import copyright.hxqh.com.copyright.copright.ui.author.entity.Payres;
import copyright.hxqh.com.copyright.copright.ui.author.entity.RighterResRel;
import copyright.hxqh.com.copyright.copright.ui.author.entity.Royalty;
import copyright.hxqh.com.copyright.copright.view.MyListView;

public class RoyaltydetailActivity extends AppCompatActivity {
    private TextView
            name,
            field1,
            credtype,
            credcardno,
            authormobile,
            mail,
            fax,
            wechatno,
            status,
            bank,
            bankno,
            contractnum,
            titletext;
    private ImageView backimage, searchimage;
    private Royalty royalty;
    private List<Payres> payresList;
    private PayresAdapter payresAdapter;
    private MyListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.royaltydetailactivity);
        getIntentData();
        findView();
        initView();
    }

    public void findView() {
        name = findViewById(R.id.name_id);
        field1 = findViewById(R.id.field1_id);
        credtype = findViewById(R.id.credtype_id);
        credcardno = findViewById(R.id.credcardno_id);
        authormobile = findViewById(R.id.authormobile_id);
        mail = findViewById(R.id.email_id);
        fax = findViewById(R.id.faxno_id);
        wechatno = findViewById(R.id.wechatno);
        bank = findViewById(R.id.bank_id);
        bankno = findViewById(R.id.bankno_id);
        contractnum = findViewById(R.id.contractnum_id);
        titletext = findViewById(R.id.menu_title);
        backimage = findViewById(R.id.back_id);
        searchimage = findViewById(R.id.title_search);
        status = findViewById(R.id.status_id);
        myListView = findViewById(R.id.list);
    }

    private void initView() {
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        searchimage.setVisibility(View.GONE);
        name.setText(royalty.getName());
        titletext.setText(royalty.getName());
        field1.setText(royalty.getField1());
        credtype.setText(royalty.getCredtype());
        credcardno.setText(royalty.getCredcardno());
        authormobile.setText(royalty.getAuthormobile());
        mail.setText(royalty.getMail());
        status.setText(royalty.getStatus());
        fax.setText(royalty.getFax());
        wechatno.setText(royalty.getWechatno());
        bank.setText(royalty.getBank());
        bankno.setText(royalty.getBankno());
        contractnum.setText(royalty.getContractnum());
        initAdpter();
    }

    private void getIntentData() {
        royalty = (Royalty) getIntent().getExtras().get("royalty");
        payresList = royalty.getPayresList();
    }

    public void initAdpter() {
        payresAdapter = new PayresAdapter(this, R.layout.royalty_card,payresList);
        myListView.setAdapter(payresAdapter);
    }
}
