package com.thefinestartist.finestwebview.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes

/**
 * Created by Leonardo on 11/21/15.
 */
object BitmapUtil {
  fun getColoredBitmap(
    context: Context,
    @DrawableRes drawableRes: Int,
    @ColorInt color: Int
  ): Bitmap {
    val bitmap = BitmapFactory.decodeResource(context.resources, drawableRes)
    return getColoredBitmap(bitmap, color)
  }

  fun getGradientBitmap(width: Int, height: Int, @ColorInt color: Int): Bitmap {
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val alpha = Color.alpha(color)
    val red = Color.red(color)
    val green = Color.green(color)
    val blue = Color.blue(color)
    val pixels = IntArray(width * height)
    bitmap.getPixels(pixels, 0, width, 0, 0, width, height)
    for (y in 0 until height) {
      val gradientAlpha =
        (alpha.toFloat() * (height - y).toFloat() * (height - y).toFloat()
          / height.toFloat()
          / height.toFloat()).toInt()
      for (x in 0 until width) {
        pixels[x + y * width] = Color.argb(gradientAlpha, red, green, blue)
      }
    }
    bitmap.setPixels(pixels, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
    return bitmap
  }

  private fun getColoredBitmap(bitmap: Bitmap, @ColorInt color: Int): Bitmap {
    val alpha = Color.alpha(color)
    val red = Color.red(color)
    val green = Color.green(color)
    val blue = Color.blue(color)
    val pixels = IntArray(bitmap.width * bitmap.height)
    bitmap.getPixels(pixels, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
    for (i in pixels.indices) {
      val pixel = pixels[i]
      val pixelAlpha = Color.alpha(pixel)
      if (pixelAlpha != 0) {
        pixels[i] = Color.argb((pixelAlpha * alpha / 256f).toInt(), red, green, blue)
      }
    }
    val coloredBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
    coloredBitmap.setPixels(pixels, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
    return coloredBitmap
  }
}