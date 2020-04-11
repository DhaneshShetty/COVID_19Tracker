package com.example.covid_19tracker;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.covid_19tracker.Fragments.DashboardFragment;
import com.example.covid_19tracker.Fragments.HelplineFragment;
import com.example.covid_19tracker.Fragments.HomeFragment;
import com.example.covid_19tracker.Fragments.PrecautionsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Fragment homeFragment = new HomeFragment();
        final Fragment dashboardFragment = new DashboardFragment();
        final Fragment notificationsFragment = new PrecautionsFragment();
        final Fragment helplineFragment=new HelplineFragment();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        loadFragment(homeFragment);
                        return true;
                    case R.id.navigation_world:
                        loadFragment(dashboardFragment);
                        return true;
                    case R.id.navigation_precautions:
                        loadFragment(notificationsFragment);
                        return true;
                    case R.id.navigation_helpline:
                        loadFragment(helplineFragment);
                        return true;
                }
                return false;

            }
        });
    }

    private void loadFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment,fragment)
                .commit();
    }


}
