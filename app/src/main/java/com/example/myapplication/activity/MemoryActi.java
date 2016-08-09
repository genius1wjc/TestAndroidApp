package com.example.myapplication.activity;

import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.myapplication.R;

// More info on memory management is on https://developer.android.com/training/articles/memory.html
public class MemoryActi extends AppCompatActivity {

    private final static String TAG = "MemoryActi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        Runtime rt = Runtime.getRuntime();
        long maxMemory = rt.maxMemory();
        Log.v(TAG, "How many total bytes of heap my app is allowed to use " + maxMemory);

        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        int memoryClass = am.getMemoryClass();
        Log.v(TAG, "How many megabytes of heap my app should use " + memoryClass);

        // If use large heap size, use the following
        Log.v(TAG, "How many megabytes of heap my app should use in case of large heap size was requested"
                + am.getLargeMemoryClass());
    }

    // OnTrimMemory和OnLowMemory的关系？

    // 在引入OnTrimMemory之前都是使用OnLowMemory回调，需要知道的是，OnLowMemory大概和
    // OnTrimMemory中的TRIM_MEMORY_COMPLETE级别相同，如果你想兼容api<14的机器，那么可以用OnLowMemory来实现，
    // 否则你可以忽略OnLowMemory，直接使用OnTrimMemory即可．

    /*1. 哪些组件可以实现OnTrimMemory回调？

            Application.onTrimMemory()
            Activity.onTrimMemory()
            Fragment.OnTrimMemory()
            Service.onTrimMemory()
            ContentProvider.OnTrimMemory()*/

    /**
     * This can be overridden in the Application class
     * @param level
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        // don't compare with == as intermediate stages also can be reported, always better to check >= or <=
        if (level >= ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW) {

        }

        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            // indicates your UI is now hidden from view and you should free resources that only your UI uses

            // What can be released?
            // 1. 缓存 缓存包括一些文件缓存，图片缓存等，在用户正常使用的时候这些缓存很有作用，
            // 但当你的应用程序UI不可见的时候，这些缓存就可以被清除以减少内存的使用．比如第三方图片库的缓存．
            // 2. 一些动态生成动态添加的View/fragment． 这些动态生成和添加的View且少数情况下才使用到的View，
            // 这时候可以被释放，下次使用的时候再进行动态生成即可．比如原生桌面中，
            // 会在 OnTrimMemory的TRIM_MEMORY_MODERATE等级中，释放所有AppsCustomizePagedView的资源，
            // 来保证在低内存的时候，桌面不会轻易被杀掉．
        }

        /*下面三个等级是当我们的应用程序真正运行时的回调：

        TRIM_MEMORY_RUNNING_MODERATE 表示应用程序正常运行，并且不会被杀掉。但是目前手机的内存已经有点低了，系统可能会开始根据LRU缓存规则来去杀死进程了。
        TRIM_MEMORY_RUNNING_LOW 表示应用程序正常运行，并且不会被杀掉。但是目前手机的内存已经非常低了，我们应该去释放掉一些不必要的资源以提升系统的性能，同时这也会直接影响到我们应用程序的性能。
        TRIM_MEMORY_RUNNING_CRITICAL 表示应用程序仍然正常运行，但是系统已经根据LRU缓存规则杀掉了大部分缓存的进程了。这个时候我们应当尽可能地去释放任何不必要的资源，不然的话系统可能会继续杀掉所有缓存中的进程，并且开始杀掉一些本来应当保持运行的进程，比如说后台运行的服务。
        当应用程序是缓存的，则会收到以下几种类型的回调：

        TRIM_MEMORY_BACKGROUND 表示手机目前内存已经很低了，系统准备开始根据LRU缓存来清理进程。这个时候我们的程序在LRU缓存列表的最近位置，是不太可能被清理掉的，但这时去释放掉一些比较容易恢复的资源能够让手机的内存变得比较充足，从而让我们的程序更长时间地保留在缓存当中，这样当用户返回我们的程序时会感觉非常顺畅，而不是经历了一次重新启动的过程。
        TRIM_MEMORY_MODERATE 表示手机目前内存已经很低了，并且我们的程序处于LRU缓存列表的中间位置，如果手机内存还得不到进一步释放的话，那么我们的程序就有被系统杀掉的风险了。
        TRIM_MEMORY_COMPLETE 表示手机目前内存已经很低了，并且我们的程序处于LRU缓存列表的最边缘位置，系统会最优先考虑杀掉我们的应用程序，在这个时候应当尽可能地把一切可以释放的东西都进行释放。*/
    }
}
