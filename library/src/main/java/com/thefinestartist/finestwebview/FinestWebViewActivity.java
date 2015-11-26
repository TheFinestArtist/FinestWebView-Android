package com.thefinestartist.finestwebview;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;
import com.thefinestartist.finestwebview.enums.Position;
import com.thefinestartist.finestwebview.helpers.BitmapHelper;
import com.thefinestartist.finestwebview.helpers.DipPixelHelper;
import com.thefinestartist.finestwebview.helpers.ScreenHelper;
import com.thefinestartist.finestwebview.helpers.TypefaceHelper;
import com.thefinestartist.finestwebview.helpers.UrlParser;


/**
 * Created by Leonardo on 11/14/15.
 */
public class FinestWebViewActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    protected int toolbarColor;
    protected int toolbarScrollFlags;

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

    protected int animationCloseEnter;
    protected int animationCloseExit;

    protected boolean showRefresh;
    protected boolean backPressToClose;

    protected boolean collapsingToolbar;
    protected boolean edgeControlSide;
    protected boolean edgeControlTop;

    protected String url;

    protected void getOptions() {
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
        toolbarScrollFlags = intent.getIntExtra("toolbarScrollFlags", AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);

        iconDefaultColor = intent.getIntExtra("iconDefaultColor", colorAccent);
        iconDisabledColor = intent.getIntExtra("iconDisabledColor", colorAccent);
        iconPressedColor = intent.getIntExtra("iconPressedColor", colorAccent);
        iconSelector = intent.getIntExtra("iconSelector", R.drawable.selector_grey);

        showDivider = intent.getBooleanExtra("showDivider", true);
        gradientDivider = intent.getBooleanExtra("gradientDivider", true);
        dividerColor = intent.getIntExtra("dividerColor", ContextCompat.getColor(this, R.color.silver));
        dividerHeight = intent.getFloatExtra("dividerHeight", DipPixelHelper.getPixel(this, 2));

        showProgressBar = intent.getBooleanExtra("showProgressBar", true);
        progressBarColor = intent.getIntExtra("progressBarColor", colorAccent);
        progressBarHeight = intent.getFloatExtra("progressBarHeight", DipPixelHelper.getPixel(this, 2));
        progressBarPosition = Position.fromSerializable(intent.getSerializableExtra("progressBarPosition"));

        titleDefault = intent.getStringExtra("titleDefault");
        updateTitleFromHtml = intent.getBooleanExtra("updateTitleFromHtml", true);
        titleSize = intent.getFloatExtra("titleSize", DipPixelHelper.getPixel(this, 14));
        titleFont = intent.getStringExtra("titleFont") == null ? "Roboto-Medium.ttf" : intent.getStringExtra("titleFont");
        titleColor = intent.getIntExtra("titleColor", textColorPrimary);

        showUrl = intent.getBooleanExtra("showUrl", true);
        urlSize = intent.getFloatExtra("urlSize", DipPixelHelper.getPixel(this, 10));
        urlFont = intent.getStringExtra("urlFont") == null ? "Roboto-Regular.ttf" : intent.getStringExtra("titleFont");
        urlColor = intent.getIntExtra("urlColor", textColorSecondary);

        animationCloseEnter = intent.getIntExtra("animationCloseEnter", R.anim.modal_activity_close_enter);
        animationCloseExit = intent.getIntExtra("animationCloseExit", R.anim.modal_activity_close_exit);

        showRefresh = intent.getBooleanExtra("showRefresh", false); // TODO
        backPressToClose = intent.getBooleanExtra("backPressToClose", false);

        collapsingToolbar = intent.getBooleanExtra("collapsingToolbar", true);
        edgeControlSide = intent.getBooleanExtra("edgeControlSide", true); // TODO
        edgeControlTop = intent.getBooleanExtra("edgeControlTop", true); // TODO

        url = intent.getStringExtra("url");
    }

    protected CoordinatorLayout coordinatorLayout;

    protected AppBarLayout appBar;
    protected Toolbar toolbar;
    protected RelativeLayout toolbarLayout;

    protected TextView title;
    protected TextView urlTv;

    protected ImageButton close;
    protected ImageButton back;
    protected ImageButton forward;
    protected ImageButton more;

    protected NestedScrollView nestedScrollView;
    protected WebView webView;

    protected View gradient;
    protected View divider;
    protected ProgressBar progressBar;

    protected void bindViews() {
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        appBar = (AppBarLayout) findViewById(R.id.appBar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarLayout = (RelativeLayout) findViewById(R.id.toolbarLayout);

        title = (TextView) findViewById(R.id.title);
        urlTv = (TextView) findViewById(R.id.url);

        close = (ImageButton) findViewById(R.id.close);
        back = (ImageButton) findViewById(R.id.back);
        forward = (ImageButton) findViewById(R.id.forward);
        more = (ImageButton) findViewById(R.id.more);

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        webView = (WebView) findViewById(R.id.webView);

        gradient = findViewById(R.id.gradient);
        divider = findViewById(R.id.divider);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    protected void layoutViews() {
        setSupportActionBar(toolbar);

        { // AppBar
            float toolbarHeight = getResources().getDimension(R.dimen.toolbarHeight);
            if (!gradientDivider)
                toolbarHeight += dividerHeight;
            CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) toolbarHeight);
            appBar.setLayoutParams(params);
            coordinatorLayout.requestLayout();
        }

        { // Toolbar
            float toolbarHeight = getResources().getDimension(R.dimen.toolbarHeight);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) toolbarHeight);
            toolbarLayout.setMinimumHeight((int) toolbarHeight);
            toolbarLayout.setLayoutParams(params);
            coordinatorLayout.requestLayout();
        }

        { // TextViews
            int maxWidth = getMaxWidth();

            title.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize);
            title.setMaxWidth(maxWidth);

            urlTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, urlSize);
            urlTv.setMaxWidth(maxWidth);

            requestCenterLayout();
        }

        { // Icons
            updateIcon(close, R.drawable.ic_launcher);
            updateIcon(back, R.drawable.ic_launcher);
            updateIcon(forward, R.drawable.ic_launcher);
            updateIcon(more, R.drawable.ic_launcher);
        }

        { // Content
        }

        { // Divider
            if (gradientDivider) {
                float toolbarHeight = getResources().getDimension(R.dimen.toolbarHeight);
                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) gradient.getLayoutParams();
                params.setMargins(0, (int) toolbarHeight, 0, 0);
                gradient.setLayoutParams(params);
            }
        }

        { // ProgressBar
            progressBar.setMinimumHeight((int) progressBarHeight);
            CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    (int) progressBarHeight
            );
            float toolbarHeight = getResources().getDimension(R.dimen.toolbarHeight);
            switch (progressBarPosition) {
                case TOP_OF_TOOLBAR:
                    params.setMargins(0, 0, 0, 0);
                    break;
                case BOTTON_OF_TOOLBAR:
                    params.setMargins(0, (int) toolbarHeight - (int) progressBarHeight, 0, 0);
                    break;
                case TOP_OF_WEBVIEW:
                    params.setMargins(0, (int) toolbarHeight, 0, 0);
                    break;
                case BOTTOM_OF_WEBVIEW:
                    params.setMargins(0, ScreenHelper.getHeight(this) - (int) progressBarHeight, 0, 0);
                    break;
            }
            progressBar.setLayoutParams(params);
        }

        { // Options

        }
    }

    protected void initializeViews() {
        setSupportActionBar(toolbar);

        { // AppBar
            appBar.addOnOffsetChangedListener(this);
        }

        { // Toolbar
            toolbar.setBackgroundColor(toolbarColor);
            AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
            params.setScrollFlags(toolbarScrollFlags);
            toolbar.setLayoutParams(params);
        }

        { // TextViews
            title.setText(titleDefault);
            title.setTypeface(TypefaceHelper.get(this, titleFont));
            title.setTextColor(titleColor);

            urlTv.setText(UrlParser.getHost(url));
            urlTv.setTypeface(TypefaceHelper.get(this, urlFont));
            urlTv.setTextColor(urlColor);

            requestCenterLayout();
        }

        { // Icons
            close.setBackgroundResource(iconSelector);
            back.setBackgroundResource(iconSelector);
            forward.setBackgroundResource(iconSelector);
            more.setBackgroundResource(iconSelector);
        }

        { // Content
            webView.setWebChromeClient(new MyWebChromeClient());
            webView.setWebViewClient(new MyWebViewClient());
//            webView.getSettings().setUseWideViewPort(true);
//            webView.setInitialScale(100);
//            webView.getSettings().setUseWideViewPort(true);
//            webView.getSettings().setBuiltInZoomControls(true);
//            webView.getSettings().setSupportZoom(true);
//            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//            webView.getSettings().setAllowFileAccess(true);
//            webView.getSettings().setDomStorageEnabled(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setAppCacheEnabled(true);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
//                webView.getSettings().setDisplayZoomControls(false);
//            else
//                webView.getSettings().setBuiltInZoomControls(false);
            webView.loadUrl(url);
        }

        { // Divider
            gradient.setVisibility(showDivider && gradientDivider ? View.VISIBLE : View.GONE);
            divider.setVisibility(showDivider && !gradientDivider ? View.VISIBLE : View.GONE);
            if (gradientDivider) {
                int dividerWidth = ScreenHelper.getWidth(this);
                Bitmap bitmap = BitmapHelper.getGradientBitmap(dividerWidth, (int) dividerHeight, dividerColor);
                BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    gradient.setBackgroundDrawable(drawable);
                } else {
                    gradient.setBackground(drawable);
                }

                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) gradient.getLayoutParams();
                params.height = (int) dividerHeight;
                gradient.setLayoutParams(params);
            } else {
                divider.setBackgroundColor(dividerColor);

                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) divider.getLayoutParams();
                params.height = (int) dividerHeight;
                divider.setLayoutParams(params);
            }
        }

        { // ProgressBar
            progressBar.setVisibility(showProgressBar ? View.VISIBLE : View.GONE);
            progressBar.getProgressDrawable().setColorFilter(progressBarColor, PorterDuff.Mode.SRC_IN);
            progressBar.setMinimumHeight((int) progressBarHeight);
            CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    (int) progressBarHeight
            );
            float toolbarHeight = getResources().getDimension(R.dimen.toolbarHeight);
            switch (progressBarPosition) {
                case TOP_OF_TOOLBAR:
                    params.setMargins(0, 0, 0, 0);
                    break;
                case BOTTON_OF_TOOLBAR:
                    params.setMargins(0, (int) toolbarHeight - (int) progressBarHeight, 0, 0);
                    break;
                case TOP_OF_WEBVIEW:
                    params.setMargins(0, (int) toolbarHeight, 0, 0);
                    break;
                case BOTTOM_OF_WEBVIEW:
                    params.setMargins(0, ScreenHelper.getHeight(this) - (int) progressBarHeight, 0, 0);
                    break;
            }
            progressBar.setLayoutParams(params);
            progressBar.setProgress(30);
        }

        { // Options

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
            Bitmap bitmap = BitmapHelper.getColoredBitmap(this, drawableRes, iconPressedColor);
            BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
            states.addState(new int[]{android.R.attr.state_pressed}, drawable);
        }
        {
            Bitmap bitmap = BitmapHelper.getColoredBitmap(this, drawableRes, iconDisabledColor);
            BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
            states.addState(new int[]{-android.R.attr.state_enabled}, drawable);
        }
        {
            Bitmap bitmap = BitmapHelper.getColoredBitmap(this, drawableRes, iconDefaultColor);
            BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
            states.addState(new int[]{}, drawable);
        }
        icon.setImageDrawable(states);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finest_web_view);
        getOptions();
        bindViews();
        layoutViews();
        initializeViews();
    }

    @Override
    public void onBackPressed() {
        if (backPressToClose || !webView.canGoBack()) {
            close();
        } else {
            webView.goBack();
        }
    }

    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.close) {
            close();
        } else if (viewId == R.id.back) {
            webView.goBack();
        } else if (viewId == R.id.forward) {
            webView.goForward();
        } else if (viewId == R.id.more) {

        }
    }

    protected void close() {
        super.onBackPressed();
        overridePendingTransition(animationCloseEnter, animationCloseExit);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (toolbarScrollFlags == 0)
            return;

        ViewHelper.setTranslationY(gradient, verticalOffset);
        ViewHelper.setAlpha(gradient, 1 - (float) Math.abs(verticalOffset) / (float) appBarLayout.getTotalScrollRange());

        switch (progressBarPosition) {
            case BOTTON_OF_TOOLBAR:
                ViewHelper.setTranslationY(progressBar, Math.max(verticalOffset, progressBarHeight - appBarLayout.getTotalScrollRange()));
                break;
            case TOP_OF_WEBVIEW:
                ViewHelper.setTranslationY(progressBar, verticalOffset);
                break;
            case TOP_OF_TOOLBAR:
            case BOTTOM_OF_WEBVIEW:
            default:
                break;
        }
    }

    public class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int progress) {
            if (progress == 100)
                progress = 0;
            progressBar.setProgress(progress);
        }
    }

    public class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            title.setText(view.getTitle());
            urlTv.setText(UrlParser.getHost(url));
            requestCenterLayout();

            if (view.canGoBack() || view.canGoForward()) {
                back.setVisibility(View.VISIBLE);
                forward.setVisibility(View.VISIBLE);
                back.setEnabled(view.canGoBack());
                forward.setEnabled(view.canGoForward());
            } else {
                back.setVisibility(View.GONE);
                forward.setVisibility(View.GONE);
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.endsWith(".mp4")) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(url), "video/*");
                view.getContext().startActivity(intent);
                return true;
            } else {
                return super.shouldOverrideUrlLoading(view, url);
            }
        }
    }

    protected void requestCenterLayout() {
        int maxWidth;
        if (webView.canGoBack() || webView.canGoForward()) {
            maxWidth = (int) (ScreenHelper.getWidth(this) - DipPixelHelper.getPixel(this, 48) * 4);
        } else {
            maxWidth = (int) (ScreenHelper.getWidth(this) - DipPixelHelper.getPixel(this, 48) * 2);
        }

        title.setMaxWidth(maxWidth);
        urlTv.setMaxWidth(maxWidth);
        title.requestLayout();
        urlTv.requestLayout();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layoutViews();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutViews();
        }
    }
}