package copyright.hxqh.com.copyright.copright.ui.IRM.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;

/**
 * Created by zzw on 2018/9/18.
 */

public class ImageAdapter extends ArrayAdapter {
    private LayoutInflater inflater;
    private int resource;

    public ImageAdapter(@NonNull Context context, int resource, @NonNull List<String> list) {
        super(context, resource, list);
        inflater = LayoutInflater.from(context);
        this.resource = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String item = (String) this.getItem(position);
        ViewHolder viewHolder;
        viewHolder = new ViewHolder();
        View view = inflater.inflate(resource, null);
        viewHolder.imageView = view.findViewById(R.id.image);
        AcountUtil.imageShow2(viewHolder.imageView,item);
        return view;
    }
    public static class ViewHolder {
        public ImageView imageView;
    }
}
