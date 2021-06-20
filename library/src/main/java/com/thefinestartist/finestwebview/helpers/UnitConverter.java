package com.thefinestartist.finestwebview.helpers;

import android.content.Context;

public class UnitConverter {
  public static int dpToPx(Context context, int dp) {
    return (int) (dp * context.getResources().getDisplayMetrics().density + 0.5f);
  }
}
