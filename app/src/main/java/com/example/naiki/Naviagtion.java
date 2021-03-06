package com.example.naiki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class Naviagtion extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    MeowBottomNavigation meowBottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naviagtion);

        meowBottomNavigation = findViewById(R.id.meowbottom);

        meowBottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_nearme ));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_donate ));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_baseline_dashboard_24 ));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_baseline_leaderboard_24 ));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(5, R.drawable.ic_profile ));

        meowBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener(

        ) {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;

                switch (item.getId()){
                    case 1:
                        fragment = new home();
                        break;
                    case 2:
                        fragment = new donate();
                        break;
                    case 3:
                        fragment = new Dashboard();
                        break;
                    case 4:
                        fragment = new MyAchievements();
                        break;
                    case 5:
                        fragment = new profile();
                        break;




                }
                loadFragment(fragment);
            }
        });

        meowBottomNavigation.setCount(1 , "");
        meowBottomNavigation.show(3 , true);
        meowBottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
//                Toast.makeText(getApplicationContext() , "You clicked" + item.getId(), Toast.LENGTH_SHORT).show();

                meowBottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
                    @Override
                    public void onReselectItem(MeowBottomNavigation.Model item) {
//                        Toast.makeText(getApplicationContext() , "You reselected" + item.getId(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout , fragment).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        sharedPreferences = getSharedPreferences("userr" , Context.MODE_PRIVATE);

        if(sharedPreferences.contains("rid") && sharedPreferences.contains("uphone"))
        {
            Intent intent = new Intent(this , Naviagtion.class);
            startActivity(intent);


        }

    }
}