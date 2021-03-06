package cn.muzi.lazyviewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;


/**
 * User: Geek_Soledad(msdx.android@qq.com)
 * Date: 2014-08-27
 * Time: 09:20
 * FIXME
 */
public class IconTabPageIndicator extends LinearLayout implements PageIndicator {
    /**
     * Title text used when no title is provided by the adapter.
     */
    private static final CharSequence EMPTY_TITLE = "";

    /**
     * Interface for a callback when the selected tab has been reselected.
     */
    public interface OnTabReselectedListener {
        /**
         * Callback when the selected tab has been reselected.
         *
         * @param position Position of the current center item.
         */
        void onTabReselected(int position);
    }

    private Runnable mTabSelector;

    private final OnClickListener mTabClickListener = new OnClickListener() {
        public void onClick(View view) {
            TabView tabView = (TabView) view;
            final int oldSelected = mViewPager.getCurrentItem();
            final int newSelected = tabView.getIndex();
            mViewPager.setCurrentItem(newSelected, false);
            if (oldSelected == newSelected && mTabReselectedListener != null) {
                mTabReselectedListener.onTabReselected(newSelected);
            }
        }
    };

    private final LinearLayout mTabLayout;

    private LazyViewPager mViewPager;
    private LazyViewPager.OnPageChangeListener mListener;

    private int mSelectedTabIndex;

    private OnTabReselectedListener mTabReselectedListener;

    private int mTabWidth;

    public IconTabPageIndicator(Context context) {
        this(context, null);
    }

    public IconTabPageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        setHorizontalScrollBarEnabled(false);

