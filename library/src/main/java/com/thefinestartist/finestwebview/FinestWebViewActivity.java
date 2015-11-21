package com.thefinestartist.finestwebview;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ObservableWebView;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.thefinestartist.finestwebview.enums.Position;
import com.thefinestartist.finestwebview.helpers.DipPixelHelper;
import com.thefinestartist.finestwebview.helpers.ScreenHelper;
import com.thefinestartist.finestwebview.helpers.TypefaceHelper;


/**
 * Created by Leonardo on 11/14/15.
 */
public class FinestWebViewActivity extends AppCompatActivity implements ObservableScrollViewCallbacks {

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
        gradientDivider = intent.getBooleanExtra("gradientDivider", true);
        dividerColor = intent.getIntExtra("dividerColor", ContextCompat.getColor(this, R.color.black));
        dividerHeight = intent.getFloatExtra("dividerHeight", DipPixelHelper.getPixel(this, 2));

        showProgressBar = intent.getBooleanExtra("showProgressBar", true);
        progressBarColor = intent.getIntExtra("progressBarColor", colorAccent);
        progressBarHeight = intent.getFloatExtra("progressBarHeight", DipPixelHelper.getPixel(this, 2));
        progressBarPosition = Position.fromSerializable(intent.getSerializableExtra("progressBarPosition"));

        titleDefault = intent.getStringExtra("titleDefault");
        updateTitleFromHtml = intent.getBooleanExtra("updateTitleFromHtml", true); // TODO
        titleSize = intent.getFloatExtra("titleSize", DipPixelHelper.getPixel(this, 14));
        titleFont = intent.getStringExtra("titleFont") == null ? "Roboto-Medium.ttf" : intent.getStringExtra("titleFont");
        titleColor = intent.getIntExtra("titleColor", textColorPrimary);

        showUrl = intent.getBooleanExtra("showUrl", true);
        urlSize = intent.getFloatExtra("urlSize", DipPixelHelper.getPixel(this, 10));
        urlFont = intent.getStringExtra("urlFont") == null ? "Roboto-Regular.ttf" : intent.getStringExtra("titleFont");
        urlColor = intent.getIntExtra("urlColor", textColorSecondary);

        enterAnimation = intent.getIntExtra("enterAnimation", R.anim.activity_close_enter); // TODO
        exitAnimation = intent.getIntExtra("exitAnimation", R.anim.activity_close_exit); // TODO

        showRefresh = intent.getBooleanExtra("showRefresh", false); // TODO
        backPressToClose = intent.getBooleanExtra("backPressToClose", false); // TODO

        edgeControlSide = intent.getBooleanExtra("edgeControlSide", true); // TODO
        edgeControlTop = intent.getBooleanExtra("edgeControlTop", true);

