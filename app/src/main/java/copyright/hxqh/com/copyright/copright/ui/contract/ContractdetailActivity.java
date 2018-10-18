package copyright.hxqh.com.copyright.copright.ui.contract;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.contract.adpter.PayresAdapter;
import copyright.hxqh.com.copyright.copright.ui.contract.entity.Contract;
import copyright.hxqh.com.copyright.copright.ui.contract.entity.Payres;
import copyright.hxqh.com.copyright.copright.view.MyListView;

public class ContractdetailActivity extends AppCompatActivity {
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
    private Contract contract;
    private List<Payres> payresList;
    private PayresAdapter payresAdapter;
    private MyListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contractdetailactivity1);
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
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        searchimage.setVisibility(View.GONE);
        contractname.setText(contract.getContractname());
        contractnum .setText(contract.getContractnum());
        authorizedparty .setText(contract.getAuthorizedparty());
        memo .setText(contract.getMemo());
        authorizedpartys .setText(contract.getAuthorizedpartys());
        signdate .setText(contract.getSigndate());
        status .setText(contract.getStatus());
        attachment.setText(contract.getAttachment());
        sealattachment.setText(contract.getSealattachment());
        initAdpter();
    }

    private void getIntentData() {
        contract = (Contract) getIntent().getExtras().get("contract");
        payresList = contract.getPayresList();
    }

    public void initAdpter() {
        payresAdapter = new PayresAdapter(this, R.layout.royalty_card, payresList);
        myListView.setAdapter(payresAdapter);
    }
}
