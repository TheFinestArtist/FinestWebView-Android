package com.thefinestartist.finestwebview

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.webkit.WebSettings.LayoutAlgorithm
import androidx.annotation.*
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.LayoutParams
import com.google.android.material.appbar.AppBarLayout.LayoutParams.*
import com.thefinestartist.finestwebview.enums.ProgressBarPosition
import com.thefinestartist.finestwebview.listeners.BroadCastManager
import com.thefinestartist.finestwebview.listeners.WebViewListener
import java.io.Serializable
import java.util.*

/** Created by Leonardo on 11/21/15.  */
class FinestWebView {
  class Builder : Serializable {
    @Transient protected val context: Context
    @Transient protected var listeners: MutableList<WebViewListener> = ArrayList()

    var key: Int? = null

    var rtl: Boolean? = null
    var theme: Int? = null

    var statusBarColor: Int? = null

    var toolbarColor: Int? = null
    var toolbarScrollFlags: Int? = null

    var iconDefaultColor: Int? = null
    var iconDisabledColor: Int? = null
    var iconPressedColor: Int? = null
    var iconSelector: Int? = null

    var showIconClose: Boolean? = null
    var disableIconClose: Boolean? = null
    var showIconBack: Boolean? = null
    var disableIconBack: Boolean? = null
    var showIconForward: Boolean? = null
    var disableIconForward: Boolean? = null
    var showIconMenu: Boolean? = null
    var disableIconMenu: Boolean? = null

    var showSwipeRefreshLayout: Boolean? = null
    var swipeRefreshColor: Int? = null
    var swipeRefreshColors: IntArray? = null

    var showDivider: Boolean? = null
    var gradientDivider: Boolean? = null
    var dividerColor: Int? = null
    var dividerHeight: Float? = null

    var showProgressBar: Boolean? = null
    var progressBarColor: Int? = null
    var progressBarHeight: Float? = null
    var progressBarPosition: ProgressBarPosition? = null

    var titleDefault: String? = null
    var updateTitleFromHtml: Boolean? = null
    var titleSize: Float? = null
    var titleFont: String? = null
    var titleColor: Int? = null

    var showUrl: Boolean? = null
    var urlSize: Float? = null
    var urlFont: String? = null
    var urlColor: Int? = null

    var menuColor: Int? = null
    var menuDropShadowColor: Int? = null
    var menuDropShadowSize: Float? = null
    var menuSelector: Int? = null

    var menuTextSize: Float? = null
    var menuTextFont: String? = null
    var menuTextColor: Int? = null

    var menuTextGravity: Int? = null
    var menuTextPaddingLeft: Float? = null
    var menuTextPaddingRight: Float? = null

    var showMenuRefresh: Boolean? = null
    var stringResRefresh: Int? = null
    var showMenuFind: Boolean? = null
    var stringResFind: Int? = null
    var showMenuShareVia: Boolean? = null
    var stringResShareVia: Int? = null
    var showMenuCopyLink: Boolean? = null
    var stringResCopyLink: Int? = null
    var showMenuOpenWith: Boolean? = null
    var stringResOpenWith: Int? = null

    protected var animationOpenEnter = R.anim.modal_activity_open_enter
    protected var animationOpenExit = R.anim.modal_activity_open_exit
    @JvmField var animationCloseEnter: Int? = null
    @JvmField var animationCloseExit: Int? = null

    var backPressToClose: Boolean? = null
    var stringResCopiedToClipboard: Int? = null

