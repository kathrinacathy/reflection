/**
 * Created by admin on 2019/3/12 22:17
 *
 * @Author: created by admin
 * @Date: created in 22:17 2019/3/12
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:  线程安全的
 * @version:
 */

public class Singleton5 {

    private static Singleton5 instance;
    private Singleton5() {
    }

    public static Singleton5 getInstance () throws InterruptedException {
        if(instance == null) {
            synchronized (Singleton5.class) {
                if(instance == null) {
                    Thread.sleep(100);
                    instance = new Singleton5();
                }
            }
        }
        return instance;
    }
}
