import 'dart:async';

import 'package:flutter/services.dart';

class Ovclipboard {
  static const MethodChannel _channel =
      const MethodChannel('ovclipboard');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<int> copyToClipBoard(String text) async {
    final int result = await _channel.invokeMethod('copyToClipBoard',
    <String,String>{
      'text':text
    });
    return result;
  }
}
