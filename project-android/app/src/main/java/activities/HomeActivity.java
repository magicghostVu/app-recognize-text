package activities;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.m.uet.apptranslate.R;

import java.io.File;

import constants.Constants;
import orc.man.OrcManager;
import utils.FileUtils;


public class HomeActivity extends AppCompatActivity {
    ActionBar actionBar;
    private ImageButton button_setting;
    private ImageButton button_saved;
    private ImageButton camera;


    private final int requestCodeChooseFile = 1001;


    String tag = "Home_Tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#673fb4")));

        button_setting = (ImageButton) findViewById(R.id.button_setting);
        button_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
                startActivity(intent);

            }
        });

        button_saved = (ImageButton) findViewById(R.id.button_saved);
        button_saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SavedActivity.class);
                startActivity(intent);

            }
        });


        Constants.intitConstant();

        OrcManager.getInstance().init();

    }


    // gắn hàm này vào nút chọn file
    private void showChooseFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    requestCodeChooseFile);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode) {
            case requestCodeChooseFile: {

                Uri uri = data.getData();

                Log.d(tag, "uri file is " + uri.toString());

                try {
                    String path = FileUtils.getPath(this, uri);
                    Log.d(tag, "File Path: " + path);

                    File file = new File(path);

                    //String fileName= file.getName();

                    String extFile = FileUtils.getExtensionFile(file);

                    if (!Constants.getExtAllow().contains(extFile)) {
                        Toast.makeText(this, "File not supported", Toast.LENGTH_SHORT);
                    } else {
                        //todo: load bit map ở đây

                        

                    }


                } catch (Exception e) {

                }

                // load lên một bit map để xử lý tiếp setView vvv....
                break;
            }
        }
    }
}
