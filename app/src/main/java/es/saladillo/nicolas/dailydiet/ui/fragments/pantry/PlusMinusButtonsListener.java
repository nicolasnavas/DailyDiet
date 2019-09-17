package es.saladillo.nicolas.dailydiet.ui.fragments.pantry;

import es.saladillo.nicolas.dailydiet.data.local.entities.Ingredient;

public interface PlusMinusButtonsListener {

    void onPlusClicked(Ingredient ingredient);

    void onMinusClicked(Ingredient ingredient);

}
