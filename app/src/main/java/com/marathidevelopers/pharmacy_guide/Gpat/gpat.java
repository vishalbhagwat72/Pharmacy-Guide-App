package com.marathidevelopers.pharmacy_guide.Gpat;

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

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.marathidevelopers.pharmacy_guide.R;

public class gpat extends AppCompatActivity {

    WebView gpat;
    private AdView mAdView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpat);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(getString(R.string.banner));
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                super.onAdFailedToLoad(adError);
                mAdView.loadAd(adRequest);
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                super.onAdOpened();
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });


        gpat= (WebView)findViewById(R.id.gpat);
        gpat.loadUrl("https://drive.google.com/drive/folders/1kXju-9KAhOCjsvYQRYnWmhkbPc2EN0ix?usp=sharing");
        gpat.setWebViewClient(new gpat.Client());
        WebSettings ws = gpat.getSettings();
        ws.setJavaScriptEnabled(true);
        gpat.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        gpat.clearCache(true);
        gpat.clearHistory();




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
        this.gpat.setDownloadListener(new DownloadListener() {
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
                        gpat.this.getSystemService("download")).enqueue(request);
                Toast.makeText(getApplicationContext(), "Downloading Started", Toast.LENGTH_SHORT).show();
                gpat.this.registerReceiver(new BroadcastReceiver() {
                                               public void onReceive(Context context, Intent intent) {
                                                   Toast.makeText(getApplicationContext(), "Download Completed", Toast.LENGTH_SHORT).show();
                                                   gpat.this.unregisterReceiver(this);
                                               }
                                           },
                        new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
            }
        });
//            AdRequest adRequest = new AdRequest.Builder().build();
//            InterstitialAd.load(this,"ca-app-pub-4219589346175353/3400488458", adRequest, new InterstitialAdLoadCallback() {
//                @Override
//                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
//                    // The mInterstitialAd reference will be null until
//                    // an ad is loaded.
//                    mInterstitialAd = interstitialAd;
//                }
//
//                @Override
//                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                    // Handle the error
//                    mInterstitialAd = null;
//                }
//            });
//    }
//    @Override
//    public void onBackPressed() {
//
//        Intent intent = new Intent(gpat.this, MainActivity.class);
//        startActivity(intent);
//
//
//        if (mInterstitialAd != null) {
//            mInterstitialAd.show(gpat.this);
//            gpat.super.onBackPressed();
//
//        } else {
//            Log.d("ADMOB", "The interstitial ad wasn't ready yet.");
//        }
    }}
