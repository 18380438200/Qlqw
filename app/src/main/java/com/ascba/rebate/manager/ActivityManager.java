package com.ascba.rebate.manager;

import android.app.Activity;
import android.util.Log;

import com.ascba.rebate.activities.main.MainActivity;

import java.util.Stack;

/**
 * Created by 李平 on 2017/8/10.
 * activity类
 */

public class ActivityManager {
    private static ActivityManager manager = new ActivityManager();
    private Stack<Activity> ayList = new Stack<>();
    private String TAG="ActivityManager";

    private ActivityManager() {
    }

    public static ActivityManager getInstance() {
        return manager;
    }

    /**
     * 添加 activity
     */
    public void addActivity(Activity a) {
        if (!ayList.contains(a)) {
            ayList.add(a);
            Log.d(TAG, "addActivity: "+a);
        }
    }

    /**
     * 添加 activity
     */
    public void removeActivity(Activity a) {
        if (ayList.contains(a)) {
            ayList.remove(a);
            Log.d(TAG, "removeActivity: "+a);
        }
    }

    /**
     * 退出所有activity（退出app）
     */
    public void removeAllActivity() {
        for (Activity a : ayList) {
            a.finish();
        }
        //System.exit(0);
    }

    /**
     * 除指定的一个activity外，全部退出
     * @param a 不退出的activity
     */
    public void removeAllExceptOne(Activity a) {
        for (Activity aa : ayList) {
            if(aa!=a){
                aa.finish();
            }
        }
    }

    /**
     * 获取activity数量
     */
    public int getSize(){
        return ayList.size();
    }

    public void killMainActivity() {
        for (Activity a : ayList) {
            if(a instanceof MainActivity){
                a.finish();
            }
        }
    }
}
