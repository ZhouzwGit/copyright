package copyright.hxqh.com.copyright.copright.ui.product.adapter;


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
import copyright.hxqh.com.copyright.copright.ui.product.entity.Product;
import copyright.hxqh.com.copyright.copright.view.MyImageView;

/**
 * Created by zzw on 2018/9/18.
 */

public class ProductAdapter2 extends BaseQuickAdapter<Product,BaseViewHolder> {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
    public ProductAdapter2(int layoutResId, @Nullable List<Product> data) {
        super(layoutResId, data);
    }

    public ProductAdapter2(@Nullable List<Product> data) {
        super(data);
    }

    public ProductAdapter2(int layoutResId) {
        super(layoutResId);
    }
    @Override
    protected void convert(BaseViewHolder helper, Product item) {
        CardView cardView = helper.getView(R.id.card_container);
        helper.setText(R.id.productname_id,item.getProductname());
        TextView name = helper.getView(R.id.productname_id);
        name.setMovementMethod(ScrollingMovementMethod.getInstance());
        name.setHorizontallyScrolling(true);
        name.setFocusable(true);
        helper.setText(R.id.createdate_id,item.getCreateDate());
        helper.setText(R.id.productusage_id,item.getProductusage());
        helper.setText(R.id.productqty_id,item.getQuantity()+"");
        helper.setText(R.id.productno_id,item.getProductno());
        helper.setText(R.id.status_id,item.getStatus());

    }
}
