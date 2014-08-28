SimpleWebViewFragment
=====================

Download sample app from https://play.google.com/store/apps/details?id=com.thefinestartist.simplewebviewfragment

There is YouTubePlayerActivity which using YouTubePlayerAPI

HOW TO USE
----------------

Add url as Argument in SimpleWebViewFragment and commit fragment
    
    String url = "http://beautifulsonglyrics.blogspot.com/2013/04/secret-hyosung-yes-underwear-pics.html#.UV5sraspbfU";
    
    Fragment frag = new SimpleWebViewFragment();
    Bundle bundle = new Bundle();
    bundle.putString(SimpleWebViewFragment.EXTRA_URL, url);
    frag.setArguments(bundle);
                
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.add(R.id.content, frag);
    ft.addToBackStack(SimpleWebViewFragment.class.getSimpleName());
    ft.commit();
        
You Need To Add!!!
    
    Drawables (includes png files and xml files)
    Dimens (for 320dp, 360dp, 400dp)
    Layout (web_view.xml)
    
    

WHAT IT DOES
----------------


* WebView Back & Previous Event

* WebView Refresh Event

* WebView Loading Progress Bar

* Show Url in other Web Browser App    
    
    
Developer
----------------
THE FINEST ARTIST

Website : http://www.TheFinestArtist.com

Email : contact@TheFinestArtist.com



Screen Shot
----------------
<img src=https://github.com/TheFinestArtist/SimpleWebViewFragment/blob/master/res/drawable-xhdpi/screenshot_1.png?raw=true width=500px>


<img src=https://github.com/TheFinestArtist/SimpleWebViewFragment/blob/master/res/drawable-xhdpi/screenshot_2.png?raw=true width=500px>


<img src=https://github.com/TheFinestArtist/SimpleWebViewFragment/blob/master/res/drawable-xhdpi/screenshot_3.png?raw=true width=500px>

## License

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
