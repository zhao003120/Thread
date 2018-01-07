package com.atguigu.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



class Ticket
{
	private int number = 30;
	Lock lock = new ReentrantLock();//List list = new ArrayList();
	
	
	public void saleTicket()
	{
		lock.lock();
		try
		{
			if(number > 0)
			{
				Thread.sleep(200);
				System.out.println(Thread.currentThread().getName()+"卖出第:\t"+(number--)+"\t 剩余"+number);
			}			
		} catch (Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
}


/**
 * 
 * @author zhouyang
 * 复习卖票的case，同步复习
 * 三个售票员卖票，
 * 
 * 111111
 * 222222
 * 333333
 * 444444
 * nnnnnn
 * 
 * 1	线程   操作   资源
 * 2	高内聚	低耦合
 * 
 */
public class ThreadDemo0
{
	public static void main(String[] args)
	{
		final Ticket ticket = new Ticket();
		
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				for (int i = 1; i <=40; i++)
				{
					ticket.saleTicket();
				}
			}
		},"AA").start();
		
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				for (int i = 1; i <=40; i++)
				{
					ticket.saleTicket();
				}
			}
		},"BB").start();		
		
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				for (int i = 1; i <=40; i++)
				{
					ticket.saleTicket();
				}
			}
		},"CC").start();		
		
		
		
		

	}
}