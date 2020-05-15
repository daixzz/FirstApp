package com.zhen.firstapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class MyAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater listContainer;
    private List<String> listItems;

    private TextView textView1;


    public void ListViewAdapter(Context context,List<String> list) {
// TODO Auto-generated constructor stub
        this.context = context;
        this.listItems = list;
        this.listContainer = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
// TODO Auto-generated method stub
        return listItems.size();
    }

    @Override
    public Object getItem(int arg0) {
// TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
// TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
// TODO Auto-generated method stub
        String str = listItems.get(position).toString();

            convertView = listContainer.inflate(R.layout.list_item2, null);
            textView1 = (TextView)convertView.findViewById(R.id.SearchTitle);
            textView1.setText(listItems.get(position));
            textView1.setTextColor(Color.BLACK);
            return convertView;

        }
}
