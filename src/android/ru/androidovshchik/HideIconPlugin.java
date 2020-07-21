package ru.androidovshchik;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

public class HideIconPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) {
        if (Build.VERSION.SDK_INT >= 29) {
            // see https://developer.android.com/reference/android/content/pm/LauncherApps#getActivityList(java.lang.String,%20android.os.UserHandle)
            callbackContext.sendPluginResult(new PluginResult(
                PluginResult.Status.ERROR,
                "As of Android Q (API 29) all app icons will be visible in the launcher no matter what unless special privileges"
            ));
            return true;
        }
        switch (action) {
            case "toggle":
                try {
                    toggleIcon(data.getBoolean(0));
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.NO_RESULT));
                } catch (JSONException e) {
                    e.printStackTrace();
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.JSON_EXCEPTION));
                }
                break;
            case "isHidden":
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, isIconHidden()));
                break;
            default:
                return false;
        }
        return true;
    }

    private void toggleIcon(boolean enable) {
        Context context = cordova.getContext();
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(context.getPackageName());
        packageManager.setComponentEnabledSetting(
            intent.getComponent(),
            enable ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED : PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        );
    }

    private boolean isIconHidden() {
        Context context = cordova.getContext();
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(context.getPackageName());
        int state = packageManager.getComponentEnabledSetting(intent.getComponent());
        return state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
    }
}
