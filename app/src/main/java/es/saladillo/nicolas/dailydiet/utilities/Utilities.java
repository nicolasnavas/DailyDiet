package es.saladillo.nicolas.dailydiet.utilities;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Utilities {
    private Utilities() {
    }


    public static void drawImageGlide(String imageUrl, ImageView imgViewTarget){
        Glide.with(imgViewTarget.getContext()).load(imageUrl).centerCrop().into(imgViewTarget);
    }

}
