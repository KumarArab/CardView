package com.example.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class PostAdapter extends FirebaseRecyclerAdapter<Blog, PostAdapter.BlogViewHolder> {

    public PostAdapter(@NonNull FirebaseRecyclerOptions<Blog> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull BlogViewHolder viewHolder, int position, @NonNull Blog model) {
        viewHolder.setTitle(model.getTitle());
        viewHolder.setDescription(model.getDesc());
        viewHolder.setImage(model.getImage());
    }

    @NonNull
    @Override
    public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.blog_row, parent, false);
        return new BlogViewHolder(view);
    }

    static class BlogViewHolder extends RecyclerView.ViewHolder{
        View mView;
        BlogViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        void setTitle(String title){
            System.out.println(title);
            TextView post_Title = mView.findViewById(R.id.post_title);
            post_Title.setText(title);
        }
        void setDescription(String desc){
            System.out.println(desc);
            TextView post_description = mView.findViewById(R.id.post_description);
            post_description.setText(desc);
        }
        void setImage(String image){
            System.out.println(image);
            ImageView post_Image = mView.findViewById(R.id.post_image);
            Picasso.get().load(image).placeholder(R.drawable.ic_call).into(post_Image);
        }
    }


}
