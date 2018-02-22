package com.goldenmine.threadapi;

public interface APIThreadable {
  void onThreadExecute() throws InterruptedException;

  void onKeepUp();

  void onInterrupt();

  void onStart();

  void onPause();

  void onResume();

  void onStop();

  @Deprecated
  default void APIPause() {
    pause();
  }

  void pause();

  @Deprecated
  default void APIResume() {
    resume();
  }

  void resume();

  @Deprecated
  default void APIStop() {
    stop();
  }

  void stop();
}
