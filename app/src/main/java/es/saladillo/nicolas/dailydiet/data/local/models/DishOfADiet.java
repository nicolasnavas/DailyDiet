package es.saladillo.nicolas.dailydiet.data.local.models;

import androidx.room.ColumnInfo;

public class DishOfADiet {
    @ColumnInfo(name="id")
    private long dish_id;
    @ColumnInfo(name="day")
    private String day;
    @ColumnInfo(name="type")
    private int type;

    public long getDish_id() {
        return dish_id;
    }

    public void setDish_id(long dish_id) {
        this.dish_id = dish_id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
