package com.example.goohaejo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class WriteForGoOut extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_for_go_out);
        final ImageView close = findViewById(R.id.close);
        final TextView complete = findViewById(R.id.complete);
        final EditText city = findViewById(R.id.city);
        final EditText message = findViewById(R.id.message);




        // 나라들 스피너
        final Spinner spinner = (Spinner) findViewById(R.id.country_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.country_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setPrompt("나라를선택해주세요");








        //스피너 이벤트
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, View view, int position, long id) {
                if(position ==0){
                    complete.setTextColor(getColor(R.color.colorGrey));
                }else{

                    //나라 선택했을 때만 글작성 가능     도시나 글내용은 일단보류?
                    complete.setTextColor(getColor(R.color.colorPrimary));
                    complete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String country_write=spinner.getSelectedItem().toString();
                            String city_write=city.getText().toString();
                            String message_write=message.getText().toString();
                            String[] goout_data = new String[3];
                            goout_data[0]=country_write;
                            goout_data[1]=city_write;
                            goout_data[2]=message_write;

                            Intent intent=new Intent(parent.getContext(),MainLeave.class);
                            intent.putExtra("goout_data",goout_data);
                            startActivity(intent);
                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        //종료버튼
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}