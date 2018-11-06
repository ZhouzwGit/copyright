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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.HttpManager.HttpConnect;
import copyright.hxqh.com.copyright.copright.custom.ConstantsChart;
import copyright.hxqh.com.copyright.copright.ui.statistics.entity.MaxConversion;
import copyright.hxqh.com.copyright.copright.ui.statistics.entity.ResourceKind;
import copyright.hxqh.com.copyright.copright.ui.statistics.entity.StorageCounts;
import copyright.hxqh.com.copyright.copright.ui.statistics.fragment.StorageFragment;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;
import copyright.hxqh.com.copyright.copright.util.ChartUtils;
import copyright.hxqh.com.copyright.copright.view.LazyViewPager;
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
 * Created by lianjh on 2018\10\29 0029.
 * Current page
 */

public class MaxConversionAuthorActivity extends FragmentActivity {
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
    @Bind(R.id.frameNewBase)
    ColumnChartView barChart;

    private JSONObject json;
    private JSONObject json2;
    private int page = 1;
    ArrayList<String> list = new ArrayList<>();
    String resourcekind;
    ArrayList<Integer> data = new ArrayList<>();
    ArrayList<String> text = new ArrayList<>();

    Dialog mCameraDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maxconversionauthor);
        ButterKnife.bind(this);
        initView();
    }
    protected void initView() {
        barChart.setOnValueTouchListener(new ValueTouchListener());
        barChart.setValueSelectionEnabled(true);
        json = HttpConnect.getBasicJson(this);
        json2 = HttpConnect.getDeadLineProductionCounts(this, resourcekind);
        titleTextView.setText("高转化率作者Top10");
        searchButton.setVisibility(View.GONE);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recoursetype.setOnClickListener(recoursetypeOnClickListener);
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
                json2 = HttpConnect.getDeadLineProductionCounts(MaxConversionAuthorActivity.this, textresourcekind.getText().toString());
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
        new AsyncTask<String, String, MaxConversion>() {

            @Override
            protected MaxConversion doInBackground(String... strings) {
                MaxConversion products =  HttpConnect.getMaxConversionAuthor(json);
                return products;
            }

            @Override
            protected void onPostExecute(MaxConversion preoducts) {
                super.onPostExecute(preoducts);
                data = (ArrayList<Integer>) preoducts.getSeries().get(0).getData();
                text = (ArrayList<String>) preoducts.getXaxis().get(0).getData();
                AcountUtil.closeProgressDialog();
                initColumnDatas();
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
        if (text != null){
            for(int i = 0 ; i < text.size(); i++){
//                String num = String.valueOf(i+1);
                itemX.add(text.get(i).replaceAll("\n",""));
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
//            Collections.sort(data);
//            for (int i = 1; i <= data.size(); i++) {
//                itemY.add(data.get(i));
//            }
            itemY = data;
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

        //设置行为属性，支持缩放、滑动以及平移
        barChart.setInteractive(true);
        barChart.setZoomType(ZoomType.HORIZONTAL);
//        barChart.setMaxZoom((float) 4);//最大方法比例
        barChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        barChart.setColumnChartData(data);

        Viewport v = new Viewport(barChart.getMaximumViewport());
        v.left = 0;
        v.right = 3;
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
}
