package com.atguigu.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;


class MyThread implements Callable<Integer>
{
	@Override
	public Integer call() throws Exception
	{
		System.out.println("come in Callable-----");
		return new Random().nextInt(100);
	}
	
}


/**
 * 
 * @author zhouyang
 * 获得多线程的方式Callable
 * 
 * Thread(Runnable target, String name) 
          分配新的 Thread 对象。
          
          
 
 * 
 * 
 */
public class ThreadDemo1
{
	public static void main(String[] args)
	{
		FutureTask futureTask = new FutureTask(new MyThread());
		
		
		new Thread(futureTask,""
				+ "aa").start();
		
		// do my biz
	}
}