package com.zhen.firstapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RateActivity extends AppCompatActivity {

    EditText rmb;
    TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        rmb = (EditText)findViewById(R.id.rmb);
        show = (TextView)findViewById(R.id.showOut);
    }

    public void onClick(View btn){
        //获取用户输入内容
        String str = rmb.getText().toString();
        float r =0;
        if(str.length()>0){
            r = Float.parseFloat(str);

        }
        else{
            //提示用户输入内容
            Toast.makeText(this, "请输入金额", Toast.LENGTH_SHORT).show();
        }
        float val;
        if(btn.getId()==R.id.btn_dollar){
            val = r * (1/7.1f);
            show.setText(String.valueOf(val));
        }

        else if(btn.getId()==R.id.btn_euro){
            val = r * (1/11f);
            show.setText(String.valueOf(val));

        }
        else {
            val =r *174;
            show.setText(String.valueOf(val));
        }

    }
    public void openOne(View btn){
        //打开一个页面Activity
        Log.i("open", "openOne: ");
        Intent hello =new Intent(this,SecondActivity.class);
        Intent web = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.jd.com"));
        startActivity(web);
    }


}
