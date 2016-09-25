package com.therishka.androidlab_2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.therishka.androidlab_2.models.VkAttachments;
import com.therishka.androidlab_2.models.VkLikes;
import com.therishka.androidlab_2.models.VkNewsItem;
import com.therishka.androidlab_2.models.VkPhoto;
import com.therishka.androidlab_2.network.RxVk;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    ProgressBar mProgress;
    RecyclerView mRecyclerList;
    RecyclerNewsAdapter mNewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        mProgress = (ProgressBar) findViewById(R.id.news_loading_view);
        mRecyclerList = (RecyclerView) findViewById(R.id.news_list);
        mNewsAdapter = new RecyclerNewsAdapter(this);
        mRecyclerList.setAdapter(mNewsAdapter);
        mRecyclerList.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerList.setVisibility(View.GONE);
        mProgress.setVisibility(View.VISIBLE);

        RxVk api = new RxVk();
        api.getNews(new RxVk.RxVkListener<LinkedList<VkNewsItem>>() {
            @Override
            public void requestFinished(LinkedList<VkNewsItem> requestResult) {
                mNewsAdapter.setNewsList(requestResult);
                mProgress.setVisibility(View.GONE);
                mRecyclerList.setVisibility(View.VISIBLE);

            }
        });
    }

    public class RecyclerNewsAdapter extends RecyclerView.Adapter<RecyclerNewsAdapter.NewsViewHolder>{
        private List<VkNewsItem> newsList;
        private Context mContext;

        public RecyclerNewsAdapter(Context context) {
            mContext = context;
        }

        @Override
        public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
            return new NewsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(NewsViewHolder holder, int position) {
            if (holder instanceof NewsViewHolder){
                VkNewsItem vkn = newsList.get(position);
                if (vkn != null){
                    String dateString;
                    Date date = new Date(vkn.getDate() * 1000);
                    DateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yyyy ");
                    dateString = dateFormat.format(date);

                    holder.clearImage();

                    holder.getTitle().setText(vkn.getPublisher().getName());
                    holder.getDate().setText(dateString);
                    holder.getText().setText(vkn.getText());
                    holder.getLikes().setText(R.string.likes);
                    VkLikes likes = vkn.getLikes();
                    if (likes != null) {
                        int countLikes = 0;
                        countLikes = likes.getCount();
                        holder.getLikes().append(Integer.toString(countLikes));
                    }


                    Glide.with(mContext).load(vkn.getPublisher().getPhoto_50())
                            .fitCenter()
                            .into(((NewsViewHolder) holder).getPhoto());
                    int k;
                    List<VkAttachments> lvka = vkn.getAttachments();
                    if (lvka != null){
                        k = lvka.size();
                        int j;
                        j = 0;
                        String[] photos = new String[k];
                        for(VkAttachments i: lvka){
                            String photo = null;
                            if (i != null) {
                                VkPhoto pht;
                                pht = i.getPhoto();
                                if (pht != null) {
                                    photo = pht.getPhoto_2560();
                                    if (photo == null) {
                                        photo = pht.getPhoto_1280();
                                        if (photo == null) {
                                            photo = pht.getPhoto_807();
                                            if (photo == null) {
                                                photo = pht.getPhoto_604();
                                                if (photo == null) {
                                                    photo = pht.getPhoto_130();
                                                    if (photo == null) {
                                                        photo = pht.getPhoto_75();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (photo != null){
                                ImageView img = null;
                                switch (j){
                                    case 0:
                                        img = holder.getImg1();
                                        break;
                                    case 1:
                                        img = holder.getImg2();
                                        break;
                                    case 2:
                                        img = holder.getImg3();
                                        break;
                                    case 3:
                                        img = holder.getImg4();
                                        break;
                                    case 4:
                                        img = holder.getImg5();
                                        break;
                                }
                                if (img != null){
                                    img.setVisibility(View.VISIBLE);
                                    Glide.with(mContext).load(photo)
                                            .fitCenter()
                                            .into(img);
                                }
                            }
                            j++;
                        }
                    }
                }

            }
        }

        @Override
        public int getItemCount() {
            return newsList == null ? 0:newsList.size();
        }

        public void setNewsList(List<VkNewsItem> l){
            this.newsList = l;
            notifyDataSetChanged();

        }

        public class NewsViewHolder extends RecyclerView.ViewHolder{

            private ImageView photo;
            private TextView title;
            private TextView date;
            private TextView text;
            private ImageView img1;
            private ImageView img2;
            private ImageView img3;
            private ImageView img4;
            private ImageView img5;
            private TextView likes;


            public NewsViewHolder(View itemView) {

                super(itemView);
                photo = (ImageView) itemView.findViewById(R.id.news_photo);
                title = (TextView) itemView.findViewById(R.id.news_title);
                date = (TextView) itemView.findViewById(R.id.news_date);
                text = (TextView) itemView.findViewById(R.id.news_text);
                likes = (TextView) itemView.findViewById(R.id.news_likes);
                img1 = (ImageView) itemView.findViewById(R.id.news_img_1);
                img2 = (ImageView) itemView.findViewById(R.id.news_img_2);
                img3 = (ImageView) itemView.findViewById(R.id.news_img_3);
                img4 = (ImageView) itemView.findViewById(R.id.news_img_4);
                img5 = (ImageView) itemView.findViewById(R.id.news_img_5);

            }

            public void clearImage(){

                img1.setVisibility(View.GONE);
                img2.setVisibility(View.GONE);
                img3.setVisibility(View.GONE);
                img4.setVisibility(View.GONE);
                img5.setVisibility(View.GONE);
            }

            public ImageView getPhoto() {
                return photo;
            }

            public TextView getTitle() {
                return title;
            }

            public TextView getDate() {
                return date;
            }

            public TextView getText() {
                return text;
            }

            public ImageView getImg1() {
                return img1;
            }

            public ImageView getImg2() {
                return img2;
            }

            public ImageView getImg3() {
                return img3;
            }

            public ImageView getImg4() {
                return img4;
            }

            public ImageView getImg5() {
                return img5;
            }

            public TextView getLikes() {

                return likes;
            }
        }
    }
}
