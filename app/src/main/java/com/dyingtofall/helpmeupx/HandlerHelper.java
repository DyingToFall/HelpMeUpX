package com.dyingtofall.helpmeupx;

import android.os.CountDownTimer;
import android.os.Handler;
import android.view.animation.Animation;
import android.widget.Button;

/**
 * Created by Home on 9/6/2017.
 */


public class HandlerHelper extends Handler
{
    MainActivityFragment mainActivityFragmentThis;
    Button cancelBtn;
    Animation animate;
    CountDownTimer timer;

    public HandlerHelper(Button button, MainActivityFragment mainActivityFragment, Animation animation, CountDownTimer counting)
    {
        super();
        cancelBtn = button;
        mainActivityFragmentThis = mainActivityFragment;
        animate = animation;
        timer = counting;
    }
}
