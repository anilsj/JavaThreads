package org.anil.javaworld;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Pool {
	
	private final int MAX_CAPACITY = 5;
	private final int MAX_NO_THREADS=10;
	
	private BlockingQueue<Runnable> queue = null;
	private List<ThreadObj> pool = new ArrayList<ThreadObj>();
	
	public Pool(){
		this.queue = new ArrayBlockingQueue<Runnable>(MAX_CAPACITY);
		intializePool(this.queue);
	}
	
	private void intializePool(BlockingQueue<Runnable> queue){
		ThreadObj obj = null;
		for(int i=0; i< MAX_NO_THREADS; i++){
			obj = new ThreadObj(queue);
			obj.setName("Thread"+ i);
			pool.add(obj);
		}
		for(ThreadObj thread :pool){
			thread.start();
		}
	}
	
	public void add(Runnable task) throws InterruptedException{
		this.queue.put(task);
	}
	
	public boolean terminatePool(){
		for(ThreadObj thread :pool){
			thread.stopThread();
		}
		return true;
	}
}
