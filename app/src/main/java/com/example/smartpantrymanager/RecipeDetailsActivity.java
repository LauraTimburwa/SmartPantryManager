
package com.example.smartpantrymanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RecipeDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String name = getIntent().getStringExtra("recipeName");
        String instructions =
                getIntent().getStringExtra("instructions");
        String ingredients =
                getIntent().getStringExtra("ingredients");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(24, 24, 24, 24);

        TextView title = new TextView(this);
        title.setText(name == null ? "Recipe Details" : name);
        title.setTextSize(26);
        title.setGravity(Gravity.CENTER);

        TextView ingredientTitle = new TextView(this);
        ingredientTitle.setText("Ingredients");
        ingredientTitle.setTextSize(20);

        TextView ingredientText = new TextView(this);
        ingredientText.setText(
                ingredients == null ? "No ingredients listed" : ingredients);
        ingredientText.setTextSize(16);

        TextView instructionTitle = new TextView(this);
        instructionTitle.setText("Instructions");
        instructionTitle.setTextSize(20);

        TextView instructionText = new TextView(this);
        instructionText.setText(
                instructions == null
                        ? "No instructions available"
                        : instructions);
        instructionText.setTextSize(16);

        Button backButton = new Button(this);
        backButton.setText("Back to Recipes");
        backButton.setOnClickListener(v -> finish());

        layout.addView(title);
        layout.addView(ingredientTitle);
        layout.addView(ingredientText);
        layout.addView(instructionTitle);
        layout.addView(instructionText);
        layout.addView(backButton);

        setContentView(layout);
    }
}
