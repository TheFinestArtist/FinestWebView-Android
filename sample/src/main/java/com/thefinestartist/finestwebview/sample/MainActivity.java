package com.thefinestartist.finestwebview.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.thefinestartist.finestwebview.FinestWebView;

import java.io.Serializable;


public class MainActivity extends AppCompatActivity implements Serializable {

    private FinestWebView.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.defaultTheme) {
            new FinestWebView.Builder(this).titleDefault("The Finest Artist")
                    .show("http://thefinestartist.com");
            ////                    .toolbarScrollFlags(0)
            //                    .webViewJavaScriptEnabled(true)
            //                    .webViewUseWideViewPort(false)
            ////                    .show("http://andrewliu.in/2016/01/30/聊聊Redis的订阅发布/");
            //                    .show("http://www.youtube.com");
        } else if (view.getId() == R.id.redTheme) {
            //            Intent intent = new Intent(this, WebViewActivity.class);
            //            startActivity(intent);
            new FinestWebView.Builder(this).theme(R.style.RedTheme)
                    .titleDefault("Bless This Stuff")
                    .webViewBuiltInZoomControls(true)
                    .webViewDisplayZoomControls(true)
                    .dividerHeight(0)
                    .gradientDivider(false)
                    .webViewJavaScriptEnabled(true)//设置可以和JAVA交互
                    .addJavascriptInterface(new JsInteration())//JS将可以调用该类下面的@JavascriptInterface修饰的方法，此类须implements Serializable
                    .setCustomAnimations(R.anim.activity_open_enter, R.anim.activity_open_exit,
                            R.anim.activity_close_enter, R.anim.activity_close_exit)
                    .show("http://test.2000new.com/nyd/water/index.html");
        } else if (view.getId() == R.id.blueTheme) {
            builder = new FinestWebView.Builder(this).theme(R.style.FinestWebViewTheme)
                    .titleDefault("Vimeo")
                    .showUrl(false)
                    .statusBarColorRes(R.color.bluePrimaryDark)
                    .toolbarColorRes(R.color.bluePrimary)
                    .titleColorRes(R.color.finestWhite)
                    .urlColorRes(R.color.bluePrimaryLight)
                    .iconDefaultColorRes(R.color.finestWhite)
                    .progressBarColorRes(R.color.finestWhite)
//                    .webViewJavaScriptEnabled(true)//设置可以和JAVA交互
                    .addJavascriptInterface(this)//JS将可以调用该类下面的@JavascriptInterface修饰的方法，此类须implements Serializable
                    .stringResCopiedToClipboard(R.string.copied_to_clipboard)
                    .stringResCopiedToClipboard(R.string.copied_to_clipboard)
                    .stringResCopiedToClipboard(R.string.copied_to_clipboard)
                    .showSwipeRefreshLayout(true)
                    .swipeRefreshColorRes(R.color.bluePrimaryDark)
                    .menuSelector(R.drawable.selector_light_theme)
                    .menuTextGravity(Gravity.CENTER)
                    .menuTextPaddingRightRes(R.dimen.defaultMenuTextPaddingLeft)
                    .dividerHeight(0)
                    .gradientDivider(false)

                    .setCustomAnimations(R.anim.slide_up, R.anim.hold, R.anim.hold, R.anim.slide_down);
            builder.show("http://test.2000new.com/nyd/water/index.html");
        } else if (view.getId() == R.id.blackTheme) {
            new FinestWebView.Builder(this).theme(R.style.FinestWebViewTheme)
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
                    .menuTextGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT)
                    .menuTextPaddingRightRes(R.dimen.defaultMenuTextPaddingLeft)
                    .dividerHeight(0)
                    .gradientDivider(false)
                    //                    .setCustomAnimations(R.anim.slide_up, R.anim.hold, R.anim.hold, R.anim.slide_down)
                    .setCustomAnimations(R.anim.slide_left_in, R.anim.hold, R.anim.hold,
                            R.anim.slide_right_out)
                    //                    .setCustomAnimations(R.anim.fade_in_fast, R.anim.fade_out_medium, R.anim.fade_in_medium, R.anim.fade_out_fast)
                    .disableIconBack(true)
                    .disableIconClose(true)
                    .disableIconForward(true)
                    .disableIconMenu(true)
                    .show("https://dribbble.com");
        }
    }

    @JavascriptInterface
    public String jsToAndroid() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                builder.getWebView().evaluateJavascript("javascript:anroidToJs('安卓调用JS ')", null);//调用JAVA中的方法
            }
        });
        return "JS调用安卓";
    }

    class JsInteration implements Serializable {

        @JavascriptInterface
        public String jsToAndroid() {
            return "JS调用安卓";
        }
    }
}
