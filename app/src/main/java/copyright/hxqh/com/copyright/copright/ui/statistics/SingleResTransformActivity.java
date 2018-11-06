package copyright.hxqh.com.copyright.copright.ui.statistics;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.HttpManager.HttpConnect;
import copyright.hxqh.com.copyright.copright.custom.ConstantsChart;
import copyright.hxqh.com.copyright.copright.custom.CustomLinearLayoutManager;
import copyright.hxqh.com.copyright.copright.ui.statistics.adapter.ResCostAdapter;
import copyright.hxqh.com.copyright.copright.ui.statistics.entity.ResCostingByKind;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;
import copyright.hxqh.com.copyright.copright.util.ChartUtils;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * Created by lianjh on 2018\11\5 0005.
 * Current page
 */

public class SingleResTransformActivity  extends FragmentActivity {
    @Bind(R.id.menu_title) //标题
            TextView titleTextView;
    @Bind(R.id.back_id)
    ImageView backButton; //返回
    @Bind(R.id.title_search)
    ImageView searchButton; //搜索
    @Bind(R.id.recyclerView_id)
    RecyclerView recyclerView;
    @Bind(R.id.text_detail)
    TextView detail;
    @Bind(R.id.liner_detail)
    LinearLayout liner_detail;
    @Bind(R.id.frameNewBase)
    ColumnChartView barChart;
    @Bind(R.id.item_tittle)
    TextView item_tittle;

    CustomLinearLayoutManager layoutManager;
    private Animation rotate;

    ResCostAdapter resCostAdapter;
    private JSONObject json;
    List<String> data = new ArrayList<>();
    ArrayList<String> text = new ArrayList<>();
    Float unstorage;
    //x轴的数据
    List<String> itemX = new ArrayList<>();
    //y轴的数据
    ArrayList<Float> itemY = new ArrayList<Float>();
    List<Float> mRatios = new ArrayList<>();

