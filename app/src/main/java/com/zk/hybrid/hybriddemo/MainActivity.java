package com.zk.hybrid.hybriddemo;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final H5WebView webView = (H5WebView) findViewById(R.id.webview);

        webView.loadUrl("file:///android_asset/test.html");
        webView.addJavascriptInterface(new JsInteration(), "android");


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("javascript:javacalljs()");
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("javascript:javacalljswith(" + "'http://blog.csdn.net/Leejizhou'" + ")");
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                webView.evaluateJavascript("sum(1,2)", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        Toast.makeText(MainActivity.this,value,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }


    public  class  JsInteration{

        @JavascriptInterface
        public void javaFunction() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "show", Toast.LENGTH_SHORT).show();
                }
            });
        }


        @JavascriptInterface
        public void javaFunction(final String text) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }




}
