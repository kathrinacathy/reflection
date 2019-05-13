package com.atguigu.reflect.InstanceTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

import org.junit.Test;

public class TestReflection {
	//������ļ�������ClassLoader
	@Test
	public void test5() throws Exception{
		ClassLoader loader1 = ClassLoader.getSystemClassLoader();
		System.out.println(loader1);
		
		ClassLoader loader2 = loader1.getParent();
		System.out.println(loader2);
		
		ClassLoader loader3 = loader2.getParent();
		System.out.println(loader3);
		
		Class clazz1 = Person.class;
		ClassLoader loader4 = clazz1.getClassLoader();
		System.out.println(loader4);
		
		String className = "java.lang.String";
		Class clazz2 = Class.forName(className);
		ClassLoader loader5 = clazz2.getClassLoader();
		System.out.println(loader5);
		
		//��������
		//��һ��
		ClassLoader loader = this.getClass().getClassLoader();
		InputStream is = loader.getResourceAsStream("com\\atguigu\\java\\jdbc.properties");
		//������
//		FileInputStream is = new FileInputStream(new File("jdbc1.properties"));
		
		Properties pros = new Properties();
		pros.load(is);
		String name = pros.getProperty("user");
		System.out.println(name);
		
		String password = pros.getProperty("password");
		System.out.println(password);
		
	}
	//��λ�ȡClass��ʵ����3�֣�
	@Test
	public void test4() throws ClassNotFoundException{
		//1.��������ʱ�౾���.class����
		Class clazz1 = Person.class;
		System.out.println(clazz1.getName());
		
		Class clazz2 = String.class;
		System.out.println(clazz2.getName());
		
		//2.ͨ������ʱ��Ķ����ȡ
		Person p = new Person();
		Class clazz3 = p.getClass();
		System.out.println(clazz3.getName());
		
		//3.ͨ��Class�ľ�̬������ȡ.ͨ���˷�ʽ�����һ�£�����Ķ�̬�ԡ�
		String className = "com.atguigu.java.Person";
		
		
		Class clazz4 = Class.forName(className);
//		clazz4.newInstance();
		System.out.println(clazz4.getName());
		
		//4.���˽⣩ͨ����ļ�����
		ClassLoader classLoader = this.getClass().getClassLoader();
		Class clazz5 = classLoader.loadClass(className);
		System.out.println(clazz5.getName());
		
		System.out.println(clazz1 == clazz3);//true
		System.out.println(clazz1 == clazz4);//true
		System.out.println(clazz1 == clazz5);//true
	}
	
	/*
	 * java.lang.Class:�Ƿ����Դͷ��
	 * ���Ǵ�����һ���࣬ͨ�����루javac.exe��,���ɶ�Ӧ��.class�ļ���֮������ʹ��java.exe���أ�JVM�����������ɵģ�
	 * ��.class�ļ�����.class�ļ����ص��ڴ��Ժ󣬾���һ������ʱ�࣬�����ڻ���������ô�������ʱ�౾�����һ��Class��ʵ����
	 * 1.ÿһ������ʱ��ֻ����һ�Σ�
	 * 2.����Class��ʵ���Ժ����ǲſ��Խ������µĲ�����
	 *     1��*������Ӧ������ʱ��Ķ���
	 *     2����ȡ��Ӧ������ʱ��������ṹ�����ԡ����������������ڲ��ࡢ���ࡢ���ڵİ����쳣��ע�⡢...��
	 *     3��*���ö�Ӧ������ʱ���ָ���Ľṹ(���ԡ�������������)
	 *     4�������Ӧ�ã���̬����
	 */
	@Test
	public void test3(){
		Person p = new Person();
		Class clazz = p.getClass();//ͨ������ʱ��Ķ��󣬵�����getClass()������������ʱ�ࡣ
		System.out.println(clazz);
	}
	
	
	//���˷��䣬����ͨ�����䴴��һ����Ķ��󣬲��������еĽṹ
	@Test
	public void test2() throws Exception{
		Class clazz = Person.class;
		
//		Class clazz1 = String.class;
		
		//1.����clazz��Ӧ������ʱ��Person��Ķ���
		Person p = (Person)clazz.newInstance();
		System.out.println(p);
		//2.ͨ�������������ʱ���ָ��������
		//2.1
		Field f1 = clazz.getField("name");
		f1.set(p,"LiuDeHua");
		System.out.println(p);
		//2.2
		Field f2 = clazz.getDeclaredField("age");
		f2.setAccessible(true);
		f2.set(p, 20);
		System.out.println(p);
		
		//3.ͨ�������������ʱ���ָ���ķ���
		Method m1 = clazz.getMethod("show");
		m1.invoke(p);
		
		Method m2 = clazz.getMethod("display",String.class);
		m2.invoke(p,"CHN");
		
	}
	
	//���з�����ǰ����δ���һ����Ķ��󣬲��������еķ���������
	@Test
	public void test1(){
		Person p = new Person();
//		Person p1 = new Person();
		p.setAge(10);
		p.setName("TangWei");
		System.out.println(p);
		p.show();
//		p.display("HK");
	}
}
