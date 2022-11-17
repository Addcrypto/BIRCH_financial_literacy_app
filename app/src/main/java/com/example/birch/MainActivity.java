package com.example.birch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.birch.ui.Recommendations.RecommendationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity{
    NavController navController;
    private Button button_rf;
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
        button_rf = (Button) findViewById(R.id.recommendation_button_id);
        button_rf.setOnClickListener(new View.OnClickListener(){
            @Override
                public void onClick(View v){
            {
                move_to_recommend();
            }
        }
        });
    }
    public void move_to_recommend() {

            Intent Reco_Button = new Intent(this, RecommendationsFragment.class);
            this.startActivity(Reco_Button);

    }
}