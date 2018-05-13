package mytask;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import activities.HomeActivity;
import utils.Utils;

/**
 * Created by magic on 13/05/2018.
 */

public class TaskCopyFile extends AsyncTask<Void, Void, Void> {

    private String tag = "TaskCopyFile";

    private Activity parentAct;


    public TaskCopyFile(Activity parentAct) {
        this.parentAct = parentAct;
    }


    // thông báo đang copy file
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        Toast.makeText(parentAct, "Start copy resource data to target", Toast.LENGTH_SHORT).show();

    }


    // chuyển sang home
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //
        Intent intent = new Intent(parentAct.getApplicationContext(), HomeActivity.class);
        parentAct.startActivity(intent);
        parentAct.finish();

    }

    // thực hiện copy file
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Log.d(tag, "try to copy data");

            Utils.copyDataFromAssetToAppData();
        } catch (Exception e) {
            Log.d(tag, "err while copy file " + e);
            e.printStackTrace();
        }
        return null;
    }
}
