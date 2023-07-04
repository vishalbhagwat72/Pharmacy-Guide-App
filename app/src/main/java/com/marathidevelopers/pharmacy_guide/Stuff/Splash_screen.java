package com.marathidevelopers.pharmacy_guide.Stuff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;
import com.marathidevelopers.pharmacy_guide.MainActivity;
import com.marathidevelopers.pharmacy_guide.R;

public class Splash_screen extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread td = new Thread(){
            public  void run(){
                try {
                    sleep(4000);

                }catch (Exception ex){
                    ex.printStackTrace();
                }
                finally {

                    Intent intent = new Intent(Splash_screen.this , MainActivity.class );
                    startActivity(intent);
                    finish();
                }
            }
        };td.start();

//        checkUpdate();
//    }



//    private void checkUpdate() {
//
//        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(this);
//
//// Returns an intent object that you use to check for an update.
//        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
//
//
//// Checks that the platform will allow the specified type of update.
//        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
//            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
//                    // This example applies an immediate update. To apply a flexible update
//                    // instead, pass in AppUpdateType.FLEXIBLE
//                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
//                // Request the update.
//                try {
//                    appUpdateManager.startUpdateFlowForResult(
//                            // Pass the intent that is returned by 'getAppUpdateInfo()'.
//                            appUpdateInfo,
//                            // Or 'AppUpdateType.FLEXIBLE' for flexible updates.
//                            AppUpdateType.IMMEDIATE,
//                            // The current activity making the update request.
//                            this,
//                            // Include a request code to later monitor this update request.
//                            MY_REQUEST_CODE);
//                } catch (IntentSender.SendIntentException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == MY_REQUEST_CODE) {
//            if (resultCode != RESULT_OK) {
//                Log.w("Splash_screen","Update flow failed! Result code: " + resultCode);
//
//                // If the update is cancelled or fails,
//                // you can request to start the update again.
//            }
//        }
    }

    }
