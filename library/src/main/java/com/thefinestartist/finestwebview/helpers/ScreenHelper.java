package com.thefinestartist.finestwebview.helpers;

/**
 * Copyright 2013 The Finest Artist
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

public class ScreenHelper {

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    public static int getWidth(Context context) {

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point size = new Point();
            display.getSize(size);
            return size.x;
        } else
            return display.getWidth();
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    // Whole Screen includes StatusBar and ActionBar
    public static int getHeight(Context context) {

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point size = new Point();
            display.getSize(size);
            return size.y;
        } else
            return display.getHeight();
    }

    // StatusBar
    public static int getSBHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    // ActionBar
    public static int getABHeight(Context context) {

        int actionBarHeight = 0;
        TypedValue tva = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tva, true))
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tva.data, context
                    .getResources()
                    .getDisplayMetrics());
        return actionBarHeight;
    }

    // Content without StatusBar and ActionBar
    public static int getContentHeight(Context context) {
        return getHeight(context) - getSBHeight(context) - getABHeight(context);
    }

    @SuppressLint("NewApi")
    public static int getNBarHeight(@NonNull Activity activity) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            DisplayMetrics metrics = new DisplayMetrics();
//            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
//            int usableHeight = metrics.heightPixels;
//            activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
//            int realHeight = metrics.heightPixels;
//            if (realHeight > usableHeight)
//                return realHeight - usableHeight;
//            else
//                return 0;
//        }
//        return 0;
        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }
}