    ArrayList<String> text3 = new ArrayList<>();
    List<Float> mRatios3 = new ArrayList<>();
    ArrayList<String> itemY3 = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleutilize);
        ButterKnife.bind(this);
        initView();
    }
    protected void initView() {
        json = HttpConnect.getREStYPEJson(this);
        titleTextView = (TextView) findViewById(R.id.menu_title);
        titleTextView.setText("资产频度分析");
        item_tittle.setText("单资产转化率");
        backButton = (ImageView) findViewById(R.id.back_id);
        searchButton.setVisibility(View.GONE);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rotate = AnimationUtils.loadAnimation(this, R.anim.arrow_rotate);//创建动画
        liner_detail.setOnClickListener(imag_downOnClickListener);
        layoutManager = new CustomLinearLayoutManager(this);
        layoutManager.setScrollEnabled(false);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        barChart.setOnValueTouchListener(new ValueTouchListener());
        barChart.setValueSelectionEnabled(true);
        AcountUtil.showProgressDialog(this, "");
        getData(json);
    }
    private View.OnClickListener imag_downOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (startAnaim()) {
                detail.setVisibility(View.GONE);
                resCostAdapter = new ResCostAdapter(SingleResTransformActivity.this, (ArrayList<String>) data, text, (ArrayList<Float>) mRatios);
                recyclerView.setAdapter(resCostAdapter);
            } else {
                resCostAdapter = new ResCostAdapter(SingleResTransformActivity.this,itemY3,text3, (ArrayList<Float>) mRatios3);
                recyclerView.setAdapter(resCostAdapter);
                detail.setVisibility(View.VISIBLE);
            }
        }
    };
    private void getData(final JSONObject json) {
        final String url = "http://118.190.115.150:8889/jeesite/htjk/apphttpjk/singleResTransformData";
        new AsyncTask<String, String, ResCostingByKind>() {

            @Override
            protected ResCostingByKind doInBackground(String... strings) {
                ResCostingByKind products =  HttpConnect.getResCostingByKind(json,url);
                return products;
            }
            @Override
            protected void onPostExecute(ResCostingByKind preoducts) {
                super.onPostExecute(preoducts);
                if (preoducts!=null){
                    data =  preoducts.getSeries().get(0).getData();
                    text = (ArrayList<String>) preoducts.getXaxis().get(0).getData();
                }
                initColumnDatas();

                AcountUtil.closeProgressDialog();
            }
            @Override
            protected void finalize() throws Throwable {
                super.finalize();
            }
        }.execute();

    }
    private void initColumnDatas() {
        mRatios.clear();
        itemX.clear();
        itemY.clear();
        if (data != null){
            float sum = 0;
            for (int i = 0;i<data.size();i++){
                sum = sum + Float.valueOf(data.get(i));
            }
            for (int i = 0;i<data.size();i++){
                itemX.add(text.get(i).replaceAll("\n",""));
                itemY.add(Float.valueOf(data.get(i)));
                if (Float.valueOf(data.get(i)) == 0 || Float.valueOf(data.get(i)) == 0.0){
                    unstorage = Float.valueOf(0);
                }else {
                    unstorage = Float.valueOf(String.format("%.2f",(float)Float.valueOf(data.get(i))/sum));
                }
                mRatios.add(unstorage);
            }

        }else {
            Toast.makeText(SingleResTransformActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
        }
        if (itemY.size() < 3){
            liner_detail.setVisibility(View.GONE);
            resCostAdapter = new ResCostAdapter(this, (ArrayList<String>) data,text, (ArrayList<Float>) mRatios);
            recyclerView.setAdapter(resCostAdapter);
        }else {
            itemY3.clear();
            text3.clear();
            mRatios3.clear();
            for (int i=0;i<4;i++){
                itemY3.add(data.get(i));
                mRatios3.add(mRatios.get(i));
                text3.add(itemX.get(i));
            }
            resCostAdapter = new ResCostAdapter(this,itemY3,text3, (ArrayList<Float>) mRatios3);
            recyclerView.setAdapter(resCostAdapter);
        }
        //定义X轴刻度值的数据集合
        ArrayList<AxisValue> axisValuesX = new ArrayList<AxisValue>();
        for (int j = 0; j < itemX.size(); ++j) {
            axisValuesX.add(new AxisValue(j).setValue(j).setLabel(itemX.get(j)));
        }
        int numSubcolumns = 1;
        int numColumns = itemX.size();
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {
            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                values.add(new SubcolumnValue(itemY.get(i), Color.rgb(33,139,255)));
            }
            Column column = new Column(values);
            column.setHasLabels(ConstantsChart.hasLabels);
            column.setHasLabelsOnlyForSelected(false);
            columns.add(column);
        }

        itemX.clear();
        ColumnChartData data = new ColumnChartData(columns);

        if (ConstantsChart.hasAxes) {
            Axis axisX = new Axis();
            axisX.setTextColor(ChartUtils.DARKEN_COLOR);
            axisX.setValues(axisValuesX);
            axisX.setHasTiltedLabels(true);
            axisX.setTextSize(10);// 设置X轴文字大小
            axisX.setHasLines(false); //x 轴分割线
//            axisX.setMaxLabelChars(4);
            Axis axisY = new Axis().setHasLines(false);
            if (ConstantsChart.hasAxesNames) {
//                axisX.setName("作品入库情况占比");
//                axisY.setName("数量");
            }
            data.setAxisXBottom(axisX);
//            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }
        data.setValueLabelTextSize(10);
        data.setFillRatio(0.4F);
        //设置行为属性，支持缩放、滑动以及平移
        barChart.setInteractive(true);
        barChart.setZoomType(ZoomType.HORIZONTAL);

        barChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        barChart.setColumnChartData(data);

        Viewport v = new Viewport(barChart.getMaximumViewport());
        v.left = 0;
        v.right = 5;
        barChart.setCurrentViewport(v);

    }
    private class ValueTouchListener implements ColumnChartOnValueSelectListener {

        @Override
        public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {

        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }
    private boolean startAnaim() {

        rotate.setInterpolator(new LinearInterpolator());//设置为线性旋转

        rotate.setFillAfter(!rotate.getFillAfter());//每次都取相反值，使得可以不恢复原位的旋转

        liner_detail.startAnimation(rotate);
        return rotate.getFillAfter();
    }
}
