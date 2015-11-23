# FinestWebView

#### FinestWebView is easy to use WebView that includes back, forward, refresh and share features

* Builder pattern
* Various options
* Material design
* Pre-made icons


## Gradle Dependency (jcenter)

Easily reference the library in your Android projects using this dependency in your module's `build.gradle` file:

```java
dependencies {
    compile 'com.thefinestartist:finestwebview:1.0.0'
}
```


## Manifest Settings

FinestWebView is basically and Android activity with webview, toolbar and etc. You have to add FinestWebViewActivity in your `AndroidManifest.xml` 

```xml
<activity
        android:name="com.thefinestartist.finestwebview.FinestWebViewActivity"
        android:configChanges="keyboardHidden|orientation|screenSize"
        android:screenOrientation="sensor"
        android:theme="@style/FinestWebViewTheme" />
```


## Basic WebView
 
```java
new FinestWebViewActivity.Builder(this)
        .defaultTitle(R.string.title)
        .showUrl(true)
        .show(url);
overridePendingTransition(R.anim.modal_activity_open_enter, R.anim.modal_activity_open_exit);
```


## Custom WebView
 
```java
new FinestWebView.Builder(this)
        .titleDefault("Default Title")
        .toolbarScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS)
        .gradientDivider(false)
        .dividerHeight(100)
        .toolbarColorRes(R.color.accent)
        .dividerColorRes(R.color.black_30)
        .iconDefaultColorRes(R.color.accent)
        .iconDisabledColorRes(R.color.gray)
        .iconPressedColorRes(R.color.black)
        .progressBarHeight(DipPixelHelper.getPixel(this, 3))
        .progressBarColorRes(R.color.accent)
        .backPressToClose(false)
        .show(url);
overridePendingTransition(R.anim.modal_activity_open_enter, R.anim.modal_activity_open_exit);
```

## More Options

```java
// Toolbar Options
toolbarColor(@ColorInt int color)
toolbarScrollFlags(@ScrollFlags int flags)
toolbarColorRes(@ColorRes int color)

// Icon Options
iconDefaultColor(@ColorInt int color)
iconDefaultColorRes(@ColorRes int color)
iconDisabledColor(@ColorInt int color)
iconDisabledColorRes(@ColorRes int colorRes)
iconPressedColor(@ColorInt int color)
iconPressedColorRes(@ColorRes int colorRes)
iconSelector(@DrawableRes int selectorRes)

// Divider Options
showDivider(boolean showDivider)
gradientDivider(boolean gradientDivider)
dividerColor(@ColorInt int color)
dividerColorRes(@ColorRes int colorRes)
dividerHeight(float height)
dividerHeight(int height)
dividerHeightRes(@DimenRes int height)

// ProgressBar Options
showProgressBar(boolean showProgressBar)
progressBarColor(@ColorInt int color)
progressBarColorRes(@ColorRes int colorRes)
progressBarHeight(float height)
progressBarHeight(int height)
progressBarHeightRes(@DimenRes int height)
progressBarPosition(@NonNull Position position)

// Title Options
titleDefault(@NonNull String title)
titleDefaultRes(@StringRes int stringRes)
updateTitleFromHtml(boolean updateTitleFromHtml)
titleSize(float titleSize)
titleSize(int titleSize)
titleSizeRes(@DimenRes int titleSize)
titleFont(String titleFont)
titleColor(@ColorInt int color)
titleColorRes(@ColorRes int colorRes)

// Url Options
showUrl(boolean showUrl)
urlSize(float urlSize)
urlSize(int urlSize)
urlSizeRes(@DimenRes int urlSize)
urlFont(String urlFont)
urlColor(@ColorInt int color)
urlColorRes(@ColorRes int colorRes)

// More Options
setCloseAnimations(@AnimRes int animationCloseEnter, @AnimRes int animationCloseExit)
showRefresh(boolean showRefresh)
backPressToClose(boolean backPressToClose) 
```

```xml
<style name="AppTheme.NoActionBar" parent="Theme.AppCompat.Light.NoActionBar">
        <item name="colorPrimary">@color/primary</item>
        <item name="colorPrimaryDark">@color/primary_dark</item>
        <item name="colorAccent">@color/accent</item>
        <item name="android:textColorPrimary">@color/primary_text</item>
        <item name="android:textColorSecondary">@color/secondary_text</item>
        <item name="android:windowContentOverlay">@null</item>
        <item name="android:windowFullscreen">true</item>
</style>
```

## Contributors

#### Min Kim

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