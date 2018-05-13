package mytask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import language_support.LanguageSupported;
import orc.man.OrcManager;

import com.m.uet.apptranslate.R;

/**
 * Created by magic on 06/05/2018.
 */

public class GetTextFromBitMap extends AsyncTask<Bitmap, Void, String> {


    private String tag = "myTask";

    private LanguageSupported languageSupported;


    public GetTextFromBitMap(Activity parentContext, LanguageSupported languageSupported) {
        this.parentContext = parentContext;
        this.languageSupported = languageSupported;
    }

    private Activity parentContext;

    //private Bitmap data;

    public Activity getParentContext() {
        return parentContext;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();


        Log.d(tag, "Image is processing");

        Toast.makeText(parentContext, "Img is being processed", Toast.LENGTH_SHORT).show();


        // todo: làm cho nút chọn ảnh không bấm được nữa


    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Log.d(tag, "img had been processed");

        Toast.makeText(parentContext, "Img had been processed", Toast.LENGTH_SHORT).show();


        EditText editText = getParentContext().findViewById(R.id.edit_text);

        editText.setText(s);

        // todo: bật nút chọn ảnh trở lại

    }

    @Override
    protected String doInBackground(Bitmap[] bitmaps) {
        Bitmap data = bitmaps[0];
        return OrcManager.getInstance().getDataFromBitMap(data, languageSupported);
        //return null;
    }
}
