module.exports = {
    toggleIcon: function (enable, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "cordova-hide-icon", "toggleIcon", [enable]);
    },
    isIconHidden: function (successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "cordova-hide-icon", "isIconHidden", []);
    }
};
