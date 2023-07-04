package com.marathidevelopers.pharmacy_guide.Semisters;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.marathidevelopers.pharmacy_guide.R;


public class sem1 extends AppCompatActivity {

    WebView sem1;
    private AdView mAdView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem1);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(getString(R.string.banner));
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);






        sem1 = (WebView) findViewById(R.id.sem1);
        sem1.loadUrl("https://drive.google.com/drive/folders/1vTo5LbX4-c4T-Bp4paxON_Tvot82QigA?usp=sharing");
        sem1.setWebViewClient(new sem1.Client());
        WebSettings ws = sem1.getSettings();
        ws.setJavaScriptEnabled(true);
        sem1.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        sem1.clearCache(true);
        sem1.clearHistory();
    }

    private class Client extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }

    public void onStart() {
        super.onStart();
        this.sem1.setDownloadListener(new DownloadListener() {
            @SuppressLint("WrongConstant")
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
                request.addRequestHeader("cookie", CookieManager.getInstance().getCookie(str));
                request.addRequestHeader("User-Agent", str2);
                request.setDescription("Downloading file...");
                request.setTitle(URLUtil.guessFileName(str, str3, str4));
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(1);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(str, str3, str4));
                ((DownloadManager)
                sem1.this.getSystemService("download")).enqueue(request);
                Toast.makeText(getApplicationContext(),"Downloading Started",Toast.LENGTH_SHORT).show();
                sem1.this.registerReceiver(new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                Toast.makeText(getApplicationContext(),"Download Completed",Toast.LENGTH_SHORT).show();
                sem1.this.unregisterReceiver(this); }},
                new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
            }
        });
    }
}
