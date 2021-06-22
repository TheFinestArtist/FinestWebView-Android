package com.thefinestartist.finestwebview.views

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.thefinestartist.finestwebview.R

/**
 * Created by Leonardo on 11/26/15.
 */
class ShadowLayout : FrameLayout {
  private var shadowColor = 0
  private var shadowSize = 0f
  private var cornerRadius = 0f
  private var dx = 0f
  private var dy = 0f

  constructor(context: Context) : super(context) {
    setWillNotDraw(false)
    initAttributes(null)
    setPadding()
  }

  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
    setWillNotDraw(false)
    initAttributes(attrs)
    setPadding()
  }

  constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int
  ) : super(context, attrs, defStyleAttr) {
    setWillNotDraw(false)
    initAttributes(attrs)
    setPadding()
  }

  private fun initAttributes(attrs: AttributeSet?) {
    val attr = context.obtainStyledAttributes(attrs, R.styleable.ShadowLayout, 0, 0)
    try {
      cornerRadius = attr.getDimension(R.styleable.ShadowLayout_slCornerRadius, resources.getDimension(R.dimen.defaultMenuDropShadowCornerRadius))
      shadowSize = attr.getDimension(R.styleable.ShadowLayout_slShadowSize, resources.getDimension(R.dimen.defaultMenuDropShadowSize))
      dx = attr.getDimension(R.styleable.ShadowLayout_slDx, 0f)
      dy = attr.getDimension(R.styleable.ShadowLayout_slDy, 0f)
      shadowColor = attr.getColor(
        R.styleable.ShadowLayout_slShadowColor,
        ContextCompat.getColor(context, R.color.finestBlack10)
      )
    } finally {
      attr.recycle()
    }
  }

  private fun setPadding() {
    val xPadding = (shadowSize + Math.abs(dx)).toInt()
    val yPadding = (shadowSize + Math.abs(dy)).toInt()
    setPadding(xPadding, yPadding, xPadding, yPadding)
  }

  fun setShadowColor(shadowColor: Int) {
    this.shadowColor = shadowColor
    invalidate()
  }

  fun setShadowSize(shadowSize: Float) {
    this.shadowSize = shadowSize
    setPadding()
  }

  fun setCornerRadius(cornerRadius: Float) {
    this.cornerRadius = cornerRadius
    invalidate()
  }

  fun setDx(dx: Float) {
    this.dx = dx
    setPadding()
  }

  fun setDy(dy: Float) {
    this.dy = dy
    setPadding()
  }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)

    //        RoundRectShape rss = new RoundRectShape(new float[]{12f, 12f, 12f,
    //                12f, 12f, 12f, 12f, 12f}, null, null);
    //        ShapeDrawable sds = new ShapeDrawable(rss);
    //        sds.setShaderFactory(new ShapeDrawable.ShaderFactory() {
    //
    //            @Override
    //            public Shader resize(int width, int height) {
    //                LinearGradient lg = new LinearGradient(0, 0, 0, height,
    //                        new int[]{Color.parseColor("#e5e5e5"),
    //                                Color.parseColor("#e5e5e5"),
    //                                Color.parseColor("#e5e5e5"),
    //                                Color.parseColor("#e5e5e5")}, new float[]{0,
    //                        0.50f, 0.50f, 1}, Shader.TileMode.REPEAT);
    //                return lg;
    //            }
    //        });
    //
    //        LayerDrawable ld = new LayerDrawable(new Drawable[]{sds, sds});
    //        ld.setLayerInset(0, 5, 5, 0, 0); // inset the shadow so it doesn't start right at the left/top
    //        ld.setLayerInset(1, 0, 0, 5, 5); // inset the top drawable so we can leave a bit of space for the shadow to use
    setBackgroundCompat(canvas.width, canvas.height)
  }

  private fun setBackgroundCompat(w: Int, h: Int) {
    val bitmap = createShadowBitmap(
      w,
      h,
      cornerRadius,
      shadowSize,
      dx,
      dy,
      shadowColor,
      Color.TRANSPARENT
    )
    val drawable = BitmapDrawable(resources, bitmap)
    background = drawable
  }

  private fun createShadowBitmap(shadowWidth: Int, shadowHeight: Int, cornerRadius: Float, shadowSize: Float, dx: Float, dy: Float, shadowColor: Int, fillColor: Int): Bitmap {
    val output = Bitmap.createBitmap(shadowWidth, shadowHeight, Bitmap.Config.ALPHA_8)
    val canvas = Canvas(output)
    val shadowRect = RectF(shadowSize, shadowSize, shadowWidth - shadowSize, shadowHeight - shadowSize)
    if (dy > 0) {
      shadowRect.top += dy
      shadowRect.bottom -= dy
    } else if (dy < 0) {
      shadowRect.top += Math.abs(dy)
      shadowRect.bottom -= Math.abs(dy)
    }
    if (dx > 0) {
      shadowRect.left += dx
      shadowRect.right -= dx
    } else if (dx < 0) {
      shadowRect.left += Math.abs(dx)
      shadowRect.right -= Math.abs(dx)
    }
    val shadowPaint = Paint()
    shadowPaint.isAntiAlias = true
    shadowPaint.color = fillColor
    shadowPaint.style = Paint.Style.FILL
    shadowPaint.setShadowLayer(shadowSize, dx, dy, shadowColor)
    canvas.drawRoundRect(shadowRect, cornerRadius, cornerRadius, shadowPaint)
    return output
  }

  override fun getSuggestedMinimumWidth() = 0

  override fun getSuggestedMinimumHeight() = 0
}