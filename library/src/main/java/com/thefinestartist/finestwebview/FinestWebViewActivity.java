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
import com.thefinestartist.finestwebview.helpers.DipPixelHelper;
import com.thefinestartist.finestwebview.helpers.ScreenHelper;
import com.thefinestartist.finestwebview.helpers.TypefaceHelper;
import com.thefinestartist.finestwebview.helpers.UrlParser;
import com.thefinestartist.finestwebview.views.ShadowLayout;


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

    protected int animationCloseEnter;
    protected int animationCloseExit;

    protected boolean backPressToClose;

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
        int colorPrimary = a.getColor(0, ContextCompat.getColor(this, R.color.finestWhite));
        int colorAccent = a.getColor(1, ContextCompat.getColor(this, R.color.finestBlack));
        int textColorPrimary = a.getColor(2, ContextCompat.getColor(this, R.color.finestBlack));
        int textColorSecondary = a.getColor(3, ContextCompat.getColor(this, R.color.finestSilver));
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
        dividerColor = intent.getIntExtra("dividerColor", ContextCompat.getColor(this, R.color.finestBlack30));
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
        menuDropShadowColor = intent.getIntExtra("menuDropShadowColor", ContextCompat.getColor(this, R.color.finestSilver));
        menuDropShadowSize = intent.getFloatExtra("menuDropShadowSize", getResources().getDimension(R.dimen.defaultMenuDropShadowSize));
        menuSelector = intent.getIntExtra("menuSelector", R.drawable.selector_grey);

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

    protected RelativeLayout menuBackground;
    protected ShadowLayout menuLayout;
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

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        webView = (WebView) findViewById(R.id.webView);

        gradient = findViewById(R.id.gradient);
        divider = findViewById(R.id.divider);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        menuBackground = (RelativeLayout) findViewById(R.id.menuBackground);
        menuLayout = (ShadowLayout) findViewById(R.id.menuLayout);
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

        { // Menu
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
            menuBackground.setVisibility(View.VISIBLE);
            Animation popupAnim = AnimationUtils.loadAnimation(this, R.anim.popup_flyout_show);
            menuLayout.startAnimation(popupAnim);
        } else if (viewId == R.id.menuBackground) {
            Animation popupAnim = AnimationUtils.loadAnimation(this, R.anim.popup_flyout_hide);
            menuLayout.startAnimation(popupAnim);
            popupAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    menuBackground.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
        } else if (viewId == R.id.menuRefresh) {
            webView.reload();
        } else if (viewId == R.id.menuShareVia) {
        } else if (viewId == R.id.menuCopyLink) {
        } else if (viewId == R.id.menuOpenWith) {
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

        ViewHelper.setTranslationY(menuLayout, Math.max(verticalOffset, - getResources().getDimension(R.dimen.defaultMenuLayoutMargin)));
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
}