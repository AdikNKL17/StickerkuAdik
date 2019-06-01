package com.example.samplestickerapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.ads.consent.ConsentInfoUpdateListener;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.onesignal.OneSignal;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class Menu_home extends AppCompatActivity {
//    private InterstitialAd mInterstitialAd;
    CardView bt1,bt2,bt3,bt4;
    Button bt5;
    private AdView mAdView;
    private static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_home);
                OneSignal.startInit(this)
                .setNotificationOpenedHandler(new MyNotificationOpenedHandler())
                .setNotificationReceivedHandler( new MyNotificationReceivedHandler() )
                .init();


        Fabric.with(this, new Crashlytics());
        logUser();
        final Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                .debuggable(true)
                .build();
        Fabric.with(fabric);
        StartAppSDK.init(this, getResources().getString(R.string.startapp_app_id), true);
        StartAppAd.showAd(this);
        context = getApplicationContext();
        ConsentInformation consentInformation = ConsentInformation.getInstance(getApplication());
        String[] publisherIds = {getResources().getString(R.string.admobApp)};
        consentInformation.requestConsentInfoUpdate(publisherIds, new ConsentInfoUpdateListener() {
            @Override
            public void onConsentInfoUpdated(ConsentStatus consentStatus) {
            }

            @Override
            public void onFailedToUpdateConsentInfo(String errorDescription) {
                // User's consent status failed to update.
            }
        });
        MobileAds.initialize(this, getResources().getString(R.string.admobApp));
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

//        mInterstitialAd = new InterstitialAd(this);
//        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial));
//        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        bt1 = findViewById(R.id.bt1);
        bt1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent sticker = new Intent(getApplicationContext(),EntryActivity.class);
                startActivity(sticker);
//                if (mInterstitialAd.isLoaded()) {
//                    mInterstitialAd.show();
//                } else {
//                 }

            }
        });

        bt2 = findViewById(R.id.bt2);
        bt2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent sticker = new Intent(getApplicationContext(),PrivacyPolicy.class);
                startActivity(sticker);

            }
        });
        bt3 = findViewById(R.id.bt3);
        bt3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));

            }
        });

        bt4 = findViewById(R.id.bt4);
        bt4.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:Romadhon+Kareem+Studio")));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/developer?id=Romadhon+Kareem+Studio")));
                }

            }
        });

        bt5 = findViewById(R.id.bt5);
        bt5.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
               finish();

            }
        });

    }
    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }
    private void logUser() {
        // TODO: Use the current user's information
        // You can call any combination of these three methods
        Crashlytics.setUserIdentifier("12345");
        Crashlytics.setUserEmail("user@fabric.io");
        Crashlytics.setUserName("Test User");
    }
}
