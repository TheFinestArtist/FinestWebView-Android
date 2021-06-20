package com.thefinestartist.finestwebview.helpers;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public class DisplayUtil {

  public static int getWidth(Context context) {
    WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = windowManager.getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    return size.x;
  }

  public static int getHeight(Context context) {
    WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = windowManager.getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    return size.y;
  }

  public static int getStatusBarHeight(Context context) {
    int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
    return resourceId > 0 ? context.getResources().getDimensionPixelSize(resourceId) : 0;
  }
}
