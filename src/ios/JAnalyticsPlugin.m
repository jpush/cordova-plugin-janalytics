#import "JAnalyticsPlugin.h"
#import <CoreLocation/CoreLocation.h>

#import "JANALYTICSService.h"
#import "JANALYTICSEventObject.h"

// 如果需要使用idfa功能所需要引入的头文件（可选）
//#import <AdSupport/AdSupport.h>

@implementation JAnalyticsPlugin

- (void)init:(CDVInvokedUrlCommand *)command {
    NSDictionary *plistDic = [[NSBundle mainBundle] infoDictionary];
    NSString* APP_KEY = [[plistDic objectForKey:@"JAnalytics"] objectForKey:@"APP_KEY"];
    NSString* CHANNEL = [[plistDic objectForKey:@"JAnalytics"] objectForKey:@"CHANNEL"];
    
    JANALYTICSLaunchConfig * config = [[JANALYTICSLaunchConfig alloc] init];
    config.appKey = APP_KEY;
    config.channel = CHANNEL;
    [JANALYTICSService setupWithConfig:config];
}

- (void)setDebugMode:(CDVInvokedUrlCommand *)command {
    NSDictionary *params = [command.arguments objectAtIndex:0];
    bool enable = params[@"enable"];
    [JANALYTICSService setDebug:enable];
}

- (void)initCrashHandler:(CDVInvokedUrlCommand *)command {
    [JANALYTICSService crashLogON];
}

- (void)stopCrashHandler:(CDVInvokedUrlCommand *)command {
    
}

- (void)onPageStart:(CDVInvokedUrlCommand *)command {
    NSDictionary *params = [command.arguments objectAtIndex:0];
    NSString *pageName = params[@"pageName"];
    [JANALYTICSService startLogPageView:pageName];
}

- (void)onPageEnd:(CDVInvokedUrlCommand *)command {
    NSDictionary *params = [command.arguments objectAtIndex:0];
    NSString *pageName = params[@"pageName"];
    [JANALYTICSService stopLogPageView:pageName];
}

- (void)addCountEvent:(CDVInvokedUrlCommand *)command {
    NSDictionary *params = [command.arguments objectAtIndex:0];
    NSString *eventId = params[@"eventId"];
    NSDictionary<NSString *, NSString *> *extra = params[@"extras"];
    
    JANALYTICSCountEvent *event = [[JANALYTICSCountEvent alloc] init];
    event.eventID = eventId;
    event.extra = extra;
    [JANALYTICSService eventRecord:event];
}

- (void)addCalculateEvent:(CDVInvokedUrlCommand *)command {
    NSDictionary *params = [command.arguments objectAtIndex:0];
    NSString *eventId = params[@"eventId"];
    NSNumber *eventValue = params[@"eventValue"];
    NSDictionary<NSString *, NSString *> *extra = params[@"extras"];
    
    JANALYTICSCalculateEvent *event = [[JANALYTICSCalculateEvent alloc] init];
    event.eventID = eventId;
    event.value = [eventValue floatValue];
    event.extra = extra;
    [JANALYTICSService eventRecord:event];
}

- (void)addLoginEvent:(CDVInvokedUrlCommand *)command {
    NSDictionary *params = [command.arguments objectAtIndex:0];
    NSString *loginMethod = params[@"loginMethod"];
    NSNumber *isLoginSuccess = params[@"isLoginSuccess"];
    NSDictionary<NSString *, NSString *> *extra = params[@"extras"];
    
    JANALYTICSLoginEvent *event = [[JANALYTICSLoginEvent alloc] init];
    event.method = loginMethod;
    event.success = isLoginSuccess;
    event.extra = extra;
    [JANALYTICSService eventRecord:event];
}

- (void)addRegisterEvent:(CDVInvokedUrlCommand *)command {
    NSDictionary *params = [command.arguments objectAtIndex:0];
    NSString *registerMethod = params[@"registerMethod"];
    NSNumber *isRegisterSuccess = params[@"isRegisterSuccess"];
    NSDictionary<NSString *, NSString *> *extra = params[@"extras"];
    
    JANALYTICSRegisterEvent *event = [[JANALYTICSRegisterEvent alloc] init];
    event.method = registerMethod;
    event.success = isRegisterSuccess;
    event.extra = extra;
    [JANALYTICSService eventRecord:event];
}

