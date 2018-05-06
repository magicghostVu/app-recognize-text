package activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.m.uet.apptranslate.R;


public class SettingActivity extends AppCompatActivity {

    private ImageButton button_home2;
    private ImageButton button_saved2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);



        button_home2 = (ImageButton) findViewById(R.id.button_home2);
        button_home2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        button_saved2 = (ImageButton) findViewById(R.id.button_saved2);
        button_saved2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this,SavedActivity.class);
                startActivity(intent);

            }
        });
}

}
