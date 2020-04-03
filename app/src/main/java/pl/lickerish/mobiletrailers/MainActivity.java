package pl.lickerish.mobiletrailers;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import pl.lickerish.mobiletrailers.fragments.AboutFragment;
import pl.lickerish.mobiletrailers.fragments.HomeFragment;
import pl.lickerish.mobiletrailers.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        setOnNavigationListener();
    }

    private void setOnNavigationListener() {
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(menuItem -> {
            Fragment selectedFragment = null;
            switch (menuItem.getItemId()) {
                case R.id.nav_home: {
                    selectedFragment = new HomeFragment();
                    break;
                }
                case R.id.nav_search: {
                    selectedFragment = new SearchFragment();
                    break;
                }
                case R.id.nav_about: {
                    selectedFragment = new AboutFragment();
                    break;
                }
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        });
    }
}
