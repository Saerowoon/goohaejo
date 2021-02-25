package com.example.goohaejo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class GoOut extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_out);
        RecyclerView rv = findViewById(R.id.recyclerview);
        ImageView add = findViewById(R.id.add);




        /////리사이클러뷰 어댑터 레이아웃매니저 등록
        GoOutAdapter adapter = new GoOutAdapter(this);
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
            GoOutItem item = new GoOutItem();
            String[] strarr=intent.getStringArrayExtra("goout_data");
            item.country=strarr[0];
            item.city=strarr[1];
            item.message=strarr[2];
            adapter.listdata.add(item);
        }

        for(int i=0;i<9;i++){
            GoOutItem item = new GoOutItem();
            item.city="서울";
            item.country="한국";
            item.message="내일 일산가는데 빵사드림";
            adapter.listdata.add(item);
        }


    }
}

class GoOutItem{
    String country,message,city;
}


class GoOutAdapter extends RecyclerView.Adapter<GoOutAdapter.GoOutViewHolder>{
    ArrayList<GoOutItem> listdata = new ArrayList<>();
    Context mcontext;
    GoOutAdapter(Context context){
        mcontext=context;
    }
    @NonNull
    @Override
    public GoOutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goout,parent,false);

        return new GoOutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoOutViewHolder holder, int position) {
        String c_c=String.format("%s | %s 으로 떠나요", listdata.get(position).country,listdata.get(position).city);
        holder.country_and_city.setText(c_c);
        holder.message.setText(listdata.get(position).message);
        holder.go_to_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mcontext,Chat.class);
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    class GoOutViewHolder extends RecyclerView.ViewHolder{
        CircleImageView profile_photo;
        TextView nickname;
        TextView country_and_city;
        TextView message;
        TextView go_to_chat;
        // todo 다음에 추가
        public GoOutViewHolder(@NonNull View itemView) {
            super(itemView);
            profile_photo=itemView.findViewById(R.id.profile_photo);
            nickname=itemView.findViewById(R.id.nickname);
            country_and_city=itemView.findViewById(R.id.country_and_city);
            message=itemView.findViewById(R.id.message);
            go_to_chat=itemView.findViewById(R.id.go_to_chat);
        }
    }
}