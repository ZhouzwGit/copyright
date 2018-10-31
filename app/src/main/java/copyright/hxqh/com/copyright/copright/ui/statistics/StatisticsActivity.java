package copyright.hxqh.com.copyright.copright.ui.statistics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.publicService.PublicServiceActivity;

public class StatisticsActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.workscount_id)
    RelativeLayout works;//作品数量分析
    @Bind(R.id.rightcount_id)
    RelativeLayout right;//权力项数量分析
    @Bind(R.id.costcount_id)
    RelativeLayout cost;//成本分析
    @Bind(R.id.earnings_id)
    RelativeLayout earn;//收益分析
    @Bind(R.id.productseq_id)
    RelativeLayout asset;//资产频度分析
    @Bind(R.id.title_search)
    ImageView searchimage;
    @Bind(R.id.back_id)
    ImageView backImage;
    @Bind(R.id.menu_title)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        ButterKnife.bind(this);
        works.setOnClickListener(this);
        right.setOnClickListener(this);
        cost.setOnClickListener(this);
        earn.setOnClickListener(this);
        asset.setOnClickListener(this);
        backImage.setOnClickListener(this);
        title.setText("统计分析");
        searchimage.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.workscount_id:
                Intent intent = new Intent(this, ProductionActivity.class);
                startActivity(intent);
                break;
            case R.id.rightcount_id:
                Intent intent1 = new Intent(this, RightCountActivity.class);
                startActivity(intent1);
                break;
            case R.id.costcount_id:
                break;
            case R.id.earnings_id:
                break;
            case R.id.productseq_id:
                break;
            case R.id.back_id:
                finish();
                break;
        }
    }
}
