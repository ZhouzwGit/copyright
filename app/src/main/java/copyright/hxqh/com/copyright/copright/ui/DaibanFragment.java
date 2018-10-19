package copyright.hxqh.com.copyright.copright.ui;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import butterknife.Bind;
import butterknife.ButterKnife;
import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.HttpManager.HttpConnect;
import copyright.hxqh.com.copyright.copright.ui.publicService.LawVinDicateDetailActivity;
import copyright.hxqh.com.copyright.copright.ui.publicService.RoyaltyActivity;
import copyright.hxqh.com.copyright.copright.ui.publicService.adapter.LawvindicateAdapter;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;

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

    private int page = 1;
    private int pageNum;

    private JSONObject json;


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
//        AcountUtil.showProgressDialog(getActivity(), "");
        getData();
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.high_opinion_dialog_layout,null,false);
                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).setView(view).create();

                Button btn_cancel_high_opion = view.findViewById(R.id.btn_cancel_high_opion);
                Button btn_agree_high_opion = view.findViewById(R.id.btn_agree_high_opion);

                btn_cancel_high_opion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btn_agree_high_opion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //... To-do
                        dialog.dismiss();
                    }
                });

                dialog.show();
                //此处设置位置窗体大小，我这里设置为了手机屏幕宽度的3/4
//                dialog.getWindow().setLayout((ScreenUtils.getScreenWidth(this)/4*3),LinearLayout.LayoutParams.WRAP_CONTENT);

            }
        });
    }

    @Override
    public void onRefresh() {
        refresh_layout.setRefreshing(true);
        json = HttpConnect.getBasicJson(getActivity());
        page = 1;
        getData();
    }

    private void getData() {
    }

    private void initAdpter() {
//        lawvindicateAdapter = new LawvindicateAdapter(R.layout.activity_daiban_item);
//        recyclerView.setAdapter(lawvindicateAdapter);
//        lawvindicateAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                page++;
//                getData();
//            }
//        }, recyclerView);
    }
}
