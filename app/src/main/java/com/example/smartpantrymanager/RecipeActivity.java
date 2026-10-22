
package com.example.smartpantrymanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RecipeActivity extends Activity {

    private final List<Recipe> recipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addRecipes();

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(24, 24, 24, 24);

        TextView title = new TextView(this);
        title.setText("Suggested Recipes");
        title.setTextSize(26);
        title.setGravity(Gravity.CENTER);
        layout.addView(title);

        PantryDatabaseHelper db = new PantryDatabaseHelper(this);
        ArrayList<String> pantryItems = db.getAllIngredients();
        List<String> pantryNames = new ArrayList<>();

        for (String item : pantryItems) {
            String name = item.split(" - Quantity:")[0]
                    .trim().toLowerCase(Locale.ROOT);
            pantryNames.add(name);
        }

        int matches = 0;

        for (Recipe recipe : recipes) {
            boolean hasAllIngredients = true;

            for (String required : recipe.getIngredients()) {
                if (!pantryNames.contains(
                        required.toLowerCase(Locale.ROOT))) {
                    hasAllIngredients = false;
                    break;
                }
            }

            if (hasAllIngredients) {
                matches++;

                Button recipeButton = new Button(this);
                recipeButton.setText(recipe.getName());

                recipeButton.setOnClickListener(v -> {
                    Intent intent = new Intent(
                            RecipeActivity.this,
                            RecipeDetailsActivity.class);
                    intent.putExtra("recipeName", recipe.getName());
                    intent.putExtra(
                            "instructions", recipe.getInstructions());
                    intent.putExtra(
                            "ingredients",
                            String.join(", ", recipe.getIngredients()));
                    startActivity(intent);
                });

                layout.addView(recipeButton);
            }
        }

        if (matches == 0) {
            TextView message = new TextView(this);
            message.setText(
                    "No recipes match your pantry yet. "
                            + "Add ingredients to find recipes.");
            layout.addView(message);
        }

        Button backButton = new Button(this);
        backButton.setText("Back to Pantry");
        backButton.setOnClickListener(v -> finish());
        layout.addView(backButton);

        setContentView(layout);
    }

    private void addRecipes() {
        recipes.add(new Recipe("Rice and Beans",
                "Cook the rice. Heat the beans and serve together.",
                "rice", "beans"));

        recipes.add(new Recipe("Egg Sandwich",
                "Cook the eggs and place them in bread.",
                "eggs", "bread"));

        recipes.add(new Recipe("Tomato Pasta",
                "Cook pasta and mix with chopped tomatoes.",
                "pasta", "tomato"));

        recipes.add(new Recipe("Potato Salad",
                "Boil potatoes, allow to cool and mix with mayonnaise.",
                "potato", "mayonnaise"));

        recipes.add(new Recipe("Omelette",
                "Beat the eggs and cook in a pan.",
                "eggs", "onion"));

        recipes.add(new Recipe("Chicken and Rice",
                "Cook the chicken thoroughly and serve with rice.",
                "chicken", "rice"));

        recipes.add(new Recipe("Peanut Butter Sandwich",
                "Spread peanut butter on bread.",
                "bread", "peanut butter"));

        recipes.add(new Recipe("Banana Smoothie",
                "Blend banana and milk until smooth.",
                "banana", "milk"));

        recipes.add(new Recipe("Corn Salad",
                "Mix the cooked corn with chopped tomato.",
                "corn", "tomato"));

        recipes.add(new Recipe("Cheese Toast",
                "Place cheese on bread and toast until melted.",
                "bread", "cheese"));

        recipes.add(new Recipe("Garlic Pasta",
                "Cook pasta and mix with garlic.",
                "pasta", "garlic"));

        recipes.add(new Recipe("Apple Salad",
                "Wash and chop the apples before serving.",
                "apple", "lettuce"));

        recipes.add(new Recipe("Beef and Potatoes",
                "Cook the beef thoroughly and serve with boiled potatoes.",
                "beef", "potato"));

        recipes.add(new Recipe("Milk Porridge",
                "Cook the oats with milk until soft.",
                "oats", "milk"));

        recipes.add(new Recipe("Tomato Omelette",
                "Cook beaten eggs with chopped tomato.",
                "eggs", "tomato"));
    }
}
