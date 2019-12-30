package com.example.DanhLam.Control;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import com.example.DanhLam.Model.DanhLamThangCanhModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class PostBaiController {
    public static int SELECT_PICTIRE_CALLBACK_CODE=1000;

    List<Uri> listImage;
    List<String> imagesEncodedList;
    FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
    DatabaseReference nootRoot=firebaseDatabase.getReference();

    FirebaseStorage firebaseStorage=FirebaseStorage.getInstance();
    StorageReference storageReference=  firebaseStorage.getReference();

    Context mContext;
    Activity mActivity;
    FirebaseUser mUser= FirebaseAuth.getInstance().getCurrentUser();

    public PostBaiController(Context mContext, Activity mActivity) {
        this.mContext = mContext;
        this.mActivity = mActivity;
    }

    /*public void NewPost(String _noiDung, String _tieuDe,String _diaChi, Double _kinhDo, Double _viDo)
    {
        if(listImage==null||listImage.size()==0)
        {
            Toast.makeText(mContext,"Bạn phải chọn ít nhất 1 ảnh!",Toast.LENGTH_LONG);

        }
        else
        {
            final DanhLamThangCanhModel danhLamThangCanhModel= new DanhLamThangCanhModel(_tieuDe,"",_diaChi,_noiDung,_kinhDo,_viDo);
            final DatabaseReference newPost= nootRoot.child("danhlamthangcanhs").push();
            String ma=newPost.getKey();
            danhLamThangCanhModel.setMadanhlam(ma);
//            newPost.setValue(danhLamThangCanhModel);
            final DatabaseReference newPostImage= nootRoot.child("hinhanhdanhlams").child(ma);
            final List<String> listNameFile= new ArrayList<>();
            for(int i=0;i<listImage.size();i++)
            {
                final String nameFile=ma+"_"+i+".jpg";
                StorageReference pictureStorage= storageReference.child("hinhdanhlam/"+nameFile);
                pictureStorage.putFile(listImage.get(i)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        listNameFile.add(nameFile);
                        if(listNameFile.size()==listImage.size());
                        {
                            newPost.setValue(danhLamThangCanhModel);
                            newPostImage.setValue(listNameFile);
                            Toast.makeText(mContext,"Đăng bài thành công!",Toast.LENGTH_LONG).show();

                        }

                    }
                });
            }


        }
    }*/

    public void NewPost(String maDLCT, List<Uri> hinhDanhLam)
    {
//        final DanhLamThangCanhModel danhLamThangCanhModel= new DanhLamThangCanhModel(_tieuDe,"",_diaChi,_noiDung,_kinhDo,_viDo);
//        final DatabaseReference newPost= nootRoot.child("danhlamthangcanhs").child(_noiDung).push();
//        String ma = newPost.getKey();
//        danhLamThangCanhModel.setMadanhlam(ma);
//        newPost.setValue(danhLamThangCanhModel);
//
//        for(Uri hinh : hinhDanhLam){
//            if (hinh != null) {
//                FirebaseStorage.getInstance().getReference().child("hinhdanhlam/"+hinh.getLastPathSegment()).putFile(hinh);
//                //Node hình quán ăn
//                nootRoot.child("hinhanhdanhlams").child(ma).push().setValue(hinh.getLastPathSegment());
//                Toast.makeText(mContext,"Đăng bài thành công!",Toast.LENGTH_LONG).show();
//            }
//            else
//            {
//                Toast.makeText(mContext,"Đăng bài thành công!",Toast.LENGTH_LONG).show();
//            }
//        }


        for(Uri hinh : hinhDanhLam){
            if (hinh != null) {
                FirebaseStorage.getInstance().getReference().child("hinhdanhlam/"+hinh.getLastPathSegment()).putFile(hinh);
                //Node hình danh lam
                nootRoot.child("hinhanhdanhlams").child(maDLCT).push().setValue(hinh.getLastPathSegment());
                Toast.makeText(mContext,"Thêm hình ảnh thành công!",Toast.LENGTH_LONG).show();
                FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                String UID=user.getUid();
                nootRoot.child("hinhanhdonggop").child(UID).push().setValue(hinh.getLastPathSegment());
            }
            else
            {
                //Toast.makeText(mContext,"Đăng bài thành công!",Toast.LENGTH_LONG).show();
            }
        }
    }

    /*public void SelectPicture()
    {
        Intent intent = new Intent();
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivity.startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTIRE_CALLBACK_CODE);
    }*/

    /*public void HandleCallBackActivitySelectPicture(int requestCode, int resultCode,Intent data)
    {
        if(requestCode==SELECT_PICTIRE_CALLBACK_CODE&&resultCode==Activity.RESULT_OK&&null!=data)
        {
            Log.d("pictureSelect",data.toString());
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            imagesEncodedList = new ArrayList<String>();

            if(data.getData()!=null)
            {
                Uri mImageUri=data.getData();

                Cursor cursor = mContext.getContentResolver().query(mImageUri,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imageEncoded  = cursor.getString(columnIndex);
                imagesEncodedList.add(imageEncoded);
                cursor.close();
            }
            else {
                if (data.getClipData() != null) {
                    ClipData mClipData = data.getClipData();
                    listImage = new ArrayList<Uri>();
                    for (int i = 0; i < mClipData.getItemCount(); i++) {

                        ClipData.Item item = mClipData.getItemAt(i);
                        Uri uri = item.getUri();
                        listImage.add(uri);
                        // Get the cursor
                        Cursor cursor = mContext.getContentResolver().query(uri, filePathColumn, null, null, null);
                        // Move to first row
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String imageEncoded  = cursor.getString(columnIndex);
                        imagesEncodedList.add(imageEncoded);
                        cursor.close();

                    }
                    Log.v("LOG_TAG", "Selected Images" + listImage.size());
                }
            }
        }
    }*/
}
