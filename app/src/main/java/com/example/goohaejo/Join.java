package com.example.goohaejo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Join extends AppCompatActivity {

    Button bt_join;
    EditText editText_EmailAddress,editText_Password,editText_Password_Check,editText_Nickname;
    Button button_Email_check;
    String EmailAddress,Password, Nickname;
    TextView textView_password_wrong;
    ImageView ImageView_password_wrong,ImageView_password_right,iv_cancel2;

    private boolean check_email;

    // 체크박스 체크여부
    public int TERMS_AGREE_1 = 0; // No Check = 0, Check = 1
    public int TERMS_AGREE_2 = 0; // No Check = 0, Check = 1
    public int TERMS_AGREE_3 = 0; // No Check = 0, Check = 1

    // 체크박스
    CheckBox check1; // 첫번쨰 동의
    CheckBox check2; // 두번쨰 동의
    CheckBox check3; // 전체 동의

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        editText_Password = findViewById(R.id.editText_Password);
        textView_password_wrong = findViewById(R.id.textView_password_wrong);
        ImageView_password_wrong = findViewById(R.id.ImageView_password_wrong);
        ImageView_password_right = findViewById(R.id.ImageView_password_right);

        //나가기 버튼
        iv_cancel2 = findViewById(R.id.iv_cancel2);
        iv_cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        textView_password_wrong.setVisibility(View.INVISIBLE);
        ImageView_password_wrong.setVisibility(View.INVISIBLE);
        ImageView_password_right.setVisibility(View.INVISIBLE);

        editText_Password_Check = findViewById(R.id.editText_Password_Check);
        editText_Password_Check.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            //비밀번호 체크
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editText_Password.getText().toString().equals(editText_Password_Check.getText().toString())) {
                    //비밀번호와 비밀번호 확인이 같으면
                    ImageView_password_right.setVisibility(View.VISIBLE);
                    ImageView_password_wrong.setVisibility(View.INVISIBLE);
                    textView_password_wrong.setVisibility(View.INVISIBLE);
                } else {
                    //비밀번호와 비밀번호 확인이 다르면
                    ImageView_password_wrong.setVisibility(View.VISIBLE);
                    textView_password_wrong.setVisibility(View.VISIBLE);
                    ImageView_password_right.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        button_Email_check = findViewById(R.id.button_Email_check);
        button_Email_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText_EmailAddress = findViewById(R.id.editText_EmailAddress);
                String Emailcheck = editText_EmailAddress.getText().toString();

                SharedPreferences prefs = getSharedPreferences(Emailcheck, 0);
                if(prefs.getString(Emailcheck,"").length()>1){
                    Toast.makeText(Join.this,"이미 가입된 메일입니다.",Toast.LENGTH_SHORT).show();
                    check_email = false;
                }else{
                    Toast.makeText(Join.this,"가입 가능한 메일입니다.",Toast.LENGTH_SHORT).show();
                    check_email = true;
                    button_Email_check.setClickable(false);
                }
            }
        });

        bt_join = findViewById(R.id.button_Join_Complete);
        bt_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check_email){


                    Toast.makeText(Join.this,"회원가입이 완료되었습니다.",Toast.LENGTH_SHORT).show();
                    finish();


                }else{
                    Toast.makeText(Join.this,"메일 중복확인을 해주세요",Toast.LENGTH_SHORT).show();
                }
            }
        });

        check1 = findViewById(R.id.checkBox);
        check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    TERMS_AGREE_1 = 1;
                } else {

                    TERMS_AGREE_1 = 0;
                }
            }
        });
        check2 = findViewById(R.id.checkBox2);
        check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    TERMS_AGREE_2 = 1;
                } else {

                    TERMS_AGREE_2 = 0;
                }
            }
        });
        check3 = findViewById(R.id.checkBox3);
        check3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    check1.setChecked(true);
                    check2.setChecked(true);
                    TERMS_AGREE_3 = 1;
                } else {
                    check1.setChecked(false);
                    check2.setChecked(false);
                    TERMS_AGREE_3 = 0;
                }
            }
        });

        //약관동의
//        Button button = findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //어디에서 어디로
//                Intent intent = new Intent(JoinActivity.this,JoinAgree1.class);
//                startActivity(intent);
//            }
//        });
//        Button button1 = findViewById(R.id.button2);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //어디에서 어디로
//                Intent intent = new Intent(JoinActivity.this,JoinAgree2.class);
//                startActivity(intent);
//            }
//        });
    }


}