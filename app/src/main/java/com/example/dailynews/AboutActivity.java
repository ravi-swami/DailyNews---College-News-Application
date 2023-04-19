package com.example.dailynews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class AboutActivity extends AppCompatActivity {
    SliderView sliderView;
    List<String> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        sliderView = findViewById(R.id.imageSlider);
        imageList = new ArrayList<>();

        imageList.add("https://firebasestorage.googleapis.com/v0/b/dailynews-baf8e.appspot.com/o/Slider%20Images%2FBKBIET_Home.jpg?alt=media&token=8b3428ef-9b72-4b4d-b910-fd7bd69d663d");
        imageList.add("https://firebasestorage.googleapis.com/v0/b/dailynews-baf8e.appspot.com/o/Slider%20Images%2FHistory-R-1.jpg?alt=media&token=19720c6c-1d9c-4bc0-93dd-57d6b0473278");
        imageList.add("https://firebasestorage.googleapis.com/v0/b/dailynews-baf8e.appspot.com/o/Slider%20Images%2Fcertificate-001-2-1.jpg?alt=media&token=4fb05468-aee9-4125-a81c-a8f22f7002e9");
        imageList.add("https://firebasestorage.googleapis.com/v0/b/dailynews-baf8e.appspot.com/o/Slider%20Images%2FGlobal-achievers-award-certificate_1-1024x724.jpg?alt=media&token=a7331486-5a5a-4338-bae2-03d1c341d32e");
        imageList.add("https://firebasestorage.googleapis.com/v0/b/dailynews-baf8e.appspot.com/o/Slider%20Images%2FNEA-award-1024x725.jpg?alt=media&token=c06d1156-86e4-4217-9c99-1c28224079d9");
        imageList.add("https://firebasestorage.googleapis.com/v0/b/dailynews-baf8e.appspot.com/o/Slider%20Images%2FBKBIET-in-Top-10-540x378.png?alt=media&token=d5cc1734-260b-49a4-8c39-52874deea3bf");
        imageList.add("https://firebasestorage.googleapis.com/v0/b/dailynews-baf8e.appspot.com/o/Slider%20Images%2FAICTE-Internship1-540x378.png?alt=media&token=fd5cd919-e737-493e-aeaa-48c3dc89e595");

        SliderAdapter sliderAdapter = new SliderAdapter(getApplicationContext(), imageList);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setAutoCycle(true);

    }
}