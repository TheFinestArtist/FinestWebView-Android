package com.thefinestartist.finestwebview.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

/**
 * Created by Leonardo on 11/21/15.
 */
public class BitmapHelper {

    private BitmapHelper() {
    }

    public static Bitmap getColoredBitmap(@NonNull Bitmap bitmap, @ColorInt int color) {
        int alpha = Color.alpha(color);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);

        int[] pixels = new int[bitmap.getWidth() * bitmap.getHeight()];
        bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        for (int i = 0; i < pixels.length; i++) {
            int pixel = pixels[i];
            int pixelAlpha = Color.alpha(pixel);
            if (pixelAlpha != 0)
                pixels[i] = Color.argb((int) (pixelAlpha * alpha / 256f), red, green, blue);
        }

        Bitmap coloredBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        coloredBitmap.setPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        return coloredBitmap;
    }

    public static Bitmap getColoredBitmap(@NonNull Context context, @DrawableRes int drawableRes, @ColorInt int color) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), drawableRes);
        return getColoredBitmap(bitmap, color);
    }

    public static Bitmap getGradientBitmap(int width, int height, @ColorInt int color) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        int alpha = Color.alpha(color);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);

        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int y = 0; y < height; y++) {
            int gradientAlpha = (int) ((float) alpha * (float) (height - y) * (float) (height - y) / (float) height / (float) height);
            for (int x = 0; x < width; x++) {
                pixels[x + y * width] = Color.argb(gradientAlpha, red, green, blue);
            }
        }

        bitmap.setPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        return bitmap;
    }
}
