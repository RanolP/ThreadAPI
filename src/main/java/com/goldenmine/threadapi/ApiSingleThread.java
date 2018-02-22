package com.goldenmine.threadapi;


import com.goldenmine.threadapi.handler.ApiThreadHandler;
import com.goldenmine.threadapi.unit.FpsTimeUnit;

public final class ApiSingleThread implements ApiThread {
  private double fps;
  private boolean stop = false;
  private boolean paused = false;
  private long start;
  private final int keepUp = -2000;
  private final Thread thread = new Thread(this::run);
  private final ApiThreadHandler handler;

  private int firstRemain;

  public ApiSingleThread(FpsTimeUnit unit, double time, ApiThreadHandler handler) {
    this(unit.convert(time), 0, handler);
  }

  public ApiSingleThread(double fps, ApiThreadHandler handler) {
    this.fps = fps;
    this.handler = handler;
  }

  ApiSingleThread(double fps, int firstRemain, ApiThreadHandler handler) {
    this(fps, handler);
    this.firstRemain = firstRemain;
  }

  @Override
  public void start() {
    thread.start();
  }

  private void run() {
    handler.onStart();

    Delay delay = new Delay(fps);
    start = System.currentTimeMillis() + firstRemain;

    try {
      Thread.sleep(firstRemain);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    while (!stop) {
      try {
        if (paused) {
          Thread.sleep(Integer.MAX_VALUE);
          continue;
        }
        handler.onThreadExecute();

        start = delay.autoCompute(start);
        long cal = delay.keepUp(start, keepUp);

        if (cal > 0) {
          handler.onKeepUp();
          start += cal;
        }
      } catch (InterruptedException ex) {
        handler.onInterrupt();
        try {
          if (firstRemain > 0) {
            Thread.sleep(firstRemain);
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  @Override
  public void stop() {
    handler.onStop();
    stop = true;
    thread.interrupt();
  }

  @Override
  public void resume() {
    handler.onResume();
    start = System.currentTimeMillis() + firstRemain;
    paused = false;
    thread.interrupt();
  }

  @Override
  public void pause() {
    handler.onPause();
    paused = true;
    thread.interrupt();
  }
}
