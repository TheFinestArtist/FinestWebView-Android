package com.thefinestartist.finestwebview;

import android.app.Activity;
import android.webkit.WebView;

/**
 * Created by Administrator on 2017/2/17.
 */

public  class BaseJsInteration {

    protected Activity instance;
    protected WebView webView;

    public  void setContext(Activity activity){
        instance=activity;
    }
    public  Activity getContext(){
        return instance;
    }

    public WebView getWebView() {
        return webView;
    }

    public void setWebView(WebView webView) {
        this.webView = webView;
    }
}
