package copyright.hxqh.com.copyright.copright.ui.publicService;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.publicService.adapter.ObligeeInfoAdapter;
import copyright.hxqh.com.copyright.copright.ui.publicService.adapter.TortinfoAdapter;
import copyright.hxqh.com.copyright.copright.ui.publicService.entity.Obligeeinfo;
import copyright.hxqh.com.copyright.copright.ui.publicService.entity.RoyaltyEnity;
import copyright.hxqh.com.copyright.copright.ui.publicService.entity.Tortinfo;
import copyright.hxqh.com.copyright.copright.view.MyListView;

/**
 * Created by lianjh on 2018\10\17 0017.
 * Current page
 */

public class LawVinDicateDetailActivity extends AppCompatActivity {
    @Bind(R.id.menu_title) //标题
    TextView titleTextView;
    @Bind(R.id.back_id)
    ImageView backButton; //返回
    @Bind(R.id.title_search)
    ImageView searchButton; //搜索

    @Bind(R.id.lawvindicate_num) //维权编号
     TextView lawvindicateNum;
    @Bind(R.id.lawvindicate_status) //状态
    TextView lawvindicateStatus;
    @Bind(R.id.lawvindicate_book) //登记书
    TextView lawvindicateBook;
    @Bind(R.id.lawvindicate_confidencefile) //证明文件
    TextView lawvindicateConfidenceFile;
    @Bind(R.id.lawvindicate_demo) //作品样本
    TextView lawvindicateDemo;
    @Bind(R.id.lawvindicate_signaturefiles) //签章扫描件
     TextView lawvindicatesignaturefiles;
    @Bind(R.id.lawvindicate_printscreen)
    TextView lawvindicateprintscreen;

    @Bind(R.id.lawvindicate_telepeople) //联系人
    TextView lawvindicateTelepeople;
    @Bind(R.id.lawvindicate_telephone) //电话
    TextView lawvindicatePhone;
    @Bind(R.id.lawvindicate_email) //邮箱
     TextView lawvindicateEmail;
    @Bind(R.id.lawvindicate_advice) //处理意见
     TextView lawvindicateAdvice;

    @Bind(R.id.list)
    MyListView myListView;//权利人列表

    @Bind(R.id.list2)
    MyListView mListView;//侵权方列表

    private RoyaltyEnity royaltyEnity;

    private List<Obligeeinfo> obligeeinfoList;
    private List<Tortinfo> tortinfoList;

    private ObligeeInfoAdapter obligeeInfoAdapter;
    private TortinfoAdapter tortinfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_detail);
        ButterKnife.bind(this);
        myListView = findViewById(R.id.list);
        getIntentData();
        initView();
    }
    private void getIntentData() {
        royaltyEnity = (RoyaltyEnity) getIntent().getExtras().get("royalty");
        obligeeinfoList = royaltyEnity.getObligeeinfoList();
        tortinfoList = royaltyEnity.getTortinfoList();
    }
    private void initView() {
        titleTextView.setText(R.string.title_law_service);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        searchButton.setVisibility(View.GONE);
        if (royaltyEnity != null){
            lawvindicateNum.setText(royaltyEnity.getLawvindicateno());
            lawvindicateStatus.setText(royaltyEnity.getStatus());
            if (royaltyEnity.getIscredential().equals("true")){
                String str = royaltyEnity.getCredential();
                String[] splitstr=str.split("/");
                lawvindicateBook.setText(splitstr[splitstr.length - 1]);
            }
            if (royaltyEnity.getIscertifyfile().equals("true")){
                String str = royaltyEnity.getCertifyfile();
                String[] splitstr=str.split("/");
                lawvindicateConfidenceFile.setText(splitstr[splitstr.length - 1]);
            }
            if (royaltyEnity.getIsprintscreen().equals("true")){
                String str = royaltyEnity.getIsprintscreen();
                String[] splitstr=str.split("/");
                lawvindicateprintscreen.setText(splitstr[splitstr.length - 1]);
            }
            String str0 = royaltyEnity.getSignaturefiles();
            String[] splitstr0=str0.split("/");
            lawvindicatesignaturefiles.setText(splitstr0[splitstr0.length - 1]);

            String str1 = royaltyEnity.getSample();
            String[] splitstr1=str1.split("/");
            lawvindicateDemo.setText(splitstr1[splitstr1.length - 1]);

            lawvindicateTelepeople.setText(royaltyEnity.getLinkman());
            lawvindicatePhone.setText(royaltyEnity.getPhone());
            lawvindicateEmail.setText(royaltyEnity.getEmail());
            lawvindicateAdvice.setText(royaltyEnity.getDealidea());

        }
        initAdapter();
    }

    private void initAdapter() {
        obligeeInfoAdapter = new ObligeeInfoAdapter(this, R.layout.obligeeinfo_card,obligeeinfoList==null?new ArrayList<Obligeeinfo>():obligeeinfoList );
        myListView.setAdapter(obligeeInfoAdapter);

        tortinfoAdapter = new TortinfoAdapter(this, R.layout.tortinfo_card,tortinfoList);
        mListView.setAdapter(tortinfoAdapter);
    }
}