    var webViewSupportZoom: Boolean? = null
    var webViewMediaPlaybackRequiresUserGesture: Boolean? = null
    var webViewBuiltInZoomControls: Boolean? = null
    var webViewDisplayZoomControls: Boolean? = null
    var webViewAllowFileAccess: Boolean? = null
    var webViewAllowContentAccess: Boolean? = null
    var webViewLoadWithOverviewMode: Boolean? = null
    var webViewSaveFormData: Boolean? = null
    var webViewTextZoom: Int? = null
    var webViewUseWideViewPort: Boolean? = null
    var webViewSupportMultipleWindows: Boolean? = null
    var webViewLayoutAlgorithm: LayoutAlgorithm? = null
    var webViewStandardFontFamily: String? = null
    var webViewFixedFontFamily: String? = null
    var webViewSansSerifFontFamily: String? = null
    var webViewSerifFontFamily: String? = null
    var webViewCursiveFontFamily: String? = null
    var webViewFantasyFontFamily: String? = null
    var webViewMinimumFontSize: Int? = null
    var webViewMinimumLogicalFontSize: Int? = null
    var webViewDefaultFontSize: Int? = null
    var webViewDefaultFixedFontSize: Int? = null
    var webViewLoadsImagesAutomatically: Boolean? = null
    var webViewBlockNetworkImage: Boolean? = null
    var webViewBlockNetworkLoads: Boolean? = null
    var webViewJavaScriptEnabled: Boolean? = null
    var webViewAllowUniversalAccessFromFileURLs: Boolean? = null
    var webViewAllowFileAccessFromFileURLs: Boolean? = null
    var webViewGeolocationDatabasePath: String? = null
    var webViewAppCacheEnabled: Boolean? = null
    var webViewAppCachePath: String? = null
    var webViewDatabaseEnabled: Boolean? = null
    var webViewDomStorageEnabled: Boolean? = null
    var webViewGeolocationEnabled: Boolean? = null
    var webViewJavaScriptCanOpenWindowsAutomatically: Boolean? = null
    var webViewDefaultTextEncodingName: String? = null
    var webViewUserAgentString: String? = null
    var webViewNeedInitialFocus: Boolean? = null
    var webViewCacheMode: Int? = null
    var webViewMixedContentMode: Int? = null
    var webViewOffscreenPreRaster: Boolean? = null

    var injectJavaScript: String? = null

    var mimeType: String? = null
    var encoding: String? = null
    var data: String? = null
    var url: String? = null

    constructor(activity: Activity) {
      context = activity
    }

    /**
     * If you use context instead of activity, FinestWebView won't be able to override activity
     * animation. Try to create builder with Activity if it's possible.
     */
    constructor(context: Context) {
      this.context = context
    }

    fun setWebViewListener(listener: WebViewListener): Builder {
      listeners.clear()
      listeners.add(listener)
      return this
    }

    fun addWebViewListener(listener: WebViewListener): Builder {
      listeners.add(listener)
      return this
    }

    fun removeWebViewListener(listener: WebViewListener): Builder {
      listeners.remove(listener)
      return this
    }

    fun rtl(rtl: Boolean): Builder {
      this.rtl = rtl
      return this
    }

    fun theme(@StyleRes theme: Int): Builder {
      this.theme = theme
      return this
    }

    fun statusBarColor(@ColorInt color: Int): Builder {
      statusBarColor = color
      return this
    }

    fun statusBarColorRes(@ColorRes colorRes: Int): Builder {
      statusBarColor = ContextCompat.getColor(context, colorRes)
      return this
    }

    fun toolbarColor(@ColorInt color: Int): Builder {
      toolbarColor = color
      return this
    }

    fun toolbarColorRes(@ColorRes colorRes: Int): Builder {
      toolbarColor = ContextCompat.getColor(context, colorRes)
      return this
    }

    fun toolbarScrollFlags(@ScrollFlags flags: Int): Builder {
      toolbarScrollFlags = flags
      return this
    }

    fun iconDefaultColor(@ColorInt color: Int): Builder {
      iconDefaultColor = color
      return this
    }

    fun iconDefaultColorRes(@ColorRes color: Int): Builder {
      iconDefaultColor = ContextCompat.getColor(context, color)
      return this
    }

    fun iconDisabledColor(@ColorInt color: Int): Builder {
      iconDisabledColor = color
      return this
    }

    fun iconDisabledColorRes(@ColorRes colorRes: Int): Builder {
      iconDisabledColor = ContextCompat.getColor(context, colorRes)
      return this
    }

    fun iconPressedColor(@ColorInt color: Int): Builder {
      iconPressedColor = color
      return this
    }

    fun iconPressedColorRes(@ColorRes colorRes: Int): Builder {
      iconPressedColor = ContextCompat.getColor(context, colorRes)
      return this
    }

