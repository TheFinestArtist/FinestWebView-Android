package com.thefinestartist.finestwebview.sample;

import android.content.Intent;
import android.webkit.JavascriptInterface;

import com.thefinestartist.finestwebview.BaseJsInteration;
import com.thefinestartist.utils.log.L;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/17.
 */

public class JsInteration extends BaseJsInteration implements Serializable {


    @JavascriptInterface
    public void toActivity() {
        Intent intent = new Intent(instance, TestActivity.class);
        instance.startActivity(intent);
    }

    @JavascriptInterface
    public String jsToAndroid() {
        instance.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //这是安卓调用JS中的方法，JS中必须声明了对应androidToJs(param)函数，第二个参数为回调，如果你需要该方法的返回值就加上回调接口
                //该方法必须在UIThread中调用。如果你是在Fragment中可以用getActivity.runOnUiThread
                if(webView!=null){
                    webView.evaluateJavascript("javascript:anroidToJs('Android invok JS ')", null);
                }else{
                    L.e("webview is null");
                }
            }
        });
        return "JS invok Android";
    }
}
