package copyright.hxqh.com.copyright.copright.ui.contract.adpter;


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
import copyright.hxqh.com.copyright.copright.ui.contract.entity.Contract;
import copyright.hxqh.com.copyright.copright.ui.product.entity.Product;

/**
 * Created by zzw on 2018/9/18.
 */

public class ContractAdapter extends BaseQuickAdapter<Contract,BaseViewHolder> {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
    public ContractAdapter(int layoutResId, @Nullable List<Contract> data) {
        super(layoutResId, data);
    }

    public ContractAdapter(@Nullable List<Contract> data) {
        super(data);
    }

    public ContractAdapter(int layoutResId) {
        super(layoutResId);
    }
    @Override
    protected void convert(BaseViewHolder helper, Contract item) {
        ImageView imageView = helper.getView(R.id.image_id);
        if (item.getContracttype().contains("授权合同")){
            imageView.setImageResource(R.mipmap.contract2);
        }else {
            imageView.setImageResource(R.mipmap.contract1);
        }
        helper.setText(R.id.contractnum_id,item.getContractnum());
        helper.setText(R.id.contractname_id,item.getContractname());
        TextView name = helper.getView(R.id.contractname_id);
        name.setMovementMethod(ScrollingMovementMethod.getInstance());
        name.setHorizontallyScrolling(true);
        name.setFocusable(true);
        helper.setText(R.id.authorizedparty_id,item.getAuthorizedparty());
        helper.setText(R.id.authorizedtoparty_id,item.getAuthorizedpartys());
        helper.setText(R.id.createdate_id,item.getSigndate());
        helper.setText(R.id.status_id,item.getStatus());

    }
}
