package com.sound.app.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sound.app.R;

import java.util.ArrayList;

/**
 * Created by Francis on 2015-06-10.
 */
public class ContentListAdapter extends BaseAdapter {

    private Context lefticon;
    private LayoutInflater Inflater;
    private ArrayList<Content> contents;
    private int layout;

    public ContentListAdapter(Context context, int layout, ArrayList<Content> contents) {
        this.lefticon=context;
        this.Inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.contents=contents;
        this.layout=layout;
    }

    public int getCount() {
        return contents.size();
    }

    public String getItem(int position) {
        return contents.get(position).getText();
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        final int pos=position;
        if(convertView==null) {
            convertView=Inflater.inflate(layout, parent, false);
        }

        ImageView img=(ImageView)convertView.findViewById(R.id.img);
        img.setImageResource(contents.get(position).getIcon());

        TextView txt=(TextView)convertView.findViewById(R.id.text);
        txt.setText(contents.get(position).getText());
        return convertView;
    }
}