    fun iconSelector(@DrawableRes selectorRes: Int): Builder {
      iconSelector = selectorRes
      return this
    }

    fun showIconClose(showIconClose: Boolean): Builder {
      this.showIconClose = showIconClose
      return this
    }

    fun disableIconClose(disableIconClose: Boolean): Builder {
      this.disableIconClose = disableIconClose
      return this
    }

    fun showIconBack(showIconBack: Boolean): Builder {
      this.showIconBack = showIconBack
      return this
    }

    fun disableIconBack(disableIconBack: Boolean): Builder {
      this.disableIconBack = disableIconBack
      return this
    }

    fun showIconForward(showIconForward: Boolean): Builder {
      this.showIconForward = showIconForward
      return this
    }

    fun disableIconForward(disableIconForward: Boolean): Builder {
      this.disableIconForward = disableIconForward
      return this
    }

    fun showIconMenu(showIconMenu: Boolean): Builder {
      this.showIconMenu = showIconMenu
      return this
    }

    fun disableIconMenu(disableIconMenu: Boolean): Builder {
      this.disableIconMenu = disableIconMenu
      return this
    }

    fun showSwipeRefreshLayout(showSwipeRefreshLayout: Boolean): Builder {
      this.showSwipeRefreshLayout = showSwipeRefreshLayout
      return this
    }

    fun swipeRefreshColor(@ColorInt color: Int): Builder {
      swipeRefreshColor = color
      return this
    }

    fun swipeRefreshColorRes(@ColorRes colorRes: Int): Builder {
      swipeRefreshColor = ContextCompat.getColor(context, colorRes)
      return this
    }

    fun swipeRefreshColors(colors: IntArray): Builder {
      val swipeRefreshColors = IntArray(colors.size)
      for (i in colors.indices) {
        swipeRefreshColors[i] = colors[i]
      }
      this.swipeRefreshColors = swipeRefreshColors
      return this
    }

    fun swipeRefreshColorsRes(@ArrayRes colorsRes: Int): Builder {
      val colors = context.resources.getIntArray(colorsRes)
      return swipeRefreshColors(colors)
    }

    fun showDivider(showDivider: Boolean): Builder {
      this.showDivider = showDivider
      return this
    }

    fun gradientDivider(gradientDivider: Boolean): Builder {
      this.gradientDivider = gradientDivider
      return this
    }

    fun dividerColor(@ColorInt color: Int): Builder {
      dividerColor = color
      return this
    }

    fun dividerColorRes(@ColorRes colorRes: Int): Builder {
      dividerColor = ContextCompat.getColor(context, colorRes)
      return this
    }

    fun dividerHeight(height: Float): Builder {
      dividerHeight = height
      return this
    }

    fun dividerHeight(height: Int): Builder {
      dividerHeight = height.toFloat()
      return this
    }

    fun dividerHeightRes(@DimenRes height: Int): Builder {
      dividerHeight = context.resources.getDimension(height)
      return this
    }

    fun showProgressBar(showProgressBar: Boolean): Builder {
      this.showProgressBar = showProgressBar
      return this
    }

    fun progressBarColor(@ColorInt color: Int): Builder {
      progressBarColor = color
      return this
    }

    fun progressBarColorRes(@ColorRes colorRes: Int): Builder {
      progressBarColor = ContextCompat.getColor(context, colorRes)
      return this
    }

    fun progressBarHeight(height: Float): Builder {
      progressBarHeight = height
      return this
    }

    fun progressBarHeight(height: Int): Builder {
      progressBarHeight = height.toFloat()
      return this
    }

    fun progressBarHeightRes(@DimenRes height: Int): Builder {
      progressBarHeight = context.resources.getDimension(height)
      return this
    }

    fun progressBarPosition(progressBarPosition: ProgressBarPosition): Builder {
      this.progressBarPosition = progressBarPosition
      return this
    }

    fun titleDefault(title: String): Builder {
      titleDefault = title
      return this
    }

    fun titleDefaultRes(@StringRes stringRes: Int): Builder {
      titleDefault = context.getString(stringRes)
      return this
    }

