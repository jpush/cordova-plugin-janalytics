package cn.jiguang.cordova.analytics;

import android.content.Context;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.jiguang.analytics.android.api.Account;
import cn.jiguang.analytics.android.api.AccountCallback;
import cn.jiguang.analytics.android.api.BrowseEvent;
import cn.jiguang.analytics.android.api.CalculateEvent;
import cn.jiguang.analytics.android.api.CountEvent;
import cn.jiguang.analytics.android.api.Currency;
import cn.jiguang.analytics.android.api.JAnalyticsInterface;
import cn.jiguang.analytics.android.api.LoginEvent;
import cn.jiguang.analytics.android.api.PurchaseEvent;
import cn.jiguang.analytics.android.api.RegisterEvent;

public class JAnalyticsPlugin extends CordovaPlugin {

    private ExecutorService threadPool = Executors.newFixedThreadPool(1);

    private Context mContext;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        mContext = cordova.getActivity().getApplicationContext();
    }

    @Override
    public boolean execute(final String action, final JSONArray data, final CallbackContext callback)
            throws JSONException {
        threadPool.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    Method method = JAnalyticsPlugin.class.getDeclaredMethod(action, JSONArray.class,
                            CallbackContext.class);
                    method.invoke(JAnalyticsPlugin.this, data, callback);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        return true;
    }

    void init(JSONArray data, CallbackContext callback) {
        JAnalyticsInterface.init(mContext);
    }

    void setDebugMode(JSONArray data, CallbackContext callback) throws JSONException {
        JSONObject params = data.getJSONObject(0);
        boolean enable = params.getBoolean("enable");
        JAnalyticsInterface.setDebugMode(enable);
    }

    void initCrashHandler(JSONArray data, CallbackContext callback) {
        JAnalyticsInterface.initCrashHandler(mContext);
    }

    void stopCrashHandler(JSONArray data, CallbackContext callback) {
        JAnalyticsInterface.stopCrashHandler(mContext);
    }

    void setChannel(JSONArray data, CallbackContext callback) throws JSONException {
        JSONObject params = data.getJSONObject(0);
        String channel = params.getString("channel");
        JAnalyticsInterface.setChannel(mContext, channel);
    }

    void onPageStart(JSONArray data, CallbackContext callback) throws JSONException {
        JSONObject params = data.getJSONObject(0);
        String pageName = params.getString("pageName");
        JAnalyticsInterface.onPageStart(mContext, pageName);
    }

    void onPageEnd(JSONArray data, CallbackContext callback) throws JSONException {
        JSONObject params = data.getJSONObject(0);
        String pageName = params.getString("pageName");
        JAnalyticsInterface.onPageEnd(mContext, pageName);
    }

    void addCountEvent(JSONArray data, CallbackContext callback) throws JSONException {
        JSONObject params = data.getJSONObject(0);
        String eventId = params.getString("eventId");
        JSONObject extras = params.has("extras") ? params.getJSONObject("extras") : null;

        CountEvent event = new CountEvent(eventId);
        if (extras != null) {
            event.addExtMap(toMap(extras));
        }
        JAnalyticsInterface.onEvent(mContext, event);
    }

    void addCalculateEvent(JSONArray data, CallbackContext callback) throws JSONException {
        JSONObject params = data.getJSONObject(0);
        String eventId = params.getString("eventId");
        double eventValue = params.getDouble("eventValue");
        JSONObject extras = params.has("extras") ? params.getJSONObject("extras") : null;

        CalculateEvent event = new CalculateEvent(eventId, eventValue);
        if (extras != null) {
            event.addExtMap(toMap(extras));
        }
        JAnalyticsInterface.onEvent(mContext, event);
    }

    void addLoginEvent(JSONArray data, CallbackContext callback) throws JSONException {
        JSONObject params = data.getJSONObject(0);
        String loginMethod = params.getString("loginMethod");
        boolean isLoginSuccess = params.getBoolean("isLoginSuccess");
        JSONObject extras = params.has("extras") ? params.getJSONObject("extras") : null;

        LoginEvent event = new LoginEvent(loginMethod, isLoginSuccess);
        if (extras != null) {
            event.addExtMap(toMap(extras));
        }
        JAnalyticsInterface.onEvent(mContext, event);
    }

    void addRegisterEvent(JSONArray data, CallbackContext callback) throws JSONException {
        JSONObject params = data.getJSONObject(0);
        String registerMethod = params.getString("registerMethod");
        boolean isRegisterSuccess = params.getBoolean("isRegisterSuccess");
        JSONObject extras = params.has("extras") ? params.getJSONObject("extras") : null;

        RegisterEvent event = new RegisterEvent(registerMethod, isRegisterSuccess);
        if (extras != null) {
            event.addExtMap(toMap(extras));
        }
        JAnalyticsInterface.onEvent(mContext, event);
    }

    void addBrowseEvent(JSONArray data, CallbackContext callback) throws JSONException {
        JSONObject params = data.getJSONObject(0);
        String browseId = params.getString("browseId");
        String browseName = params.getString("browseName");
        String browseType = params.getString("browseType");
        long browseDuration = params.getLong("browseDuration");
        JSONObject extras = params.has("extras") ? params.getJSONObject("extras") : null;

        BrowseEvent event = new BrowseEvent(browseId, browseName, browseType, browseDuration);
        if (extras != null) {
            event.addExtMap(toMap(extras));
        }
        JAnalyticsInterface.onEvent(mContext, event);
    }

    void addPurchaseEvent(JSONArray data, CallbackContext callback) throws JSONException {
        JSONObject params = data.getJSONObject(0);
        String goodsId = params.getString("goodsId");
        String goodsName = params.getString("goodsName");
        double price = params.getDouble("price");
        boolean isPurchaseSuccess = params.getBoolean("isPurchaseSuccess");
        String goodsType = params.getString("goodsType");
        int goodsCount = params.getInt("goodsCount");

        String currencyStr = params.getString("currency");
        Currency currency = Currency.CNY;
        if (currencyStr.equals("USD")) {
            currency = Currency.USD;
        }

        JSONObject extras = params.has("extras") ? params.getJSONObject("extras") : null;

        PurchaseEvent event = new PurchaseEvent(goodsId, goodsName, price, isPurchaseSuccess, currency, goodsType,
                goodsCount);
        if (extras != null) {
            event.addExtMap(toMap(extras));
        }

        JAnalyticsInterface.onEvent(mContext, event);
    }

    void setAnalyticsReportPeriod(JSONArray data, CallbackContext callback) throws JSONException {// ......
        JSONObject params = data.getJSONObject(0);
        int period = params.getInt("period");
        JAnalyticsInterface.setAnalyticsReportPeriod(mContext, period);
    }

    void identifyAccount(JSONArray data, CallbackContext callback) throws JSONException {// ......
        JSONObject params = data.getJSONObject(0);
        String accountID = params.getString("accountID");
        long creationTime = params.getLong("creationTime");
        String name = params.getString("name");
        int sex = params.getInt("sex");
        int paid = params.getInt("paid");
        String birthdate = params.getString("birthdate");
        String phone = params.getString("phone");
        String email = params.getString("email");
        String weiboID = params.getString("weiboID");
        String wechatID = params.getString("wechatID");
        String qqID = params.getString("qqID");
        JSONObject extras = params.has("extras") ? params.getJSONObject("extras") : null;

        Account account = new Account(accountID);
        account.setCreationTime(creationTime); // 账户创建的时间戳
        account.setName(name);
        account.setSex(sex);
        account.setPaid(paid);
        account.setBirthdate(birthdate); // "19880920"是yyyyMMdd格式的字符串
        account.setPhone(phone);
        account.setEmail(email);
        account.setWeiboId(weiboID);
        account.setWechatId(wechatID);
        account.setQqId(qqID);
        if (extras != null) {
            Iterator iterator = extras.keys();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                String value = extras.getString(key);
                account.setExtraAttr(key, value); // key如果为空，或者以极光内部namespace(符号$)开头，会设置失败并打印日志
            }
        }
        JAnalyticsInterface.identifyAccount(mContext, account, new AccountCallback() {
            @Override
            public void callback(int code, String msg) {
                if (code == 0) {
                    callback.success();
                } else {
                    Log.i(JAnalyticsPlugin.class.getSimpleName(), "Identify account error code " + code + " :" + msg);
                    callback.error(msg);
                }
            }
        });
    }

    private Map<String, String> toMap(JSONObject json) throws JSONException {
        Map<String, String> map = new HashMap<String, String>();
        Iterator iterator = json.keys();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String value = json.getString(key);
            map.put(key, value);
        }
        return map;
    }
}
