package cn.muzi.lazyviewpager;

public interface IconPagerAdapter {
    /**
     * Get icon representing the page at {@code index} in the adapter.
     */
    int getIconResId(int index);
    int getTextColorId(int index);
    int getTabGapId(int index);
    int getViewChooseId(int index);

    // From PagerAdapter
    int getCount();
}
