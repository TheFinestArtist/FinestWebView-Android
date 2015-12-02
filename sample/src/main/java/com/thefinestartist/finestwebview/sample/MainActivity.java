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
                    .titleDefault("Bless This Stuff")
                    .toolbarScrollFlags(0)
                    .statusBarColorRes(R.color.redStatusBar)
                    .toolbarColorRes(R.color.redNavBar)
                    .titleColorRes(R.color.finestWhite)
                    .urlColorRes(R.color.redUrlText)
                    .iconDefaultColorRes(R.color.finestWhite)
                    .iconSelector(R.drawable.selector_white)
                    .progressBarColorRes(R.color.finestWhite)
                    .dividerHeight(0)
                    .gradientDivider(false)
                    .setCustomAnimations(R.anim.activity_open_enter, R.anim.activity_open_exit, R.anim.activity_close_enter, R.anim.activity_close_exit)
                    .show("http://www.blessthisstuff.com");
        } else if (view.getId() == R.id.blueTheme) {
            new FinestWebView.Builder(this)
                    .titleDefault("Vimeo")
                    .toolbarScrollFlags(0)
                    .statusBarColorRes(R.color.blueStatusBar)
                    .toolbarColorRes(R.color.blueNavBar)
                    .titleColorRes(R.color.finestWhite)
                    .urlColorRes(R.color.blueUrlText)
                    .iconDefaultColorRes(R.color.finestWhite)
                    .iconSelector(R.drawable.selector_white)
                    .progressBarColorRes(R.color.finestWhite)
                    .dividerHeight(0)
                    .gradientDivider(false)
                    .setCustomAnimations(R.anim.fragment_open_enter, R.anim.fragment_open_exit, R.anim.fragment_close_enter, R.anim.fragment_close_exit)
                    .show("https://vimeo.com");
        } else if (view.getId() == R.id.blackTheme) {
            new FinestWebView.Builder(this)
                    .titleDefault("Dribbble")
                    .toolbarScrollFlags(0)
                    .statusBarColorRes(R.color.blackStatusBar)
                    .toolbarColorRes(R.color.blackNavBar)
                    .titleColorRes(R.color.finestWhite)
                    .urlColorRes(R.color.blackUrlText)
                    .iconDefaultColorRes(R.color.finestWhite)
                    .iconSelector(R.drawable.selector_white)
                    .progressBarColorRes(R.color.finestWhite)
                    .dividerHeight(0)
                    .gradientDivider(false)
                    .show("https://dribbble.com");
        }
    }
}
