package com.example.dailynews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class NewsContent extends AppCompatActivity {
    TextView titleView, dateView, contentView;
    ImageView imageView;

    String title, date, content, imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        titleView = findViewById(R.id.textView2);
        dateView = findViewById(R.id.textView3);
        contentView = findViewById(R.id.textView4);
        imageView = findViewById(R.id.img2);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        date = intent.getStringExtra("date");
        content = intent.getStringExtra("content");
        imageUrl = intent.getStringExtra("imageUrl");

        titleView.setText(title);
        dateView.setText(date);
        contentView.setText(content);

        if(imageUrl.equalsIgnoreCase("null")){
            Glide.with(NewsContent.this).load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSADPzrYm_hQg2XMNc_9KTr9Axmn35s0DbsIQ&usqp=CAU").into(imageView);
        }
        else{
            Glide.with(NewsContent.this).load(imageUrl).into(imageView);
        }

    }
}