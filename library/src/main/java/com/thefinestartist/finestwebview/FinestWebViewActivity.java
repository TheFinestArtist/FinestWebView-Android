package com.thefinestartist.finestwebview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.thefinestartist.finestwebview.enums.Position;
import com.thefinestartist.finestwebview.helpers.DipPixelHelper;


/**
 * Created by Leonardo on 11/14/15.
 */
public class FinestWebViewActivity extends AppCompatActivity {

    public static class Builder {

        private Context context;

        protected boolean showStatusBar;
        protected int statusBarColor;
        protected int toolBarColor;

        protected int iconDefaultColor;
        protected int iconDisabledColor;
        protected int iconPressedColor;
        protected int iconSelector;

        protected boolean showProgressBar;
        protected int progressBarColor;
        protected float progressBarHeight;
        protected Position progressBarPosition;

        protected String titleDefault;
        protected boolean updateTitleFromHtml;
        protected float titleSize;
        protected String titleFont;
        protected int titleColor;

        protected boolean showUrl;
        protected float urlSize;
        protected String urlFont;
        protected int urlColor;

        protected int enterAnimation;
        protected int exitAnimation;

        protected boolean showRefresh;
        protected boolean backPressToClose;

        protected boolean edgeControlSide;
        protected boolean edgeControlTop;

        public Builder(@NonNull Context context) {
            this.context = context;
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

        public Builder iconSelector(@DrawableRes int selectorRes) {
            this.iconSelector = selectorRes;
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
            this.progressBarHeight = height;
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

        public void show(@StringRes int urlRes) {
            show(context.getString(urlRes));
        }

        public void show(@NonNull String url) {
            Intent intent = new Intent(context, FinestWebViewActivity.class);

            intent.putExtra("showStatusBar", showStatusBar);
            intent.putExtra("statusBarColor", statusBarColor);
            intent.putExtra("toolBarColor", toolBarColor);

            intent.putExtra("iconDefaultColor", iconDefaultColor);
            intent.putExtra("iconDisabledColor", iconDisabledColor);
            intent.putExtra("iconPressedColor", iconPressedColor);
            intent.putExtra("iconSelector", iconSelector);

            intent.putExtra("showProgressBar", showProgressBar);
            intent.putExtra("progressBarColor", progressBarColor);
            intent.putExtra("progressBarHeight", progressBarHeight);
            intent.putExtra("progressBarPosition", progressBarPosition);

            intent.putExtra("titleDefault", titleDefault);
            intent.putExtra("updateTitleFromHtml", updateTitleFromHtml);
            intent.putExtra("titleSize", titleSize);
            intent.putExtra("titleFont", titleFont);
            intent.putExtra("titleColor", titleColor);

            intent.putExtra("showUrl", showUrl);
            intent.putExtra("urlSize", urlSize);
            intent.putExtra("urlFont", urlFont);
            intent.putExtra("urlColor", urlColor);

            intent.putExtra("enterAnimation", enterAnimation);
            intent.putExtra("exitAnimation", exitAnimation);

            intent.putExtra("showRefresh", showRefresh);
            intent.putExtra("backPressToClose", backPressToClose);

            intent.putExtra("edgeControlSide", edgeControlSide);
            intent.putExtra("edgeControlTop", edgeControlTop);

            context.startActivity(intent);
        }
    }

    protected boolean showStatusBar;
    protected int statusBarColor;
    protected int toolBarColor;

    protected int iconDefaultColor;
    protected int iconDisabledColor;
    protected int iconPressedColor;
    protected int iconSelector;

    protected boolean showProgressBar = true;
    protected int progressBarColor = 0X000000;
    protected float progressBarHeight = 0f;
    protected Position progressBarPosition = Position.BOTTON_OF_TOOLBAR;

    protected String titleDefault;
    protected boolean updateTitleFromHtml;
    protected float titleSize;
    protected String titleFont;
    protected int titleColor;

    protected boolean showUrl;
    protected float urlSize;
    protected String urlFont;
    protected int urlColor;

    protected int enterAnimation;
    protected int exitAnimation;

    protected boolean showRefresh;
    protected boolean backPressToClose;

    protected boolean edgeControlSide;
    protected boolean edgeControlTop;

    protected void initialize() {
        Intent intent = getIntent();
        if (intent == null)
            return;

        showStatusBar = intent.getBooleanExtra("showStatusBar", true);
        statusBarColor = intent.getIntExtra("statusBarColor", 0Xffffff);
        toolBarColor = intent.getIntExtra("toolBarColor", 0Xffffff);

        iconDefaultColor = intent.getIntExtra("iconDefaultColor", 0Xffffff);
        iconDisabledColor = intent.getIntExtra("iconDisabledColor", 0Xffffff);
        iconPressedColor = intent.getIntExtra("iconPressedColor", 0Xffffff);
        iconSelector = intent.getIntExtra("iconSelector", R.drawable.selector_grey);

        showProgressBar = intent.getBooleanExtra("showProgressBar", true);
        progressBarColor = intent.getIntExtra("progressBarColor", 0X000000);
        progressBarHeight = intent.getFloatExtra("progressBarHeight", DipPixelHelper.getPixel(this, 2));
        progressBarPosition = Position.fromSerializable(intent.getSerializableExtra("progressBarPosition"));

        titleDefault = intent.getStringExtra("titleDefault");
        updateTitleFromHtml = intent.getBooleanExtra("updateTitleFromHtml", true);
        titleSize = intent.getFloatExtra("titleSize", DipPixelHelper.getPixel(this, 16));
        titleFont = intent.getStringExtra("titleFont") == null ? "Roboto-Regular.ttf" : intent.getStringExtra("titleFont");
        titleColor = intent.getIntExtra("titleColor", 0X000000);

        showUrl = intent.getBooleanExtra("showUrl", true);
        urlSize = intent.getFloatExtra("urlSize", DipPixelHelper.getPixel(this, 16));
        urlFont = intent.getStringExtra("urlFont") == null ? "Roboto-Regular.ttf" : intent.getStringExtra("titleFont");
        urlColor = intent.getIntExtra("urlColor", 0X000000);

        enterAnimation = intent.getIntExtra("enterAnimation", R.anim.modal_activity_close_enter);
        exitAnimation = intent.getIntExtra("exitAnimation", R.anim.modal_activity_close_exit);

        showRefresh = intent.getBooleanExtra("showRefresh", false);
        backPressToClose = intent.getBooleanExtra("backPressToClose", false);

        edgeControlSide = intent.getBooleanExtra("edgeControlSide", true);
        edgeControlTop = intent.getBooleanExtra("edgeControlTop", true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finest_web_view);
        initialize();
    }

    @Override
    public void onBackPressed() {
        if (backPressToClose) {
            super.onBackPressed();
            overridePendingTransition(enterAnimation, exitAnimation);
        } else {

        }
    }
}