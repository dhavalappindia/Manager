import android.content.Context;
import android.util.Log;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class AdManager {
    // Static fields are shared between all instances.
    static InterstitialAd interstitialAd;

    public AdManager(Context context) {
        createAd(context);
    }

    public static void createAd(Context context) {
        // Create an ad.
        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId(context.getString(R.string.admob_fullscreen_id));

        final AdRequest adRequest = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                .addTestDevice(TEST_DEVICE_ID)
                .build();

        // Load the interstitial ad.
        interstitialAd.loadAd(adRequest);
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                Log.d("TAG===","CLOSE ADS");
                interstitialAd.loadAd(adRequest);
            }
        });
    }

    public static void showAd(Context context) {
//        return interstitialAd;

        if (interstitialAd.isLoaded())
            interstitialAd.show();
        else
            showFacebookFullScreen(context);
    }

    public static void showFacebookFullScreen(Context context){

        Log.d("TAG","LOAD FACEBOOK ADS");
        final com.facebook.ads.InterstitialAd fbInterstitialAd = new com.facebook.ads.InterstitialAd(context,context.getString(R.string.fb_interstitial_ads_id));
        fbInterstitialAd.setAdListener(new InterstitialAdListener() {

            @Override
            public void onError(Ad arg0, AdError arg1) {

            }

            @Override
            public void onAdLoaded(Ad ad) {
                fbInterstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad arg0) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }

            @Override
            public void onInterstitialDisplayed(Ad arg0) {

            }

            @Override
            public void onInterstitialDismissed(Ad arg0) {

            }
        });
        fbInterstitialAd.loadAd();
    }
}
