package es.saladillo.nicolas.dailydiet.ui.fragments.pantry;

import android.graphics.drawable.Drawable;

import androidx.annotation.ColorInt;

public class IconicDrawable {
    @ColorInt
    private final int backgroundColor;
    private final Drawable icon;

    public IconicDrawable(int backgroundColor, Drawable icon) {
        this.backgroundColor = backgroundColor;
        this.icon = icon;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public Drawable getIcon() {
        return icon;
    }
}
