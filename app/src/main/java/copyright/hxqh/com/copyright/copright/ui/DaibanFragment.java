package copyright.hxqh.com.copyright.copright.ui;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    /**
     * 时间控件
     */
    private DatePickerDialog datePickerDialog;

    private int layoutnum;
    StringBuffer sb;

    private ArrayAdapter<String> mAdapter ;
    private ArrayList<String> mStringArray = new ArrayList<>();
    private ArrayList<String> ValueArray= new ArrayList<>();

    String actvalue;

    Boolean flag = false;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list, container,
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
    }

    protected void initView() {
        relativeLayout.setBackgroundResource(R.drawable.background_gradient);
        titleTextView.setText("待办列表");

        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        refresh_layout.setOnRefreshListener(this);

        backButton.setVisibility(View.GONE);
        searchButton.setImageResource(R.drawable.ic_handlight);
        json = HttpConnect.getBasicJson(getActivity());
        initAdpter();
        AcountUtil.showProgressDialog(getActivity(), "");
        getData();
        getFlowType();
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.high_opinion_dialog_layout,null,false);
                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).setView(view).create();

                Button btn_cancel_high_opion = view.findViewById(R.id.btn_cancel_high_opion);
                Button btn_agree_high_opion = view.findViewById(R.id.btn_agree_high_opion);
                startdate = view.findViewById(R.id.startdate_id);
                enddate = view.findViewById(R.id.enddate_id);
                spinner = view.findViewById(R.id.spinner2);
                //使用自定义的ArrayAdapter
                mAdapter = new SpinnerArrayAdapter(getActivity(),mStringArray);
                spinner.setAdapter(mAdapter);
                //监听Item选中事件
                spinner.setOnItemSelectedListener(new ItemSelectedListenerImpl());

                btn_cancel_high_opion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startdate.setText("");
                        enddate.setText("");
                        startdate.setHint("请选择时间");
                        enddate.setHint("请选择时间");
                        spinner.setSelection(0);
                    }
                });

                btn_agree_high_opion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        flag = true;
                        json2 = HttpConnect.getFlowTypeJson(getActivity(),actvalue ,startdate.getText().toString(),enddate.getText().toString());
                        getData();
                        dialog.dismiss();
                    }
                });
                setDataListener();
                startdate.setOnClickListener(new MydateListener());
                enddate.setOnClickListener(new MydateListener());

                dialog.show();

            }
        });
    }
    /**
     * 下拉选中的条目*
     */
    private class ItemSelectedListenerImpl implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position,long arg3) {
            String flowType = mStringArray.get(position);
            actvalue = ValueArray.get(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {}

    }

    /**
     * 设置时间选择器*
     */
    private void setDataListener() {
        final Calendar objTime = Calendar.getInstance();
        int iYear = objTime.get(Calendar.YEAR);
        int iMonth = objTime.get(Calendar.MONTH);
        int iDay = objTime.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(getActivity(), new datelistener(), iYear, iMonth, iDay);
    }
    private class MydateListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            layoutnum = 0;
            sb = new StringBuffer();
            layoutnum = view.getId();
            datePickerDialog.show();
        }
    }

    private class datelistener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            sb = new StringBuffer();
            monthOfYear = monthOfYear + 1;
            if (dayOfMonth < 10) {
                sb.append(year + "-" + monthOfYear + "-" + "0" + dayOfMonth);
            } else {
                sb.append(year + "-" + monthOfYear + "-" + dayOfMonth);
            }

            if (layoutnum == startdate.getId()) {
                startdate.setText(sb);
            }else if (layoutnum == enddate.getId()){
                enddate.setText(sb);
            }
        }
    }
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
