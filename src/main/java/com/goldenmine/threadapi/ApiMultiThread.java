package com.goldenmine.threadapi;

import com.goldenmine.threadapi.unit.FpsTimeUnit;

import java.util.ArrayList;
import java.util.List;

public final class ApiMultiThread implements ApiThread {
  private List<ApiSingleThread> threads = new ArrayList<>();
  private ApiThreadHandler handler;

  public ApiMultiThread(FpsTimeUnit timeUnit, double time, int count, ApiThreadHandler handler) {
    this(timeUnit.convert(time), count, handler);
  }

  public ApiMultiThread(final double fps, final int count, ApiThreadHandler handler) {
    this.handler = handler;
    for (int i = 0; i < count; i++) {
      ApiSingleThread t = new ApiSingleThread(fps, (int) Math.round(fps / count * 1000 * i), new ApiThreadHandler() {
        public void onThreadExecute() throws InterruptedException {
          handler.onThreadExecute();
        }

        @Override
        public void onKeepUp() {
          handler.onKeepUp();
        }

        @Override
        public void onInterrupt() {
          handler.onInterrupt();
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
      });
      threads.add(t);
    }
  }

  @Override
  public void pause() {
    handler.onPause();
    for (ApiSingleThread thread : threads) {
      thread.pause();
    }
  }

  @Override
  public void resume() {
    handler.onResume();
    for (ApiSingleThread thread : threads) {
      thread.resume();
    }
  }

  @Override
  public void stop() {
    handler.onStop();
    for (ApiSingleThread thread : threads) {
      thread.stop();
    }
  }

  @Override
  public void start() {
    handler.onStart();
    for (ApiSingleThread thread : threads) {
      thread.start();
    }
  }
}
