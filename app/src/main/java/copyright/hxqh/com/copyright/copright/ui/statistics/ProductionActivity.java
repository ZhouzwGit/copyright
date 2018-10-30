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
 * Created by lianjh on 2018\10\29 0029.
 * Current page
 */

public class ProductionActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.assetcount_id)
    RelativeLayout assetcount;//作品转化资产情况
    @Bind(R.id.storagecount_id)
    RelativeLayout storagecount;//作品入库情况占比
    @Bind(R.id.deadlinecount_id)
    RelativeLayout deadlinecount;//未截稿作品数量占比
    @Bind(R.id.author_id)
    RelativeLayout author;//高转化率作者TOP10
    @Bind(R.id.resource_id)
    RelativeLayout resource;//资源库
    @Bind(R.id.title_search)
    ImageView searchimage;
    @Bind(R.id.back_id)
    ImageView backImage;
    @Bind(R.id.menu_title)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production);
        ButterKnife.bind(this);
        assetcount.setOnClickListener(this);
        storagecount.setOnClickListener(this);
        deadlinecount.setOnClickListener(this);
        author.setOnClickListener(this);
        resource.setOnClickListener(this);
        backImage.setOnClickListener(this);
        title.setText("作品数量分析");
        searchimage.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.assetcount_id:
                Intent intent0 = new Intent(this, WorksPropertyActivity.class);
                startActivity(intent0);
                break;
            case R.id.storagecount_id:
                Intent intent = new Intent(this, WorksCountActivity.class);
                startActivity(intent);
                break;
            case R.id.deadlinecount_id:
                Intent intent1 = new Intent(this, DeadLineProductionCountsActivity.class);
                startActivity(intent1);
                break;
            case R.id.author_id:
                Intent intent2 = new Intent(this, MaxConversionAuthorActivity.class);
                startActivity(intent2);
                break;
            case R.id.resource_id:
                Intent intent3 = new Intent(this, ResTypePieActivity.class);
                startActivity(intent3);
                break;
            case R.id.back_id:
                finish();
                break;
        }
    }
}
