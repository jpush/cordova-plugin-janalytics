package cn.jiguang.cordova.analytics;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

import cn.jiguang.analytics.android.api.BrowseEvent;
import cn.jiguang.analytics.android.api.CalculateEvent;
import cn.jiguang.analytics.android.api.CountEvent;
import cn.jiguang.analytics.android.api.Currency;
import cn.jiguang.analytics.android.api.JAnalyticsInterface;
import cn.jiguang.analytics.android.api.LoginEvent;
import cn.jiguang.analytics.android.api.PurchaseEvent;
import cn.jiguang.analytics.android.api.RegisterEvent;

/**
 * This class echoes a string called from JavaScript.
 */
public class JAnalytics extends CordovaPlugin {

    @Override
    protected void pluginInitialize() {
        JAnalyticsInterface.init(this.cordova.getActivity().getApplication());
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        Class<? extends JAnalytics> that = this.getClass();
        Method method = null;
        try {
            method = that.getDeclaredMethod(action, JSONArray.class, CallbackContext.class);
            method.setAccessible(true);
            method.invoke(this, args, callbackContext);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            callbackContext.error(e.toString());
            return false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            callbackContext.error(e.toString());
            return false;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            callbackContext.error(e.toString());
            return false;
        }
        return true;
    }

    /**
     * 设置是否开启debug模式。
     */
    private void setDebugModel(JSONArray args, CallbackContext callbackContext) throws JSONException{
        boolean enable = args.getBoolean(0);
        JAnalyticsInterface.setDebugMode(enable);
        callbackContext.success();
    }

    /**
     * 页面启动接口
     */
    private void onPageStart(JSONArray args, CallbackContext callbackContext) throws JSONException{
        String pageName = args.getString(0);
        JAnalyticsInterface.onPageStart(this.cordova.getActivity(), pageName);
        callbackContext.success();
    }

    /**
     * 页面启动接口
     */
    private void onPageEnd(JSONArray args, CallbackContext callbackContext) throws JSONException{
        String pageName = args.getString(0);
        JAnalyticsInterface.onPageEnd(this.cordova.getActivity(), pageName);
        callbackContext.success();
    }

    /**
     * 计数事件
     * 字符串字段（key与 value）限制大小不超过256字节，超过限制的key或value该事件将会被丢弃.
     * 自定义键值对数目不能超过10个，超过10个限制该事件将会被丢弃.
     *
     * 自定义计数事件模型中扩展参数中不能使用以下 key 值：
     * event_id
     * 此类 key 已被模型使用，如果使用则会导致统计到的数据不准确.
     */
    private void onCountEvent(JSONArray args, CallbackContext callbackContext) throws JSONException {
        String eventId = args.getString(0);
        JSONObject extMap = args.getJSONObject(1);

        CountEvent event = new CountEvent(eventId);
        for(Iterator<String> it = extMap.keys();it.hasNext();){
            String key = it.next();
            String value = extMap.getString(key);
            event.addKeyValue(key, value);
        }
        JAnalyticsInterface.onEvent(this.cordova.getActivity(), event);
        callbackContext.success();
    }

    /**
     * 计算事件
     * 自定义计算事件模型中扩展参数中不能使用以下 key 值：
     * event_id
     * event_value
     * 此类 key 已被模型使用，如果使用则会导致统计到的数据不准确.
     */
    private void onCalculateEvent(JSONArray args, CallbackContext callbackContext) throws JSONException {
        String eventId = args.getString(0);
        double eventValue = args.getDouble(1);
        JSONObject extMap = args.getJSONObject(2);

        CalculateEvent event = new CalculateEvent(eventId, eventValue);
        for(Iterator<String> it = extMap.keys();it.hasNext();){
            String key = it.next();
            String value = extMap.getString(key);
            event.addKeyValue(key, value);
        }
        JAnalyticsInterface.onEvent(this.cordova.getActivity(), event);
        callbackContext.success();
    }

