package com.thefinestartist.finestwebview

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.net.MailTo
import android.net.Uri
import android.os.Build.*
import android.os.Bundle
import android.os.Handler
import android.util.TypedValue
import android.view.*
import android.view.animation.Animation
import android.view.animation.Animation.*
import android.view.animation.AnimationUtils
import android.webkit.DownloadListener
import android.webkit.WebChromeClient
import android.webkit.WebSettings.*
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import com.thefinestartist.finestwebview.enums.ProgressBarPosition
import com.thefinestartist.finestwebview.listeners.BroadCastManager.Companion.onDownloadStart
import com.thefinestartist.finestwebview.listeners.BroadCastManager.Companion.onLoadResource
import com.thefinestartist.finestwebview.listeners.BroadCastManager.Companion.onPageCommitVisible
import com.thefinestartist.finestwebview.listeners.BroadCastManager.Companion.onPageFinished
import com.thefinestartist.finestwebview.listeners.BroadCastManager.Companion.onPageStarted
import com.thefinestartist.finestwebview.listeners.BroadCastManager.Companion.onProgressChanged
import com.thefinestartist.finestwebview.listeners.BroadCastManager.Companion.onReceivedTitle
import com.thefinestartist.finestwebview.listeners.BroadCastManager.Companion.onReceivedTouchIconUrl
import com.thefinestartist.finestwebview.listeners.BroadCastManager.Companion.unregister
import com.thefinestartist.finestwebview.utils.BitmapUtil.getColoredBitmap
import com.thefinestartist.finestwebview.utils.BitmapUtil.getGradientBitmap
import com.thefinestartist.finestwebview.utils.ColorUtil.disableColor
import com.thefinestartist.finestwebview.utils.DisplayUtil.getHeight
import com.thefinestartist.finestwebview.utils.DisplayUtil.getStatusBarHeight
import com.thefinestartist.finestwebview.utils.DisplayUtil.getWidth
import com.thefinestartist.finestwebview.utils.TypefaceUtil
import com.thefinestartist.finestwebview.utils.UnitConverter.dpToPx
import com.thefinestartist.finestwebview.utils.UrlParser.getHost
import com.thefinestartist.finestwebview.views.ShadowLayout

/**
 * Created by Leonardo on 11/14/15.
 */
class FinestWebViewActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener, View.OnClickListener {

  protected var key = 0

  protected var rtl = false

  protected var statusBarColor = 0

  protected var toolbarColor = 0
  protected var toolbarScrollFlags = 0

  protected var iconDefaultColor = 0
  protected var iconDisabledColor = 0
  protected var iconPressedColor = 0
  protected var iconSelector = 0

  protected var showIconClose = false
  protected var disableIconClose = false
  protected var showIconBack = false
  protected var disableIconBack = false
  protected var showIconForward = false
  protected var disableIconForward = false
  protected var showIconMenu = false
  protected var disableIconMenu = false

  protected var showSwipeRefreshLayout = false
  protected var swipeRefreshColor = 0
  protected var swipeRefreshColors: IntArray? = null

  protected var showDivider = false
  protected var gradientDivider = false
  protected var dividerColor = 0
  protected var dividerHeight = 0f

  protected var showProgressBar = false
  protected var progressBarColor = 0
  protected var progressBarHeight = 0f
  protected var progressBarPosition: ProgressBarPosition? = null

  protected var titleDefault: String? = null
  protected var updateTitleFromHtml = false
  protected var titleSize = 0f
  protected var titleFont: String? = null
  protected var finestWebViewTitleColor = 0

  protected var showUrl = false
  protected var urlSize = 0f
  protected var urlFont: String? = null
  protected var urlColor = 0

  protected var menuColor = 0
  protected var menuDropShadowColor = 0
  protected var menuDropShadowSize = 0f
  protected var menuSelector = 0

  protected var menuTextSize = 0f
  protected var menuTextFont: String? = null
  protected var menuTextColor = 0

  protected var menuTextGravity = 0
  protected var menuTextPaddingLeft = 0f
  protected var menuTextPaddingRight = 0f

  protected var showMenuRefresh = false
  protected var stringResRefresh = 0
  protected var showMenuFind = false
  protected var stringResFind = 0
  protected var showMenuShareVia = false
  protected var stringResShareVia = 0
  protected var showMenuCopyLink = false
  protected var stringResCopyLink = 0
  protected var showMenuOpenWith = false
  protected var stringResOpenWith = 0

  protected var animationCloseEnter = 0
  protected var animationCloseExit = 0

  protected var backPressToClose = false
  protected var stringResCopiedToClipboard = 0

  protected var webViewSupportZoom: Boolean? = null
  protected var webViewMediaPlaybackRequiresUserGesture: Boolean? = null
  protected var webViewBuiltInZoomControls: Boolean? = null
  protected var webViewDisplayZoomControls: Boolean? = null
  protected var webViewAllowFileAccess: Boolean? = null
  protected var webViewAllowContentAccess: Boolean? = null
  protected var webViewLoadWithOverviewMode: Boolean? = null
  protected var webViewSaveFormData: Boolean? = null
  protected var webViewTextZoom: Int? = null
  protected var webViewUseWideViewPort: Boolean? = null
  protected var webViewSupportMultipleWindows: Boolean? = null
  protected var webViewLayoutAlgorithm: LayoutAlgorithm? = null
  protected var webViewStandardFontFamily: String? = null
  protected var webViewFixedFontFamily: String? = null
  protected var webViewSansSerifFontFamily: String? = null
  protected var webViewSerifFontFamily: String? = null
  protected var webViewCursiveFontFamily: String? = null
  protected var webViewFantasyFontFamily: String? = null
  protected var webViewMinimumFontSize: Int? = null
  protected var webViewMinimumLogicalFontSize: Int? = null
  protected var webViewDefaultFontSize: Int? = null
  protected var webViewDefaultFixedFontSize: Int? = null
  protected var webViewLoadsImagesAutomatically: Boolean? = null
  protected var webViewBlockNetworkImage: Boolean? = null
  protected var webViewBlockNetworkLoads: Boolean? = null
  protected var webViewJavaScriptEnabled: Boolean? = null
  protected var webViewAllowUniversalAccessFromFileURLs: Boolean? = null
  protected var webViewAllowFileAccessFromFileURLs: Boolean? = null
  protected var webViewGeolocationDatabasePath: String? = null
  protected var webViewAppCacheEnabled: Boolean? = null
  protected var webViewAppCachePath: String? = null
  protected var webViewDatabaseEnabled: Boolean? = null
  protected var webViewDomStorageEnabled: Boolean? = null
  protected var webViewGeolocationEnabled: Boolean? = null
  protected var webViewJavaScriptCanOpenWindowsAutomatically: Boolean? = null
  protected var webViewDefaultTextEncodingName: String? = null
  protected var webViewUserAgentString: String? = null
  protected var webViewNeedInitialFocus: Boolean? = null
  protected var webViewCacheMode: Int? = null
  protected var webViewMixedContentMode: Int? = null
  protected var webViewOffscreenPreRaster: Boolean? = null

