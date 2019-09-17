package es.saladillo.nicolas.dailydiet.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import es.saladillo.nicolas.dailydiet.data.local.entities.Diet;
import es.saladillo.nicolas.dailydiet.data.local.entities.Diet_Dish;
import es.saladillo.nicolas.dailydiet.data.local.entities.Dish_Ingredient;
import es.saladillo.nicolas.dailydiet.data.local.entities.Ingredient;
import es.saladillo.nicolas.dailydiet.data.local.entities.Dish;

@Database(entities = {Diet.class, Diet_Dish.class, Ingredient.class, Dish.class, Dish_Ingredient.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "daily_diet.db";

    public abstract DietDAO dietDAO();
    public abstract Diet_DishDAO diet_dishDAO();
    public abstract DishDAO dishDAO();
    public abstract Dish_IngredientDAO dish_ingredientDAO();
    public abstract IngredientDAO ingredientDAO();

    private static volatile AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }

}