    /**
     * 登陆事件
     * 登录事件模型中扩展参数中不能使用以下 key 值：
     * login_method
     * login_success
     * 此类 key 已被模型使用，如果使用则会导致统计到的数据不准确.
     */
    private void onLoginEvent(JSONArray args, CallbackContext callbackContext) throws JSONException {
        String loginMethod = args.getString(0);
        boolean loginSuccess = args.getBoolean(1);
        JSONObject extMap = args.getJSONObject(2);

        LoginEvent event = new LoginEvent(loginMethod, loginSuccess);
        for(Iterator<String> it = extMap.keys();it.hasNext();){
            String key = it.next();
            String value = extMap.getString(key);
            event.addKeyValue(key, value);
        }
        JAnalyticsInterface.onEvent(this.cordova.getActivity(), event);
        callbackContext.success();
    }

    /**
     * 注册事件
     * 注册事件模型中扩展参数中不能使用以下 key 值:
     * register_method
     * register_success
     * 此类 key 已被模型使用，如果使用则会导致统计到的数据不准确.
     */
    private void onRegisterEvent(JSONArray args, CallbackContext callbackContext) throws JSONException {
        String registerMethod = args.getString(0);
        boolean registerSuccess = args.getBoolean(1);
        JSONObject extMap = args.getJSONObject(2);

        RegisterEvent event = new RegisterEvent(registerMethod, registerSuccess);
        for(Iterator<String> it = extMap.keys();it.hasNext();){
            String key = it.next();
            String value = extMap.getString(key);
            event.addKeyValue(key, value);
        }
        JAnalyticsInterface.onEvent(this.cordova.getActivity(), event);
        callbackContext.success();
    }

    /**
     * 浏览事件
     * 浏览事件模型中扩展参数中不能使用以下 key 值：
     * browse_content_id
     * browse_name
     * browse_type
     * browse_duration
     * 此类 key 已被模型使用，如果使用则会导致统计到的数据不准确.
     */
    private void onBrowseEvent(JSONArray args, CallbackContext callbackContext) throws JSONException {
        String browseId = args.getString(0);
        String browseName = args.getString(1);
        String browseType = args.getString(2);
        long browseDuration = args.getLong(3);
        JSONObject extMap = args.getJSONObject(4);

        BrowseEvent event = new BrowseEvent(browseId, browseName, browseType, browseDuration);
        for(Iterator<String> it = extMap.keys();it.hasNext();){
            String key = it.next();
            String value = extMap.getString(key);
            event.addKeyValue(key, value);
        }
        JAnalyticsInterface.onEvent(this.cordova.getActivity(), event);
        callbackContext.success();
    }

    /**
     * 购买事件
     * 购买事件模型中扩展参数中不能使用以下 key 值：
     * purchase_goods_id
     * purchase_goods_name
     * purchase_price
     * purchase_currency
     * purchase_goods_type
     * purchase_quantity
     * 此类 key 已被模型使用，如果使用则会导致统计到的数据不准确.
     */
    private void onPurchaseEvent(JSONArray args, CallbackContext callbackContext) throws JSONException {
        String purchaseGoodsid = args.getString(0);
        String purchaseGoodsName = args.getString(1);
        double purchasePrice = args.getDouble(2);
        boolean purchaseSuccess = args.getBoolean(3);
        Currency purchaseCurrency = args.getString(4).equals("USD") ? Currency.USD : Currency.CNY;
        String purchaseGoodsType = args.getString(5);
        int purchaseGoodsCount = args.getInt(6);
        JSONObject extMap = args.getJSONObject(7);

        PurchaseEvent event = new PurchaseEvent(purchaseGoodsid, purchaseGoodsName, purchasePrice,
          purchaseSuccess, purchaseCurrency, purchaseGoodsType, purchaseGoodsCount);

        for(Iterator<String> it = extMap.keys(); it.hasNext();){
            String key = it.next();
            String value = extMap.getString(key);
            event.addKeyValue(key, value);
        }
        JAnalyticsInterface.onEvent(this.cordova.getActivity(), event);
        callbackContext.success();
    }
}
