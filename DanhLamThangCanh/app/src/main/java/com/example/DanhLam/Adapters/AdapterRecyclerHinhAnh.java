package com.example.DanhLam.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.DanhLam.R;
import com.example.DanhLam.View.FullScreenListImageView;

import java.util.ArrayList;

public class AdapterRecyclerHinhAnh extends RecyclerView.Adapter<AdapterRecyclerHinhAnh.ViewHinhAnh> {

    Context context;
    int resource;
    ArrayList<String> listHinh;
    ArrayList<Bitmap> bitmapList;
    public  AdapterRecyclerHinhAnh(  Context context,int resource, ArrayList<String> listHinh, ArrayList<Bitmap> bitmapList)
    {
        this.context=context;
        this.resource=resource;
        this.listHinh=listHinh;
        this.bitmapList=bitmapList;
    }
    public class ViewHinhAnh extends RecyclerView.ViewHolder {
        ImageView imgHinhAnhDep;
        public ViewHinhAnh(@NonNull View itemView) {
            super(itemView);
            imgHinhAnhDep=itemView.findViewById(R.id.imgHinhanhDep);
        }
    }
    @NonNull
    @Override
    public ViewHinhAnh onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);
        ViewHinhAnh viewHinhAnh=new ViewHinhAnh(view);
        return  viewHinhAnh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHinhAnh viewHinhAnh, int i) {

        viewHinhAnh.imgHinhAnhDep.setImageBitmap(bitmapList.get(i));
        viewHinhAnh.imgHinhAnhDep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageIntent= new Intent(context, FullScreenListImageView.class);
                imageIntent.putExtra("imageList", listHinh);
                context.startActivity(imageIntent);

            }
        });


    }
    @Override
    public int getItemCount() {
        return listHinh.size();
    }

}
