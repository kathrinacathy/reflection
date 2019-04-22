/**
 * Created by admin on 2019/3/13 10:32
 *
 * @Author: created by admin
 * @Date: created in 10:32 2019/3/13
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public class ThreadPoolCreate {



    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run() {
//            super.run();
                System.out.println("创建新线程t1");
            }
        };

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                System.out.println("创建线程t2");
            }
        });



    }


}
