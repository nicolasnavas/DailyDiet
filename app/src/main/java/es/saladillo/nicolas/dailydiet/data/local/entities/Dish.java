package es.saladillo.nicolas.dailydiet.data.local.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "dish", indices = {@Index(value = "dish_id", unique = true)})
public class Dish {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    private long dish_id;
    private String name;
    private String image;

    public long getDish_id() {
        return dish_id;
    }

    public void setDish_id(long dish_id) {
        this.dish_id = dish_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
