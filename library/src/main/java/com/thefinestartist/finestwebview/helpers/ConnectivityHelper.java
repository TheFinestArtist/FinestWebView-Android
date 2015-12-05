package com.thefinestartist.finestwebview.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

/**
 * Created by Leonardo on 12/5/15.
 */
public class ConnectivityHelper {

//    public static boolean haveNetworkConnection() {
//        boolean haveConnectedWifi = false;
//        boolean haveConnectedMobile = false;
//
//        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        Network[] netInfo = cm.getAllNetworks();
//
//        for (Network ni : netInfo) {
//            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
//                if (ni.isConnected())
//                    haveConnectedWifi = true;
//            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
//                if (ni.isConnected())
//                    haveConnectedMobile = true;
//        }
//        return haveConnectedWifi || haveConnectedMobile;
//    }
}
