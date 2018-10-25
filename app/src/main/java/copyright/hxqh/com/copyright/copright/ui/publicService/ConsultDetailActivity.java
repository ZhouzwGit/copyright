package copyright.hxqh.com.copyright.copright.ui.publicService;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.publicService.entity.Consult;
import copyright.hxqh.com.copyright.copright.ui.publicService.entity.RoyaltyEnity;
import copyright.hxqh.com.copyright.copright.util.StockUtil;

/**
 * Created by lianjh on 2018\10\19 0019.
 * Current page
 */

public class ConsultDetailActivity extends AppCompatActivity {
    @Bind(R.id.menu_title) //标题
            TextView titleTextView;
    @Bind(R.id.back_id)
    ImageView backButton; //返回
    @Bind(R.id.title_search)
    ImageView searchButton; //搜索

    @Bind(R.id.consult_num) //咨询编号
            TextView consultNum;
    @Bind(R.id.consult_status) //状态
            TextView consultStatus;
    @Bind(R.id.consult_user) //申请人姓名
            TextView consultUser;
    @Bind(R.id.consult_telephone) //电话
            TextView consultTelephone;
    @Bind(R.id.consult_description) //需求描述
            TextView consultDescription;
    @Bind(R.id.consult_file) //附件
            TextView consultFile;
    @Bind(R.id.consult_advice) //意见
            TextView consultAdvice;

    Consult consult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_detail);
        ButterKnife.bind(this);
        getIntentData();
        initView();
    }

    private void getIntentData() {
        consult = (Consult) getIntent().getExtras().get("consult");
    }
    private void initView() {
        titleTextView.setText("咨询服务");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        searchButton.setVisibility(View.GONE);
        if (consult !=null){
            consultNum.setText(consult.getServiceno());
            consultStatus.setText(consult.getStatus());
            consultUser.setText(consult.getApplyUser());
            consultTelephone.setText(consult.getPhone());
            consultDescription.setText(consult.getManagementabqu());
            consultAdvice.setText(consult.getDealidea());

            String str = consult.getServicefile();
            String[] splitstr=str.split("/");
            consultFile.setText(splitstr[splitstr.length - 1]);
        }
        consultFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new StockUtil().downLoadFile(ConsultDetailActivity.this, consult.getServicefile());
            }
        });
    }
}
