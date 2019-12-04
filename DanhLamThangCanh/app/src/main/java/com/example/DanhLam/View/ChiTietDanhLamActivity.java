package com.example.DanhLam.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.DanhLam.Adapters.AdapterRecyclerHinhAnh;
import com.example.DanhLam.Control.BinhLuanController;
import com.example.DanhLam.Model.DanhLamThangCanhModel;
import com.example.DanhLam.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ChiTietDanhLamActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {
    static int BINHLUAN_CODE_RESULT=1998;
    TextView txtTenDanhLamThangCanh,txtDiaChiDanhLamThangCanh,txtDanhGia,txtDanhDau,txtTieuDeToolbar,txtGioiThieu,txtYeuThich;
    DanhLamThangCanhModel danhLamThangCanhModel;
    ImageView imgHinhAnhDanhLam, imgYeuThich,imgDanhDau;
    MapFragment mapFragment;
    GoogleMap googleMap;
    RecyclerView recyclerViewAnhDep,recyclerViewBinhLuan;
    AdapterRecyclerHinhAnh adapterRecyclerHinhAnh;
    FirebaseUser user;
    ArrayList<Bitmap> bitmapList= new ArrayList<>();
    ArrayList<String> listHinh=new ArrayList<>();
    Context mContext;
    BinhLuanController binhLuanController;
    FloatingActionButton fabAdd;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietdanhlam);

        danhLamThangCanhModel=getIntent().getParcelableExtra("danhlamthangcanh");
        mContext=this;
        user =FirebaseAuth.getInstance().getCurrentUser();
        AddControl();
        HienThiChiTietDanhLam();
        HienThiHinhAnhDep();

        AddEvents();
        recyclerViewBinhLuan.setLayoutManager(new LinearLayoutManager(this));
        binhLuanController= new BinhLuanController(recyclerViewBinhLuan,this,danhLamThangCanhModel.getMadanhlam(),R.layout.custom_layout_binhluan);
        binhLuanController.GetDanhSachBinhLuan();
    }
    public  void AddControl(){
        txtGioiThieu=findViewById(R.id.txtGioiThieu);
        txtTieuDeToolbar=findViewById(R.id.txtTieuDeToolbar);
        txtYeuThich=findViewById(R.id.txtYeuThich);
        txtDanhGia=findViewById(R.id.txtDanhGia);

        txtDanhDau=findViewById(R.id.txtDanhDau);
        imgDanhDau=findViewById(R.id.imgDanhDau);

        fabAdd=findViewById(R.id.fabAdd);

        txtDiaChiDanhLamThangCanh=findViewById(R.id.txtDiaChiDanhLamThangCanh);
        txtTenDanhLamThangCanh=findViewById(R.id.txtTenDanhLamThangCanh);

        imgYeuThich = findViewById(R.id.imgYeuThich);
        imgHinhAnhDanhLam=findViewById(R.id.imgHinhAnhDanhLam);



        recyclerViewAnhDep=findViewById(R.id.recyclerHinhAnhDep);
        mapFragment=(MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        recyclerViewBinhLuan= findViewById(R.id.recyclerViewBinhLuanDanhlam);

    }
    public void HienThiChiTietDanhLam()
    {
        txtGioiThieu.setText(danhLamThangCanhModel.getGioithieu());
        txtTenDanhLamThangCanh.setText(danhLamThangCanhModel.getTendanhlam());
        txtDiaChiDanhLamThangCanh.setText(danhLamThangCanhModel.getDiachi());
        txtTieuDeToolbar.setText(danhLamThangCanhModel.getTendanhlam());
        StorageReference storageHinhAnh = FirebaseStorage.getInstance().getReference().child("hinhdanhlam").child(danhLamThangCanhModel.getHinhanh());
        long ONE_MEGABYTE = 5 * 1024 * 1024;
        storageHinhAnh.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                imgHinhAnhDanhLam.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imgHinhAnhDanhLam.setImageBitmap(bitmap);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap=googleMap;
        double longtitude=danhLamThangCanhModel.getLongtitude();
        Log.d("kt",danhLamThangCanhModel.getLongtitude()+"");
        double latitude=danhLamThangCanhModel.getLatitude();
        LatLng latLng=new LatLng(latitude,longtitude);
        MarkerOptions markerOptions= new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(danhLamThangCanhModel.getTendanhlam());
        googleMap.addMarker(markerOptions);

        CameraUpdate cameraUpdate=CameraUpdateFactory.newLatLngZoom(latLng, 15);
        googleMap.moveCamera(cameraUpdate);

    }
    public void HienThiHinhAnhDep()
    {
        recyclerViewAnhDep.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        listHinh=danhLamThangCanhModel.getHinhanhdanhlam();

        for (String imageChild:listHinh) {

            StorageReference storageHinhAnh = FirebaseStorage.getInstance().getReference().child("hinhdanhlam").child(imageChild);
            long ONE_MEGABYTE = 5 * 1024 * 1024;
            storageHinhAnh.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    bitmapList.add(bitmap);
                    if(bitmapList.size()==listHinh.size())
                    {
                        recyclerViewAnhDep.setAdapter(new AdapterRecyclerHinhAnh(mContext,R.layout.custom_layout_hinhanhdep, listHinh,bitmapList));

                    }

                }
            });
        }

    }
    public void AddEvents(){

        txtYeuThich.setOnClickListener(this);
        txtDanhDau.setOnClickListener(this);
        imgYeuThich.setOnClickListener(this);
        imgDanhDau.setOnClickListener(this);
        txtDanhGia.setOnClickListener(this);
        fabAdd.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==BINHLUAN_CODE_RESULT)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                String result=data.getStringExtra("result");
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                F5BinhLuan();

            }
        }
    }
    void F5BinhLuan()
    {
        binhLuanController.RefreshBinhLuan();
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.txtDanhGia:

                if(user!=null)
                {
                    Intent iBinhLuan=new Intent(ChiTietDanhLamActivity.this,BinhLuanActivity.class);
                    iBinhLuan.putExtra("maDanhLam",danhLamThangCanhModel.getMadanhlam());
                    iBinhLuan.putExtra("tendanhlam",danhLamThangCanhModel.getTendanhlam());
                    iBinhLuan.putExtra("vitridanhlam",danhLamThangCanhModel.getDiachi());
                    startActivityForResult(iBinhLuan,BINHLUAN_CODE_RESULT);
                }else {
                    Toast.makeText(ChiTietDanhLamActivity.this,"Bạn Cần Đăng Nhập",Toast.LENGTH_SHORT).show();
                    Intent iDangNhap=new Intent(ChiTietDanhLamActivity.this,HomeLoginActivity.class);
                    startActivityForResult(iDangNhap,BINHLUAN_CODE_RESULT);

                }
                break;
            case  R.id.txtYeuThich:
                if (imgYeuThich.getTag() == "0")
                {
                    imgYeuThich.setImageResource(R.drawable.like_click);
                    imgYeuThich.setTag("1");
                }
                else
                {
                    imgYeuThich.setImageResource(R.drawable.like);
                    imgYeuThich.setTag("0");
                }
                break;
            case R.id.txtDanhDau:
                if (imgDanhDau.getTag() == "0")
                {
                    imgDanhDau.setImageResource(R.drawable.success_click);
                    imgDanhDau.setTag("1");
                }
                else
                {
                    imgDanhDau.setImageResource(R.drawable.success);
                    imgDanhDau.setTag("0");
                }
                break;
            case  R.id.imgYeuThich:
                if (imgYeuThich.getTag() == "0")
                {
                    imgYeuThich.setImageResource(R.drawable.like_click);
                    imgYeuThich.setTag("1");
                }
                else
                {
                    imgYeuThich.setImageResource(R.drawable.like);
                    imgYeuThich.setTag("0");
                }
                break;
            case R.id.imgDanhDau:
                if (imgDanhDau.getTag() == "0")
                {
                    imgDanhDau.setImageResource(R.drawable.success_click);
                    imgDanhDau.setTag("1");
                }
                else
                {
                    imgDanhDau.setImageResource(R.drawable.success);
                    imgDanhDau.setTag("0");
                }
                break;
            case R.id.fabAdd:
                if(user!=null)
                {
                    Intent iAddImage= new Intent(ChiTietDanhLamActivity.this,PostBaiActivity.class);
                    iAddImage.putExtra("maDanhLam",danhLamThangCanhModel.getMadanhlam());
                    startActivity(iAddImage);

                }
                else  {
                    Toast.makeText(ChiTietDanhLamActivity.this,"Bạn Cần Đăng Nhập",Toast.LENGTH_SHORT).show();
                    Intent iDangNhap=new Intent(ChiTietDanhLamActivity.this,HomeLoginActivity.class);
                    startActivity(iDangNhap);

                }

                break;

        }
    }

}
