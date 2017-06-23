# Coordinatorlayout_Extend
实现Coordinatorlayout与tableLayout复合效果，RecycleView上拉加载<br>
效果图:<br>
![alt text](https://github.com/javaexception/Coordinatorlayout_Extend/blob/master/app/src/main/res/raw/GIF1111.gif)<br>

 [我的简书地址](http://www.jianshu.com/u/2a55d6e39135)<br>
 [我的博客地址](http://blog.csdn.net/qq_34908107)<br>
 
 我的公众号:<br>
 
![alt text](https://github.com/javaexception/Coordinatorlayout_Extend/blob/master/app/src/main/res/raw/qzs.jpg)<br>
思路：<br>
1.首先解决Coordinatorlayout与tableLayout复合效果。<br>
2.RecycleView上拉加载，以及RecycleView的item点击事件.<br>
3.点击条目跳转的URL，添加一个状态View:MultiStateView.<br>

1.
(1)<br>
```
dependencies {
    compile 'cn.hugeterry.coordinatortablayout:coordinatortablayout:1.0.6'
}
```
(2)<br>
```
<cn.hugeterry.coordinatortablayout.CoordinatorTabLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/coordinatortablayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <android.support.v4.view.ViewPager
        android:id="@+id/vp"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_behavior="@string/appbar_scrolling_view_behavior" />
</cn.hugeterry.coordinatortablayout.CoordinatorTabLayout>
```
(3)<br>
1.setTitle(String title):Set the CoordinatorTabLayout's title.<br>
2.setupWithViewPager(ViewPager viewPager):To link the two together.<br>
3.setImageArray(int[] imageArray):Set the image array of the header according to the number of tabs and pass it to the control.<br>

 
 [CoordinatorTabLayout的github地址](https://github.com/hugeterry/CoordinatorTabLayout)<br>
 
 
 2.
 RecycleView想必到家都会的，当时由于时间的原因我用的是AnimRefreshRecyclerView，我在用AnimRefreshRecyclerView的时候我发现了它的不足之处，
 上拉加载没有问题，但是在下拉刷新时，如果对RecycleView的item设置完点击事件后，无法下拉刷新，换句话说只要item中大部分控件都有了点击事件，那么久无法下拉刷新，
 所以大家在用这个库的时候要慎重。
 
 
 3.根据加载状态切换不同的界面<br>
 四种不同的状态:<br>

Content<br>
Empty<br>
Error<br>
Loading<br>
 
 这个比较简单：直接用的是MultiStateView，实测这个库比较好用.<br>
 [MultiStateView库地址](https://github.com/Kennyc1012/MultiStateView)
 
