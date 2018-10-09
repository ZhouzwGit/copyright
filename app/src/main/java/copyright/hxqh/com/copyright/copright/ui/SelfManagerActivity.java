package copyright.hxqh.com.copyright.copright.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.entity.UserInfo;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;

public class SelfManagerActivity extends Fragment implements View.OnClickListener{
    private UserInfo userInfo;
    private TextView username,rolename,loadout;
    private RelativeLayout userinfolayout;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userInfo = AcountUtil.getUser(getActivity(),"UserInfo","user");
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myinfo_layout, container,
                false);
        findViewById(view);
        initView();
        return view;
    }
    private void initView() {
        username.setText(userInfo.getName());
        rolename.setText(userInfo.getRolenames());
        userinfolayout.setOnClickListener(this);
        loadout.setOnClickListener(this);
    }

    private void findViewById(View view) {
        username = view.findViewById(R.id.username_id);
        rolename = view.findViewById(R.id.rolenames_id);
        userinfolayout = view.findViewById(R.id.userinfo_layout);
        loadout = view.findViewById(R.id.loadout_id);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.userinfo_layout:
                Intent intent = new Intent(getActivity(),UserinfoActivity.class);
                startActivity(intent);
                break;
            case R.id.loadout_id:
                final NormalDialog normalDialog = new NormalDialog(getActivity());
                normalDialog.content("是否确定退出程序？");
                normalDialog.show();
                normalDialog.setOnBtnClickL(new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        normalDialog.dismiss();
                    }
                }, new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        Intent intent1 = new Intent(getActivity(), Login_Activity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent1);
                    }
                });
                break;
        }

    }
}
