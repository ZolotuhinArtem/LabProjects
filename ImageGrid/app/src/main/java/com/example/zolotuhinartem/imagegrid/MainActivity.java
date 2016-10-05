package com.example.zolotuhinartem.imagegrid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int columns = 3;
        int listSize = 25;


        recyclerView = (RecyclerView) findViewById(R.id.grid_list);
        ImageItemAdapter adapter = new ImageItemAdapter(this, columns);

        ArrayList<ImageItem> list = new ArrayList<>(listSize);
        fillList(list, listSize);

        adapter.setList(list);
        adapter.setImageItemListener(new ImageItemListener() {
            @Override
            public void onClick(ImageItem item) {
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                intent.putExtra("image_id", item.getImageId());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, columns, GridLayoutManager.VERTICAL, false));

    }

    public int getRandomImageId(){
        int k;
        k = (int)Math.round(Math.random() * 5);
        int r = 0;
        switch (k){
            case 0:
                r = R.drawable.img1;
                break;
            case 1:
                r = R.drawable.img2;
                break;
            case 2:
                r = R.drawable.img3;
                break;
            case 3:
                r = R.drawable.img4;
                break;
            case 4:
                r = R.drawable.img5;
                break;
            case 5:
                r = R.drawable.img6;
                break;
        }
        return r;
    }
    public void fillList(List<ImageItem> list, int size){
        for(int i = 0; i < size; i++){
            list.add(new ImageItem(getRandomImageId()));
        }
    }
}
