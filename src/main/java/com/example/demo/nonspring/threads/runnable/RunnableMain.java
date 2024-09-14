package com.example.demo.nonspring.threads.runnable;

class RunnableMain {

  public static void main(String[] args) {

//	runRunnables();

	runRunnablesAsNewThread();
  }

  // this executes them all in single thread
  private static void runRunnables() {
	for (int i = 0; i < 10; i++) {
	  Runnable myRunnable = new MyRunnable();
	  myRunnable.run();
	}
  }

  private static void runRunnablesAsNewThread() {
	MyRunnable myRunnable = new MyRunnable();
	for (int i = 0; i < 10; i++) {
	  Thread myThread = new Thread(myRunnable);
	  myThread.start();
	}
  }
}
