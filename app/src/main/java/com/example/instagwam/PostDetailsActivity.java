package com.example.instagwam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PostDetailsActivity extends AppCompatActivity {

    TextView tvUsername;
    TextView tvDescription;
    TextView tvTimeAgo;
    ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        tvUsername = findViewById(R.id.tvUsername);
        tvDescription = findViewById(R.id.tvDescription);
        ivImage = findViewById(R.id.ivImage);
        tvTimeAgo = findViewById(R.id.tvTimeAgo);

        Post post = (Post) Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));
        this.bind(post);

    }

    public void bind(Post post){

        tvUsername.setText(post.getUser().getUsername());
        tvDescription.setText(post.getDescription());
        DateFormat formatter = new SimpleDateFormat("MMMM D");
        String date = formatter.format(post.getCreatedAt());
        int dateLength = date.length();
        tvTimeAgo.setText(date.substring(0, dateLength - 2) + date.substring(dateLength - 1, dateLength));
        ParseFile image = post.getImage();
        if (image != null){
            Glide.with(this).load(image.getUrl()).into(ivImage);
        }
    }
}