        url = intent.getStringExtra("url");
    }

    protected Toolbar toolbar;
    protected TextView title;
    protected TextView urlTv;

    protected ImageButton close;
    protected ImageButton back;
    protected ImageButton forward;
    protected ImageButton more;

    protected View divider;
    protected ProgressBar progressBar;
    protected ObservableWebView webView;

    protected void bind() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) findViewById(R.id.title);
        urlTv = (TextView) findViewById(R.id.url);

        close = (ImageButton) findViewById(R.id.close);
        back = (ImageButton) findViewById(R.id.back);
        forward = (ImageButton) findViewById(R.id.forward);
        more = (ImageButton) findViewById(R.id.more);

        divider = findViewById(R.id.divider);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        webView = (ObservableWebView) findViewById(R.id.webView);
        webView.setScrollViewCallbacks(this);
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
            if (gradientDivider) {
                GradientDrawable gradient = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{dividerColor, ContextCompat.getColor(this, android.R.color.transparent)});
                gradient.setGradientType(GradientDrawable.LINEAR_GRADIENT);
                gradient.setShape(GradientDrawable.RECTANGLE);
                if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    divider.setBackgroundDrawable(gradient);
                } else {
                    divider.setBackground(gradient);
                }
            } else
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

        { // Options
//            SlidrConfig config = new SlidrConfig.Builder()
////                .primaryColor(primary)
////                .secondaryColor(secondary)
//                    .position(SlidrPosition.TOP)
//                    .velocityThreshold(2400)
//                    .distanceThreshold(.25f)
//                    .edge(true)
//                    .touchSize(getResources().getDimensionPixelSize(R.dimen.toolbarHeight))
//                    .build();
//
//            SlidrInterface slidrInterface = Slidr.attach(this, config);
//            if (edgeControlTop)
//                slidrInterface.unlock();
//            else
//                slidrInterface.lock();
        }

        { // Content
            webView.loadUrl(url);
//            FinestWebViewFragment fragment = new FinestWebViewFragment();
//            Bundle args = new Bundle();
//            args.putString(FinestWebViewFragment.EXTRA_URL, url);
//            fragment.setArguments(args);
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.content, fragment);
//            transaction.commit();
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
            Bitmap bitmap = getColoredBitmap(drawableRes, iconPressedColor);
            BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
            states.addState(new int[]{android.R.attr.state_pressed}, drawable);
        }
        {
            Bitmap bitmap = getColoredBitmap(drawableRes, iconDisabledColor);
            BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
            states.addState(new int[]{-android.R.attr.state_enabled}, drawable);
        }
        {
            Bitmap bitmap = getColoredBitmap(drawableRes, iconDefaultColor);
            BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
            states.addState(new int[]{}, drawable);
        }
        icon.setImageDrawable(states);
    }

    protected Bitmap getColoredBitmap(@DrawableRes int drawableRes, @ColorInt int color) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableRes);

        int defAlpha = Color.alpha(color);
        int defRed = Color.red(color);
        int defGreen = Color.green(color);
        int defBlue = Color.blue(color);

        int[] pixels = new int[bitmap.getWidth() * bitmap.getHeight()];
        bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        for (int i = 0; i < pixels.length; i++) {
            int pixel = pixels[i];
            int alpha = Color.alpha(pixel);
            if (alpha != 0)
                pixels[i] = Color.argb((int) (alpha * defAlpha / 256f), defRed, defGreen, defBlue);
        }

        Bitmap coloredBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        coloredBitmap.setPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        return coloredBitmap;
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

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        if (scrollState == ScrollState.UP) {
            if (toolbarIsShown()) {
                hideToolbar();
            }
        } else if (scrollState == ScrollState.DOWN) {
            if (toolbarIsHidden()) {
                showToolbar();
            }
        }
    }

    private boolean toolbarIsShown() {
        return ViewHelper.getTranslationY(toolbar) == 0;
    }

    private boolean toolbarIsHidden() {
        return ViewHelper.getTranslationY(toolbar) == -toolbar.getHeight();
    }

    private void showToolbar() {
        moveToolbar(0);
    }

    private void hideToolbar() {
        moveToolbar(-toolbar.getHeight());
    }

    private void moveToolbar(float toTranslationY) {
        if (ViewHelper.getTranslationY(toolbar) == toTranslationY)
            return;

        ValueAnimator animator = ObjectAnimator.ofFloat(ViewHelper.getTranslationY(toolbar), toTranslationY).setDuration(200);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float translationY = (float) animation.getAnimatedValue();
                ViewHelper.setTranslationY(toolbar, translationY);
                ViewHelper.setTranslationY(webView, translationY);
                ViewHelper.setTranslationY(divider, translationY);
                ViewHelper.setTranslationY(progressBar, translationY);
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) webView.getLayoutParams();
                lp.height = (int) -translationY + ScreenHelper.getHeight(FinestWebViewActivity.this) - lp.topMargin;
                webView.requestLayout();
            }
        });
        animator.start();
    }
}