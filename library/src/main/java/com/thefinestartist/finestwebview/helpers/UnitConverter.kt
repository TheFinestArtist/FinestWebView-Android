package com.thefinestartist.finestwebview.helpers

import android.content.Context

object UnitConverter {
  @JvmStatic
  fun dpToPx(context: Context, dp: Int): Int {
    return (dp * context.resources.displayMetrics.density + 0.5f).toInt()
  }
}