package com.thefinestartist.finestwebview;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import com.thefinestartist.finestwebview.helpers.ClipboardHelper;
import com.thefinestartist.finestwebview.helpers.ColorHelper;
import com.thefinestartist.finestwebview.helpers.DipPixelHelper;
import com.thefinestartist.finestwebview.helpers.ScreenHelper;
import com.thefinestartist.finestwebview.helpers.TypefaceHelper;
import com.thefinestartist.finestwebview.helpers.UrlParser;
import com.thefinestartist.finestwebview.views.ShadowLayout;


/**
 * Created by Leonardo on 11/14/15.
 */
public class FinestWebViewActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    protected int theme;

    protected int statusBarColor;

    protected int toolbarColor;
    protected int toolbarScrollFlags;

    protected int iconDefaultColor;
    protected int iconDisabledColor;
    protected int iconPressedColor;
    protected int iconSelector;

    protected boolean showSwipeRefreshLayout;
    protected int swipeRefreshColor;
    protected int[] swipeRefreshColors;

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

    protected int menuColor;
    protected int menuDropShadowColor;
    protected float menuDropShadowSize;
    protected int menuSelector;

    protected float menuTextSize;
    protected String menuTextFont;
    protected int menuTextColor;

    protected boolean showMenuRefresh;
    protected int stringResRefresh;
    protected boolean showMenuShareVia;
    protected int stringResShareVia;
    protected boolean showMenuCopyLink;
    protected int stringResCopyLink;
    protected boolean showMenuOpenWith;
    protected int stringResOpenWith;

    protected int animationCloseEnter;
    protected int animationCloseExit;

    protected boolean backPressToClose;
    protected int stringResCopiedToClipboard;

    protected boolean webViewJavaScriptEnabled;
    protected boolean webViewAppCacheEnabled;
    protected boolean webViewAllowFileAccess;
    protected boolean webViewUseWideViewPort;
    protected boolean webViewLoadWithOverviewMode;
    protected boolean webViewDomStorageEnabled;
    protected boolean webViewBuiltInZoomControls;
    protected boolean webViewDisplayZoomControls;
    protected boolean webViewDesktopMode;

    protected String url;

    protected void getOptions() {
        Intent intent = getIntent();
        if (intent == null)
            return;

        TypedValue typedValue = new TypedValue();
        TypedArray a = obtainStyledAttributes(typedValue.data, new int[]{
                R.attr.colorPrimaryDark,
                R.attr.colorPrimary,
                R.attr.colorAccent,
                android.R.attr.textColorPrimary,
                android.R.attr.textColorSecondary,
                android.R.attr.selectableItemBackground,
                android.R.attr.selectableItemBackgroundBorderless});
        int colorPrimaryDark = a.getColor(0, ContextCompat.getColor(this, R.color.finestGray));
        int colorPrimary = a.getColor(1, ContextCompat.getColor(this, R.color.finestWhite));
        int colorAccent = a.getColor(2, ContextCompat.getColor(this, R.color.finestBlack));
        int textColorPrimary = a.getColor(3, ContextCompat.getColor(this, R.color.finestBlack));
        int textColorSecondary = a.getColor(4, ContextCompat.getColor(this, R.color.finestSilver));
        int selectableItemBackground = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                a.getResourceId(5, 0) : R.drawable.selector_light_theme;
        int selectableItemBackgroundBorderless = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ?
                a.getResourceId(6, 0) : R.drawable.selector_light_theme;
        a.recycle();

        theme = intent.getIntExtra("theme", 0);

        statusBarColor = intent.getIntExtra("statusBarColor", colorPrimaryDark);

        toolbarColor = intent.getIntExtra("toolbarColor", colorPrimary);
        toolbarScrollFlags = intent.getIntExtra("toolbarScrollFlags", AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);

        iconDefaultColor = intent.getIntExtra("iconDefaultColor", colorAccent);
        iconDisabledColor = intent.getIntExtra("iconDisabledColor", ColorHelper.disableColor(iconDefaultColor));
        iconPressedColor = intent.getIntExtra("iconPressedColor", iconDefaultColor);
        iconSelector = intent.getIntExtra("iconSelector", selectableItemBackgroundBorderless);

        showSwipeRefreshLayout = intent.getBooleanExtra("showSwipeRefreshLayout", true);
        swipeRefreshColor = intent.getIntExtra("swipeRefreshColor", colorAccent);
        swipeRefreshColors = intent.getIntArrayExtra("swipeRefreshColors");

        showDivider = intent.getBooleanExtra("showDivider", true);
        gradientDivider = intent.getBooleanExtra("gradientDivider", true);
        dividerColor = intent.getIntExtra("dividerColor", ContextCompat.getColor(this, R.color.finestBlack10));
        dividerHeight = intent.getFloatExtra("dividerHeight", getResources().getDimension(R.dimen.defaultDividerHeight));

        showProgressBar = intent.getBooleanExtra("showProgressBar", true);
        progressBarColor = intent.getIntExtra("progressBarColor", colorAccent);
        progressBarHeight = intent.getFloatExtra("progressBarHeight", getResources().getDimension(R.dimen.defaultProgressBarHeight));
        progressBarPosition = Position.fromSerializable(intent.getSerializableExtra("progressBarPosition"));

        titleDefault = intent.getStringExtra("titleDefault");
        updateTitleFromHtml = intent.getBooleanExtra("updateTitleFromHtml", true);
        titleSize = intent.getFloatExtra("titleSize", getResources().getDimension(R.dimen.defaultTitleSize));
        titleFont = intent.getStringExtra("titleFont") == null ? "Roboto-Medium.ttf" : intent.getStringExtra("titleFont");
        titleColor = intent.getIntExtra("titleColor", textColorPrimary);

        showUrl = intent.getBooleanExtra("showUrl", true);
        urlSize = intent.getFloatExtra("urlSize", getResources().getDimension(R.dimen.defaultUrlSize));
        urlFont = intent.getStringExtra("urlFont") == null ? "Roboto-Regular.ttf" : intent.getStringExtra("urlFont");
        urlColor = intent.getIntExtra("urlColor", textColorSecondary);

        menuColor = intent.getIntExtra("menuColor", ContextCompat.getColor(this, R.color.finestWhite));
        menuDropShadowColor = intent.getIntExtra("menuDropShadowColor", ContextCompat.getColor(this, R.color.finestBlack10));
        menuDropShadowSize = intent.getFloatExtra("menuDropShadowSize", getResources().getDimension(R.dimen.defaultMenuDropShadowSize));
        menuSelector = intent.getIntExtra("menuSelector", selectableItemBackground);

        menuTextSize = intent.getFloatExtra("menuTextSize", getResources().getDimension(R.dimen.defaultMenuTextSize));
        menuTextFont = intent.getStringExtra("menuTextFont") == null ? "Roboto-Regular.ttf" : intent.getStringExtra("menuTextFont");
        menuTextColor = intent.getIntExtra("menuTextColor", ContextCompat.getColor(this, R.color.finestBlack));

        showMenuRefresh = intent.getBooleanExtra("showMenuRefresh", true);
        stringResRefresh = intent.getIntExtra("stringResRefresh", R.string.refresh);
        showMenuShareVia = intent.getBooleanExtra("showMenuShareVia", true);
        stringResShareVia = intent.getIntExtra("stringResShareVia", R.string.share_via);
        showMenuCopyLink = intent.getBooleanExtra("showMenuCopyLink", true);
        stringResCopyLink = intent.getIntExtra("stringResCopyLink", R.string.copy_link);
        showMenuOpenWith = intent.getBooleanExtra("showMenuOpenWith", true);
        stringResOpenWith = intent.getIntExtra("stringResOpenWith", R.string.open_with);

        animationCloseEnter = intent.getIntExtra("animationCloseEnter", R.anim.modal_activity_close_enter);
        animationCloseExit = intent.getIntExtra("animationCloseExit", R.anim.modal_activity_close_exit);

        backPressToClose = intent.getBooleanExtra("backPressToClose", false);
        stringResCopiedToClipboard = intent.getIntExtra("stringResCopiedToClipboard", R.string.copied_to_clipboard);

        webViewJavaScriptEnabled = intent.getBooleanExtra("webViewJavaScriptEnabled", true);
        webViewAppCacheEnabled = intent.getBooleanExtra("webViewAppCacheEnabled", true);
        webViewAllowFileAccess = intent.getBooleanExtra("webViewAllowFileAccess", true);
        webViewUseWideViewPort = intent.getBooleanExtra("webViewUseWideViewPort", true);
        webViewLoadWithOverviewMode = intent.getBooleanExtra("webViewLoadWithOverviewMode", true);
        webViewDomStorageEnabled = intent.getBooleanExtra("webViewDomStorageEnabled", true);
        webViewBuiltInZoomControls = intent.getBooleanExtra("webViewBuiltInZoomControls", false);
        webViewDisplayZoomControls = intent.getBooleanExtra("webViewDisplayZoomControls", false);
        webViewDesktopMode = intent.getBooleanExtra("webViewDesktopMode", false);

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

    protected SwipeRefreshLayout swipeRefreshLayout;
    protected WebView webView;

    protected View gradient;
    protected View divider;
    protected ProgressBar progressBar;

    protected RelativeLayout menuLayout;
    protected ShadowLayout shadowLayout;
    protected LinearLayout menuBackground;

    protected LinearLayout menuRefresh;
    protected TextView menuRefreshTv;
    protected LinearLayout menuShareVia;
    protected TextView menuShareViaTv;
    protected LinearLayout menuCopyLink;
    protected TextView menuCopyLinkTv;
    protected LinearLayout menuOpenWith;
    protected TextView menuOpenWithTv;

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

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        webView = (WebView) findViewById(R.id.webView);

        gradient = findViewById(R.id.gradient);
        divider = findViewById(R.id.divider);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        menuLayout = (RelativeLayout) findViewById(R.id.menuLayout);
        shadowLayout = (ShadowLayout) findViewById(R.id.shadowLayout);
        menuBackground = (LinearLayout) findViewById(R.id.menuBackground);

        menuRefresh = (LinearLayout) findViewById(R.id.menuRefresh);
        menuRefreshTv = (TextView) findViewById(R.id.menuRefreshTv);
        menuShareVia = (LinearLayout) findViewById(R.id.menuShareVia);
        menuShareViaTv = (TextView) findViewById(R.id.menuShareViaTv);
        menuCopyLink = (LinearLayout) findViewById(R.id.menuCopyLink);
        menuCopyLinkTv = (TextView) findViewById(R.id.menuCopyLinkTv);
        menuOpenWith = (LinearLayout) findViewById(R.id.menuOpenWith);
        menuOpenWithTv = (TextView) findViewById(R.id.menuOpenWithTv);
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
            title.setMaxWidth(maxWidth);
            urlTv.setMaxWidth(maxWidth);
            requestCenterLayout();
        }

        { // Icons
            updateIcon(close, R.drawable.close);
            updateIcon(back, R.drawable.back);
            updateIcon(forward, R.drawable.forward);
            updateIcon(more, R.drawable.more);
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
    }

    protected void initializeViews() {
        setSupportActionBar(toolbar);

        { // StatusBar
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(statusBarColor);
            }
        }

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
            title.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize);
            title.setTypeface(TypefaceHelper.get(this, titleFont));
            title.setTextColor(titleColor);

            urlTv.setText(UrlParser.getHost(url));
            urlTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, urlSize);
            urlTv.setTypeface(TypefaceHelper.get(this, urlFont));
            urlTv.setTextColor(urlColor);

            requestCenterLayout();
        }

        { // Icons
            close.setBackgroundResource(iconSelector);
            back.setBackgroundResource(iconSelector);
            forward.setBackgroundResource(iconSelector);
            more.setBackgroundResource(iconSelector);
            if (showMenuRefresh || showMenuShareVia || showMenuCopyLink || showMenuOpenWith)
                more.setVisibility(View.VISIBLE);
            else
                more.setVisibility(View.GONE);
        }

        { // WebView
            webView.setWebChromeClient(new MyWebChromeClient());
            webView.setWebViewClient(new MyWebViewClient());

            webView.getSettings().setJavaScriptEnabled(webViewJavaScriptEnabled);
            webView.getSettings().setAppCacheEnabled(webViewAppCacheEnabled);
            webView.getSettings().setAllowFileAccess(webViewAllowFileAccess);
            webView.getSettings().setUseWideViewPort(webViewUseWideViewPort);
            webView.getSettings().setLoadWithOverviewMode(webViewLoadWithOverviewMode);
            webView.getSettings().setDomStorageEnabled(webViewDomStorageEnabled);

//            // Other webview options
//            webView.getSettings().setLoadWithOverviewMode(true);
//            webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
//            webView.setScrollbarFadingEnabled(false);
//            webView.getSettings().setBuiltInZoomControls(true);
//            //Additional Webview Properties
//            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//            webView.getSettings().setDatabaseEnabled(true);
//            webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
//            webView.getSettings().setLayoutAlgorithm(webView.getSettings().getLayoutAlgorithm().NORMAL);
//            webView.getSettings().setLoadWithOverviewMode(true);
//            webView.getSettings().setUseWideViewPort(false);
//            webView.setSoundEffectsEnabled(true);
//            webView.setHorizontalFadingEdgeEnabled(false);
//            webView.setKeepScreenOn(true);
//            webView.setScrollbarFadingEnabled(true);
//            webView.setVerticalFadingEdgeEnabled(false);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
                webView.getSettings().setDisplayZoomControls(webViewDisplayZoomControls);

            webView.getSettings().setBuiltInZoomControls(webViewBuiltInZoomControls);
            if (webViewBuiltInZoomControls) {
                // Remove NestedScrollView to enable BuiltInZoomControls
                ((ViewGroup) webView.getParent()).removeAllViews();
                swipeRefreshLayout.addView(webView);
                swipeRefreshLayout.removeViewAt(1);
            }

            if (webViewDesktopMode)
                webView.getSettings().setUserAgentString("Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.4) Gecko/20100101 Firefox/4.0");

            webView.loadUrl(url);
        }

        { // SwipeRefreshLayout
            swipeRefreshLayout.setEnabled(showSwipeRefreshLayout);
            if (showSwipeRefreshLayout) {
                swipeRefreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(true);
                    }
                });
            }

            if (swipeRefreshColors == null)
                swipeRefreshLayout.setColorSchemeColors(swipeRefreshColor);
            else swipeRefreshLayout.setColorSchemeColors(swipeRefreshColors);

            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    webView.reload();
                }
            });
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
        }

        { // Menu
            GradientDrawable drawable = new GradientDrawable();
            drawable.setCornerRadius(getResources().getDimension(R.dimen.defaultMenuCornerRadius));
            drawable.setColor(menuColor);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                menuBackground.setBackground(drawable);
            else
                menuBackground.setBackgroundDrawable(drawable);

            shadowLayout.setShadowColor(menuDropShadowColor);
            shadowLayout.setShadowSize(menuDropShadowSize);

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) shadowLayout.getLayoutParams();
            int margin = (int) (getResources().getDimension(R.dimen.defaultMenuLayoutMargin) - menuDropShadowSize);
            params.setMargins(0, margin, margin, 0);
            shadowLayout.setLayoutParams(params);

            menuRefresh.setVisibility(showMenuRefresh ? View.VISIBLE : View.GONE);
            menuRefresh.setBackgroundResource(menuSelector);
            menuRefreshTv.setText(stringResRefresh);
            menuRefreshTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, menuTextSize);
            menuRefreshTv.setTypeface(TypefaceHelper.get(this, menuTextFont));
            menuRefreshTv.setTextColor(menuTextColor);

            menuShareVia.setVisibility(showMenuShareVia ? View.VISIBLE : View.GONE);
            menuShareVia.setBackgroundResource(menuSelector);
            menuShareViaTv.setText(stringResShareVia);
            menuShareViaTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, menuTextSize);
            menuShareViaTv.setTypeface(TypefaceHelper.get(this, menuTextFont));
            menuShareViaTv.setTextColor(menuTextColor);

            menuCopyLink.setVisibility(showMenuCopyLink ? View.VISIBLE : View.GONE);
            menuCopyLink.setBackgroundResource(menuSelector);
            menuCopyLinkTv.setText(stringResCopyLink);
            menuCopyLinkTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, menuTextSize);
            menuCopyLinkTv.setTypeface(TypefaceHelper.get(this, menuTextFont));
            menuCopyLinkTv.setTextColor(menuTextColor);

            menuOpenWith.setVisibility(showMenuOpenWith ? View.VISIBLE : View.GONE);
            menuOpenWith.setBackgroundResource(menuSelector);
            menuOpenWithTv.setText(stringResOpenWith);
            menuOpenWithTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, menuTextSize);
            menuOpenWithTv.setTypeface(TypefaceHelper.get(this, menuTextFont));
            menuOpenWithTv.setTextColor(menuTextColor);
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
        getOptions();
        if (theme != 0) setTheme(theme);
        setContentView(R.layout.finest_web_view);
        bindViews();
        layoutViews();
        initializeViews();
    }

    @Override
    public void onBackPressed() {
        if (menuLayout.getVisibility() == View.VISIBLE) {
            hideMenu();
        } else if (backPressToClose || !webView.canGoBack()) {
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
            showMenu();
        } else if (viewId == R.id.menuLayout) {
            hideMenu();
        } else if (viewId == R.id.menuRefresh) {
            webView.reload();
            hideMenu();
        } else if (viewId == R.id.menuShareVia) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, webView.getUrl());
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, getResources().getString(stringResShareVia)));

            hideMenu();
        } else if (viewId == R.id.menuCopyLink) {
            ClipboardHelper.clip(this, webView.getUrl());

            Snackbar snackbar = Snackbar.make(coordinatorLayout, getString(stringResCopiedToClipboard), Snackbar.LENGTH_LONG);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(toolbarColor);
            if (snackbarView instanceof ViewGroup)
                updateChildTextView((ViewGroup) snackbarView);
            snackbar.show();

            hideMenu();
        } else if (viewId == R.id.menuOpenWith) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webView.getUrl()));
            startActivity(browserIntent);

            hideMenu();
        }
    }

    protected void updateChildTextView(ViewGroup viewGroup) {
        if (viewGroup == null || viewGroup.getChildCount() == 0)
            return;

        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                textView.setTextColor(titleColor);
                textView.setTypeface(TypefaceHelper.get(this, titleFont));
                textView.setLineSpacing(0, 1.1f);
                textView.setIncludeFontPadding(false);
            }

            if (view instanceof ViewGroup)
                updateChildTextView((ViewGroup) view);
        }
    }

    protected void showMenu() {
        menuLayout.setVisibility(View.VISIBLE);
        Animation popupAnim = AnimationUtils.loadAnimation(this, R.anim.popup_flyout_show);
        shadowLayout.startAnimation(popupAnim);
    }

    protected void hideMenu() {
        Animation popupAnim = AnimationUtils.loadAnimation(this, R.anim.popup_flyout_hide);
        shadowLayout.startAnimation(popupAnim);
        popupAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                menuLayout.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
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

        if (menuLayout.getVisibility() == View.VISIBLE
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            ViewHelper.setTranslationY(menuLayout, Math.max(verticalOffset, -getResources().getDimension(R.dimen.defaultMenuLayoutMargin)));
    }

    public class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int progress) {
            if (showSwipeRefreshLayout) {
                if (swipeRefreshLayout.isRefreshing() && progress == 100) {
                    swipeRefreshLayout.post(new Runnable() {
                        @Override
                        public void run() {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    });
                }

                if (!swipeRefreshLayout.isRefreshing() && progress != 100) {
                    swipeRefreshLayout.post(new Runnable() {
                        @Override
                        public void run() {
                            swipeRefreshLayout.setRefreshing(true);
                        }
                    });
                }
            }

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
            if (updateTitleFromHtml)
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null)
            webView.destroy();
    }
}