package com.game.gamewinner.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.game.gamewinner.R;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;
public class Slider_Adapter extends SliderViewAdapter<Slider_Adapter.hol> {

     int []  images;
     Context context;

    public Slider_Adapter(int[] images, Context context) {
        this.images = images;
        this.context = context;
    }

    @Override
    public hol onCreateViewHolder(ViewGroup parent) {

        View view=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.slide_image_demo,parent,false);

        return new hol(view);
    }

    @Override
    public void onBindViewHolder(hol viewHolder, int position) {


        viewHolder.imageView.setImageResource(images[position]);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    public  class  hol extends ViewHolder {
          ImageView imageView;
        public hol(View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.image_view);
        }
    }
}
