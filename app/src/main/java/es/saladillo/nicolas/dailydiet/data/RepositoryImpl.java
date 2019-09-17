package es.saladillo.nicolas.dailydiet.data;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import es.saladillo.nicolas.dailydiet.data.local.DietDAO;
import es.saladillo.nicolas.dailydiet.data.local.Diet_DishDAO;
import es.saladillo.nicolas.dailydiet.data.local.DishDAO;
import es.saladillo.nicolas.dailydiet.data.local.Dish_IngredientDAO;
import es.saladillo.nicolas.dailydiet.data.local.IngredientDAO;
import es.saladillo.nicolas.dailydiet.data.local.entities.Diet;
import es.saladillo.nicolas.dailydiet.data.local.entities.Diet_Dish;
import es.saladillo.nicolas.dailydiet.data.local.entities.Dish;
import es.saladillo.nicolas.dailydiet.data.local.entities.Dish_Ingredient;
import es.saladillo.nicolas.dailydiet.data.local.entities.Ingredient;
import es.saladillo.nicolas.dailydiet.data.local.models.DishOfADiet;

public class RepositoryImpl implements Repository {

    private final DietDAO dietDAO;
    private final Diet_DishDAO diet_dishDAO;
    private final DishDAO dishDAO;
    private final Dish_IngredientDAO dish_ingredientDAO;
    private final IngredientDAO ingredientDAO;

    public RepositoryImpl(DietDAO dietDAO, Diet_DishDAO diet_dishDAO, DishDAO dishDAO, Dish_IngredientDAO dish_ingredientDAO, IngredientDAO ingredientDAO) {
        this.dietDAO = dietDAO;
        this.diet_dishDAO = diet_dishDAO;
        this.dishDAO = dishDAO;
        this.dish_ingredientDAO = dish_ingredientDAO;
        this.ingredientDAO = ingredientDAO;
    }

    // Diet functions
    @Override
    public LiveData<List<Diet>> getAllDiets() {
        // Al ser una consulta que retorna un livedata Room la ejecuta directamente en un hilo secundario
        return dietDAO.getAllDiets();
    }

    @Override
    public void insertDiet(final Diet diet) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> dietDAO.insert(diet));
    }
    @Override
    public void updateDiet(Diet diet) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> dietDAO.update(diet));
    }
    @Override
    public void deleteDiet(Diet diet) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> dietDAO.delete(diet));
    }

    // Dish_Diet function
    @Override
    public LiveData<List<DishOfADiet>> getAllDishesInADiet(int diet_id) {
        return diet_dishDAO.getAllDishesInADiet(diet_id);
    }

    @Override
    public void insertDiet_Dish(Diet_Dish diet_dish) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> diet_dishDAO.insert(diet_dish));
    }
    @Override
    public void updateDiet_Dish(Diet_Dish diet_dish) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> diet_dishDAO.update(diet_dish));
    }
    @Override
    public void deleteDiet_Dish(Diet_Dish diet_dish) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> diet_dishDAO.delete(diet_dish));
    }

    // Dishes functions
    @Override
    public LiveData<List<Dish>> getAllDishes() {
        return dishDAO.getAllDishes();
    }

    @Override
    public void insertDish(Dish dish) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> dishDAO.insert(dish));
    }
    @Override
    public void updateDish(Dish dish) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> dishDAO.insert(dish));
    }
    @Override
    public void deleteDish(Dish dish) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> dishDAO.insert(dish));
    }

    // Dish_Ingredient function
    @Override
    public LiveData<List<Dish_Ingredient>> getAllIngredientsInADish(long dish_id) {
        return dish_ingredientDAO.getAllIngredientsInADish(dish_id);
    }

    @Override
    public void insertDish_Ingredient(Dish_Ingredient dish_ingredient) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> dish_ingredientDAO.insert(dish_ingredient));
    }
    @Override
    public void updateDish_Ingredient(Dish_Ingredient dish_ingredient) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> dish_ingredientDAO.update(dish_ingredient));
    }
    @Override
    public void deleteDish_Ingredient(Dish_Ingredient dish_ingredient) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> dish_ingredientDAO.delete(dish_ingredient));
    }

    // Ingredient function
    @Override
    public LiveData<List<Ingredient>> getAllIngredients() {
        return ingredientDAO.getAllIngredients();
    }

    @Override
    public void insertIngredient(Ingredient ingredient) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> ingredientDAO.insert(ingredient));
    }
    @Override
    public void updateIngredient(Ingredient ingredient) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> ingredientDAO.update(ingredient));
    }
    @Override
    public void deleteIngredient(Ingredient ingredient) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> ingredientDAO.delete(ingredient));
    }
}
