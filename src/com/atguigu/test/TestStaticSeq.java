package com.atguigu.test;


class Father
{
	public Father(){
		System.out.println("111111");
	}
	{
		System.out.println("222222");
	}
	static{
		System.out.println("333333");
	}
}
class Son extends Father
{
	public Son(){
		System.out.println("444444");
	}
	{
		System.out.println("555555");
	}
	static{
		System.out.println("666666");
	}	
}
public class TestStaticSeq
{
	public static void main(String[] args)
	{
		new Son();
		System.out.println("=====================");
		new Son();
		System.out.println("=====================");
		new Father();
	}
}
//	3 1 2 6 4 5
//	6 1 3 2 4 5
//=============

