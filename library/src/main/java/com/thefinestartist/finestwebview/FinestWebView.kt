package com.thefinestartist.finestwebview

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.webkit.WebSettings.*
import androidx.annotation.AnimRes
import androidx.annotation.ArrayRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.AppBarLayout.LayoutParams.*
import com.thefinestartist.finestwebview.enums.ProgressBarPosition
import com.thefinestartist.finestwebview.listeners.BroadCastManager
import com.thefinestartist.finestwebview.listeners.WebViewListener
import java.io.Serializable
import java.util.*

/** Created by Leonardo on 11/21/15.  */
data class FinestWebView(
  @Transient var context: Context,
  @Transient var listeners: MutableList<WebViewListener> = ArrayList(),

  var key: Int? = null,

  var rtl: Boolean? = null,
  var theme: Int? = null,

  var statusBarColor: Int? = null,

  var toolbarColor: Int? = null,
  var toolbarScrollFlags: Int? = null,

  var iconDefaultColor: Int? = null,
  var iconDisabledColor: Int? = null,
  var iconPressedColor: Int? = null,
  var iconSelector: Int? = null,

  var showIconClose: Boolean? = null,
  var disableIconClose: Boolean? = null,
  var showIconBack: Boolean? = null,
  var disableIconBack: Boolean? = null,
  var showIconForward: Boolean? = null,
  var disableIconForward: Boolean? = null,
  var showIconMenu: Boolean? = null,
  var disableIconMenu: Boolean? = null,

  var showSwipeRefreshLayout: Boolean? = null,
  var swipeRefreshColor: Int? = null,
  var swipeRefreshColors: IntArray? = null,

  var showDivider: Boolean? = null,
  var gradientDivider: Boolean? = null,
  var dividerColor: Int? = null,
  var dividerHeight: Float? = null,

  var showProgressBar: Boolean? = null,
  var progressBarColor: Int? = null,
  var progressBarHeight: Float? = null,
  var progressBarPosition: ProgressBarPosition? = null,

  var titleDefault: String? = null,
  var updateTitleFromHtml: Boolean? = null,
  var titleSize: Float? = null,
  var titleFont: String? = null,
  var titleColor: Int? = null,

  var showUrl: Boolean? = null,
  var urlSize: Float? = null,
  var urlFont: String? = null,
  var urlColor: Int? = null,

  var menuColor: Int? = null,
  var menuDropShadowColor: Int? = null,
  var menuDropShadowSize: Float? = null,
  var menuSelector: Int? = null,

  var menuTextSize: Float? = null,
  var menuTextFont: String? = null,
  var menuTextColor: Int? = null,

  var menuTextGravity: Int? = null,
  var menuTextPaddingLeft: Float? = null,
  var menuTextPaddingRight: Float? = null,

  var showMenuRefresh: Boolean? = null,
  var stringResRefresh: Int? = null,
  var showMenuFind: Boolean? = null,
  var stringResFind: Int? = null,
  var showMenuShareVia: Boolean? = null,
  var stringResShareVia: Int? = null,
  var showMenuCopyLink: Boolean? = null,
  var stringResCopyLink: Int? = null,
  var showMenuOpenWith: Boolean? = null,
  var stringResOpenWith: Int? = null,

  var animationOpenEnter: Int = R.anim.modal_activity_open_enter,
  var animationOpenExit: Int = R.anim.modal_activity_open_exit,
  var animationCloseEnter: Int? = null,
  var animationCloseExit: Int? = null,

  var backPressToClose: Boolean? = null,
  var stringResCopiedToClipboard: Int? = null,

  var webViewSupportZoom: Boolean? = null,
  var webViewMediaPlaybackRequiresUserGesture: Boolean? = null,
  var webViewBuiltInZoomControls: Boolean? = null,
  var webViewDisplayZoomControls: Boolean? = null,
  var webViewAllowFileAccess: Boolean? = null,
  var webViewAllowContentAccess: Boolean? = null,
  var webViewLoadWithOverviewMode: Boolean? = null,
  var webViewSaveFormData: Boolean? = null,
  var webViewTextZoom: Int? = null,
  var webViewUseWideViewPort: Boolean? = null,
  var webViewSupportMultipleWindows: Boolean? = null,
  var webViewLayoutAlgorithm: LayoutAlgorithm? = null,
  var webViewStandardFontFamily: String? = null,
  var webViewFixedFontFamily: String? = null,
  var webViewSansSerifFontFamily: String? = null,
  var webViewSerifFontFamily: String? = null,
  var webViewCursiveFontFamily: String? = null,
  var webViewFantasyFontFamily: String? = null,
  var webViewMinimumFontSize: Int? = null,
  var webViewMinimumLogicalFontSize: Int? = null,
  var webViewDefaultFontSize: Int? = null,
  var webViewDefaultFixedFontSize: Int? = null,
  var webViewLoadsImagesAutomatically: Boolean? = null,
  var webViewBlockNetworkImage: Boolean? = null,
  var webViewBlockNetworkLoads: Boolean? = null,
  var webViewJavaScriptEnabled: Boolean? = null,
  var webViewAllowUniversalAccessFromFileURLs: Boolean? = null,
  var webViewAllowFileAccessFromFileURLs: Boolean? = null,
  var webViewGeolocationDatabasePath: String? = null,
  var webViewAppCacheEnabled: Boolean? = null,
  var webViewAppCachePath: String? = null,
  var webViewDatabaseEnabled: Boolean? = null,
  var webViewDomStorageEnabled: Boolean? = null,
  var webViewGeolocationEnabled: Boolean? = null,
  var webViewJavaScriptCanOpenWindowsAutomatically: Boolean? = null,
  var webViewDefaultTextEncodingName: String? = null,
  var webViewUserAgentString: String? = null,
  var webViewNeedInitialFocus: Boolean? = null,
  var webViewCacheMode: Int? = null,
  var webViewMixedContentMode: Int? = null,
  var webViewOffscreenPreRaster: Boolean? = null,

  var injectJavaScript: String? = null,

  var mimeType: String? = null,
  var encoding: String? = null,
  var data: String? = null,
  var url: String? = null,
) : Serializable {

  constructor(ctx: Context) : this(context = ctx)

  /**
   * If you use context instead of activity, FinestWebView won't be able to override activity
   * animation. Try to create builder with Activity if it's possible.
   */
  constructor(activity: Activity) : this(context = activity)

  fun setWebViewListener(listener: WebViewListener) = apply {
    listeners.clear()
    listeners.add(listener)
  }

  fun addWebViewListener(listener: WebViewListener) = apply { listeners.add(listener) }
  fun removeWebViewListener(listener: WebViewListener) = apply { listeners.remove(listener) }

  fun rtl(rtl: Boolean) = apply { this.rtl = rtl }

  fun theme(@StyleRes theme: Int) = apply { this.theme = theme }

  fun statusBarColor(@ColorInt color: Int) = apply { this.statusBarColor = color }
  fun statusBarColorRes(@ColorRes colorRes: Int) = apply { this.statusBarColor = ContextCompat.getColor(context, colorRes) }

  fun toolbarColor(@ColorInt color: Int) = apply { this.toolbarColor = color }
  fun toolbarColorRes(@ColorRes colorRes: Int) = apply { this.toolbarColor = ContextCompat.getColor(context, colorRes) }
  fun toolbarScrollFlags(@ScrollFlags flags: Int) = apply { this.toolbarScrollFlags = flags }

  fun iconDefaultColor(@ColorInt color: Int) = apply { this.iconDefaultColor = color }
  fun iconDefaultColorRes(@ColorRes color: Int) = apply { this.iconDefaultColor = ContextCompat.getColor(context, color) }
  fun iconDisabledColor(@ColorInt color: Int) = apply { this.iconDisabledColor = color }
  fun iconDisabledColorRes(@ColorRes colorRes: Int) = apply { this.iconDisabledColor = ContextCompat.getColor(context, colorRes) }
  fun iconPressedColor(@ColorInt color: Int) = apply { this.iconPressedColor = color }
  fun iconPressedColorRes(@ColorRes colorRes: Int) = apply { this.iconPressedColor = ContextCompat.getColor(context, colorRes) }
  fun iconSelector(@DrawableRes selectorRes: Int) = apply { this.iconSelector = selectorRes }

  fun showIconClose(showIconClose: Boolean) = apply { this.showIconClose = showIconClose }
  fun disableIconClose(disableIconClose: Boolean) = apply { this.disableIconClose = disableIconClose }

  fun showIconBack(showIconBack: Boolean) = apply { this.showIconBack = showIconBack }
  fun disableIconBack(disableIconBack: Boolean) = apply { this.disableIconBack = disableIconBack }

  fun showIconForward(showIconForward: Boolean) = apply { this.showIconForward = showIconForward }
  fun disableIconForward(disableIconForward: Boolean) = apply { this.disableIconForward = disableIconForward }

  fun showIconMenu(showIconMenu: Boolean) = apply { this.showIconMenu = showIconMenu }
  fun disableIconMenu(disableIconMenu: Boolean) = apply { this.disableIconMenu = disableIconMenu }

  fun showSwipeRefreshLayout(showSwipeRefreshLayout: Boolean) = apply { this.showSwipeRefreshLayout = showSwipeRefreshLayout }

  fun swipeRefreshColor(@ColorInt color: Int) = apply { this.swipeRefreshColor = color }
  fun swipeRefreshColorRes(@ColorRes colorRes: Int) = apply { this.swipeRefreshColor = ContextCompat.getColor(context, colorRes) }
  fun swipeRefreshColors(colors: IntArray) = apply {
    val swipeRefreshColors = IntArray(colors.size)
    for (i in colors.indices) {
      swipeRefreshColors[i] = colors[i]
    }
    this.swipeRefreshColors = swipeRefreshColors
  }

  fun swipeRefreshColorsRes(@ArrayRes colorsRes: Int) = apply {
    val colors = context.resources.getIntArray(colorsRes)
    swipeRefreshColors(colors)
  }

  fun showDivider(showDivider: Boolean) = apply { this.showDivider = showDivider }

  fun gradientDivider(gradientDivider: Boolean) = apply { this.gradientDivider = gradientDivider }

  fun dividerColor(@ColorInt color: Int) = apply { this.dividerColor = color }
  fun dividerColorRes(@ColorRes colorRes: Int) = apply { this.dividerColor = ContextCompat.getColor(context, colorRes) }
  fun dividerHeight(height: Float) = apply { this.dividerHeight = height }
  fun dividerHeight(height: Int) = apply { this.dividerHeight = height.toFloat() }
  fun dividerHeightRes(@DimenRes height: Int) = apply { this.dividerHeight = context.resources.getDimension(height) }

  fun showProgressBar(showProgressBar: Boolean) = apply { this.showProgressBar = showProgressBar }

  fun progressBarColor(@ColorInt color: Int) = apply { this.progressBarColor = color }
  fun progressBarColorRes(@ColorRes colorRes: Int) = apply { this.progressBarColor = ContextCompat.getColor(context, colorRes) }

  fun progressBarHeight(height: Float) = apply { this.progressBarHeight = height }
  fun progressBarHeight(height: Int) = apply { this.progressBarHeight = height.toFloat() }
  fun progressBarHeightRes(@DimenRes height: Int) = apply { this.progressBarHeight = context.resources.getDimension(height) }
  fun progressBarPosition(progressBarPosition: ProgressBarPosition) = apply { this.progressBarPosition = progressBarPosition }

  fun titleDefault(title: String) = apply { this.titleDefault = title }
  fun titleDefaultRes(@StringRes stringRes: Int) = apply { this.titleDefault = context.getString(stringRes) }

  fun updateTitleFromHtml(updateTitleFromHtml: Boolean) = apply { this.updateTitleFromHtml = updateTitleFromHtml }

  fun titleSize(titleSize: Float) = apply { this.titleSize = titleSize }
  fun titleSize(titleSize: Int) = apply { this.titleSize = titleSize.toFloat() }
  fun titleSizeRes(@DimenRes titleSize: Int) = apply { this.titleSize = context.resources.getDimension(titleSize) }

  fun titleFont(titleFont: String?) = apply { this.titleFont = titleFont }
  fun titleColor(@ColorInt color: Int) = apply { this.titleColor = color }
  fun titleColorRes(@ColorRes colorRes: Int) = apply { this.titleColor = ContextCompat.getColor(context, colorRes) }

  fun showUrl(showUrl: Boolean) = apply { this.showUrl = showUrl }

  fun urlSize(urlSize: Float) = apply { this.urlSize = urlSize }
  fun urlSize(urlSize: Int) = apply { this.urlSize = urlSize.toFloat() }
  fun urlSizeRes(@DimenRes urlSize: Int) = apply { this.urlSize = context.resources.getDimension(urlSize) }

  fun urlFont(urlFont: String?) = apply { this.urlFont = urlFont }

  fun urlColor(@ColorInt color: Int) = apply { this.urlColor = color }
  fun urlColorRes(@ColorRes colorRes: Int) = apply { this.urlColor = ContextCompat.getColor(context, colorRes) }

  fun menuColor(@ColorInt color: Int) = apply { this.menuColor = color }
  fun menuColorRes(@ColorRes colorRes: Int) = apply { this.menuColor = ContextCompat.getColor(context, colorRes) }

  fun menuTextGravity(gravity: Int) = apply { this.menuTextGravity = gravity }

  fun menuTextPaddingLeft(menuTextPaddingLeft: Float) = apply { this.menuTextPaddingLeft = menuTextPaddingLeft }
  fun menuTextPaddingLeft(menuTextPaddingLeft: Int) = apply { this.menuTextPaddingLeft = menuTextPaddingLeft.toFloat() }
  fun menuTextPaddingLeftRes(@DimenRes menuTextPaddingLeft: Int) = apply { this.menuTextPaddingLeft = context.resources.getDimension(menuTextPaddingLeft) }

  fun menuTextPaddingRight(menuTextPaddingRight: Float) = apply { this.menuTextPaddingRight = menuTextPaddingRight }
  fun menuTextPaddingRight(menuTextPaddingRight: Int) = apply { this.menuTextPaddingRight = menuTextPaddingRight.toFloat() }
  fun menuTextPaddingRightRes(@DimenRes menuTextPaddingRight: Int) = apply { this.menuTextPaddingRight = context.resources.getDimension(menuTextPaddingRight) }

  fun menuDropShadowColor(@ColorInt color: Int) = apply { this.menuDropShadowColor = color }
  fun menuDropShadowColorRes(@ColorRes colorRes: Int) = apply { this.menuDropShadowColor = ContextCompat.getColor(context, colorRes) }

  fun menuDropShadowSize(menuDropShadowSize: Float) = apply { this.menuDropShadowSize = menuDropShadowSize }
  fun menuDropShadowSize(menuDropShadowSize: Int) = apply { this.menuDropShadowSize = menuDropShadowSize.toFloat() }
  fun menuDropShadowSizeRes(@DimenRes menuDropShadowSize: Int) = apply { this.menuDropShadowSize = context.resources.getDimension(menuDropShadowSize) }

  fun menuSelector(@DrawableRes selectorRes: Int) = apply { this.menuSelector = selectorRes }

  fun menuTextSize(menuTextSize: Float) = apply { this.menuTextSize = menuTextSize }
  fun menuTextSize(menuTextSize: Int) = apply { this.menuTextSize = menuTextSize.toFloat() }
  fun menuTextSizeRes(@DimenRes menuTextSize: Int) = apply { this.menuTextSize = context.resources.getDimension(menuTextSize) }

  fun menuTextFont(menuTextFont: String?) = apply { this.menuTextFont = menuTextFont }

  fun menuTextColor(@ColorInt color: Int) = apply { this.menuTextColor = color }
  fun menuTextColorRes(@ColorRes colorRes: Int) = apply { this.menuTextColor = ContextCompat.getColor(context, colorRes) }

  fun showMenuRefresh(showMenuRefresh: Boolean) = apply { this.showMenuRefresh = showMenuRefresh }

  fun stringResRefresh(@StringRes stringResRefresh: Int) = apply { this.stringResRefresh = stringResRefresh }

  fun showMenuFind(showMenuFind: Boolean) = apply { this.showMenuFind = showMenuFind }

  fun stringResFind(@StringRes stringResFind: Int) = apply { this.stringResFind = stringResFind }

  fun showMenuShareVia(showMenuShareVia: Boolean) = apply { this.showMenuShareVia = showMenuShareVia }

  fun stringResShareVia(@StringRes stringResShareVia: Int) = apply { this.stringResShareVia = stringResShareVia }

  fun showMenuCopyLink(showMenuCopyLink: Boolean) = apply { this.showMenuCopyLink = showMenuCopyLink }

  fun stringResCopyLink(@StringRes stringResCopyLink: Int) = apply { this.stringResCopyLink = stringResCopyLink }

  fun showMenuOpenWith(showMenuOpenWith: Boolean) = apply { this.showMenuOpenWith = showMenuOpenWith }

  fun stringResOpenWith(@StringRes stringResOpenWith: Int) = apply { this.stringResOpenWith = stringResOpenWith }

  fun setCustomAnimations(@AnimRes animationOpenEnter: Int, @AnimRes animationOpenExit: Int, @AnimRes animationCloseEnter: Int, @AnimRes animationCloseExit: Int) = apply {
    this.animationOpenEnter = animationOpenEnter
    this.animationOpenExit = animationOpenExit
    this.animationCloseEnter = animationCloseEnter
    this.animationCloseExit = animationCloseExit
  }

  @Deprecated("As of release 1.0.1, replaced by {@link #setCustomAnimations(int, int, int, int)}")
  fun setCloseAnimations(@AnimRes animationCloseEnter: Int, @AnimRes animationCloseExit: Int) = apply {
    this.animationCloseEnter = animationCloseEnter
    this.animationCloseExit = animationCloseExit
  }

  fun backPressToClose(backPressToClose: Boolean) = apply { this.backPressToClose = backPressToClose }

  fun stringResCopiedToClipboard(@StringRes stringResCopiedToClipboard: Int) = apply { this.stringResCopiedToClipboard = stringResCopiedToClipboard }

  fun webViewSupportZoom(webViewSupportZoom: Boolean) = apply { this.webViewSupportZoom = webViewSupportZoom }

  fun webViewMediaPlaybackRequiresUserGesture(webViewMediaPlaybackRequiresUserGesture: Boolean) = apply { this.webViewMediaPlaybackRequiresUserGesture = webViewMediaPlaybackRequiresUserGesture }

  fun webViewBuiltInZoomControls(webViewBuiltInZoomControls: Boolean) = apply { this.webViewBuiltInZoomControls = webViewBuiltInZoomControls }

  fun webViewDisplayZoomControls(webViewDisplayZoomControls: Boolean) = apply { this.webViewDisplayZoomControls = webViewDisplayZoomControls }

  fun webViewAllowFileAccess(webViewAllowFileAccess: Boolean) = apply { this.webViewAllowFileAccess = webViewAllowFileAccess }

  fun webViewAllowContentAccess(webViewAllowContentAccess: Boolean) = apply { this.webViewAllowContentAccess = webViewAllowContentAccess }

  fun webViewLoadWithOverviewMode(webViewLoadWithOverviewMode: Boolean) = apply { this.webViewLoadWithOverviewMode = webViewLoadWithOverviewMode }

  fun webViewSaveFormData(webViewSaveFormData: Boolean) = apply { this.webViewSaveFormData = webViewSaveFormData }

  fun webViewTextZoom(webViewTextZoom: Int) = apply { this.webViewTextZoom = webViewTextZoom }

  fun webViewUseWideViewPort(webViewUseWideViewPort: Boolean) = apply { this.webViewUseWideViewPort = webViewUseWideViewPort }

  fun webViewSupportMultipleWindows(webViewSupportMultipleWindows: Boolean) = apply { this.webViewSupportMultipleWindows = webViewSupportMultipleWindows }

  fun webViewLayoutAlgorithm(webViewLayoutAlgorithm: LayoutAlgorithm?): FinestWebView = apply { this.webViewLayoutAlgorithm = webViewLayoutAlgorithm }

  fun webViewStandardFontFamily(webViewStandardFontFamily: String?) = apply { this.webViewStandardFontFamily = webViewStandardFontFamily }

  fun webViewFixedFontFamily(webViewFixedFontFamily: String?) = apply { this.webViewFixedFontFamily = webViewFixedFontFamily }

  fun webViewSansSerifFontFamily(webViewSansSerifFontFamily: String?) = apply { this.webViewSansSerifFontFamily = webViewSansSerifFontFamily }

  fun webViewSerifFontFamily(webViewSerifFontFamily: String?) = apply { this.webViewSerifFontFamily = webViewSerifFontFamily }

  fun webViewCursiveFontFamily(webViewCursiveFontFamily: String?) = apply { this.webViewCursiveFontFamily = webViewCursiveFontFamily }

  fun webViewFantasyFontFamily(webViewFantasyFontFamily: String?) = apply { this.webViewFantasyFontFamily = webViewFantasyFontFamily }

  fun webViewMinimumFontSize(webViewMinimumFontSize: Int) = apply { this.webViewMinimumFontSize = webViewMinimumFontSize }

  fun webViewMinimumLogicalFontSize(webViewMinimumLogicalFontSize: Int) = apply { this.webViewMinimumLogicalFontSize = webViewMinimumLogicalFontSize }

  fun webViewDefaultFontSize(webViewDefaultFontSize: Int) = apply { this.webViewDefaultFontSize = webViewDefaultFontSize }

  fun webViewDefaultFixedFontSize(webViewDefaultFixedFontSize: Int) = apply { this.webViewDefaultFixedFontSize = webViewDefaultFixedFontSize }

  fun webViewLoadsImagesAutomatically(webViewLoadsImagesAutomatically: Boolean) = apply { this.webViewLoadsImagesAutomatically = webViewLoadsImagesAutomatically }

  fun webViewBlockNetworkImage(webViewBlockNetworkImage: Boolean) = apply { this.webViewBlockNetworkImage = webViewBlockNetworkImage }

  fun webViewBlockNetworkLoads(webViewBlockNetworkLoads: Boolean) = apply { this.webViewBlockNetworkLoads = webViewBlockNetworkLoads }

  fun webViewJavaScriptEnabled(webViewJavaScriptEnabled: Boolean) = apply { this.webViewJavaScriptEnabled = webViewJavaScriptEnabled }

  fun webViewAllowUniversalAccessFromFileURLs(webViewAllowUniversalAccessFromFileURLs: Boolean) = apply { this.webViewAllowUniversalAccessFromFileURLs = webViewAllowUniversalAccessFromFileURLs }

  fun webViewAllowFileAccessFromFileURLs(webViewAllowFileAccessFromFileURLs: Boolean) = apply { this.webViewAllowFileAccessFromFileURLs = webViewAllowFileAccessFromFileURLs }

  fun webViewGeolocationDatabasePath(webViewGeolocationDatabasePath: String?) = apply { this.webViewGeolocationDatabasePath = webViewGeolocationDatabasePath }

  fun webViewAppCacheEnabled(webViewAppCacheEnabled: Boolean) = apply { this.webViewAppCacheEnabled = webViewAppCacheEnabled }

  fun webViewAppCachePath(webViewAppCachePath: String?) = apply { this.webViewAppCachePath = webViewAppCachePath }

  fun webViewDatabaseEnabled(webViewDatabaseEnabled: Boolean) = apply { this.webViewDatabaseEnabled = webViewDatabaseEnabled }

  fun webViewDomStorageEnabled(webViewDomStorageEnabled: Boolean) = apply { this.webViewDomStorageEnabled = webViewDomStorageEnabled }

  fun webViewGeolocationEnabled(webViewGeolocationEnabled: Boolean) = apply { this.webViewGeolocationEnabled = webViewGeolocationEnabled }

  fun webViewJavaScriptCanOpenWindowsAutomatically(webViewJavaScriptCanOpenWindowsAutomatically: Boolean) = apply { this.webViewJavaScriptCanOpenWindowsAutomatically = webViewJavaScriptCanOpenWindowsAutomatically }

  fun webViewDefaultTextEncodingName(webViewDefaultTextEncodingName: String?) = apply { this.webViewDefaultTextEncodingName = webViewDefaultTextEncodingName }

  fun webViewUserAgentString(webViewUserAgentString: String?) = apply { this.webViewUserAgentString = webViewUserAgentString }

  fun webViewNeedInitialFocus(webViewNeedInitialFocus: Boolean) = apply { this.webViewNeedInitialFocus = webViewNeedInitialFocus }

  fun webViewCacheMode(webViewCacheMode: Int) = apply { this.webViewCacheMode = webViewCacheMode }

  fun webViewMixedContentMode(webViewMixedContentMode: Int) = apply { this.webViewMixedContentMode = webViewMixedContentMode }

  fun webViewOffscreenPreRaster(webViewOffscreenPreRaster: Boolean) = apply { this.webViewOffscreenPreRaster = webViewOffscreenPreRaster }

  fun webViewDesktopMode(webViewDesktopMode: Boolean) = apply {
    return if (webViewDesktopMode) {
      webViewUserAgentString("Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.4) Gecko/20100101 Firefox/4.0")
    } else {
      this
    }
  }

  fun injectJavaScript(injectJavaScript: String?) = apply { this.injectJavaScript = injectJavaScript }

  fun load(@StringRes dataRes: Int) {
    load(context.getString(dataRes))
  }

  fun load(data: String?, mimeType: String? = "text/html", encoding: String? = "UTF-8") {
    this.mimeType = mimeType
    this.encoding = encoding
    show(null, data)
  }

  fun show(@StringRes urlRes: Int) {
    show(context.getString(urlRes))
  }

  fun show(url: String) {
    show(url, null)
  }

  fun show(url: String?, data: String?) {
    this.url = url
    this.data = data
    key = System.identityHashCode(this)
    if (listeners.isNotEmpty()) {
      BroadCastManager(context, key!!, listeners)
    }
    val intent = Intent(context, FinestWebViewActivity::class.java)
    intent.putExtra("FinestWebView", this)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)
    if (context is Activity) {
      (context as Activity).overridePendingTransition(animationOpenEnter, animationOpenExit)
    }
  }
}