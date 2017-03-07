## API
> - 用例中的'success', 'error'参数代表成功回调函数与失败回调函数.
>
> - extMap为附加参数，仅能纯属一个小于10个字段的1级key-value对象
>   正确用例：`{ a:1, b:2, c: 3}`
>   错误用例：`{a: {c: 1, b:2}}`
>
>   下面将不再提及


### 设置是否开启debug模式(开启后将打印更多调试信息)
Janalytics.setDebugModel(enable, success, error);
- enable `boolean` 是否开启debug模式
- success `function`
- error `function`

``` javascript
Janalytics.setDebugModel(true, 
	function(){
        alert("操作成功");
	}, 
	function(err){
		alert(err);
	}
);
```

### 记录页面启动
Janalytics.onPageStart(pageName, success, error)
- pageName `string` 页面名称
- success `function`
- error `function`
> 注意 此接口应当尽可能与onPageEnd对应

``` javascript
Janalytics.onPageStart("登陆页面", function(){}, function(err){});
```

### 记录页面结束
Janalytics.onPageEnd(pageName, success, error)
- pageName `string` 页面名称
- success `function`
- error `function`

``` javascript
Janalytics.onPageEnd("登陆页面", function(){}, function(err){});
```

### 自定义计数事件
Janalytics.onCountEvent(eventId, extMap, success, error)
- eventId `string` 自定义事件ID
- extMap `keyValue`
- success `function`
- error `function`
> 注意 此接口属于自定义事件，需要提前到极光控制台中创建与eventId同名的事件

``` javascript
Janalytics.onCountEvent("计数事件测试", {a:1, b:2}, function(){}, function(err){});
```

### 自定义计算事件
Janalytics.onCalculateEvent(eventId, eventValue, extMap, success, error)
- eventId `string` 自定义事件ID
- eventValue `number` 计算值
- extMap `keyValue`
- success `function`
- error `function`
> 注意 此接口属于自定义事件，需要提前到极光控制台中创建与eventId同名的事件

``` javascript
Janalytics.onCalculateEvent("计算事件测试", 1.2, {a:1, b:2}, function(){}, function(err){});
```

### 登陆事件
Janalytics.onLoginEvent(loginMethod, loginSuccess, extMap, success, error)
- loginMethod `string` 登陆方式
- loginSuccess `boolean` 是否成功登陆
- extMap `keyValue`
- success `function`
- error `function`
``` javascript
Janalytics.onLoginEvent("QQ登陆", true, {a:1, b:2}, function(){}, function(err){});
```

### 注册事件
Janalytics.onRegisterEvent(registerMethod, registerSuccess, extMap, success, error)
- registerMethod `string` 注册方式
- registerSuccess `boolean` 是否成功注册
- extMap `keyValue`
- success `function`
- error `function`

``` javascript
Janalytics.onRegisterEvent("手机号注册", true, {a:1, b:2}, function(){}, function(err){});
```

### 浏览事件
Janalytics.onRegisterEvent(browseId, browseName, browseType, browseDuration, extMap, success, error)
- browseId `string` 浏览ID
- browseName `string` 浏览名
- browseType `string` 浏览类型
- browseDuration `number` 浏览时长
- extMap `keyValue`
- success `function`
- error `function`

``` javascript
  Janalytics.onRegisterEvent(
    browseId, //浏览ID `string`
    browseName,//浏览名 `string`
    browseType,//浏览类型 `string`
    browseDuration,//浏览时长 `long`
    extMap,
    success, error
  )
```

### 购买事件
Janalytics.onPurchaseEvent(purchaseGoodsid, purchaseGoodsName, purchasePrice, purchaseSuccess, purchaseCurrency, purchaseGoodsType, purchaseGoodsCount, purchaseGoodsCount, extMap,  success, error)
- purchaseGoodsid `string` 商品ID
- purchaseGoodsName `string` 商品名
- purchasePrice `number` 商品价格
- purchaseSuccess `boolean` 是否购买成功
- purchaseCurrency `string` 货币类型 ("USD":美元，"CNY":人民币)
- purchaseGoodsType `string` 商品类型
- purchaseGoodsCount `number` 商品数量
- extMap `keyValue`
- success `function`
- error `function`

``` javascript
  Janalytics.onPurchaseEvent(
    purchaseGoodsid, //商品ID `string`
    purchaseGoodsName, //商品名 `string`
    purchasePrice, //商品价格 `float`
    purchaseSuccess, //是否购买成功  `booleabn`
    purchaseCurrency, //货币类型  `string` ("USD":美元，"CNY":人民币)
    purchaseGoodsType, //商品类型 `string`
    purchaseGoodsCount, //商品数量 `int`
    extMap,
    success, error
  )
```

### 开启崩溃收集(仅支持IOS设备, 默认是关闭的)
Janalytics.crashLogON(success, error)
- success `function`
- error `function`

### 上报地理位置(仅支持IOS设备)
Janalytics.setLocation(latitude, longitude, success, error)
- latitude `number` 纬度
- longitude `number` 经度
- success `function`
- error `function`

## 参考资料
[极光官方 janalytics 开发文档](https://docs.jiguang.cn/janalytics/guideline/intro/)
