package es.saladillo.nicolas.dailydiet.ui.fragments.pantry;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.saladillo.nicolas.dailydiet.data.Repository;
import es.saladillo.nicolas.dailydiet.data.local.entities.Ingredient;

public class PantryViewModel extends ViewModel {
    private Repository repository;
    private LiveData<List<Ingredient>> storedIngredients;

    public PantryViewModel(Repository repository) {
        this.repository = repository;
        storedIngredients = repository.getAllIngredients();
    }

    public void updateIngredient(Ingredient ingredient){
        repository.updateIngredient(ingredient);
    }
    public void addIngredient(Ingredient ingredient){
        repository.insertIngredient(ingredient);
    }

    public LiveData<List<Ingredient>> getStoredIngredients() {
        return storedIngredients;
    }
}
