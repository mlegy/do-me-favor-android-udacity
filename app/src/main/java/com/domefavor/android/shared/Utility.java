package com.domefavor.android.shared;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.domefavor.android.App;

/**
 * Created by ahmad on 4/30/17.
 */

public class Utility {

    public static boolean isConnectingToInternet() {
        ConnectivityManager cm =
                (ConnectivityManager) App.getApp().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
