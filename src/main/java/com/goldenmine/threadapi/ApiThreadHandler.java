package com.goldenmine.threadapi;

public interface ApiThreadHandler {
  void onThreadExecute() throws InterruptedException;

  void onKeepUp();

  void onInterrupt();

  void onStart();

  void onPause();

  void onResume();

  void onStop();
}
