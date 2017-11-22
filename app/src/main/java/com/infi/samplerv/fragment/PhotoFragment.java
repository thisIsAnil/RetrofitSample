package com.infi.samplerv.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.infi.samplerv.R;
import com.infi.samplerv.adapter.PhotoAdapter;
import com.infi.samplerv.helper.FileLog;
import com.infi.samplerv.helper.RetryListener;
import com.infi.samplerv.models.Model;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by INFIi on 11/22/2017.
 */

public class PhotoFragment extends Fragment {

    @BindView(R.id.main_rv)
    RecyclerView recyclerView;

    @BindView(R.id.error_msg_tv)
    TextView errorMsgTv;

    @BindView(R.id.loading_bar)
    ProgressBar loadingBar;

    @BindView(R.id.retry_btn)
    Button retryBtn;

    private boolean showError=false;
    private String errorMsg;
    private RetryListener retryListener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.photo_fragment,container,false);
        ButterKnife.bind(this,root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if(showError){
            showError(errorMsg);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.retry_btn)
    void onRetry(){
        if(retryListener!=null){
            retryListener.onRetryRequest();
        }
    }

    public void setRetryListener(RetryListener retryListener) {
        this.retryListener = retryListener;
    }

    //Called when data is downloaded and ready to be displayed to user
    public void loadRV(List<Model> dataList,boolean isBasic){
            if (dataList == null || dataList.size() == 0) {
                showError(getActivity().getString(R.string.error));
                return;
            }
            hideLoading();
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            PhotoAdapter adapter = new PhotoAdapter(getActivity(), dataList, isBasic);
            recyclerView.setAdapter(adapter);
    }

    //hide loading view when data is ready to be loaded in recycler view
    private void hideLoading(){
        recyclerView.setVisibility(View.VISIBLE);
        loadingBar.setVisibility(View.GONE);
        errorMsgTv.setVisibility(View.GONE);
        retryBtn.setVisibility(View.GONE);
    }

    //show loading view when retry btn is pressed
    public void showLoading(){
        recyclerView.setVisibility(View.GONE);
        retryBtn.setVisibility(View.GONE);
        loadingBar.setVisibility(View.VISIBLE);
        errorMsgTv.setVisibility(View.VISIBLE);
        errorMsgTv.setText(getActivity().getString(R.string.loading));
    }

    //Called when any error occurs.hides recycler view and shows error msg
    public void showError(String message){
        try {
            recyclerView.setVisibility(View.GONE);
            loadingBar.setVisibility(View.GONE);
            errorMsgTv.setVisibility(View.VISIBLE);
            errorMsgTv.setText(message);
            retryBtn.setVisibility(View.VISIBLE);
        }catch (Exception e){
            FileLog.e(e);
        }
    }

    public void setNoInternet(String message){
        if(recyclerView==null){
            showError=true;
            errorMsg=message;
        }
        else showError(message);
    }

}
