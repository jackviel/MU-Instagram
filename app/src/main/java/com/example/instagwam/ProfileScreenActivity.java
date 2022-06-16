package com.example.instagwam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.Parse;
import com.parse.ParseFile;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ProfileScreenActivity extends AppCompatActivity {

    TextView tvUsername;
    ImageView ivProfilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        tvUsername = findViewById(R.id.tvUsername);
        ivProfilePic = findViewById(R.id.ivProfilePic);

        ParseUser user = (ParseUser) Parcels.unwrap(getIntent().getParcelableExtra(ParseUser.class.getSimpleName()));
        Log.i("usercheck", user.toString());
        this.bind(user);
    }

    public void bind(ParseUser user){

        tvUsername.setText(user.getString("username"));
        ParseFile image = user.getParseFile("profilePic");
        if (image != null){
            Glide.with(this).load(image.getUrl()).into(ivProfilePic);
        }
    }
}