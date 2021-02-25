package com.example.goohaejo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChattingRoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_room);
        ImageView close = findViewById(R.id.close);
        final EditText message=findViewById(R.id.inputchat);
        TextView send = findViewById(R.id.send);
        final RecyclerView rv = findViewById(R.id.recyclerview);


        //////////////리사이클러뷰 어댑터 레이아웃매니저등록
        final ChatAdapter adapter = new ChatAdapter(this);
        rv.setAdapter(adapter);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        ///////////////////////////////////////////

        for(int i=0;i<3;i++){
            ChatItem item = new ChatItem();
            item.message="ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ";
            item.isme=false;
            ChatItem item2 = new ChatItem();
            item2.message="ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ";
            item2.isme=true;
            adapter.listdata.add(item);
            adapter.listdata.add(item2);
        }rv.scrollToPosition(adapter.listdata.size()-1);






        //보내기 버튼
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mes = message.getText().toString();
                if(!mes.equals("")){
                    ChatItem item = new ChatItem();
                    item.message=message.getText().toString();
                    item.isme=true;
                    adapter.listdata.add(item);
                    rv.scrollToPosition(adapter.listdata.size()-1);
                    message.setText("");
                }
            }
        });

        //닫기 버튼
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
class ChatItem{
    String message;
    boolean isme;
}

class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    ArrayList<ChatItem> listdata = new ArrayList<>();
    Context mcontext;
    ChatAdapter(Context context){
        mcontext = context;
    }
    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType==1) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_me,parent,false);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_partner,parent,false);
        }
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        holder.message.setText(listdata.get(position).message);
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }


    @Override
    public int getItemViewType(int position) {
        if(listdata.get(position).isme){
            return 1;
        }else{
            return 0;
        }
    }

    class ChatViewHolder extends RecyclerView.ViewHolder{
        TextView message;
        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            message=itemView.findViewById(R.id.message);
        }

    }
}