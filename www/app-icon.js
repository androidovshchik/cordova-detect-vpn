module.exports = {
    toggle: function (enable, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "AppIconPlugin", "toggle", [enable]);
    },
    isHidden: function (successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "AppIconPlugin", "isHidden", []);
    }
};
