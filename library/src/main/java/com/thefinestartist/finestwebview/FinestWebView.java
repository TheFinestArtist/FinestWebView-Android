package com.thefinestartist.finestwebview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.AnimRes;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.design.widget.AppBarLayout.LayoutParams.ScrollFlags;
import android.support.v4.content.ContextCompat;

import com.thefinestartist.finestwebview.enums.Position;
import com.thefinestartist.finestwebview.helpers.DipPixelHelper;

/**
 * Created by Leonardo on 11/21/15.
 */
public class FinestWebView {

    public static class Builder {

        private Activity activity;

        protected Integer theme;

        protected Integer statusBarColor;

        protected Integer toolbarColor;
        protected Integer toolbarScrollFlags;

        protected Integer iconDefaultColor;
        protected Integer iconDisabledColor;
        protected Integer iconPressedColor;
        protected Integer iconSelector;

        protected Boolean showSwipeRefreshLayout;
        protected Integer swipeRefreshColor;
        protected Integer[] swipeRefreshColors;

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

        protected Integer menuColor;
        protected Integer menuDropShadowColor;
        protected Float menuDropShadowSize;
        protected Integer menuSelector;

        protected Float menuTextSize;
        protected String menuTextFont;
        protected Integer menuTextColor;

        protected Boolean showMenuRefresh;
        protected Integer stringResRefresh;
        protected Boolean showMenuShareVia;
        protected Integer stringResShareVia;
        protected Boolean showMenuCopyLink;
        protected Integer stringResCopyLink;
        protected Boolean showMenuOpenWith;
        protected Integer stringResOpenWith;

        protected Integer animationOpenEnter;
        protected Integer animationOpenExit;
        protected Integer animationCloseEnter;
        protected Integer animationCloseExit;

        protected Boolean backPressToClose;
        protected Integer stringResCopiedToClipboard;

        protected Boolean webViewJavaScriptEnabled;
        protected Boolean webViewAppCacheEnabled;
        protected Boolean webViewAllowFileAccess;
        protected Boolean webViewUseWideViewPort;
        protected Boolean webViewLoadWithOverviewMode;
        protected Boolean webViewDomStorageEnabled;
        protected Boolean webViewBuiltInZoomControls;
        protected Boolean webViewDisplayZoomControls;
        protected Boolean webViewDesktopMode;

        protected String url;

        @Deprecated
        public Builder(@NonNull Context context) {
            if (context instanceof Activity)
                this.activity = (Activity) context;
        }

        public Builder(@NonNull Activity activity) {
            this.activity = activity;
        }

        public Builder theme(@StyleRes int theme) {
            this.theme = theme;
            return this;
        }

        public Builder statusBarColor(@ColorInt int color) {
            this.statusBarColor = color;
            return this;
        }

        public Builder statusBarColorRes(@ColorRes int color) {
            this.statusBarColor = ContextCompat.getColor(activity, color);
            return this;
        }

        public Builder toolbarColor(@ColorInt int color) {
            this.toolbarColor = color;
            return this;
        }

        public Builder toolbarColorRes(@ColorRes int color) {
            this.toolbarColor = ContextCompat.getColor(activity, color);
            return this;
        }

        public Builder toolbarScrollFlags(@ScrollFlags int flags) {
            this.toolbarScrollFlags = flags;
            return this;
        }

        public Builder iconDefaultColor(@ColorInt int color) {
            this.iconDefaultColor = color;
            return this;
        }

        public Builder iconDefaultColorRes(@ColorRes int color) {
            this.iconDefaultColor = ContextCompat.getColor(activity, color);
            return this;
        }

        public Builder iconDisabledColor(@ColorInt int color) {
            this.iconDisabledColor = color;
            return this;
        }

        public Builder iconDisabledColorRes(@ColorRes int colorRes) {
            this.iconDisabledColor = ContextCompat.getColor(activity, colorRes);
            return this;
        }

        public Builder iconPressedColor(@ColorInt int color) {
            this.iconPressedColor = color;
            return this;
        }

        public Builder iconPressedColorRes(@ColorRes int colorRes) {
            this.iconPressedColor = ContextCompat.getColor(activity, colorRes);
            return this;
        }

