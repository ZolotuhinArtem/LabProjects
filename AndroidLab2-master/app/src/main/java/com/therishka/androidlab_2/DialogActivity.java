package com.therishka.androidlab_2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.therishka.androidlab_2.models.VkDialog;
import com.therishka.androidlab_2.models.VkDialogResponse;
import com.therishka.androidlab_2.models.VkFriend;
import com.therishka.androidlab_2.network.RxVk;

import java.util.List;

public class DialogActivity extends AppCompatActivity {

    ProgressBar mProgress;
    RecyclerView mRecyclerList;
    RecyclerDialogAdapter mRecyclerDialogAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        mProgress = (ProgressBar) findViewById(R.id.dialog_loading_view);
        mRecyclerList = (RecyclerView) findViewById(R.id.dialog_list);

        mRecyclerDialogAdapter = new RecyclerDialogAdapter(this);
        mRecyclerList.setAdapter(mRecyclerDialogAdapter);
        mRecyclerList.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerList.setVisibility(View.GONE);
        mProgress.setVisibility(View.VISIBLE);
        RxVk api = new RxVk();
        api.getDialogs(new RxVk.RxVkListener<VkDialogResponse>() {
            @Override
            public void requestFinished(VkDialogResponse requestResult) {
                mRecyclerDialogAdapter.setDialogList(requestResult.getDialogs());
                mRecyclerList.setVisibility(View.VISIBLE);
                mProgress.setVisibility(View.GONE);
            }
        });

    }

    private class RecyclerDialogAdapter extends RecyclerView.Adapter<RecyclerDialogAdapter.DialogViewHolder>{

        private List<VkDialog> mDialogList;
        private Context mContext;

        public RecyclerDialogAdapter(Context context) {
            mContext = context;
        }

        @Override
        public RecyclerDialogAdapter.DialogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_item, parent, false);
            return new DialogViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerDialogAdapter.DialogViewHolder holder, int position) {
            if(holder instanceof DialogViewHolder){
                VkDialog vkd = mDialogList.get(position);
                if (vkd != null) {
                    String title;
                    title = vkd.getTitle();
                    if (title.equals(" ... ")){
                        title = vkd.getUsername();
                    }
                    holder.getTitle().setText(title);
                    holder.getMessage().setText(vkd.getMessage());
                    holder.getIsRead().setVisibility(vkd.is_read() ? ViewGroup.INVISIBLE : ViewGroup.VISIBLE);
                    Glide.with(mContext).load(vkd.getPhoto())
                            .fitCenter()
                            .into(((DialogViewHolder) holder).getPhoto());
                }
            }
        }


        @Override
        public int getItemCount() {
            return mDialogList != null ? mDialogList.size() : 0;
        }

        public void setDialogList(List<VkDialog> dialogList) {
            this.mDialogList = dialogList;
            notifyDataSetChanged();
        }


        public class DialogViewHolder extends RecyclerView.ViewHolder{
            private ImageView photo;
            private TextView title;
            private TextView message;
            private View isRead;

            public DialogViewHolder(View itemView) {
                super(itemView);

                photo = (ImageView) itemView.findViewById(R.id.dialog_avatar);
                title = (TextView) itemView.findViewById(R.id.dialog_title);
                message = (TextView) itemView.findViewById(R.id.dialog_message);
                isRead = (View) itemView.findViewById(R.id.dialog_is_read);

            }

            public ImageView getPhoto() {
                return photo;
            }

            public TextView getTitle() {
                return title;
            }

            public TextView getMessage() {
                return message;
            }

            public View getIsRead() {
                return isRead;
            }
        }
    }
}
