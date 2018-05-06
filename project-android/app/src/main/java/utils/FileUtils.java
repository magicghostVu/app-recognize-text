package utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.io.File;
import java.net.URISyntaxException;

/**
 * Created by magic on 05/05/2018.
 */

public class FileUtils {
    public static String getPath(Context context, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {"_data"};
            Cursor cursor = null;

            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    // lấy đuôi file
    public static String getExtensionFile(File f) {
        if (!f.exists()) return null;

        String name = f.getName();

        if (!name.contains(".")) return "";

        try {
            int i = name.lastIndexOf('.');
            return name.substring(i + 1);
        } catch (Exception e) {
            return null;
        }
    }
}
