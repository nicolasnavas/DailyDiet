package es.saladillo.nicolas.dailydiet.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import es.saladillo.nicolas.dailydiet.data.base.BaseDao;
import es.saladillo.nicolas.dailydiet.data.local.entities.Diet_Dish;
import es.saladillo.nicolas.dailydiet.data.local.models.DishOfADiet;

@Dao
public interface Diet_DishDAO extends BaseDao<Diet_Dish> {
    @Query("SELECT dd_dish_id as id,day,type FROM dishes_in_diet WHERE dd_diet_id = :diet_id")
    LiveData<List<DishOfADiet>> getAllDishesInADiet(int diet_id);
}

