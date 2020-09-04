package com.chingaridesigin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.chingaridesigin.model.HashTagsModel;

import java.util.ArrayList;

public class HashTagsAdapter extends RecyclerView.Adapter<HashTagsAdapter.ViewHolder> {

    private ArrayList<HashTagsModel> list;
    LayoutInflater inflater;

    public HashTagsAdapter(Context context, ArrayList<HashTagsModel> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public HashTagsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_tag_property_type,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HashTagsAdapter.ViewHolder holder, int position) {
        holder.bindItems(list.get(position));
    }

    @Override
    public int getItemCount() {
        return   list == null ? 0 : list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        public ViewHolder(@NonNull View itemView) { super(itemView);
            tv= itemView.findViewById(R.id.tv_level);
        }
        public void bindItems(final HashTagsModel postModel) {
           tv.setText(postModel.getHashtag());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((PostActivityDemo) view.getContext()).addTags(postModel.getHashtag());
                }
            });
        }
    }
}