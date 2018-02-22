package com.goldenmine.threadapi;

public class Delay {
  private double fps = 0;
  private int ms;
  private int ns;
  private int tick = 0;
  private int interval = 0;

  public Delay(double fps) {
    double time = 1000D / fps;
    int ms = (int) time;
    int ns = (int) ((time - ms) * 1000000);
    double val = ns * fps;

    this.fps = fps;
    this.ms = ms;
    this.ns = ns;
    this.interval = (int) (1000000 - (val - (int) val / 1000000 * 1000000));

    if (this.interval == 1000000) {
      this.interval = 0;
    }
  }

  public Delay(int fps, int delay) {
    this(fps);
    tick = delay;
  }

  public int getMS() {
    return ms;
  }

  public int getNS() {
    return ns;
  }

  public int update() {
    tick++;
    if (tick == fps) {
      tick = 0;
      return interval;
    }
    return 0;
  }

  public int getUpdateMS() {
    int updated = update();
    int msplus = 0;

    if (updated > 0) {
      msplus = 1;
    }
    if (updated < 0) {
      msplus = -1;
    }

    return msplus;
  }

  public long keepUp(long start, int keepup) {
    long cal = 0;
    if (getRemainMS(start) <= keepup) {
      while (getRemainMS(start + cal) >= -keepup) {
        cal += getMS() + getUpdateMS();
      }
    }
    return cal;
  }

  public long autoCompute(long start) throws InterruptedException {
    int ms = getRemainMS(start);
    int msplus = getUpdateMS();

    start += getMS() + msplus;

    Delay.sleep(ms + msplus);

    return start;
  }

  public double getFps() {
    return fps;
  }

  public static void sleep(int ms, int ns) throws InterruptedException {
    if (ms > 0) {
      if (ns > 0) {
        Thread.sleep(ms, ns);
      } else {
        Thread.sleep(ms);
      }
    } else {
      if (ns > 0) {
        Thread.sleep(0, ns);
      }
    }
  }

  public static void sleep(int ms) throws InterruptedException {
    if (ms > 0) {
      Thread.sleep(ms);
    }
  }

  public int getRemainMS(long start) {
    return (int) (getMS() - (System.currentTimeMillis() - start));
  }
}
