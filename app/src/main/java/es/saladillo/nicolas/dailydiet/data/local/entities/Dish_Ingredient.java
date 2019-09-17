package es.saladillo.nicolas.dailydiet.data.local.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "ingredients_of_a_dish",primaryKeys = {"di_dish_id", "di_ingredient_id"},foreignKeys = {
        @ForeignKey(entity = Dish.class, parentColumns = "dish_id", childColumns = "di_dish_id"),
        @ForeignKey(entity = Ingredient.class, parentColumns = "ingredient_id", childColumns = "di_ingredient_id")
}
        ,indices = {
        @Index(value = "di_dish_id", unique = true),
        @Index(value = "di_ingredient_id", unique = true)
}
)
public class Dish_Ingredient {
    @NonNull
    private long di_dish_id;
    @NonNull
    private long di_ingredient_id;
    private int  ammount;

    public long getDi_dish_id() {
        return di_dish_id;
    }

    public void setDi_dish_id(long di_dish_id) {
        this.di_dish_id = di_dish_id;
    }

    public long getDi_ingredient_id() {
        return di_ingredient_id;
    }

    public void setDi_ingredient_id(long di_ingredient_id) {
        this.di_ingredient_id = di_ingredient_id;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }
}
