package copyright.hxqh.com.copyright.copright.ui;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.HttpManager.HttpConnect;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;

public class ModifyPwdActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText oldpwd,newpwd,confirmpwd;
    private TextView title;
    private ImageView backimage,searchImage;
    private Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pwd);
        findViewById();
        initView();
    }
    public void findViewById(){
        oldpwd = findViewById(R.id.oldpwd);
        newpwd = findViewById(R.id.newpwd);
        confirmpwd = findViewById(R.id.confirmpwd);
        ok = findViewById(R.id.ok);
        backimage = findViewById(R.id.back_id);
        searchImage = findViewById(R.id.title_search);
        title = findViewById(R.id.menu_title);
    }
    public void initView(){
        title.setText("修改密码");
        searchImage.setVisibility(View.GONE);
        backimage.setOnClickListener(this);
        oldpwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(oldpwd.getText()) || TextUtils.isEmpty(newpwd.getText()) || TextUtils.isEmpty(confirmpwd.getText())) {
                    ok.setEnabled(Boolean.FALSE);
                    ok.setBackgroundResource(R.color.gray4);
                } else {
                    ok.setEnabled(Boolean.TRUE);
                    ok.setBackgroundResource(R.color.blue);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        newpwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(oldpwd.getText()) || TextUtils.isEmpty(newpwd.getText()) || TextUtils.isEmpty(confirmpwd.getText())) {
                    ok.setEnabled(Boolean.FALSE);
                    ok.setBackgroundResource(R.color.gray4);
                } else {
                    ok.setEnabled(Boolean.TRUE);
                    ok.setBackgroundResource(R.color.blue);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        confirmpwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(oldpwd.getText()) || TextUtils.isEmpty(newpwd.getText()) || TextUtils.isEmpty(confirmpwd.getText())) {
                    ok.setEnabled(Boolean.FALSE);
                    ok.setBackgroundResource(R.color.gray4);
                } else {
                    ok.setEnabled(Boolean.TRUE);
                    ok.setBackgroundResource(R.color.blue);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ok.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_id:
                finish();
                break;
            case R.id.ok:
                if (testPwd()){
                    AcountUtil.showProgressDialog(this,"正在修改");
                    modifypwd();
                }
                break;
        }
    }
    private boolean testPwd(){
        if (!oldpwd(oldpwd.getText().toString())){
            message(1);
            return false;
        }
        if (!isPasswordValid(newpwd.getText().toString())){
            message(3);
            return false;
        }
        if (!newpwdright(newpwd.getText().toString(),confirmpwd.getText().toString())){
            message(2);
            return false;
        }
        return true;
    }
    private void message(int i){
        switch (i){
            case 1:
                Toast.makeText(this,"您输入的旧密码不正确",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this,"您输入的新秘密和确认密码不一致",Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this,"密码长度至少为6",Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private boolean oldpwd(String oldpwd){
        return oldpwd.equals(AcountUtil.getPassword(this));
    }
    private boolean isPasswordValid(String password) {
        return password.length() > 5;
    }
    private boolean newpwdright(String newpwd,String okpwd){
        return newpwd.equals(okpwd);
    }
    public void modifypwd(){
        new AsyncTask<Void, Void, Boolean>(){

            @Override
            protected Boolean doInBackground(Void... voids) {
                boolean flag = HttpConnect.modifyPwd(AcountUtil.getUsername(ModifyPwdActivity.this),newpwd.getText().toString());
                return flag;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                if (aBoolean){
                    AcountUtil.closeProgressDialog();
                    Toast.makeText(ModifyPwdActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(ModifyPwdActivity.this,"修改失败",Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }
}
