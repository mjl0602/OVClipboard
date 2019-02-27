package com.oversketch.ovclipboard;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** OvclipboardPlugin */
public class OvclipboardPlugin implements MethodCallHandler {

  private Registrar registrar;

  private ClipboardManagerPlugin(Registrar registrar){
    this.registrar=registrar;
  }
  
  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "ovclipboard");
    channel.setMethodCallHandler(new OvclipboardPlugin());
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    }else if (call.method.equals("copyToClipBoard")) {
      String thingToCopy = call.argument("text");
      Context context;
      if (registrar.activity() != null) {
        context = (Context) registrar.activity();
      } else {
        context = registrar.context();
      }
      ClipboardManager clipboard = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
      ClipData clip = ClipData.newPlainText("", thingToCopy);
      clipboard.setPrimaryClip(clip);
      result.success(1);
    } else {
      result.notImplemented();
    }
  }
}
