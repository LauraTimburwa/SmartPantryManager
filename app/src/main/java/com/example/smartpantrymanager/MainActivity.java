
package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private PantryDatabaseHelper databaseHelper;
    private LinearLayout layout;
    private LinearLayout ingredientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseHelper = new PantryDatabaseHelper(this);

        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(24, 24, 24, 24);

        TextView title = new TextView(this);
        title.setText("My Pantry");
        title.setTextSize(28);
        title.setGravity(Gravity.CENTER);

        Button addButton = new Button(this);
        addButton.setText("Add Ingredient");

        Button recipesButton = new Button(this);
        recipesButton.setText("Suggested Recipes");

        Button settingsButton = new Button(this);
        settingsButton.setText("Settings");

        TextView listTitle = new TextView(this);
        listTitle.setText("Your Ingredients");
        listTitle.setTextSize(20);

        ingredientList = new LinearLayout(this);
        ingredientList.setOrientation(LinearLayout.VERTICAL);

        layout.addView(title);
        layout.addView(addButton);
        layout.addView(recipesButton);
        layout.addView(settingsButton);
        layout.addView(listTitle);
        layout.addView(ingredientList);

        settingsButton.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    SettingsActivity.class
            );
            startActivity(intent);
        });

        setContentView(layout);

        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    AddIngredientActivity.class
            );
            startActivity(intent);
        });

        recipesButton.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    RecipeActivity.class
            );
            startActivity(intent);
        });
        );
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (databaseHelper != null && ingredientList != null) {
            displayIngredients();
        }
    }

    private void displayIngredients() {
        ingredientList.removeAllViews();

        ArrayList<String> ingredients =
                databaseHelper.getAllIngredients();

        if (ingredients.isEmpty()) {
            TextView emptyMessage = new TextView(this);
            emptyMessage.setText("Your pantry is empty. Add an ingredient!");
            ingredientList.addView(emptyMessage);
            return;
        }

        for (String ingredient : ingredients) {
            TextView item = new TextView(this);
            item.setText(ingredient);
            item.setTextSize(16);
            item.setPadding(8, 12, 8, 12);
            ingredientList.addView(item);
        }
    }
}
