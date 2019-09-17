package es.saladillo.nicolas.dailydiet.data.local.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "dishes_in_diet",primaryKeys = {"dd_diet_id", "dd_dish_id","day","type"},foreignKeys = {
        @ForeignKey(entity = Diet.class, parentColumns = "diet_id", childColumns = "dd_diet_id"),
        @ForeignKey(entity = Dish.class, parentColumns = "dish_id", childColumns = "dd_dish_id")
}
        ,indices = {
        @Index(value = "dd_diet_id", unique = true),
        @Index(value = "dd_dish_id", unique = true)
}
        )
public class Diet_Dish {
    @NonNull
    private long dd_diet_id;
    @NonNull
    private long dd_dish_id;
    @NonNull
    private String day;
    @NonNull
    private int type;

    public long getDd_diet_id() {
        return dd_diet_id;
    }

    public void setDd_diet_id(long dd_diet_id) {
        this.dd_diet_id = dd_diet_id;
    }

    public long getDd_dish_id() {
        return dd_dish_id;
    }

    public void setDd_dish_id(long dd_dish_id) {
        this.dd_dish_id = dd_dish_id;
    }

    @NonNull
    public String getDay() {
        return day;
    }

    public void setDay(@NonNull String day) {
        this.day = day;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
