package com.example.DanhLam.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.DanhLam.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ManagerFragment  extends AppCompatActivity {
    private Fragment fragment;
    private FragmentManager fragmentManager;
    FirebaseUser user;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_navigation_bottom);
        fragmentManager= getSupportFragmentManager();
        fragment= new HomeFragment();
        final FragmentTransaction transaction= fragmentManager.beginTransaction();
        transaction.add(R.id.frame_container,fragment).commit();
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.navigation_home:
                        fragment=new HomeFragment();
                        break;
                    case R.id.navigation_user:
                        user= FirebaseAuth.getInstance().getCurrentUser();
                        if(user==null)
                        {
                            fragment = new LoginFragment();
                        }
                        else
                            fragment = new UserFragment();
                        break;

                }
                final FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_container,fragment).commit();

                return true;
            }
        });
    }
}
