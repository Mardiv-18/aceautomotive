package in.co.aceautomotive;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.ContextThemeWrapper;

public class InternetConnection {



    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        @SuppressWarnings("deprecation")
        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            return true;
        }

        @SuppressWarnings("deprecation")
        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return true;
        }

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        }

        return false;

    }


    public static void showAlert(String msg, Context context) {

        ContextThemeWrapper themedContext;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            themedContext = new ContextThemeWrapper(context,android.R.style.Theme_Holo_Light_Dialog_NoActionBar);
        } else {
            themedContext = new ContextThemeWrapper(context,android.R.style.Theme_Light_NoTitleBar);
        }

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(themedContext);

        alertDialogBuilder.setTitle(context.getResources().getString(R.string.app_name));
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setMessage(msg).setPositiveButton("Reload",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Call webview again.
                        dialog.cancel();

                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
}
