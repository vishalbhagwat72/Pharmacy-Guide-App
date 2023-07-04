
package com.marathidevelopers.pharmacy_guide.BPharm;

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
import com.google.android.gms.ads.MobileAds;
import com.marathidevelopers.pharmacy_guide.R;


public class qpaper extends AppCompatActivity {

    WebView qpaper;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qpaper);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        MobileAds.initialize(this, initializationStatus -> {
        });

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(getString(R.string.banner));
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        qpaper = (WebView) findViewById(R.id.qpaper1);
        qpaper.loadUrl("https://drive.google.com/drive/folders/1Sb0fD5Jpr4sbIc4mmOt8SAJGBkbIFQ9i?usp=sharing");
        qpaper.setWebViewClient(new Client());
        WebSettings ws = qpaper.getSettings();
        ws.setJavaScriptEnabled(true);
        qpaper.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        qpaper.clearCache(true);
        qpaper.clearHistory();
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
        this.qpaper.setDownloadListener(new DownloadListener() {
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
                        qpaper.this.getSystemService("download")).enqueue(request);
                Toast.makeText(getApplicationContext(),"Downloading Started",Toast.LENGTH_SHORT).show();
                qpaper.this.registerReceiver(new BroadcastReceiver() {
                                              public void onReceive(Context context, Intent intent) {
                                                  Toast.makeText(getApplicationContext(),"Download Completed",Toast.LENGTH_SHORT).show();
                                                  qpaper.this.unregisterReceiver(this); }},
                        new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
            }
        });
    }
}