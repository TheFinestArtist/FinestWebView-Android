package com.thefinestartist.finestwebview;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.AnimRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;

import com.thefinestartist.finestwebview.enums.Position;
import com.thefinestartist.finestwebview.helpers.DipPixelHelper;

/**
 * Created by Leonardo on 11/21/15.
 */
public class FinestWebView {

    public static class Builder {

        private final Context context;

        protected Integer toolbarColor;

        protected Integer iconDefaultColor;
        protected Integer iconDisabledColor;
        protected Integer iconPressedColor;
        protected Integer iconSelector;

        protected Boolean showDivider;
        protected Boolean gradientDivider;
        protected Integer dividerColor;
        protected Float dividerHeight;

        protected Boolean showProgressBar;
        protected Integer progressBarColor;
        protected Float progressBarHeight;
        protected Position progressBarPosition;

        protected String titleDefault;
        protected Boolean updateTitleFromHtml;
        protected Float titleSize;
        protected String titleFont;
        protected Integer titleColor;

        protected Boolean showUrl;
        protected Float urlSize;
        protected String urlFont;
        protected Integer urlColor;

        protected Integer enterAnimation;
        protected Integer exitAnimation;

        protected Boolean showRefresh;
        protected Boolean backPressToClose;

        protected Boolean edgeControlSide;
        protected Boolean edgeControlTop;

        protected String url;

        public Builder(@NonNull Context context) {
            this.context = context;
        }

        public Builder toolbarColor(@ColorInt int color) {
            this.toolbarColor = color;
            return this;
        }

        public Builder toolbarColorRes(@ColorRes int color) {
            this.toolbarColor = ContextCompat.getColor(context, color);
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

        public Builder iconSelector(@DrawableRes int selectorRes) {
            this.iconSelector = selectorRes;
            return this;
        }

        public Builder showDivider(boolean showDivider) {
            this.showDivider = showDivider;
            return this;
        }

        public Builder gradientDivider(boolean gradientDivider) {
            this.gradientDivider = gradientDivider;
            return this;
        }

        public Builder dividerColor(@ColorInt int color) {
            this.dividerColor = color;
            return this;
        }

        public Builder dividerColorRes(@ColorRes int colorRes) {
            this.dividerColor = ContextCompat.getColor(context, colorRes);
            return this;
        }

        public Builder dividerHeight(float height) {
            this.dividerHeight = height;
            return this;
        }

        public Builder dividerHeight(int height) {
            this.dividerHeight = (float) height;
            return this;
        }

        public Builder dividerHeightRes(@DimenRes int height) {
            this.dividerHeight = DipPixelHelper.getPixel(context, height);
            return this;
        }

        public Builder showProgressBar(boolean showProgressBar) {
            this.showProgressBar = showProgressBar;
            return this;
        }

        public Builder progressBarColor(@ColorInt int color) {
            this.progressBarColor = color;
            return this;
        }

        public Builder progressBarColorRes(@ColorRes int colorRes) {
            this.progressBarColor = ContextCompat.getColor(context, colorRes);
            return this;
        }

        public Builder progressBarHeight(float height) {
            this.progressBarHeight = height;
            return this;
        }

        public Builder progressBarHeight(int height) {
            this.progressBarHeight = (float) height;
            return this;
        }

        public Builder progressBarHeightRes(@DimenRes int height) {
            this.progressBarHeight = DipPixelHelper.getPixel(context, height);
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
            this.titleSize = (float) titleSize;
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
            this.urlSize = (float) urlSize;
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

        public void show(@StringRes int urlRes) {
            show(context.getString(urlRes));
        }

        public void show(@NonNull String url) {
            this.url = url;

            Intent intent = new Intent(context, FinestWebViewActivity.class);

            if (toolbarColor != null)
                intent.putExtra("toolbarColor", (int) toolbarColor);

            if (iconDefaultColor != null)
                intent.putExtra("iconDefaultColor", (int) iconDefaultColor);
            if (iconDisabledColor != null)
                intent.putExtra("iconDisabledColor", (int) iconDisabledColor);
            if (iconPressedColor != null)
                intent.putExtra("iconPressedColor", (int) iconPressedColor);
            if (iconSelector != null)
                intent.putExtra("iconSelector", (int) iconSelector);

            if (showDivider != null)
                intent.putExtra("showDivider", (boolean) showDivider);
            if (gradientDivider != null)
                intent.putExtra("gradientDivider", (boolean) gradientDivider);
            if (dividerColor != null)
                intent.putExtra("dividerColor", (int) dividerColor);
            if (dividerHeight != null)
                intent.putExtra("dividerHeight", (float) dividerHeight);

            if (showProgressBar != null)
                intent.putExtra("showProgressBar", (boolean) showProgressBar);
            if (progressBarColor != null)
                intent.putExtra("progressBarColor", (int) progressBarColor);
            if (progressBarHeight != null)
                intent.putExtra("progressBarHeight", (float) progressBarHeight);
            if (progressBarPosition != null)
                intent.putExtra("progressBarPosition", progressBarPosition);

            if (titleDefault != null)
                intent.putExtra("titleDefault", titleDefault);
            if (updateTitleFromHtml != null)
                intent.putExtra("updateTitleFromHtml", (boolean) updateTitleFromHtml);
            if (titleSize != null)
                intent.putExtra("titleSize", (float) titleSize);
            if (titleFont != null)
                intent.putExtra("titleFont", titleFont);
            if (titleColor != null)
                intent.putExtra("titleColor", (int) titleColor);

            if (showUrl != null)
                intent.putExtra("showUrl", (boolean) showUrl);
            if (urlSize != null)
                intent.putExtra("urlSize", (float) urlSize);
            if (urlFont != null)
                intent.putExtra("urlFont", urlFont);
            if (urlColor != null)
                intent.putExtra("urlColor", (int) urlColor);

            if (enterAnimation != null)
                intent.putExtra("enterAnimation", (int) enterAnimation);
            if (exitAnimation != null)
                intent.putExtra("exitAnimation", (int) exitAnimation);

            if (showRefresh != null)
                intent.putExtra("showRefresh", (boolean) showRefresh);
            if (backPressToClose != null)
                intent.putExtra("backPressToClose", (boolean) backPressToClose);

            if (edgeControlSide != null)
                intent.putExtra("edgeControlSide", (boolean) edgeControlSide);
            if (edgeControlTop != null)
                intent.putExtra("edgeControlTop", (boolean) edgeControlTop);

            intent.putExtra("url", url);

            context.startActivity(intent);
        }
    }
}
