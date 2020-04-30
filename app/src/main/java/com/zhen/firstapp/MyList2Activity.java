package com.zhen.firstapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MyList2Activity extends ListActivity {

    Handler handler;
    private ArrayList<HashMap<String,String>> listItems;
    private SimpleAdapter listItemAdapter;
    private int msgwhat = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        initListView();
        this.setListAdapter(listItemAdapter);
    }

    private void initListView(){
        listItems = new ArrayList<HashMap<String, String>>();
        for(int i = 0;i < 10;i++){
            HashMap<String,String> map = new HashMap<String, String>();
            map.put("ItemTitle","Rate:" + i);
            map.put("ItemDetail","detail:" + i);
            listItems.add(map);
        }
        listItemAdapter = new SimpleAdapter(this,listItems,
        R.layout.list_item,
                new String[]{"ItemTitle","ItemDetail"},
                new int[]{R.id.itemTitle,R.id.itemDetail}
        );
    }
}
