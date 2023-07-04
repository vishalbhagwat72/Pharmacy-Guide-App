package com.marathidevelopers.pharmacy_guide.MPharm.QA;

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

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdView;
import com.marathidevelopers.pharmacy_guide.R;

public class QA_Books extends AppCompatActivity {
    WebView wb;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qa_books);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        AdView adView = new AdView(this);
//        adView.setAdSize(AdSize.BANNER);
//        adView.setAdUnitId(getString(R.string.banner));
////        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);






        wb = (WebView) findViewById(R.id.web);
        wb.loadUrl("https://drive.google.com/drive/folders/17nw1Jxgh1uPMVujy0qZ9g65Wa_VdVW_c?usp=share_link");
        wb.setWebViewClient(new QA_Books.Client());
        WebSettings ws = wb.getSettings();
        ws.setJavaScriptEnabled(true);
        wb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wb.clearCache(true);
        wb.clearHistory();
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
        this.wb.setDownloadListener(new DownloadListener() {
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
                        QA_Books.this.getSystemService("download")).enqueue(request);
                Toast.makeText(getApplicationContext(),"Downloading Started",Toast.LENGTH_SHORT).show();
                QA_Books.this.registerReceiver(new BroadcastReceiver() {
                                                     public void onReceive(Context context, Intent intent) {
                                                         Toast.makeText(getApplicationContext(),"Download Completed",Toast.LENGTH_SHORT).show();
                                                         QA_Books.this.unregisterReceiver(this); }},
                        new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
            }
        });
    }
}