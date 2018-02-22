package com.goldenmine.threadapi;

import com.goldenmine.threadapi.unit.FpsTimeUnit;

import java.util.ArrayList;
import java.util.List;

public abstract class ApiMultiThread implements ApiThread {
  private List<ApiSingleThread> threads = new ArrayList<>();

  public ApiMultiThread(FpsTimeUnit timeUnit, double time, int count) {
    this(timeUnit.convert(time), count);
  }

  public ApiMultiThread(final double fps, final int count) {
    for (int i = 0; i < count; i++) {
      ApiSingleThread t = new ApiSingleThread(fps, (int) Math.round(fps / count * 1000 * i)) {
        public void onThreadExecute() throws InterruptedException {
          process();
        }

        @Override
        public void onKeepUp() {
          processKeepUp();
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
  public void pause() {
    processPause();
    for (ApiSingleThread thread : threads) {
      thread.pause();
    }
  }

  @Override
  public void resume() {
    processResume();
    for (ApiSingleThread thread : threads) {
      thread.resume();
    }
  }

  @Override
  public void stop() {
    processStop();
    for (ApiSingleThread thread : threads) {
      thread.stop();
    }
  }

  @Override
  public void start() {
    processStart();
    for (ApiSingleThread thread : threads) {
      thread.start();
    }
  }

  private void process() throws InterruptedException {
    onThreadExecute();
  }

  private void processKeepUp() {
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
