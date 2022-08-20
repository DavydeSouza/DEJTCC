package com.example.dejtcc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.dejtcc.Fragments.CartFragment;
import com.example.dejtcc.Fragments.HomeFragment;
import com.example.dejtcc.Fragments.ProfileFragments;
import com.example.dejtcc.Fragments.RequestFrament;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView menuzin;
    HomeFragment homeFragment = new HomeFragment();
    ProfileFragments profileFragments = new ProfileFragments();
    RequestFrament requestFrament = new RequestFrament();
    CartFragment cartFragment = new CartFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuzin = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
        menuzin.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        return true;
                    case R.id.pedidos:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, requestFrament).commit();
                        return true;
                    case R.id.perfil:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragments).commit();
                        return true;
                    case R.id.carrinho:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, cartFragment).commit();
                        return true;
                }
                return false;
            }
        });

    }
}