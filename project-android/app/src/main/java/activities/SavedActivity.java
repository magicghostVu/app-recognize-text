package activities;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.m.uet.apptranslate.R;


public class SavedActivity extends AppCompatActivity {
    private ImageButton button_home1;
    private ImageButton button_setting1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);



        button_home1 = (ImageButton) findViewById(R.id.button_home1);
        button_home1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SavedActivity.this,HomeActivity.class);
                startActivity(intent);

            }
        });
        button_setting1 = (ImageButton) findViewById(R.id.button_setting1);
        button_setting1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SavedActivity.this,SettingActivity.class);
                startActivity(intent);

            }
        });
    }
}
