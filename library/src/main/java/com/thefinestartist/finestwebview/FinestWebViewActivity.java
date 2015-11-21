package com.thefinestartist.finestwebview;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.StateListDrawable;
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
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.thefinestartist.finestwebview.enums.Position;
import com.thefinestartist.finestwebview.helpers.DipPixelHelper;
import com.thefinestartist.finestwebview.helpers.ScreenHelper;
import com.thefinestartist.finestwebview.helpers.TypefaceHelper;


/**
 * Created by Leonardo on 11/14/15.
 */
public class FinestWebViewActivity extends AppCompatActivity {

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

    protected int toolbarColor;

    protected int iconDefaultColor;
    protected int iconDisabledColor;
    protected int iconPressedColor;
    protected int iconSelector;

    protected boolean showDivider;
    protected boolean gradientDivider;
    protected int dividerColor;
    protected float dividerHeight;

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

    protected String url;

    protected void initialize() {
        Intent intent = getIntent();
        if (intent == null)
            return;

        TypedValue typedValue = new TypedValue();
        TypedArray a = obtainStyledAttributes(typedValue.data, new int[]{
                R.attr.colorPrimary,
                R.attr.colorAccent,
                android.R.attr.textColorPrimary,
                android.R.attr.textColorSecondary});
        int colorPrimary = a.getColor(0, ContextCompat.getColor(this, R.color.white));
        int colorAccent = a.getColor(1, ContextCompat.getColor(this, R.color.black));
        int textColorPrimary = a.getColor(2, ContextCompat.getColor(this, R.color.black));
        int textColorSecondary = a.getColor(3, ContextCompat.getColor(this, R.color.silver));
        a.recycle();

        toolbarColor = intent.getIntExtra("toolbarColor", colorPrimary);

        iconDefaultColor = intent.getIntExtra("iconDefaultColor", colorAccent);
        iconDisabledColor = intent.getIntExtra("iconDisabledColor", colorAccent);
        iconPressedColor = intent.getIntExtra("iconPressedColor", colorAccent);
        iconSelector = intent.getIntExtra("iconSelector", R.drawable.selector_grey);

        showDivider = intent.getBooleanExtra("showDivider", true);
        gradientDivider = intent.getBooleanExtra("gradientDivider", true); // TODO
        dividerColor = intent.getIntExtra("dividerColor", ContextCompat.getColor(this, R.color.black)); // TODO
        dividerHeight = intent.getFloatExtra("dividerHeight", DipPixelHelper.getPixel(this, 2)); // TODO

        showProgressBar = intent.getBooleanExtra("showProgressBar", true);
        progressBarColor = intent.getIntExtra("progressBarColor", colorAccent);
        progressBarHeight = intent.getFloatExtra("progressBarHeight", DipPixelHelper.getPixel(this, 2));
        progressBarPosition = Position.fromSerializable(intent.getSerializableExtra("progressBarPosition"));

        titleDefault = intent.getStringExtra("titleDefault");
        updateTitleFromHtml = intent.getBooleanExtra("updateTitleFromHtml", true);
        titleSize = intent.getFloatExtra("titleSize", DipPixelHelper.getPixel(this, 14)); // TODO
        titleFont = intent.getStringExtra("titleFont") == null ? "Roboto-Medium.ttf" : intent.getStringExtra("titleFont");
        titleColor = intent.getIntExtra("titleColor", textColorPrimary);

        showUrl = intent.getBooleanExtra("showUrl", true);
        urlSize = intent.getFloatExtra("urlSize", DipPixelHelper.getPixel(this, 10));
        urlFont = intent.getStringExtra("urlFont") == null ? "Roboto-Regular.ttf" : intent.getStringExtra("titleFont");
        urlColor = intent.getIntExtra("urlColor", textColorSecondary);

        enterAnimation = intent.getIntExtra("enterAnimation", R.anim.modal_activity_close_enter);
        exitAnimation = intent.getIntExtra("exitAnimation", R.anim.modal_activity_close_exit);

        showRefresh = intent.getBooleanExtra("showRefresh", false); // TODO
        backPressToClose = intent.getBooleanExtra("backPressToClose", false); // TODO

        edgeControlSide = intent.getBooleanExtra("edgeControlSide", true); // TODO
        edgeControlTop = intent.getBooleanExtra("edgeControlTop", true); // TODO

        url = intent.getStringExtra("url");
    }

    protected Toolbar toolbar;
    protected TextView title;
    protected TextView urlTv;

    protected ImageButton close;
    protected ImageButton back;
    protected ImageButton forward;
    protected ImageButton more;

    protected WebView webView;

    protected View divider;
    protected ProgressBar progressBar;

