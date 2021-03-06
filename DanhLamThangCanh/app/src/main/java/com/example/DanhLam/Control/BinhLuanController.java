package com.example.DanhLam.Control;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.DanhLam.Adapters.AdapterBinhLuan;
import com.example.DanhLam.Control.Interfaces.BinhLuanInterface;
import com.example.DanhLam.Model.BinhLuanCon;
import com.example.DanhLam.Model.BinhLuanModel;

import java.util.ArrayList;

public class BinhLuanController {
    String id;
    ArrayList<BinhLuanCon> danhsachBinhLuan= new ArrayList<>();
    RecyclerView recyclerView;
    AdapterBinhLuan adapterBinhLuan;
    int resource;
    Context mContext;
    BinhLuanModel binhLuanModel;
    public  BinhLuanController(RecyclerView recyclerView,Context mContext, String id, int resource)
    {
        this.mContext=mContext;
        this.recyclerView=recyclerView;
        this.id=id;
        binhLuanModel= new BinhLuanModel(id);
        this.resource=resource;
        this.recyclerView=recyclerView;
        adapterBinhLuan= new AdapterBinhLuan(mContext,resource,danhsachBinhLuan);
        recyclerView.setAdapter(adapterBinhLuan);
    }

    public void GetDanhSachBinhLuan()
    {
        BinhLuanInterface binhLuanInterface= new BinhLuanInterface() {
            @Override
            public void getDanhSachBinhLuan(BinhLuanCon binhLuanCon) {
                danhsachBinhLuan.add(binhLuanCon);
                adapterBinhLuan.notifyDataSetChanged();

            }
        };

        binhLuanModel.getDanhSachBinhLuan(binhLuanInterface);

    }
    public void RefreshBinhLuan() {
        danhsachBinhLuan.clear();
        GetDanhSachBinhLuan();
    }
}
