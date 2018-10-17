package copyright.hxqh.com.copyright.copright.ui.author;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import copyright.hxqh.com.copyright.R;
import copyright.hxqh.com.copyright.copright.ui.author.entity.Right;

public class RightDialogActivity extends Dialog {
    private Context context;
    private Right right;
    private TextView
            rightrestrict,
            rightattribute,
            languages,
            startdate,
            resname,
            finishplacearea,
            rightname,
            finishdate,
            gainway;
    private ImageView closeimage;

    public RightDialogActivity(Context context) {
        super(context);
        this.context = context;
    }
    public RightDialogActivity(@NonNull Context context,Right right) {
        super(context);
        this.context = context;
        this.right = right;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_right_dialog);
        //getIntentData();
        findViewById();
        initView();
    }

    public void findViewById() {
        rightrestrict = findViewById(R.id.rightrestrict_id);
        rightattribute = findViewById(R.id.rightattribute_id);
        languages = findViewById(R.id.languages_id);
        startdate = findViewById(R.id.startdate_id);
        resname = findViewById(R.id.resname_id);
        finishplacearea = findViewById(R.id.finishplacearea_id);
        rightname = findViewById(R.id.rightname_id);
        finishdate = findViewById(R.id.enddate_id);
        gainway = findViewById(R.id.gainway_id);
        closeimage = findViewById(R.id.close_id);
    }

    public void initView() {
        rightrestrict.setText(right.getRightrestrict());
        rightattribute.setText(right.getRightattribute());
        languages.setText(right.getLanguages());
        startdate.setText(right.getStartdate());
        resname.setText("《"+right.getResname()+"》");
        finishplacearea.setText(right.getFinishplacearea());
        rightname.setText(right.getRightname());
        finishdate.setText(right.getFinishdate());
        gainway.setText(right.getGainway());
        closeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dismiss();
            }
        });
    }
    private void initLayoutParams() {
        // 布局的参数
/*        ViewGroup.LayoutParams params = getWindow().getAttributes();
        params. = Gravity.CENTER_HORIZONTAL | Gravity.CENTER;
        params.alpha = 1f;
        getWindow().setAttributes(params);*/
    }


/*    public void getIntentData() {
        right = (Right) getIntent().getExtras().get("right");
    }*/
}
