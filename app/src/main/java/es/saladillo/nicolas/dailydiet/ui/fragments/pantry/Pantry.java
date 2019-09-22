package es.saladillo.nicolas.dailydiet.ui.fragments.pantry;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import es.saladillo.nicolas.dailydiet.R;
import es.saladillo.nicolas.dailydiet.data.RepositoryImpl;
import es.saladillo.nicolas.dailydiet.data.local.AppDatabase;
import es.saladillo.nicolas.dailydiet.data.local.DietDAO;
import es.saladillo.nicolas.dailydiet.data.local.Diet_DishDAO;
import es.saladillo.nicolas.dailydiet.data.local.DishDAO;
import es.saladillo.nicolas.dailydiet.data.local.Dish_IngredientDAO;
import es.saladillo.nicolas.dailydiet.data.local.IngredientDAO;
import es.saladillo.nicolas.dailydiet.data.local.entities.Ingredient;
import es.saladillo.nicolas.dailydiet.databinding.PantryFragmentBinding;
import es.saladillo.nicolas.dailydiet.ui.dialogs.MinimumQuantityDialogFragment;
import es.saladillo.nicolas.dailydiet.utilities.LayoutLeaveBehindCallback;

public class Pantry extends Fragment implements MinimumQuantityDialogFragment.Listener {

    private PantryViewModel vm;
    private RepositoryImpl repository;
    private PantryAdapter pantryAdapter;
    private PantryFragmentBinding pb;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        pb = PantryFragmentBinding.inflate(inflater, container, false);
        return pb.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        repositoryCreation();
        vm = ViewModelProviders.of(getActivity(), new PantryViewModelFactory(repository)).get(PantryViewModel.class);
        setupRecyclerView();
    }


    private void repositoryCreation() {
        DietDAO dietDAO = AppDatabase.getInstance(getContext()).dietDAO();
        DishDAO dishDAO = AppDatabase.getInstance(getContext()).dishDAO();
        Diet_DishDAO diet_dishDAO = AppDatabase.getInstance(getContext()).diet_dishDAO();
        IngredientDAO ingredientDAO = AppDatabase.getInstance(getContext()).ingredientDAO();
        Dish_IngredientDAO dish_ingredientDAO = AppDatabase.getInstance(getContext()).dish_ingredientDAO();

        repository = new RepositoryImpl(dietDAO, diet_dishDAO, dishDAO, dish_ingredientDAO, ingredientDAO);
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
                if (ingredient.getQuantity() >= 1) {
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
        pb.listIngredients.addItemDecoration(new DividerItemDecoration(pb.listIngredients.getContext(), DividerItemDecoration.VERTICAL));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new LayoutLeaveBehindCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            protected View getForegroundView(RecyclerView.ViewHolder viewHolder) {
                return ((PantryAdapter.ViewHolder) viewHolder).plib.cardView;
            }

            @Override
            protected View getRightLeaveBehindView(RecyclerView.ViewHolder viewHolder) {
                return ((PantryAdapter.ViewHolder) viewHolder).plib.rlRightLeaveBehind;
            }

            @Override
            protected View getLeftLeaveBehindView(RecyclerView.ViewHolder viewHolder) {
                return ((PantryAdapter.ViewHolder) viewHolder).plib.rlRightLeaveBehind;
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Ingredient swipedIngredient = ((PantryAdapter.ViewHolder) viewHolder).ingredient;

                // If the user swipes left
                if (direction == ItemTouchHelper.LEFT) {
                    // A popup is shown asking for the minimum amount of ingredients before an alert/notification is shown
                    vm.setModifiedIngredient(swipedIngredient);
                    showMinimumQuantityDialog();
                // If the user swipes right
                } else {
                    // The ingredient is deleted from the database (pantry)
                    vm.deleteIngredient(swipedIngredient);
                    Snackbar.make(getView(),swipedIngredient.getName() + " ha sido eliminado",Snackbar.LENGTH_LONG).show();
                }
            }
        });

        itemTouchHelper.attachToRecyclerView(pb.listIngredients);

        vm.getStoredIngredients().observe(getViewLifecycleOwner(), this::updateIngredientList);

    }

    public void showMinimumQuantityDialog(){
        MinimumQuantityDialogFragment minimumQuantityDialog;
        minimumQuantityDialog = new MinimumQuantityDialogFragment();
        minimumQuantityDialog.setTargetFragment(this,1);
        minimumQuantityDialog.show(getFragmentManager(),"TAG_DIALOG_FRAGMENT");
    }

    private void updateIngredientList(List<Ingredient> ingredients) {
        pantryAdapter.submitList(ingredients);
        if (ingredients.size() == 0) {
            pb.lblEmptyList.setVisibility(View.VISIBLE);
        } else {
            pb.lblEmptyList.setVisibility(View.INVISIBLE);
        }
    }


    // Minimum quantity dialog methods
    @Override
    public void onAcceptClick(DialogFragment dialog) {
        TextView txtMinimum = dialog.getDialog().findViewById(R.id.txtMinimumQuantity);
        int newIngredientMinimum = Integer.parseInt(txtMinimum.getText().toString());

        vm.getModifiedIngredient().setMinimum(newIngredientMinimum);
        vm.updateIngredient(vm.getModifiedIngredient());

        Toast.makeText(getContext(),"The minimum amount is now " + newIngredientMinimum,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCloseClick(DialogFragment dialog) {

    }
}
