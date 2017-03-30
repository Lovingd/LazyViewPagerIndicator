package cn.muzi.lazy;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import cn.muzi.lazyviewpager.IconPagerAdapter;


/**
 * 南京图慧信息技术有限公司
 *
 * 管理主屏切换页面
 * Created by Orchid on 2017/3/23 9:39.
 */

public class HomeFragmentAdapter extends FragmentPagerAdapter implements IconPagerAdapter {
    private List<BaseFragment> mFragments;

    public HomeFragmentAdapter(List<BaseFragment> fragments, FragmentManager fm) {
        super(fm);
        mFragments = fragments;
    }

    public List<BaseFragment> getFragments()
    {
        return mFragments;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getIconResId(int index) {
        return mFragments.get(index).getIconId();
    }

    @Override
    public int getTextColorId(int index) {
        return mFragments.get(index).getTextId();
    }

    @Override
    public int getTabGapId(int index) {
        return mFragments.get(index).getTabgapId();
    }

    @Override
    public int getViewChooseId(int index) {
        return mFragments.get(index).getViewchooseId();
    }

    @Override
    public boolean isShowChoose() {
        return false;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }
}
