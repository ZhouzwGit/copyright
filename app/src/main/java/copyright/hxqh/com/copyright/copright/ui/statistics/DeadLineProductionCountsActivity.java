package copyright.hxqh.com.copyright.copright.ui.statistics;

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

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.lixs.charts.PieChartView;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.HttpManager.HttpConnect;
import copyright.hxqh.com.copyright.copright.custom.ConstantsChart;
import copyright.hxqh.com.copyright.copright.ui.statistics.entity.ResourceKind;
import copyright.hxqh.com.copyright.copright.ui.statistics.entity.StorageCounts;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * Created by lianjh on 2018\10\29 0029.
 * Current page
 */

public class DeadLineProductionCountsActivity  extends FragmentActivity {
    @Bind(R.id.menu_title) //标题
            TextView titleTextView;
    @Bind(R.id.back_id)
    ImageView backButton; //返回
    @Bind(R.id.title_search)
    ImageView searchButton; //搜索
    @Bind(R.id.text_resourcekind)
    TextView textresourcekind;
    @Bind(R.id.recoursetype)
    ImageView recoursetype;
    @Bind(R.id.storage_count)
    TextView storagecount;
    @Bind(R.id.storage_precent)
    TextView storageprecent;
    @Bind(R.id.unstorage_count)
    TextView unstoragecount;
    @Bind(R.id.unstorage_precent)
    TextView unstorageprecent;
    @Bind(R.id.liner_pie)
    LinearLayout linerPie;
    @Bind(R.id.text_pie)
    TextView textpie;
    @Bind(R.id.image_pie)
    ImageView imagepie;
    @Bind(R.id.liner_LBar)
    LinearLayout linerLbar;
    @Bind(R.id.text_Lbar)
    TextView textLbar;
    @Bind(R.id.image_Lbar)
    ImageView imageLbar;
    @Bind(R.id.pieView)
    PieChartView pieChartView;
    @Bind(R.id.frameNewBase)
    ColumnChartView barChart;
    @Bind(R.id.text1)
    TextView text1;
    @Bind(R.id.text2)
    TextView text2;
    Dialog mCameraDialog;

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private int mScreenWidth;

    String resourcekind;
    private JSONObject json;
    private JSONObject json2;
    private int page = 1;

