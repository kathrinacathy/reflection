package com.atguigu.reflect.InstanceTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Test;

public class TestMethod {
	//1.��ȡ����ʱ��ķ���
	
	@Test
	public void test1(){
		Class clazz = Person.class;
		//1.getMethods():��ȡ����ʱ�༰�丸�������е�����Ϊpublic�ķ���
		Method[] m1 = clazz.getMethods();
		for(Method m : m1){
			System.out.println(m);
		}
		System.out.println();
		
		//2.getDeclaredMethods():��ȡ����ʱ�౾�����������еķ���
		Method[] m2 = clazz.getDeclaredMethods();
		for(Method m : m2){
			System.out.println(m);
		}
	}
	//ע�� Ȩ�����η� ����ֵ���� ������ �β��б� �쳣
	@Test
	public void test2(){
		Class clazz = Person.class;
		
		Method[] m2 = clazz.getDeclaredMethods();
		for(Method m : m2){
			//1.ע��
			Annotation[] ann = m.getAnnotations();
			for(Annotation a : ann){
				System.out.println(a);
			}
			
			//2.Ȩ�����η�
			String str = Modifier.toString(m.getModifiers());
			System.out.print(str + " ");
			//3.����ֵ����
			Class returnType = m.getReturnType();
			System.out.print(returnType.getName() + " ");
			//4.������
			System.out.print(m.getName() + " ");
			
			//5.�β��б�
			System.out.print("(");
			Class[] params = m.getParameterTypes();
			for(int i = 0;i < params.length;i++){
				System.out.print(params[i].getName() + " args-" + i + " ");
			}
			System.out.print(")");
			
			//6.�쳣����
			Class[] exps = m.getExceptionTypes();
			if(exps.length != 0){
				System.out.print("throws ");
			}
			for(int i = 0;i < exps.length;i++){
				System.out.print(exps[i].getName() + " ");
			}
			System.out.println();
		}
	}
	//��������ʱ����ָ���ķ���
	@Test
	public void test3() throws Exception{
		Class clazz = Person.class;
		//getMethod(String methodName,Class ... params):��ȡ����ʱ��������Ϊpublic��ָ���ķ���
		Method m1 = clazz.getMethod("show");
		Person p = (Person)clazz.newInstance();
		//����ָ���ķ�����Object invoke(Object obj,Object ... obj)
		Object returnVal = m1.invoke(p);//����һ����
		System.out.println(returnVal);//null
		
		Method m2 = clazz.getMethod("toString");
		Object returnVal1 = m2.invoke(p);
		System.out.println(returnVal1);//Person [name=null, age=0]
		//��������ʱ���о�̬�����ĵ���
		Method m3 = clazz.getMethod("info");
		m3.invoke(Person.class);
		
		//getDeclaredMethod(String methodName,Class ... params):��ȡ����ʱ���������˵�ָ���ķ���
		Method m4 = clazz.getDeclaredMethod("display",String.class,Integer.class);
		m4.setAccessible(true);
		Object value = m4.invoke(p,"CHN",10);//�ҵĹ����ǣ�CHN
		System.out.println(value);//10
	}
}
