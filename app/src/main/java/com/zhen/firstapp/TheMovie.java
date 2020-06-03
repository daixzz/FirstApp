package com.zhen.firstapp;

import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TheMovie extends AppCompatActivity {
    private final String TAG = "Rate";

    String updateTime = "";
    EditText searchMovie;
    TextView show;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_movie);
    }
}
