var exec = require('cordova/exec')

var PLUGIN_NAME = 'JAnalyticsPlugin'

var JAnalytics = {
  init: function () {
    exec(null, null, PLUGIN_NAME, 'init', [])
  },
  /**
   * 是否开启 debug 模式（开启后会打印更多调试信息）。
   *
   * @param {object} params = {'enable': Boolean}
   * @param {function} success = function () {} // 成功回调
   * @param {function} error = function () {}   // 失败回调
   */
  setDebugMode: function (params) {
    exec(null, null, PLUGIN_NAME, 'setDebugMode', [params])
  },
  /**
   * 开启 crash log 上报。
   */
  initCrashHandler: function () {
    exec(null, null, PLUGIN_NAME, 'initCrashHandler', [])
  },
  /**
   * 关闭 crash log 上报。
   */
  stopCrashHandler: function () {
    exec(null, null, PLUGIN_NAME, 'stopCrashHandler', [])
  },
  /**
   * @param {object} params = {
   *  'pageName': String  // 页面名称，和 onPageEnd 需要成对调用。
   * }
   */
  onPageStart: function (params) {
    exec(null, null, PLUGIN_NAME, 'onPageStart', [params])
  },
  /**
   * @param {object} params = {
   *  'pageName': String  // 页面名称，和 onPageStart 需要成对调用。
   * }
   */
  onPageEnd: function (params) {
    exec(null, null, PLUGIN_NAME, 'onPageEnd', [params])
  },
  /**
   * 添加计数事件。
   *
   * @param {object} params = {
   *  'eventId': String,  // 事件 id
   *  'extra': Object    // Optional. 扩展参数，类似 {'key1': String, 'key2': String}
   * }
   */
  addCountEvent: function (params) {
    exec(null, null, PLUGIN_NAME, 'addCountEvent', [params])
  },
  /**
   * 添加计算事件。
   * 
   * @param {object} params = {
   *  'eventId': String,    // 事件 id
   *  'eventValue': Number, // 事件的值
   *  'extra': Object      // Optional. 扩展参数，类似 {'key1': 'value1'}
   * }
   */
  addCalculateEvent: function (params) {
    exec(null, null, PLUGIN_NAME, 'addCalculateEvent', [params])
  },
  /**
   * 添加登录事件。
   * 
   * @param {object} params = {
   *  'loginMethod': String,     // 登录方式
   *  'isLoginSuccess': Boolean, // 是否登录成功
   *  'extra': Object           // Optional. 扩展参数，类似 {'key1': 'value1'}
   * }
   */
  addLoginEvent: function (params) {
    exec(null, null, PLUGIN_NAME, 'addLoginEvent', [params])
  },
  /**
   * 添加注册事件。
   * 
   * @param {object} params = {
   *  'registerMethod': String,     // 登录方式
   *  'isRegisterSuccess': Boolean, // 是否登录成功
   *  'extra': Object              // Optional. 扩展参数，类似 {'key1': 'value1'}
   * }
   */
  addRegisterEvent: function (params) {
    exec(null, null, PLUGIN_NAME, 'addRegisterEvent', [params])
  },
  /**
   * 添加浏览事件。
   * 
   * @param {object} params = {
   *  'browseId': String,       // 浏览内容 id
   *  'browseName': String,     // 内容名称
   *  'browseType': String,     // 内容类型
   *  'browseDuration': Number, // 浏览时长，单位秒
   *  'extra': Object          // Optional. 扩展参数，类似 {'key1': 'value1'}
   * }
   */
  addBrowseEvent: function (params) {
    exec(null, null, PLUGIN_NAME, 'addBrowseEvent', [params])
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
   *  'extra': Object              // Optional. 扩展参数，类似 {'key1': 'value1'}
   * }
   */
  addPurchaseEvent: function (params) {
    exec(null, null, PLUGIN_NAME, 'addPurchaseEvent', [params])
  }
}

module.exports = JAnalytics
