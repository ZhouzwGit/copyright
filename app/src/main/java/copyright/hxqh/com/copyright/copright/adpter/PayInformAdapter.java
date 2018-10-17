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
import copyright.hxqh.com.copyright.copright.entity.Payinform;
import copyright.hxqh.com.copyright.copright.ui.author.entity.Author;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;

/**
 * Created by zzw on 2018/9/18.
 */

public class PayInformAdapter extends BaseQuickAdapter<Payinform,BaseViewHolder> {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
    public PayInformAdapter(int layoutResId, @Nullable List<Payinform> data) {
        super(layoutResId, data);
    }

    public PayInformAdapter(@Nullable List<Payinform> data) {
        super(data);
    }

    public PayInformAdapter(int layoutResId) {
        super(layoutResId);
    }
    @Override
    protected void convert(BaseViewHolder helper, Payinform item) {
        CardView cardView = helper.getView(R.id.card_container);
        helper.setText(R.id.resourcename_id,item.getResourcename());
        helper.setText(R.id.payamount_id,item.getPayamount());
        helper.setText(R.id.name_id,item.getName());
        helper.setText(R.id.paydate_id,item.getPaydate());

    }
}
