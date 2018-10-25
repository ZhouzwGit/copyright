package copyright.hxqh.com.copyright.copright.ui.author.adpter;


import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.author.entity.Author;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Product;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;

/**
 * Created by zzw on 2018/9/18.
 */

public class AuthorAdapter extends BaseQuickAdapter<Author,BaseViewHolder> {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
    public AuthorAdapter(int layoutResId, @Nullable List<Author> data) {
        super(layoutResId, data);
    }

    public AuthorAdapter(@Nullable List<Author> data) {
        super(data);
    }

    public AuthorAdapter(int layoutResId) {
        super(layoutResId);
    }
    @Override
    protected void convert(BaseViewHolder helper, Author item) {
        CardView cardView = helper.getView(R.id.card_container);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) cardView.getLayoutParams();
        int i = (int) mContext.getResources().getDisplayMetrics().density;
        if (helper.getLayoutPosition()==0){
            layoutParams.setMargins(0,10*i,0,7*i);
            cardView.setLayoutParams(layoutParams);
        }else {
            layoutParams.setMargins(0,0,0,7*i);
            cardView.setLayoutParams(layoutParams);
        }
        helper.setText(R.id.name_id,item.getName());
        TextView name = helper.getView(R.id.name_id);
        name.setMovementMethod(ScrollingMovementMethod.getInstance());
        name.setHorizontallyScrolling(true);
        name.setFocusable(true);
        helper.setText(R.id.ahthortype_id,item.getAhthortype());
        helper.setText(R.id.restype_id,item.getRestype());
        helper.setText(R.id.ahthorno_id,item.getAhthorno());
        helper.setText(R.id.isAuthor_id,item.getIsAuthor());
        ImageView imageView = helper.getView(R.id.authorimage_id);
        AcountUtil.imageShow(imageView,item.getAuthorimg(),120);
    }
}
