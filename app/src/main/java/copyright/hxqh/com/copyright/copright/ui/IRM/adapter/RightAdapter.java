package copyright.hxqh.com.copyright.copright.ui.IRM.adapter;


import android.annotation.SuppressLint;
import android.database.DataSetObserver;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.RatingBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.IRM.enity.Asset;
import copyright.hxqh.com.copyright.copright.ui.IRM.enity.RightInfo;
import copyright.hxqh.com.copyright.copright.view.MyImageView;

/**
 * Created by zzw on 2018/9/18.
 */

public class RightAdapter extends BaseQuickAdapter<RightInfo,BaseViewHolder> implements Serializable,ListAdapter {
    public RightAdapter(int layoutResId, @Nullable List<RightInfo> data) {
        super(layoutResId, data);
    }

    public RightAdapter(@Nullable List<RightInfo> data) {
        super(data);
    }

    public RightAdapter(int layoutResId) {
        super(layoutResId);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, RightInfo item) {
        CardView cardView = helper.getView(R.id.card_container);
        helper.setText(R.id.copyright_id,item.getContractnum());
        helper.setText(R.id.name_id,item.getResourcename());
        //helper.setText(R.id.getType_id,item.get());
        /*helper.setText(R.id.to_date_id,item.getStartdate() + "至" + item.getFinishdate());
        helper.setText(R.id.,item.getPersonalproduct());
        helper.setText(R.id.languages_id,item.getLanguages());
        helper.setText(R.id.finishplacearea,item.getFinishplacearea());*/
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
