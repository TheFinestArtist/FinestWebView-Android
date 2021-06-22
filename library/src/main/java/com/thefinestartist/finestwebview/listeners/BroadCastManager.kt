package com.thefinestartist.finestwebview.listeners

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager

/**
 * Created by TheFinestArtist on 1/26/16.
 */
class BroadCastManager(
  context: Context,
  private val key: Int,
  private val listeners: List<WebViewListener>
) {
  private val manager: LocalBroadcastManager = LocalBroadcastManager.getInstance(context)
  private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
      val key = intent.getIntExtra(EXTRA_KEY, Int.MIN_VALUE)
      if (this@BroadCastManager.key == key) {
        handleIntent(intent)
      }
    }
  }

  init {
    manager.registerReceiver(receiver, IntentFilter(WEBVIEW_EVENT))
  }

  private fun handleIntent(intent: Intent) {
    when (intent.getSerializableExtra(EXTRA_TYPE)) {
      Type.PROGRESS_CHANGED -> onProgressChanged(intent)
      Type.RECEIVED_TITLE -> onReceivedTitle(intent)
      Type.RECEIVED_TOUCH_ICON_URL -> onReceivedTouchIconUrl(intent)
      Type.PAGE_STARTED -> onPageStarted(intent)
      Type.PAGE_FINISHED -> onPageFinished(intent)
      Type.LOAD_RESOURCE -> onLoadResource(intent)
      Type.PAGE_COMMIT_VISIBLE -> onPageCommitVisible(intent)
      Type.DOWNLOADED_START -> onDownloadStart(intent)
      Type.UNREGISTER -> unregister()
    }
  }

  private fun onProgressChanged(intent: Intent) {
    for (listener in listeners) {
      listener.onProgressChanged(intent.getIntExtra(EXTRA_PROGRESS, 0))
    }
  }

  private fun onReceivedTitle(intent: Intent) {
    for (listener in listeners) {
      listener.onReceivedTitle(intent.getStringExtra(EXTRA_TITLE))
    }
  }

  private fun onReceivedTouchIconUrl(intent: Intent) {
    for (listener in listeners) {
      listener.onReceivedTouchIconUrl(
        intent.getStringExtra(EXTRA_URL),
        intent.getBooleanExtra(EXTRA_PRECOMPOSED, false)
      )
    }
  }

  private fun onPageStarted(intent: Intent) {
    for (listener in listeners) {
      listener.onPageStarted(intent.getStringExtra(EXTRA_URL))
    }
  }

  private fun onPageFinished(intent: Intent) {
    for (listener in listeners) {
      listener.onPageFinished(intent.getStringExtra(EXTRA_URL))
    }
  }

  private fun onLoadResource(intent: Intent) {
    for (listener in listeners) {
      listener.onLoadResource(intent.getStringExtra(EXTRA_URL))
    }
  }

  private fun onPageCommitVisible(intent: Intent) {
    for (listener in listeners) {
      listener.onPageCommitVisible(intent.getStringExtra(EXTRA_URL))
    }
  }

  private fun onDownloadStart(intent: Intent) {
    for (listener in listeners) {
      listener.onDownloadStart(
        intent.getStringExtra(EXTRA_URL),
        intent.getStringExtra(EXTRA_USER_AGENT),
        intent.getStringExtra(EXTRA_CONTENT_DISPOSITION),
        intent.getStringExtra(EXTRA_MIME_TYPE),
        intent.getLongExtra(EXTRA_CONTENT_LENGTH, 0L)
      )
    }
  }

  private fun unregister() {
    manager.unregisterReceiver(receiver)
  }

  enum class Type {
    PROGRESS_CHANGED,
    RECEIVED_TITLE,
    RECEIVED_TOUCH_ICON_URL,
    PAGE_STARTED,
    PAGE_FINISHED,
    LOAD_RESOURCE,
    PAGE_COMMIT_VISIBLE,
    DOWNLOADED_START,
    UNREGISTER
  }

  companion object {
    const val WEBVIEW_EVENT = "WEBVIEW_EVENT"
    const val EXTRA_KEY = "EXTRA_KEY"
    const val EXTRA_TYPE = "EXTRA_TYPE"
    const val EXTRA_URL = "EXTRA_URL"
    const val EXTRA_TITLE = "EXTRA_TITLE"
    const val EXTRA_PROGRESS = "EXTRA_PROGRESS"
    const val EXTRA_PRECOMPOSED = "EXTRA_PRECOMPOSED"
    const val EXTRA_USER_AGENT = "EXTRA_USER_AGENT"
    const val EXTRA_CONTENT_DISPOSITION = "EXTRA_CONTENT_DISPOSITION"
    const val EXTRA_MIME_TYPE = "EXTRA_MIME_TYPE"
    const val EXTRA_CONTENT_LENGTH = "EXTRA_CONTENT_LENGTH"

    // Base Static Methods
    private fun getBaseIntent(key: Int, type: Type): Intent {
      return Intent(WEBVIEW_EVENT).putExtra(EXTRA_KEY, key).putExtra(EXTRA_TYPE, type)
    }

    private fun sendBroadCast(context: Context, intent: Intent) {
      LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
    }

    // Handle Each Event Type
    @JvmStatic
    fun onProgressChanged(context: Context, key: Int, progress: Int) {
      val intent = getBaseIntent(key, Type.PROGRESS_CHANGED).putExtra(EXTRA_PROGRESS, progress)
      sendBroadCast(context, intent)
    }

    @JvmStatic
    fun onReceivedTitle(context: Context, key: Int, title: String?) {
      val intent = getBaseIntent(key, Type.RECEIVED_TITLE).putExtra(EXTRA_TITLE, title)
      sendBroadCast(context, intent)
    }

    @JvmStatic
    fun onReceivedTouchIconUrl(context: Context, key: Int, url: String?, precomposed: Boolean) {
      val intent =
        getBaseIntent(key, Type.RECEIVED_TOUCH_ICON_URL)
          .putExtra(EXTRA_URL, url)
          .putExtra(EXTRA_PRECOMPOSED, precomposed)
      sendBroadCast(context, intent)
    }

    @JvmStatic
    fun onPageStarted(context: Context, key: Int, url: String?) {
      val intent = getBaseIntent(key, Type.PAGE_STARTED).putExtra(EXTRA_URL, url)
      sendBroadCast(context, intent)
    }

    @JvmStatic
    fun onPageFinished(context: Context, key: Int, url: String?) {
      val intent = getBaseIntent(key, Type.PAGE_FINISHED).putExtra(EXTRA_URL, url)
      sendBroadCast(context, intent)
    }

    @JvmStatic
    fun onLoadResource(context: Context, key: Int, url: String?) {
      val intent = getBaseIntent(key, Type.LOAD_RESOURCE).putExtra(EXTRA_URL, url)
      sendBroadCast(context, intent)
    }

    @JvmStatic
    fun onPageCommitVisible(context: Context, key: Int, url: String?) {
      val intent = getBaseIntent(key, Type.PAGE_COMMIT_VISIBLE).putExtra(EXTRA_URL, url)
      sendBroadCast(context, intent)
    }

    @JvmStatic
    fun onDownloadStart(
      context: Context,
      key: Int,
      url: String?,
      userAgent: String?,
      contentDisposition: String?,
      mimeType: String?,
      contentLength: Long
    ) {
      val intent =
        getBaseIntent(key, Type.DOWNLOADED_START)
          .putExtra(EXTRA_URL, url)
          .putExtra(EXTRA_USER_AGENT, userAgent)
          .putExtra(EXTRA_CONTENT_DISPOSITION, contentDisposition)
          .putExtra(EXTRA_MIME_TYPE, mimeType)
          .putExtra(EXTRA_CONTENT_LENGTH, contentLength)
      sendBroadCast(context, intent)
    }

    @JvmStatic
    fun unregister(context: Context, key: Int) {
      val intent = getBaseIntent(key, Type.UNREGISTER)
      sendBroadCast(context, intent)
    }
  }
}