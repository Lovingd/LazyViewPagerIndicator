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

    /**
     * 设置底部图标的变化
     * @param index
     * @return
     */
    @Override
    public int getIconResId(int index) {
        return mFragments.get(index).getIconId();
    }

    /**
     * 设置底部文字的颜色
     * @param index
     * @return
     */
    @Override
    public int getTextColorId(int index) {
        return mFragments.get(index).getTextId();
    }

    /**
     * 设置间隙的颜色值
     * @param index
     * @return
     */
    @Override
    public int getTabGapId(int index) {
        return mFragments.get(index).getTabgapId();
    }

    /**
     * 设置底部选择的线的颜色
     * @param index
     * @return
     */
    @Override
    public int getViewChooseId(int index) {
        return mFragments.get(index).getViewchooseId();
    }

    /**
     * 设置底部选择的线   是否显示
     * @return
     */
    @Override
    public boolean isShowChoose() {
        return true;
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
