package com.zhen.firstapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RateCalcActivity extends AppCompatActivity {

    EditText ip2;
    String TAG = "rateCalc";
    float rate;
    TextView show2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_calc);

        ip2 = (EditText) findViewById(R.id.ip2);
        show2 = (TextView) findViewById(R.id.show2);
        String title = getIntent().getStringExtra("title");

        rate = getIntent().getFloatExtra("rate",0f);

        Log.i(TAG,"onCreate:title = " + title);
        Log.i(TAG,"onCreate:rate = " + rate);
        ((TextView)findViewById(R.id.title2)).setText(title);


    }
    public void onClick(View btn){
        String str = ip2.getText().toString();
        float r = 0 ;
        if(str.length()>0){
            r = Float.parseFloat(str);

        }
        else{
            //提示用户输入内容
            Toast.makeText(this, "请输入金额", Toast.LENGTH_SHORT).show();
        }
            float val = r*rate;
            show2.setText(String.valueOf(val));

    }
}
