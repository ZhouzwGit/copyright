package copyright.hxqh.com.copyright.copright.ui.product.adapter;


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
import copyright.hxqh.com.copyright.copright.ui.product.entity.Product;
import copyright.hxqh.com.copyright.copright.view.MyImageView;

/**
 * Created by zzw on 2018/9/18.
 */

public class ProductAdapter extends BaseQuickAdapter<Product,BaseViewHolder> {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
    public ProductAdapter(int layoutResId, @Nullable List<Product> data) {
        super(layoutResId, data);
    }

    public ProductAdapter(@Nullable List<Product> data) {
        super(data);
    }

    public ProductAdapter(int layoutResId) {
        super(layoutResId);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, Product item) {
        CardView cardView = helper.getView(R.id.card_container);
        MyImageView myImageView = helper.getView(R.id.bookimage_id);
        myImageView.setImageResource(R.mipmap.havenoimage);
        helper.setText(R.id.bookname_id,item.getProductname());
        helper.setText(R.id.createdate_value_id,item.getCreateDate());
        helper.setText(R.id.productcost_id,item.getProductcost()+"");
        helper.setText(R.id.productcount_id,item.getQuantity()+"");

    }
}