    fun updateTitleFromHtml(updateTitleFromHtml: Boolean): Builder {
      this.updateTitleFromHtml = updateTitleFromHtml
      return this
    }

    fun titleSize(titleSize: Float): Builder {
      this.titleSize = titleSize
      return this
    }

    fun titleSize(titleSize: Int): Builder {
      this.titleSize = titleSize.toFloat()
      return this
    }

    fun titleSizeRes(@DimenRes titleSize: Int): Builder {
      this.titleSize = context.resources.getDimension(titleSize)
      return this
    }

    fun titleFont(titleFont: String?): Builder {
      this.titleFont = titleFont
      return this
    }

    fun titleColor(@ColorInt color: Int): Builder {
      titleColor = color
      return this
    }

    fun titleColorRes(@ColorRes colorRes: Int): Builder {
      titleColor = ContextCompat.getColor(context, colorRes)
      return this
    }

    fun showUrl(showUrl: Boolean): Builder {
      this.showUrl = showUrl
      return this
    }

    fun urlSize(urlSize: Float): Builder {
      this.urlSize = urlSize
      return this
    }

    fun urlSize(urlSize: Int): Builder {
      this.urlSize = urlSize.toFloat()
      return this
    }

    fun urlSizeRes(@DimenRes urlSize: Int): Builder {
      this.urlSize = context.resources.getDimension(urlSize)
      return this
    }

    fun urlFont(urlFont: String?): Builder {
      this.urlFont = urlFont
      return this
    }

    fun urlColor(@ColorInt color: Int): Builder {
      urlColor = color
      return this
    }

    fun urlColorRes(@ColorRes colorRes: Int): Builder {
      urlColor = ContextCompat.getColor(context, colorRes)
      return this
    }

    fun menuColor(@ColorInt color: Int): Builder {
      menuColor = color
      return this
    }

    fun menuColorRes(@ColorRes colorRes: Int): Builder {
      menuColor = ContextCompat.getColor(context, colorRes)
      return this
    }

    fun menuTextGravity(gravity: Int): Builder {
      menuTextGravity = gravity
      return this
    }

    fun menuTextPaddingLeft(menuTextPaddingLeft: Float): Builder {
      this.menuTextPaddingLeft = menuTextPaddingLeft
      return this
    }

    fun menuTextPaddingLeft(menuTextPaddingLeft: Int): Builder {
      this.menuTextPaddingLeft = menuTextPaddingLeft.toFloat()
      return this
    }

    fun menuTextPaddingLeftRes(@DimenRes menuTextPaddingLeft: Int): Builder {
      this.menuTextPaddingLeft = context.resources.getDimension(menuTextPaddingLeft)
      return this
    }

    fun menuTextPaddingRight(menuTextPaddingRight: Float): Builder {
      this.menuTextPaddingRight = menuTextPaddingRight
      return this
    }

    fun menuTextPaddingRight(menuTextPaddingRight: Int): Builder {
      this.menuTextPaddingRight = menuTextPaddingRight.toFloat()
      return this
    }

    fun menuTextPaddingRightRes(@DimenRes menuTextPaddingRight: Int): Builder {
      this.menuTextPaddingRight = context.resources.getDimension(menuTextPaddingRight)
      return this
    }

    fun menuDropShadowColor(@ColorInt color: Int): Builder {
      menuDropShadowColor = color
      return this
    }

    fun menuDropShadowColorRes(@ColorRes colorRes: Int): Builder {
      menuDropShadowColor = ContextCompat.getColor(context, colorRes)
      return this
    }

    fun menuDropShadowSize(menuDropShadowSize: Float): Builder {
      this.menuDropShadowSize = menuDropShadowSize
      return this
    }

    fun menuDropShadowSize(menuDropShadowSize: Int): Builder {
      this.menuDropShadowSize = menuDropShadowSize.toFloat()
      return this
    }

    fun menuDropShadowSizeRes(@DimenRes menuDropShadowSize: Int): Builder {
      this.menuDropShadowSize = context.resources.getDimension(menuDropShadowSize)
      return this
    }

