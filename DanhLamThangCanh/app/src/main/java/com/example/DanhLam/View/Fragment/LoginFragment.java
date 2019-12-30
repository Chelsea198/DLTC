package com.example.DanhLam.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.DanhLam.R;
import com.example.DanhLam.View.ProfileActivity;
import com.example.DanhLam.View.VerifyPhoneActivity;
import com.google.firebase.auth.FirebaseAuth;


public class LoginFragment extends Fragment {
    TextInputEditText editTextCountryCode, editTextPhone;
    AppCompatButton buttonContinue;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public LoginFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.activity_main,container,false);

        editTextCountryCode = view.findViewById(R.id.editTextCountryCode);
        editTextPhone = view.findViewById(R.id.editTextPhone);
        buttonContinue = view.findViewById(R.id.buttonContinue);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(getContext(),UserFragment.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editTextCountryCode.getText().toString().trim();
                String number = editTextPhone.getText().toString().trim();

                if (number.isEmpty() || number.length() <8) {
                    editTextPhone.setError("Valid number is required");
                    editTextPhone.requestFocus();
                    return;
                }

                String phoneNumber = code + number;

//                Intent intent = new Intent(getActivity(),VerifyFragment.class);
//                intent.putExtra("phoneNumber", phoneNumber);
//                startActivity(intent);
                VerifyFragment fr = new VerifyFragment();
                Bundle args = new Bundle();
                args.putString("phoneNumber",phoneNumber);

                fr.setArguments(args);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, fr);
                fragmentTransaction.commit();
            }
        });

    }
}
