package org.anil.javaworld;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class TestThreadPool 
{
    @SuppressWarnings("static-access")
	public static void main( String[] args ) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the no of task to be processed:");
        
        int noOfTasks = scanner.nextInt();
        
        Pool pool = new Pool();
        
        System.out.println("--------------Pool Started-----------------");
        try{
	        for(int i=0; i < noOfTasks; i++){
	        	pool.add(new Runnable() {
					private int value;
					public void run() {
						System.out.println(Thread.currentThread().getName()+":Task value:"+value);
					}
					private Runnable addValue(int newValue){
						this.value = newValue;
						return this;
					}
				}.addValue(i));
	        }
	        
	        Thread.currentThread().sleep(100);
	        
	        pool.terminatePool();
	        
	        Thread.currentThread().sleep(100);
        }
        catch(InterruptedException e){}
    }
}
