package com.example.DanhLam.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.DanhLam.Adapters.AdapterRecyclerLoadDanhLam;
import com.example.DanhLam.Control.DanhLamController;
import com.example.DanhLam.Model.DanhLamThangCanhModel;
import com.example.DanhLam.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment{
    SearchView searchView;
    DanhLamController danhLamController;
    RecyclerView recyclerViewdata;
    Context context;

    public HomeFragment()
    {

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_home,container,false);
        context=rootView.getContext();
        recyclerViewdata=rootView.findViewById(R.id.recyclerDanhLam);
        searchView=rootView.findViewById(R.id.searchView);
        danhLamController = new DanhLamController(context,recyclerViewdata);
        danhLamController.getDanhSachDanhLamThangCanhController(context, recyclerViewdata, false,"");
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchView.clearFocus();
                if(!s.equals(""))
                {
                    Log.d("appcheck","query: "+s);
                    danhLamController.getDanhSachDanhLamThangCanhController(context,recyclerViewdata,true, s);

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
                danhLamController.getDanhSachDanhLamThangCanhController(context, recyclerViewdata, false,"");
                return false;
            }
        });

    }
}
