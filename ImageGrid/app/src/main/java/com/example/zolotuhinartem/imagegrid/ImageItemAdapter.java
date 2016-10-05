package com.example.zolotuhinartem.imagegrid;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zolotuhinartem on 05.10.16.
 */

public class ImageItemAdapter extends RecyclerView.Adapter<ImageItemViewHolder> {

    private ImageItemListener mImageItemListener;
    private int columns;
    private List<ImageItem> list;
    private Context context;

    public ImageItemAdapter(Context ctx, int columns){
        this.context = ctx;
        this.columns = columns;
    }

    public void setImageItemListener(ImageItemListener mImageItemListener) {
        this.mImageItemListener = mImageItemListener;
    }

    public void setList(List<ImageItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ImageItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new ImageItemViewHolder(view, columns);
    }

    @Override
    public void onBindViewHolder(ImageItemViewHolder holder, int position) {
        if (holder instanceof ImageItemViewHolder){
            ImageItem item = list.get(position);
            if (item != null){
                holder.bind(item, mImageItemListener);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
