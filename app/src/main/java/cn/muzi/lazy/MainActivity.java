package cn.muzi.lazy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.muzi.lazyviewpager.IconTabPageIndicator;
import cn.muzi.lazyviewpager.LazyViewPager;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.indicator)
    IconTabPageIndicator mIndicator;
    @BindView(R.id.view_pager)
    LazyViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        List<BaseFragment> fragments = initFragments();
        HomeFragmentAdapter adapter = new HomeFragmentAdapter(fragments, getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
//        mViewPager.setOffscreenPageLimit(fragments.size());
        mIndicator.setViewPager(mViewPager,0);
    }

    private List<BaseFragment> initFragments() {

        List<BaseFragment> fragments = new ArrayList<>();

        BaseFragment userFragment = new TestFragment();

        userFragment.setTitle(getResources().getString(R.string.str_home_event));
        userFragment.setIconId(R.drawable.tab_mine_selector);
        fragments.add(userFragment);

        BaseFragment dayFragment = new TestFragment();

        dayFragment.setTitle(getResources().getString(R.string.str_home_daily));
        dayFragment.setIconId(R.drawable.tab_event_selector);
        fragments.add(dayFragment);

        BaseFragment noteFragment = new TestFragment();
        noteFragment.setTitle(getResources().getString(R.string.str_home_message));
        noteFragment.setIconId(R.drawable.tab_message_selector);
        fragments.add(noteFragment);

        BaseFragment contactFragment = new TestFragment();
        contactFragment.setTitle(getResources().getString(R.string.str_home_collection));
        contactFragment.setIconId(R.drawable.tab_collection_selector);
        fragments.add(contactFragment);


        return fragments;

    }
}
