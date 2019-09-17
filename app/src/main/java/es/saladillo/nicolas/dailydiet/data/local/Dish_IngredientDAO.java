package es.saladillo.nicolas.dailydiet.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import es.saladillo.nicolas.dailydiet.data.base.BaseDao;
import es.saladillo.nicolas.dailydiet.data.local.entities.Diet;
import es.saladillo.nicolas.dailydiet.data.local.entities.Dish_Ingredient;

@Dao
public interface Dish_IngredientDAO extends BaseDao<Dish_Ingredient> {
    @Query("SELECT * FROM ingredients_of_a_dish WHERE di_dish_id = :dish_id")
    LiveData<List<Dish_Ingredient>> getAllIngredientsInADish(long dish_id);
}
