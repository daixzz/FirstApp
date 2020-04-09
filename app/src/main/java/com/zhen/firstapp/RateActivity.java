package com.zhen.firstapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RateActivity extends AppCompatActivity implements Runnable{


    private final String TAG = "Rate";
    private float dollarRate = 0.1f;
    private float euroRate = 0.2f;
    private float wonRate = 0.3f;
    EditText rmb;
    TextView show;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        rmb = (EditText)findViewById(R.id.rmb);
        show = (TextView)findViewById(R.id.showOut);

        //获得sp里保存的数据
        SharedPreferences sharedPreferences = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        //SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        dollarRate = sharedPreferences.getFloat("dollar_rate",0.0f);
        euroRate = sharedPreferences.getFloat("euro_rate",0.0f);
        wonRate = sharedPreferences.getFloat("won_rate",0.0f);


        Log.i(TAG,"onCreate: sp dollarRate=" +dollarRate);
        Log.i(TAG,"onCreate: sp wonRate=" +wonRate);
        Log.i(TAG,"onCreate: sp euroRate=" +euroRate);


        //开启子线程
        Thread t = new Thread(this);
        t.start();

        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what == 5){
                    String str = (String)msg.obj;
                    Log.i(TAG,"handleMessage:getMessage msg ="+ str);
                }
                super.handleMessage(msg);
            }
        };

    }

    public void onClick(View btn){
        //获取用户输入内容
        Log.i(TAG,"onClick:");
        String str = rmb.getText().toString();
        Log.i(TAG,"onClick:get str="+str);

        float r =0;
        if(str.length()>0){
            r = Float.parseFloat(str);

        }
        else{
            //提示用户输入内容
            Toast.makeText(this, "请输入金额", Toast.LENGTH_SHORT).show();
        }

        if(btn.getId()==R.id.btn_dollar){
            show.setText(String.format("%.2f",r*dollarRate));
        }

        else if(btn.getId()==R.id.btn_euro){
            show.setText(String.format("%.2f",r*euroRate));

        }
        else {
            show.setText(String.format("%.2f",r*wonRate));
        }

    }
    public void openOne(View btn) {
        //打开一个页面Activity

        openConfig();
    }

    private void openConfig() {
        Intent config = new Intent(this, ConfigActivity.class);

        config.putExtra("dollar_rate_key", dollarRate);
        config.putExtra("euro_rate_key", euroRate);
        config.putExtra("won_rate_key", wonRate);


        Log.i(TAG, "openOne:dollar_rate_key=" + dollarRate);
        Log.i(TAG, "openOne:euro_rate_key=" + euroRate);
        Log.i(TAG, "openOne:won_rate_key=" + wonRate);



        startActivityForResult(config, 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.rate,menu);
    return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_set){
            openConfig();
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode , int resultCode , Intent data){
         //requestCode 区分是谁返回的数据；resultCode区分返回的数据通过什么格式去区分
         if(requestCode ==1 && resultCode == 2){
             /*
             bdl.putFloat("key_dollar",newDollar);
        bdl.putFloat("key_euro",newEuro);
        bdl.putFloat("key_won",newWon);
              */
             Bundle bundle = data.getExtras();
             dollarRate = bundle.getFloat("key_dollar",0.1f);
             euroRate = bundle.getFloat("key_euro",0.1f);
             wonRate = bundle.getFloat("key_won",0.1f);

             Log.i(TAG,"onActivityResult:dollarRate=" +dollarRate);
             Log.i(TAG,"onActivityResult:euroRate=" +euroRate);
             Log.i(TAG,"onActivityResult:wonRate=" +wonRate);

             //将新设置的汇率写到sp里

             SharedPreferences sharedPreferences = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
             SharedPreferences.Editor editor = sharedPreferences.edit();
             editor.putFloat("dollar_rate",dollarRate);
             editor.putFloat("euro_rate",euroRate);
             editor.putFloat("won_rate",wonRate);

            editor.commit();
            Log.i(TAG,"onActivityResult:数据已保存到sharedPreference");


         }
         super.onActivityResult(requestCode,resultCode,data);
        }

        public void run(){
            Log.i(TAG,"run:run()......");
            for(int i = 1 ;i<3;i++){
                Log.i(TAG,"run:i="+1);
                try{
                    Thread.sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

            //获取message对象 用于返回主线程
            Message msg = handler.obtainMessage(5);
            //msg.what = S;
            msg.obj = "Hello from run()";
            handler.sendMessage(msg);


            //获取网络数据
            URL url =null;
            try {
                url = new URL("https://www.boc.cn/sourcedb/whpj/");
                HttpURLConnection http = (HttpURLConnection)url.openConnection();
                InputStream in = http.getInputStream();

                String html = inputStream2String(in);
                Log.i(TAG,"run:html="+ html);

            }catch(MalformedURLException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        private String inputStream2String(InputStream inputStream) throws IOException{
            final int bufferSize = 1024;
            final char[] buffer = new char[bufferSize];
            final StringBuilder out = new StringBuilder();
            Reader in = new InputStreamReader(inputStream, "UTF-8");
            for(; ; ){
                int rsz = in.read(buffer,0,buffer.length);
                if(rsz < 0 )
                    break;
                out.append(buffer,0,buffer.length);
            }


            return out.toString();
        }

}
