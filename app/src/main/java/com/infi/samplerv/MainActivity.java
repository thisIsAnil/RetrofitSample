package com.infi.samplerv;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.infi.samplerv.adapter.FragmentAdapter;
import com.infi.samplerv.fragment.PhotoFragment;
import com.infi.samplerv.helper.ConnectionHelper;
import com.infi.samplerv.helper.FileLog;
import com.infi.samplerv.helper.RetrofitHelper;
import com.infi.samplerv.helper.RetrofitInterface;
import com.infi.samplerv.helper.RetryListener;
import com.infi.samplerv.models.Model;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by INFIi on 11/21/2017.
 */

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.list_btn)
    AppCompatImageView listBtn;
    @BindView(R.id.post_btn)
    AppCompatImageView postBtn;

    private PhotoFragment listFragment;
    private PhotoFragment postFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
            ButterKnife.bind(this);

            setupViewPager();

        try {
            if (ConnectionHelper.isNetworkAvailable(this)) {
                loadDataUsingRetrofit();
            } else {
                showNoInternet();
            }
        }catch (Throwable t){
            FileLog.e(t);
        }
    }

    @OnClick(R.id.post_btn)
    void onPostBtnClicked(){
        if(viewPager!=null)viewPager.setCurrentItem(1,true);
    }

    @OnClick(R.id.list_btn)
    void onListBtnClicked(){
        if(viewPager!=null)viewPager.setCurrentItem(0,true);
    }
    private void setupViewPager(){

        RetryListener retryListener=new RetryListener() {
            @Override
            public void onRetryRequest() {
                listFragment.showLoading();
                postFragment.showLoading();
                if(ConnectionHelper.isNetworkAvailable(MainActivity.this)) {
                    loadDataUsingRetrofit();
                }else {
                    showNoInternet();//TODO delay using handler for more realistic feel
                }

            }
        };
        listFragment=new PhotoFragment();
        postFragment=new PhotoFragment();
        listFragment.setRetryListener(retryListener);
        postFragment.setRetryListener(retryListener);
        FragmentAdapter adapter=new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(listFragment,"List");
        adapter.addFragment(postFragment,"Post");
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
    }

    private void showNoInternet(){
        listFragment.setNoInternet(getString(R.string.no_internet));
        postFragment.setNoInternet(getString(R.string.no_internet));
    }
    private void loadDataUsingRetrofit(){
        RetrofitInterface retrofitInterface = RetrofitHelper.getInstance().create(RetrofitInterface.class);
        Call<List<Model>> call = retrofitInterface.getPhotos();

        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                List<Model> dataList = response.body();
                listFragment.loadRV(dataList, true);
                postFragment.loadRV(dataList, false);

            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                    listFragment.showError(t.getMessage());
                    postFragment.showError(t.getMessage());
            }
        });

    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position==0){
            listBtn.setImageResource(R.drawable.list_on);
            postBtn.setImageResource(R.drawable.post_off);
        }else {
            listBtn.setImageResource(R.drawable.list_off);
            postBtn.setImageResource(R.drawable.post_on);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
