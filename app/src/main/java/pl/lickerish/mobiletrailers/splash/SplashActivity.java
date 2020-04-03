package pl.lickerish.mobiletrailers.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import pl.lickerish.mobiletrailers.MainActivity;
import pl.lickerish.mobiletrailers.R;

public class SplashActivity extends Activity {

    private final Long SPLASH_TIME = 1500L;
    private Handler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        myHandler = new Handler();
        myHandler.postDelayed(() -> {
            Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
            SplashActivity.this.startActivity(mainIntent);
            SplashActivity.this.finish();
        }, SPLASH_TIME);
    }
}
