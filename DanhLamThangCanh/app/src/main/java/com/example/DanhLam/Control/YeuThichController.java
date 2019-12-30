package com.example.DanhLam.Control;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.DanhLam.Adapters.AdapterDanhLamYeuThich;
import com.example.DanhLam.Control.Interfaces.DanhLamInterface;
import com.example.DanhLam.Model.DanhLamThangCanhModel;
import com.example.DanhLam.R;

import java.util.ArrayList;
import java.util.List;

public class YeuThichController {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Context context;
    DanhLamThangCanhModel danhLamThangCanhModel;
    AdapterDanhLamYeuThich adapterDanhLamYeuThich;
    List<DanhLamThangCanhModel> danhLamThangCanhModelList = new ArrayList<>();
    public YeuThichController(Context context,RecyclerView recyclerView){
        this.context = context;
        danhLamThangCanhModel = new DanhLamThangCanhModel();
        layoutManager = new LinearLayoutManager(context);
        this.recyclerView=recyclerView;
        recyclerView.setLayoutManager(layoutManager);
        adapterDanhLamYeuThich = new AdapterDanhLamYeuThich(context, danhLamThangCanhModelList, R.layout.custom_layout_recyclerview_danhlamyeuthich);
        recyclerView.setAdapter(adapterDanhLamYeuThich);
    }
    public  void LoadDanhSachDanhLamYeuThichController(Context context,RecyclerView recyclerViewDanhLamYeuThich)
    {
        DanhLamInterface danhLamInterface=new DanhLamInterface() {
            @Override
            public void getDanhSachDanhLamThangCanh(DanhLamThangCanhModel danhLamThangCanhModel) {
                danhLamThangCanhModelList.add(danhLamThangCanhModel);
                adapterDanhLamYeuThich.notifyDataSetChanged();
            }
        } ;
        danhLamThangCanhModelList.clear();
        adapterDanhLamYeuThich.notifyDataSetChanged();
        danhLamThangCanhModel.getDanhLamYeuThich(danhLamInterface);
    }
}
