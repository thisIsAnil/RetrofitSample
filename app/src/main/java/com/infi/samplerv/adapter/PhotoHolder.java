package com.infi.samplerv.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.infi.samplerv.R;
import com.infi.samplerv.helper.FileLog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by INFIi on 11/22/2017.
 */

class PhotoHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.holder_title)
    TextView title;

    @BindView(R.id.holder_thumbnail)
    ImageView photo;

    PhotoHolder(View view){
        super(view);
        ButterKnife.bind(this, view);
    }
}
