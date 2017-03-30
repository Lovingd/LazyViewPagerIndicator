# LazyViewPagerIndicator

ViewPager+底部导航栏（懒加载）
![](https://github.com/Lovingd/LazyViewPagerIndicator/blob/master/lazyViewpager.gif)


添加了设置底部的移动的颜色
添加了设置底部的间隙的颜色

# 使用方式 #
	添加在你的根build.gradle在库底：
      allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}


     添加依赖关系

    dependencies {
	        compile 'com.github.Lovingd:LazyViewPagerIndicator:1.0.4'
	}

# 住方法里面的调用方式 #
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

# 具体的调用方式请下载demo #

  这个是基于大神的控件修改并适应自己的用法

  [![](https://jitpack.io/v/Lovingd/LazyViewPagerIndicator.svg)](https://jitpack.io/#Lovingd/LazyViewPagerIndicator)
