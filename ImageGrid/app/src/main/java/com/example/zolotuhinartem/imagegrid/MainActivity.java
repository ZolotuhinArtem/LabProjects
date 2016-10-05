package com.example.zolotuhinartem.imagegrid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.grid_list);
        int columns = 3;
        ImageItemAdapter adapter = new ImageItemAdapter(this, columns);
        int k, r;
        int listSize = 25;
        ArrayList<ImageItem> list = new ArrayList<>(listSize);
        for(int i = 0; i < listSize; i++){
            k = (int)Math.round(Math.random() * 5);
            r = 0;
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
            list.add(new ImageItem(r));
        }

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
}
