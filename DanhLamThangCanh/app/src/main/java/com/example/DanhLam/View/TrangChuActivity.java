package com.example.DanhLam.View;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.DanhLam.Control.DanhLamController;
import com.example.DanhLam.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TrangChuActivity extends AppCompatActivity{
    public static int CALLBACK_LOGIN_CODE=99;
    RecyclerView recyclerViewdata;
    SearchView searchView;
    DanhLamController danhLamController;
    Button btnDangXuat;
    FirebaseUser user;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu);

        recyclerViewdata=findViewById(R.id.recylerOdau);
        searchView= findViewById(R.id.searchView);
        btnDangXuat = findViewById(R.id.btnDangXuat);

        firebaseAuth = FirebaseAuth.getInstance();

        danhLamController= new DanhLamController(getApplicationContext(),recyclerViewdata);
        danhLamController.getDanhSachDanhLamThangCanhController(getApplicationContext(), recyclerViewdata, false,"");

    }

    @Override
    protected void onStart() {
        super.onStart();

        user = firebaseAuth.getCurrentUser();
        if(user != null){
            btnDangXuat.setVisibility(View.VISIBLE);

            btnDangXuat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    firebaseAuth.signOut();
                    Toast.makeText(TrangChuActivity.this,"Đăng xuất thành công",Toast.LENGTH_LONG).show();
                    btnDangXuat.setVisibility(View.GONE);
                    DangNhapActivity.KIEMTRA_PROVIDER_DANGNHAP = 0;
                }
            });
        }
        else
        {
            btnDangXuat.setVisibility(View.GONE);
        }

//        danhLamController = new DanhLamController(this, recyclerViewdata);
//        danhLamController.getDanhSachQuanAnController(this, recyclerViewdata, false,"");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchView.clearFocus();
                if(!s.equals(""))
                {
                    Log.d("appcheck","query: "+s);
                    danhLamController.getDanhSachDanhLamThangCanhController(getApplicationContext(),recyclerViewdata,true, s);

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                danhLamController.getDanhSachDanhLamThangCanhController(getApplicationContext(), recyclerViewdata, false,"");
                return false;
            }
        });

    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode==CALLBACK_LOGIN_CODE)
//        {
//            if(resultCode==VerifyPhoneActivity.RESULT_LOGIN_SUCCESS)
//            {
//                Intent intentNewPost= new Intent(this,PostBaiActivity.class);
//                startActivity(intentNewPost);
//            }
//        }
//    }
}
