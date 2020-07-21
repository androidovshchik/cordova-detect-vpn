module.exports = {
    toggle: function (enable, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "cordova-hide-icon", "toggle", [enable]);
    },
    isHidden: function (successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "cordova-hide-icon", "isHidden", []);
    }
};
