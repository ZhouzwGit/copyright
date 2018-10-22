package copyright.hxqh.com.copyright.copright.adpter;

import android.app.AlertDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.OnTextChanged;
import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.publicService.entity.Consult;

/**
 * Created by lianjh on 2018\10\19 0019.
 * Current page
 */

public class DaibanAdapter  extends BaseQuickAdapter<Consult,BaseViewHolder> {
    Context mcontext;
    final int maxNum = 300;

    public DaibanAdapter(int layoutResId,Context context) {
        super(layoutResId);
        mcontext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Consult item) {
        helper.setText(R.id.daiban_flowtype,item.getManagementabqu());
        helper.setText(R.id.diaban_workname,item.getServiceno());
        helper.setText(R.id.daiban_step,item.getApplyUser());
        helper.setText(R.id.daiban_date,item.getPhone());
        helper.setOnClickListener(R.id.daiban_remark, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_edit,null,false);
                final AlertDialog dialog = new AlertDialog.Builder(mContext).setView(view).create();

                Button cancel = view.findViewById(R.id.btn_no);
                Button agree = view.findViewById(R.id.btn_yes);
                final EditText et_remark =  view.findViewById(R.id.et_remak);
                final TextView textView = view.findViewById(R.id.editor_font_count);
                et_remark.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }
                    @Override
                    public void afterTextChanged(Editable s) {
                        if (maxNum-s.length() == 0){
                            Toast.makeText(mContext, "已达输入上限", Toast.LENGTH_SHORT).show();
                        }
                        textView.setText(s.length()+"/"+maxNum);
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                agree.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                dialog.show();
            }

        });
    }
}
