package com.thefinestartist.finestwebview.sample

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.thefinestartist.finestwebview.FinestWebView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.defaultTheme -> {
                FinestWebView.Builder(this)
                        .titleDefault("Awwwards")
                        .show("https://www.awwwards.com/")
            }
            R.id.redTheme -> {
                FinestWebView.Builder(this)
                        .theme(R.style.RedTheme)
                        .titleDefault("GitHub")
                        .webViewBuiltInZoomControls(true)
                        .webViewDisplayZoomControls(true)
                        .dividerHeight(0)
                        .gradientDivider(false)
                        .setCustomAnimations(
                                R.anim.activity_open_enter,
                                R.anim.activity_open_exit,
                                R.anim.activity_close_enter,
                                R.anim.activity_close_exit
                        )
                        .injectJavaScript("""
                            javascript: document
                                .getElementById('msg')
                                .innerHTML='Hello TheFinestArtist!';
                        """.trimIndent())
                        .show("https://github.com/")
            }
            R.id.blueTheme -> {
                FinestWebView.Builder(this).theme(R.style.FinestWebViewTheme)
                        .titleDefault("Google Translate")
                        .showUrl(false)
                        .statusBarColorRes(R.color.bluePrimaryDark)
                        .toolbarColorRes(R.color.bluePrimary)
                        .titleColorRes(R.color.finestWhite)
                        .urlColorRes(R.color.bluePrimaryLight)
                        .iconDefaultColorRes(R.color.finestWhite)
                        .progressBarColorRes(R.color.finestWhite)
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
                        .setCustomAnimations(R.anim.slide_up, R.anim.hold, R.anim.hold, R.anim.slide_down)
                        .show("https://translate.google.com/")
            }
            R.id.blackTheme -> {
                FinestWebView.Builder(this).theme(R.style.FinestWebViewTheme)
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
                        .menuTextGravity(Gravity.CENTER_VERTICAL or Gravity.END)
                        .menuTextPaddingRightRes(R.dimen.defaultMenuTextPaddingLeft)
                        .dividerHeight(0)
                        .gradientDivider(false)
                        .setCustomAnimations(
                                R.anim.slide_left_in,
                                R.anim.hold,
                                R.anim.hold,
                                R.anim.slide_right_out
                        )
                        .disableIconBack(true)
                        .disableIconClose(true)
                        .disableIconForward(true)
                        .disableIconMenu(true)
                        .show("https://dribbble.com")
            }
            R.id.noToolbarTheme -> {
                FinestWebView.Builder(this)
                        .titleDefault("Awwwards")
                        .showToolbar(false)
                        .show("https://www.awwwards.com/")
            }
        }
    }
}