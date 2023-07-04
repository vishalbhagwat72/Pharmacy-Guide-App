package com.marathidevelopers.pharmacy_guide.Stuff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebView;

import com.marathidevelopers.pharmacy_guide.R;


public class otherapps extends AppCompatActivity {

    WebView app;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otherapps);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        app= (WebView)findViewById(R.id.app);
        app.loadUrl("https://play.google.com/store/apps/dev?id=7639115298975832359");
    }
}

