package cn.muzi.lazy;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import butterknife.ButterKnife;

/**
 * Created by XY on 2016/9/11.
 */
public abstract class BaseFragment extends Fragment {

    /**
     * 这两个都是
     */
    private String title;
    private int iconId;
    private int textId;
    private int tabgapId;
    private int viewchooseId;

    public int getTextId() {
        return textId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public int getTabgapId() {
        return tabgapId;
    }

    public void setTabgapId(int tabgapId) {
        this.tabgapId = tabgapId;
    }

    public int getViewchooseId() {
        return viewchooseId;
    }

    public void setViewchooseId(int viewchooseId) {
        this.viewchooseId = viewchooseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    private View mRootView;
    public Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = initView(inflater,container);
        ButterKnife.bind(this, mRootView);//绑定到butterknife
        mContext = this.getContext();

        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("TTT","--------------------->");
        initData();
        initListener();

    }

    protected abstract View initView(LayoutInflater inflater, ViewGroup container);
    protected abstract void initData();
    protected abstract void initListener();


    private ConnectivityManager manager;
    /**
     * 判断是否有网
     * @return
     */
    public boolean checkNetworkState(Context mContext){
        boolean flag=false;
        //得到网络连接信息
        manager=(ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        //去进行判断网络是否连接
        if(manager.getActiveNetworkInfo()!=null){
            flag=manager.getActiveNetworkInfo().isAvailable();
        }
        return flag;
    }


}
