package com.atguigu.reflect.InstanceTest;

import java.lang.reflect.Constructor;

import org.junit.Test;

public class TestConstructor {
	@Test
	public void test1() throws Exception{
		String className = "com.atguigu.java.Person";
		Class clazz = Class.forName(className);
		//������Ӧ������ʱ��Ķ���ʹ��newInstance()��ʵ���Ͼ��ǵ���������ʱ��ĿղεĹ�������
		//Ҫ���ܹ������ɹ�����Ҫ���Ӧ������ʱ��Ҫ�пղεĹ��������ڹ�������Ȩ�����η���Ȩ��Ҫ�㹻��
		Object obj = clazz.newInstance();
		Person p = (Person)obj;
		System.out.println(p);
	}
	
	@Test
	public void test2() throws ClassNotFoundException{
		String className = "com.atguigu.java.Person";
		Class clazz = Class.forName(className);
		
		Constructor[] cons = clazz.getDeclaredConstructors();
		for(Constructor c : cons){
			System.out.println(c);
		}
	}
	
	//����ָ���Ĺ�����,��������ʱ��Ķ���
	@Test
	public void test3() throws Exception{
		String className = "com.atguigu.java.Person";
		Class clazz = Class.forName(className);
		
		Constructor cons = clazz.getDeclaredConstructor(String.class,int.class);
		cons.setAccessible(true);
		Person p = (Person)cons.newInstance("��ΰ",20);
		System.out.println(p);
	}
}
