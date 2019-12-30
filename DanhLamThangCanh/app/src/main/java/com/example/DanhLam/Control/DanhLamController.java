package com.example.DanhLam.Control;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.DanhLam.Adapters.AdapterDanhLamYeuThich;
import com.example.DanhLam.Adapters.AdapterRecyclerLoadDanhLam;
import com.example.DanhLam.Control.Interfaces.DanhLamInterface;
import com.example.DanhLam.Model.DanhLamThangCanhModel;
import com.example.DanhLam.R;

import java.util.ArrayList;
import java.util.List;

public class DanhLamController {
    public static int CALLBACK_LOGIN_CODE=99;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Context context;
    DanhLamThangCanhModel danhLamThangCanhModel;
    AdapterRecyclerLoadDanhLam adapterRecyclerLoadDanhLam;
    List<DanhLamThangCanhModel> danhLamThangCanhModelList = new ArrayList<>();
    public DanhLamController(Context context,RecyclerView recyclerView){
        this.context = context;
        danhLamThangCanhModel = new DanhLamThangCanhModel();
        layoutManager = new LinearLayoutManager(context);
        this.recyclerView=recyclerView;
        recyclerView.setLayoutManager(layoutManager);
        adapterRecyclerLoadDanhLam = new AdapterRecyclerLoadDanhLam(context, danhLamThangCanhModelList, R.layout.custom_layout_recyclerview_danhlam);
        recyclerView.setAdapter(adapterRecyclerLoadDanhLam);
    }

    public void getDanhSachDanhLamThangCanhController(Context context,RecyclerView recyclerOdau, boolean isQuery, String query){
        DanhLamInterface danhLamInterface = new DanhLamInterface() {
            @Override
            public void getDanhSachDanhLamThangCanh(DanhLamThangCanhModel danhLamThangCanhModel) {
                danhLamThangCanhModelList.add(danhLamThangCanhModel);
                adapterRecyclerLoadDanhLam.notifyDataSetChanged();
            }
        };
        if(isQuery)
        {
            danhLamThangCanhModelList.clear();
            adapterRecyclerLoadDanhLam.notifyDataSetChanged();
            danhLamThangCanhModel.getDanhSachDanhLamThangCanhwithQuery(danhLamInterface,query);

        }
        else{
            danhLamThangCanhModelList.clear();
            adapterRecyclerLoadDanhLam.notifyDataSetChanged();
            danhLamThangCanhModel.getDanhSachDanhLamThangCanh(danhLamInterface);
        }

    }



}
