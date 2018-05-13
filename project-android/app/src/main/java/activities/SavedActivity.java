package activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.m.uet.apptranslate.R;

import constants.Constants;
import language_support.LanguageSupported;


public class SavedActivity extends AppCompatActivity {
    private ImageButton button_home1;
    private ImageButton button_setting1;
    Spinner spinner1;

    String tag = "trans_act";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);

        spinner1 = findViewById(R.id.spinner1);


        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.country_name, android.R.layout.simple_spinner_item);


        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner1.setAdapter(arrayAdapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


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

        Log.d(tag, "text is " + textNeedTrans + " " + "lan is " + languageSupported);


    }
}
