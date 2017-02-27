/********* JAnalytics.m Cordova Plugin Implementation *******/
#import "JAnalytics.h"
#import <CoreLocation/CoreLocation.h>

// 引入JAnalytics功能所需头文件
#import "JANALYTICSService.h"
// 如果需要使用idfa功能所需要引入的头文件（可选）
//#import <AdSupport/AdSupport.h>

@implementation JAnalytics

- (void)pluginInitialize
{
    NSDictionary *plistDic = [[NSBundle mainBundle] infoDictionary];
    NSString* APP_KEY = [[plistDic objectForKey:@"JAnalytics"] objectForKey:@"APP_KEY"];
    NSString* CHANNEL = [[plistDic objectForKey:@"JAnalytics"] objectForKey:@"CHANNEL"];
    
    JANALYTICSLaunchConfig * config = [[JANALYTICSLaunchConfig alloc] init];
    config.appKey = APP_KEY;
    config.channel = CHANNEL;
    [JANALYTICSService setupWithConfig:config];
}

- (void)setDebugModel:(CDVInvokedUrlCommand*)command
{
    bool enable = [command.arguments objectAtIndex:0];
    [JANALYTICSService setDebug:enable];

    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)onPageStart:(CDVInvokedUrlCommand *)command
{
    NSString* pageName = [command.arguments objectAtIndex:0];
    [JANALYTICSService startLogPageView:pageName];
    
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)onPageEnd:(CDVInvokedUrlCommand *)command
{
    NSString* pageName = [command.arguments objectAtIndex:0];
    [JANALYTICSService stopLogPageView:pageName];
    
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)onCountEvent:(CDVInvokedUrlCommand *)command
{
    NSString* eventId =[command.arguments objectAtIndex:0];
    NSDictionary<NSString *, NSString *>* extra = [command.arguments objectAtIndex:1];
    
    JANALYTICSCountEvent * event = [[JANALYTICSCountEvent alloc] init];
    event.eventID = eventId;
    event.extra = extra;
    [JANALYTICSService eventRecord:event];
    
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)onCalculateEvent:(CDVInvokedUrlCommand *)command
{
    NSString* eventId =[command.arguments objectAtIndex:0];
    NSNumber* value = [command.arguments objectAtIndex:1];
    NSDictionary<NSString *, NSString *>* extra = [command.arguments objectAtIndex:2];
    
    JANALYTICSCalculateEvent * event = [[JANALYTICSCalculateEvent alloc] init];
    event.eventID = eventId;
    event.value = [value doubleValue];
    event.extra = extra;
    [JANALYTICSService eventRecord:event];
    
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)onLoginEvent:(CDVInvokedUrlCommand *)command
{
    NSString* loginMethod =[command.arguments objectAtIndex:0];
    BOOL loginSuccess = [command.arguments objectAtIndex:1];
    NSDictionary<NSString *, NSString *>* extra = [command.arguments objectAtIndex:2];
    
    JANALYTICSLoginEvent * event = [[JANALYTICSLoginEvent alloc] init];
    event.method = loginMethod;
    event.success = loginSuccess;
    event.extra = extra;
    [JANALYTICSService eventRecord:event];
    
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)onRegisterEvent:(CDVInvokedUrlCommand *)command
{
    NSString* registerMethod =[command.arguments objectAtIndex:0];
    BOOL registerSuccess = [command.arguments objectAtIndex:1];
    NSDictionary<NSString *, NSString *>* extra = [command.arguments objectAtIndex:2];
    
    JANALYTICSRegisterEvent * event = [[JANALYTICSRegisterEvent alloc] init];
    event.method = registerMethod;
    event.success = registerSuccess;
    event.extra = extra;
    [JANALYTICSService eventRecord:event];
    
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)onBrowseEvent:(CDVInvokedUrlCommand *)command
{
    NSString* browseId = [command.arguments objectAtIndex:0];
    NSString* browseName = [command.arguments objectAtIndex:1];
    NSString* browseType = [command.arguments objectAtIndex:2];
    NSNumber* browseDuration = [command.arguments objectAtIndex:3];
    NSDictionary<NSString *, NSString *>* extra = [command.arguments objectAtIndex:4];
    
    JANALYTICSBrowseEvent * event = [[JANALYTICSBrowseEvent alloc] init];
    event.contentID = browseId;
    event.name = browseName;
    event.type = browseType;
    event.duration = [browseDuration longValue];
    event.extra = extra;
    [JANALYTICSService eventRecord:event];
    
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)onPurchaseEvent:(CDVInvokedUrlCommand *)command
{
    NSString* purchaseGoodsid = [command.arguments objectAtIndex:0];
    NSString* purchaseGoodsName = [command.arguments objectAtIndex:1];
    NSNumber* purchasePrice = [command.arguments objectAtIndex:2];
    BOOL purchaseSuccess = [command.arguments objectAtIndex:3];
    NSString* purchaseCurrency = [command.arguments objectAtIndex:4];
    NSString* purchaseGoodsType = [command.arguments objectAtIndex:5];
    NSNumber* purchaseGoodsCount = [command.arguments objectAtIndex:6];
    NSDictionary<NSString *, NSString *>* extra = [command.arguments objectAtIndex:7];
    
    JANALYTICSPurchaseEvent * event = [[JANALYTICSPurchaseEvent alloc] init];
    event.success = purchaseSuccess;
    event.price = [purchasePrice floatValue];
    event.goodsName = purchaseGoodsName;
    event.goodsType = purchaseGoodsType;
    event.quantity = [purchaseGoodsCount intValue];
    event.goodsID = purchaseGoodsid;
    event.currency = [purchaseCurrency isEqual:@"USD"] ? JANALYTICSCurrencyUSD: JANALYTICSCurrencyCNY;
    event.extra = extra;
    [JANALYTICSService eventRecord:event];
    
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)setLocation:(CDVInvokedUrlCommand*)command
{
    NSNumber* latitude = [command.arguments objectAtIndex:0];
    NSNumber* longitude = [command.arguments objectAtIndex:1];
    
    [JANALYTICSService setLatitude:[latitude doubleValue] longitude:[longitude doubleValue]];
    
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)crashLogON:(CDVInvokedUrlCommand*)command
{
    [JANALYTICSService crashLogON];
    
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
