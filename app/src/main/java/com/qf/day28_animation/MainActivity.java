package com.qf.day28_animation;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * 补间动画，执行完毕以后，会回到初始状态
 * <p/>
 * 补间动画的配置文件：摆放在/res/anim 文件夹
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "print";
    private ImageView iv;
    private TextView tv;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.iv);
        tv = (TextView) findViewById(R.id.tv);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * 停止动画
     *
     * @param v
     */
    public void stopanimation(View v) {
        iv.clearAnimation();
    }

    /**
     * 执行渐变动画
     *
     * @param v
     */
    public void alphaanimation(View v) {
        Animation animation = new AlphaAnimation(1, 0);//表示这个动画，从1（不透明）到0（完全透明）

        animation.setDuration(2000);//该动画的执行时间， 单位是毫秒

        animation.setFillAfter(true);//保留补间动画执行完毕后的状态

        /**
         * 动画执行的模式
         * Animation.RESTART 重头开始执行
         * Animation.REVERSE 有一个回放的动作
         */
        animation.setRepeatMode(Animation.REVERSE);

        animation.setRepeatCount(Animation.INFINITE);//无限次执行

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d(TAG, "onAnimationStart: 动画开始");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d(TAG, "onAnimationStart: 动画结束");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.d(TAG, "onAnimationStart: 动画重复开始");
            }
        });

        iv.startAnimation(animation);//开始执行动画
//        iv.clearAnimation();//移除作用在该对象上的动画，达到停止动画的效果
    }

    /**
     * 平移动画
     * 坐标0，0表示控件的当前位置， 不是表示0,0这个坐标点
     *
     * @param v
     */
    public void tranlationanimation(View v) {

        //获得屏幕的宽度
        int screenWidth = getResources().getDisplayMetrics().widthPixels;

        Animation animation = new TranslateAnimation(0, screenWidth - iv.getWidth(), 0, 0);
        animation.setDuration(2000);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(Animation.INFINITE);
        iv.startAnimation(animation);
    }

    /**
     * 旋转动画
     * 如果没有指定旋转中心点的话，默认为控件左上角
     * <p/>
     * Animation.RELATIVE_TO_SELF 表示相对于本身
     * Animation.RELATIVE_TO_PARENT 表示相对于父控件
     *
     * @param v
     */
    public void rotateanimation(View v) {

//        Animation animation = new RotateAnimation(0, 180);
//        Animation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 1f);
        Animation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
        animation.setDuration(2000);
        iv.startAnimation(animation);
    }

    /**
     * 缩放动画
     * <p/>
     * 默认根据左上角拉伸
     *
     * @param v
     */
    public void scaleanimation(View v) {

//        Animation animation = new ScaleAnimation(1f, 2f, 1f, 0.5f);
        Animation animation = new ScaleAnimation(1f, 2f, 1f, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
        animation.setDuration(2000);
        iv.startAnimation(animation);
    }

    /**
     * 动画集合
     *
     * @param v
     */
    public void setanimation(View v) {
        AnimationSet animation = new AnimationSet(true);//表示加速插值器是否共享
        animation.setDuration(5000);

        //渐变动画
        Animation animation1 = new AlphaAnimation(1, 0);//表示这个动画，从1（不透明）到0（完全透明）
//        animation1.setDuration(2000);//该动画的执行时间， 单位是毫秒

        //获得屏幕的宽度
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        Animation animation2 = new TranslateAnimation(0, screenWidth - iv.getWidth(), 0, 0);
//        animation2.setDuration(2000);

//        Animation animation3 = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        animation3.setDuration(2000);

        Animation animation4 = new ScaleAnimation(1f, 2f, 1f, 2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        animation4.setDuration(2000);

        /**
         * 把动画添加进集合中
         */
        animation.addAnimation(animation1);
        animation.addAnimation(animation2);
//        animation.addAnimation(animation3);
        animation.addAnimation(animation4);

        iv.startAnimation(animation);

    }

    /**
     * 通过配置文件执行属性动画
     *
     * @param v
     */
    public void configanimation(View v) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.text_translate);
        tv.startAnimation(animation);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.qf.day28_animation/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.qf.day28_animation/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
