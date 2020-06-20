package com.bagusf.babeoapss;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bagusf.babeoapss.fragment.Fragment_akun;
import com.bagusf.babeoapss.fragment.Fragment_kategori;
import com.bagusf.babeoapss.fragment.Fragment_notifikasi;
import com.bagusf.babeoapss.fragment.fragment_keranjang;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.luseen.spacenavigation.SpaceNavigationView;

public class JavaNavigation extends AppCompatActivity {
    SpaceNavigationView spaceNavigationView;
    private Fragment_home fragmentHome;
    private Fragment_akun fragmentAkun;
    private BottomNavigationView bottomNavigationView;
    private Fragment fragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView);
        bottomNavigationView.inflateMenu(R.menu.menu_bottom_nav);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.Frame_Home, new Fragment_home()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.Homes:
                        fragment = new Fragment_home();
                        break;
                    case R.id.kategori:
                        fragment = new Fragment_kategori();
                        break;
                    case R.id.notifikasi:
                        fragment = new fragment_keranjang();
                        break;
                    case R.id.akun:
                        fragment = new Fragment_akun();
                        break;
                }
                final FragmentTransaction transaction= fragmentManager.beginTransaction();
                transaction.replace(R.id.Frame_Home,fragment).commit();
                return true;
            }
        });
//
//        fragmentHome = new Fragment_home();
//        fragmentAkun = new Fragment_akun();
//
//        spaceNavigationView = findViewById(R.id.space);
//        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
//        spaceNavigationView.showIconOnly();
//
//        spaceNavigationView.addSpaceItem(new SpaceItem("shopping", R.drawable.ic_shopping_basket_black_24dp));
//        spaceNavigationView.addSpaceItem(new SpaceItem("account", R.drawable.ic_account_circle_black_24dp));
//        spaceNavigationView.addSpaceItem(new SpaceItem("home", R.drawable.ic_home_black_24dp));
//        spaceNavigationView.addSpaceItem(new SpaceItem("SEARCH", R.drawable.ic_account_circle_black_24dp));
//
//        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
//            @Override
//            public void onCentreButtonClick() {
//                setfragment(fragmentHome);
//            }
//
//            @Override
//            public void onItemClick(int itemIndex, String itemName) {
//                switch (itemIndex){
//                    case 0:
//                        setfragment(fragmentAkun);
//                        return;
//                    case 1:
//                        setfragment(fragmentHome);
//                        return;
//                    default:
//                            setfragment(fragmentHome);
//
//                }
//
//            }
//
//            @Override
//            public void onItemReselected(int itemIndex, String itemName) {
//
//            }
//        });
//    }
//    private void setfragment(Fragment fragment){
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.container_home, fragment);
//        fragmentTransaction.commit();
//    }
    }
}
