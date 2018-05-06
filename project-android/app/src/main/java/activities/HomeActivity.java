package activities;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.m.uet.apptranslate.R;

import java.io.File;

import constants.Constants;
import orc.man.OrcManager;
import utils.FileUtils;
import utils.Utils;


public class HomeActivity extends AppCompatActivity {
    private ImageButton button_setting;
    private ImageButton button_saved;
    private ImageButton button_image;
    ImageView iv;

    private final int requestCodeChooseFile = 1001;
    private static final int SELECTED_PICTURE = 1;


    String tag = "Home_Tag";


    private final int requestCodeReadPermission = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


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

        button_image = (ImageButton) findViewById(R.id.button_image);
        iv = (ImageView) findViewById(R.id.imageView);

        Constants.intitConstant();

        OrcManager.getInstance().init();

        //todo: add permission here

        boolean canReadDataFromSdCard = Utils.checkPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (!canReadDataFromSdCard) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                    requestCodeReadPermission);
        }


    }

    public void imageClick(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECTED_PICTURE);
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        switch (requestCode) {
            case requestCodeReadPermission: {

                // nếu cho phép
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "read permission granted", Toast.LENGTH_SHORT);
                }
                // không cho phép
                else {
                    //

                    Toast.makeText(this, "App can not read data from storage, so exit now", Toast.LENGTH_SHORT).show();

                    // todo: exit here

                }


                break;
            }
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
                        Toast.makeText(this, "File not supported", Toast.LENGTH_SHORT).show();
                    } else {
                        //todo: load bit map ở đây


                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = Bitmap.Config.ARGB_8888;


                        Bitmap bitmap = BitmapFactory.decodeFile(path, options);

                        //todo: hiện bit mp lên


                    }


                } catch (Exception e) {

                }

                // load lên một bit map để xử lý tiếp setView vvv....
                break;
            }
        }
    }
}
