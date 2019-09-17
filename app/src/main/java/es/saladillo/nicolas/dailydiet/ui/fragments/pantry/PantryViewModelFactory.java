package es.saladillo.nicolas.dailydiet.ui.fragments.pantry;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import es.saladillo.nicolas.dailydiet.data.Repository;

public class PantryViewModelFactory implements ViewModelProvider.Factory {

    private final Repository repository;

    public PantryViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PantryViewModel(repository);
    }
}
