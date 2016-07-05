package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter extends BaseAdapter {
    private static final String TAG = "CustomAdapter";

    String[] prgmNameList;
    Context context;
    int[] imageId;
    private static LayoutInflater inflater = null;

    public CustomAdapter(Activity activity, String[] prgmNameList, int[] prgmImages) {
        this.prgmNameList = prgmNameList;
        context = activity;
        imageId = prgmImages;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return prgmNameList.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder {
        TextView tv;
        ImageView img;
    }

    /**
     * Used recommended view holder pattern
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "position is " + position);
        Holder holder = new Holder();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.program_list, null);
            holder.tv = (TextView) convertView.findViewById(R.id.textView1);
            holder.img = (ImageView) convertView.findViewById(R.id.imageView1);
            holder.tv.setText(prgmNameList[position]);
            holder.img.setImageResource(imageId[position]);
            convertView.setTag(holder);

            convertView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "You Clicked " + prgmNameList[position], Toast.LENGTH_LONG).show();
                }
            });
        }
        else {
            holder = (Holder)convertView.getTag();
            holder.tv.setText(prgmNameList[position]);
            holder.img.setImageResource(imageId[position]);
        }

        return convertView;
    }
}
