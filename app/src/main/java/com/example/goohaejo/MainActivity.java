package com.example.goohaejo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ImageView iv_add;

    String title, rigion, fee, img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        //리사이클러뷰
        RecyclerView recyclerView = findViewById(R.id.rv_goohaejo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MainAdapter adapter = new MainAdapter();
        recyclerView.setAdapter(adapter);

        //보카 등록된 데이터 받기
        try {
            if (bundle.getString("title") == null) {
//        if(data.size()==0){
                Log.d("구해줘액티비티", "데이터 없음");
            } else {
                try {
                    Goohaejodata goohaejodata = new Goohaejodata();
                    title = bundle.getString("title");
                    rigion = bundle.getString("region");
                    fee = bundle.getString("fee");
                    img = bundle.getString("img");
                    Log.d("구해줘액티비티", "제목 : " + title);

                    goohaejodata.title = title;
                    goohaejodata.rigion = rigion;
                    goohaejodata.fee = fee;
                    goohaejodata.img = img;

                    adapter.addgoohaejo(goohaejodata);


                } catch (Exception e) {

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 5; i++) {
            Goohaejodata goohaejodata = new Goohaejodata();
            goohaejodata.title = "과자";
            goohaejodata.rigion = "미국";
            goohaejodata.fee = "10,000";
            adapter.addgoohaejo(goohaejodata);


            bottomNavigationView = findViewById(R.id.bottomNavigationView);
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.action_home:
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            //인텐트 실행
                            startActivity(intent);
                            break;
                        case R.id.action_leave:
                            Intent intent1 = new Intent(MainActivity.this, MainLeave.class);
                            //인텐트 실행
                            startActivity(intent1);
                            break;
                        case R.id.action_chat:
                            Intent intent2 = new Intent(MainActivity.this, MainChat.class);
                            //인텐트 실행
                            startActivity(intent2);
                            break;
                        case R.id.action_setting:
                            Intent intent3 = new Intent(MainActivity.this, MainMypage.class);
                            //인텐트 실행
                            startActivity(intent3);
                            break;

                    }
                    return true;
                }
            });

            iv_add = findViewById(R.id.iv_add);
            iv_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //어디에서 어디로
                    Intent intent = new Intent(MainActivity.this, RegiPostGuham.class);
                    //인텐트 실행
                    startActivity(intent);
                }
            });


        }
    }

    class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
        public Context mContext;
        public MainAdapter adapter;

        public ArrayList<Goohaejodata> mData = new ArrayList<>();

        @NonNull
        @Override
        public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = View.inflate(context, R.layout.item_goohaejo, null);
            MainAdapter.ViewHolder vh = new MainAdapter.ViewHolder(view, adapter);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
            Goohaejodata text = mData.get(position);
            holder.tv_title.setText(text.title);
            holder.tv_region.setText(text.rigion);
            holder.tv_fee.setText(text.fee);
            Glide.with(holder.iv_goohaeho).load(text.getImg()).centerCrop().into(holder.iv_goohaeho);

            holder.cl_goohaejo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView tv_title, tv_region, tv_fee, tv_like;
            ConstraintLayout cl_goohaejo;
            ImageView iv_goohaeho;

            MainAdapter mAdapter;

            public ViewHolder(@NonNull View itemView, MainAdapter adapter) {
                super(itemView);

                tv_title = itemView.findViewById(R.id.tv_title);
                tv_region = itemView.findViewById(R.id.tv_region);
                tv_fee = itemView.findViewById(R.id.tv_fee);
                tv_like = itemView.findViewById(R.id.tv_like);
                iv_goohaeho = itemView.findViewById(R.id.iv_goohaeho);
                cl_goohaejo = itemView.findViewById(R.id.cl_goohaejo);
                mAdapter = adapter;
                mContext = itemView.getContext();

            }

            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();

                switch (view.getId()) {

                    case R.id.cl_goohaejo:


                        break;

                }
            }

        }

        void addgoohaejo(Goohaejodata goohaejodata) {
            mData.add(goohaejodata);
        }
    }

    class Goohaejodata {

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getRigion() {
            return rigion;
        }

        public void setRigion(String rigion) {
            this.rigion = rigion;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        String title;
        String rigion;
        String fee;
        String img;
    }
}