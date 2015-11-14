# FinestWebView

FinestWebView is a Android library which extends Android WebView that has back, forward, refresh and share button in it.

## Gradle Dependency (jcenter)

Easily reference the library in your Android projects using this dependency in your module's `build.gradle` file:

```Gradle
dependencies {
    compile 'com.thefinestartist:finestwebview:1.0.0'
}
```

## Basic WebView

```java
new FinestWebViewActivity.Builder(this)
        .defaultTitle(R.string.title)
        .showUrl(true)
        .show(url);
```

## Options
```java
showStatusBar(boolean) // default: true
statusBarColor(int) // default: white
statusBarColorRes(int) //default: 0

toolBarColor(int) // default: white
toolBarColorRes(int) // default: white

iconDefaultColor(int) // default: black
iconDefaultColorRes(int) // default: 0
iconDisabledColor(int) // default: gray
iconDisabledColorRes(int) // default: 0
iconPressedColor(int) // default: black
iconPressedColorRes(int) // default: 0
iconSelector(int) // default: 0

showProgressBar(boolean) // default: true
progressBarColor(int) // default: ?
progressBarColorRes(int) // default: 0
progressBarHeight(float) // default: 0
progressBarHeight(int) // default: 0
progressBarHeightRes(int) // default: 0
progressBarPosition(Position) // default: Position.BOTTON_OF_TOOLBAR

titleDefault(String) // default: null
titleDefaultRes(int) // default: 0
updateTitleFromHtml(boolean) // default: true
titleSize(float) // default: 0
titleSize(int) // default: 0
titleSizeRes(int) // default: 0
titleFont(String) // default: Roboto-Regular.ttf
titleColor(int) // default: black
titleColorRes(int) // default: 0

showUrl(boolean) // default: true
urlSize(float) // default: 0
urlSize(int) // default: 0
urlSizeRes(int) // default: 0
urlFont(String) // default: Roboto-Regular.ttf
urlColor(int) // default: black
urlColorRes(int) // default: 0

enterAnimation(int) // default: R.anim.modal_activity_close_enter
exitAnimation(int) // default: R.anim.modal_activity_close_exit

showRefresh(boolean) // default: false
backPressToClose(boolean) // default: false

edgeControlSide(boolean) // default: true
edgeControlTop(boolean) // default: true
```



https://github.com/liuguangqiang/SwipeBack

https://github.com/r0adkll/Slidr

https://github.com/ppamorim/Dragger

Share via

Open with

기본 Activity 보여주고 없애는 Animation

## User Interface Design & Graphic Design

### Min Kim

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