        public Builder iconSelector(@DrawableRes int selectorRes) {
            this.iconSelector = selectorRes;
            return this;
        }

        public Builder showSwipeRefreshLayout(boolean showSwipeRefreshLayout) {
            this.showSwipeRefreshLayout = showSwipeRefreshLayout;
            return this;
        }

        public Builder swipeRefreshColor(@ColorInt int color) {
            this.swipeRefreshColor = color;
            return this;
        }

        public Builder swipeRefreshColorRes(@ColorRes int colorRes) {
            this.swipeRefreshColor = ContextCompat.getColor(activity, colorRes);
            return this;
        }

        public Builder swipeRefreshColors(int[] colors) {
            Integer[] swipeRefreshColors = new Integer[colors.length];
            for (int i = 0; i < colors.length; i++)
                swipeRefreshColors[i] = colors[i];
            this.swipeRefreshColors = swipeRefreshColors;
            return this;
        }

        public Builder swipeRefreshColorsRes(@ArrayRes int colorsRes) {
            int[] colors = activity.getResources().getIntArray(colorsRes);
            return swipeRefreshColors(colors);
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
            this.dividerColor = ContextCompat.getColor(activity, colorRes);
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
            this.dividerHeight = DipPixelHelper.getPixel(activity, height);
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
            this.progressBarColor = ContextCompat.getColor(activity, colorRes);
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
            this.progressBarHeight = DipPixelHelper.getPixel(activity, height);
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
            this.titleDefault = activity.getString(stringRes);
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
            this.titleSize = DipPixelHelper.getPixel(activity, titleSize);
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
            this.titleColor = ContextCompat.getColor(activity, colorRes);
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
            this.urlSize = DipPixelHelper.getPixel(activity, urlSize);
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
            this.urlColor = ContextCompat.getColor(activity, colorRes);
            return this;
        }

        public Builder menuColor(@ColorInt int color) {
            this.menuColor = color;
            return this;
        }

        public Builder menuColorRes(@ColorRes int colorRes) {
            this.menuColor = ContextCompat.getColor(activity, colorRes);
            return this;
        }

        public Builder menuDropShadowColor(@ColorInt int color) {
            this.menuDropShadowColor = color;
            return this;
        }

        public Builder menuDropShadowColorRes(@ColorRes int colorRes) {
            this.menuDropShadowColor = ContextCompat.getColor(activity, colorRes);
            return this;
        }

        public Builder menuDropShadowSize(float menuDropShadowSize) {
            this.menuDropShadowSize = menuDropShadowSize;
            return this;
        }

        public Builder menuDropShadowSize(int menuDropShadowSize) {
            this.menuDropShadowSize = (float) menuDropShadowSize;
            return this;
        }

        public Builder menuDropShadowSizeRes(@DimenRes int menuDropShadowSize) {
            this.menuDropShadowSize = DipPixelHelper.getPixel(activity, menuDropShadowSize);
            return this;
        }

        public Builder menuSelector(@DrawableRes int selectorRes) {
            this.menuSelector = selectorRes;
            return this;
        }

        public Builder menuTextSize(float menuTextSize) {
            this.menuTextSize = menuTextSize;
            return this;
        }

        public Builder menuTextSize(int menuTextSize) {
            this.menuTextSize = (float) menuTextSize;
            return this;
        }

        public Builder menuTextSizeRes(@DimenRes int menuTextSize) {
            this.menuTextSize = DipPixelHelper.getPixel(activity, menuTextSize);
            return this;
        }

        public Builder menuTextFont(String menuTextFont) {
            this.menuTextFont = menuTextFont;
            return this;
        }

        public Builder menuTextColor(@ColorInt int color) {
            this.menuTextColor = color;
            return this;
        }

        public Builder menuTextColorRes(@ColorRes int colorRes) {
            this.menuTextColor = ContextCompat.getColor(activity, colorRes);
            return this;
        }

        public Builder showMenuRefresh(boolean showMenuRefresh) {
            this.showMenuRefresh = showMenuRefresh;
            return this;
        }

        public Builder stringResRefresh(@StringRes int stringResRefresh) {
            this.stringResRefresh = stringResRefresh;
            return this;
        }

        public Builder showMenuShareVia(boolean showMenuShareVia) {
            this.showMenuShareVia = showMenuShareVia;
            return this;
        }