        mTabLayout = new LinearLayout(context, null);
        addView(mTabLayout, new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));
    }

    public void setOnTabReselectedListener(OnTabReselectedListener listener) {
        mTabReselectedListener = listener;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final boolean lockedExpanded = widthMode == MeasureSpec.EXACTLY;

        final int childCount = mTabLayout.getChildCount();

        if (childCount > 1 && (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST)) {
            mTabWidth = MeasureSpec.getSize(widthMeasureSpec) / childCount;
        } else {
            mTabWidth = -1;
        }

        final int oldWidth = getMeasuredWidth();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int newWidth = getMeasuredWidth();

        if (lockedExpanded && oldWidth != newWidth) {
            // Recenter the tab display if we're at a new (scrollable) size.
            setCurrentItem(mSelectedTabIndex);
        }
    }

    private void animateToTab(final int position) {
        final View tabView = mTabLayout.getChildAt(position);
        if (mTabSelector != null) {
            removeCallbacks(mTabSelector);
        }
        mTabSelector = new Runnable() {
            public void run() {
                final int scrollPos = tabView.getLeft() - (getWidth() - tabView.getWidth()) / 2;
                mTabSelector = null;
            }
        };
        post(mTabSelector);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mTabSelector != null) {
            // Re-post the selector we saved
            post(mTabSelector);
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mTabSelector != null) {
            removeCallbacks(mTabSelector);
        }
    }

    private void addTab(int index, CharSequence text,boolean isShow, int... ResId) {
        final TabView tabView = new TabView(getContext());
        tabView.mIndex = index;
        tabView.setOnClickListener(mTabClickListener);
        tabView.setText(text);

        if (ResId[0] > 0) {
            tabView.setIcon(ResId[0]);
        }
        if (ResId[1] > 0) {
            tabView.setTextColor(ResId[1]);
        }
        if (ResId[2] > 0) {
            tabView.setTabgapColor(ResId[2]);
        }
        if (ResId[3] > 0) {
            tabView.setViewChooseColor(ResId[3]);
        }

        tabView.setViewChooseVisible(isShow);
        
        if(index == 0)
        {
        	tabView.setTabgapVisible(false);
        }
       
        mTabLayout.addView(tabView, new LayoutParams(0, MATCH_PARENT, 1));
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        if (mListener != null) {
            mListener.onPageScrollStateChanged(arg0);
        }
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        if (mListener != null) {
            mListener.onPageScrolled(arg0, arg1, arg2);
        }
    }

    @Override
    public void onPageSelected(int arg0) {
        setCurrentItem(arg0);
        if (mListener != null) {
            mListener.onPageSelected(arg0);
        }
    }

    @Override
    public void setViewPager(LazyViewPager view) {
        if (mViewPager == view) {
            return;
        }
        if (mViewPager != null) {
            mViewPager.setOnPageChangeListener(null);
        }
        final PagerAdapter adapter = view.getAdapter();
        if (adapter == null) {
            throw new IllegalStateException("LazyViewPager does not have adapter instance.");
        }
        mViewPager = view;
        view.setOnPageChangeListener(this);
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged() {
        mTabLayout.removeAllViews();
        PagerAdapter adapter = mViewPager.getAdapter();
        IconPagerAdapter iconAdapter = null;
        if (adapter instanceof IconPagerAdapter) {
            iconAdapter = (IconPagerAdapter) adapter;
        }
        final int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            CharSequence title = adapter.getPageTitle(i);
            if (title == null) {
                title = EMPTY_TITLE;
            }
            int[] iconResId = new int[]{0,0,0,0};
            boolean isShow=true;
            if (iconAdapter != null) {
                iconResId[0] = iconAdapter.getIconResId(i);
                iconResId[1] = iconAdapter.getTextColorId(i);
                iconResId[2] = iconAdapter.getTabGapId(i);
                iconResId[3] = iconAdapter.getViewChooseId(i);
                isShow=iconAdapter.isShowChoose();
            }
            addTab(i, title,isShow, iconResId);
        }
        if (mSelectedTabIndex > count) {
            mSelectedTabIndex = count - 1;
        }
        setCurrentItem(mSelectedTabIndex);
        requestLayout();
    }

    @Override
    public void setViewPager(LazyViewPager view, int initialPosition) {
        setViewPager(view);
        setCurrentItem(initialPosition);
    }

    @Override
    public void setCurrentItem(int item) {
        if (mViewPager == null) {
            throw new IllegalStateException("LazyViewPager has not been bound.");
        }
        mSelectedTabIndex = item;
        mViewPager.setCurrentItem(item, false);

        final int tabCount = mTabLayout.getChildCount();
        for (int i = 0; i < tabCount; i++) {
            final View child = mTabLayout.getChildAt(i);
            final boolean isSelected = (i == item);
            child.setSelected(isSelected);
            if (isSelected) {
                animateToTab(item);
            }
        }
    }

    @Override
    public void setOnPageChangeListener(LazyViewPager.OnPageChangeListener listener) {
        mListener = listener;
    }

    private class TabView extends LinearLayout {
        private int mIndex;
        private ImageView mImageView;
        private TextView mTextView;
        private View tab_gap;
        private View view_choose;


        public TabView(Context context) {
            super(context, null);
            View view = View.inflate(context, R.layout.tab_view, null);
            mImageView = (ImageView) view.findViewById(R.id.tab_image);
            mTextView = (TextView) view.findViewById(R.id.tab_text);
            tab_gap =  view.findViewById(R.id.tab_gap);
            view_choose =  view.findViewById(R.id.view_choose);

            this.setGravity(Gravity.CENTER);
            this.addView(view, new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));
        }

        @Override
        public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            // Re-measure if we went beyond our maximum size.
            if (mTabWidth > 0) {
            	super.onMeasure(MeasureSpec.makeMeasureSpec(mTabWidth, MeasureSpec.EXACTLY),
            			heightMeasureSpec);
            }
              
        }

        /**
         * 设置字体内容
         * @param text
         */
        public void setText(CharSequence text) {
            mTextView.setText(text);
        }

        /**
         * 设置字体的颜色
         * @param colorId
         */
        public void  setTextColor(int colorId){
            if(colorId>0){
                mTextView.setTextColor(colorId);
            }
        }

        public void setIcon(int resId) {
            if (resId > 0) {
                mImageView.setImageResource(resId);
            }
        }

        public int getIndex() {
            return mIndex;
        }
        
        public void setTabgapVisible(boolean visible)
        {
        	if(visible)
        	{
        		tab_gap.setVisibility(View.VISIBLE);
        	}
        	else
        	{
        		tab_gap.setVisibility(View.INVISIBLE);
        	}
        	
        }

        /**
         * 设置间隙的颜色
         * @param colorId
         */
        public void setTabgapColor(int colorId){
            if(colorId>0) {
                tab_gap.setBackgroundResource(colorId);
            }
        }
        /**
         * 设置标示的颜色
         * @param colorId
         */
        public void setViewChooseColor(int colorId){
            if(colorId>0) {
                view_choose.setBackgroundResource(colorId);
            }
        }

        public void setViewChooseVisible(boolean visible)
        {
            if(visible)
            {
                view_choose.setVisibility(View.VISIBLE);
            }
            else
            {
                view_choose.setVisibility(View.INVISIBLE);
            }

        }
    }
}

