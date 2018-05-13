package activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;

import com.m.uet.apptranslate.R;

import java.io.IOException;

import translate_api.LanguageForTransApi;
import translate_api.TranslateUtils;
import utils.Utils;


public class HelloActivity extends AppCompatActivity {
    private Handler mWaitHandler = new Handler();


    private String tag = "helloAct__";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);


        /*Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics ();
        display.getMetrics(outMetrics);

        float density  = getResources().getDisplayMetrics().density;
        float dpHeight = outMetrics.heightPixels / density;
        float dpWidth  = outMetrics.widthPixels / density;


        Log.d(tag, "dpWidth is "+ dpWidth+ " height is "+ dpHeight+ "density is "+ density);*/

        Runnable r = () -> {
            String text = "This is a text";

            String translatedText = TranslateUtils.getTextTranslated(text, LanguageForTransApi.eng, LanguageForTransApi.vie);

            Log.d(tag, "trans text is " + translatedText);
        };


        new Thread(r).start();


        /*mWaitHandler.postDelayed(new Runnable() {

            @Override
            public void run() {

                //The following code will execute after the 5 seconds.

                try {
                    //Go to next page i.e, start the next activity.
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    //Let's Finish Splash Activity since we don't want to show this when user press back button.
                    finish();
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }
            }
        }, 2000);  // Give a 2 seconds delay.*/


        // todo check trained data exist

        if (Utils.checkTrainedDataExist()) {
            // todo: ra thông báo đã có data và chuyển đến Home

            Log.d(tag, "all data is done");

            Toast.makeText(this, "allData copied", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            //Let's Finish Splash Activity since we don't want to show this when user press back button.
            finish();

        } else {
            // thông báo hãy đợi cho đến khi hoàn tất copy data
            // thực hiện copy file từ asset đến thư mục trên thẻ nhớ

            // khi nào copy xong thì chuyển đến home activity
            try {

                Log.d(tag, "try to copy data");

                Utils.copyDataFromAssetToAppData();
                //Go to next page i.e, start the next activity.
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                //Let's Finish Splash Activity since we don't want to show this when user press back button.
                finish();

            } catch (Exception e) {
                Log.d(tag, e.getMessage());
            }
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //Remove all the callbacks otherwise navigation will execute even after activity is killed or closed.
        mWaitHandler.removeCallbacksAndMessages(null);
    }
}
