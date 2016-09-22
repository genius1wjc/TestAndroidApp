package com.example.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jiechao on 9/22/16.
 */

public class AnimAdapter extends RecyclerView.Adapter<AnimAdapter.ViewHolder> {

    private static final String TAG = AnimAdapter.class.getSimpleName();

    private Context context;

    // The items to display in your RecyclerView
    private List<String> items;
    // Allows to remember the last item shown on screen
    private int lastPosition = -1;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        // You need to retrieve the container (ie the root ViewGroup from your custom_item_layout)
        // It's the view that will be animated
        FrameLayout container;

        public ViewHolder(View itemView) {
            super(itemView);
            container = (FrameLayout) itemView.findViewById(R.id.item_layout_container);
            text = (TextView) itemView.findViewById(R.id.item_layout_text);
        }
    }

    public AnimAdapter(List<String> items, Context context) {
        this.items = items;
        this.context = context;
    }

    private int a = 1, b = 1;

    @Override
    public AnimAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "Creating view holder " + a++);

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.i(TAG, "Binding view holder " + b++);

        holder.text.setText(items.get(position));
        // Here you apply the animation when the view is bound
        setAnimation(holder.container, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

}
