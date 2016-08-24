package edu.utdallas.taskExecutorImpl;

import edu.utdallas.taskExecutor.Task;

import edu.utdallas.blockFIFO.BlockingQueue;

public class TaskRunner implements Runnable {
	public static BlockingQueue blockingqueue=new BlockingQueue();

	public static BlockingQueue getTaskQueue() {
		return blockingqueue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			Task task;
			try {
				task = blockingqueue.take();
				task.execute();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
	}

}