- (void)addBrowseEvent:(CDVInvokedUrlCommand *)command {
    NSDictionary *params = [command.arguments objectAtIndex:0];
    
    NSString *browseId = params[@"browseId"];
    NSString *browseName = params[@"browseName"];
    NSString *browseType = params[@"browseType"];
    NSNumber *browseDuration = params[@"browseDuration"];
    NSDictionary<NSString *, NSString *> *extra = params[@"extras"];
    
    JANALYTICSBrowseEvent * event = [[JANALYTICSBrowseEvent alloc] init];
    event.contentID = browseId;
    event.name = browseName;
    event.type = browseType;
    event.duration = [browseDuration longValue];
    event.extra = extra;
    [JANALYTICSService eventRecord:event];
}

- (void)addPurchaseEvent:(CDVInvokedUrlCommand *)command {
    NSDictionary *params = [command.arguments objectAtIndex:0];
    
    NSString *goodsId = params[@"goodsId"];
    NSString *goodsName = params[@"goodsName"];
    NSNumber *price = params[@"price"];
    NSString *currency = params[@"currency"];
    NSNumber *isPurchaseSuccess = params[@"isPurchaseSuccess"];
    NSString *goodsType = params[@"goodsType"];
    NSNumber *goodsCount = params[@"goodsCount"];
    NSDictionary<NSString *, NSString *> *extra = params[@"extras"];
    
    JANALYTICSPurchaseEvent * event = [[JANALYTICSPurchaseEvent alloc] init];
    event.success = isPurchaseSuccess;
    event.price = [price floatValue];
    event.goodsName = goodsName;
    event.goodsType = goodsType;
    event.quantity = [goodsCount integerValue];
    event.goodsID = goodsId;
    event.currency = [currency isEqual:@"USD"] ? JANALYTICSCurrencyUSD: JANALYTICSCurrencyCNY;
    event.extra = extra;
    [JANALYTICSService eventRecord:event];
}

- (void)setAnalyticsReportPeriod:(CDVInvokedUrlCommand *)command {
  NSDictionary *params = [command.arguments objectAtIndex:0];
  [JANALYTICSService setFrequency:[params[@"period"] unsignedIntegerValue]];
}

- (void)identifyAccount:(CDVInvokedUrlCommand *)command {
  NSDictionary *params = [command.arguments objectAtIndex:0];
  
  JANALYTICSUserInfo *userInfo = [[JANALYTICSUserInfo alloc] init];
  userInfo.accountID = params[@"accountID"];
  userInfo.name = params[@"name"];
  userInfo.creationTime = [params[@"creationTime"] doubleValue];
  if ([params[@"sex"] intValue] == 1) {
    userInfo.sex = JANALYTICSSexMale;
  } else if ([params[@"creationTime"] intValue] == 2) {
    userInfo.sex = JANALYTICSSexFemale;
  } else {
    userInfo.sex = JANALYTICSSexUnknown;
  }
  
  if ([params[@"paid"] intValue] == 1) {
    userInfo.paid = JANALYTICSPaidPaid;
  } else if ([params[@"creationTime"] intValue] == 2) {
    userInfo.paid = JANALYTICSPaidUnpaid;
  } else {
    userInfo.paid = JANALYTICSPaidUnknown;
  }
  
  userInfo.birthdate = params[@"birthdate"];
  userInfo.phone = params[@"phone"];
  userInfo.email = params[@"email"];
  userInfo.weiboID = params[@"weiboID"];
  userInfo.wechatID = params[@"wechatID"];
  userInfo.qqID = params[@"qqID"];
  NSDictionary *extras = params[@"extras"];
  for (NSString* key in extras.allKeys) {
    [userInfo setExtraObject:extras[key] forKey:key];
  }
  
  [JANALYTICSService identifyAccount:userInfo with:^(NSInteger err, NSString *msg) {
    CDVPluginResult *result;
    if (err) {
       result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString: msg];
      [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    } else {

      result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
      [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    }
  }];
}

@end
