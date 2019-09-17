package es.saladillo.nicolas.dailydiet.data.base;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

public interface BaseDao<M> {
    @Insert
    long insert(M model);

    @Update
    int update(M... model);

    @Delete
    int delete(M... model);
}
