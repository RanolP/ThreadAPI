package com.goldenmine.threadapi;

public interface ApiThread {
  void onThreadExecute() throws InterruptedException;

  void onKeepUp();

  void onInterrupt();

  void onStart();

  void onPause();

  void onResume();

  void onStop();

  void pause();

  void resume();

  void stop();

  void start();
}
