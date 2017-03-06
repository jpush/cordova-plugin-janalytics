#import <Cordova/CDV.h>

@interface JAnalytics : CDVPlugin
{
    // Member variables go here.
}

- (void)pluginInitialize;

- (void)setDebugModel:(CDVInvokedUrlCommand *)command;

- (void)onPageStart:(CDVInvokedUrlCommand *)command;

- (void)onPageEnd:(CDVInvokedUrlCommand *)command;

- (void)onCountEvent:(CDVInvokedUrlCommand *)command;

- (void)onCalculateEvent:(CDVInvokedUrlCommand *)command;

- (void)onLoginEvent:(CDVInvokedUrlCommand *)command;

- (void)onRegisterEvent:(CDVInvokedUrlCommand *)command;

- (void)onBrowseEvent:(CDVInvokedUrlCommand *)command;

- (void)onPurchaseEvent:(CDVInvokedUrlCommand *)command;

- (void)setLocation:(CDVInvokedUrlCommand *)command;

- (void)crashLogON:(CDVInvokedUrlCommand *)command;

@end