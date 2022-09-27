package com.example.birch;

import android.os.Handler;
import android.os.Message;

public class IntroThread extends Thread{

    private Handler handler;
    public IntroThread(android.os.Handler handler){
        this.handler = handler;
    }

    @Override
    public void run() {
        Message msg = new Message();

        try {
            Thread.sleep(3000);
            msg.what = 1;
            handler.sendEmptyMessage(msg.what);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
