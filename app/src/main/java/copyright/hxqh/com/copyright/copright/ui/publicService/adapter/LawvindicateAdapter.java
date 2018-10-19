package copyright.hxqh.com.copyright.copright.ui.publicService.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.author.entity.Royalty;
import copyright.hxqh.com.copyright.copright.ui.publicService.entity.RoyaltyEnity;

/**
 * Created by lianjh on 2018\10\17 0017.
 * Current page
 */

public class LawvindicateAdapter extends BaseQuickAdapter<RoyaltyEnity,BaseViewHolder> {

    public LawvindicateAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, RoyaltyEnity item) {
        helper.setText(R.id.lawvindicate_name,item.getResourcesname());
        helper.setText(R.id.lawvindicate_num,item.getLawvindicateno());
        helper.setText(R.id.lawvindicate_author,item.getLinkman());
        helper.setText(R.id.lawvindicate_phone,item.getPhone());
        helper.setText(R.id.lawvindicate_date,item.getCreateDate());
        helper.setText(R.id.lawvindicate_status,item.getStatus());
    }

}