    protected void bind() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) findViewById(R.id.title);
        urlTv = (TextView) findViewById(R.id.url);

        close = (ImageButton) findViewById(R.id.close);
        back = (ImageButton) findViewById(R.id.back);
        forward = (ImageButton) findViewById(R.id.forward);
        more = (ImageButton) findViewById(R.id.more);

        webView = (WebView) findViewById(R.id.webView);

        divider = findViewById(R.id.divider);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    protected void drawViews() {
        setSupportActionBar(toolbar);
        int maxWidth = getMaxWidth();

        updateIcon(close, R.drawable.ic_launcher);
        close.setBackgroundResource(iconSelector);
        updateIcon(back, R.drawable.ic_launcher);
        back.setBackgroundResource(iconSelector);
        updateIcon(forward, R.drawable.ic_launcher);
        forward.setBackgroundResource(iconSelector);
        updateIcon(more, R.drawable.ic_launcher);
        more.setBackgroundResource(iconSelector);

        { // Toolbar
            toolbar.setBackgroundColor(toolbarColor);
        }

        { // Title
            title.setText(titleDefault);
            title.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize);
            title.setTypeface(TypefaceHelper.get(this, titleFont));
            title.setTextColor(titleColor);
            title.setMaxWidth(maxWidth);
        }

        { // UrlTv
            urlTv.setText(url);
            urlTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, urlSize);
            urlTv.setTypeface(TypefaceHelper.get(this, urlFont));
            urlTv.setTextColor(urlColor);
            urlTv.setMaxWidth(maxWidth);
        }

        { // Divider
            divider.setVisibility(showDivider ? View.VISIBLE : View.GONE);
//        gradientDivider = intent.getBooleanExtra("gradientDivider", true); // TODO
            divider.setBackgroundColor(dividerColor);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    (int) dividerHeight
            );
            float toolbarHeight = getResources().getDimension(R.dimen.toolbarHeight);
            params.setMargins(0, (int) toolbarHeight, 0, 0);
            divider.setLayoutParams(params);
        }

        { // ProgressBar
            progressBar.setVisibility(showProgressBar ? View.VISIBLE : View.GONE);
            progressBar.getProgressDrawable().setColorFilter(progressBarColor, PorterDuff.Mode.SRC_IN);
            progressBar.setMinimumHeight((int) progressBarHeight);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    (int) progressBarHeight
            );
            float toolbarHeight = getResources().getDimension(R.dimen.toolbarHeight);
            switch (progressBarPosition) {
                case TOP_OF_TOOLBAR:
                    params.setMargins(0, 0, 0, 0);
                    break;
                case BOTTON_OF_TOOLBAR:
                    params.setMargins(0, (int) (toolbarHeight - progressBarHeight), 0, 0);
                    break;
                case TOP_OF_WEBVIEW:
                    params.setMargins(0, (int) toolbarHeight, 0, 0);
                    break;
                case BOTTOM_OF_WEBVIEW:
                    params.setMargins(0, (int) (ScreenHelper.getHeight(this) - progressBarHeight), 0, 0);
                    break;
            }
            progressBar.setLayoutParams(params);
            progressBar.setProgress(30);
        }

        { // WebView
            webView.getSettings().setUseWideViewPort(true);
            webView.setInitialScale(100);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setSupportZoom(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webView.getSettings().setAllowFileAccess(true);
            webView.getSettings().setDomStorageEnabled(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setAppCacheEnabled(true);
            webView.getSettings().setDisplayZoomControls(false);

            webView.setWebViewClient(new WebViewClient() {
            });

            webView.loadUrl(url);
        }
    }

    protected int getMaxWidth() {
        if (forward.getVisibility() == View.VISIBLE) {
            return (int) (ScreenHelper.getWidth(this) - DipPixelHelper.getPixel(this, 100));
        } else {
            return (int) (ScreenHelper.getWidth(this) - DipPixelHelper.getPixel(this, 52));
        }
    }

    protected void updateIcon(ImageButton icon, @DrawableRes int drawableRes) {
        StateListDrawable states = new StateListDrawable();
        {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableRes);
            BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
            drawable.setColorFilter(new PorterDuffColorFilter(iconPressedColor, PorterDuff.Mode.SRC_ATOP));
            states.addState(new int[]{android.R.attr.state_pressed}, drawable);
        }
        {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableRes);
            BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
            drawable.setColorFilter(new PorterDuffColorFilter(iconDisabledColor, PorterDuff.Mode.SRC_ATOP));
            states.addState(new int[]{-android.R.attr.state_enabled}, drawable);
        }
        {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableRes);
            BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
            drawable.setColorFilter(new PorterDuffColorFilter(iconDefaultColor, PorterDuff.Mode.SRC_ATOP));
            states.addState(new int[]{}, drawable);
        }
        icon.setImageDrawable(states);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finest_web_view);
        bind();
        initialize();
        drawViews();
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