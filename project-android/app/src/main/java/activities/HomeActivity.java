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


public class HomeActivity extends AppCompatActivity {
    ActionBar actionBar;
    private ImageButton button_setting;
    private ImageButton button_saved;
    private ImageButton camera;
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
                Intent intent = new Intent(HomeActivity.this,SettingActivity.class);
                startActivity(intent);

            }
        });

        button_saved = (ImageButton) findViewById(R.id.button_saved);
        button_saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,SavedActivity.class);
                startActivity(intent);

            }
        });

    }
}
