/**
 * Created by admin on 2019/3/12 22:14
 *
 * @Author: created by admin
 * @Date: created in 22:14 2019/3/12
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 *
 * 饿汉式：
 *  1 直接实例化（简洁直观）
 *  2 枚举（最简洁）
 *  3 静态代码块（适合复杂实例化）
 *
 * 饱汉式
 *  1 线程不安全（适用于单线程）
 *  2 线程安全
 *  3 静态内部类形式（适用于多线程）
 *
 * @Description:
 * @version:
 */

public class Singleton1 {

    public static final Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {};
}
