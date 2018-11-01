#import <Cordova/CDV.h>

@interface JAnalyticsPlugin : CDVPlugin

- (void)init:(CDVInvokedUrlCommand *)command;

- (void)setDebugMode:(CDVInvokedUrlCommand *)command;

- (void)initCrashHandler:(CDVInvokedUrlCommand *)command;

- (void)stopCrashHandler:(CDVInvokedUrlCommand *)command;

- (void)onPageStart:(CDVInvokedUrlCommand *)command;

- (void)onPageEnd:(CDVInvokedUrlCommand *)command;

- (void)addCountEvent:(CDVInvokedUrlCommand *)command;

- (void)addCalculateEvent:(CDVInvokedUrlCommand *)command;

- (void)addLoginEvent:(CDVInvokedUrlCommand *)command;

- (void)addRegisterEvent:(CDVInvokedUrlCommand *)command;

- (void)addBrowseEvent:(CDVInvokedUrlCommand *)command;

- (void)addPurchaseEvent:(CDVInvokedUrlCommand *)command;

- (void)setAnalyticsReportPeriod:(CDVInvokedUrlCommand *)command;

- (void)identifyAccount:(CDVInvokedUrlCommand *)command;
@end
