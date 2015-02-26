package com.lee.webviewbug;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.leee.webviewbug.R;

public class MainActivity extends Activity {

	WebViewEx mWebView;
//	WebViewEx mWebView;
    String mUrl = "http://bitkiller.duapp.com/jsobj.html";
    String mUrl2 = "file:///android_asset/html/test.html";
    String mUrl3 = "file:///android_asset/html/js3.html";
    Object mJsObj = new JSInterface();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mWebView = (WebViewEx) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new JSInterface(), "jsInterface");
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.action_load:
            mWebView.loadUrl(mUrl2);
            break;
            
        case R.id.action_refresh:
            mWebView.reload();
            break;
        }
        
        return super.onOptionsItemSelected(item);
    }
    
    class JSInterface {
        @JavascriptInterface
        public String onButtonClick(String text) {
            final String str = text;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.e("leehong2", "onButtonClick: text = " + str);
                    Toast.makeText(getApplicationContext(), "onButtonClick: text = " + str, Toast.LENGTH_LONG).show();
                }
            });
            
            return "This text is returned from Java layer.  js text = " + text;  //return数据在JS中alert显示
        }
        
        @JavascriptInterface
        public void onImageClick(String url, int width, int height) {
            final String str = "onImageClick: text = " + url + "  width = " + width + "  height = " + height;
            Log.i("leehong2", str);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
