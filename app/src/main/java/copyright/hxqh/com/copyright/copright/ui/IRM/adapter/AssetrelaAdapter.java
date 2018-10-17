package copyright.hxqh.com.copyright.copright.ui.IRM.adapter;


import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.IRM.enity.Rchrrel;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Channel;

/**
 * Created by zzw on 2018/9/18.
 */

public class AssetrelaAdapter extends BaseQuickAdapter<Rchrrel,BaseViewHolder> {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
    public AssetrelaAdapter(int layoutResId, @Nullable List<Rchrrel> data) {
        super(layoutResId, data);
    }

    public AssetrelaAdapter(@Nullable List<Rchrrel> data) {
        super(data);
    }

    public AssetrelaAdapter(int layoutResId) {
        super(layoutResId);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, Rchrrel item) {
        CardView cardView = helper.getView(R.id.card_container);
        helper.setText(R.id.name_id,item.getCopyright());
       // helper.setText(R.id.channelno_id,item.());
        helper.setText(R.id.field1_id,item.getStartdate());
        //helper.setText(R.id.creditworthiness_id,item.());
        helper.setText(R.id.createdate_id,item.getFinishdate());

    }
}
