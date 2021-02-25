package com.example.goohaejo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainChat extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home :
                        Intent intent = new Intent(MainChat.this,MainActivity.class);
                        //인텐트 실행
                        startActivity(intent);
                        break;
                    case R.id.action_leave:
                        Intent intent1 = new Intent(MainChat.this,MainLeave.class);
                        //인텐트 실행
                        startActivity(intent1);
                        break;
                    case R.id.action_chat :
                        Intent intent2 = new Intent(MainChat.this,MainChat.class);
                        //인텐트 실행
                        startActivity(intent2);
                        break;
                    case R.id.action_setting :
                        Intent intent3 = new Intent(MainChat.this,MainMypage.class);
                        //인텐트 실행
                        startActivity(intent3);
                        break;

                }
                return true;
            }
        });
    }
}