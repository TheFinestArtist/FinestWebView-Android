package com.thefinestartist.finestwebview.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.thefinestartist.finestwebview.FinestWebViewActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.show)
    public void show() {
        new FinestWebViewActivity.Builder(this)
                .titleDefault("FinestWebView")
                .backPressToClose(true)
                .show("https://medium.com/@kollinz");
    }
}
