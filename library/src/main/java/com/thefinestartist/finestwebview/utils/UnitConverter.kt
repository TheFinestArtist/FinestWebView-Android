package com.thefinestartist.finestwebview.utils

import android.content.Context

object UnitConverter {
  fun dpToPx(context: Context, dp: Int): Int {
    return (dp * context.resources.displayMetrics.density + 0.5f).toInt()
  }
}