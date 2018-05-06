package utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import application.MyApp;

/**
 * Created by magic on 06/05/2018.
 */

public class Utils {

    private static String tag = "UTILS";

    public static boolean checkPermission(Context context, String permission) {
        int res = ContextCompat.checkSelfPermission(context, permission);
        return res == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean checkTrainedDataExist() {
        File folderAppData = MyApp.getInstance().getExternalFilesDir(null);

        File folderTessData = new File(folderAppData.getAbsolutePath() + "/tessdata");

        if (!folderAppData.exists()) return false;

        File[] allFileTrainedData = folderTessData.listFiles();
        Log.d(tag, "all file is " + Arrays.toString(allFileTrainedData));
        return allFileTrainedData.length > 0;

    }

    private static String getPathToTessDataAppFolder() {
        File folderAppData = MyApp.getInstance().getExternalFilesDir(null);
        File folderTessDataApp = new File(folderAppData.getAbsolutePath() + "/tessdata");
        if (!folderTessDataApp.exists()) {
            folderTessDataApp.mkdir();
        }
        return folderTessDataApp.getAbsolutePath();
        //return Paths.get(folderTessDataApp.getAbsolutePath());

    }


    public static void copyAnInputStreamToFile(InputStream data, String pathToFile) throws IOException {
        File file = new File(pathToFile);
        if (file.exists()) return;
        else {

            file.createNewFile();

            FileOutputStream fileOutputStream = new FileOutputStream(file);

            byte[] buffer = new byte[1024];

            int lenghtRead = data.read(buffer);
            while (lenghtRead != -1) {
                fileOutputStream.write(buffer, 0, lenghtRead);
                lenghtRead = data.read(buffer);
            }

            Log.d(tag, "File " + file.getName() + " copied");
        }
    }

    public static void copyDataFromAssetToAppData() throws IOException {
        //File folderTessData = MyApp.getInstance().getExternalFilesDir(null);
        //File[] allFileTrainedData = folderTessData.listFiles();

        AssetManager assetManager = MyApp.getInstance().getAssets();

        String[] allFileName = assetManager.list("");


        Predicate<String> filterFileName = s -> {
            return s.contains("traineddata");
        };

        List<String> allFileWillCopy = Arrays.stream(allFileName).filter(filterFileName).collect(Collectors.toList());


        Consumer<String> copyAFile = name -> {

            try {

                Log.d(tag, "file " + name + "is coping");
                InputStream inputStream = assetManager.open(name);

                String pathToCopy = getPathToTessDataAppFolder() + "/" + name;

                copyAnInputStreamToFile(inputStream, pathToCopy);

            } catch (Exception e) {
                Log.d(tag, "err while copy file ", e);
            }

        };

        Log.d(tag, "all file name is " + allFileWillCopy.toString());

        allFileWillCopy.forEach(copyAFile);


        //Files.

    }
}