    fun menuSelector(@DrawableRes selectorRes: Int): Builder {
      menuSelector = selectorRes
      return this
    }

    fun menuTextSize(menuTextSize: Float): Builder {
      this.menuTextSize = menuTextSize
      return this
    }

    fun menuTextSize(menuTextSize: Int): Builder {
      this.menuTextSize = menuTextSize.toFloat()
      return this
    }

    fun menuTextSizeRes(@DimenRes menuTextSize: Int): Builder {
      this.menuTextSize = context.resources.getDimension(menuTextSize)
      return this
    }

    fun menuTextFont(menuTextFont: String?): Builder {
      this.menuTextFont = menuTextFont
      return this
    }

    fun menuTextColor(@ColorInt color: Int): Builder {
      menuTextColor = color
      return this
    }

    fun menuTextColorRes(@ColorRes colorRes: Int): Builder {
      menuTextColor = ContextCompat.getColor(context, colorRes)
      return this
    }

    fun showMenuRefresh(showMenuRefresh: Boolean): Builder {
      this.showMenuRefresh = showMenuRefresh
      return this
    }

    fun stringResRefresh(@StringRes stringResRefresh: Int): Builder {
      this.stringResRefresh = stringResRefresh
      return this
    }

    fun showMenuFind(showMenuFind: Boolean): Builder {
      this.showMenuFind = showMenuFind
      return this
    }

    fun stringResFind(@StringRes stringResFind: Int): Builder {
      this.stringResFind = stringResFind
      return this
    }

    fun showMenuShareVia(showMenuShareVia: Boolean): Builder {
      this.showMenuShareVia = showMenuShareVia
      return this
    }

    fun stringResShareVia(@StringRes stringResShareVia: Int): Builder {
      this.stringResShareVia = stringResShareVia
      return this
    }

    fun showMenuCopyLink(showMenuCopyLink: Boolean): Builder {
      this.showMenuCopyLink = showMenuCopyLink
      return this
    }

    fun stringResCopyLink(@StringRes stringResCopyLink: Int): Builder {
      this.stringResCopyLink = stringResCopyLink
      return this
    }

    fun showMenuOpenWith(showMenuOpenWith: Boolean): Builder {
      this.showMenuOpenWith = showMenuOpenWith
      return this
    }

    fun stringResOpenWith(@StringRes stringResOpenWith: Int): Builder {
      this.stringResOpenWith = stringResOpenWith
      return this
    }

    fun setCustomAnimations(
        @AnimRes animationOpenEnter: Int,
        @AnimRes animationOpenExit: Int,
        @AnimRes animationCloseEnter: Int,
        @AnimRes animationCloseExit: Int): Builder {
      this.animationOpenEnter = animationOpenEnter
      this.animationOpenExit = animationOpenExit
      this.animationCloseEnter = animationCloseEnter
      this.animationCloseExit = animationCloseExit
      return this
    }

    @Deprecated("As of release 1.0.1, replaced by {@link #setCustomAnimations(int, int, int, int)}")
    fun setCloseAnimations(
        @AnimRes animationCloseEnter: Int, @AnimRes animationCloseExit: Int): Builder {
      this.animationCloseEnter = animationCloseEnter
      this.animationCloseExit = animationCloseExit
      return this
    }

    fun backPressToClose(backPressToClose: Boolean): Builder {
      this.backPressToClose = backPressToClose
      return this
    }

    fun stringResCopiedToClipboard(@StringRes stringResCopiedToClipboard: Int): Builder {
      this.stringResCopiedToClipboard = stringResCopiedToClipboard
      return this
    }

    fun webViewSupportZoom(webViewSupportZoom: Boolean): Builder {
      this.webViewSupportZoom = webViewSupportZoom
      return this
    }

    fun webViewMediaPlaybackRequiresUserGesture(
        webViewMediaPlaybackRequiresUserGesture: Boolean): Builder {
      this.webViewMediaPlaybackRequiresUserGesture = webViewMediaPlaybackRequiresUserGesture
      return this
    }

    fun webViewBuiltInZoomControls(webViewBuiltInZoomControls: Boolean): Builder {
      this.webViewBuiltInZoomControls = webViewBuiltInZoomControls
      return this
    }

