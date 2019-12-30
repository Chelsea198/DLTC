package com.example.DanhLam.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import com.example.DanhLam.Control.YeuThichController;
import com.example.DanhLam.R;

public class DanhLamYeuThichActivity extends AppCompatActivity {
    RecyclerView recyclerViewYeuThich;

    YeuThichController yeuThichController;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danhlam_yeuthich);


        recyclerViewYeuThich=findViewById(R.id.recyclerDanhLamYeuThich);

    }

    @Override
    protected void onStart() {
        super.onStart();
        yeuThichController=new YeuThichController(getApplicationContext(),recyclerViewYeuThich);
        yeuThichController.LoadDanhSachDanhLamYeuThichController(getApplicationContext(),recyclerViewYeuThich);
    }
}
