package copyright.hxqh.com.copyright.copright.adpter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.json.JSONObject;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.HttpManager.HttpConnect;
import copyright.hxqh.com.copyright.copright.entity.ToDo;
import copyright.hxqh.com.copyright.copright.ui.publicService.PublicServiceActivity;

/**
 * Created by lianjh on 2018\10\19 0019.
 * Current page
 */

public class DaibanAdapter extends BaseQuickAdapter<ToDo,BaseViewHolder> {
    Context mcontext;
    final int maxNum = 300;
    String flag;
    private JSONObject json;
    Boolean remain = false;

    public DaibanAdapter(int layoutResId,Context context) {
        super(layoutResId);
        mcontext = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final ToDo item) {
        helper.setText(R.id.daiban_flowtype,item.getTask().get(0).getCategory());
        helper.setText(R.id.diaban_workname,item.getTask().get(0).getDescription());
        helper.setText(R.id.daiban_step,item.getTask().get(0).getName());
        helper.setText(R.id.daiban_date,item.getTask().get(0).getCreateTime());
        if (item.getRemaindflag().equals("1")){
            helper.setImageResource(R.id.daiban_remark,R.drawable.icon_concern);
        }else {
            helper.setImageResource(R.id.daiban_remark,R.drawable.icon_not_concern);
        }
        helper.setOnClickListener(R.id.daiban_remark, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (remain == false){
                    helper.setImageResource(R.id.daiban_remark,R.drawable.icon_concern);
                    json = HttpConnect.SaveJson(mcontext,item.getBusinessTable(),item.getBusinessId(),item.getProcInsId(),"1");
                    remain = true;
                }else {
                    helper.setImageResource(R.id.daiban_remark,R.drawable.icon_not_concern);
                    json = HttpConnect.SaveJson(mcontext,item.getBusinessTable(),item.getBusinessId(),item.getProcInsId(),"0");
                    remain = false;
                }
                save();
//                View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_edit,null,false);
//                final AlertDialog dialog = new AlertDialog.Builder(mContext).setView(view).create();
//
//                Button cancel = view.findViewById(R.id.btn_no);
//                Button agree = view.findViewById(R.id.btn_yes);
//                final EditText et_remark =  view.findViewById(R.id.et_remak);
//                final TextView textView = view.findViewById(R.id.editor_font_count);
//                et_remark.addTextChangedListener(new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                    }
//                    @Override
//                    public void onTextChanged(CharSequence s, int start, int before, int count) {
//                    }
//                    @Override
//                    public void afterTextChanged(Editable s) {
//                        if (maxNum-s.length() == 0){
//                            Toast.makeText(mContext, "已达输入上限", Toast.LENGTH_SHORT).show();
//                        }
//                        textView.setText(s.length()+"/"+maxNum);
//                    }
//                });
//                cancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });
//                agree.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
//                dialog.show();
            }

        });
    }
    private void save() {
        new AsyncTask<String, String, String>() {

            @Override
            protected String doInBackground(String... strings) {
                String products = HttpConnect.save(json);
                return products;
            }
            @Override
            protected void onPostExecute(String preoducts) {
                Toast.makeText(mContext, preoducts, Toast.LENGTH_SHORT).show();
            }
            @Override
            protected void finalize() throws Throwable {
                super.finalize();
            }
        }.execute();
    }
}
