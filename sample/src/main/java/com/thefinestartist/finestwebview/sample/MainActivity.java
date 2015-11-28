package com.thefinestartist.finestwebview.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.thefinestartist.finestwebview.FinestWebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.defaultTheme) {
            new FinestWebView.Builder(this)
                    .titleDefault("Loading...")
                    .show("http://thefinestartist.com");
            overridePendingTransition(R.anim.modal_activity_open_enter, R.anim.modal_activity_open_exit);
        } else if (view.getId() == R.id.redTheme) {
            new FinestWebView.Builder(this)
                    .titleDefault("Red Theme")
                    .show("http://www.blessthisstuff.com");
            overridePendingTransition(R.anim.modal_activity_open_enter, R.anim.modal_activity_open_exit);
        } else if (view.getId() == R.id.blueTheme) {
            new FinestWebView.Builder(this).show("https://vimeo.com");
            overridePendingTransition(R.anim.modal_activity_open_enter, R.anim.modal_activity_open_exit);
        } else if (view.getId() == R.id.blackTheme) {
            new FinestWebView.Builder(this)
                    .titleDefault("Dribbble")
                    .show("https://dribbble.com");
            overridePendingTransition(R.anim.modal_activity_open_enter, R.anim.modal_activity_open_exit);
        }
    }
}