        public Builder stringResShareVia(@StringRes int stringResShareVia) {
            this.stringResShareVia = stringResShareVia;
            return this;
        }

        public Builder showMenuCopyLink(boolean showMenuCopyLink) {
            this.showMenuCopyLink = showMenuCopyLink;
            return this;
        }

        public Builder stringResCopyLink(@StringRes int stringResCopyLink) {
            this.stringResCopyLink = stringResCopyLink;
            return this;
        }

        public Builder showMenuOpenWith(boolean showMenuOpenWith) {
            this.showMenuOpenWith = showMenuOpenWith;
            return this;
        }

        public Builder stringResOpenWith(@StringRes int stringResOpenWith) {
            this.stringResOpenWith = stringResOpenWith;
            return this;
        }

        public Builder setCustomAnimations(@AnimRes int animationOpenEnter,
                                           @AnimRes int animationOpenExit,
                                           @AnimRes int animationCloseEnter,
                                           @AnimRes int animationCloseExit) {
            this.animationOpenEnter = animationOpenEnter;
            this.animationOpenExit = animationOpenExit;
            this.animationCloseEnter = animationCloseEnter;
            this.animationCloseExit = animationCloseExit;
            return this;
        }

        /**
         * @deprecated As of release 1.0.1, replaced by {@link #setCustomAnimations(int, int, int, int)}
         */
        public Builder setCloseAnimations(@AnimRes int animationCloseEnter,
                                          @AnimRes int animationCloseExit) {
            this.animationCloseEnter = animationCloseEnter;
            this.animationCloseExit = animationCloseExit;
            return this;
        }

        public Builder backPressToClose(boolean backPressToClose) {
            this.backPressToClose = backPressToClose;
            return this;
        }

        public Builder stringResCopiedToClipboard(@StringRes int stringResCopiedToClipboard) {
            this.stringResCopiedToClipboard = stringResCopiedToClipboard;
            return this;
        }

        public Builder webViewJavaScriptEnabled(boolean webViewJavaScriptEnabled) {
            this.webViewJavaScriptEnabled = webViewJavaScriptEnabled;
            return this;
        }

        public Builder webViewAppCacheEnabled(boolean webViewAppCacheEnabled) {
            this.webViewAppCacheEnabled = webViewAppCacheEnabled;
            return this;
        }

        public Builder webViewAllowFileAccess(boolean webViewAllowFileAccess) {
            this.webViewAllowFileAccess = webViewAllowFileAccess;
            return this;
        }

        public Builder webViewUseWideViewPort(boolean webViewUseWideViewPort) {
            this.webViewUseWideViewPort = webViewUseWideViewPort;
            return this;
        }

        public Builder webViewLoadWithOverviewMode(boolean webViewLoadWithOverviewMode) {
            this.webViewLoadWithOverviewMode = webViewLoadWithOverviewMode;
            return this;
        }

        public Builder webViewDomStorageEnabled(boolean webViewDomStorageEnabled) {
            this.webViewDomStorageEnabled = webViewDomStorageEnabled;
            return this;
        }

        public Builder webViewBuiltInZoomControls(boolean webViewBuiltInZoomControls) {
            this.webViewBuiltInZoomControls = webViewBuiltInZoomControls;
            return this;
        }

        public Builder webViewDisplayZoomControls(boolean webViewDisplayZoomControls) {
            this.webViewDisplayZoomControls = webViewDisplayZoomControls;
            return this;
        }

        public Builder webViewDesktopMode(boolean webViewDesktopMode) {
            this.webViewDesktopMode = webViewDesktopMode;
            return this;
        }

        public void show(@StringRes int urlRes) {
            show(activity.getString(urlRes));
        }

