package constants;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by magic on 05/05/2018.
 */

public class Constants {
    public static final String TAG = "appTrans";


    private static Set<String> extAllow;

    public static void intitConstant() {
        extAllow = new HashSet<>();
        extAllow.add("jpg");
        extAllow.add("png");
    }

    public static Set<String> getExtAllow() {
        return extAllow;
    }
}
