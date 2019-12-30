package com.example.DanhLam.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.DanhLam.Model.DanhLamThangCanhModel;
import com.example.DanhLam.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class AdapterHinhAnhDongGop extends RecyclerView.Adapter<AdapterHinhAnhDongGop.ViewHolderHinhAnh> {

    List<String> listHinhAnhDongGop;
    int resource;
    Context context;
    public AdapterHinhAnhDongGop(Context context,List<String> listHinhAnhDongGop, int resource) {
        this.listHinhAnhDongGop = listHinhAnhDongGop;
        this.resource = resource;
        this.context=context;
    }
    public class ViewHolderHinhAnh extends RecyclerView.ViewHolder {
        ImageView imgHinhAnhDanhLam;
        public ViewHolderHinhAnh(@NonNull View itemView) {
            super(itemView);
            imgHinhAnhDanhLam=itemView.findViewById(R.id.imgHinhAnhDongGop);

        }
    }
    @NonNull
    @Override
    public AdapterHinhAnhDongGop.ViewHolderHinhAnh onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);
        ViewHolderHinhAnh viewHolderHinhAnh = new ViewHolderHinhAnh(view);

        return viewHolderHinhAnh;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterHinhAnhDongGop.ViewHolderHinhAnh viewHolderHinhAnh, int i) {
        if (listHinhAnhDongGop.size() > 0) {
            String linkHinhANh = listHinhAnhDongGop.get(i);
            StorageReference storageHinhAnh = FirebaseStorage.getInstance().getReference().child("hinhdanhlam").child(linkHinhANh);
            long ONE_MEGABYTE = 5 * 1024 * 1024;
            storageHinhAnh.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    viewHolderHinhAnh.imgHinhAnhDanhLam.setImageBitmap(bitmap);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listHinhAnhDongGop.size();
    }


}
