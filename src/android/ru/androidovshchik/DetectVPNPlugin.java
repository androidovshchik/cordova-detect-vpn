package ru.androidovshchik;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
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

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;

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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("MissingPermission")
    private boolean isEnabled() {
        Context context = cordova.getContext();
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network network = cm.getActiveNetwork();
            if (network != null) {
                NetworkCapabilities caps = cm.getNetworkCapabilities(network);
                if (caps != null) {
                    return caps.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
                }
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] networks = cm.getAllNetworks();
            for (Network network : networks) {
                NetworkCapabilities caps = cm.getNetworkCapabilities(network);
                if (caps != null && caps.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
                    return true;
                }
            }
        } else {
            NetworkInfo networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_VPN);
            if (networkInfo != null) {
                return networkInfo.isConnectedOrConnecting();
            }
        }
        // last attempt
        try {
            List<NetworkInterface> networks = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface networkInterface : networks) {
                if (networkInterface.isUp()) {
                    String iFace = networkInterface.getName();
                    if (iFace != null) {
                        if (iFace.contains("tun") || iFace.contains("ppp") || iFace.contains("pptp")) {
                            return true;
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return false;
    }
}
