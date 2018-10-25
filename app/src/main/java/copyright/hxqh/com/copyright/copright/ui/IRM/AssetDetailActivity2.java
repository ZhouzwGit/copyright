package copyright.hxqh.com.copyright.copright.ui.IRM;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.entity.RightInfo;
import copyright.hxqh.com.copyright.copright.ui.IRM.adapter.AssetrelaAdapter;
import copyright.hxqh.com.copyright.copright.ui.IRM.adapter.ImageAdapter;
import copyright.hxqh.com.copyright.copright.ui.IRM.adapter.RightAdapter2;
import copyright.hxqh.com.copyright.copright.ui.IRM.enity.Asset;
import copyright.hxqh.com.copyright.copright.ui.IRM.enity.Rchrrel;
import copyright.hxqh.com.copyright.copright.util.AcountUtil;

public class AssetDetailActivity2 extends AppCompatActivity {
    private TextView
            signature,
            authorid,
            detailid,
            crownerids,
            contactids,
            authorname,
            rightinfoid,
            audvispdtid,
            orignid,
            iscontact,
            resourceno,
            resourcename,
            resourcekind,
            resmedium,
            reslevel,
            finishdate,
            finishplace,
            isrelease,
            isfstrls,
            fstrlsadd,
            fstrlsdate,
            releaseplace,
            resourcelabel,
            count,
            createnature,
            resgainway,
            cost,
            resdiscribe,
            rightgainway,
            rightgainnote,
            warehousestatus,
            departmentnum,
            status,
            isbnnum,
            createby,
            relevantcontract,
            resourcetype,
            resourcecost,
            specialrestrict,
            credentials,
            otherrestrict,
            isContinue,
            purpose,
            isPutInStorage,
            createdate,
            remark,
            havenodate1,
            havenodata2, copyrightcount, relatcount, title, isrcno;
    private Asset asset;
    private ImageView previewimg, backimage, searchimage;
    private RatingBar ratingBar;
    private GridView lablegridView, imagegridview;
    private List<RightInfo> rightList;
    private List<Rchrrel> rchrrelList;
    private ListView listView1;
    private RecyclerView listView2;
    private RightAdapter2 rightAdapter;
    private AssetrelaAdapter assetrelaAdapter;
    private LinearLayoutManager layoutManager;
    LinearLayout isshowlist1, isshowlist2;
    boolean showlist1, showlist2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asset_detail2);
        getIntentDate();
        findViewById();
        initView();

    }

    public void findViewById() {
        authorname = findViewById(R.id.authorname_id);
        orignid = findViewById(R.id.originalnum_id);
        resourceno = findViewById(R.id.resourceno_id);
        resourcename = findViewById(R.id.resourcename_id);
        resourcekind = findViewById(R.id.resourcekind_id);
        resmedium = findViewById(R.id.resmedium_id);
        finishdate = findViewById(R.id.finishdate_id);
        finishplace = findViewById(R.id.finishplacearea_id);
        isrelease = findViewById(R.id.isrelease_id);
        resgainway = findViewById(R.id.resgainway_id);
        resdiscribe = findViewById(R.id.resdiscribe_id);
        rightgainway = findViewById(R.id.rightgainway_id);
        previewimg = findViewById(R.id.assetimage_id);
        backimage = findViewById(R.id.back_id);
        //resourcelabel = findViewById(R.id.reslabel_id);
        ratingBar = findViewById(R.id.ratingbar_id);
        isbnnum = findViewById(R.id.ISBN_id);
        signature = findViewById(R.id.signature_id);
        cost = findViewById(R.id.cost_id);
        remark = findViewById(R.id.remark_id);
        createby = findViewById(R.id.createby_id);
        createdate = findViewById(R.id.createdate_id);
        lablegridView = findViewById(R.id.lablegrid_id);
        searchimage = findViewById(R.id.title_search);
        imagegridview = findViewById(R.id.credentials_id);
        listView1 = findViewById(R.id.list_1);
        listView2 = findViewById(R.id.list_2);
        havenodata2 = findViewById(R.id.have_not_data_id2);
        havenodate1 = findViewById(R.id.have_not_data_id);
        copyrightcount = findViewById(R.id.copyright_id);
        relatcount = findViewById(R.id.relationship_id);
        fstrlsadd = findViewById(R.id.fstrlsadd_id);
        fstrlsdate = findViewById(R.id.fstrlsdate_id);
        purpose = findViewById(R.id.purpose_id);
        title = findViewById(R.id.menu_title);
        isrcno = findViewById(R.id.ISRC_id);
        isshowlist1 = findViewById(R.id.isshowlist_id);
        isshowlist2 = findViewById(R.id.isshowlist2_id);

    }

    private void initView() {
        isrcno.setText(asset.getIsrcnum());
        title.setText(asset.getResourcename());
        purpose.setText(asset.getPurpose());
        fstrlsdate.setText(asset.getFstrlsdate());
        fstrlsadd.setText(asset.getFstrlsadd());
        if (rightList.isEmpty()) {
            havenodate1.setVisibility(View.VISIBLE);
            isshowlist1.setVisibility(View.GONE);
        }
        if (rchrrelList.isEmpty()) {
            havenodata2.setVisibility(View.VISIBLE);
            isshowlist2.setVisibility(View.GONE);
        }
        relatcount.setText("共 " + rchrrelList.size() + " 项");
        copyrightcount.setText("共 " + rightList.size() + " 项");
        searchimage.setVisibility(View.GONE);
        createdate.setText(asset.getCreateDate());
        createby.setText(asset.getCreateBy());
        remark.setText(asset.getRemark());
        cost.setText("￥" + asset.getResourcecost() + "元");
        signature.setText(asset.getSignname());
        isbnnum.setText(asset.getIsbnnum());
        if (asset.getReslevel() != null && !asset.getReslevel().equals("")) {
            ratingBar.setRating(Float.parseFloat(asset.getReslevel()));
        } else {
            ratingBar.setRating(0);
        }
        //resourcelabel.setText(asset.getReslabel());
        String author = asset.getAuthorname();
        if (!author.equals("")) {
            author.contains(",");
            author = author.replace(",", " ");
        }
        authorname.setText(author);
        authorname.setTextColor(Color.BLUE);
        orignid.setText(asset.getOrignid());
        resourceno.setText(asset.getResourceno());
        resourcename.setText(asset.getResourcename());
        resourcekind.setText(asset.getResourcekind());
        resmedium.setText(asset.getResmedium());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Date date = new Date(Long.parseLong(asset.getResfinishdate()));
        finishdate.setText(asset.getResfinishdate());
        finishplace.setText(asset.getCreateNation() + " " + asset.getCreateCity());
        isrelease.setText(asset.getIsrelease());
        resgainway.setText(asset.getResgainway());
        resdiscribe.setText(asset.getResdiscribe());
        rightgainway.setText(asset.getRightgainway());
        AcountUtil.imageShow(previewimg, asset.getPreviewimg(), 20);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setSmoothScrollbarEnabled(false);
        listView2.setLayoutManager(layoutManager);
        initGrid();
        initAdpter();
        isshowlist1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!showlist1) {
                    listView1.setVisibility(View.GONE);
                } else {
                    listView1.setVisibility(View.VISIBLE);
                }

            }
        });
        listView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!showlist2) {
                    listView2.setVisibility(View.GONE);
                } else {
                    listView2.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void getIntentDate() {
        asset = (Asset) getIntent().getExtras().get("asset");
        rightList = asset.getRightinfoList();
        rchrrelList = asset.getRchrrelList();
    }

    public void initGrid() {
        String lable = asset.getReslabel();
        String url = asset.getCredentials();
        if (url != null && url.contains("|")) {
            url = url.replace("|", "####");
            String[] urls = url.split("####");
            List<String> dataList = new ArrayList<>();
            for (int i = 1; i < urls.length; i++) {
                dataList.add(urls[i]);
            }
            ImageAdapter adpter = new ImageAdapter(this, R.layout.gridimage_item, dataList);
            imagegridview.setAdapter(adpter);
        }
        String[] lables = null;
        if (lable != null && !lable.equals("")) {
            lables = lable.split(" ");
            List<Map<String, String>> dataList = new ArrayList<>();
            for (int i = 0; i < lables.length; i++) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("text", lables[i]);
                dataList.add(map);
            }
            String[] from = {"text"};
            int[] to = {R.id.text};
            SimpleAdapter adapter = new SimpleAdapter(this, dataList, R.layout.gridview_item, from, to);
            lablegridView.setAdapter(adapter);
        }
    }

    public void initAdpter() {
        rightAdapter = new RightAdapter2(this, R.layout.asset_detail_list1_item, rightList);
        assetrelaAdapter = new AssetrelaAdapter(R.layout.asset_detail_list2_item, rchrrelList);
        listView1.setAdapter(rightAdapter);
        listView2.setAdapter(assetrelaAdapter);
    }

}
