package es.saladillo.nicolas.dailydiet.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import es.saladillo.nicolas.dailydiet.R;
import es.saladillo.nicolas.dailydiet.utilities.BottomNavigationBehavior;
import es.saladillo.nicolas.dailydiet.utilities.BottomNavigationBehavior2;

public class MainActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navController = Navigation.findNavController(this, R.id.navHostFragment);
        setupViews();
    }

    private void setupViews() {
        BottomNavigationView bottomNavigationView;
//        CoordinatorLayout.LayoutParams layoutParams;

        bottomNavigationView = ActivityCompat.requireViewById(this, R.id.bottomNavigationView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

//        layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
//        layoutParams.setBehavior(new BottomNavigationBehavior());

    }
}
