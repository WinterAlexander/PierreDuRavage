package me.winterguardian.scheduling;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Scheduler 
{
	private List<Task> tasks;
	private long pauseTime;
	private long lastPause;
	private long lastPauseTime;
	private boolean pause;

	public Scheduler()
	{
		this.tasks = new ArrayList<>();
		this.pause = true;
		this.lastPause = System.nanoTime() / 1_000_000;
		this.lastPauseTime = 0;
		this.pauseTime = 0;
	}
	
	public void addTask(Task task)
	{
		this.tasks.add(task);
		task.register(this);
	}
	
	public void addTasks(Collection<Task> tasks)
	{
		for(Task task : tasks)
			this.addTask(task);
	}
	
	public void cancel(Task task)
	{
		this.tasks.remove(task);
	}
	
	public void cancelAll()
	{
		this.tasks.clear();
	}
	
	public void update()
	{
		if(this.pause)
			return;

		for(Task task : new ArrayList<>(this.tasks))
		{
			int turns = (int) ((getGameTimeMillis() - task.getLastWork()) / task.getDelay());
			if(!task.isRepeating() && turns >= 1)
			{
				run(task);
				cancel(task);
				continue;
			}

			for(int i = 0; i < turns; i++)
				run(task);

			task.setLastWork(task.getLastWork() + task.getDelay() * turns);
		}

	}

	public void run(Task task)
	{
		if(task == null)
			return;

		try
		{
			task.run();
		}
		catch(Exception e)
		{
			new Exception("An internal error occured when executing task " + task.getClass().getSimpleName(), e).printStackTrace();
		}
	}
	
	public void pause()
	{
		if(!this.pause)
		{
			this.pause = true;
			this.lastPause = System.nanoTime() / 1_000_000;
		}
	}
	
	public void start()
	{
		this.pause = false;
		this.pauseTime += System.nanoTime() / 1_000_000 - this.lastPause;
		this.lastPauseTime = System.nanoTime() / 1_000_000 - this.lastPause;
	}

	public boolean isPause()
	{
		return this.pause;
	}

	public long getLastPauseTime()
	{
		return lastPauseTime;
	}
	
	public long getLastPause()
	{
		return lastPause;
	}
	
	public long getGameTimeMillis()
	{
		return System.nanoTime() / 1_000_000 - this.pauseTime;
	}
}