        public void show(@NonNull String url) {
            this.url = url;

            Intent intent = new Intent(activity, FinestWebViewActivity.class);

            if (theme != null)
                intent.putExtra("theme", theme.intValue());

            if (statusBarColor != null)
                intent.putExtra("statusBarColor", statusBarColor.intValue());

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

            if (showSwipeRefreshLayout != null)
                intent.putExtra("showSwipeRefreshLayout", showSwipeRefreshLayout.booleanValue());
            if (swipeRefreshColor != null)
                intent.putExtra("swipeRefreshColor", swipeRefreshColor.intValue());
            if (swipeRefreshColors != null) {
                int[] colors = new int[swipeRefreshColors.length];
                for (int i = 0; i < swipeRefreshColors.length; i++)
                    colors[i] = swipeRefreshColors[i];
                intent.putExtra("swipeRefreshColors", colors);
            }

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

            if (menuColor != null)
                intent.putExtra("menuColor", menuColor.intValue());
            if (menuDropShadowColor != null)
                intent.putExtra("menuDropShadowColor", menuDropShadowColor.intValue());
            if (menuDropShadowSize != null)
                intent.putExtra("menuDropShadowSize", menuDropShadowSize.floatValue());
            if (menuSelector != null)
                intent.putExtra("menuSelector", menuSelector.intValue());

            if (menuTextSize != null)
                intent.putExtra("menuTextSize", menuTextSize.floatValue());
            if (menuTextFont != null)
                intent.putExtra("menuTextFont", menuTextFont);
            if (menuTextColor != null)
                intent.putExtra("menuTextColor", menuTextColor.intValue());

            if (showMenuRefresh != null)
                intent.putExtra("showMenuRefresh", showMenuRefresh.booleanValue());
            if (stringResRefresh != null)
                intent.putExtra("stringResRefresh", stringResRefresh.intValue());
            if (showMenuShareVia != null)
                intent.putExtra("showMenuShareVia", showMenuShareVia.booleanValue());
            if (stringResShareVia != null)
                intent.putExtra("stringResShareVia", stringResShareVia.intValue());
            if (showMenuCopyLink != null)
                intent.putExtra("showMenuCopyLink", showMenuCopyLink.booleanValue());
            if (stringResCopyLink != null)
                intent.putExtra("stringResCopyLink", stringResCopyLink.intValue());
            if (showMenuOpenWith != null)
                intent.putExtra("showMenuOpenWith", showMenuOpenWith.booleanValue());
            if (stringResOpenWith != null)
                intent.putExtra("stringResOpenWith", stringResOpenWith.intValue());

            if (animationCloseEnter != null)
                intent.putExtra("animationCloseEnter", animationCloseEnter.intValue());
            if (animationCloseExit != null)
                intent.putExtra("animationCloseExit", animationCloseExit.intValue());

            if (backPressToClose != null)
                intent.putExtra("backPressToClose", backPressToClose.booleanValue());
            if (stringResCopiedToClipboard != null)
                intent.putExtra("stringResCopiedToClipboard", stringResCopiedToClipboard.intValue());

            if (webViewJavaScriptEnabled != null)
                intent.putExtra("webViewJavaScriptEnabled", webViewJavaScriptEnabled.booleanValue());
            if (webViewAppCacheEnabled != null)
                intent.putExtra("webViewAppCacheEnabled", webViewAppCacheEnabled.booleanValue());
            if (webViewAllowFileAccess != null)
                intent.putExtra("webViewAllowFileAccess", webViewAllowFileAccess.booleanValue());
            if (webViewUseWideViewPort != null)
                intent.putExtra("webViewUseWideViewPort", webViewUseWideViewPort.booleanValue());
            if (webViewLoadWithOverviewMode != null)
                intent.putExtra("webViewLoadWithOverviewMode", webViewLoadWithOverviewMode.booleanValue());
            if (webViewDomStorageEnabled != null)
                intent.putExtra("webViewDomStorageEnabled", webViewDomStorageEnabled.booleanValue());
            if (webViewBuiltInZoomControls != null)
                intent.putExtra("webViewBuiltInZoomControls", webViewBuiltInZoomControls.booleanValue());
            if (webViewDisplayZoomControls != null)
                intent.putExtra("webViewDisplayZoomControls", webViewDisplayZoomControls.booleanValue());
            if (webViewDesktopMode != null)
                intent.putExtra("webViewDesktopMode", webViewDesktopMode.booleanValue());

            intent.putExtra("url", url);

            activity.startActivity(intent);
            activity.overridePendingTransition(animationOpenEnter == null ? R.anim.modal_activity_open_enter : animationOpenEnter,
                    animationOpenExit == null ? R.anim.modal_activity_open_exit : animationOpenExit);
        }
    }
}
