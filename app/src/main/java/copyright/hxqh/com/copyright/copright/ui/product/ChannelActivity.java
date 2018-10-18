package copyright.hxqh.com.copyright.copright.ui.product;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.entity.RightInfo;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Acrel;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Prrel;

public class ChannelActivity extends AppCompatActivity {
    private TextView languagerestrict;
    private TextView cost;
    private TextView enddate;
    private TextView otherrestrict;
    private TextView territoryrestrict;
    private TextView payment;
    private TextView startdate;
    private TextView channelname;
    ;
    private TextView remarks;

    private ImageView backimage, searchimage;
    private Acrel acrel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel_activity);
        getIntentData();
        findViewById();
        initView();
    }

    public void findViewById() {
        backimage = findViewById(R.id.back_id);
        searchimage = findViewById(R.id.title_search);
        languagerestrict = findViewById(R.id.languagerestrict_id);
        cost = findViewById(R.id.cost_id);
        enddate = findViewById(R.id.finishdate_id);
        territoryrestrict = findViewById(R.id.territoryrestrict_id);
        payment = findViewById(R.id.payment_id);
        startdate = findViewById(R.id.startdate_id);
        channelname = findViewById(R.id.channelname_id);


    }

    public void initView() {
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        searchimage.setVisibility(View.GONE);
        languagerestrict.setText(acrel.getLanguagerestrict());
        cost.setText(acrel.getCost() + "");
        enddate.setText(acrel.getEnddate() + "");
        territoryrestrict .setText(acrel.getTerritoryrestrict() + "");
        payment.setText(acrel.getPayment() + "");
        startdate.setText(acrel.getStartdate() + "");
        channelname .setText(acrel.getChannelname() + "");
    }

    private void getIntentData() {
        acrel = (Acrel) getIntent().getSerializableExtra("acrel");
    }
}
