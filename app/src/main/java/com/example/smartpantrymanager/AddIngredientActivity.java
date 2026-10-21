
package com.example.smartpantrymanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddIngredientActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(24, 24, 24, 24);

        TextView title = new TextView(this);
        title.setText("Add Ingredient");
        title.setTextSize(26);
        title.setGravity(Gravity.CENTER);

        EditText nameInput = new EditText(this);
        nameInput.setHint("Ingredient name");

        EditText quantityInput = new EditText(this);
        quantityInput.setHint("Quantity");
        quantityInput.setInputType(2);

        Button saveButton = new Button(this);
        saveButton.setText("Save Ingredient");

        layout.addView(title);
        layout.addView(nameInput);
        layout.addView(quantityInput);
        layout.addView(saveButton);

        setContentView(layout);
    }
}

