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
                    .titleDefault("The Finest Artist")
                    .show("http://thefinestartist.com");
        } else if (view.getId() == R.id.redTheme) {
            new FinestWebView.Builder(this)
                    .theme(R.style.FinestWebViewTheme)
                    .titleDefault("Bless This Stuff")
                    .statusBarColorRes(R.color.redPrimaryDark)
                    .toolbarColorRes(R.color.redPrimary)
                    .titleColorRes(R.color.finestWhite)
                    .urlColorRes(R.color.redPrimaryLight)
                    .iconDefaultColorRes(R.color.finestWhite)
                    .progressBarColorRes(R.color.finestWhite)
                    .webViewBuiltInZoomControls(true)
                    .webViewDisplayZoomControls(true)
                    .swipeRefreshColorRes(R.color.redPrimaryDark)
                    .menuColorRes(R.color.redPrimaryDark)
                    .menuTextColorRes(R.color.finestWhite)
                    .dividerHeight(0)
                    .gradientDivider(false)
                    .setCustomAnimations(R.anim.activity_open_enter, R.anim.activity_open_exit, R.anim.activity_close_enter, R.anim.activity_close_exit)
                    .show("http://www.blessthisstuff.com");
        } else if (view.getId() == R.id.blueTheme) {
            new FinestWebView.Builder(this)
                    .theme(R.style.FinestWebViewTheme)
                    .titleDefault("Vimeo")
                    .toolbarScrollFlags(0)
                    .statusBarColorRes(R.color.bluePrimaryDark)
                    .toolbarColorRes(R.color.bluePrimary)
                    .titleColorRes(R.color.finestWhite)
                    .urlColorRes(R.color.bluePrimaryLight)
                    .iconDefaultColorRes(R.color.finestWhite)
                    .progressBarColorRes(R.color.finestWhite)
                    .showSwipeRefreshLayout(true)
                    .swipeRefreshColorRes(R.color.bluePrimaryDark)
                    .menuSelector(R.drawable.selector_light_theme)
                    .dividerHeight(0)
                    .gradientDivider(false)
                    .setCustomAnimations(R.anim.slide_up, R.anim.hold, R.anim.hold, R.anim.slide_down)
                    .show("https://vimeo.com");
        } else if (view.getId() == R.id.blackTheme) {
            new FinestWebView.Builder(this)
                    .theme(R.style.FinestWebViewTheme)
                    .titleDefault("Dribbble")
                    .toolbarScrollFlags(0)
                    .statusBarColorRes(R.color.blackPrimaryDark)
                    .toolbarColorRes(R.color.blackPrimary)
                    .titleColorRes(R.color.finestWhite)
                    .urlColorRes(R.color.blackPrimaryLight)
                    .iconDefaultColorRes(R.color.finestWhite)
                    .progressBarColorRes(R.color.finestWhite)
                    .swipeRefreshColorRes(R.color.blackPrimaryDark)
                    .menuSelector(R.drawable.selector_light_theme)
                    .dividerHeight(0)
                    .gradientDivider(false)
//                    .setCustomAnimations(R.anim.slide_up, R.anim.hold, R.anim.hold, R.anim.slide_down)
                    .setCustomAnimations(R.anim.slide_left_in, R.anim.hold, R.anim.hold, R.anim.slide_right_out)
//                    .setCustomAnimations(R.anim.fade_in_fast, R.anim.fade_out_medium, R.anim.fade_in_medium, R.anim.fade_out_fast)
                    .show("https://dribbble.com");
        }
    }
}
