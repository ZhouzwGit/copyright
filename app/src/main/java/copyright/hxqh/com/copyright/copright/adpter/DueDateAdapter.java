package copyright.hxqh.com.copyright.copright.adpter;


import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.entity.Expiretip;
import copyright.hxqh.com.copyright.copright.entity.Payinform;
import copyright.hxqh.com.copyright.copright.ui.author.entity.Author;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;

/**
 * Created by zzw on 2018/9/18.
 */

public class DueDateAdapter extends BaseQuickAdapter<Expiretip,BaseViewHolder> {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
    public DueDateAdapter(int layoutResId, @Nullable List<Expiretip> data) {
        super(layoutResId, data);
    }

    public DueDateAdapter(@Nullable List<Expiretip> data) {
        super(data);
    }

    public DueDateAdapter(int layoutResId) {
        super(layoutResId);
    }
    @Override
    protected void convert(BaseViewHolder helper, Expiretip item) {
        helper.setText(R.id.resourcename_id,item.getResourcename());
        helper.setText(R.id.copyright_id,item.getCopyright());
        helper.setText(R.id.rightno_id,item.getRightno());
        helper.setText(R.id.finishdate_id,item.getFinishdate());
        helper.setText(R.id.gainway_id,item.getGainway());
    }
}
