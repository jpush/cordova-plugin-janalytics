var exec = require('cordova/exec');

function isPlatformIOS() {
    var isPlatformIOS = device.platform == 'iPhone' ||
        device.platform == 'iPad' ||
        device.platform == 'iPod touch' ||
        device.platform == 'iOS'
    return isPlatformIOS
}

/**
 * 设置是否开启debug模式。
 */
exports.setDebugModel = function(enable, success, error) {
    enable = !!enable;
    exec(success, error, "JAnalytics", "setDebugModel", [enable]);
};

/**
 * 页面启动接口
 */
exports.onPageStart = function(pageName, success, error) {
    pageName = pageName || "";
    exec(success, error, "JAnalytics", "onPageStart", [pageName]);
};

/**
 * 页面启动接口
 */
exports.onPageEnd = function(pageName, success, error) {
    pageName = pageName || "";
    exec(success, error, "JAnalytics", "onPageEnd", [pageName]);
};

/**
 * 计数事件
 */
exports.onCountEvent = function(eventId, extMap, success, error) {
    eventId = eventId || "";
    extMap = extMap || {};
    exec(success, error, "JAnalytics", "onCountEvent", [eventId, extMap]);
};

/**
 * 计算事件
 */
exports.onCalculateEvent = function(eventId, eventValue, extMap, success, error) {
    eventId = eventId || "";
    eventValue = eventValue || 0;
    extMap = extMap || {};
    exec(success, error, "JAnalytics", "onCalculateEvent", [eventId, eventValue, extMap]);
};

/**
 * 登陆事件
 */
exports.onLoginEvent = function(loginMethod, loginSuccess, extMap, success, error) {
    loginMethod = loginMethod || "";
    loginSuccess = !!loginSuccess;
    extMap = extMap || {};
    exec(success, error, "JAnalytics", "onLoginEvent", [loginMethod, loginSuccess, extMap]);
};

/**
 * 注册事件
 */
exports.onRegisterEvent = function(registerMethod, registerSuccess, extMap, success, error) {
    registerMethod = registerMethod || "";
    registerSuccess = !!registerSuccess;
    extMap = extMap || {};
    exec(success, error, "JAnalytics", "onRegisterEvent", [registerMethod, registerSuccess, extMap]);
};

/**
 * 浏览事件
 */
exports.onBrowseEvent = function(
    browseId,
    browseName,
    browseType,
    browseDuration,
    extMap,
    success,
    error
) {
    exec(success, error, "JAnalytics", "onBrowseEvent", [
        browseId || "",
        browseName || "",
        browseType || "",
        browseDuration || 0,
        extMap || {}
    ]);
};

/**
 * 购买事件
 */
exports.onPurchaseEvent = function(
    purchaseGoodsid,
    purchaseGoodsName,
    purchasePrice,
    purchaseSuccess,
    purchaseCurrency,
    purchaseGoodsType,
    purchaseGoodsCount,
    extMap,
    success, error
) {
    exec(success, error, "JAnalytics", "onPurchaseEvent", [
        purchaseGoodsid || "",
        purchaseGoodsName || "",
        purchasePrice || 0,
        !!purchaseSuccess,
        purchaseCurrency || "CNY",
        purchaseGoodsType || "",
        purchaseGoodsCount || 0,
        extMap || {},
    ]);
};

/**
 * 上报地理位置
 * 仅对IOS有效
 */
exports.setLocation = function(latitude, longitude, success, error) {
    if (isPlatformIOS()) {
        exec(success, error, "JAnalytics", "setLocation", [latitude || 0, longitude || 0]);
    }
};

/**
 * 开启崩溃收集，默认是关闭的
 * 仅对IOS有效
 */
exports.crashLogON = function(success, error) {
    if (isPlatformIOS()) {
        exec(success, error, "JAnalytics", "crashLogON", []);
    }
};