    ArrayList<String> list = new ArrayList<>();
    ArrayList<Integer> data = new ArrayList<>();
    ArrayList<String> text = new ArrayList<>();
    List<String> mDescription = new ArrayList<>();
    List<Integer> mArcColors = new ArrayList<>();
    List<Float> mRatios = new ArrayList<>();
    Float instorage;
    Float unstorage;
    private int blueColor = Color.rgb(39,71,132);
    private int greenColor = Color.rgb(1,191,157);
    //x轴的数据
    List<String> itemX = new ArrayList<>();
    //y轴的数据
    ArrayList<Integer> itemY = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadlineproduction);
        ButterKnife.bind(this);
        initView();
    }

    protected void initView() {
        json = HttpConnect.getBasicJson(this);
        json2 = HttpConnect.getDeadLineProductionCounts(this, resourcekind);
        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        titleTextView = (TextView) findViewById(R.id.menu_title);
        titleTextView.setText("未截稿作品数量占比");
        backButton = (ImageView) findViewById(R.id.back_id);
        searchButton.setVisibility(View.GONE);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recoursetype.setOnClickListener(recoursetypeOnClickListener);
        barChart.setVisibility(View.GONE);
        barChart.setOnValueTouchListener(new ValueTouchListener());
        barChart.setValueSelectionEnabled(true);
        linerPie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pieChartView.setVisibility(View.VISIBLE);
                barChart.setVisibility(View.GONE);
                textpie.setTextColor(Color.parseColor("#ffffff"));
                textLbar.setTextColor(Color.parseColor("#218BFF"));
                linerPie.setBackgroundResource(R.drawable.tab_left_selector);
                linerLbar.setBackgroundResource(R.drawable.tab_right_unselector);
                imagepie.setImageResource(R.drawable.ic_pie);
                imageLbar.setImageResource(R.drawable.ic_line);
            }
        });
        linerLbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pieChartView.setVisibility(View.GONE);
                barChart.setVisibility(View.VISIBLE);
                textpie.setTextColor(Color.parseColor("#218BFF"));
                textLbar.setTextColor(Color.parseColor("#ffffff"));
                linerPie.setBackgroundResource(R.drawable.tab_left_unselector);
                linerLbar.setBackgroundResource(R.drawable.tab_right_selector);
                imagepie.setImageResource(R.drawable.ic_unselector_pie);
                imageLbar.setImageResource(R.drawable.ic_unselector_line);

            }
        });
        AcountUtil.showProgressDialog(this, "");
        getType();
        getData(json2);
    }

    private void getType() {
        new AsyncTask<String, String, List<ResourceKind>>() {

            @Override
            protected List<ResourceKind> doInBackground(String... strings) {
                List<ResourceKind> products = (List<ResourceKind>) HttpConnect.getResourceType(json, page);
                return products;
            }

            @Override
            protected void onPostExecute(List<ResourceKind> preoducts) {
                for (int i = 0; i < preoducts.size(); i++) {
                    list.add(preoducts.get(i).getResourcekind());
                }
                if (preoducts.size() % 2 == 0) {
                    resourcekind = preoducts.get(preoducts.size() / 2).getResourcekind();
                } else if (preoducts.size() % 2 == 1) {
                    resourcekind = preoducts.get(preoducts.size() / 2 + 1).getResourcekind();
                }

            }

            @Override
            protected void finalize() throws Throwable {
                super.finalize();
            }
        }.execute();
    }

    private View.OnClickListener recoursetypeOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showDialog();
        }
    };

    private void showDialog() {
        mCameraDialog = new Dialog(this, R.style.my_dialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.my_dialog, null);
        LoopView loopView = (LoopView) root.findViewById(R.id.loopView);
        root.findViewById(R.id.text_cancel).setOnClickListener(btnlistener);
        root.findViewById(R.id.text_sure).setOnClickListener(btnlistener);
        mCameraDialog.setContentView(root);
        //设置初始位置
        loopView.setInitPosition(0);
        loopView.setCurrentPosition(0);
        // 滚动监听
        loopView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                resourcekind = list.get(index);
            }
        });

        // 设置原始数据
        loopView.setItems(list);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        mCameraDialog.setCanceledOnTouchOutside(true);
        dialogWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = -20; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度

        root.measure(0, 0);
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }

    private View.OnClickListener btnlistener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int i = v.getId();
            if (i == R.id.text_sure) {
                textresourcekind.setText(resourcekind);
                json2 = HttpConnect.getDeadLineProductionCounts(DeadLineProductionCountsActivity.this, textresourcekind.getText().toString());
                getData(json2);
                mCameraDialog.dismiss();
            } else if (i == R.id.text_cancel) {
                if (mCameraDialog != null) {
                    mCameraDialog.dismiss();
                }

            }
        }
    };

    private void getData(final JSONObject json) {
        new AsyncTask<String, String, List<StorageCounts>>() {

            @Override
            protected List<StorageCounts> doInBackground(String... strings) {
                List<StorageCounts> products = (List<StorageCounts>) HttpConnect.getDeadLineProductionCounts(json);
                return products;
            }

            @Override
            protected void onPostExecute(List<StorageCounts> preoducts) {
                super.onPostExecute(preoducts);
                data.clear();
                text.clear();
                if (preoducts.get(0).getSeries().get(0).getData()!=null){
                    for (int i =0 ; i < preoducts.get(0).getSeries().get(0).getData().size();i++){
                        data.add(preoducts.get(0).getSeries().get(0).getData().get(i).getValue());
                        text.add(preoducts.get(0).getSeries().get(0).getData().get(i).getName());
                    }
                }
                if (data == null || data.size()==0){
                    linerLbar.setEnabled(false);
                    linerPie.setEnabled(false);
                    pieChartView.setVisibility(View.GONE);
                }else {
                    linerLbar.setEnabled(true);
                    linerPie.setEnabled(true);
                    pieChartView.setVisibility(View.VISIBLE);
                }
                initPieDatas();
                initColumnDatas();
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
        mRatios.clear();
        mArcColors.clear();
        mDescription.clear();
        itemX.clear();
        itemY.clear();
        unstoragecount.setText("0");
        unstorageprecent.setText("(0%)");
        storagecount.setText("0");
        storageprecent.setText("(0%)");
        if (data != null && data.size()!=0){
            mArcColors.add(greenColor);
            mArcColors.add(blueColor);
            int sum = 0;
            for (int i = 0;i<data.size();i++){
                sum = sum + data.get(i);
            }
            for (int i = 0;i<data.size();i++){
                itemX.add(text.get(i).replaceAll("\r\n",""));
                itemY.add(data.get(i));
                mDescription.add(text.get(i).replaceAll("\r\n",""));
                if (Float.valueOf(data.get(i)) == 0 || Float.valueOf(data.get(i)) == 0.0){
                    unstorage = Float.valueOf(0);
                }else {
                    unstorage = Float.valueOf(String.format("%.4f",(float)Float.valueOf(data.get(i))/sum));
                }
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
            Toast.makeText(DeadLineProductionCountsActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
        }
        //点击动画开启
        pieChartView.setCanClickAnimation(true);
        pieChartView.setDatas(mRatios, mArcColors, mDescription);
    }
    private void initColumnDatas() {
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
                values.add(new SubcolumnValue(itemY.get(i), mArcColors.get(i)));
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
            axisX.setTextColor(copyright.hxqh.com.copyright.copright.util.ChartUtils.DARKEN_COLOR);
            axisX.setValues(axisValuesX);
            axisX.setHasTiltedLabels(false);
            axisX.setTextSize(12);// 设置X轴文字大小
            axisX.setHasLines(false); //x 轴分割线
            axisX.setMaxLabelChars(4);
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
        data.setValueLabelTextSize(12);
        data.setFillRatio(0.4F);
        barChart.setColumnChartData(data);
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
}
