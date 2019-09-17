package es.saladillo.nicolas.dailydiet.utilities;

import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import es.saladillo.nicolas.dailydiet.ui.fragments.pantry.IconicDrawable;

public abstract class LeaveBehindCallback extends ItemTouchHelper.SimpleCallback {

    private IconicDrawable leftIconicDrawable;
    private IconicDrawable rightIconicDrawable;

    public LeaveBehindCallback(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    public LeaveBehindCallback withLeftIconicDrawable(IconicDrawable iconicDrawable) {
        this.leftIconicDrawable = iconicDrawable;
        return this;
    }

    public LeaveBehindCallback withRightIconicDrawable(IconicDrawable iconicDrawable) {
        this.rightIconicDrawable = iconicDrawable;
        return this;
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {
        if (dX < 0 && rightIconicDrawable != null) {
            showRightLeaveBehind(c, viewHolder, dX);
        } else if (dX > 0 && leftIconicDrawable != null) {
            showLeftLeaveBehind(c, viewHolder, dX);
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState,
                isCurrentlyActive);
    }

    private void showRightLeaveBehind(Canvas c, RecyclerView.ViewHolder viewHolder,
                                      float dX) {
        // Draw background.
        ColorDrawable background = new ColorDrawable();
        background.setColor(rightIconicDrawable.getBackgroundColor());
        background.setBounds(viewHolder.itemView.getRight() + (int) dX,
                viewHolder.itemView.getTop(), viewHolder.itemView.getRight(),
                viewHolder.itemView.getBottom());
        background.draw(c);
        // Draw icon.
        int itemHeight = viewHolder.itemView.getBottom() - viewHolder.itemView.getTop();
        Drawable icon = rightIconicDrawable.getIcon();
        int iconIntrinsicWidth = icon.getIntrinsicWidth();
        int iconIntrinsicHeight = icon.getIntrinsicHeight();
        int iconTop = viewHolder.itemView.getTop() +
                (itemHeight - iconIntrinsicHeight) / 2;
        int iconMargin = (itemHeight - iconIntrinsicHeight) / 2;
        int iconLeft = viewHolder.itemView.getRight() - iconMargin - iconIntrinsicWidth;
        int iconRight = viewHolder.itemView.getRight() - iconMargin;
        int iconBottom = iconTop + iconIntrinsicHeight;
        icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
        icon.draw(c);
    }

    private void showLeftLeaveBehind(Canvas c, RecyclerView.ViewHolder viewHolder,
                                     float dX) {
        // Draw background.
        ColorDrawable background = new ColorDrawable();
        background.setColor(leftIconicDrawable.getBackgroundColor());
        background.setBounds(viewHolder.itemView.getLeft(),
                viewHolder.itemView.getTop(),
                viewHolder.itemView.getLeft() + (int) dX,
                viewHolder.itemView.getBottom());
        background.draw(c);
        // Draw icon.
        int itemHeight = viewHolder.itemView.getBottom() - viewHolder.itemView.getTop();
        Drawable icon = leftIconicDrawable.getIcon();
        int iconIntrinsicWidth = icon.getIntrinsicWidth();
        int iconIntrinsicHeight = icon.getIntrinsicHeight();
        int iconTop = viewHolder.itemView.getTop() +
                (itemHeight - iconIntrinsicHeight) / 2;
        int iconMargin = (itemHeight - iconIntrinsicHeight) / 2;
        int iconLeft = viewHolder.itemView.getLeft() + iconMargin;
        int iconRight = viewHolder.itemView.getLeft() + iconMargin + iconIntrinsicWidth;
        int iconBottom = iconTop + iconIntrinsicHeight;
        icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
        icon.draw(c);
    }

}
