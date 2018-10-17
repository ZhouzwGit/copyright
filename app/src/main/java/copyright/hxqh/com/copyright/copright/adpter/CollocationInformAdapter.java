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
import copyright.hxqh.com.copyright.copright.entity.Collectinform;
import copyright.hxqh.com.copyright.copright.ui.author.entity.Author;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;

/**
 * Created by zzw on 2018/9/18.
 */

public class CollocationInformAdapter extends BaseQuickAdapter<Collectinform,BaseViewHolder> {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
    public CollocationInformAdapter(int layoutResId, @Nullable List<Collectinform> data) {
        super(layoutResId, data);
    }

    public CollocationInformAdapter(@Nullable List<Collectinform> data) {
        super(data);
    }

    public CollocationInformAdapter(int layoutResId) {
        super(layoutResId);
    }
    @Override
    protected void convert(BaseViewHolder helper, Collectinform item) {
        CardView cardView = helper.getView(R.id.card_container);
        helper.setText(R.id.resourcename_id,item.getResourcename());
        helper.setText(R.id.collectioncount_id,item.getCollectioncount());
        helper.setText(R.id.channelname_id,item.getChannelname());
        helper.setText(R.id.collectiondate_id,item.getCollectiondate());
    }
}
