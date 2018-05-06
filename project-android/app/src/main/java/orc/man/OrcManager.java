package orc.man;

import android.util.Log;

import com.googlecode.tesseract.android.TessBaseAPI;

import application.MyApp;
import constants.Constants;

/**
 * Created by magic on 05/05/2018.
 */

public class OrcManager {

    private TessBaseAPI api;

    private static OrcManager instance = new OrcManager();

    public OrcManager() {

    }

    public void init() {
        api = new TessBaseAPI();
        String dataPath = MyApp.getInstance().getExternalFilesDir(null).getAbsolutePath();

        Log.d(Constants.TAG, "datapath is " + dataPath);
        api.init(dataPath, "vie");
    }


    public static OrcManager getInstance() {
        return instance;
    }
}
