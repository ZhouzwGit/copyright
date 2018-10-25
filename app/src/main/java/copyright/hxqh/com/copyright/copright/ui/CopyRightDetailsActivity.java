package copyright.hxqh.com.copyright.copright.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.entity.RightInfo;
import copyright.hxqh.com.copyright.copright.ui.author.entity.Right;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Prrel;

public class CopyRightDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView
            rightrestrict,
            contractnum,
            languages,
            earnings,
            resourceno,
            costprice,
            status,
            resourcename,
            personalproduct,
            copyright,
            gainway,
            resourcekind,
            deptnum,
            rightattribute,
            startdate,
            rightno,
            usestatus,
            finishplacearea,
            finishdate,
            history,
            earnings2,
            titletext;
    private ImageView backimage, searchimage;
    private Prrel prrel;
    private RightInfo rightInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy_right_details);
        getIntentData();
        findViewById();
        initView();
    }

    public void findViewById() {
        rightrestrict = findViewById(R.id.rightrestrict_id);
        earnings = findViewById(R.id.earnings_id);
        costprice = findViewById(R.id.costprice_id);
        personalproduct = findViewById(R.id.personalproduct_id);
        copyright = findViewById(R.id.copyright_id);
        gainway = findViewById(R.id.gainway_id);
        deptnum = findViewById(R.id.deptnum_id);
        rightattribute = findViewById(R.id.rightattribute_id);
        startdate = findViewById(R.id.startdate_id);
        rightno = findViewById(R.id.rightno_id);
        usestatus = findViewById(R.id.usestatus_id);
        finishplacearea = findViewById(R.id.finishplacearea_id);
        finishdate = findViewById(R.id.finishdate_id);
        history = findViewById(R.id.history_id);
        backimage = findViewById(R.id.back_id);
        searchimage = findViewById(R.id.title_search);
        earnings2 = findViewById(R.id.earnings2_id);
        titletext = findViewById(R.id.menu_title);
        contractnum = findViewById(R.id.contractnum_id);
        languages = findViewById(R.id.languages_id);
    }

    public void initView() {
        backimage.setOnClickListener(this);
        searchimage.setVisibility(View.GONE);
        history.setOnClickListener(this);
        earnings2.setOnClickListener(this);
        if (prrel != null) {
            rightrestrict.setText(prrel.getRightrestrict());
            earnings.setText(prrel.getEarnings());
            costprice.setText(prrel.getCostprice());
            personalproduct.setText(prrel.getPersonalproduct());
            copyright.setText(prrel.getCopyright());
            gainway.setText(prrel.getGainway());
            deptnum.setText(prrel.getDeptnum());
            rightattribute.setText(prrel.getRightattribute());
            startdate.setText(prrel.getStartdate());
            rightno.setText(prrel.getRightno());
            usestatus.setText(prrel.getUsestatus());
            finishplacearea.setText(prrel.getFinishplacearea());
            finishdate.setText(prrel.getFinishdate());
            titletext.setText(prrel.getResourcename());
            contractnum.setText(prrel.getContractnum());
            languages.setText(prrel.getLanguages());
        } else {
            rightrestrict.setText(rightInfo.getRightrestrict());
            earnings.setText(rightInfo.getEarnings());
            costprice.setText(rightInfo.getCostprice());
            personalproduct.setText(rightInfo.getPersonalproduct());
            copyright.setText(rightInfo.getCopyright());
            gainway.setText(rightInfo.getGainway());
            deptnum.setText(rightInfo.getDeptnum());
            rightattribute.setText(rightInfo.getRightattribute());
            startdate.setText(rightInfo.getStartdate());
            rightno.setText(rightInfo.getRightno());
            usestatus.setText(rightInfo.getUsestatus());
            finishplacearea.setText(rightInfo.getFinishplacearea());
            finishdate.setText(rightInfo.getFinishdate());
            titletext.setText(rightInfo.getResourcename());
            contractnum.setText(rightInfo.getContractnum());
            languages.setText(rightInfo.getLanguages());
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_id:
                finish();
                break;
            case R.id.history_id:
                break;
            case R.id.earnings_id:
                break;
            case R.id.earnings2_id:
                break;
        }
    }

    private void getIntentData() {

        if (getIntent().getIntExtra("type", 0) == 1) {
            prrel = (Prrel) getIntent().getSerializableExtra("copyright");
        } else {
            rightInfo = (RightInfo) getIntent().getSerializableExtra("copyright");
        }
    }
}
