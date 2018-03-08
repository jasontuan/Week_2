package com.example.anhtuan.week_2.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anhtuan.week_2.R;
import com.example.anhtuan.week_2.contract.IArticle;
import com.example.anhtuan.week_2.model.Doc;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DataViewHolder> {

    IArticle.OnItemClickListener onItemClickListener;
    Context context;
    List<Doc> docList;

    public void setOnItemClickListener(IArticle.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public RecyclerViewAdapter(Context context, List<Doc> docList) {
        this.context = context;
        this.docList = docList;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_main, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Doc doc = docList.get(position);
//        Glide.with(context).load("http://image.tmdb.org/t/p/w500" + doc.getUr)
        holder.tvSnippet.setText(doc.getSnippet());
        holder.cvArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.OnItemClick(position, true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return docList != null ? docList.size() : 0;
    }

    static class DataViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cv_article)
        CardView cvArticle;
        @BindView(R.id.img_article)
        ImageView imgArticle;
        @BindView(R.id.tv_snippet)
        TextView tvSnippet;

        public DataViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
