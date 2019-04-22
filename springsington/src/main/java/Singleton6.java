/**
 * Created by admin on 2019/3/12 22:17
 *
 * @Author: created by admin
 * @Date: created in 22:17 2019/3/12
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description: 静态内部类不会自动随着外部类的家中和自动化而初始化，屙屎单独加载和初始化
 * 因为是在内部类加载和初始化时候创建的，因此是线程安全的。
 * @version: 线程安全，用内部类，饱汉式
 */

public class Singleton6 {


    private Singleton6 () {

    }

    private static class inner {
        private static final Singleton6 INSTANCE = new Singleton6();
    }

    public static Singleton6 getInstance() {
        return inner.INSTANCE;
    }
}
