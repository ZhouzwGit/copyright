package copyright.hxqh.com.copyright.copright.ui.author.adpter;


import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.author.entity.Royalty;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Product;

/**
 * Created by zzw on 2018/9/18.
 */

public class RoyaltyAdapter extends BaseQuickAdapter<Royalty,BaseViewHolder> {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
    public RoyaltyAdapter(int layoutResId, @Nullable List<Royalty> data) {
        super(layoutResId, data);
    }

    public RoyaltyAdapter(@Nullable List<Royalty> data) {
        super(data);
    }

    public RoyaltyAdapter(int layoutResId) {
        super(layoutResId);
    }
    @Override
    protected void convert(BaseViewHolder helper, Royalty item) {
        CardView cardView = helper.getView(R.id.card_container);
        helper.setText(R.id.name_id,item.getName());
        TextView name = helper.getView(R.id.name_id);
        name.setMovementMethod(ScrollingMovementMethod.getInstance());
        name.setHorizontallyScrolling(true);
        name.setFocusable(true);
        helper.setText(R.id.status_id,item.getStatus());
        helper.setText(R.id.field1_id,item.getField1());
        helper.setText(R.id.createdate_id,item.getCreateDate());
        helper.setText(R.id.contractnum_id,item.getContractnum());

    }
}
