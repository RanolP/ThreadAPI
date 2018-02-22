package com.GoldenMine.Thread.ThreadAPI;

import com.GoldenMine.Thread.ThreadAPI.Unit.TimeUnitFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class APIMultiThread implements APIThreadable {
    private List<APIThread> threads = new ArrayList<APIThread>();

    public APIMultiThread(TimeUnitFactory factory, double unit, int time) {
        this(factory.convert(unit), time);
    }

    public APIMultiThread(final double fps, final int time) {
        /*create threads*/
        for (int i = 0; i < time; i++) {

            APIThread t = new APIThread(fps, (int) Math.round(fps / time * 1000 * i)) {
                public void onThreadExecute() throws InterruptedException {
                    process();
                }

                @Override
                public void onKeepUp() {
                    processKeepup();
                }

                @Override
                public void onInterrupt() {
                    processInterrupt();
                }

                @Override
                public void onStart() {
                    
                }

                @Override
                public void onPause() {
                    
                }

                @Override
                public void onResume() {
                    
                }

                @Override
                public void onStop() {
                    
                }
            };
            threads.add(t);
        }
    }

    @Override
    public void APIPause() {
        processPause();
        
        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).APIPause();
        }
    }

    @Override
    public void APIResume() {
        processResume();
        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).APIResume();
        }

    }

    @Override
    public void APIStop() {
        processStop();
        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).APIStop();
        }
    }

    public void start() {
        processStart();
        
        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).start();
        }
    }

    private void process() throws InterruptedException {
        onThreadExecute();
    }

    private void processKeepup() {
        onKeepUp();
    }

    private void processInterrupt() {
        onInterrupt();
    }

    private void processStart() {
        onStart();
    }

    private void processPause() {
        onPause();
    }

    private void processResume() {
        onResume();
    }

    private void processStop() {
        onStop();
    }

}
