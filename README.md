SimpleWebViewFragment
=====================

Download sample app from https://play.google.com/store/apps/details?id=com.thefinestartist.simplewebviewfragment

There is YouTubePlayerActivity which using YouTubePlayerAPI

HOW TO USE
----------------

Put Youtube_api_key in YouTubePlayerActivity

    public static final String GOOGLE_API_KEY = "AIzaSyAOfxiG4aV66h3XmssCEkP3qCvCq******";

GET Youtube Video id from URL
    
    final String videoId = YouTubePlayerActivity.getYouTubeVideoId("http://www.youtube.com/watch?v=9bZkp7q19f0"); 
        
ADD video id as Extra and Start Activity
    
    Intent intent = new Intent(MainActivity.this, YouTubePlayerActivity.class);
    
    intent.putExtra(YouTubePlayerActivity.EXTRA_VIDEO_ID, videoId);
    
    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
    
    startActivity(intent);

WHAT IT DOES
----------------


* Orientation Problem (with Auto Rotation mode)

    Auto-Rotation ON : You can either use sensor or YouTube full screen button.

    Auto-Rotation OFF : You can just use YouTube full screen button.


* Youtube url Parsing Problem

    Method called parseYoutubeVideoId can make YouTube URL to Video ID.
    
    Get some help from http://androidsnippets.wordpress.com/2012/10/11/how-to-get-extract-video-id-from-an-youtube-url-in-android-java/


* Media Volume Problem

    While watching YouTube Player, users should be able to set media volume!!!
    
    
    
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
