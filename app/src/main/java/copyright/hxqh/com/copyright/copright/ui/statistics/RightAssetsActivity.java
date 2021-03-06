package copyright.hxqh.com.copyright.copright.ui.statistics;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lixs.charts.PieChartView;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.HttpManager.HttpConnect;
import copyright.hxqh.com.copyright.copright.custom.ConstantsChart;
import copyright.hxqh.com.copyright.copright.ui.statistics.entity.ResourceKind;
import copyright.hxqh.com.copyright.copright.ui.statistics.entity.RightPutInStorage;
import copyright.hxqh.com.copyright.copright.ui.statistics.entity.Series_data;
import copyright.hxqh.com.copyright.copright.ui.statistics.entity.StorageCounts;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * Created by lianjh on 2018\10\31 0031.
 * Current page
 */

public class RightAssetsActivity   extends FragmentActivity {
    @Bind(R.id.menu_title) //标题
            TextView titleTextView;
    @Bind(R.id.back_id)
    ImageView backButton; //返回
    @Bind(R.id.title_search)
    ImageView searchButton; //搜索
    @Bind(R.id.storage_count)
    TextView storagecount;
    @Bind(R.id.storage_precent)
    TextView storageprecent;
    @Bind(R.id.unstorage_count)
    TextView unstoragecount;
    @Bind(R.id.unstorage_precent)
    TextView unstorageprecent;
    @Bind(R.id.pieView)
    PieChartView pieChartView;
    @Bind(R.id.text1)
    TextView text1;
    @Bind(R.id.text2)
    TextView text2;

    private JSONObject json2;

    ArrayList<Series_data> serieslist= new ArrayList<>();

    List<String> mDescription = new ArrayList<>();
    List<Integer> mArcColors = new ArrayList<>();
    List<Float> mRatios = new ArrayList<>();
    Float instorage;
    Float unstorage;
    private int blueColor = Color.rgb(39,71,132);
    private int greenColor = Color.rgb(1,191,157);
    ArrayList<Integer> itemY = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rightasset);
        ButterKnife.bind(this);
        initView();

    }
    protected void initView() {
        json2 =  HttpConnect.getREStYPEJson(this);
        titleTextView = (TextView) findViewById(R.id.menu_title);
        titleTextView.setText("资产库");
        backButton = (ImageView) findViewById(R.id.back_id);
        searchButton.setVisibility(View.GONE);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        AcountUtil.showProgressDialog(this, "");
        getData(json2);
    }

    private void getData(final JSONObject json) {
        final String url = "http://118.190.115.150:8889/jeesite/htjk/apphttpjk/rightAssetsTypePieData";
        new AsyncTask<String, String, StorageCounts>() {

            @Override
            protected StorageCounts doInBackground(String... strings) {
                StorageCounts products =  HttpConnect.getRightAssets(json,url);
                return products;
            }
            @Override
            protected void onPostExecute(StorageCounts preoducts) {
                super.onPostExecute(preoducts);
                serieslist = (ArrayList<Series_data>) preoducts.getSeries().get(0).getData();
                initPieDatas();
                AcountUtil.closeProgressDialog();
            }
            @Override
            protected void finalize() throws Throwable {
                super.finalize();
            }
        }.execute();

    }
    private void initPieDatas() {
        java.text.NumberFormat formate = java.text.NumberFormat.getNumberInstance();
        formate.setMaximumFractionDigits(2);
        mDescription.clear();
        mArcColors.clear();
        mRatios.clear();
        unstoragecount.setText("0");
        unstorageprecent.setText("(0%)");
        storagecount.setText("0");
        storageprecent.setText("(0%)");
        if (serieslist != null && serieslist.size()!=0){
            mArcColors.add(greenColor);
            mArcColors.add(blueColor);
            int sum = 0;
            for (int i = 0;i<serieslist.size();i++){
                sum = sum + serieslist.get(i).getValue();
            }

            for (int i = 0;i<serieslist.size();i++){
                itemY.add(serieslist.get(i).getValue());
                mDescription.add(serieslist.get(i).getName().replaceAll("\r\n",""));
                unstorage = Float.valueOf(String.format("%.4f",(float)serieslist.get(i).getValue()/sum));
                mRatios.add(unstorage);
            }
            if (mRatios.size()>1){
                text1.setText(mDescription.get(0));
                text2.setText(mDescription.get(1));
                storagecount.setText(String.valueOf(itemY.get(0)));
                unstoragecount.setText(String.valueOf(itemY.get(1)));
                storageprecent.setText("("+String.valueOf(formate.format(mRatios.get(0)*100))+"%)");
                unstorageprecent.setText("("+String.valueOf(formate.format(mRatios.get(1)*100))+"%)");
            }else {
                text1.setText(mDescription.get(0));
                text2.setText("");
                storagecount.setText(String.valueOf(itemY.get(0)));
                storageprecent.setText("("+String.valueOf(formate.format(mRatios.get(0)*100))+"%)");

            }

        }else {
            Toast.makeText(RightAssetsActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
        }

        //点击动画开启
        pieChartView.setCanClickAnimation(true);
        pieChartView.setDatas(mRatios, mArcColors, mDescription);
    }

}
