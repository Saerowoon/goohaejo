package com.example.goohaejo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    TextView textView_FindPassword,textView_join;
    String getPassword;
    static String getEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button Button_Login = findViewById(R.id.Button_Login);
        Button_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEmail = ((EditText)findViewById(R.id.EditText_Email)).getText().toString();

                Toast.makeText(Login.this,"로그인되었습니다.",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, MainActivity.class);
                intent.putExtra("UserID", getEmail);
                startActivity(intent);

                finish();


            }
        });
        //XLM 아이디와 연결
        textView_join = findViewById(R.id.textView_join);
        textView_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //어디에서 어디로
                Intent intent = new Intent(Login.this, Join.class);

                //인텐트 실행
                startActivity(intent);
            }
        });
    }
}