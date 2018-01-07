package com.atguigu.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class ShareData
{
	private int number = 0;
	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	
	public void increment() throws InterruptedException
	{
		lock.lock();
		try
		{
			if(number != 0 )
			{
				condition.await();//this.wait();
			}
			++number;
			System.out.println(Thread.currentThread().getName()+"\t"+number);
			condition.signalAll();//this.notifyAll();
		} catch (Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}	
	
	public void decrement() throws InterruptedException
	{
		lock.lock();
		try
		{
			if(number == 0 )
			{
				condition.await();//this.wait();
			}
			--number;
			System.out.println(Thread.currentThread().getName()+"\t"+number);
			condition.signalAll();//this.notifyAll();
		} catch (Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}	
	
	/*如果对象调用了wait方法就会使持有该对象的线程把该对象的控制权交出去，然后处于等待状态。
	public synchronized void increment() throws InterruptedException
	{
		while(number != 0 )
		{
			this.wait();
		}
		++number;
		System.out.println(Thread.currentThread().getName()+"\t"+number);
		this.notifyAll();
	}
	
	public synchronized void decrement() throws InterruptedException
	{
		while(number == 0 )
		{
			this.wait();
		}
		--number;
		System.out.println(Thread.currentThread().getName()+"\t"+number);
		this.notifyAll();
	}
	*/
}


/**
 * 题目：现在两个线程，可以操作同一个变量，实现一个线程对该变量加1，一个线程对该变量减1，
 * 实现交替，来10轮，变量初始值为零。
 * @author admin

 * 
 * 1 	高内聚低耦合
 * 2	线程			操作				资源
 */
public class ThreadDemo2
{
	public static void main(String[] args)
	{
		final ShareData sd = new ShareData();
		
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				for (int i = 1; i <=10; i++)
				{
					try
					{
						Thread.sleep(200);
						sd.increment();
					} catch (InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		},"AA").start();
		
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				for (int i = 1; i <=10; i++)
				{
					try
					{
						Thread.sleep(300);
						sd.decrement();
					} catch (InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		},"BB").start();		
		
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				for (int i = 1; i <=10; i++)
				{
					try
					{
						Thread.sleep(400);
						sd.increment();
					} catch (InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		},"CC").start();
		
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				for (int i = 1; i <=10; i++)
				{
					try
					{
						Thread.sleep(500);
						sd.decrement();
					} catch (InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		},"DD").start();			
		
		
		
	}
}
