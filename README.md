# cordova-vpn-detect plugin

Simple plugin that allows to detect VPN connection on device

## Getting started

Create a new Cordova Project

    $ cordova create example com.example Example
    
Install the plugin

    $ cd example
    $ cordova plugin add https://github.com/androidovshchik/cordova-vpn-detect.git
    

Edit `www/js/index.js` and add the following code inside `onDeviceReady`

```js
DetectVPN.isEnabled(function(result) {
    if (result) {
        // there was found VPN connection
    }
});
```

Install Android platform

    cordova platform add android
    
Run the app

    cordova run android