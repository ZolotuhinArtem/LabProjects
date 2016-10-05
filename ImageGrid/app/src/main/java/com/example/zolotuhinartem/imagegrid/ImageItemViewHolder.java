package com.example.zolotuhinartem.imagegrid;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by zolotuhinartem on 05.10.16.
 */

public class ImageItemViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    public ImageItemViewHolder(View itemView, int columns) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.iv_item);
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        int widthDevice = itemView.getContext().getResources().getDisplayMetrics().widthPixels;
        float width = widthDevice;
        width /= (float) columns;
        lp.width = (int)Math.round(width) - 3;
        lp.height = (int)Math.round(width) - 3;

    }
    public void bind(final ImageItem item, final ImageItemListener listener){
        if (item != null){
            if (imageView != null){
                imageView.setImageResource(item.getImageId());
            }

        }
        if (listener != null){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(item);
                }
            });
        }
    }
}
