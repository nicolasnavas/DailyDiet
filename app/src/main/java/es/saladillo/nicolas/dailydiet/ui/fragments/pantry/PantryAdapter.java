package es.saladillo.nicolas.dailydiet.ui.fragments.pantry;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;

import es.saladillo.nicolas.dailydiet.R;
import es.saladillo.nicolas.dailydiet.data.local.entities.Ingredient;
import es.saladillo.nicolas.dailydiet.databinding.PantryListItemBinding;
import es.saladillo.nicolas.dailydiet.utilities.Utilities;

public class PantryAdapter extends ListAdapter<Ingredient, PantryAdapter.ViewHolder> {

    private PantryListItemBinding plib;
    public PlusMinusButtonsListener plusMinusButtonsListener;

    public PantryAdapter(PlusMinusButtonsListener plusMinusButtonsListener) {
        super(new DiffUtil.ItemCallback<Ingredient>() {
            @Override
            public boolean areItemsTheSame(@NonNull Ingredient oldItem, @NonNull Ingredient newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Ingredient oldItem, @NonNull Ingredient newItem) {
                return TextUtils.equals(oldItem.getName(), newItem.getName())
                        && TextUtils.equals(oldItem.getImage(), newItem.getImage())
                        && oldItem.getQuantity() == newItem.getQuantity()
                        && oldItem.getIngredient_id() == newItem.getIngredient_id();
            }
        });

        this.plusMinusButtonsListener = plusMinusButtonsListener;

    }

    @NonNull
    @Override
    public PantryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        plib = PantryListItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(plib,plusMinusButtonsListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }


    @Override
    public Ingredient getItem(int position) {
        return super.getItem(position);
    }
    // VIEWHOLDER
    public class ViewHolder extends RecyclerView.ViewHolder {
        public Ingredient ingredient;
        public PantryListItemBinding plib;
        private WeakReference<PlusMinusButtonsListener> plusMinusButtonsListener;

        ViewHolder(PantryListItemBinding plib,PlusMinusButtonsListener plusMinusButtonsListener) {
            super(plib.getRoot());
            this.plib = plib;
            this.plusMinusButtonsListener = new WeakReference<>(plusMinusButtonsListener);
        }

        void bind(Ingredient ingredient) {
            this.ingredient = ingredient;
            // Showing the information from the ingredient
            Utilities.drawImageGlide(ingredient.getImage(),plib.imgIngredient);
            plib.lblIngredientName.setText(ingredient.getName());
            if(ingredient.getQuantity() < ingredient.getMinimum()){
                plib.lblStoredIngredientQuantity.setTextColor(plib.lblIngredientName.getContext().getResources().getColor(R.color.holo_red_light));
            }
            plib.lblStoredIngredientQuantity.setText(Integer.toString(ingredient.getQuantity()));

            // Listeners for the + and - buttons
            plib.lblPlusOne.setOnClickListener(v -> plusMinusButtonsListener.get().onPlusClicked(ingredient));
            plib.lblMinusOne.setOnClickListener(v -> plusMinusButtonsListener.get().onMinusClicked(ingredient));

        }



    }
}
