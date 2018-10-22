package copyright.hxqh.com.copyright.copright.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.entity.UserInfo;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;
import copyright.hxqh.com.copyright.copright.view.MyCircleImage;
import copyright.hxqh.com.copyright.copright.view.MyImageView;

public class SelfManagerActivity extends Fragment implements View.OnClickListener{
    private UserInfo userInfo;
    private TextView username,rolename,loadout,remark,email;
    private RelativeLayout userinfolayout,modifypwd;
    private MyCircleImage myImageView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userInfo = AcountUtil.getUser(getActivity(),"UserInfo","user");
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myinfo_layout2, container,
                false);
        findViewById(view);
        initView();
        return view;
    }
    private void initView() {
        username.setText(userInfo.getName());
        rolename.setText(userInfo.getRolenames());
        remark.setText(userInfo.getRemarks());
        email.setText(userInfo.getEmail());
     /*   rolename.setMovementMethod(ScrollingMovementMethod.getInstance());
        rolename.setHorizontallyScrolling(true);
        rolename.setFocusable(true);*/
        userinfolayout.setOnClickListener(this);
        loadout.setOnClickListener(this);
        modifypwd.setOnClickListener(this);
        String url = userInfo.getPhoto();
        if ("".equals(url)){
            myImageView.setImageResource(R.drawable.defaultuserpg);
        }else {
            AcountUtil.imageShow(myImageView,url,120);
        }

        myImageView.setDrawingCacheEnabled(true);
    }

    private void findViewById(View view) {
        username = view.findViewById(R.id.username_id);
        rolename = view.findViewById(R.id.rolenames_id);
        userinfolayout = view.findViewById(R.id.userinfo_layout);
        loadout = view.findViewById(R.id.loadout_id);
        myImageView = view.findViewById(R.id.headportrait_id);
        modifypwd = view.findViewById(R.id.modifypwd_id);
        remark = view.findViewById(R.id.remark_id);
        email = view.findViewById(R.id.email_id);
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
            case R.id.modifypwd_id:
                Intent intent1 = new Intent(getActivity(),ModifyPwdActivity.class);
                getActivity().startActivity(intent1);
                break;
        }

    }
}
