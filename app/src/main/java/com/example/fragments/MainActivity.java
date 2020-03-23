package com.example.fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Global");
        mDatabase.keepSynced(true);

        mBlogList=findViewById(R.id.MyRecyclerView);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        Query query = mDatabase;
        FirebaseRecyclerOptions<Blog> options =
                new FirebaseRecyclerOptions.Builder<Blog>().setQuery(query, new SnapshotParser<Blog>() {
                            @NonNull
                            @Override
                            public Blog parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new Blog(Objects.requireNonNull(snapshot.child("title").getValue()).toString(),
                                        Objects.requireNonNull(snapshot.child("desc").getValue()).toString(),
                                        Objects.requireNonNull(snapshot.child("image").getValue()).toString());
                            }
                        }).build();
        FirebaseRecyclerAdapter<Blog,BlogViewHolder> adapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull BlogViewHolder viewHolder, int i, @NonNull Blog model) {
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
        };

        mBlogList.setAdapter(adapter);

    }
    public static class BlogViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public BlogViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setTitle(String title){
            TextView post_Title = mView.findViewById(R.id.post_title);
            post_Title.setText(title);
        }
        public void setDescription(String desc){
            TextView post_description = mView.findViewById(R.id.post_description);
            post_description.setText(desc);
        }
        public void setImage(String image){
            ImageView post_Image = mView.findViewById(R.id.post_image);
            Picasso.get().load(image).placeholder(R.drawable.ic_call).into(post_Image);
        }
    }
}