  protected var injectJavaScript: String? = null

  protected var mimeType: String? = null
  protected var encoding: String? = null
  protected var data: String? = null
  protected var url: String? = null
  protected var coordinatorLayout: CoordinatorLayout? = null
  protected var appBar: AppBarLayout? = null
  protected var toolbar: Toolbar? = null
  protected var toolbarLayout: RelativeLayout? = null
  protected var title: TextView? = null
  protected var urlTv: TextView? = null
  protected var close: AppCompatImageButton? = null
  protected var back: AppCompatImageButton? = null
  protected var forward: AppCompatImageButton? = null
  protected var more: AppCompatImageButton? = null
  protected var swipeRefreshLayout: SwipeRefreshLayout? = null
  protected var webView: WebView? = null
  protected var gradient: View? = null
  protected var divider: View? = null
  protected var progressBar: ProgressBar? = null
  protected var menuLayout: RelativeLayout? = null
  protected var shadowLayout: ShadowLayout? = null
  protected var menuBackground: LinearLayout? = null
  protected var menuRefresh: LinearLayout? = null
  protected var menuRefreshTv: TextView? = null
  protected var menuFind: LinearLayout? = null
  protected var menuFindTv: TextView? = null
  protected var menuShareVia: LinearLayout? = null
  protected var menuShareViaTv: TextView? = null
  protected var menuCopyLink: LinearLayout? = null
  protected var menuCopyLinkTv: TextView? = null
  protected var menuOpenWith: LinearLayout? = null
  protected var menuOpenWithTv: TextView? = null
  protected var webLayout: FrameLayout? = null

  var downloadListener = DownloadListener { url, userAgent, contentDisposition, mimetype, contentLength ->
    onDownloadStart(this@FinestWebViewActivity, key, url, userAgent, contentDisposition, mimetype, contentLength)
  }

