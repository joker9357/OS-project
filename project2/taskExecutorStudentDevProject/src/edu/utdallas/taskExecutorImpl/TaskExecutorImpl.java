package edu.utdallas.taskExecutorImpl;

import java.util.ArrayList;
import java.util.List;

import edu.utdallas.blockFIFO.BlockingQueue;
import edu.utdallas.taskExecutor.Task;
import edu.utdallas.taskExecutor.TaskExecutor;
import edu.utdallas.taskExecutor.studentTest.SimpleTestTask;

public class TaskExecutorImpl implements TaskExecutor
{
	
	private List<TaskRunner> runnerPool=new ArrayList<>();
	
	public TaskExecutorImpl(int size) {
		// TODO Auto-generated constructor stub
		for (int i = 0; i < size; i++) {
			TaskRunner taskRunner=new TaskRunner();
			runnerPool.add(taskRunner);
			Thread thread = new Thread(taskRunner);
			thread.start();
			Thread.yield();
		}
	}
	@Override
	public void addTask(Task task)
	{
		// TODO Complete the implementation
		try {
			TaskRunner.getTaskQueue().put(task);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
