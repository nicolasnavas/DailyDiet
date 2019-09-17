package es.saladillo.nicolas.dailydiet.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import es.saladillo.nicolas.dailydiet.data.base.BaseDao;
import es.saladillo.nicolas.dailydiet.data.local.entities.Diet;

@Dao
public interface DietDAO extends BaseDao<Diet> {
    @Query("SELECT * FROM diet")
    LiveData<List<Diet>> getAllDiets();
}
