package com.goldenmine.threadapi.handler;

import java.util.Objects;

public final class ApiThreadHandlerBuilder {
  private ThreadExecuteHandler threadExecuteHandler = () -> {
  };
  private Handler keepUpHandler = () -> {
  };
  private Handler interruptHandler = () -> {
  };
  private Handler startHandler = () -> {
  };
  private Handler pauseHandler = () -> {
  };
  private Handler resumeHandler = () -> {
  };
  private Handler stopHandler = () -> {
  };

  public ApiThreadHandlerBuilder onThreadExecute(ThreadExecuteHandler handler) {
    Objects.requireNonNull(handler, "handler");
    threadExecuteHandler = handler;
    return this;
  }

  public ApiThreadHandlerBuilder onKeepUp(Handler handler) {
    Objects.requireNonNull(handler, "handler");
    keepUpHandler = handler;
    return this;
  }

  public ApiThreadHandlerBuilder onInterrupt(Handler handler) {
    Objects.requireNonNull(handler, "handler");
    interruptHandler = handler;
    return this;
  }

  public ApiThreadHandlerBuilder onStart(Handler handler) {
    Objects.requireNonNull(handler, "handler");
    startHandler = handler;
    return this;
  }

  public ApiThreadHandlerBuilder onPause(Handler handler) {
    Objects.requireNonNull(handler, "handler");
    pauseHandler = handler;
    return this;
  }

  public ApiThreadHandlerBuilder onResume(Handler handler) {
    Objects.requireNonNull(handler, "handler");
    resumeHandler = handler;
    return this;
  }

  public ApiThreadHandlerBuilder onStop(Handler handler) {
    Objects.requireNonNull(handler, "handler");
    stopHandler = handler;
    return this;
  }

  public ApiThreadHandler build() {
    return new ApiThreadHandler() {
      @Override
      public void onThreadExecute() throws InterruptedException {
        threadExecuteHandler.handle();
      }

      @Override
      public void onKeepUp() {
        keepUpHandler.handle();
      }

      @Override
      public void onInterrupt() {
        interruptHandler.handle();
      }

      @Override
      public void onStart() {
        startHandler.handle();
      }

      @Override
      public void onPause() {
        pauseHandler.handle();
      }

      @Override
      public void onResume() {
        resumeHandler.handle();
      }

      @Override
      public void onStop() {
        stopHandler.handle();
      }
    };
  }
}
