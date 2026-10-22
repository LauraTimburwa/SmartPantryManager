
package com.example.smartpantrymanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AddIngredientActivity extends Activity {

    private PantryDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseHelper = new PantryDatabaseHelper(this);

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

        saveButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            String quantityText =
                    quantityInput.getText().toString().trim();

            if (name.isEmpty() || quantityText.isEmpty()) {
                Toast.makeText(this,
                        "Please fill in all fields",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int quantity = Integer.parseInt(quantityText);

                if (quantity <= 0) {
                    Toast.makeText(this,
                            "Quantity must be greater than zero",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean saved =
                        databaseHelper.addIngredient(name, quantity);

                if (saved) {
                    Toast.makeText(this,
                            "Ingredient saved successfully!",
                            Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this,
                            "Could not save ingredient",
                            Toast.LENGTH_SHORT).show();
                }

            } catch (NumberFormatException e) {
                Toast.makeText(this,
                        "Enter a valid whole number",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}


