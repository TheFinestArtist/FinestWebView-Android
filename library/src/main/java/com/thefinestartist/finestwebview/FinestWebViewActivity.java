package com.thefinestartist.finestwebview;

import android.content.Context;
import android.support.annotation.AnimRes;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.thefinestartist.finestwebview.enums.Position;
import com.thefinestartist.finestwebview.helpers.DipPixelHelper;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;

/**
 * Created by Leonardo on 11/14/15.
 */
public class FinestWebViewActivity extends AppCompatActivity {

    public static class Builder {

        private Context context;

        protected boolean showStatusBar = true;
        protected int statusBarColor = 0Xffffff;

        protected int toolBarColor = 0Xffffff;

        protected int iconDefaultColor = 0Xffffff;
        protected int iconDisabledColor = 0Xffffff;
        protected int iconPressedColor = 0Xffffff;

        protected boolean showProgressBar = true;
        protected int[] progressBarColors = new int[]{0Xffffff};
        protected SmoothProgressBar smoothProgressBar = null;
        protected Position progressBarPosition = Position.BOTTON_OF_TOOLBAR;

        protected String titleDefault = null;
        protected boolean updateTitleFromHtml = true;
        private final float titleSizeInDp = 16f;
        protected float titleSize = 0f;
        protected String titleFont = "Roboto-Regular.ttf";
        protected int titleColor = 0X000000;

        protected boolean showUrl = true;
        private final float urlSizeInDp = 16f;
        protected float urlSize = 0f;
        protected String urlFont = "Roboto-Regular.ttf";
        protected int urlColor = 0X000000;

        protected int enterAnimation = R.anim.modal_activity_close_enter;
        protected int exitAnimation = R.anim.modal_activity_close_exit;

        protected boolean showRefresh = false;
        protected boolean backPressToClose = false;

        protected boolean edgeControlSide = true;
        protected boolean edgeControlTop = true;

        public Builder(@NonNull Context context) {
            this.context = context;
            this.titleSize = DipPixelHelper.getPixel(context, titleSizeInDp);
            this.urlSize = DipPixelHelper.getPixel(context, urlSizeInDp);
        }

        public Builder showStatusBar(boolean showStatusBar) {
            this.showStatusBar = showStatusBar;
            return this;
        }

        public Builder statusBarColor(@ColorInt int color) {
            this.statusBarColor = color;
            return this;
        }

        public Builder statusBarColorRes(@ColorRes int colorRes) {
            this.statusBarColor = ContextCompat.getColor(context, colorRes);
            return this;
        }

        public Builder toolBarColor(@ColorInt int color) {
            this.toolBarColor = color;
            return this;
        }

        public Builder toolBarColorRes(@ColorRes int colorRes) {
            this.toolBarColor = ContextCompat.getColor(context, colorRes);
            return this;
        }

        public Builder iconDefaultColor(@ColorInt int color) {
            this.iconDefaultColor = color;
            return this;
        }

        public Builder iconDefaultColorRes(@ColorRes int color) {
            this.iconDefaultColor = ContextCompat.getColor(context, color);
            return this;
        }

        public Builder iconDisabledColor(@ColorInt int color) {
            this.iconDisabledColor = color;
            return this;
        }

        public Builder iconDisabledColorRes(@ColorRes int colorRes) {
            this.iconDisabledColor = ContextCompat.getColor(context, colorRes);
            return this;
        }

        public Builder iconPressedColor(@ColorInt int color) {
            this.iconPressedColor = color;
            return this;
        }

        public Builder iconPressedColorRes(@ColorRes int colorRes) {
            this.iconPressedColor = ContextCompat.getColor(context, colorRes);
            return this;
        }

        public Builder showProgressBar(boolean showProgressBar) {
            this.showProgressBar = showProgressBar;
            return this;
        }

        public Builder progressBarColor(@ColorInt int color) {
            this.progressBarColors = new int[]{color};
            return this;
        }

        public Builder progressBarColors(int[] colors) {
            this.progressBarColors = colors;
            return this;
        }

        public Builder progressBarColorRes(@ColorRes int colorRes) {
            this.progressBarColors = new int[]{ContextCompat.getColor(context, colorRes)};
            return this;
        }

        public Builder progressBarColorsRes(@ArrayRes int arrayRes) {
            this.progressBarColors = context.getResources().getIntArray(arrayRes);
            return this;
        }

        public Builder smoothProgressBar(@NonNull SmoothProgressBar smoothProgressBar) {
            this.smoothProgressBar = smoothProgressBar;
            return this;
        }

        public Builder progressBarPosition(@NonNull Position position) {
            this.progressBarPosition = position;
            return this;
        }

        public Builder titleDefault(@NonNull String title) {
            this.titleDefault = title;
            return this;
        }

        public Builder titleDefaultRes(@StringRes int stringRes) {
            this.titleDefault = context.getString(stringRes);
            return this;
        }

        public Builder updateTitleFromHtml(boolean updateTitleFromHtml) {
            this.updateTitleFromHtml = updateTitleFromHtml;
            return this;
        }

        public Builder titleSize(float titleSize) {
            this.titleSize = titleSize;
            return this;
        }

        public Builder titleSize(int titleSize) {
            this.titleSize = titleSize;
            return this;
        }

        public Builder titleSizeRes(@DimenRes int titleSize) {
            this.titleSize = DipPixelHelper.getPixel(context, titleSize);
            return this;
        }

        public Builder titleFont(String titleFont) {
            this.titleFont = titleFont;
            return this;
        }

        public Builder titleColor(@ColorInt int color) {
            this.titleColor = color;
            return this;
        }

        public Builder titleColorRes(@ColorRes int colorRes) {
            this.titleColor = ContextCompat.getColor(context, colorRes);
            return this;
        }

        public Builder showUrl(boolean showUrl) {
            this.showUrl = showUrl;
            return this;
        }

        public Builder urlSize(float urlSize) {
            this.urlSize = urlSize;
            return this;
        }

        public Builder urlSize(int urlSize) {
            this.urlSize = urlSize;
            return this;
        }

        public Builder urlSizeRes(@DimenRes int urlSize) {
            this.urlSize = DipPixelHelper.getPixel(context, urlSize);
            return this;
        }

        public Builder urlFont(String urlFont) {
            this.urlFont = urlFont;
            return this;
        }

        public Builder urlColor(@ColorInt int color) {
            this.urlColor = color;
            return this;
        }

        public Builder urlColorRes(@ColorRes int colorRes) {
            this.urlColor = ContextCompat.getColor(context, colorRes);
            return this;
        }

        public Builder enterAnimation(@AnimRes int enterAnimation) {
            this.enterAnimation = enterAnimation;
            return this;
        }

        public Builder exitAnimation(@AnimRes int exitAnimation) {
            this.exitAnimation = exitAnimation;
            return this;
        }

        public Builder showRefresh(boolean showRefresh) {
            this.showRefresh = showRefresh;
            return this;
        }

        public Builder backPressToClose(boolean backPressToClose) {
            this.backPressToClose = backPressToClose;
            return this;
        }

        public Builder edgeControlSide(boolean edgeControlSide) {
            this.edgeControlSide = edgeControlSide;
            return this;
        }

        public Builder edgeControlTop(boolean edgeControlTop) {
            this.edgeControlTop = edgeControlTop;
            return this;
        }
    }
}