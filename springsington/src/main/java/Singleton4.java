/**
 * Created by admin on 2019/3/12 22:17
 *
 * @Author: created by admin
 * @Date: created in 22:17 2019/3/12
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:  线程不安全
 * @version:
 */

public class Singleton4 {

    private static Singleton4 instance;
    private Singleton4() {
    }

    public static Singleton4 getInstance () throws InterruptedException {
        if(instance == null) {
            Thread.sleep(100);
            instance = new Singleton4();
        }
        return instance;
    }
}
