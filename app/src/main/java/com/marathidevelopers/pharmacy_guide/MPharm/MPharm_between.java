package com.marathidevelopers.pharmacy_guide.MPharm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;


import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.marathidevelopers.pharmacy_guide.MPharm.Cognosy.Between_Cognosy;
import com.marathidevelopers.pharmacy_guide.MPharm.Cology.Between_Cology;
import com.marathidevelopers.pharmacy_guide.MPharm.PH_Chem.Between_PH_Chem;
import com.marathidevelopers.pharmacy_guide.MPharm.QA.Between_QA;
import com.marathidevelopers.pharmacy_guide.MPharm.Reg_Affair.Between_Reg_Affair;
import com.marathidevelopers.pharmacy_guide.MPharm.Sutics.Between_Sutics;
import com.marathidevelopers.pharmacy_guide.R;
import com.marathidevelopers.pharmacy_guide.Semisters.sem1;
import com.marathidevelopers.pharmacy_guide.Semisters.sem2;
import com.marathidevelopers.pharmacy_guide.Semisters.sem3;
import com.marathidevelopers.pharmacy_guide.Semisters.sem4;
import com.marathidevelopers.pharmacy_guide.Semisters.sem5;
import com.marathidevelopers.pharmacy_guide.Semisters.sem6;


public class MPharm_between extends AppCompatActivity {
    CardView box1;
    CardView box2;
    CardView box3;
    CardView box4;
    CardView box5;
    CardView box6;
    CardView box7;
    CardView box8;
    private InterstitialAd mInterstitialAd;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpharm_between);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });





        box1= findViewById(R.id.box1);
        box2= findViewById(R.id.box2);
        box3= findViewById(R.id.box3);
        box4= findViewById(R.id.box4);
        box5= findViewById(R.id.box5);
        box6= findViewById(R.id.box6);


        box1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
//                loadInterstitialAd();
                startActivity(new Intent(MPharm_between.this, Between_PH_Chem.class));

            }

        });


        box2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                loadInterstitialAd();
                startActivity(new Intent(MPharm_between.this, Between_Sutics.class));
            }

        });


        box3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loadInterstitialAd();
                startActivity(new Intent(MPharm_between.this, Between_Cognosy.class));
            }
        });

        box4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                loadInterstitialAd();
                startActivity(new Intent(MPharm_between.this, Between_Cology.class));
            }
        });

        box5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loadInterstitialAd();
                startActivity(new Intent(MPharm_between.this, Between_QA.class));
            }
        });

        box6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//                loadInterstitialAd();
                startActivity(new Intent(MPharm_between.this, Between_Reg_Affair.class));
            }
        });


    }

    public void setAds(){

        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,getString(R.string.inter), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;

            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                mInterstitialAd = null;
            }
        });

    }

    public void loadInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this, getString(R.string.inter), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {

                        interstitialAd.show(MPharm_between.this);
                        interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                super.onAdFailedToShowFullScreenContent(adError);
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                super.onAdShowedFullScreenContent();
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                super.onAdDismissedFullScreenContent();
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    }
                });
    }
}