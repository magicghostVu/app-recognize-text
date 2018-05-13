package constants;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by magic on 05/05/2018.
 */

public class Constants {
    public static final String TAG = "appTrans";


    private static Set<String> extAllow;

    private static String apiKey = "AIzaSyCyW3ZrelqLmFzcG3wf7MDlzXXAFmKMNl8";

    private static String urlTransApi = "https://translation.googleapis.com/language/translate/v2";

    private static final String keyExtrasText = "keyExtrasText";

    private static final String keyLanExtras = "keyLanExtras";


    public static void initConstant() {
        extAllow = new HashSet<>();
        extAllow.add("jpg");
        extAllow.add("png");
    }

    public static Set<String> getExtAllow() {
        return extAllow;
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getUrlTransApi() {
        return urlTransApi;
    }

    public static String getKeyExtrasText() {
        return keyExtrasText;
    }

    public static String getKeyLanExtras() {
        return keyLanExtras;
    }
}
