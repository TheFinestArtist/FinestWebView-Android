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

Facebook : www.facebook.com/TheFinestArtist

Email : liger745547@gmail.com



Screen Shot
----------------
<img src=https://github.com/TheFinestArtist/SimpleWebViewFragment/blob/master/res/drawable-xhdpi/screenshot_1.png?raw=true width=500px>


<img src=https://github.com/TheFinestArtist/SimpleWebViewFragment/blob/master/res/drawable-xhdpi/screenshot_2.png?raw=true width=500px>


<img src=https://github.com/TheFinestArtist/SimpleWebViewFragment/blob/master/res/drawable-xhdpi/screenshot_3.png?raw=true width=500px>

## License

    Copyright 2013 The Finest Artist

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
