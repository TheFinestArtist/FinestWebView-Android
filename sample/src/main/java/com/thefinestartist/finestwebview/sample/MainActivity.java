package com.thefinestartist.finestwebview.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.thefinestartist.finestwebview.FinestWebView;
import com.thefinestartist.finestwebview.helpers.DipPixelHelper;

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
        new FinestWebView.Builder(this)
                .titleDefault("FinestWebView")
                .backPressToClose(true)
                .dividerHeight(100)
//                .toolbarColorRes(R.color.accent)
                .gradientDivider(true)
                .dividerColorRes(R.color.black_30)
                .iconDefaultColorRes(R.color.accent)
                .progressBarHeight(DipPixelHelper.getPixel(this, 3))
                .progressBarColorRes(R.color.accent)
                .backPressToClose(false)
                .show("https://medium.com/@kollinz");
        overridePendingTransition(R.anim.modal_activity_open_enter, R.anim.modal_activity_open_exit);
    }
}
