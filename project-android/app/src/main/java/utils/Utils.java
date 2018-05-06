package utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * Created by magic on 06/05/2018.
 */

public class Utils {
    public static boolean checkPermission(Context context, String permission){
        int res = ContextCompat.checkSelfPermission(context, permission);
        return res == PackageManager.PERMISSION_GRANTED;
    }
}
