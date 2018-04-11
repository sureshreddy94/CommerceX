package com.kryptoblocks.commercex.payment;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by suresh on 13-03-2018.
 */

public class Utility {

    public boolean checkInternetConnection(Context contextValue) {

        Context context = contextValue;
        ConnectivityManager connectivity = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);


// ARE WE CONNECTED TO THE NET
        return connectivity.getActiveNetworkInfo() != null
                && connectivity.getActiveNetworkInfo().isAvailable()
                && connectivity.getActiveNetworkInfo().isConnected();
    }


}
