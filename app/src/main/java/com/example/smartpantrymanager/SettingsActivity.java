
package com.example.smartpantrymanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(24, 24, 24, 24);

        TextView title = new TextView(this);
        title.setText("Settings");
        title.setTextSize(28);
        title.setGravity(Gravity.CENTER);

        TextView appName = new TextView(this);
        appName.setText("Smart Pantry Manager");
        appName.setTextSize(20);

        TextView description = new TextView(this);
        description.setText(
                "Manage your pantry and discover recipes "
                        + "using the ingredients you have.");

        Button backButton = new Button(this);
        backButton.setText("Back to Pantry");
        backButton.setOnClickListener(v -> finish());

        layout.addView(title);
        layout.addView(appName);
        layout.addView(description);
        layout.addView(backButton);

        setContentView(layout);
    }
}
