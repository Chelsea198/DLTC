package com.example.DanhLam.View;

import com.example.DanhLam.View.Fragment.ManagerFragment;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.api.Places;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.DanhLam.Control.PostBaiController;
import com.example.DanhLam.R;

import java.util.Arrays;
import java.util.List;

public class PostBaiActivity extends AppCompatActivity implements View.OnClickListener {
    final int RESULT_IMG1 = 111;
    final int RESULT_IMG2 = 112;
    final int RESULT_IMG3 = 113;
    final int RESULT_IMG4 = 114;
    final int RESULT_IMG5 = 115;
    final int RESULT_IMG6 = 116;
    static int PLACE_PICKER_REQUEST=101;
    static int PERMISSION_REQUEST_CODE;

    Button btnAddImage;

    ImageView imgHinh1, imgHinh2, imgHinh3, imgHinh4, imgHinh5, imgHinh6;
    List<Uri> hinhDanhLam;
    Location location;
    Context mContext;
    Activity mActivity;
    PostBaiController postBaiController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_bai);
        postBaiController=new PostBaiController(this,this);

        // Initialize Places.
        Places.initialize(this, "AIzaSyA_yXGk2lddVkWbHCXtMyqv3VGjoqAkT2Y");

        // Create a new Places client instance.
        PlacesClient placesClient = Places.createClient(this);



        mActivity=this;
        mContext=this;
        AnhXa();
        SetEvents();

    }

    private void AnhXa() {
        btnAddImage = findViewById(R.id.btnDangBai);

        imgHinh1 = findViewById(R.id.imgHinh1);
        imgHinh2 = findViewById(R.id.imgHinh2);
        imgHinh3 = findViewById(R.id.imgHinh3);
        imgHinh4 = findViewById(R.id.imgHinh4);
        imgHinh5 = findViewById(R.id.imgHinh5);
        imgHinh6 = findViewById(R.id.imgHinh6);
        hinhDanhLam = Arrays.asList(new Uri[6]);
    }

    private void SetEvents() {
        imgHinh1.setOnClickListener(this);
        imgHinh2.setOnClickListener(this);
        imgHinh3.setOnClickListener(this);
        imgHinh4.setOnClickListener(this);
        imgHinh5.setOnClickListener(this);
        imgHinh6.setOnClickListener(this);
        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String maDLTC=getIntent().getStringExtra("maDanhLam");
                postBaiController.NewPost(maDLTC, hinhDanhLam);
                Intent iTrangChu=new Intent(PostBaiActivity.this, ManagerFragment.class);
                startActivity(iTrangChu);

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==PERMISSION_REQUEST_CODE)
        {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // permission was granted, yay! Do the
                // contacts-related task you need to do.
            } else {

                // permission denied, boo! Disable the
                // functionality that depends on this permission.
                Toast.makeText(PostBaiActivity.this, "Permission denied to get your Location", Toast.LENGTH_SHORT).show();
            }
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d("appcheck","activity return "+requestCode);
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case RESULT_IMG1:
                if(RESULT_OK == resultCode){
                    Uri uri = data.getData();
                    imgHinh1.setImageURI(uri);
                    hinhDanhLam.set(0, uri);
                }
                break;

            case RESULT_IMG2:
                if(RESULT_OK == resultCode){
                    Uri uri = data.getData();
                    imgHinh2.setImageURI(uri);
                    hinhDanhLam.set(1, uri);
                }
                break;

            case RESULT_IMG3:
                if(RESULT_OK == resultCode){
                    Uri uri = data.getData();
                    imgHinh3.setImageURI(uri);
                    hinhDanhLam.set(2, uri);
                }
                break;

            case RESULT_IMG4:
                if(RESULT_OK == resultCode){
                    Uri uri = data.getData();
                    imgHinh4.setImageURI(uri);
                    hinhDanhLam.set(3, uri);
                }
                break;

            case RESULT_IMG5:
                if(RESULT_OK == resultCode){
                    Uri uri = data.getData();
                    imgHinh5.setImageURI(uri);
                    hinhDanhLam.set(4, uri);
                }
                break;

            case RESULT_IMG6:
                if(RESULT_OK == resultCode){
                    Uri uri = data.getData();
                    imgHinh6.setImageURI(uri);
                    hinhDanhLam.set(5, uri);
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgHinh1:
                ChonHinhTuGallary(RESULT_IMG1);
                break;

            case R.id.imgHinh2:
                ChonHinhTuGallary(RESULT_IMG2);
                break;

            case R.id.imgHinh3:
                ChonHinhTuGallary(RESULT_IMG3);
                break;

            case R.id.imgHinh4:
                ChonHinhTuGallary(RESULT_IMG4);
                break;

            case R.id.imgHinh5:
                ChonHinhTuGallary(RESULT_IMG5);
                break;

            case R.id.imgHinh6:
                ChonHinhTuGallary(RESULT_IMG6);
                break;
        }
    }

    private void ChonHinhTuGallary(int requestCode){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Chọn hình..."),requestCode);
    }
}
