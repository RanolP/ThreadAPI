package com.goldenmine.threadapi;

import com.goldenmine.threadapi.unit.FpsTimeUnit;

public class TestThread {
  public static void main(String[] args) {
    ApiThread apiThread = new ApiSingleThread(FpsTimeUnit.SECOND, 1, new ApiThreadHandler() {
      long first = -1;

      int time;

      @Override
      public void onThreadExecute() throws InterruptedException {
        Delay.sleep(10);
        if (first == -1) {
          first = System.currentTimeMillis();
        }
        //System.out.println(System.currentTimeMillis() - first);

        System.out.println(++time + "초가 지났습니다");
      }

      @Override
      public void onKeepUp() {

      }

      @Override
      public void onInterrupt() {

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
    apiThread.start(); // 쓰레드 시작

    try {
      Thread.sleep(5000L);
      apiThread.pause(); // 5초후 쓰레드 일시정지
      Thread.sleep(3000L);
      apiThread.resume(); // 8초후 쓰레드 재개
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    //System.out.println(System.currentTimeMillis() + ", " + System.nanoTime() + ", " + (System.nanoTime()-System.currentTimeMillis()*1000000));
    //System.out.println(System.currentTimeMillis() + ", " + System.nanoTime());
        /*Scanner scan = new Scanner(System.in);

        System.out.print("input FPS: ");
        int fps = scan.nextInt();

        APIMultiThread mt = new APIMultiThread(fps, 4, new ApiThreadHandler() {
            int i = 0;
            @Override
            public void onThreadExecute() {
                System.out.println("running: " + i++);
            }

            @Override
            public void onKeepUp() {

            }

            @Override
            public void onInterrupt() {

            }
        });*/
  }
}
