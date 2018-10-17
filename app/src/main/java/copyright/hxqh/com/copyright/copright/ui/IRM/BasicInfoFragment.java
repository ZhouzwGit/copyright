package copyright.hxqh.com.copyright.copright.ui.IRM;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.IRM.enity.Asset;
import copyright.hxqh.com.copyright.copright.view.MyImageView;

/**
 * Created by zzw on 2018/9/18.
 */

public class BasicInfoFragment extends Fragment {
    private TextView authorname,resourcename,resourceno,signname,resourcekind,resdiscribe,ISBN,cost,resgainway,rightgainway,
            resmedium,isrelease,createdate,createCity;
    private MyImageView credentials,bookImage;
    private RatingBar resleve;
    private Asset asset;
    private GridView reslableView;
    private String[] lablelist;
    private SimpleAdapter adapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        asset = (Asset) getArguments().get("asset");
        String lable = asset.getReslabel();
        if (lable!=null){
            lablelist = lable.split(",");
        }

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.asset_detail, container,
                false);
        findViewById(view);
        initView();
        return view;
    }
    public void findViewById(View view){
        authorname = view.findViewById(R.id.authorname_id);
        resourcename = view.findViewById(R.id.bookname_id);
        resourceno= view.findViewById(R.id.assetnum_id);
        signname = view.findViewById(R.id.signature_id);
        resourcekind = view.findViewById(R.id.resourcekind_id);
        resdiscribe = view.findViewById(R.id.resdiscribe_id);
        ISBN = view.findViewById(R.id.ISBN_id);
        cost = view.findViewById(R.id.cost_id);
        resgainway = view.findViewById(R.id.resgainway_id);
        rightgainway = view.findViewById(R.id.rightgainway_id);
        resmedium=view.findViewById(R.id.resmedium_id);
        isrelease=view.findViewById(R.id.isrelease_id);
        createdate = view.findViewById(R.id.createdate_id);
        createCity = view.findViewById(R.id.createcity_id);
        resleve = view.findViewById(R.id.ratingbar_id);
        credentials = view.findViewById(R.id.credentials_id);
        bookImage = view.findViewById(R.id.bookImage_id);
        reslableView = view.findViewById(R.id.lablegrid_id);
    }
    private void initView() {
        authorname.setText(asset.getAuthorname());
        resourcename.setText(asset.getResourcename());
        String no=asset.getResourceno();
        if (no==null){
            no="";
        }
        resourceno.setText(no);
        signname.setText(asset.getSignname());
        resourcekind.setText(asset.getResourcekind());
        resdiscribe.setText(asset.getResdiscribe());
        ISBN.setText(asset.getIsbnnum());
        cost.setText(asset.getCost());
        resgainway.setText(asset.getResgainway());
        rightgainway.setText(asset.getRightgainway());
        resmedium.setText(asset.getResmedium());
        isrelease.setText(asset.getIsrelease());
        createdate.setText(asset.getCreatedate());
        createCity.setText(asset.getCreateCity());
        if (asset.getReslevel()!=null && !asset.getReslevel().equals(""))
        {
            resleve.setRating(Float.parseFloat(asset.getReslevel()));
        }else {
            resleve.setRating(0);
        }
        if (!"".equals(asset.getCredentials()) && asset.getCredentials()!=null){
            String path = asset.getCredentials();
            path = path.substring(1);
            credentials.setImageURL( path);
        }
        if (!"".equals(asset.getPreviewimg())&&asset.getCredentials()!=null){
            String path = asset.getPreviewimg();
            path = path.substring(1);
            bookImage.setImageURL( path);
        }else {
            bookImage.setImageResource(R.mipmap.havenoimage);
        }
        reslableView.setNumColumns(lablelist.length);
        initAdpter();
    }
    private  void initAdpter(){
        List<Map<String,String>> dataList = new ArrayList<>();
        for (int i=0;i<lablelist.length;i++) {
            Map<String, String> map=new HashMap<String, String>();
            map.put("text",lablelist[i]);
            dataList.add(map);
        }
        String[] from = {"text"};
        int[] to = {R.id.text};
        adapter = new SimpleAdapter(getActivity(),dataList,R.layout.gridview_item,from,to);
        reslableView.setAdapter(adapter);
    }
}
