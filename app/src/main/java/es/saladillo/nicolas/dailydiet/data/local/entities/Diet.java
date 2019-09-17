package es.saladillo.nicolas.dailydiet.data.local.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "diet", indices = {@Index(value = "diet_id", unique = true)})
public class Diet {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    private long diet_id;
    private String name;

    public long getDiet_id() {
        return diet_id;
    }

    public void setDiet_id(long diet_id) {
        this.diet_id = diet_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
