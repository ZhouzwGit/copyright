package copyright.hxqh.com.copyright.copright.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.ImageView;

import copyright.hxqh.com.copyright.R;

public class TheMainActivity extends AppCompatActivity {
    private ImageView selfManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        findViewById();
        selfManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToSelfManager();
            }
        });
    }

    public void findViewById() {
        selfManager = findViewById(R.id.selfmanager);
    }
    public void intentToSelfManager(){
        Intent intent = new Intent(this,SelfManagerActivity.class);
        startActivity(intent);
    }
}
