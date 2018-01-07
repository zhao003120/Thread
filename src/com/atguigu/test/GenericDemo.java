package com.atguigu.test;

import java.util.*;

public class GenericDemo
{
	
	//public void methodA(List<Object> mylist)
	//public void methodA(List<? extends Object> mylist)
	public void methodA(List<?> mylist)
	{
	}
	
	public static void main(String[] args)
	{
		GenericDemo test = new GenericDemo();
		
		test.methodA(new ArrayList<Object>());
		test.methodA(new LinkedList<Integer>());
		test.methodA(new ArrayList<String>());
	}		
}