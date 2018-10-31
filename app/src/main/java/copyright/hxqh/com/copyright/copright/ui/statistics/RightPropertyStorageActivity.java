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
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.HttpManager.HttpConnect;
import copyright.hxqh.com.copyright.copright.custom.ConstantsChart;
import copyright.hxqh.com.copyright.copright.ui.statistics.entity.ResourceKind;
import copyright.hxqh.com.copyright.copright.ui.statistics.entity.RightPutInStorage;
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

public class RightPropertyStorageActivity  extends FragmentActivity {
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


    String resourcekind;
    String rightkinds;

    private JSONObject json;
    private JSONObject json2;
    private int page = 1;

    ArrayList<Integer> data = new ArrayList<>();
    ArrayList<String> text = new ArrayList<>();

    ArrayList<String> list = new ArrayList<>();
    List<String> mDescription = new ArrayList<>();
    List<Integer> mArcColors = new ArrayList<>();
    Float instorage;
    Float unstorage;
    private int blueColor = Color.rgb(39,71,132);
    private int greenColor = Color.rgb(1,191,157);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propertyput);
        ButterKnife.bind(this);
        initView();

    }
    protected void initView() {
        json = HttpConnect.getBasicJson(this);
        json2 = HttpConnect.getRightPutInStorageJson(this,resourcekind,"pie","rightIsassets",rightkinds);
        titleTextView = (TextView) findViewById(R.id.menu_title);
        titleTextView.setText("权利项转化资产情况");
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
            @SuppressLint("ResourceAsColor")
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
                for (int i = 0;i<preoducts.size();i++){
                    list.add(preoducts.get(i).getResourcekind());
                }
                if (preoducts.size() % 2 == 0){
                    resourcekind = preoducts.get(preoducts.size()/2).getResourcekind();
                }else if (preoducts.size() % 2 == 1){
                    resourcekind = preoducts.get(preoducts.size()/2+1).getResourcekind();
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
                json2 = HttpConnect.getRightPutInStorageJson(RightPropertyStorageActivity.this,resourcekind,"pie","rightIsassets",rightkinds);
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
        new AsyncTask<String, String, RightPutInStorage>() {

            @Override
            protected RightPutInStorage doInBackground(String... strings) {
                RightPutInStorage products =  HttpConnect.getRightPutInStorageCounts(json);
                return products;
            }
            @Override
            protected void onPostExecute(RightPutInStorage preoducts) {
                super.onPostExecute(preoducts);
                data = (ArrayList<Integer>) preoducts.getSeries().get(0).getData();
                text = (ArrayList<String>) preoducts.getXaxis().get(0).getData();
                if (data == null){
                    linerLbar.setEnabled(false);
                    linerPie.setEnabled(false);
                    pieChartView.setVisibility(View.GONE);
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
    private void initColumnDatas() {
        //x轴的数据
        List<String> itemX = new ArrayList<>();
        if (text!=null){
            for (int i = 0;i<text.size();i++){

                itemX.add(text.get(i));

            }
        }

        //定义X轴刻度值的数据集合
        ArrayList<AxisValue> axisValuesX = new ArrayList<AxisValue>();
        for (int j = 0; j < itemX.size(); ++j) {
            axisValuesX.add(new AxisValue(j).setValue(j).setLabel(itemX.get(j)));
        }
        //y轴的数据
        ArrayList<Integer> itemY = new ArrayList<>();
        if (data!=null) {
            for (int i = 0; i < data.size(); i++) {

                itemY.add(data.get(i));

            }
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
            axisX.setTextColor(ChartUtils.COLOR_BLUE);
            axisX.setValues(axisValuesX);
            axisX.setHasTiltedLabels(false);
            axisX.setTextSize(12);// 设置X轴文字大小
            axisX.setHasLines(true); //x 轴分割线
            axisX.setMaxLabelChars(4);
            Axis axisY = new Axis().setHasLines(true);
            if (ConstantsChart.hasAxesNames) {
//                axisX.setName("作品入库情况占比");
                axisY.setName("数量");
            }
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }
        data.setValueLabelTextSize(12);
        data.setFillRatio(0.4F);
        barChart.setColumnChartData(data);
    }
    private void initPieDatas() {
        List<Float> mRatios = new ArrayList<>();
        mDescription.clear();
        mArcColors.clear();
        mRatios.clear();
        unstoragecount.setText("0");
        unstorageprecent.setText("(0%)");
        storagecount.setText("0");
        storageprecent.setText("(0%)");
        if (data != null ) {
            int sum = 0;
            for (int i = 0; i < data.size(); i++) {
                sum = sum + data.get(i);
            }
            text1.setText("资产");
            text2.setText("资源");
            for (int i = 0; i < data.size(); i++) {
                if (text.get(i).contains("资产")) {
                    storagecount.setText(String.valueOf(data.get(i)));
                    instorage = Float.valueOf(String.format("%.2f", (float) data.get(i) / sum));
                    storageprecent.setText("(" + String.valueOf(instorage * 100) + "%)");
                    mDescription.add(text.get(i));
                    mArcColors.add(greenColor);
                    mRatios.add(instorage);
                } else if (text.get(i).contains("资源")) {
                    unstoragecount.setText(String.valueOf(data.get(i)));
                    unstorage = Float.valueOf(String.format("%.2f", (float) data.get(i) / sum));
                    unstorageprecent.setText("(" + String.valueOf(unstorage * 100) + "%)");
                    mDescription.add(text.get(i));
                    mArcColors.add(blueColor);
                    mRatios.add(unstorage);
                }
            }
        }else {
            Toast.makeText(RightPropertyStorageActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
        }

        //点击动画开启
        pieChartView.setCanClickAnimation(true);
        pieChartView.setDatas(mRatios, mArcColors, mDescription);
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