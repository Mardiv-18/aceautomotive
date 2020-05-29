package in.co.aceautomotive;
import in.co.aceautomotive.InternetConnection;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebChromeClient;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.content.Intent;

import static in.co.aceautomotive.InternetConnection.isConnectingToInternet;

public class MainActivity extends AppCompatActivity {
    private WebView mywebView;
    private Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mywebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = mywebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mywebView.loadUrl("<Your Website Link>");
        mywebView.setWebViewClient(new myWebClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                return super.shouldOverrideUrlLoading(view, request);
            }
        });


    }
    public class myWebClient extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon){
            if(isConnectingToInternet(context)){
                super.onPageStarted(view, url, favicon);
            }else{

                InternetConnection.showAlert("Internet is not working, Tap Refresh to retry",context);
            }
        }
    }

    @Override
    public void onBackPressed(){
        if (mywebView.canGoBack()){
            mywebView.goBack();
        }else {
            super.onBackPressed();
        }
    }



}
