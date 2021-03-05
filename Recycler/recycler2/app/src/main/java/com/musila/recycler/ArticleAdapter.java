package com.musila.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder> {

    ArrayList<Article> articles;
    public ArticleAdapter() {

        articles=new ArrayList<>();
    }

    public void setData(ArrayList<Article> articles){
        this.articles=articles;
    }


    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View articleView=layoutInflater.inflate(R.layout.recycler_row,parent,false);
        return new ArticleViewHolder(articleView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article=articles.get(position);
        Picasso.get().load(article.image).into(holder.image);
        holder.title.setText(article.body);
        holder.body.setText(article.body);

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
