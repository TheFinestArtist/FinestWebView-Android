package com.thefinestartist.finestwebview.sample

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by TheFinestArtist on 3/11/16.
 */
class WebViewActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.webview)

        val url = "http://docs.google.com/gview?embedded=true&url=uet.vnu.edu.vn/~chauttm/e-books/java/Effective.Java.2nd.Edition.May.2008.3000th.Release.pdf"
        val webView = findViewById<View>(R.id.webView) as WebView

        webView.settings.javaScriptEnabled = true
        webView.loadUrl(url)
    }

}