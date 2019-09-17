package es.saladillo.nicolas.dailydiet.ui.fragments.pantry;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import es.saladillo.nicolas.dailydiet.data.RepositoryImpl;
import es.saladillo.nicolas.dailydiet.data.local.AppDatabase;
import es.saladillo.nicolas.dailydiet.data.local.DietDAO;
import es.saladillo.nicolas.dailydiet.data.local.Diet_DishDAO;
import es.saladillo.nicolas.dailydiet.data.local.DishDAO;
import es.saladillo.nicolas.dailydiet.data.local.Dish_IngredientDAO;
import es.saladillo.nicolas.dailydiet.data.local.IngredientDAO;
import es.saladillo.nicolas.dailydiet.data.local.entities.Ingredient;
import es.saladillo.nicolas.dailydiet.databinding.PantryFragmentBinding;
import es.saladillo.nicolas.dailydiet.utilities.LayoutLeaveBehindCallback;

public class Pantry extends Fragment {

    private PantryViewModel vm;
    private RepositoryImpl repository;
    private PantryAdapter pantryAdapter;
    private PantryFragmentBinding pb;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        pb = PantryFragmentBinding.inflate(inflater,container,false);
        return pb.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        repositoryCreation();
        vm = ViewModelProviders.of(getActivity(),new PantryViewModelFactory(repository)).get(PantryViewModel.class);
        setupRecyclerView();
    }


    private void repositoryCreation() {
        DietDAO dietDAO = AppDatabase.getInstance(getContext()).dietDAO();
        DishDAO dishDAO = AppDatabase.getInstance(getContext()).dishDAO();
        Diet_DishDAO diet_dishDAO = AppDatabase.getInstance(getContext()).diet_dishDAO();
        IngredientDAO ingredientDAO = AppDatabase.getInstance(getContext()).ingredientDAO();
        Dish_IngredientDAO dish_ingredientDAO = AppDatabase.getInstance(getContext()).dish_ingredientDAO();

        repository = new RepositoryImpl(dietDAO,diet_dishDAO,dishDAO,dish_ingredientDAO,ingredientDAO);
    }

    private void setupRecyclerView() {
        GridLayoutManager gridLayoutManager;

        pantryAdapter = new PantryAdapter(new PlusMinusButtonsListener() {
            @Override
            public void onPlusClicked(Ingredient ingredient) {
                ingredient.setQuantity(ingredient.getQuantity() + 1);
                vm.updateIngredient(ingredient);
            }

            @Override
            public void onMinusClicked(Ingredient ingredient) {
                if(ingredient.getQuantity() >= 1){
                    ingredient.setQuantity(ingredient.getQuantity() - 1);
                    vm.updateIngredient(ingredient);
                }
            }
        });

        gridLayoutManager = new GridLayoutManager(requireContext(), 1);

        pb.listIngredients.setHasFixedSize(true);
        pb.listIngredients.setLayoutManager(gridLayoutManager);
        pb.listIngredients.setItemAnimator(new DefaultItemAnimator());
        pb.listIngredients.setAdapter(pantryAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new LayoutLeaveBehindCallback() {
            @Override
            protected View getForegroundView(RecyclerView.ViewHolder viewHolder) {
                return ((PantryAdapter.ViewHolder) viewHolder).foreground_view;
            }

            @Override
            protected View getRightLeaveBehindView(RecyclerView.ViewHolder viewHolder) {
                return ((PantryAdapter.ViewHolder) viewHolder).rightLeaveBehind;
            }

            @Override
            protected View getLeftLeaveBehindView(RecyclerView.ViewHolder viewHolder) {
                return ((PantryAdapter.ViewHolder) viewHolder).leftLeaveBehind;
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.LEFT) {
                    deleteStudent(viewHolder.getAdapterPosition());
                } else {
                    archiveStudent(viewHolder.getAdapterPosition());
                }
            }
        });
        itemTouchHelper.attachToRecyclerView(lstStudents);

        vm.getStoredIngredients().observe(getViewLifecycleOwner(), this::updateIngredientList);

    }

    private void updateIngredientList(List<Ingredient> ingredients) {
        pantryAdapter.submitList(ingredients);
        if (ingredients.size() == 0) {
            pb.lblEmptyList.setVisibility(View.VISIBLE);
        } else {
            pb.lblEmptyList.setVisibility(View.INVISIBLE);
        }
    }

}
