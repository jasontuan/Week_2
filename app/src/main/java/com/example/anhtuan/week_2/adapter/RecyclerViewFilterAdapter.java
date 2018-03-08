package com.example.anhtuan.week_2.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anhtuan.week_2.R;
import com.example.anhtuan.week_2.contract.IArticle;
import com.example.anhtuan.week_2.model.ImageFilter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewFilterAdapter extends RecyclerView.Adapter<RecyclerViewFilterAdapter.DataFilterViewHolder> {

    private IArticle.OnItemClickListener onItemClickListener;
    private Context context;
    private List<ImageFilter> imageFilterList;
    private int curPos = -1;

    public RecyclerViewFilterAdapter(Context context, List<ImageFilter> imageFilterList) {
        this.context = context;
        this.imageFilterList = imageFilterList;

    }

    public void setCurPos(int curPos) {
        this.curPos = curPos;
    }

    public void setOnItemClickListener(IArticle.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public DataFilterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_filter, parent, false);
        return new DataFilterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DataFilterViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final ImageFilter imageFilter = imageFilterList.get(position);
        Glide.with(context).load(imageFilter.getImageLink()).into(holder.imgDesk);
        holder.tvDesk.setText(imageFilter.getImageName());
        holder.cvDesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curPos = position;
                if (!imageFilter.isSelected()) {
                    onItemClickListener.OnItemClick(position, true);
                } else {
                    onItemClickListener.OnItemClick(position, false);
                }
                notifyDataSetChanged();
            }
        });
        if (position == curPos && !imageFilter.isSelected()) {
            holder.rlItemsFilter.setBackgroundColor(Color.YELLOW);
            imageFilter.setSelected(true);
        } else {
            holder.rlItemsFilter.setBackgroundColor(Color.WHITE);
            imageFilter.setSelected(false);
        }
    }

    @Override
    public int getItemCount() {
        return imageFilterList != null ? imageFilterList.size() : 0;
    }

    static class DataFilterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_desk)
        ImageView imgDesk;
        @BindView(R.id.tv_desk)
        TextView tvDesk;
        @BindView(R.id.cv_desk)
        CardView cvDesk;
        @BindView(R.id.rl_itemsfilter)
        RelativeLayout rlItemsFilter;

        DataFilterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
