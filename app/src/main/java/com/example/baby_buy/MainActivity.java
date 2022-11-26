package com.example.baby_buy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private FloatingActionButton floatingBtn;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recycler);
        floatingBtn=findViewById(R.id.fab);

        itemAdapter=new ItemAdapter();
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        itemAdapter.setData(getListItem());
        recyclerView.setAdapter(itemAdapter);
        floatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy>0){
                    floatingBtn.hide();
                }else {
                    floatingBtn.show();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });


    }
    private List<Lists> getListItem(){
        List<Lists>list=new ArrayList<>();
        list.add(new Lists(R.drawable.img3,"Rahul Kumar","RS:200","this is Rahul"));
        list.add(new Lists(R.drawable.img10,"Rohan Kumar","RS:400","this is Rahul"));
        list.add(new Lists(R.drawable.img0,"Sumit Kumar","RS:250","this is Rahul"));
        list.add(new Lists(R.drawable.babybuylogo,"sunita Kumari","RS:950","this is Rahul"));
        list.add(new Lists(R.drawable.img3,"Rahul Kumar","RS:200","this is Rahul"));
        list.add(new Lists(R.drawable.img10,"Rohan Kumar","RS:400","this is Rahul"));
        list.add(new Lists(R.drawable.img0,"Sumit Kumar","RS:250","this is Rahul"));
        list.add(new Lists(R.drawable.babybuylogo,"sunita Kumari","RS:950","this is Rahul"));
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }
}