package com.atguigu.reflect;

/**
 * Created by admin on 2019/5/10 10:09
 *
 * @Author: created by admin
 * @Date: created in 10:09 2019/5/10
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */
@MyAnnotation("hello")
public class Person extends Creature<String> implements Comparable,MyInterface {

    public String name;
    private int age;
    int id;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person() {
    }

    private Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @MyAnnotation("showAbc")
    public void show() {
        System.out.println("hello show");
    }

    private void display(String nation) throws Exception {
        System.out.println("hello 我是来自："+nation);
    }

    public int compareTo(Object o) {
        return 0;
    }

    public static void info () {
        System.out.println("中国人");
    }

    private Integer fly(String nation) {
        System.out.println("我的国际是："+nation);
        return 1;
    }

    /**
     * 内部类
     */
    class birth{

    }

}
