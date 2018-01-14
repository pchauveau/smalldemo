package com.smalldemo.pch.smalldemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smalldemo.pch.smalldemo.R;
import com.smalldemo.pch.smalldemo.model.ItemInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Basic ArrayAdapter used with the ItemInterface object.
 * Allow to use different types of data model, but never have to adapt this Adapter.
 */
public class ListViewImageAdapter extends ArrayAdapter<ItemInterface> {

    public ListViewImageAdapter(Context context, List<ItemInterface> items) {
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ItemInterface item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_basic_image, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.item_basic_image_iv);
        // By default it is true. Need to implement the boolean to use that.
        // Could also have been done with a Singleton listing the position of the items we are willing to show fullsize.
        if (item.isThumbnailToShow()) {
            Picasso.with(getContext()).load(item.getLinkThumbnail()).placeholder(R.drawable.basic_interogation).into(imageView);
        } else {
            Picasso.with(getContext()).load(item.getLinkFullsizeImage()).placeholder(R.drawable.basic_interogation).into(imageView);
        }

        TextView textView = convertView.findViewById(R.id.item_basic_image_tv);
        try {
            textView.setText(item.getTitle());
        } catch (NullPointerException e) {
            Log.e("Bad", e.getLocalizedMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            Log.e("Bad", e.getLocalizedMessage());
        }

        return convertView;
    }
}