#import "OvclipboardPlugin.h"

@implementation OvclipboardPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  FlutterMethodChannel* channel = [FlutterMethodChannel
      methodChannelWithName:@"ovclipboard"
            binaryMessenger:[registrar messenger]];
  OvclipboardPlugin* instance = [[OvclipboardPlugin alloc] init];
  [registrar addMethodCallDelegate:instance channel:channel];
}

- (void)handleMethodCall:(FlutterMethodCall*)call result:(FlutterResult)result {
  if ([@"getPlatformVersion" isEqualToString:call.method]) {
    result([@"iOS " stringByAppendingString:[[UIDevice currentDevice] systemVersion]]);
  }
  else if ([@"copyToClipBoard" isEqualToString:call.method]) {
        UIPasteboard *pasteboard = [UIPasteboard generalPasteboard];
        pasteboard.string = call.arguments[@"text"];
        result(@(1));
  }
  else {
    result(FlutterMethodNotImplemented);
  }
}

@end
