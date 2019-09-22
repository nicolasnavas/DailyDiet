package es.saladillo.nicolas.dailydiet.utilities;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

@SuppressWarnings("unused")
public class ScrollAwareFabBehavior extends FloatingActionButton.Behavior {

    private static final long ANIMATION_DURATION = 200;

    private boolean isHidden;

    public ScrollAwareFabBehavior(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(@NonNull final CoordinatorLayout coordinatorLayout,
                                       @NonNull final FloatingActionButton child, @NonNull final View directTargetChild, @NonNull final View target,
                                       final int nestedScrollAxes, int type) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(
                coordinatorLayout, child, directTargetChild, target, nestedScrollAxes, type);
    }


    @Override
    public void onNestedScroll(@NonNull final CoordinatorLayout coordinatorLayout,
                               @NonNull final FloatingActionButton child, @NonNull final View target, final int dxConsumed,
                               final int dyConsumed, final int dxUnconsumed, final int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed,
                dyUnconsumed, type);
        if (dyConsumed > 0 && !isHidden) {
            child.animate().scaleX(0).scaleY(0).setDuration(ANIMATION_DURATION).setListener(
                    new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            isHidden = true;
                            child.setTag(true);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });

        } else if (dyConsumed < 0 && isHidden) {
            child.animate().scaleX(1).scaleY(1).setDuration(ANIMATION_DURATION).setListener(
                    new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            isHidden = false;
                            child.setTag(false);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
        }
    }

}