package com.example.zolotuhinartem.imagegrid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Intent intent = getIntent();
        ImageView imageView = (ImageView) findViewById(R.id.show_image_view);
        imageView.setImageResource(intent.getIntExtra("image_id",0));
    }
}
