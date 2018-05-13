package activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.m.uet.apptranslate.R;

import constants.Constants;
import language_support.LanguageSupported;
import mytask.TaskTranslate;
import translate_api.LanguageForTransApi;


public class SavedActivity extends AppCompatActivity {
    private ImageButton button_home1;
    private ImageButton button_setting1;
    Spinner spinner1;

    String tag = "trans_act";

    LanguageForTransApi languageFromHome;

    LanguageForTransApi targetLanguage;

    public ImageButton getButton_home1() {
        return button_home1;
    }

    public LanguageForTransApi getLanguageFromHome() {
        return languageFromHome;
    }

    public LanguageForTransApi getTargetLanguage() {
        return targetLanguage;
    }

    public void translate(View v) {

        TextView t = findViewById(R.id.text_View1);
        CharSequence c = t.getText();

        if (c.toString().equals("")) {
            Toast.makeText(this, "Nothings to translate", Toast.LENGTH_SHORT).show();
        }
        // thực hiện dịch ở đây
        else {
            TaskTranslate taskTranslate = new TaskTranslate(this);
            taskTranslate.execute(c.toString());
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);

        spinner1 = findViewById(R.id.spinner1);


        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.country_name, android.R.layout.simple_spinner_item);


        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        SavedActivity thisAct = this;

        spinner1.setAdapter(arrayAdapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LanguageSupported selected = LanguageSupported.fromPosition(position);

                LanguageForTransApi targetLanguage = LanguageForTransApi.valueOf(selected.name());
                thisAct.targetLanguage = targetLanguage;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        button_home1 = findViewById(R.id.button_home1);
        button_home1.setOnClickListener(v -> {
            Intent intent = new Intent(SavedActivity.this, HomeActivity.class);
            startActivity(intent);

        });
        button_setting1 = findViewById(R.id.button_setting1);
        button_setting1.setOnClickListener(v -> {
            Intent intent = new Intent(SavedActivity.this, SettingActivity.class);
            startActivity(intent);

        });


        Intent beforeIntent = this.getIntent();

        String textNeedTrans = beforeIntent.getStringExtra(Constants.getKeyExtrasText());

        LanguageSupported languageSupported = LanguageSupported.valueOf(beforeIntent.getStringExtra(Constants.getKeyLanExtras()));

        languageFromHome = LanguageForTransApi.valueOf(languageSupported.name());

        Log.d(tag, "text is " + textNeedTrans + " " + "lan is " + languageSupported);


        TextView textViewData = findViewById(R.id.text_View1);
        textViewData.setText(textNeedTrans);
        TextView textViewLanguage = findViewById(R.id.textView_lan);
        textViewLanguage.setText(languageSupported.getNameLanguage());
    }
}
