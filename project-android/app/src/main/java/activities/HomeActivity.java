package activities;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.m.uet.apptranslate.R;

import java.io.File;

import constants.Constants;
import language_support.LanguageSupported;
import mytask.GetTextFromBitMap;
import orc.man.OrcManager;
import utils.FileUtils;
import utils.Utils;


public class HomeActivity extends AppCompatActivity {
    private ImageButton button_setting;
    private ImageButton button_translate;
    private ImageButton button_image;
    ImageView iv;
    EditText editText;

    Spinner spinner;

    LanguageSupported currentLanguageSupported;


    private final int requestCodeChooseFile = 6384;


    String tag = "home_act";


    private Bitmap currentBitMap;

    private final int requestCodeReadPermission = 2;


    public void recognizeTextFromBitMap(View v) {

        // user chưa chọn ảnh
        if (currentBitMap == null) {
            Toast.makeText(this, "You have not selected ỉmg, select img first", Toast.LENGTH_SHORT).show();
        } else {
            GetTextFromBitMap taskGetText = new GetTextFromBitMap(this, currentLanguageSupported);
            taskGetText.execute(currentBitMap);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        currentLanguageSupported = LanguageSupported.vie;
        setContentView(R.layout.activity_home);

        editText = findViewById(R.id.edit_text);

        button_setting = findViewById(R.id.button_setting);
        button_setting.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
            startActivity(intent);
        });

        button_translate = findViewById(R.id.button_translate);


        button_translate.setOnClickListener(v -> {

            // todo: check điều kiện để bấm nút này

            String currentText = editText.getText().toString();
            if (currentText == null || currentText.equals("")) {
                Toast.makeText(this, "You have no text to translate", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(HomeActivity.this, SavedActivity.class);

                // truyền data text và ngôn ngữ ở đây
                //intent.put

                intent.putExtra(Constants.getKeyExtrasText(), currentText);

                intent.putExtra(Constants.getKeyLanExtras(), currentLanguageSupported.name());


                startActivity(intent);
            }
        });

        button_image = findViewById(R.id.button_image);
        iv = findViewById(R.id.image_View);

        Constants.initConstant();

        OrcManager.getInstance().init();

        //todo: spinner

        spinner = findViewById(R.id.spinner);


        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.country_name, android.R.layout.simple_spinner_item);


        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

                LanguageSupported languageSupportedSelect = LanguageSupported.fromPosition(position);


                currentLanguageSupported = languageSupportedSelect;

                Log.d(tag, "user select " + languageSupportedSelect.name());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //todo: add permission here

        boolean canReadDataFromSdCard = Utils.checkPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (!canReadDataFromSdCard) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                    requestCodeReadPermission);
        }


    }

    public void imageClick(View view) {
        /*Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECTED_PICTURE);*/
        showChooseFile();
    }


    // gắn hàm này vào nút chọn file
    private void showChooseFile() {


        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a Image file"),
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
                    Toast.makeText(this, "read permission granted", Toast.LENGTH_SHORT).show();
                }
                // không cho phép
                else {
                    //

                    Toast.makeText(this, "App can not read data from storage, so exit now", Toast.LENGTH_SHORT).show();

                    // todo: exit here
                    System.exit(0);

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


                    Log.d(tag, "file exist " + file.exists());
                    Log.d(tag, "name file is " + file.getName() + " ext file is " + extFile);


                    if (!Constants.getExtAllow().contains(extFile)) {
                        Toast.makeText(this, "File not supported", Toast.LENGTH_SHORT).show();
                    } else {
                        //todo: load bit map ở đây

                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
                        /*Drawable d = new BitmapDrawable(bitmap);
                        Log.d("selectFile", "file path is " + path);
                        Log.d("selectFile", "file if null" + (d == null));*/

                        //todo: hiện bit mp lên
                        iv.setImageBitmap(bitmap);
                        currentBitMap = bitmap;
                        //String text = OrcManager.getInstance().getDataFromBitMap(bitmap);

                        // todo: thực hiện async task ở đây đê lấy ra text

                        /*AsyncTask<Bitmap, Void, String> myTask = new GetTextFromBitMap(this);

                        myTask.execute(bitmap);*/

                        //Log.d(tag, "text is " + text);

                        //todo: set text to Text View


                    }


                } catch (Exception e) {

                }

                // load lên một bit map để xử lý tiếp setView vvv....
                break;
            }
        }
    }
}
