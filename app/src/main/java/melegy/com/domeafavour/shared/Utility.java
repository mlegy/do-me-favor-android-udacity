package melegy.com.domeafavour.shared;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import melegy.com.domeafavour.App;

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
