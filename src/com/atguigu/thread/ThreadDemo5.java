package com.atguigu.thread;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyQueue
{
	private Object obj;
	
	ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	
	//读
	public void get()
	{
		readWriteLock.readLock().lock();
		try
		{
			System.out.println(Thread.currentThread().getName()+"\t"+obj);
		} catch (Exception e){
			e.printStackTrace();
		}finally{
			readWriteLock.readLock().unlock();
		}		
	}
	
	public void set(Object obj)
	{
		readWriteLock.writeLock().lock();
		try
		{
			this.obj = obj;
			System.out.println(Thread.currentThread().getName()+"\t"+obj);
		} catch (Exception e){
			e.printStackTrace();
		}finally{
			readWriteLock.writeLock().unlock();
		}			
	}
	
	
	
}


/**
 * 
 * @author admin
 *题目：1个线程写，100个线程读
 *	读读可共享
 *	写写/读写要互斥
 */
public class ThreadDemo5
{
	public static void main(String[] args)
	{
		final MyQueue queue = new MyQueue();
		
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				queue.set(new Random().nextInt(10));
			}
		},"AA").start();
		
//		try
//		{
//			Thread.sleep(100);
//		} catch (InterruptedException e){
//			e.printStackTrace();
//		}
		
		for (int i = 1; i <=100; i++)
		{
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					queue.get();
				}
			},String.valueOf(i)).start();			
		}
		
		
	}
}
