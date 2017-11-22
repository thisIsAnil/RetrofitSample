package com.infi.samplerv.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infi.samplerv.R;
import com.infi.samplerv.helper.FileLog;
import com.infi.samplerv.models.Model;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by INFIi on 11/22/2017.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder> {

    private List<Model> dataList;
    private Context context;
    private boolean isBasic;
    private View root;
    public PhotoAdapter(Context context,List<Model> dataList){
        this.context=context;
        this.dataList=dataList;
    }

    public PhotoAdapter(Context context, List<Model> dataList, boolean isBasic) {
        this.dataList = dataList;
        this.context = context;
        this.isBasic = isBasic;
    }

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        root=isBasic?LayoutInflater.from(context).inflate(R.layout.photo_holder_layout, parent,false):LayoutInflater.from(context).inflate(R.layout.post_holder_layout, parent,false);
        return new PhotoHolder(root);
    }

    @Override
    public void onBindViewHolder(PhotoHolder holder, int position) {
        try {
            if (holder == null) holder = new PhotoHolder(root);
            Model model = dataList.get(position);
            holder.title.setText(model.getName());
            if(isBasic)Picasso.with(context).load(Uri.parse(model.getThumbnailUrl())).into(holder.photo);
            else Picasso.with(context).load(Uri.parse(model.getUrl())).into(holder.photo);
        }catch (Throwable e){
            FileLog.e(e);
        }
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
