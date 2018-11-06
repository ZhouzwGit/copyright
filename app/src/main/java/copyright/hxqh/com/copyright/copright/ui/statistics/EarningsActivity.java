package copyright.hxqh.com.copyright.copright.ui.statistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import copyright.hxqh.com.copyright.R;

/**
 * Created by lianjh on 2018\11\2 0002.
 * Current page
 */

public class EarningsActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.earn_type_id)
    RelativeLayout earn_type;//按作品类型的收益统计
    @Bind(R.id.earn_right_id)
    RelativeLayout earn_right;//权利项收益分布
    @Bind(R.id.earn_project_id)
    RelativeLayout earn_project;//产品成本收益值
    @Bind(R.id.earn_property_id)
    RelativeLayout earn_property;//资产销售数量排行
    @Bind(R.id.title_search)
    ImageView searchimage;
    @Bind(R.id.back_id)
    ImageView backImage;
    @Bind(R.id.menu_title)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earnings);
        ButterKnife.bind(this);
        earn_type.setOnClickListener(this);
        earn_right.setOnClickListener(this);
        earn_project.setOnClickListener(this);
        earn_property.setOnClickListener(this);
        backImage.setOnClickListener(this);
        title.setText("资产价值收益分析");
        searchimage.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.earn_type_id:
                Intent intent0 = new Intent(this, ResEarningsByKindActivity.class);
                startActivity(intent0);
                break;
            case R.id.earn_right_id:
                Intent intent = new Intent(this, RightEarningsByResActivity.class);
                startActivity(intent);
                break;
            case R.id.earn_project_id:
                Intent intent1 = new Intent(this, ProductCostingActivity.class);
                startActivity(intent1);
                break;
            case R.id.earn_property_id:
                Intent intent2 = new Intent(this, AcrelSellCountsActivity.class);
                startActivity(intent2);
                break;
            case R.id.back_id:
                finish();
                break;
        }
    }
}
