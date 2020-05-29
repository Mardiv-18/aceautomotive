package in.co.aceautomotive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView mywebView;
    SwipeRefreshLayout swipe;
    private Context context = this;
    public String lastUrl="<Your Website Link>";


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mywebView = (WebView) findViewById(R.id.webview);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(mywebView.getUrl().equals("file:///android_asset/error.html") && isNetworkAvailable())
                    LoadWeb(lastUrl);
                else if(mywebView.getUrl().equals("file:///android_asset/error.html") && !isNetworkAvailable())
                    mywebView.loadUrl("file:///android_asset/error.html");
                else
                    LoadWeb(mywebView.getUrl());
            }
        });
        LoadWeb("<Your Website Link>");

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void LoadWeb(String url){

        WebSettings webSettings = mywebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mywebView.getSettings().setAppCacheEnabled(true);
        mywebView.loadUrl(url);
        swipe.setRefreshing(true);

        mywebView.setWebViewClient(new WebViewClient(){
            @Override


            public void onPageFinished(WebView view, String url){
                //Hide the SwipeRefreshLayout
                swipe.setRefreshing(false);



            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl){
                lastUrl = failingUrl;
                mywebView.loadUrl("file:///android_asset/error.html");

            }

        });
    }



    @Override
    public void onBackPressed(){
        String url= mywebView.getUrl();
        if(mywebView.getUrl().equals("<Your Website Link>")){
            super.onBackPressed();
        }
        else if (mywebView.canGoBack()) {
            mywebView.goBack();
        } else {
            super.onBackPressed();
        }

    }

}
