package com.zhen.firstapp;

import android.app.Activity;
import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class SearchkwActivity extends ListActivity implements Runnable {

    private final String TAG = "Searchkw";
    EditText srch;
    TextView show;
    Handler handler;
    String data[] = {"wait..."};
    private ArrayList<HashMap<String, String>> listItems;
    private SimpleAdapter listItemAdapter;
    private int msgwhat = 7;
    String updateDate = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchkw);
        initListView();
        this.setListAdapter(listItemAdapter);

        Thread t = new Thread(this);
        t.start();
        srch = (EditText) findViewById(R.id.Search);


        SharedPreferences sharedPreferences = getSharedPreferences("", Activity.MODE_PRIVATE);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String todayStr = sdf.format(today);


        final List<String> list1 = new ArrayList<String>();
        for (int i = 1; i < 100; i++) {
            list1.add("item" + i);

        }

        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        setListAdapter(adapter);


        handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 7) {
                    List<HashMap<String, String>> list2 = (List<HashMap<String, String>>) msg.obj;
                    listItemAdapter = new SimpleAdapter(SearchkwActivity.this, list2,
                            R.layout.activity_sear_list,
                            new String[]{"SearchTitle"},
                            new int[]{R.id.SearchTitle}
                    );
                    setListAdapter(listItemAdapter);
                }
                super.handleMessage(msg);
            }
        };
/*        if(!todayStr.equals(updateDate)){
            Log.i(TAG, "onCreate: :" + "需要更新");

            //开启子线程
            Thread d = new Thread(this);
            d.start();
        }
        else{
            Log.i(TAG, "onCreate: :" + "不需要更新");

        }*/
    }

    public void run() {
        Log.i("thread", "run......");
        List<String> retList = new ArrayList<String>();

        Document doc = null;
        try {
//            Thread.sleep(1000);
            doc = Jsoup.connect("https://it.swufe.edu.cn/index/tzgg/56.htm").get();
            Log.i(TAG, "run:" + doc.title());
            Elements divs = doc.getElementsByTag("div");

            /*int i =1;
            for(Element div : divs){
                Log.i(TAG,"run:table["+i+"]=" + div);
                i++;
            }*/

            Element divs19 = divs.get(18);
            Log.i(TAG, "run:div19=" + divs19);

            //获取span中的数据
            Elements spans = divs19.getElementsByTag("span");
            for (int i = 0; i < spans.size(); i += 2) {
                Element span1 = spans.get(i);
                Log.i(TAG, "run:text=" + span1.text());
            }

//            for(Element span:spans) {
//                Log.i(TAG, "run:spans" + span);
//                Log.i(TAG, "run:text" + span.text());
//                Log.i(TAG, "run:html" + span.html());
//
//            }


//
//                HashMap<String,String> map = new HashMap<String, String>();
//                map.put("ItemTitle",str1);
//                map.put("ItemDetail",val);
//                retList.add(map);
        }


//        } catch (InterruptedException e) {
//            e.printStackTrace();
        catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void initListView() {
        listItems = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("SearchTitle", "" + i);
            listItems.add(map);
        }
        listItemAdapter = new SimpleAdapter(this, listItems,
                R.layout.activity_sear_list,
                new String[]{"SearchTitle"},
                new int[]{R.id.SearchTitle}
        );


    }
}
