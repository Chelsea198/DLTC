package com.example.DanhLam.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.DanhLam.R;
import com.example.DanhLam.View.Fragment.ManagerFragment;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splashscreen);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent iTrangChu=new Intent(SplashScreenActivity.this, ManagerFragment.class);
                startActivity(iTrangChu);
                finish();
            }
        },3000);
    }

}
