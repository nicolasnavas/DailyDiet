package es.saladillo.nicolas.dailydiet.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import es.saladillo.nicolas.dailydiet.data.base.BaseDao;
import es.saladillo.nicolas.dailydiet.data.local.entities.Diet;
import es.saladillo.nicolas.dailydiet.data.local.entities.Dish;

@Dao
public interface DishDAO extends BaseDao<Dish> {
    @Query("SELECT * FROM dish")
    LiveData<List<Dish>> getAllDishes();

}
