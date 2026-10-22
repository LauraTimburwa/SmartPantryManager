
package com.example.smartpantrymanager;

import java.util.Arrays;
import java.util.List;

public class Recipe {

    private String name;
    private String instructions;
    private List<String> ingredients;

    public Recipe(String name, String instructions,
                  String... ingredients) {
        this.name = name;
        this.instructions = instructions;
        this.ingredients = Arrays.asList(ingredients);
    }

    public String getName() {
        return name;
    }

    public String getInstructions() {
        return instructions;
    }

    public List<String> getIngredients() {
        return ingredients;
    }
}