    fun webViewDisplayZoomControls(webViewDisplayZoomControls: Boolean): Builder {
      this.webViewDisplayZoomControls = webViewDisplayZoomControls
      return this
    }

    fun webViewAllowFileAccess(webViewAllowFileAccess: Boolean): Builder {
      this.webViewAllowFileAccess = webViewAllowFileAccess
      return this
    }

    fun webViewAllowContentAccess(webViewAllowContentAccess: Boolean): Builder {
      this.webViewAllowContentAccess = webViewAllowContentAccess
      return this
    }

    fun webViewLoadWithOverviewMode(webViewLoadWithOverviewMode: Boolean): Builder {
      this.webViewLoadWithOverviewMode = webViewLoadWithOverviewMode
      return this
    }

    fun webViewSaveFormData(webViewSaveFormData: Boolean): Builder {
      this.webViewSaveFormData = webViewSaveFormData
      return this
    }

    fun webViewTextZoom(webViewTextZoom: Int): Builder {
      this.webViewTextZoom = webViewTextZoom
      return this
    }

    fun webViewUseWideViewPort(webViewUseWideViewPort: Boolean): Builder {
      this.webViewUseWideViewPort = webViewUseWideViewPort
      return this
    }

    fun webViewSupportMultipleWindows(webViewSupportMultipleWindows: Boolean): Builder {
      this.webViewSupportMultipleWindows = webViewSupportMultipleWindows
      return this
    }

    fun webViewLayoutAlgorithm(webViewLayoutAlgorithm: LayoutAlgorithm?): Builder {
      this.webViewLayoutAlgorithm = webViewLayoutAlgorithm
      return this
    }

    fun webViewStandardFontFamily(webViewStandardFontFamily: String?): Builder {
      this.webViewStandardFontFamily = webViewStandardFontFamily
      return this
    }

    fun webViewFixedFontFamily(webViewFixedFontFamily: String?): Builder {
      this.webViewFixedFontFamily = webViewFixedFontFamily
      return this
    }

    fun webViewSansSerifFontFamily(webViewSansSerifFontFamily: String?): Builder {
      this.webViewSansSerifFontFamily = webViewSansSerifFontFamily
      return this
    }

    fun webViewSerifFontFamily(webViewSerifFontFamily: String?): Builder {
      this.webViewSerifFontFamily = webViewSerifFontFamily
      return this
    }

    fun webViewCursiveFontFamily(webViewCursiveFontFamily: String?): Builder {
      this.webViewCursiveFontFamily = webViewCursiveFontFamily
      return this
    }

    fun webViewFantasyFontFamily(webViewFantasyFontFamily: String?): Builder {
      this.webViewFantasyFontFamily = webViewFantasyFontFamily
      return this
    }

    fun webViewMinimumFontSize(webViewMinimumFontSize: Int): Builder {
      this.webViewMinimumFontSize = webViewMinimumFontSize
      return this
    }

    fun webViewMinimumLogicalFontSize(webViewMinimumLogicalFontSize: Int): Builder {
      this.webViewMinimumLogicalFontSize = webViewMinimumLogicalFontSize
      return this
    }

    fun webViewDefaultFontSize(webViewDefaultFontSize: Int): Builder {
      this.webViewDefaultFontSize = webViewDefaultFontSize
      return this
    }

    fun webViewDefaultFixedFontSize(webViewDefaultFixedFontSize: Int): Builder {
      this.webViewDefaultFixedFontSize = webViewDefaultFixedFontSize
      return this
    }

    fun webViewLoadsImagesAutomatically(webViewLoadsImagesAutomatically: Boolean): Builder {
      this.webViewLoadsImagesAutomatically = webViewLoadsImagesAutomatically
      return this
    }

    fun webViewBlockNetworkImage(webViewBlockNetworkImage: Boolean): Builder {
      this.webViewBlockNetworkImage = webViewBlockNetworkImage
      return this
    }

    fun webViewBlockNetworkLoads(webViewBlockNetworkLoads: Boolean): Builder {
      this.webViewBlockNetworkLoads = webViewBlockNetworkLoads
      return this
    }

