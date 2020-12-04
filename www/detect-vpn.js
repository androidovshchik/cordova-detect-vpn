module.exports = {
    isEnabled: function (successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "DetectVPNPlugin", "isEnabled", []);
    }
};
