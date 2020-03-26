package com.zhen.firstapp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView out;
    EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        out=(TextView)findViewById(R.id.TextView);
        edit=(EditText)findViewById(R.id.EditText);

        Button btn=(Button)findViewById(R.id.button);
        btn.setOnClickListener(this);
    }
    public void onClick(View v){
        Log.i("main","onClick msg....");

        String str=edit.getText().toString();
        double num=Double.parseDouble(str);
        if(num<=-273){
            out.setText("请输入正确的温度");
        }
        else{
        double hua=num*1.8+32;
        out.setText(String.valueOf(hua+"华氏度"));
        }
    }



    }


