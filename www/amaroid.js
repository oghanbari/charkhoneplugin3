var exec = require('cordova/exec');

exports.setTags = function (arg0, success, error) {
    exec(success, error, 'AmaroidBridge', 'setTags', [arg0]);
};

exports.trackView = function (arg0, success, error) {
    exec(success, error, 'AmaroidBridge', 'trackView', [arg0]);
};

exports.trackEvent = function (arg0, success, error) {
    exec(success, error, 'AmaroidBridge', 'trackEvent', [arg0]);
};
