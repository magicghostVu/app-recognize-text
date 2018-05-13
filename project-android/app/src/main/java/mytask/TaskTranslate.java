package mytask;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.m.uet.apptranslate.R;

import activities.SavedActivity;
import translate_api.TranslateUtils;

/**
 * Created by magic on 13/05/2018.
 */

public class TaskTranslate extends AsyncTask<String, Void, String> {


    public TaskTranslate(SavedActivity parentContext) {
        this.parentContext = parentContext;
    }

    private SavedActivity parentContext;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(parentContext, "Text is translating", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        TextView tvDisplayRes = parentContext.findViewById(R.id.text_View2);
        tvDisplayRes.setText(s);
    }

    @Override
    protected String doInBackground(String... strings) {

        String res = TranslateUtils.getTextTranslated(strings[0], parentContext.getLanguageFromHome(),
                parentContext.getTargetLanguage());


        return res;
    }
}
