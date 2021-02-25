package com.example.goohaejo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainLeave extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_leave);

        RecyclerView rv = findViewById(R.id.recyclerview);
        ImageView add = findViewById(R.id.add);




        /////리사이클러뷰 어댑터 레이아웃매니저 등록
        LeaveAdapter adapter = new LeaveAdapter(this);
        rv.setAdapter(adapter);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        /////////////////////////////////////////////////////////////////////////
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),WriteForGoOut.class);
                v.getContext().startActivity(intent);
            }
        });



        Intent intent=getIntent();
        if(intent.hasExtra("goout_data")){
            LeaveItem item = new LeaveItem();
            String[] strarr=intent.getStringArrayExtra("goout_data");
            item.country=strarr[0];
            item.city=strarr[1];
            item.message=strarr[2];
            adapter.listdata.add(item);
        }

        for(int i=0;i<9;i++){
            LeaveItem item = new LeaveItem();
            item.city="서울";
            item.country="한국";
            item.message="내일 일산가는데 빵사드림";
            adapter.listdata.add(item);
        }


        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home :
                        Intent intent = new Intent(MainLeave.this,MainActivity.class);
                        //인텐트 실행
                        startActivity(intent);
                        break;
                    case R.id.action_leave:
                        Intent intent1 = new Intent(MainLeave.this,MainLeave.class);
                        //인텐트 실행
                        startActivity(intent1);
                        break;
                    case R.id.action_chat :
                        Intent intent2 = new Intent(MainLeave.this,MainChat.class);
                        //인텐트 실행
                        startActivity(intent2);
                        break;
                    case R.id.action_setting :
                        Intent intent3 = new Intent(MainLeave.this,MainMypage.class);
                        //인텐트 실행
                        startActivity(intent3);
                        break;

                }
                return true;
            }
        });
    }
}

class LeaveAdapter extends RecyclerView.Adapter<LeaveAdapter.LeaveViewHolder>{
    ArrayList<LeaveItem> listdata = new ArrayList<>();
    Context mcontext;
    LeaveAdapter(Context context){
        mcontext=context;
    }
    @NonNull
    @Override
    public LeaveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goout,parent,false);

        return new LeaveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaveViewHolder holder, int position) {
        String c_c=String.format("%s | %s 으로 떠나요", listdata.get(position).country,listdata.get(position).city);
        holder.country_and_city.setText(c_c);
        holder.message.setText(listdata.get(position).message);
        holder.go_to_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mcontext,ChattingRoom.class);
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    class LeaveViewHolder extends RecyclerView.ViewHolder{
        CircleImageView profile_photo;
        TextView nickname;
        TextView country_and_city;
        TextView message;
        TextView go_to_chat;
        // todo 다음에 추가
        public LeaveViewHolder(@NonNull View itemView) {
            super(itemView);
            profile_photo=itemView.findViewById(R.id.profile_photo);
            nickname=itemView.findViewById(R.id.nickname);
            country_and_city=itemView.findViewById(R.id.country_and_city);
            message=itemView.findViewById(R.id.message);
            go_to_chat=itemView.findViewById(R.id.go_to_chat);
        }
    }
}

class LeaveItem{
    String country,message,city;
}

