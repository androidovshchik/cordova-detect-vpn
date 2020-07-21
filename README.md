# cordova-app-icon plugin

Simple plugin that allows to control the visibility of the app icon

> As of Android Q (API 29) all app icons will be visible in the launcher no matter what unless [special privileges](https://developer.android.com/reference/android/content/pm/LauncherApps#getActivityList(java.lang.String,%20android.os.UserHandle))

## Using

Create a new Cordova Project

    $ cordova create example com.example Example
    
Install the plugin

    $ cd example
    $ cordova plugin add https://github.com/androidovshchik/cordova-hide-icon.git
    

Edit `www/js/index.js` and add the following code inside `onDeviceReady`

```js
// hide icon
AppIcon.toggle(false);
// check if icon is hidden
AppIcon.isHidden(function(result) {
    if (result) {
        // currently icon is hidden    
    }
});
```

Install Android platform

    cordova platform add android
    
Run the code

    cordova run 

## More Info

For more information on setting up Cordova see [the documentation](http://cordova.apache.org/docs/en/latest/guide/cli/index.html)

For more info on plugins see the [Plugin Development Guide](http://cordova.apache.org/docs/en/latest/guide/hybrid/plugins/index.html)
