package copyright.hxqh.com.copyright.copright.ui.product.adapter;


import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Channel;

/**
 * Created by zzw on 2018/9/18.
 */

public class ChannelAdapter extends BaseQuickAdapter<Channel,BaseViewHolder> {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
    public ChannelAdapter(int layoutResId, @Nullable List<Channel> data) {
        super(layoutResId, data);
    }

    public ChannelAdapter(@Nullable List<Channel> data) {
        super(data);
    }

    public ChannelAdapter(int layoutResId) {
        super(layoutResId);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, Channel item) {
        CardView cardView = helper.getView(R.id.card_container);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) cardView.getLayoutParams();
        int i = (int) mContext.getResources().getDisplayMetrics().density;
        if (helper.getLayoutPosition()==0){
            layoutParams.setMargins(0,10*i,0,10*i);
            cardView.setLayoutParams(layoutParams);
        }else {
            layoutParams.setMargins(0,0,0,10*i);
            cardView.setLayoutParams(layoutParams);
        }
        helper.setText(R.id.channelname_id,item.getChannelname());
        TextView name = helper.getView(R.id.channelname_id);
        name.setMovementMethod(ScrollingMovementMethod.getInstance());
        name.setHorizontallyScrolling(true);
        name.setFocusable(true);
        helper.setText(R.id.channelno_id,item.getChannelnum());
        helper.setText(R.id.channelType_id,item.getChanneltype());
        helper.setText(R.id.creditworthiness_id,item.getCredibility());
        helper.setText(R.id.createdate_id,item.getCreateDate());

    }
}
