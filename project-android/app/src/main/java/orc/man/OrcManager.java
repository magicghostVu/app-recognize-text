package orc.man;

import android.graphics.Bitmap;
import android.util.Log;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.util.HashMap;
import java.util.Map;

import application.MyApp;
import constants.Constants;
import language_support.LanguageSupported;

/**
 * Created by magic on 05/05/2018.
 */

public class OrcManager {


    private Map<LanguageSupported, TessBaseAPI> mapTessApi;

    private TessBaseAPI api;

    private static OrcManager instance = new OrcManager();

    public OrcManager() {

    }

    public void init() {
        api = new TessBaseAPI();
        String dataPath = MyApp.getInstance().getExternalFilesDir(null).getAbsolutePath();

        Log.d(Constants.TAG, "datapath is " + dataPath);
        api.init(dataPath, "vie");


        mapTessApi = new HashMap<>();

        for (LanguageSupported languageSupported : LanguageSupported.values()) {
            TessBaseAPI api = new TessBaseAPI();
            api.init(dataPath, languageSupported.name());
            mapTessApi.put(languageSupported, api);
        }

    }


    public String getDataFromBitMap(Bitmap bitmap, LanguageSupported language) {
        TessBaseAPI api= mapTessApi.get(language);
        api.setImage(bitmap);
        return api.getUTF8Text();
    }


    public static OrcManager getInstance() {
        return instance;
    }

    public String getDataFromBitMap(Bitmap bitmap) {
        api.setImage(bitmap);
        return api.getUTF8Text();
    }
}
