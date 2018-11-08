package copyright.hxqh.com.copyright.copright.ui;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.HttpManager.HttpConnect;
import copyright.hxqh.com.copyright.copright.adpter.DaibanAdapter;
import copyright.hxqh.com.copyright.copright.adpter.SpinnerArrayAdapter;
import copyright.hxqh.com.copyright.copright.entity.Acttype;
import copyright.hxqh.com.copyright.copright.entity.ToDo;
import copyright.hxqh.com.copyright.copright.ui.publicService.LawVinDicateDetailActivity;
import copyright.hxqh.com.copyright.copright.ui.publicService.RoyaltyActivity;
import copyright.hxqh.com.copyright.copright.ui.publicService.adapter.LawvindicateAdapter;
import copyright.hxqh.com.copyright.copright.ui.publicService.entity.Consult;
import copyright.hxqh.com.copyright.copright.ui.statistics.ProductCostingActivity;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;
import copyright.hxqh.com.copyright.copright.util.CumTimePickerDialog;

/**
 * Created by lianjh on 2018\10\18 0018.
 * Current page
 */

public class DaibanFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.menu_title) //标题
    TextView titleTextView;
    @Bind(R.id.back_id)
    ImageView backButton; //返回
    @Bind(R.id.title_search)
    ImageView searchButton; //搜索
    @Bind(R.id.title_relativelayout_id)
    RelativeLayout relativeLayout;
    @Bind(R.id.flow_type)
    TextView flow_type;
    @Bind(R.id.imag_flowtype)
    ImageView imag_flowtype;
    @Bind(R.id.flow_createtime)
    TextView flow_createtime;
    @Bind(R.id.imag_createtime)
    ImageView imag_createtime;
    LinearLayoutManager layoutManager;

    @Bind(R.id.recyclerView_id)//RecyclerView
            RecyclerView recyclerView;
    @Bind(R.id.have_not_data_id)
    LinearLayout nodatalayout; //暂无数据
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout refresh_layout;//界面刷新

    TextView startdate;
    TextView enddate;
    Spinner spinner;

    private int page = 1;
    private int pageNum;

    private JSONObject json;

    private JSONObject json2;

    DaibanAdapter daibanAdapter;

    Dialog mCameraDialog;
    private ArrayList<String> list_year = new ArrayList<String>();
    private ArrayList<String> list_mooth = new ArrayList<String>();
    private ArrayList<String> list_day = new ArrayList<String>();
    LoopView loopView_year;
    LoopView loopView_month;
    LoopView loopView_day;
    //被选中或默认显示的时间
    Calendar date = Calendar.getInstance();
    private int year = date.get(Calendar.YEAR);
    private int mooth = date.get(Calendar.MONTH)+1;
    private int day = date.get(Calendar.DATE);
    private int oldDayCounts = 31;
    private ArrayList<String> mStringArray = new ArrayList<>();
    private ArrayList<String> ValueArray= new ArrayList<>();

    String actvalue;

    Boolean flag = false;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.daiban_list, container,
                false);
        ButterKnife.bind(getActivity());
        findByIdView(view);
        initView();
        return view;
    }

    private void findByIdView(View view) {
        relativeLayout  = (RelativeLayout) view.findViewById(R.id.title_relativelayout_id);
        titleTextView = (TextView) view.findViewById(R.id.menu_title);
        backButton = (ImageView) view.findViewById(R.id.back_id);
        searchButton = (ImageView) view.findViewById(R.id.title_search);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_id);
        nodatalayout = (LinearLayout) view.findViewById(R.id.have_not_data_id);
        refresh_layout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        flow_type = (TextView) view.findViewById(R.id.flow_type);
        imag_flowtype = (ImageView) view.findViewById(R.id.imag_flowtype);
        flow_createtime = (TextView) view.findViewById(R.id.flow_createtime);
        imag_createtime = (ImageView) view.findViewById(R.id.imag_createtime);
    }

    protected void initView() {
        relativeLayout.setBackgroundResource(R.drawable.background_gradient);
        titleTextView.setText("待办列表");
        imag_createtime.setOnClickListener(createtimeOnClickListener);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        refresh_layout.setOnRefreshListener(this);
        backButton.setVisibility(View.GONE);
        searchButton.setVisibility(View.GONE);
        json = HttpConnect.getBasicJson(getActivity());
        initAdpter();
        AcountUtil.showProgressDialog(getActivity(), "");
        initDataList();
        getData();
        getFlowType();
    }
    private View.OnClickListener createtimeOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showDialog();
        }
    };
    private void showDialog() {
        mCameraDialog = new Dialog(getActivity(), R.style.my_dialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(getActivity()).inflate(
                R.layout.time_dialog, null);
        loopView_year = (LoopView) root.findViewById(R.id.loopView_year);
        loopView_month = (LoopView) root.findViewById(R.id.loopView_month);
        loopView_day = (LoopView) root.findViewById(R.id.loopView_day);
        root.findViewById(R.id.text_cancel).setOnClickListener(btnlistener);
        root.findViewById(R.id.text_sure).setOnClickListener(btnlistener);
        root.findViewById(R.id.text_reset).setOnClickListener(btnlistener);
        mCameraDialog.setContentView(root);
        //滚动监听,年份
        loopView_year.setListener(new OnItemSelectedListener() {
            public void onItemSelected(int index) {
                year = Integer.parseInt(list_year.get(index));
                setRightDayCount();
            }
        });

        //滚动监听,月份
        loopView_month.setListener(new OnItemSelectedListener() {
            public void onItemSelected(int index) {
                mooth = Integer.parseInt(list_mooth.get(index));
                setRightDayCount();
            }
        });

        //滚动监听,天数
        loopView_day.setListener(new OnItemSelectedListener() {
            public void onItemSelected(int index) {
                day = Integer.parseInt(list_day.get(index));
            }
        });
        loopView_year.setItems(list_year);
        loopView_month.setItems(list_mooth);
        loopView_day.setItems(list_day);
        //默认时间,当前年月日
        for (int i = 0; i < list_year.size(); i++) {
            if (Integer.parseInt(list_year.get(i)) == year) {
                loopView_year.setCurrentPosition(i);
            }
        }
        loopView_month.setCurrentPosition(mooth-1);
        loopView_day.setCurrentPosition(day - 1);

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
    /**
     * 初始化日期的几个集合数据
     */
    private void initDataList() {
        list_year.clear();
        list_mooth.clear();
        list_day.clear();
        //年的时间
        for (int i = 2000; i < 2500; i++) {
            list_year.add("" + i);
        }

        //月的时间
        for (int i = 1; i < 13; i++) {
            list_mooth.add("" + i);
        }

        //日的时间
        for (int i = 1; i < 32; i++) {
            list_day.add("" + i);
        }
    }
    /**
     * 根据年数的月份显示对应的天数
     */
    private void setRightDayCount() {

        int dayCounts = 31;
        //28天的情况，润年2月
        if (leapYear(year) && mooth == 2) {
            dayCounts = 28;
        }
        //29天的情况，平年2月
        else if (!leapYear(year) && mooth == 2) {
            dayCounts = 29;
        }

        //30天的情况，2，4，6，9，11
        else if (mooth == 2 || mooth == 4 || mooth == 6 || mooth == 9 || mooth == 11) {
            dayCounts = 30;
        }

        //31天的情况 ，1，3，5，7，8，10，12
        else {
            dayCounts = 31;
        }

        list_day.clear();
        //日的时间
        for (int i = 1; i <= dayCounts; i++) {
            list_day.add("" + i);
        }

        if (oldDayCounts != dayCounts) {    //如果新老的天数不一样才变换天数
            //重新改变天的数量
            loopView_day.setItems(list_day);

            //判断是否要变更选中的天数，比如选中3-31滑动到2月变成2-28或2-29
            if (dayCounts < day) {
                loopView_day.setCurrentPosition(dayCounts - 1);
            }
            oldDayCounts = dayCounts;
        }


    }

    /**
     * 判断是闰年leapYear还是平年
     */
    private boolean leapYear(int year) {
        if (year % 400 == 0) {
            return true;
        }

        if (year % 100 != 0 && year % 4 == 0) {
            return true;
        }

        return false;
    }

    private View.OnClickListener btnlistener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int i = v.getId();
            if (i == R.id.text_sure) {

                flow_createtime.setText(year + "-" + mooth + "-" + day);
//                json2 = HttpConnect.getProductCostJson(getActivity(),flow_createtime.getText().toString(),textEndtime.getText().toString(),"bar");
                getData();
                mCameraDialog.dismiss();
            } else if (i == R.id.text_cancel) {
                if (mCameraDialog != null) {
                    mCameraDialog.dismiss();
                }

            }else if (i == R.id.text_reset) {
                for (int j = 0; j < list_year.size(); j++) {
                    if (Integer.parseInt(list_year.get(j)) == date.get(Calendar.YEAR)) {
                        loopView_year.setCurrentPosition(j);
                    }
                }
                loopView_month.setCurrentPosition(date.get(Calendar.MONTH));
                loopView_day.setCurrentPosition(date.get(Calendar.DATE) - 1);
                year = date.get(Calendar.YEAR);
                mooth = date.get(Calendar.MONTH);
                day = date.get(Calendar.DATE);
            }
        }
    };
    @Override
    public void onRefresh() {
        refresh_layout.setRefreshing(true);
        json = HttpConnect.getBasicJson(getActivity());
        page = 1;
        getData();
    }

    private void getData() {
        new AsyncTask<String, String, List<ToDo>>() {

            @Override
            protected List<ToDo> doInBackground(String... strings) {
                List<ToDo> products;
                if (flag == false){
                    products = (List<ToDo>) HttpConnect.getToDolist(json, page);
                }else {
                   products = (List<ToDo>) HttpConnect.getToDolist(json2, page);
                }

                return products;
            }

            @Override
            protected void onPostExecute(List<ToDo> preoducts) {
                super.onPostExecute(preoducts);
                if (preoducts == null) {
                    daibanAdapter.loadMoreFail();
                    if (daibanAdapter.getData().isEmpty()) {
                        AcountUtil.closeProgressDialog();
                        nodatalayout.setVisibility(View.VISIBLE);
                        TextView textView = (TextView) nodatalayout.getChildAt(0);
                        textView.setText("加载失败请检查网络");
                    }
                    return;
                }
                if (preoducts.isEmpty()) {
                    if (daibanAdapter.getData().isEmpty()) {
                        nodatalayout.setVisibility(View.VISIBLE);
                        TextView textView = (TextView) nodatalayout.getChildAt(0);
                        textView.setText("暂无数据");
                        daibanAdapter.replaceData(new ArrayList<ToDo>());
                    } else {
                        daibanAdapter.loadMoreEnd();
                    }
                } else {
                    pageNum = preoducts.get(0).getPagenum();
                    nodatalayout.setVisibility(View.GONE);
                    if (daibanAdapter.getData().isEmpty()) {
                        daibanAdapter.addData(preoducts);
                    } else {
                        if (page == 1) {
                            daibanAdapter.setNewData(preoducts);
                        } else if (page <= pageNum){
                            daibanAdapter.addData(preoducts);
                            daibanAdapter.loadMoreComplete();
                        }else {
                            daibanAdapter.loadMoreEnd();
                        }
                    }
                }
                daibanAdapter.setEnableLoadMore(true);
                refresh_layout.setRefreshing(false);
                AcountUtil.closeProgressDialog();
            }

            @Override
            protected void finalize() throws Throwable {
                super.finalize();
            }
        }.execute();
    }
    private void getFlowType() {
        new AsyncTask<String, String, List<Acttype>>() {

            @Override
            protected List<Acttype> doInBackground(String... strings) {
                List<Acttype> products = (List<Acttype>) HttpConnect.getFlowType(json, page);
                return products;
            }
            @Override
            protected void onPostExecute(List<Acttype> preoducts) {
                for (int i = 0;i<preoducts.size();i++){
                    mStringArray.add(preoducts.get(i).getActlabel());
                    ValueArray.add(preoducts.get(i).getActvalue());
                }

            }
            @Override
            protected void finalize() throws Throwable {
                super.finalize();
            }
        }.execute();
    }

    private void initAdpter() {
        daibanAdapter = new DaibanAdapter(R.layout.activity_daiban_item,getActivity());
        recyclerView.setAdapter(daibanAdapter);
        daibanAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                getData();
            }
        }, recyclerView);
    }
}
