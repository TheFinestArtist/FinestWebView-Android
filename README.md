# FinestWebView
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-FinestWebView-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/2861)
[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-7%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16)
[![License](https://img.shields.io/badge/License-MIT-blue.svg?style=flat)](http://opensource.org/licenses/MIT)

#### Beautiful and customizable Android Activity that shows web pages within an app.

* Builder pattern
* Material design & Pre-made icons
* Webview listeners
* Custom themes & Custom transition animations
* Support collapsing toolbar & contextual actionbar
* SwipeRefreshLayout & Progressbar
* Device rotation
* Gradient divider
* Custom typeface & translation
* Supports Right to Left

## Screenshots
<img src="https://github.com/TheFinestArtist/FinestWebView-Android/blob/master/art/screenshots.png?raw=true" width="888">

### Default theme & Copied to clipboard
<img src="https://github.com/TheFinestArtist/FinestWebView-Android/blob/master/art/first.png?raw=true" width="888">

### Back and forward & More options
<img src="https://github.com/TheFinestArtist/FinestWebView-Android/blob/master/art/second.png?raw=true" width="888">

## FinestWebView Demo Video - YouTube
<a href="https://www.youtube.com/watch?v=7qmAqnspjAM" target="_blank">
  <img alt="Youtube"
       src="https://github.com/TheFinestArtist/FinestWebView-Android/blob/master/art/youtube.png">
</a>


## Sample Project

You can download the latest sample APK from this repo here: [sample-release.apk](https://github.com/TheFinestArtist/FinestWebView-Android/blob/master/sample/sample-release.apk?raw=true)

It's also on Google Play:

<a href="https://play.google.com/store/apps/details?id=com.thefinestartist.finestwebview.sample" target="_blank">
  <img alt="Get it on Google Play"
      src="https://play.google.com/intl/en_us/badges/images/generic/en-play-badge.png" height="60"/>
</a>

## Getting started

#### Gradle Dependency (jcenter)

Easily reference the library in your Android projects using this dependency in your module's `build.gradle` file.

```java
dependencies {
    compile 'com.thefinestartist:finestwebview:1.2.7'
}
```

#### Manifest Settings

FinestWebView is basically and Android activity with webview, toolbar and etc.  
You have to add FinestWebViewActivity in your `AndroidManifest.xml`

```xml
<uses-permission android:name="android.permission.INTERNET" />

<activity
    android:name="com.thefinestartist.finestwebview.FinestWebViewActivity"
    android:configChanges="keyboardHidden|orientation|screenSize"
    android:screenOrientation="sensor"
    android:theme="@style/FinestWebViewTheme.Light" />
```

#### Basic WebView

```java
new FinestWebView.Builder(activity).show(url);
```


## Customization

#### There are 2 ways to customize FinestWebView.

### 1. Using Themes

You can use your own Theme for FinestWebView. If you want to use pre-defined theme, use `android:theme="@style/FinestWebViewTheme"` or `android:theme="@style/FinestWebViewTheme.Fullscreen"`

```xml
<style name="AppTheme.NoActionBar" parent="Theme.AppCompat.Light.NoActionBar">
    <item name="colorPrimary">@color/primary</item>
    <item name="colorPrimaryDark">@color/primary_dark</item>
    <item name="colorAccent">@color/accent</item>
    <item name="android:textColorPrimary">@color/primary_text</item>
    <item name="android:textColorSecondary">@color/secondary_text</item>
    <item name="windowActionModeOverlay">true</item>
</style>
```

```xml
<activity
    android:name="com.thefinestartist.finestwebview.FinestWebViewActivity"
    android:theme="@style/AppTheme.NoActionBar" />
```

### 2. Builder Options

#### Constructors
```java
Builder(@NonNull Activity activity);
// If you use context instead of activity, FinestWebView enter animation won't work
Builder(@NonNull Context context);
```

#### Load data or Show url
```java
load(@StringRes int dataRes);
load(String data);
load(String data, String mimeType, String encoding);

show(@StringRes int urlRes);
show(@NonNull String url);
```

#### WebView Listener Options
```java
setWebViewListener(WebViewListener listener);
addWebViewListener(WebViewListener listener);
removeWebViewListener(WebViewListener listener);
```

#### Right to Left Options
```java
rtl(boolean rtl);
```

#### Theme Options
```java
theme(@StyleRes int theme);
```

#### StatusBar Options
```java
statusBarColor(@ColorInt int color);
statusBarColorRes(@ColorRes int color);
```

#### Toolbar Options
```java
toolbarColor(@ColorInt int color);
toolbarColorRes(@ColorRes int color);
toolbarScrollFlags(@ScrollFlags int flags);
```

#### Icon Options
```java
iconDefaultColor(@ColorInt int color);
iconDefaultColorRes(@ColorRes int color);
iconDisabledColor(@ColorInt int color);
iconDisabledColorRes(@ColorRes int colorRes);
iconPressedColor(@ColorInt int color);
iconPressedColorRes(@ColorRes int colorRes);
iconSelector(@DrawableRes int selectorRes);
```

#### Icon Options
```java
showIconClose(boolean showIconClose);
disableIconClose(boolean disableIconClose);
showIconBack(boolean showIconBack);
disableIconBack(boolean disableIconBack);
showIconForward(boolean showIconForward);
disableIconForward(boolean disableIconForward);
showIconMenu(boolean showIconMenu);
disableIconMenu(boolean disableIconMenu);
```

#### SwipeRefreshLayout Options
```java
showSwipeRefreshLayout(boolean showSwipeRefreshLayout);
swipeRefreshColor(@ColorInt int color);
swipeRefreshColorRes(@ColorRes int colorRes);
swipeRefreshColors(int[] colors);
swipeRefreshColorsRes(@ArrayRes int colorsRes);
```

#### Divider Options
```java
showDivider(boolean showDivider);
gradientDivider(boolean gradientDivider);
dividerColor(@ColorInt int color);
dividerColorRes(@ColorRes int colorRes);
dividerHeight(float height);
dividerHeight(int height);
dividerHeightRes(@DimenRes int height);
```

#### ProgressBar Options
```java
showProgressBar(boolean showProgressBar);
progressBarColor(@ColorInt int color);
progressBarColorRes(@ColorRes int colorRes);
progressBarHeight(float height);
progressBarHeight(int height);
progressBarHeightRes(@DimenRes int height);
progressBarPosition(@NonNull Position position);
```

#### Title Options
```java
titleDefault(@NonNull String title);
titleDefaultRes(@StringRes int stringRes);
updateTitleFromHtml(boolean updateTitleFromHtml);
titleSize(float titleSize);
titleSize(int titleSize);
titleSizeRes(@DimenRes int titleSize);
titleFont(String titleFont);
titleColor(@ColorInt int color);
titleColorRes(@ColorRes int colorRes);
```

#### Url Options
```java
showUrl(boolean showUrl);
urlSize(float urlSize);
urlSize(int urlSize);
urlSizeRes(@DimenRes int urlSize);
urlFont(String urlFont);
urlColor(@ColorInt int color);
urlColorRes(@ColorRes int colorRes);
```

#### Menu Options
```java
menuColor(@ColorInt int color);
menuColorRes(@ColorRes int colorRes);
menuDropShadowColor(@ColorInt int color);
menuDropShadowColorRes(@ColorRes int colorRes);
menuDropShadowSize(float menuDropShadowSize);
menuDropShadowSize(int menuDropShadowSize);
menuDropShadowSizeRes(@DimenRes int menuDropShadowSize);
menuSelector(@DrawableRes int selectorRes);

menuTextSize(float menuTextSize);
menuTextSize(int menuTextSize);
menuTextSizeRes(@DimenRes int menuTextSize);
menuTextFont(String menuTextFont);
menuTextColor(@ColorInt int color);
menuTextColorRes(@ColorRes int colorRes);

menuTextGravity(int gravity);
menuTextPaddingLeft(float menuTextPaddingLeft);
menuTextPaddingLeft(int menuTextPaddingLeft);
menuTextPaddingLeftRes(@DimenRes int menuTextPaddingLeft);
menuTextPaddingRight(float menuTextPaddingRight);
menuTextPaddingRight(int menuTextPaddingRight);
menuTextPaddingRightRes(@DimenRes int menuTextPaddingRight);

showMenuRefresh(boolean showMenuRefresh);
stringResRefresh(@StringRes int stringResRefresh);
showMenuFind(boolean showMenuFind);
stringResFind(@StringRes int stringResFind);
showMenuShareVia(boolean showMenuShareVia);
stringResShareVia(@StringRes int stringResShareVia);
showMenuCopyLink(boolean showMenuCopyLink);
stringResCopyLink(@StringRes int stringResCopyLink);
showMenuOpenWith(boolean showMenuOpenWith);
stringResOpenWith(@StringRes int stringResOpenWith);
```

#### More Options
```java
setCustomAnimations(@AnimRes int animationOpenEnter,
                    @AnimRes int animationOpenExit,
                    @AnimRes int animationCloseEnter,
                    @AnimRes int animationCloseExit)
backPressToClose(boolean backPressToClose);
stringResCopiedToClipboard(@StringRes int stringResCopiedToClipboard);
```

#### WebView Options
```java
webViewSupportZoom(boolean webViewSupportZoom);
webViewMediaPlaybackRequiresUserGesture (boolean webViewMediaPlaybackRequiresUserGesture);
webViewBuiltInZoomControls (boolean webViewBuiltInZoomControls);
webViewDisplayZoomControls (boolean webViewDisplayZoomControls);
webViewAllowFileAccess (boolean webViewAllowFileAccess);
webViewAllowContentAccess (boolean webViewAllowContentAccess);
webViewLoadWithOverviewMode (boolean webViewLoadWithOverviewMode);
webViewSaveFormData (boolean webViewSaveFormData);
webViewTextZoom (int webViewTextZoom);
webViewUseWideViewPort (boolean webViewUseWideViewPort);
webViewSupportMultipleWindows (boolean webViewSupportMultipleWindows);
webViewLayoutAlgorithm (WebSettings.LayoutAlgorithm webViewLayoutAlgorithm);
webViewStandardFontFamily (String webViewStandardFontFamily);
webViewFixedFontFamily (String webViewFixedFontFamily);
webViewSansSerifFontFamily (String webViewSansSerifFontFamily);
webViewSerifFontFamily (String webViewSerifFontFamily);
webViewCursiveFontFamily (String webViewCursiveFontFamily);
webViewFantasyFontFamily (String webViewFantasyFontFamily);
webViewMinimumFontSize (int webViewMinimumFontSize);
webViewMinimumLogicalFontSize (int webViewMinimumLogicalFontSize);
webViewDefaultFontSize (int webViewDefaultFontSize);
webViewDefaultFixedFontSize (int webViewDefaultFixedFontSize);
webViewLoadsImagesAutomatically (boolean webViewLoadsImagesAutomatically);
webViewBlockNetworkImage (boolean webViewBlockNetworkImage);
webViewBlockNetworkLoads (boolean webViewBlockNetworkLoads);
webViewJavaScriptEnabled (boolean webViewJavaScriptEnabled);
webViewAllowUniversalAccessFromFileURLs (boolean webViewAllowUniversalAccessFromFileURLs);
webViewAllowFileAccessFromFileURLs (boolean webViewAllowFileAccessFromFileURLs);
webViewGeolocationDatabasePath (String webViewGeolocationDatabasePath);
webViewAppCacheEnabled (boolean webViewAppCacheEnabled);
webViewAppCachePath (String webViewAppCachePath);
webViewDatabaseEnabled (boolean webViewDatabaseEnabled);
webViewDomStorageEnabled (boolean webViewDomStorageEnabled);
webViewGeolocationEnabled (boolean webViewGeolocationEnabled);
webViewJavaScriptCanOpenWindowsAutomatically (boolean webViewJavaScriptCanOpenWindowsAutomatically);
webViewDefaultTextEncodingName (String webViewDefaultTextEncodingName);
webViewUserAgentString (String webViewUserAgentString);
webViewNeedInitialFocus (boolean webViewNeedInitialFocus);
webViewCacheMode (int webViewCacheMode);
webViewMixedContentMode (int webViewMixedContentMode);
webViewOffscreenPreRaster (boolean webViewOffscreenPreRaster);

injectJavaScript(String injectJavaScript);
```

#### Builder Pattern
```java
new FinestWebView.Builder(activity)
    .titleDefault("Default Title")
    .toolbarScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS)
    .gradientDivider(false)
    .dividerHeight(100)
    .toolbarColorRes(R.color.accent)
    .dividerColorRes(R.color.black_30)
    .iconDefaultColorRes(R.color.accent)
    .iconDisabledColorRes(R.color.gray)
    .iconPressedColorRes(R.color.black)
    .progressBarHeight(DipPixelHelper.getPixel(context, 3))
    .progressBarColorRes(R.color.accent)
    .backPressToClose(false)
    .setCustomAnimations(R.anim.activity_open_enter, R.anim.activity_open_exit, R.anim.activity_close_enter, R.anim.activity_close_exit)
    .show(url);
```


## More customizations

#### WebView Listeners
FinestWebView has its own listeners to listen event from WebView
```java
public void onProgressChanged(int progress);
public void onReceivedTitle(String title);
public void onReceivedTouchIconUrl(String url, boolean precomposed);

public void onPageStarted(String url);
public void onPageFinished(String url);
public void onLoadResource(String url);
public void onPageCommitVisible(String url);

public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength)
```

#### Status Bar Color & Toolbar Color

Status bar color will be set as `colorPrimaryDark` of FinestWebViewActivity's theme.  
Toolbar color will be set as `colorPrimary` of FinestWebViewActivity's theme.  
But, you can override theses settings using builder option `statusBarColor`, `statusBarColorRes`, `toolbarColor`, `toolbarColorRes`.


#### Disable Toolbar Collapsing

```java
new FinestWebView.Builder(activity)
    .toolbarScrollFlags(0) // By sending as 0, toolbar collapsing will be disabled
    .show(url);
```


#### Collapsing Toolbar vs WebView BuiltInZoomControls
If you enable BuiltInZoomControls `webViewBuiltInZoomControls(true)`, it will automatically disable toolbar collapsing.


#### Full Screen Mode

```xml
<style name="AppTheme.NoActionBar.FullScreen" parent="AppTheme.NoActionBar">
    <item name="android:windowContentOverlay">@null</item>
    <item name="android:windowFullscreen">true</item>
</style>
<activity
    android:name="com.thefinestartist.finestwebview.FinestWebViewActivity"
    android:theme="@style/AppTheme.NoActionBar.FullScreen" />
```


#### Customizing Animations

You can use some pre-defined animations from this library or your own animations.

```java
new FinestWebView.Builder(activity)
    .setCustomAnimations(R.anim.activity_open_enter, R.anim.activity_open_exit, R.anim.activity_close_enter, R.anim.activity_close_exit)
    .show(url);
```

Pre-defined animation sets

```java
.setCustomAnimations(R.anim.activity_open_enter, R.anim.activity_open_exit, R.anim.activity_close_enter, R.anim.activity_close_exit)
.setCustomAnimations(R.anim.fragment_open_enter, R.anim.fragment_open_exit, R.anim.fragment_close_enter, R.anim.fragment_close_exit)
.setCustomAnimations(R.anim.slide_up, R.anim.hold, R.anim.hold, R.anim.slide_down)
.setCustomAnimations(R.anim.slide_left_in, R.anim.hold, R.anim.hold, R.anim.slide_right_out)
.setCustomAnimations(R.anim.fade_in_fast, R.anim.fade_out_medium, R.anim.fade_in_medium, R.anim.fade_out_fast)
```


#### Orientation Support

Use configChange, screenOrientation to customize your orientation options

```xml
<activity
    android:name="com.thefinestartist.finestwebview.FinestWebViewActivity"
    android:configChanges="keyboardHidden|orientation|screenSize"
    android:screenOrientation="sensor"
    android:theme="@style/FinestWebViewTheme" />
```

#### Gradient Divider

You can make your divider gradient. If you do, webview will be under the gradient. If you disable gradient divider, webview will be below the divider.

```java
new FinestWebView.Builder(activity)
    .gradientDivider(false)
    .show(url);
```


#### Custom Typeface

You can use your own typeface for title, url, and menus. You have to add your font file in `assets/fonts` folder.

```java
new FinestWebView.Builder(activity)
    .titleFont("Roboto-Medium.ttf")
    .urlFont("Roboto-Regular.ttf")
    .menuTextFont("Roboto-Medium.ttf")
    .show(url);
```


#### Custom Translation

You can use your own String resources to translate strings.

```java
new FinestWebView.Builder(activity)
    .stringResCopiedToClipboard(R.string.copied_to_clipboard)
    .stringResRefresh(R.string.refresh)
    .stringResShareVia(R.string.share_via)
    .stringResCopyLink(R.string.copy_link)
    .stringResOpenWith(R.string.open_with)
    .show(url);
```

#### Right to Left

You can support right to left by setting `android:supportsRtl="true"` in `AndroidManifest.xml` or `rtl(true)`.

```xml
<application
    ...
    android:supportsRtl="true">
</application>
```
```java
new FinestWebView.Builder(activity)
    .rtl(true)
    .show(url);
```

#### WebView Desktop Mode

You can force WebView to show in desktop mode by setting `webViewUserAgentString("Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.4) Gecko/20100101 Firefox/4.0")`. 

## Designer

#### [Min Kim](https://github.com/openyourboxes)

* User Interface Design
* Graphic Design


## License

```
The MIT License (MIT)

Copyright (c) 2013 TheFinestArtist

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
```
