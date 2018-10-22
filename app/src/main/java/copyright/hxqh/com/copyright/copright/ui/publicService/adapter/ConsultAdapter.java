package copyright.hxqh.com.copyright.copright.ui.publicService.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.publicService.entity.Consult;
import copyright.hxqh.com.copyright.copright.ui.publicService.entity.RoyaltyEnity;

/**
 * Created by lianjh on 2018\10\19 0019.
 * Current page
 */

public class ConsultAdapter  extends BaseQuickAdapter<Consult,BaseViewHolder> {
    public ConsultAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Consult item) {
        helper.setText(R.id.consult_type,item.getManagementabqu());
        helper.setText(R.id.consult_num,item.getServiceno());
        helper.setText(R.id.consult_author,item.getApplyUser());
        helper.setText(R.id.consult_phone,item.getPhone());
        helper.setText(R.id.consult_date,item.getCreateDate());
        helper.setText(R.id.consult_status,item.getStatus());
    }
}
