package com.atguigu.reflect.InstanceTest;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.Test;

public class TestField {
	//��ȡ��Ӧ������ʱ�������
	@Test
	public void test1(){
		Class clazz = Person.class;
		//1.getFields():ֻ�ܻ�ȡ������ʱ���м��丸��������Ϊpublic������
		Field[] fields = clazz.getFields();
		for(int i = 0;i < fields.length;i++){
			System.out.println(fields[i]);
		}
		System.out.println();
		//2.getDeclaredFields():��ȡ����ʱ�౾�����������е�����
		Field[] fields1 = clazz.getDeclaredFields();
		for(Field f : fields1){
			System.out.println(f.getName());
		}
	}
	//Ȩ�����η�  �������� ������
	//��ȡ���Եĸ������ֵ�����
	@Test
	public void test2(){
		Class clazz = Person.class;
		Field[] fields1 = clazz.getDeclaredFields();
		for(Field f : fields1){
			//1.��ȡÿ�����Ե�Ȩ�����η�
			int i = f.getModifiers();
			String str1 = Modifier.toString(i);
			System.out.print(str1 + " ");
			//2.��ȡ���Ե�����
			Class type = f.getType();
			System.out.print(type.getName() + " ");
			//3.��ȡ������
			System.out.print(f.getName());
			
			System.out.println();
		}
	}
	
	//��������ʱ����ָ��������
	@Test
	public void test3() throws Exception{
		Class clazz = Person.class;
		//1.��ȡָ��������
		//getField(String fieldName):��ȡ����ʱ��������Ϊpublic��ָ��������ΪfieldName������
		Field name = clazz.getField("name");
		//2.��������ʱ��Ķ��� 
		Person p = (Person)clazz.newInstance();
		System.out.println(p);
		//3.������ʱ���ָ�������Ը�ֵ
		name.set(p,"Jerry");
		System.out.println(p);
		System.out.println("%"+name.get(p));
		
		System.out.println();
		//getDeclaredField(String fieldName):��ȡ����ʱ����ָ������ΪfieldName������
		Field age = clazz.getDeclaredField("age");
		//��������Ȩ�����η������ƣ�Ϊ�˱�֤���Ը����Ը�ֵ����Ҫ�ڲ���ǰʹ�ô����Կɱ�������
		age.setAccessible(true);
		age.set(p,10);
		System.out.println(p);
		
//		Field id = clazz.getField("id");
		
	}
	
}