  protected fun initializeOptions() {
    val intent = intent ?: return
    val finestWebView = intent.getSerializableExtra("FinestWebView") as FinestWebView?

    // set theme before resolving attributes depending on those
    setTheme(((if (finestWebView!!.theme != null) finestWebView.theme else 0)!!))

    // resolve themed attributes
    val typedValue = TypedValue()
    val typedArray = obtainStyledAttributes(typedValue.data, intArrayOf(R.attr.colorPrimaryDark, R.attr.colorPrimary, R.attr.colorAccent, android.R.attr.textColorPrimary, android.R.attr.textColorSecondary, android.R.attr.selectableItemBackground, android.R.attr.selectableItemBackgroundBorderless))
    val colorPrimaryDark = typedArray.getColor(0, ContextCompat.getColor(this, R.color.finestGray))
    val colorPrimary = typedArray.getColor(1, ContextCompat.getColor(this, R.color.finestWhite))
    val colorAccent = typedArray.getColor(2, ContextCompat.getColor(this, R.color.finestBlack))
    val textColorPrimary = typedArray.getColor(3, ContextCompat.getColor(this, R.color.finestBlack))
    val textColorSecondary = typedArray.getColor(4, ContextCompat.getColor(this, R.color.finestSilver))
    val selectableItemBackground = typedArray.getResourceId(5, 0)
    val selectableItemBackgroundBorderless = if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) typedArray.getResourceId(6, 0) else R.drawable.selector_light_theme
    typedArray.recycle()
    key = finestWebView.key!!
    rtl = if (finestWebView.rtl != null) finestWebView.rtl!! else resources.getBoolean(R.bool.is_right_to_left)
    statusBarColor = if (finestWebView.statusBarColor != null) finestWebView.statusBarColor!! else colorPrimaryDark
    toolbarColor = if (finestWebView.toolbarColor != null) finestWebView.toolbarColor!! else colorPrimary
    toolbarScrollFlags = if (finestWebView.toolbarScrollFlags != null) finestWebView.toolbarScrollFlags!! else AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
    iconDefaultColor = if (finestWebView.iconDefaultColor != null) finestWebView.iconDefaultColor!! else colorAccent
    iconDisabledColor = if (finestWebView.iconDisabledColor != null) finestWebView.iconDisabledColor!! else disableColor(iconDefaultColor)
    iconPressedColor = if (finestWebView.iconPressedColor != null) finestWebView.iconPressedColor!! else iconDefaultColor
    iconSelector = if (finestWebView.iconSelector != null) finestWebView.iconSelector!! else selectableItemBackgroundBorderless
    showIconClose = if (finestWebView.showIconClose != null) finestWebView.showIconClose!! else true
    disableIconClose = if (finestWebView.disableIconClose != null) finestWebView.disableIconClose!! else false
    showIconBack = if (finestWebView.showIconBack != null) finestWebView.showIconBack!! else true
    disableIconBack = if (finestWebView.disableIconBack != null) finestWebView.disableIconBack!! else false
    showIconForward = if (finestWebView.showIconForward != null) finestWebView.showIconForward!! else true
    disableIconForward = if (finestWebView.disableIconForward != null) finestWebView.disableIconForward!! else false
    showIconMenu = if (finestWebView.showIconMenu != null) finestWebView.showIconMenu!! else true
    disableIconMenu = if (finestWebView.disableIconMenu != null) finestWebView.disableIconMenu!! else false
    showSwipeRefreshLayout = if (finestWebView.showSwipeRefreshLayout != null) finestWebView.showSwipeRefreshLayout!! else true
    swipeRefreshColor = if (finestWebView.swipeRefreshColor != null) finestWebView.swipeRefreshColor!! else colorAccent
    if (finestWebView.swipeRefreshColors != null) {
      val colors = IntArray(finestWebView.swipeRefreshColors!!.size)
      for (i in finestWebView.swipeRefreshColors!!.indices) {
        colors[i] = finestWebView.swipeRefreshColors!![i]
      }
      swipeRefreshColors = colors
    }
    showDivider = if (finestWebView.showDivider != null) finestWebView.showDivider!! else true
    gradientDivider = if (finestWebView.gradientDivider != null) finestWebView.gradientDivider!! else true
    dividerColor = if (finestWebView.dividerColor != null) finestWebView.dividerColor!! else ContextCompat.getColor(this, R.color.finestBlack10)
    dividerHeight = if (finestWebView.dividerHeight != null) finestWebView.dividerHeight!! else resources.getDimension(R.dimen.defaultDividerHeight)
    showProgressBar = if (finestWebView.showProgressBar != null) finestWebView.showProgressBar!! else true
    progressBarColor = if (finestWebView.progressBarColor != null) finestWebView.progressBarColor!! else colorAccent
    progressBarHeight = if (finestWebView.progressBarHeight != null) finestWebView.progressBarHeight!! else resources.getDimension(R.dimen.defaultProgressBarHeight)
    progressBarPosition = if (finestWebView.progressBarPosition != null) finestWebView.progressBarPosition else ProgressBarPosition.BOTTOM_OF_TOOLBAR
    titleDefault = finestWebView.titleDefault
    updateTitleFromHtml = if (finestWebView.updateTitleFromHtml != null) finestWebView.updateTitleFromHtml!! else true
    titleSize = if (finestWebView.titleSize != null) finestWebView.titleSize!! else resources.getDimension(R.dimen.defaultTitleSize)
    titleFont = if (finestWebView.titleFont != null) finestWebView.titleFont else "Roboto-Medium.ttf"
    finestWebViewTitleColor = if (finestWebView.titleColor != null) finestWebView.titleColor!! else textColorPrimary
    showUrl = if (finestWebView.showUrl != null) finestWebView.showUrl!! else true
    urlSize = if (finestWebView.urlSize != null) finestWebView.urlSize!! else resources.getDimension(R.dimen.defaultUrlSize)
    urlFont = if (finestWebView.urlFont != null) finestWebView.urlFont else "Roboto-Regular.ttf"
    urlColor = if (finestWebView.urlColor != null) finestWebView.urlColor!! else textColorSecondary
    menuColor = if (finestWebView.menuColor != null) finestWebView.menuColor!! else ContextCompat.getColor(this, R.color.finestWhite)
    menuDropShadowColor = if (finestWebView.menuDropShadowColor != null) finestWebView.menuDropShadowColor!! else ContextCompat.getColor(this, R.color.finestBlack10)
    menuDropShadowSize = if (finestWebView.menuDropShadowSize != null) finestWebView.menuDropShadowSize!! else resources.getDimension(R.dimen.defaultMenuDropShadowSize)
    menuSelector = if (finestWebView.menuSelector != null) finestWebView.menuSelector!! else selectableItemBackground
    menuTextSize = if (finestWebView.menuTextSize != null) finestWebView.menuTextSize!! else resources.getDimension(R.dimen.defaultMenuTextSize)
    menuTextFont = if (finestWebView.menuTextFont != null) finestWebView.menuTextFont else "Roboto-Regular.ttf"
    menuTextColor = if (finestWebView.menuTextColor != null) finestWebView.menuTextColor!! else ContextCompat.getColor(this, R.color.finestBlack)
    menuTextGravity = if (finestWebView.menuTextGravity != null) finestWebView.menuTextGravity!! else Gravity.CENTER_VERTICAL or Gravity.START
    menuTextPaddingLeft = if (finestWebView.menuTextPaddingLeft != null) finestWebView.menuTextPaddingLeft!! else if (rtl) resources.getDimension(R.dimen.defaultMenuTextPaddingRight) else resources.getDimension(R.dimen.defaultMenuTextPaddingLeft)
    menuTextPaddingRight = if (finestWebView.menuTextPaddingRight != null) finestWebView.menuTextPaddingRight!! else if (rtl) resources.getDimension(R.dimen.defaultMenuTextPaddingLeft) else resources.getDimension(R.dimen.defaultMenuTextPaddingRight)
    showMenuRefresh = if (finestWebView.showMenuRefresh != null) finestWebView.showMenuRefresh!! else true
    stringResRefresh = if (finestWebView.stringResRefresh != null) finestWebView.stringResRefresh!! else R.string.refresh
    showMenuFind = if (finestWebView.showMenuFind != null) finestWebView.showMenuFind!! else false
    stringResFind = if (finestWebView.stringResFind != null) finestWebView.stringResFind!! else R.string.find
    showMenuShareVia = if (finestWebView.showMenuShareVia != null) finestWebView.showMenuShareVia!! else true
    stringResShareVia = if (finestWebView.stringResShareVia != null) finestWebView.stringResShareVia!! else R.string.share_via
    showMenuCopyLink = if (finestWebView.showMenuCopyLink != null) finestWebView.showMenuCopyLink!! else true
    stringResCopyLink = if (finestWebView.stringResCopyLink != null) finestWebView.stringResCopyLink!! else R.string.copy_link
    showMenuOpenWith = if (finestWebView.showMenuOpenWith != null) finestWebView.showMenuOpenWith!! else true
    stringResOpenWith = if (finestWebView.stringResOpenWith != null) finestWebView.stringResOpenWith!! else R.string.open_with
    animationCloseEnter = if (finestWebView.animationCloseEnter != null) finestWebView.animationCloseEnter!! else R.anim.modal_activity_close_enter
    animationCloseExit = if (finestWebView.animationCloseExit != null) finestWebView.animationCloseExit!! else R.anim.modal_activity_close_exit
    backPressToClose = if (finestWebView.backPressToClose != null) finestWebView.backPressToClose!! else false
    stringResCopiedToClipboard = if (finestWebView.stringResCopiedToClipboard != null) finestWebView.stringResCopiedToClipboard!! else R.string.copied_to_clipboard
    webViewSupportZoom = finestWebView.webViewSupportZoom
    webViewMediaPlaybackRequiresUserGesture = finestWebView.webViewMediaPlaybackRequiresUserGesture
    webViewBuiltInZoomControls = if (finestWebView.webViewBuiltInZoomControls != null) finestWebView.webViewBuiltInZoomControls else false
    webViewDisplayZoomControls = if (finestWebView.webViewDisplayZoomControls != null) finestWebView.webViewDisplayZoomControls else false
    webViewAllowFileAccess = if (finestWebView.webViewAllowFileAccess != null) finestWebView.webViewAllowFileAccess else true
    webViewAllowContentAccess = finestWebView.webViewAllowContentAccess
    webViewLoadWithOverviewMode = if (finestWebView.webViewLoadWithOverviewMode != null) finestWebView.webViewLoadWithOverviewMode else true
    webViewSaveFormData = finestWebView.webViewSaveFormData
    webViewTextZoom = finestWebView.webViewTextZoom
    webViewUseWideViewPort = finestWebView.webViewUseWideViewPort
    webViewSupportMultipleWindows = finestWebView.webViewSupportMultipleWindows
    webViewLayoutAlgorithm = finestWebView.webViewLayoutAlgorithm
    webViewStandardFontFamily = finestWebView.webViewStandardFontFamily
    webViewFixedFontFamily = finestWebView.webViewFixedFontFamily
    webViewSansSerifFontFamily = finestWebView.webViewSansSerifFontFamily
    webViewSerifFontFamily = finestWebView.webViewSerifFontFamily
    webViewCursiveFontFamily = finestWebView.webViewCursiveFontFamily
    webViewFantasyFontFamily = finestWebView.webViewFantasyFontFamily
    webViewMinimumFontSize = finestWebView.webViewMinimumFontSize
    webViewMinimumLogicalFontSize = finestWebView.webViewMinimumLogicalFontSize
    webViewDefaultFontSize = finestWebView.webViewDefaultFontSize
    webViewDefaultFixedFontSize = finestWebView.webViewDefaultFixedFontSize
    webViewLoadsImagesAutomatically = finestWebView.webViewLoadsImagesAutomatically
    webViewBlockNetworkImage = finestWebView.webViewBlockNetworkImage
    webViewBlockNetworkLoads = finestWebView.webViewBlockNetworkLoads
    webViewJavaScriptEnabled = if (finestWebView.webViewJavaScriptEnabled != null) finestWebView.webViewJavaScriptEnabled else true
    webViewAllowUniversalAccessFromFileURLs = finestWebView.webViewAllowUniversalAccessFromFileURLs
    webViewAllowFileAccessFromFileURLs = finestWebView.webViewAllowFileAccessFromFileURLs
    webViewGeolocationDatabasePath = finestWebView.webViewGeolocationDatabasePath
    webViewAppCacheEnabled = if (finestWebView.webViewAppCacheEnabled != null) finestWebView.webViewAppCacheEnabled else true
    webViewAppCachePath = finestWebView.webViewAppCachePath
    webViewDatabaseEnabled = finestWebView.webViewDatabaseEnabled
    webViewDomStorageEnabled = if (finestWebView.webViewDomStorageEnabled != null) finestWebView.webViewDomStorageEnabled else true
    webViewGeolocationEnabled = finestWebView.webViewGeolocationEnabled
    webViewJavaScriptCanOpenWindowsAutomatically = finestWebView.webViewJavaScriptCanOpenWindowsAutomatically
    webViewDefaultTextEncodingName = finestWebView.webViewDefaultTextEncodingName
    webViewUserAgentString = finestWebView.webViewUserAgentString
    webViewNeedInitialFocus = finestWebView.webViewNeedInitialFocus
    webViewCacheMode = finestWebView.webViewCacheMode
    webViewMixedContentMode = finestWebView.webViewMixedContentMode
    webViewOffscreenPreRaster = finestWebView.webViewOffscreenPreRaster
    injectJavaScript = finestWebView.injectJavaScript
    mimeType = finestWebView.mimeType
    encoding = finestWebView.encoding
    data = finestWebView.data
    url = finestWebView.url
  }

  protected fun bindViews() {
    coordinatorLayout = findViewById<View>(R.id.coordinatorLayout) as CoordinatorLayout
    appBar = findViewById<View>(R.id.appBar) as AppBarLayout
    toolbar = findViewById<View>(R.id.toolbar) as Toolbar
    toolbarLayout = findViewById<View>(R.id.toolbarLayout) as RelativeLayout
    title = findViewById<View>(R.id.title) as TextView
    urlTv = findViewById<View>(R.id.url) as TextView
    close = findViewById<View>(R.id.close) as AppCompatImageButton
    back = findViewById<View>(R.id.back) as AppCompatImageButton
    forward = findViewById<View>(R.id.forward) as AppCompatImageButton
    more = findViewById<View>(R.id.more) as AppCompatImageButton
    close!!.setOnClickListener(this)
    back!!.setOnClickListener(this)
    forward!!.setOnClickListener(this)
    more!!.setOnClickListener(this)
    swipeRefreshLayout = findViewById<View>(R.id.swipeRefreshLayout) as SwipeRefreshLayout
    gradient = findViewById(R.id.gradient)
    divider = findViewById(R.id.divider)
    progressBar = findViewById<View>(R.id.progressBar) as ProgressBar
    menuLayout = findViewById<View>(R.id.menuLayout) as RelativeLayout
    shadowLayout = findViewById<View>(R.id.shadowLayout) as ShadowLayout
    menuBackground = findViewById<View>(R.id.menuBackground) as LinearLayout
    menuRefresh = findViewById<View>(R.id.menuRefresh) as LinearLayout
    menuRefreshTv = findViewById<View>(R.id.menuRefreshTv) as TextView
    menuFind = findViewById<View>(R.id.menuFind) as LinearLayout
    menuFindTv = findViewById<View>(R.id.menuFindTv) as TextView
    menuShareVia = findViewById<View>(R.id.menuShareVia) as LinearLayout
    menuShareViaTv = findViewById<View>(R.id.menuShareViaTv) as TextView
    menuCopyLink = findViewById<View>(R.id.menuCopyLink) as LinearLayout
    menuCopyLinkTv = findViewById<View>(R.id.menuCopyLinkTv) as TextView
    menuOpenWith = findViewById<View>(R.id.menuOpenWith) as LinearLayout
    menuOpenWithTv = findViewById<View>(R.id.menuOpenWithTv) as TextView
    webLayout = findViewById<View>(R.id.webLayout) as FrameLayout
    webView = WebView(this)
    webLayout!!.addView(webView)
  }

  protected fun layoutViews() {
    setSupportActionBar(toolbar)
    run { // AppBar
      var toolbarHeight = resources.getDimension(R.dimen.toolbarHeight)
      if (!gradientDivider) {
        toolbarHeight += dividerHeight
      }
      val params = CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, toolbarHeight.toInt())
      appBar!!.layoutParams = params
      coordinatorLayout!!.requestLayout()
    }
    run { // Toolbar
      val toolbarHeight = resources.getDimension(R.dimen.toolbarHeight)
      val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, toolbarHeight.toInt())
      toolbarLayout!!.minimumHeight = toolbarHeight.toInt()
      toolbarLayout!!.layoutParams = params
      coordinatorLayout!!.requestLayout()
    }
    run { // TextViews
      val maxWidth = maxWidth
      title!!.maxWidth = maxWidth
      urlTv!!.maxWidth = maxWidth
      requestCenterLayout()
    }
    run { // Icons
      updateIcon(close, if (rtl) R.drawable.more else R.drawable.close)
      updateIcon(back, R.drawable.back)
      updateIcon(forward, R.drawable.forward)
      updateIcon(more, if (rtl) R.drawable.close else R.drawable.more)
    }
    run { // Divider
      if (gradientDivider) {
        val toolbarHeight = resources.getDimension(R.dimen.toolbarHeight)
        val params = gradient!!.layoutParams as CoordinatorLayout.LayoutParams
        params.setMargins(0, toolbarHeight.toInt(), 0, 0)
        gradient!!.layoutParams = params
      }
    }
    run { // ProgressBar
      progressBar!!.minimumHeight = progressBarHeight.toInt()
      val params = CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, progressBarHeight.toInt())
      val toolbarHeight = resources.getDimension(R.dimen.toolbarHeight)
      when (progressBarPosition) {
        ProgressBarPosition.TOP_OF_TOOLBAR -> params.setMargins(0, 0, 0, 0)
        ProgressBarPosition.BOTTOM_OF_TOOLBAR -> params.setMargins(0, toolbarHeight.toInt() - progressBarHeight.toInt(), 0, 0)
        ProgressBarPosition.TOP_OF_WEBVIEW -> params.setMargins(0, toolbarHeight.toInt(), 0, 0)
        ProgressBarPosition.BOTTOM_OF_WEBVIEW -> params.setMargins(0, getHeight(this) - progressBarHeight.toInt(), 0, 0)
      }
      progressBar!!.layoutParams = params
    }
    run { // WebLayout
      val toolbarHeight = resources.getDimension(R.dimen.toolbarHeight)
      val statusBarHeight = getStatusBarHeight(this)
      val screenHeight = getHeight(this)
      var webLayoutMinimumHeight = screenHeight - toolbarHeight - statusBarHeight
      if (showDivider && !gradientDivider) {
        webLayoutMinimumHeight -= dividerHeight
      }
      webLayout!!.minimumHeight = webLayoutMinimumHeight.toInt()
    }
  }

  @SuppressLint("SetJavaScriptEnabled")
  protected fun initializeViews() {
    setSupportActionBar(toolbar)
    run { // StatusBar
      if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
        val window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = statusBarColor
      }
    }
    run { // AppBar
      appBar!!.addOnOffsetChangedListener(this)
    }
    run { // Toolbar
      toolbar!!.setBackgroundColor(toolbarColor)
      val params = toolbar!!.layoutParams as AppBarLayout.LayoutParams
      params.scrollFlags = toolbarScrollFlags
      toolbar!!.layoutParams = params
    }
    run { // TextViews
      title!!.text = titleDefault
      title!!.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize)
      title!!.setTypeface(TypefaceUtil[this, titleFont!!])
      title!!.setTextColor(titleColor)
      urlTv!!.visibility = if (showUrl) View.VISIBLE else View.GONE
      urlTv!!.text = getHost(url!!)
      urlTv!!.setTextSize(TypedValue.COMPLEX_UNIT_PX, urlSize)
      urlTv!!.setTypeface(TypefaceUtil[this, urlFont!!])
      urlTv!!.setTextColor(urlColor)
      requestCenterLayout()
    }
    run { // Icons
      close!!.setBackgroundResource(iconSelector)
      back!!.setBackgroundResource(iconSelector)
      forward!!.setBackgroundResource(iconSelector)
      more!!.setBackgroundResource(iconSelector)
      close!!.visibility = if (showIconClose) View.VISIBLE else View.GONE
      close!!.isEnabled = !disableIconClose
      if ((showMenuRefresh || showMenuFind || showMenuShareVia || showMenuCopyLink || showMenuOpenWith) && showIconMenu) {
        more!!.visibility = View.VISIBLE
      } else {
        more!!.visibility = View.GONE
      }
      more!!.isEnabled = !disableIconMenu
    }
    run { // WebView
      webView!!.webChromeClient = MyWebChromeClient()
      webView!!.webViewClient = MyWebViewClient()
      webView!!.setDownloadListener(downloadListener)
      val settings = webView!!.settings
      if (webViewSupportZoom != null) {
        settings.setSupportZoom(webViewSupportZoom!!)
      }
      if (webViewMediaPlaybackRequiresUserGesture != null && VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR1) {
        settings.mediaPlaybackRequiresUserGesture = webViewMediaPlaybackRequiresUserGesture!!
      }
      if (webViewBuiltInZoomControls != null) {
        settings.builtInZoomControls = webViewBuiltInZoomControls!!
        if (webViewBuiltInZoomControls as Boolean) { // Remove NestedScrollView to enable BuiltInZoomControls
          (webView!!.parent as ViewGroup).removeAllViews()
          swipeRefreshLayout!!.addView(webView)
          swipeRefreshLayout!!.removeViewAt(1)
        }
      }
      if (webViewDisplayZoomControls != null && VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB) {
        settings.displayZoomControls = webViewDisplayZoomControls!!
      }
      if (webViewAllowFileAccess != null) {
        settings.allowFileAccess = webViewAllowFileAccess!!
      }
      if (webViewAllowContentAccess != null && VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB) {
        settings.allowContentAccess = webViewAllowContentAccess!!
      }
      if (webViewLoadWithOverviewMode != null) {
        settings.loadWithOverviewMode = webViewLoadWithOverviewMode!!
      }
      if (webViewSaveFormData != null) {
        settings.saveFormData = webViewSaveFormData!!
      }
      if (webViewTextZoom != null && VERSION.SDK_INT >= VERSION_CODES.ICE_CREAM_SANDWICH) {
        settings.textZoom = webViewTextZoom!!
      }
      if (webViewUseWideViewPort != null) {
        settings.useWideViewPort = webViewUseWideViewPort!!
      }
      if (webViewSupportMultipleWindows != null) {
        settings.setSupportMultipleWindows(webViewSupportMultipleWindows!!)
      }
      if (webViewLayoutAlgorithm != null) {
        settings.layoutAlgorithm = webViewLayoutAlgorithm
      }
      if (webViewStandardFontFamily != null) {
        settings.standardFontFamily = webViewStandardFontFamily
      }
      if (webViewFixedFontFamily != null) {
        settings.fixedFontFamily = webViewFixedFontFamily
      }
      if (webViewSansSerifFontFamily != null) {
        settings.sansSerifFontFamily = webViewSansSerifFontFamily
      }
      if (webViewSerifFontFamily != null) {
        settings.serifFontFamily = webViewSerifFontFamily
      }
      if (webViewCursiveFontFamily != null) {
        settings.cursiveFontFamily = webViewCursiveFontFamily
      }
      if (webViewFantasyFontFamily != null) {
        settings.fantasyFontFamily = webViewFantasyFontFamily
      }
      if (webViewMinimumFontSize != null) {
        settings.minimumFontSize = webViewMinimumFontSize!!
      }
      if (webViewMinimumLogicalFontSize != null) {
        settings.minimumLogicalFontSize = webViewMinimumLogicalFontSize!!
      }
      if (webViewDefaultFontSize != null) {
        settings.defaultFontSize = webViewDefaultFontSize!!
      }
      if (webViewDefaultFixedFontSize != null) {
        settings.defaultFixedFontSize = webViewDefaultFixedFontSize!!
      }
      if (webViewLoadsImagesAutomatically != null) {
        settings.loadsImagesAutomatically = webViewLoadsImagesAutomatically!!
      }
      if (webViewBlockNetworkImage != null) {
        settings.blockNetworkImage = webViewBlockNetworkImage!!
      }
      if (webViewBlockNetworkLoads != null && VERSION.SDK_INT >= VERSION_CODES.FROYO) {
        settings.blockNetworkLoads = webViewBlockNetworkLoads!!
      }
      if (webViewJavaScriptEnabled != null) {
        settings.javaScriptEnabled = webViewJavaScriptEnabled!!
      }
      if (webViewAllowUniversalAccessFromFileURLs != null && VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
        settings.allowUniversalAccessFromFileURLs = webViewAllowUniversalAccessFromFileURLs!!
      }
      if (webViewAllowFileAccessFromFileURLs != null && VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
        settings.allowFileAccessFromFileURLs = webViewAllowFileAccessFromFileURLs!!
      }
      if (webViewGeolocationDatabasePath != null) {
        settings.setGeolocationDatabasePath(webViewGeolocationDatabasePath)
      }
      if (webViewAppCacheEnabled != null) {
        settings.setAppCacheEnabled(webViewAppCacheEnabled!!)
      }
      if (webViewAppCachePath != null) {
        settings.setAppCachePath(webViewAppCachePath)
      }
      if (webViewDatabaseEnabled != null) {
        settings.databaseEnabled = webViewDatabaseEnabled!!
      }
      if (webViewDomStorageEnabled != null) {
        settings.domStorageEnabled = webViewDomStorageEnabled!!
      }
      if (webViewGeolocationEnabled != null) {
        settings.setGeolocationEnabled(webViewGeolocationEnabled!!)
      }
      if (webViewJavaScriptCanOpenWindowsAutomatically != null) {
        settings.javaScriptCanOpenWindowsAutomatically = webViewJavaScriptCanOpenWindowsAutomatically!!
      }
      if (webViewDefaultTextEncodingName != null) {
        settings.defaultTextEncodingName = webViewDefaultTextEncodingName
      }
      if (webViewUserAgentString != null) {
        settings.setUserAgentString(webViewUserAgentString)
      }
      if (webViewNeedInitialFocus != null) {
        settings.setNeedInitialFocus(webViewNeedInitialFocus!!)
      }
      if (webViewCacheMode != null) {
        settings.cacheMode = webViewCacheMode!!
      }
      if (webViewMixedContentMode != null && VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
        settings.mixedContentMode = webViewMixedContentMode!!
      }
      if (webViewOffscreenPreRaster != null && VERSION.SDK_INT >= VERSION_CODES.M) {
        settings.offscreenPreRaster = webViewOffscreenPreRaster!!
      }

      //            // Other webview options
      //            webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
      //            webView.setScrollbarFadingEnabled(false);
      //            //Additional Webview Properties
      //            webView.setSoundEffectsEnabled(true);
      //            webView.setHorizontalFadingEdgeEnabled(false);
      //            webView.setKeepScreenOn(true);
      //            webView.setScrollbarFadingEnabled(true);
      //            webView.setVerticalFadingEdgeEnabled(false);
      if (data != null) {
        webView!!.loadData(data!!, mimeType, encoding)
      } else if (url != null) {
        webView!!.loadUrl(url!!)
      }
    }
    run { // SwipeRefreshLayout
      swipeRefreshLayout!!.isEnabled = showSwipeRefreshLayout
      if (showSwipeRefreshLayout) {
        swipeRefreshLayout!!.post { swipeRefreshLayout!!.isRefreshing = true }
      }
      if (swipeRefreshColors == null) {
        swipeRefreshLayout!!.setColorSchemeColors(swipeRefreshColor)
      } else {
        swipeRefreshLayout!!.setColorSchemeColors(swipeRefreshColor)
      }
      swipeRefreshLayout!!.setOnRefreshListener { webView!!.reload() }
    }
    run { // Divider
      gradient!!.visibility = if (showDivider && gradientDivider) View.VISIBLE else View.GONE
      divider!!.visibility = if (showDivider && !gradientDivider) View.VISIBLE else View.GONE
      if (gradientDivider) {
        val dividerWidth = getWidth(this)
        val bitmap = getGradientBitmap(dividerWidth, dividerHeight.toInt(), dividerColor)
        val drawable = BitmapDrawable(resources, bitmap)
        gradient!!.background = drawable
        val params = gradient!!.layoutParams as CoordinatorLayout.LayoutParams
        params.height = dividerHeight.toInt()
        gradient!!.layoutParams = params
      } else {
        divider!!.setBackgroundColor(dividerColor)
        val params = divider!!.layoutParams as LinearLayout.LayoutParams
        params.height = dividerHeight.toInt()
        divider!!.layoutParams = params
      }
    }
    run { // ProgressBar
      progressBar!!.visibility = if (showProgressBar) View.VISIBLE else View.GONE
      progressBar!!.progressDrawable.setColorFilter(progressBarColor, PorterDuff.Mode.SRC_IN)
      progressBar!!.minimumHeight = progressBarHeight.toInt()
      val params = CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, progressBarHeight.toInt())
      val toolbarHeight = resources.getDimension(R.dimen.toolbarHeight)
      when (progressBarPosition) {
        ProgressBarPosition.TOP_OF_TOOLBAR -> params.setMargins(0, 0, 0, 0)
        ProgressBarPosition.BOTTOM_OF_TOOLBAR -> params.setMargins(0, toolbarHeight.toInt() - progressBarHeight.toInt(), 0, 0)
        ProgressBarPosition.TOP_OF_WEBVIEW -> params.setMargins(0, toolbarHeight.toInt(), 0, 0)
        ProgressBarPosition.BOTTOM_OF_WEBVIEW -> params.setMargins(0, getHeight(this) - progressBarHeight.toInt(), 0, 0)
      }
      progressBar!!.layoutParams = params
    }
    run { // Menu
      val drawable = GradientDrawable()
      drawable.cornerRadius = resources.getDimension(R.dimen.defaultMenuCornerRadius)
      drawable.setColor(menuColor)
      if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
        menuBackground!!.background = drawable
      } else {
        menuBackground!!.setBackgroundDrawable(drawable)
      }
      shadowLayout!!.setShadowColor(menuDropShadowColor)
      shadowLayout!!.setShadowSize(menuDropShadowSize)
      val params = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
      val margin = (resources.getDimension(R.dimen.defaultMenuLayoutMargin) - menuDropShadowSize).toInt()
      params.setMargins(0, margin, margin, 0)
      params.addRule(RelativeLayout.ALIGN_PARENT_TOP)
      params.addRule(if (rtl) RelativeLayout.ALIGN_PARENT_LEFT else RelativeLayout.ALIGN_PARENT_RIGHT)
      shadowLayout!!.layoutParams = params
      menuRefresh!!.visibility = if (showMenuRefresh) View.VISIBLE else View.GONE
      menuRefresh!!.setBackgroundResource(menuSelector)
      menuRefresh!!.gravity = menuTextGravity
      menuRefreshTv!!.setText(stringResRefresh)
      menuRefreshTv!!.setTextSize(TypedValue.COMPLEX_UNIT_PX, menuTextSize)
      menuRefreshTv!!.setTypeface(TypefaceUtil[this, menuTextFont!!])
      menuRefreshTv!!.setTextColor(menuTextColor)
      menuRefreshTv!!.setPadding(menuTextPaddingLeft.toInt(), 0, menuTextPaddingRight.toInt(), 0)
      menuFind!!.visibility = if (showMenuFind) View.VISIBLE else View.GONE
      menuFind!!.setBackgroundResource(menuSelector)
      menuFind!!.gravity = menuTextGravity
      menuFindTv!!.setText(stringResFind)
      menuFindTv!!.setTextSize(TypedValue.COMPLEX_UNIT_PX, menuTextSize)
      menuFindTv!!.setTypeface(TypefaceUtil[this, menuTextFont!!])
      menuFindTv!!.setTextColor(menuTextColor)
      menuFindTv!!.setPadding(menuTextPaddingLeft.toInt(), 0, menuTextPaddingRight.toInt(), 0)
      menuShareVia!!.visibility = if (showMenuShareVia) View.VISIBLE else View.GONE
      menuShareVia!!.setBackgroundResource(menuSelector)
      menuShareVia!!.gravity = menuTextGravity
      menuShareViaTv!!.setText(stringResShareVia)
      menuShareViaTv!!.setTextSize(TypedValue.COMPLEX_UNIT_PX, menuTextSize)
      menuShareViaTv!!.setTypeface(TypefaceUtil[this, menuTextFont!!])
      menuShareViaTv!!.setTextColor(menuTextColor)
      menuShareViaTv!!.setPadding(menuTextPaddingLeft.toInt(), 0, menuTextPaddingRight.toInt(), 0)
      menuCopyLink!!.visibility = if (showMenuCopyLink) View.VISIBLE else View.GONE
      menuCopyLink!!.setBackgroundResource(menuSelector)
      menuCopyLink!!.gravity = menuTextGravity
      menuCopyLinkTv!!.setText(stringResCopyLink)
      menuCopyLinkTv!!.setTextSize(TypedValue.COMPLEX_UNIT_PX, menuTextSize)
      menuCopyLinkTv!!.setTypeface(TypefaceUtil[this, menuTextFont!!])
      menuCopyLinkTv!!.setTextColor(menuTextColor)
      menuCopyLinkTv!!.setPadding(menuTextPaddingLeft.toInt(), 0, menuTextPaddingRight.toInt(), 0)
      menuOpenWith!!.visibility = if (showMenuOpenWith) View.VISIBLE else View.GONE
      menuOpenWith!!.setBackgroundResource(menuSelector)
      menuOpenWith!!.gravity = menuTextGravity
      menuOpenWithTv!!.setText(stringResOpenWith)
      menuOpenWithTv!!.setTextSize(TypedValue.COMPLEX_UNIT_PX, menuTextSize)
      menuOpenWithTv!!.setTypeface(TypefaceUtil[this, menuTextFont!!])
      menuOpenWithTv!!.setTextColor(menuTextColor)
      menuOpenWithTv!!.setPadding(menuTextPaddingLeft.toInt(), 0, menuTextPaddingRight.toInt(), 0)
    }
  }

  protected val maxWidth: Int
    protected get() = if (forward!!.visibility == View.VISIBLE) {
      getWidth(this) - dpToPx(this, 100)
    } else {
      getWidth(this) - dpToPx(this, 52)
    }

  protected fun updateIcon(icon: ImageButton?, @DrawableRes drawableRes: Int) {
    val states = StateListDrawable()
    run {
      val bitmap = getColoredBitmap(this, drawableRes, iconPressedColor)
      val drawable = BitmapDrawable(resources, bitmap)
      states.addState(intArrayOf(android.R.attr.state_pressed), drawable)
    }
    run {
      val bitmap = getColoredBitmap(this, drawableRes, iconDisabledColor)
      val drawable = BitmapDrawable(resources, bitmap)
      states.addState(intArrayOf(-android.R.attr.state_enabled), drawable)
    }
    run {
      val bitmap = getColoredBitmap(this, drawableRes, iconDefaultColor)
      val drawable = BitmapDrawable(resources, bitmap)
      states.addState(intArrayOf(), drawable)
    }
    icon!!.setImageDrawable(states)

    //        int[][] states = new int[][]{
    //                new int[]{-android.R.attr.state_enabled}, // disabled
    //                new int[]{android.R.attr.state_pressed}, // pressed
    //                new int[]{}  // default
    //        };
    //
    //        int[] colors = new int[]{
    //                iconDisabledColor,
    //                iconPressedColor,
    //                iconDefaultColor
    //        };
    //
    //        ColorStateList colorStateList = new ColorStateList(states, colors);
    //
    //        Drawable drawable = ContextCompat.getDrawable(this, drawableRes);
    //        if (APILevel.require(21)) {
    //            VectorDrawable vectorDrawable = (VectorDrawable) drawable;
    //            vectorDrawable.setTintList(colorStateList);
    //            icon.setImageDrawable(vectorDrawable);
    //        } else {
    //            VectorDrawableCompat vectorDrawable = (VectorDrawableCompat) drawable;
    //            vectorDrawable.setTintList(colorStateList);
    //            icon.setImageDrawable(vectorDrawable);
    //        }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initializeOptions()
    setContentView(R.layout.finest_web_view)
    bindViews()
    layoutViews()
    initializeViews()
  }

  override fun onBackPressed() {
    if (menuLayout!!.visibility == View.VISIBLE) {
      hideMenu()
    } else if (backPressToClose || !webView!!.canGoBack()) {
      exitActivity()
    } else {
      webView!!.goBack()
    }
  }

  override fun onClick(v: View) {
    val viewId = v.id
    if (viewId == R.id.close) {
      if (rtl) {
        showMenu()
      } else {
        exitActivity()
      }
    } else if (viewId == R.id.back) {
      if (rtl) {
        webView!!.goForward()
      } else {
        webView!!.goBack()
      }
    } else if (viewId == R.id.forward) {
      if (rtl) {
        webView!!.goBack()
      } else {
        webView!!.goForward()
      }
    } else if (viewId == R.id.more) {
      if (rtl) {
        exitActivity()
      } else {
        showMenu()
      }
    } else if (viewId == R.id.menuLayout) {
      hideMenu()
    } else if (viewId == R.id.menuRefresh) {
      webView!!.reload()
      hideMenu()
    } else if (viewId == R.id.menuFind) {
      webView!!.showFindDialog("", true)
      hideMenu()
    } else if (viewId == R.id.menuShareVia) {
      val sendIntent = Intent()
      sendIntent.action = Intent.ACTION_SEND
      sendIntent.putExtra(Intent.EXTRA_TEXT, webView!!.url)
      sendIntent.type = "text/plain"
      startActivity(Intent.createChooser(sendIntent, resources.getString(stringResShareVia)))
      hideMenu()
    } else if (viewId == R.id.menuCopyLink) {
      val clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
      val clip = ClipData.newPlainText("ClipboardManagerUtil", webView!!.url)
      clipboardManager.setPrimaryClip(clip)
      val snackbar = Snackbar.make(coordinatorLayout!!, getString(stringResCopiedToClipboard), Snackbar.LENGTH_LONG)
      val snackbarView = snackbar.view
      snackbarView.setBackgroundColor(toolbarColor)
      if (snackbarView is ViewGroup) {
        updateChildTextView(snackbarView)
      }
      snackbar.show()
      hideMenu()
    } else if (viewId == R.id.menuOpenWith) {
      val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(webView!!.url))
      startActivity(browserIntent)
      hideMenu()
    }
  }

  protected fun updateChildTextView(viewGroup: ViewGroup?) {
    if (viewGroup == null || viewGroup.childCount == 0) {
      return
    }
    for (i in 0 until viewGroup.childCount) {
      val view = viewGroup.getChildAt(i)
      if (view is TextView) {
        val textView = view
        textView.setTextColor(titleColor)
        textView.typeface = TypefaceUtil[this, titleFont!!]
        textView.setLineSpacing(0f, 1.1f)
        textView.includeFontPadding = false
      }
      if (view is ViewGroup) {
        updateChildTextView(view)
      }
    }
  }

  protected fun showMenu() {
    menuLayout!!.visibility = View.VISIBLE
    val popupAnim = AnimationUtils.loadAnimation(this, R.anim.popup_flyout_show)
    shadowLayout!!.startAnimation(popupAnim)
  }

  protected fun hideMenu() {
    val popupAnim = AnimationUtils.loadAnimation(this, R.anim.popup_flyout_hide)
    shadowLayout!!.startAnimation(popupAnim)
    popupAnim.setAnimationListener(object : AnimationListener {
      override fun onAnimationStart(animation: Animation) {}
      override fun onAnimationEnd(animation: Animation) {
        menuLayout!!.visibility = View.GONE
      }

      override fun onAnimationRepeat(animation: Animation) {}
    })
  }

  protected fun exitActivity() {
    super.onBackPressed()
    overridePendingTransition(animationCloseEnter, animationCloseExit)
  }

  override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
    if (toolbarScrollFlags == 0) {
      return
    }
    gradient?.translationY = verticalOffset.toFloat()
    gradient?.alpha = 1 - Math.abs(verticalOffset).toFloat() / appBarLayout.totalScrollRange.toFloat()
    when (progressBarPosition) {
      ProgressBarPosition.BOTTOM_OF_TOOLBAR -> progressBar?.translationY = verticalOffset.toFloat().coerceAtLeast(progressBarHeight - appBarLayout.totalScrollRange)
      ProgressBarPosition.TOP_OF_WEBVIEW -> progressBar?.translationY = verticalOffset.toFloat()
      ProgressBarPosition.TOP_OF_TOOLBAR, ProgressBarPosition.BOTTOM_OF_WEBVIEW -> {
      }
      else -> {
      }
    }
    menuLayout?.translationY = verticalOffset.toFloat().coerceAtLeast(-resources.getDimension(R.dimen.defaultMenuLayoutMargin))
  }

  protected fun requestCenterLayout() {
    val maxWidth: Int
    maxWidth = if (webView!!.canGoBack() || webView!!.canGoForward()) {
      getWidth(this) - dpToPx(this, 48) * 4
    } else {
      getWidth(this) - dpToPx(this, 48) * 2
    }
    title!!.maxWidth = maxWidth
    urlTv!!.maxWidth = maxWidth
    title!!.requestLayout()
    urlTv!!.requestLayout()
  }

  override fun onConfigurationChanged(newConfig: Configuration) {
    super.onConfigurationChanged(newConfig)
    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
      layoutViews()
    } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
      layoutViews()
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    unregister(this@FinestWebViewActivity, key)
    if (webView == null) {
      return
    }
    webView!!.onPause()
    destroyWebView()
  }

  // Wait for zoom control to fade away
  // https://code.google.com/p/android/issues/detail?id=15694
  // http://stackoverflow.com/a/5966151/1797648
  private fun destroyWebView() {
    Handler().postDelayed({
                            if (webView != null) {
                              webView!!.destroy()
                            }
                          }, ViewConfiguration.getZoomControlsTimeout() + 1000L)
  }

  inner class MyWebChromeClient : WebChromeClient() {
    override fun onProgressChanged(view: WebView, progress: Int) {
      var progress = progress
      onProgressChanged(this@FinestWebViewActivity, key, progress)
      if (showSwipeRefreshLayout) {
        if (swipeRefreshLayout!!.isRefreshing && progress == 100) {
          swipeRefreshLayout!!.post { swipeRefreshLayout!!.isRefreshing = false }
        }
        if (!swipeRefreshLayout!!.isRefreshing && progress != 100) {
          swipeRefreshLayout!!.post { swipeRefreshLayout!!.isRefreshing = true }
        }
      }
      if (progress == 100) {
        progress = 0
      }
      progressBar!!.progress = progress
    }

    override fun onReceivedTitle(view: WebView, title: String) {
      onReceivedTitle(this@FinestWebViewActivity, key, title)
    }

    override fun onReceivedTouchIconUrl(view: WebView, url: String, precomposed: Boolean) {
      onReceivedTouchIconUrl(this@FinestWebViewActivity, key, url, precomposed)
    }
  }

  inner class MyWebViewClient : WebViewClient() {
    override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
      onPageStarted(this@FinestWebViewActivity, key, url)
      if (!url.contains("docs.google.com") && url.endsWith(".pdf")) {
        webView!!.loadUrl("http://docs.google.com/gview?embedded=true&url=$url")
      }
    }

    override fun onPageFinished(view: WebView, url: String) {
      onPageFinished(this@FinestWebViewActivity, key, url)
      if (updateTitleFromHtml) {
        title!!.text = view.title
      }
      urlTv!!.text = getHost(url)
      requestCenterLayout()
      if (view.canGoBack() || view.canGoForward()) {
        back!!.visibility = if (showIconBack) View.VISIBLE else View.GONE
        forward!!.visibility = if (showIconForward) View.VISIBLE else View.GONE
        back!!.isEnabled = !disableIconBack && if (rtl) view.canGoForward() else view.canGoBack()
        forward!!.isEnabled = !disableIconForward && if (rtl) view.canGoBack() else view.canGoForward()
      } else {
        back!!.visibility = View.GONE
        forward!!.visibility = View.GONE
      }
      if (injectJavaScript != null) {
        if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
          webView!!.evaluateJavascript(injectJavaScript!!, null)
        }
      }
    }

    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
      return if (url.endsWith(".mp4")) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(Uri.parse(url), "video/*")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        view.context.startActivity(intent) // If we return true, onPageStarted, onPageFinished won't be called.
        true
      } else if (url.startsWith("tel:") || url.startsWith("sms:") || url.startsWith("smsto:") || url.startsWith("mms:") || url.startsWith("mmsto:")) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        view.context.startActivity(intent)
        true // If we return true, onPageStarted, onPageFinished won't be called.
      } else if (url.startsWith("mailto:")) {
        val mt = MailTo.parse(url)
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.type = "text/html"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(mt.to))
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, mt.subject)
        emailIntent.putExtra(Intent.EXTRA_CC, mt.cc)
        emailIntent.putExtra(Intent.EXTRA_TEXT, mt.body)
        startActivity(emailIntent)
        true
      } else {
        super.shouldOverrideUrlLoading(view, url)
      }
    }

    override fun onLoadResource(view: WebView, url: String) {
      onLoadResource(this@FinestWebViewActivity, key, url)
    }

    override fun onPageCommitVisible(view: WebView, url: String) {
      onPageCommitVisible(this@FinestWebViewActivity, key, url)
    }
  }
}