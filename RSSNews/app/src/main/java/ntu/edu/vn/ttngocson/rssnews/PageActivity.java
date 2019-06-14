package ntu.edu.vn.ttngocson.rssnews;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class PageActivity extends AppCompatActivity {

    WebView webView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        addViews();
    }

    private void addViews() {
        progressBar = findViewById(R.id.progressBar_page);
        progressBar.setMax(100);
        webView = findViewById(R.id.wvPage);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if(newProgress == 100)
                    progressBar.setVisibility(View.GONE);
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }
            }
        });
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //return super.shouldOverrideUrlLoading(view, request);
                return false;
            }
        });
        Intent intent= getIntent();
        String url = intent.getStringExtra("page_url");
        webView.loadUrl(url);
    }
    public static Intent getIntent(Context context, String url){
        Intent intent = new Intent(context, PageActivity.class);
        intent.putExtra("page_url", url);
        return intent;
    }
}
