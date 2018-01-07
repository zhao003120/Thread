package com.atguigu.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class ShareResource
{
	private int number = 1;
	
	Lock lock = new ReentrantLock();
	
	Condition condition1 = lock.newCondition();
	Condition condition2 = lock.newCondition();
	Condition condition3 = lock.newCondition();
	
	public void loopAA(int totalLoop)
	{
		lock.lock();
		try
		{
			//1判断，到底现在改线程是否可以干
			while(number != 1)
			{
				condition1.await();
			}
			//2干活
			for (int i = 1; i <=5; i++)
			{
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t总轮次: "+totalLoop);
			}
			//3通知，通知下一个线程来接手
			number = 2;
			condition2.signal();
		} catch (Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public void loopBB(int totalLoop)
	{
		lock.lock();
		try
		{
			//1判断，到底现在改线程是否可以干
			while(number != 2)
			{
				condition2.await();
			}
			//2干活
			for (int i = 1; i <=10; i++)
			{
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t总轮次: "+totalLoop);
			}
			//3通知，通知下一个线程来接手
			number = 3;
			condition3.signal();
		} catch (Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public void loopCC(int totalLoop)
	{
		lock.lock();
		try
		{
			//1判断，到底现在改线程是否可以干
			while(number != 3)
			{
				condition3.await();
			}
			//2干活
			for (int i = 1; i <=15; i++)
			{
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t总轮次: "+totalLoop);
			}
			//3通知，通知下一个线程来接手
			number = 1;
			condition1.signal();
		} catch (Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}	
	
	
	
}

/**
 * 
 * @author admin
 * 题目：三个线程，要求实现按序范文,A>B>C......
 * A打印5次，B打印10次，C打印15次
 * 接着按照上述同样的顺序，再来
 * A打印5次，B打印10次，C打印15次
 * 接着按照上述同样的顺序，再来
 * 。。。。。。来20轮
 *
 */
public class ThreadDemo4
{
	public static void main(String[] args)
	{
		final ShareResource sr = new ShareResource();
		
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				for (int i = 1; i <=20; i++)
				{
					sr.loopAA(i);
				}
			}
		},"AA").start();
		
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				for (int i = 1; i <=20; i++)
				{
					sr.loopBB(i);
				}
			}
		},"BB").start();
		
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				for (int i = 1; i <=20; i++)
				{
					sr.loopCC(i);
					System.out.println();
				}
			}
		},"CC").start();		
		
		
		
		
		
	}
}