    fun webViewJavaScriptEnabled(webViewJavaScriptEnabled: Boolean): Builder {
      this.webViewJavaScriptEnabled = webViewJavaScriptEnabled
      return this
    }

    fun webViewAllowUniversalAccessFromFileURLs(
        webViewAllowUniversalAccessFromFileURLs: Boolean): Builder {
      this.webViewAllowUniversalAccessFromFileURLs = webViewAllowUniversalAccessFromFileURLs
      return this
    }

    fun webViewAllowFileAccessFromFileURLs(webViewAllowFileAccessFromFileURLs: Boolean): Builder {
      this.webViewAllowFileAccessFromFileURLs = webViewAllowFileAccessFromFileURLs
      return this
    }

    fun webViewGeolocationDatabasePath(webViewGeolocationDatabasePath: String?): Builder {
      this.webViewGeolocationDatabasePath = webViewGeolocationDatabasePath
      return this
    }

    fun webViewAppCacheEnabled(webViewAppCacheEnabled: Boolean): Builder {
      this.webViewAppCacheEnabled = webViewAppCacheEnabled
      return this
    }

    fun webViewAppCachePath(webViewAppCachePath: String?): Builder {
      this.webViewAppCachePath = webViewAppCachePath
      return this
    }

    fun webViewDatabaseEnabled(webViewDatabaseEnabled: Boolean): Builder {
      this.webViewDatabaseEnabled = webViewDatabaseEnabled
      return this
    }

    fun webViewDomStorageEnabled(webViewDomStorageEnabled: Boolean): Builder {
      this.webViewDomStorageEnabled = webViewDomStorageEnabled
      return this
    }

    fun webViewGeolocationEnabled(webViewGeolocationEnabled: Boolean): Builder {
      this.webViewGeolocationEnabled = webViewGeolocationEnabled
      return this
    }

    fun webViewJavaScriptCanOpenWindowsAutomatically(
        webViewJavaScriptCanOpenWindowsAutomatically: Boolean): Builder {
      this.webViewJavaScriptCanOpenWindowsAutomatically = webViewJavaScriptCanOpenWindowsAutomatically
      return this
    }

    fun webViewDefaultTextEncodingName(webViewDefaultTextEncodingName: String?): Builder {
      this.webViewDefaultTextEncodingName = webViewDefaultTextEncodingName
      return this
    }

    fun webViewUserAgentString(webViewUserAgentString: String?): Builder {
      this.webViewUserAgentString = webViewUserAgentString
      return this
    }

    fun webViewNeedInitialFocus(webViewNeedInitialFocus: Boolean): Builder {
      this.webViewNeedInitialFocus = webViewNeedInitialFocus
      return this
    }

    fun webViewCacheMode(webViewCacheMode: Int): Builder {
      this.webViewCacheMode = webViewCacheMode
      return this
    }

    fun webViewMixedContentMode(webViewMixedContentMode: Int): Builder {
      this.webViewMixedContentMode = webViewMixedContentMode
      return this
    }

    fun webViewOffscreenPreRaster(webViewOffscreenPreRaster: Boolean): Builder {
      this.webViewOffscreenPreRaster = webViewOffscreenPreRaster
      return this
    }

    fun webViewDesktopMode(webViewDesktopMode: Boolean): Builder {
      return if (webViewDesktopMode) {
        webViewUserAgentString(
            "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.4) Gecko/20100101 Firefox/4.0")
      } else {
        this
      }
    }

    fun injectJavaScript(injectJavaScript: String?): Builder {
      this.injectJavaScript = injectJavaScript
      return this
    }

    fun load(@StringRes dataRes: Int) {
      load(context.getString(dataRes))
    }

    @JvmOverloads
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
      if (!listeners.isEmpty()) {
        BroadCastManager(context, key!!, listeners)
      }
      val intent = Intent(context, FinestWebViewActivity::class.java)
      intent.putExtra("builder", this)
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
      context.startActivity(intent)
      if (context is Activity) {
        context.overridePendingTransition(animationOpenEnter, animationOpenExit)
      }
    }
  }
}