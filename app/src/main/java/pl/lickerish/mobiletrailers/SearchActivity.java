package pl.lickerish.mobiletrailers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import pl.lickerish.mobiletrailers.fragments.HomeFragment;
import pl.lickerish.mobiletrailers.fragments.SearchFragment;
import pl.lickerish.mobiletrailers.fragments.SearchFragmentContainer;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchFragmentContainer()).commit();
        setOnNavigationListener();

    }

    private void setOnNavigationListener() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_search);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(menuItem -> {

            switch (menuItem.getItemId()) {
                case R.id.nav_home: {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0, 0);
                    break;
                }
                case R.id.nav_search: {
                    return true;
                }
                case R.id.nav_about: {
                    startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                    overridePendingTransition(0, 0);
                    break;
                }
            }
            return true;
        });
    }
}
