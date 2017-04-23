package org.anil.javaworld;

import java.util.concurrent.BlockingQueue;

public class ThreadObj extends Thread {

	private BlockingQueue<Runnable> queue;
	private boolean stop = false;

	public ThreadObj(BlockingQueue<Runnable> newQueue){
		this.queue = newQueue;
	}
	
	public void run() {
		System.out.println(Thread.currentThread().getName()+" is now processing.");
		
		Runnable runnable = null;
		while(!isThreadTerminated()){
			try {
				runnable = this.queue.take();
				runnable.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(Thread.currentThread().getName()+" stopped");
	}

	public synchronized void stopThread(){
		this.stop = true;
		this.interrupt();
	}
	
	private boolean isThreadTerminated(){
		return stop;
	}
}
