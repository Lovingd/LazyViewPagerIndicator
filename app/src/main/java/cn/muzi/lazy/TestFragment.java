package cn.muzi.lazy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 用途:
 * 作者：tuhui_muzi on 2017/3/28 17:09
 * 邮箱：muzi08168@163.com
 */


public class TestFragment extends BaseFragment {
    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_test, null);
    }

    @Override
    protected void initData() {
        textView.setText(getTitle());
    }

    @Override
    protected void initListener() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
