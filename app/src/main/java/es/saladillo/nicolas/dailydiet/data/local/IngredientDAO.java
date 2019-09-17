package es.saladillo.nicolas.dailydiet.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import es.saladillo.nicolas.dailydiet.data.base.BaseDao;
import es.saladillo.nicolas.dailydiet.data.local.entities.Ingredient;

@Dao
public interface IngredientDAO extends BaseDao<Ingredient> {
    @Query("SELECT * FROM ingredient")
    LiveData<List<Ingredient>> getAllIngredients();
}
