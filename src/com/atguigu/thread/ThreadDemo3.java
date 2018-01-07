package com.atguigu.thread;

//题目：判断出现打印苹果还是android


//1	标准情况，两个线程访问，先打印苹果还是android?
//2 添加Thread.sleep方法到getIOS，先打印苹果还是android?
//3 添加Hello方法，先打印苹果还是Hello?
//4 现在有两部手机，先打印苹果还是android?
//5 现在两个静态同步方法，同一部手机，先打印苹果还是android?
//6 现在两个静态同步方法，有2部手机，先打印苹果还是android?
//7 现在1个静态同步方法,1个普通同步方法，同一部手机，先打印苹果还是android?
//8 现在1个静态同步方法,1个普通同步方法，有2部手机，先打印苹果还是android?


class Phone
{
	public static synchronized void getIOS()throws Exception
	{
		Thread.sleep(4000);
		System.out.println("-----getIOS");
	}
	public  synchronized void getAndroid()throws Exception
	{
		System.out.println("-----getAndroid");
	}
	
	public void getHello()
	{
		System.out.println("-----getHello");		
	}
}


public class ThreadDemo3
{
	public static void main(String[] args)
	{
		
		final Phone phone = new Phone();
		
		final Phone phone2 = new Phone();
		
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				for (int i = 0; i <1; i++)
				{
					try
					{
						phone.getIOS();
					} catch (Exception e){
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
				for (int i = 0; i <1; i++)
				{
					try
					{
						//phone.getAndroid();
						//phone.getHello();
						phone2.getAndroid();
					} catch (Exception e){
						e.printStackTrace();
					}
				}
			}
		},"BB").start();		
		
		
	}
}














//3 新增普通方法试试,苹果还是hello?
//4 新增第2部手机试试，苹果还是android？
//5 换成静态同步方法，同一部手机，苹果还是android？
//6 换成静态同步方法，2部手机，苹果还是android？
//7 一个静态同步方法，另一个非静态同步方法，同一部手机，苹果还是android？
//8 一个静态同步方法，另一个非静态同步方法，2部手机，苹果还是android？