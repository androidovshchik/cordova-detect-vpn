module.exports = {
    toggle: function (enable, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "cordova-app-icon", "toggle", [enable]);
    },
    isHidden: function (successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "cordova-app-icon", "isHidden", []);
    }
};
