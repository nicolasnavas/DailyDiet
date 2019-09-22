package es.saladillo.nicolas.dailydiet.data.local.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "ingredient", indices = {@Index(value = "name", unique = true)})
public class Ingredient {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    private long ingredient_id;
    private String name;
    private String image;
    private int quantity;
    private int minimum;

    public long getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(long ingredient_id) {
        this.ingredient_id = ingredient_id;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }
}
