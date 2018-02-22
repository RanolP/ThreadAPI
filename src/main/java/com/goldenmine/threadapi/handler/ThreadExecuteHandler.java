package com.goldenmine.threadapi.handler;

@FunctionalInterface
public interface ThreadExecuteHandler {

  void handle() throws InterruptedException;
}
