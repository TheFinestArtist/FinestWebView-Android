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
import android.support.design.widget.AppBarLayout.LayoutParams.ScrollFlags;
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
        protected Integer toolbarScrollFlags;

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

        protected Integer animationCloseEnter;
        protected Integer animationCloseExit;

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

        public Builder toolbarScrollFlags(@ScrollFlags int flags) {
            this.toolbarScrollFlags = flags;
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

        public Builder setCloseAnimations(@AnimRes int animationCloseEnter,
                                          @AnimRes int animationCloseExit) {
            this.animationCloseEnter = animationCloseEnter;
            this.animationCloseExit = animationCloseExit;
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

//        public Builder edgeControlSide(boolean edgeControlSide) {
//            this.edgeControlSide = edgeControlSide;
//            return this;
//        }
//
//        public Builder edgeControlTop(boolean edgeControlTop) {
//            this.edgeControlTop = edgeControlTop;
//            return this;
//        }

        public void show(@StringRes int urlRes) {
            show(context.getString(urlRes));
        }

        public void show(@NonNull String url) {
            this.url = url;

            Intent intent = new Intent(context, FinestWebViewActivity.class);

            if (toolbarColor != null)
                intent.putExtra("toolbarColor", toolbarColor.intValue());
            if (toolbarScrollFlags != null)
                intent.putExtra("toolbarScrollFlags", toolbarScrollFlags.intValue());

            if (iconDefaultColor != null)
                intent.putExtra("iconDefaultColor", iconDefaultColor.intValue());
            if (iconDisabledColor != null)
                intent.putExtra("iconDisabledColor", iconDisabledColor.intValue());
            if (iconPressedColor != null)
                intent.putExtra("iconPressedColor", iconPressedColor.intValue());
            if (iconSelector != null)
                intent.putExtra("iconSelector", iconSelector.intValue());

            if (showDivider != null)
                intent.putExtra("showDivider", showDivider.booleanValue());
            if (gradientDivider != null)
                intent.putExtra("gradientDivider", gradientDivider.booleanValue());
            if (dividerColor != null)
                intent.putExtra("dividerColor", dividerColor.intValue());
            if (dividerHeight != null)
                intent.putExtra("dividerHeight", dividerHeight.floatValue());

            if (showProgressBar != null)
                intent.putExtra("showProgressBar", showProgressBar.booleanValue());
            if (progressBarColor != null)
                intent.putExtra("progressBarColor", progressBarColor.intValue());
            if (progressBarHeight != null)
                intent.putExtra("progressBarHeight", progressBarHeight.floatValue());
            if (progressBarPosition != null)
                intent.putExtra("progressBarPosition", progressBarPosition);

            if (titleDefault != null)
                intent.putExtra("titleDefault", titleDefault);
            if (updateTitleFromHtml != null)
                intent.putExtra("updateTitleFromHtml", updateTitleFromHtml.booleanValue());
            if (titleSize != null)
                intent.putExtra("titleSize", titleSize.floatValue());
            if (titleFont != null)
                intent.putExtra("titleFont", titleFont);
            if (titleColor != null)
                intent.putExtra("titleColor", titleColor.intValue());

            if (showUrl != null)
                intent.putExtra("showUrl", showUrl.booleanValue());
            if (urlSize != null)
                intent.putExtra("urlSize", urlSize.floatValue());
            if (urlFont != null)
                intent.putExtra("urlFont", urlFont);
            if (urlColor != null)
                intent.putExtra("urlColor", urlColor.intValue());

            if (animationCloseEnter != null)
                intent.putExtra("animationCloseEnter", animationCloseEnter.intValue());
            if (animationCloseExit != null)
                intent.putExtra("animationCloseExit", animationCloseExit.intValue());

            if (showRefresh != null)
                intent.putExtra("showRefresh", showRefresh.booleanValue());
            if (backPressToClose != null)
                intent.putExtra("backPressToClose", backPressToClose.booleanValue());

            if (edgeControlSide != null)
                intent.putExtra("edgeControlSide", edgeControlSide.booleanValue());
            if (edgeControlTop != null)
                intent.putExtra("edgeControlTop", edgeControlTop.booleanValue());

            intent.putExtra("url", url);

            context.startActivity(intent);
        }
    }
}
