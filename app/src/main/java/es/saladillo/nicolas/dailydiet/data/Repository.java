package es.saladillo.nicolas.dailydiet.data;

import androidx.lifecycle.LiveData;

import java.util.List;

import es.saladillo.nicolas.dailydiet.data.local.entities.Diet_Dish;
import es.saladillo.nicolas.dailydiet.data.local.entities.Dish;
import es.saladillo.nicolas.dailydiet.data.local.entities.Dish_Ingredient;
import es.saladillo.nicolas.dailydiet.data.local.entities.Ingredient;
import es.saladillo.nicolas.dailydiet.data.local.models.DishOfADiet;
import es.saladillo.nicolas.dailydiet.data.local.entities.Diet;

public interface Repository {
    // Diet functions
    LiveData<List<Diet>> getAllDiets();

    void insertDiet(Diet diet);
    void updateDiet(Diet diet);
    void deleteDiet(Diet diet);

    // Diet_Dish functions
    LiveData<List<DishOfADiet>> getAllDishesInADiet(int diet_id);

    void insertDiet_Dish(Diet_Dish diet_dish);
    void updateDiet_Dish(Diet_Dish diet_dish);
    void deleteDiet_Dish(Diet_Dish diet_dish);

    // Dish functions
    LiveData<List<Dish>> getAllDishes();

    void insertDish(Dish dish);
    void updateDish(Dish dish);
    void deleteDish(Dish dish);

    // Dish_Ingredient functions
    LiveData<List<Dish_Ingredient>> getAllIngredientsInADish(long dish_id);

    void insertDish_Ingredient(Dish_Ingredient dish_ingredient);
    void updateDish_Ingredient(Dish_Ingredient dish_ingredient);
    void deleteDish_Ingredient(Dish_Ingredient dish_ingredient);

    // Ingredient functions
    LiveData<List<Ingredient>> getAllIngredients();

    void insertIngredient(Ingredient ingredient);
    void updateIngredient(Ingredient ingredient);
    void deleteIngredient(Ingredient ingredient);
}
