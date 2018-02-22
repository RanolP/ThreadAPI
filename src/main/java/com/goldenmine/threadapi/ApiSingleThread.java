package com.goldenmine.threadapi;


import com.goldenmine.threadapi.unit.FpsTimeUnit;

public abstract class ApiSingleThread implements ApiThread {
  private double fps;
  private boolean stop = false;
  private boolean paused = false;
  private long start;
  private Delay delay;
  private final int keepup = -2000;
  private final Thread thread = new Thread(this::run);

  private int firstremain;

  public ApiSingleThread(FpsTimeUnit factory, double unit) {
    this(factory, unit, 0);
  }

  ApiSingleThread(FpsTimeUnit factory, double unit, int firstremain) {
    this(factory.convert(unit), firstremain);
  }

  public ApiSingleThread(double fps) {
    this.fps = fps;
  }

  ApiSingleThread(double fps, int firstremain) {
    this(fps);
    this.firstremain = firstremain;
  }

  @Override
  public void start() {
    thread.start();
  }

  private void run() {
    onStart();

    delay = new Delay(fps);
    start = System.currentTimeMillis() + firstremain;

    try {
      Thread.sleep(firstremain);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    while (!stop) {
      try {
        if (paused) {
          Thread.sleep(Integer.MAX_VALUE);
          continue;
        }
        onThreadExecute();

        start = delay.autoCompute(start);
        long cal = delay.keepUp(start, keepup);

        if (cal > 0) {
          onKeepUp();
          start += cal;
        }
      } catch (InterruptedException ex) {
        onInterrupt();
        try {
          if (firstremain > 0) {
            Thread.sleep(firstremain);
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  @Override
  public void stop() {
    onStop();
    stop = true;
    thread.interrupt();
  }

  @Override
  public void resume() {
    onResume();
    start = System.currentTimeMillis() + firstremain;
    paused = false;
    thread.interrupt();
  }

  @Override
  public void pause() {
    onPause();
    paused = true;
    thread.interrupt();
  }
}
