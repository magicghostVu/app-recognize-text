package application;

import android.app.Application;
import android.util.Log;

import constants.Constants;

/**
 * Created by magic on 05/05/2018.
 */

public class MyApp extends Application {

    private static MyApp instance = null;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        String dataPath = this.getExternalFilesDir(null).getAbsolutePath();

        Log.d(Constants.TAG, "data path is " + dataPath);

    }

    public static MyApp getInstance() {
        return instance;
    }

    //private void copyData
}
