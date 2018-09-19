package copyright.hxqh.com.copyright.copright.ui.IRM;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import copyright.hxqh.com.copyright.R;

/**
 * Created by zzw on 2018/9/19.
 */

public class RelatedAssetFragment extends Fragment {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list, container,
                false);
        return view;
    }
}
