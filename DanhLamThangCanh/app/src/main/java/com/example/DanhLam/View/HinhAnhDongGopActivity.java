package com.example.DanhLam.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.DanhLam.Control.HinhAnhDongGopController;
import com.example.DanhLam.R;

public class HinhAnhDongGopActivity  extends AppCompatActivity {
    RecyclerView recyclerHinhAnh;
    HinhAnhDongGopController hinhAnhDongGopController;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danhsach_hinhanh_donggop);
        recyclerHinhAnh=findViewById(R.id.recyclerHinhAnh);
        hinhAnhDongGopController = new HinhAnhDongGopController(getApplicationContext(),recyclerHinhAnh);
        hinhAnhDongGopController.getDanhSachHinhAnhDongGopController(getApplicationContext(),recyclerHinhAnh);
    }
}
