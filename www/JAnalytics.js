var exec = require('cordova/exec')

var PLUGIN_NAME = 'JAnalyticsPlugin'

var JAnalytics = {
  init: function() {
    exec(null, null, PLUGIN_NAME, "init", []);
  },
  /**
   * 是否开启 debug 模式（开启后会打印更多调试信息）。
   *
   * @param {object} params = {'enable': Boolean}
   */
  setDebugMode: function(params) {
    exec(null, null, PLUGIN_NAME, "setDebugMode", [params]);
  },
  /**
   * 开启 crash log 上报（默认关闭）。
   */
  initCrashHandler: function() {
    exec(null, null, PLUGIN_NAME, "initCrashHandler", []);
  },
  /**
   * 关闭 crash log 上报（目前 iOS 无法关闭）。
   */
  stopCrashHandler: function() {
    exec(null, null, PLUGIN_NAME, "stopCrashHandler", []);
  },
  /**
   * @param {object} params = {
   *  'pageName': String  // 页面名称，和 onPageEnd 需要成对调用。
   * }
   */
  onPageStart: function(params) {
    exec(null, null, PLUGIN_NAME, "onPageStart", [params]);
  },
  /**
   * @param {object} params = {
   *  'pageName': String  // 页面名称，和 onPageStart 需要成对调用。
   * }
   */
  onPageEnd: function(params) {
    exec(null, null, PLUGIN_NAME, "onPageEnd", [params]);
  },
  /**
   * 添加计数事件。
   *
   * @param {object} params = {
   *  'eventId': String,  // 事件 id
   *  'extras': object    // Optional. 扩展参数，类似 {'key1': String, 'key2': String}
   * }
   */
  addCountEvent: function(params) {
    exec(null, null, PLUGIN_NAME, "addCountEvent", [params]);
  },
  /**
   * 添加计算事件。
   *
   * @param {object} params = {
   *  'eventId': String,    // 事件 id
   *  'eventValue': Number, // 事件的值
   *  'extras': object      // Optional. 扩展参数，类似 {'key1': 'value1'}
   * }
   */
  addCalculateEvent: function(params) {
    exec(null, null, PLUGIN_NAME, "addCalculateEvent", [params]);
  },
  /**
   * 添加登录事件。
   *
   * @param {object} params = {
   *  'loginMethod': String,     // 登录方式
   *  'isLoginSuccess': Boolean, // 是否登录成功
   *  'extras': object           // Optional. 扩展参数，类似 {'key1': 'value1'}
   * }
   */
  addLoginEvent: function(params) {
    exec(null, null, PLUGIN_NAME, "addLoginEvent", [params]);
  },
  /**
   * 添加注册事件。
   *
   * @param {object} params = {
   *  'registerMethod': String,     // 登录方式
   *  'isRegisterSuccess': Boolean, // 是否登录成功
   *  'extras': object              // Optional. 扩展参数，类似 {'key1': 'value1'}
   * }
   */
  addRegisterEvent: function(params) {
    exec(null, null, PLUGIN_NAME, "addRegisterEvent", [params]);
  },
  /**
   * 添加浏览事件。
   *
   * @param {object} params = {
   *  'browseId': String,       // 浏览内容 id
   *  'browseName': String,     // 内容名称
   *  'browseType': String,     // 内容类型
   *  'browseDuration': Number, // 浏览时长，单位秒
   *  'extras': object          // Optional. 扩展参数，类似 {'key1': 'value1'}
   * }
   */
  addBrowseEvent: function(params) {
    exec(null, null, PLUGIN_NAME, "addBrowseEvent", [params]);
  },
  /**
   * 添加支付事件。
   *
   * @param {object} params = {
   *  'goodsId': String,            // 商品 id
   *  'goodsName': String,          // 商品名称
   *  'price': Number,              // 商品价格
   *  'currency': String,           // 货币类型：'CNY' / 'USD'
   *  'isPurchaseSuccess': Boolean, // 是否支付成功
   *  'goodsType': String,          // 商品类型
   *  'goodsCount': Number,         // 商品数量
   *  'extras': object              // Optional. 扩展参数，类似 {'key1': 'value1'}
   * }
   */
  addPurchaseEvent: function(params) {
    exec(null, null, PLUGIN_NAME, "addPurchaseEvent", [params]);
  },
  /**
   * 动态配置channel，优先级比AndroidManifest里配置的高
   *
   * @param {object} params = {
   *  'channel': String  //希望配置的channel
   * }
   */
  setChannel: function(params) {
    exec(null, null, PLUGIN_NAME, "setChannel", [params]);
  },
  /**
   * 设置统计上报的自动周期，未调用前默认即时上报
   *
   * @param {object} params = {
   *  'channel': Number  //周期，单位秒，最小10秒，最大1天，超出范围会打印调用失败日志。传0表示统计数据即时上报
   * }
   */
  setAnalyticsReportPeriod: function(params) {
    exec(null, null, PLUGIN_NAME, "setAnalyticsReportPeriod", [params]);
  },
  /**
   * 账户维度模型介绍
   *
   * @param {object} params = {
   *  'accountID': String,            // 账号ID
   *  'name': String,                 // 姓名
   *  'creationTime': Number,         // 账号创建时间
   *  'sex': Number,                  // 性别
   *  'paid': Number,                 // 是否付费
   *  'birthdate': Number,            // 出生年月
   *  'phone': String,                // 手机号码
   *  'email': String,                // 电子邮件
   *  'weiboID': String,              // 新浪微博ID
   *  'wechatID': String,             // 微信ID
   *  'qqID': String,                 // QQ ID
   *  'extras': object                // Optional. 扩展参数，类似 {'key1': 'value1'}
   * }
   * successCallback = function(){};
   * errorCallback = function(errorMsg){console.log(errorMsg)};
   */
  identifyAccount: function(params, successCallback, errorCallback) {
    exec(successCallback, errorCallback, PLUGIN_NAME, "identifyAccount", [
      params
    ]);
  }
};

module.exports = JAnalytics
