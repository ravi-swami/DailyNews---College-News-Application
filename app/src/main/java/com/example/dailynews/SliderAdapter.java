package com.example.dailynews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.MyViewHolder> {
    Context context;
    List<String> imageList;

    public SliderAdapter(Context context, List<String> list){
        this.context = context;
        this.imageList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_image_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        Glide.with(context).load(imageList.get(position)).into(viewHolder.imageView);
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    class MyViewHolder extends SliderViewAdapter.ViewHolder{
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView3);
        }
    }
}
