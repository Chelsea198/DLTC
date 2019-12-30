package com.example.DanhLam.Control;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.DanhLam.Adapters.AdapterDanhLamYeuThich;
import com.example.DanhLam.Adapters.AdapterHinhAnhDongGop;
import com.example.DanhLam.Control.Interfaces.HinhAnhDongGopInterface;
import com.example.DanhLam.Model.DanhLamThangCanhModel;
import com.example.DanhLam.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HinhAnhDongGopController {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Context context;
    AdapterHinhAnhDongGop adapterHinhAnhDongGop;
    List<String>  listHinhAnhDongGop = new ArrayList<>();
    public HinhAnhDongGopController(Context context,RecyclerView recyclerView){
        this.context = context;
        layoutManager = new LinearLayoutManager(context);
        this.recyclerView=recyclerView;
        recyclerView.setLayoutManager(layoutManager);
        adapterHinhAnhDongGop = new AdapterHinhAnhDongGop(context, listHinhAnhDongGop, R.layout.custom_layout_hinhanh_donggop);
        recyclerView.setAdapter(adapterHinhAnhDongGop);
    }
    public  void getDanhSachHinhAnhDongGop(final HinhAnhDongGopInterface hinhAnhDongGopInterface)
    {
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null)
        {
            String UID= user.getUid();
            DatabaseReference check= FirebaseDatabase.getInstance().getReference().child("hinhanhdonggop").child(UID);
            check.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                    {
                        String index=dataSnapshot1.getValue(String.class);
                        hinhAnhDongGopInterface.getDanhSachHinhAnh(index);
                    }

                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    public  void getDanhSachHinhAnhDongGopController(Context context,RecyclerView recyclerViewHinhAnhDongGop) {
        HinhAnhDongGopInterface hinhAnhDongGopInterface=new HinhAnhDongGopInterface() {
            @Override
            public void getDanhSachHinhAnh(String maHinhAnh) {
                listHinhAnhDongGop.add(maHinhAnh);
                adapterHinhAnhDongGop.notifyDataSetChanged();
            }
        };
        listHinhAnhDongGop.clear();
        adapterHinhAnhDongGop.notifyDataSetChanged();
        getDanhSachHinhAnhDongGop(hinhAnhDongGopInterface);

    }
}

