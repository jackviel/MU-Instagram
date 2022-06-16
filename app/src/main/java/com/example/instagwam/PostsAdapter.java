package com.example.instagwam;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUsername;
        private TextView tvDescription;
        private TextView tvTimeAgo;
        private ImageView ivImage;
        private ImageView ivProfilePic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvTimeAgo = itemView.findViewById(R.id.tvTimeAgo);
            ivImage = itemView.findViewById(R.id.ivImage);
            ivProfilePic = itemView.findViewById(R.id.ivProfilePic);
            ivImage.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Post post = posts.get(position);
                        Intent intent = new Intent(context, PostDetailsActivity.class);
                        intent.putExtra(Post.class.getSimpleName(), Parcels.wrap(post));
                        context.startActivity(intent);
                    }
        }
        });
            ivProfilePic.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Post post = posts.get(position);
                        ParseUser postUser = post.getUser();
                        Intent intent = new Intent(context, ProfileScreenActivity.class);
                        intent.putExtra(ParseUser.class.getSimpleName(), Parcels.wrap(postUser));
                        context.startActivity(intent);
                    }
                }
            });
            tvUsername.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Post post = posts.get(position);
                        ParseUser postUser = post.getUser();
                        Intent intent = new Intent(context, ProfileScreenActivity.class);
                        intent.putExtra(ParseUser.class.getSimpleName(), Parcels.wrap(postUser));
                        context.startActivity(intent);
                    }
                }
            });
        }


        public void bind(Post post) {
            //Bind the post data to the view elements
            tvUsername.setText(post.getUser().getUsername());
            tvDescription.setText(post.getDescription());

            DateFormat formatter = new SimpleDateFormat("MMMM D");
            String date = formatter.format(post.getCreatedAt());
            int dateLength = date.length();
            tvTimeAgo.setText(date.substring(0, dateLength - 2) + date.substring(dateLength - 1, dateLength));

            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivImage);
            } else {
                Glide.with(context).load(R.drawable.icon).into(ivImage);
            }

            ParseUser postUser = post.getUser();
            ParseFile profilePic = postUser.getParseFile("profilePic");
            if (profilePic != null) {
                Glide.with(context).load(profilePic.getUrl()).into(ivProfilePic);
            } else {
                Glide.with(context).load(R.drawable.icon).into(ivProfilePic);
            }
        }
        }
    }