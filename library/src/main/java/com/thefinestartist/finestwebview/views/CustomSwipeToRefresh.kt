package com.thefinestartist.finestwebview.views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.webkit.WebView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.thefinestartist.finestwebview.utils.UnitConverter.dpToPx

/**
 * Created by TheFinestArtist on 3/12/16.
 */
class CustomSwipeToRefresh : SwipeRefreshLayout {
  private var webView: WebView? = null

  constructor(context: Context) : super(context)
  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

  private fun initializeBuffer() {
    scrollBuffer = dpToPx(context, SCROLL_BUFFER_DIMEN)
  }

  override fun addView(child: View) {
    super.addView(child)
    if (child is WebView) {
      webView = child
    }
  }

  override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
    return webView != null
      && webView!!.scrollY <= scrollBuffer
      && super.onInterceptTouchEvent(event)
  }

  companion object {
    private const val SCROLL_BUFFER_DIMEN = 1
    private var scrollBuffer = 0
  }
}