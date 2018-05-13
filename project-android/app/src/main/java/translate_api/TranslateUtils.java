package translate_api;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import constants.Constants;
import utils.MyPair;

/**
 * Created by magic on 12/05/2018.
 */

public class TranslateUtils {


    static String encodeQueryParam(List<MyPair<String, String>> params) throws UnsupportedEncodingException {
        StringBuilder builder = new StringBuilder();

        boolean isF = true;

        for (MyPair<String, String> v : params) {

            if (isF) isF = false;
            else {
                builder.append("&");
            }

            builder.append(URLEncoder.encode(v.getKey(), "UTF-8"));
            builder.append("=");
            builder.append(URLEncoder.encode(v.getValue(), "UTF-8"));


        }


        Log.d(tag, "param is " + builder.toString());

        return builder.toString();

    }

    private static String tag = "TransApi";

    public static String getTextTranslated(String source, LanguageForTransApi lanSource,
                                           LanguageForTransApi lanTarget) {

        try {
            URL url = new URL(Constants.getUrlTransApi());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);


            List<MyPair<String, String>> params = new ArrayList<>();


            MyPair<String, String> q = new MyPair<>("q", source);

            MyPair<String, String> targetLan = new MyPair<>("target", lanTarget.getCode());

            MyPair<String, String> sourceLan = new MyPair<>("source", lanSource.getCode());

            MyPair<String, String> key = new MyPair<>("key", Constants.getApiKey());


            params.add(q);

            params.add(targetLan);

            params.add(sourceLan);

            params.add(key);

            OutputStream os = conn.getOutputStream();

            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(encodeQueryParam(params));

            writer.flush();
            writer.close();
            os.close();


            int responseCode = conn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                StringBuilder res = new StringBuilder();
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    res.append(line);
                }


                String finalRes = res.toString();

                Log.d(tag, "Response is " + finalRes);


                JSONObject jsonObject = new JSONObject(finalRes);


                String data = jsonObject.getJSONObject("data")
                        .getJSONArray("translations")
                        .getJSONObject(0)
                        .getString("translatedText");


                return data;

            } else {
                Log.d(tag, "err code is " + responseCode);
                return "";
            }


            //return null;
        } catch (Exception e) {


            Log.d(tag, "err while execute trans api " + Arrays.toString(e.getStackTrace()));


            e.printStackTrace();
            return null;
        }


    }
}
