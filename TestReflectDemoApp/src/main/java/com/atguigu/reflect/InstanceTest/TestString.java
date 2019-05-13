package com.atguigu.reflect.InstanceTest;
//ʵ���ַ����ķ�ת�����м��ַ�����
//ʱ�临�Ӷ�  �ռ临�Ӷ�
public class TestString {
	public static void main(String[] args) {
		String str1 = reverse("helloworld");
		System.out.println(str1);
		
		String str2 = reverse1("helloworld");
		System.out.println(str2);
		
		String str3 = reverse2("helloworld");
		System.out.println(str3);
	}
	
	//��������
	public static String reverse2(String str){
		StringBuffer sb = new StringBuffer();
		for(int i = str.length()-1;i >= 0;i--){
			sb.append(str.charAt(i));
		}
		return sb.toString();
	}
	
	//������:
	public static String reverse1(String str){
		StringBuffer sb = new StringBuffer(str);
		return sb.reverse().toString();
	}
	//����һ��
	public static String reverse(String str){
		char[] c = str.toCharArray();
		for(int x = 0,y = c.length-1;x < y;x++,y--){
			char temp = c[x];
			c[x] = c[y];
			c[y] = temp;
		}
		return new String(c);
	}
}
