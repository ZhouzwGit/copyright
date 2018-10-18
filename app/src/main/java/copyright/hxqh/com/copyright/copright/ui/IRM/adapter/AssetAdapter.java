package copyright.hxqh.com.copyright.copright.ui.IRM.adapter;


import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.widget.RatingBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.IRM.enity.Asset;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;
import copyright.hxqh.com.copyright.copright.view.MyImageView;

/**
 * Created by zzw on 2018/9/18.
 */

public class AssetAdapter extends BaseQuickAdapter<Asset,BaseViewHolder> {
    public AssetAdapter(int layoutResId, @Nullable List<Asset> data) {
        super(layoutResId, data);
    }

    public AssetAdapter(@Nullable List<Asset> data) {
        super(data);
    }

    public AssetAdapter(int layoutResId) {
        super(layoutResId);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, Asset item) {
        CardView cardView = helper.getView(R.id.card_container);
        MyImageView myImageView = helper.getView(R.id.bookimage_id);
        AcountUtil.imageShow(myImageView,item.getPreviewimg(),20);
        helper.setText(R.id.bookname_id,item.getResourcename());
        RatingBar ratingBar = helper.getView(R.id.ratingbar_id);
        if (!"".equals(item.getReslevel()) && item.getReslevel()!=null){
            ratingBar.setRating(Integer.parseInt(item.getReslevel()));
        }else {
            ratingBar.setRating(0);
        }
        helper.setText(R.id.authorname_id,item.getAuthorname());
        helper.setText(R.id.type_id,item.getResourcekind());
        helper.setText(R.id.status_id,item.getStatus());
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        /*if (item.getResfinishdate()!=null && !item.getResfinishdate().equals("")){
            long i = Long.valueOf(item.getResfinishdate());
            Date date = new Date(i);
            helper.setText(R.id.createdate_value_id,format.format(date));
        }else {
            helper.setText(R.id.createdate_value_id,"");
        }*/
        helper.setText(R.id.createdate_value_id,item.getResfinishdate());

    }
}
