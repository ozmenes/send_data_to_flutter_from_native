package com.ozmenes.data_to_flutter_from_native;

import androidx.annotation.NonNull;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class MainActivity extends FlutterActivity {
    private static final String CHANNEL = "from_native";

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(),CHANNEL)
                .setMethodCallHandler(
                        new MethodChannel.MethodCallHandler() {
                            @Override
                            public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
                                if (call.method.equals("helloFromNativeCode")){
                                    String sayHello = helloFromNativeCode();
                                    if(sayHello != null){
                                        result.success(sayHello);
                                    }else {
                                        result.error("UNAVAILABLE","Data can not get from Native.", null);
                                    }
                                }else{
                                    result.notImplemented();
                                }
                            }
                        }
                );
    }
    private String helloFromNativeCode() {

        return "Hello from Native Android Code";
    }
}
