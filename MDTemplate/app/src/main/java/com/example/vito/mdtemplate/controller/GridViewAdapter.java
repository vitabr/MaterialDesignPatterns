package com.example.vito.mdtemplate.controller;

import java.util.ArrayList;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.vito.myheritage.R;
import com.vito.myheritage.model.SearchResult;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class GridViewAdapter extends ArrayAdapter<SearchResult> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<SearchResult> data = new ArrayList<SearchResult>();

    public GridViewAdapter(Context context, int layoutResourceId, ArrayList<SearchResult> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) row.findViewById(R.id.imageView);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }


        SearchResult item = data.get(position);
        holder.image.setImageResource(R.drawable.anim_loading);
        ImageLoader.getInstance().displayImage(item.unescapedUrl, holder.image);
        return row;
    }

    static class ViewHolder {
        ImageView image;
    }
}