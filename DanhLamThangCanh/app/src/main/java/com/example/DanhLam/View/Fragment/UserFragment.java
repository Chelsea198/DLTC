package com.example.DanhLam.View.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.DanhLam.Control.Interfaces.DanhLamInterface;
import com.example.DanhLam.Model.DanhLamThangCanhModel;
import com.example.DanhLam.R;
import com.example.DanhLam.View.DanhLamYeuThichActivity;
import com.example.DanhLam.View.HomeLoginActivity;
import com.example.DanhLam.View.ProfileActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class UserFragment extends Fragment {
    String phoneNumber;
    TextView mobileNumber;
    public UserFragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_user,container,false);
        SharedPreferences prefs =  view.getContext().getSharedPreferences("USER_PREF",
                Context.MODE_PRIVATE);
        phoneNumber = prefs.getString("phoneNumber", NULL);

        mobileNumber = view.findViewById(R.id.mobileNumber);
        mobileNumber.setText(phoneNumber);

        view.findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

//                Intent intent = new Intent(getActivity(), LoginFragment.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
                LoginFragment fr = new LoginFragment();
                Bundle args = new Bundle();
                fr.setArguments(args);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, fr);
                fragmentTransaction.commit();
            }
        });
        view.findViewById(R.id.btnDanhLamYeuThich).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iYeuThich=new Intent(view.getContext(), DanhLamYeuThichActivity.class);
                startActivity(iYeuThich);
            }
        });
        return view;
    }

}
