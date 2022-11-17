package com.example.birch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.birch.ui.Recommendations.RecommendationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
//implements View.OnClickListener
public class MainActivity extends AppCompatActivity{
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Navigation logic
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment =
                (NavHostFragment) supportFragmentManager.findFragmentById(R.id.nav_host_fragment);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(bottomNav, navController);

        //recommendation button
        //View v = findViewById(R.id.recommendation_button_id);
        //v.setOnClickListener(this);
    }
    public void move_to_recommend(View view) {

            Intent Reco_Button = new Intent(this, RecommendationsFragment.class);
            this.startActivity(Reco_Button);

    }
}