package edu.utdallas.blockFIFO;

import edu.utdallas.taskExecutor.Task;

public class BlockingQueue {
	int count;
	int size=100;
	int start=0, end=0;
	Object notfull = new Object();
	Object notempty= new Object();
	Task[] taskQueue= new Task[size];
	public void put(Task task) throws InterruptedException{
		while (true) {
			if(count==size){
				synchronized (notfull) {
					notfull.wait();
				}
			}
			synchronized (this) {
				if(count==size){
					continue;
				}
				taskQueue[start]=task;
				start=(start+1)%size;
				count++;
				synchronized (notempty) {
					notempty.notify();
				}
				return;

			}
			
		}
	}
	public  Task take() throws InterruptedException{
		Task TaskforRun=null;
		while(true){
			if(count<1){
				synchronized (notempty) {
					notempty.wait();
				}
			}
			synchronized (this) {
				if(count<1){
					continue;
				}
				TaskforRun=taskQueue[end];
				end=(end+1)%size;
				count--;
				synchronized (notfull) {
					notfull.notify();
				}
				return TaskforRun;
			}
		}
		
		
	}

}
