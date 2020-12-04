package ru.androidovshchik;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;

public class DetectVPNPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) {
        switch (action) {
            case "isEnabled":
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, isEnabled()));
                break;
            default:
                return false;
        }
        return true;
    }

    @SuppressLint("MissingPermission")
    private boolean isEnabled() {
        Context context = cordova.getContext();
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network network = cm.getActiveNetwork();
            if (network != null) {
                NetworkCapabilities caps = cm.getNetworkCapabilities(network);
                return caps.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] networks = cm.getAllNetworks();
            for (Network network : networks) {
                NetworkCapabilities caps = cm.getNetworkCapabilities(network);
                if (caps.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
                    return true;
                }
            }
        } else {
            NetworkInfo networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_VPN);
            if (networkInfo != null) {
                return networkInfo.isConnectedOrConnecting();
            }
        }
        return false;
    }
}
