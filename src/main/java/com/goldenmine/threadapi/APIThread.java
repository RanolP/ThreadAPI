package com.GoldenMine.Thread.ThreadAPI;

import com.GoldenMine.Thread.ThreadAPI.Unit.TimeUnitFactory;

public abstract class APIThread extends Thread implements APIThreadable {
    private double fps;

    private boolean stop = false;
    private boolean paused = false;

    private long start;

    private Delay delay;
    final int keepup = -2000;

    private int firstremain;

    public APIThread(TimeUnitFactory factory, double unit) {
        this(factory, unit, 0);
    }

    /*for APIMultiThread */
    protected APIThread(TimeUnitFactory factory, double unit, int firstremain) {
        this(factory.convert(unit), firstremain);
    }

    public APIThread(double fps) {
        this.fps = fps;
    }

    /*for APIMultiThread */
    protected APIThread(double fps, int firstremain) {
        this(fps);
        this.firstremain = firstremain;
    }

    @Override
    public void run() {
        onStart();

        delay = new Delay(fps);
        start = System.currentTimeMillis() + firstremain;

        try {
            Thread.sleep(firstremain);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println(start);

        while(!stop) {
            try {
                if(paused) {
                    Thread.sleep(Integer.MAX_VALUE);
                    //interrupt();
                    continue;
                    //start += delay.keepUp(start, delay.getRemainMS(start), keepup);
                }
                onThreadExecute();

                start = delay.autoCompute(start);
                long cal = delay.keepUp(start, keepup);

                if(cal>0) {
                    onKeepUp();
                    start+=cal;
                    continue;
                }
            } catch(InterruptedException ex) {
                onInterrupt();
                //System.out.println("start: " + start);
                try {
                    if(firstremain>0)
                        Thread.sleep(firstremain);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //System.out.println("interrupted");
            }
        }
    }

    public void APIStop() {
        onStop();
        stop = true;
        interrupt();
    }

    public void APIResume() {
        onResume();
        start = System.currentTimeMillis() + firstremain;
        paused = false;
        interrupt();
    }

    public void APIPause() {
        onPause();
        paused = true;
        interrupt();
    }

    /*
    @Override
            public void onTimerTick() {
                System.out.println("test");
            }
            @Override
            public void onTimerExpire() {
            }
            @Override
            public void onTimerInterrupt() {
            }
            @Override
            public void onTimerStart() {
            }
            @Override
            public void onTimerPause() {
            }
            @Override
            public void onTimerResume() {
            }
            public enum WatchType {
    FPS,SECOND,MILLISECOND,MINUTE,HOUR;
}
     */
}
