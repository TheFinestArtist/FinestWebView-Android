package com.thefinestartist.finestwebview.helpers;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * DipPixelHelper
 *
 * @author The Finest Artist
 */
public class DipPixelHelper {

    public static float getPixel(Context context, int dip) {
        Resources r = context.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, r.getDisplayMetrics());
    }

    public static float getPixel(Context context, float dip) {
        Resources r = context.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, r.getDisplayMetrics());
    }
}
