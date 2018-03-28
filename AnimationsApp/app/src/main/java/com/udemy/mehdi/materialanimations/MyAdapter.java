package com.udemy.mehdi.materialanimations;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by johndoe on 3/20/18.
 */

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context mContext;
    OnItemClickListener itemClickListener;
    public static final int TITLES [] = {
      R.string.ripple,
            R.string.circular_reveal,
            R.string.activity_transition,
            R.string.animation_move_view,
            R.string.animation_lottie,
            R.string.constraint_layout_animations,
            R.string.slide_animation,
            R.string.simple_interpolator,
            R.string.animated_vector_drawable,


    };

    public MyAdapter(Context ctx) {
        mContext = ctx;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(TITLES[position]);
    }


    @Override
    public int getItemCount() {
        return TITLES.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemClickListener != null)
            itemClickListener.onItemClick(view, getAdapterPosition());
            else throw new IllegalArgumentException("OnClickListener not implemented");
        }
    }
    interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnClickListener(OnItemClickListener clickListener) {
        itemClickListener = clickListener;
    }

}
