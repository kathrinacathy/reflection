import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by admin on 2019/3/12 22:41
 *
 * @Author: created by admin
 * @Date: created in 22:41 2019/3/12
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public class SingleTonTest {


    @Test
    public void testSingleton1() {
        Singleton1 instance = Singleton1.INSTANCE;
        System.out.println(instance);
    }

    @Test
    public void testSingleton2() {
        Singleton2 instance = Singleton2.INSTANCE;
        System.out.print(instance);
    }


    @Test
    public void testSingleton3() {
        Singleton3 instance = Singleton3.INSTANCE;
        System.out.print(instance);
    }

    @Test
    public void testSingleton4() throws InterruptedException {
        Singleton4 s1 = Singleton4.getInstance();
        Singleton4 s2 = Singleton4.getInstance();

        System.out.println(s1==s2);
        System.out.println(s1);
        System.out.println(s2);
    }

    @Test
    public void testSingleton5() {
        /*Singleton4 s1 = Singleton4.getInstance();
        Singleton4 s2 = Singleton4.getInstance();

        System.out.println(s1==s2);
        System.out.println(s1);
        System.out.println(s2);*/

        Callable<Singleton4> c = new Callable<Singleton4>() {
            public Singleton4 call() throws Exception {
                return Singleton4.getInstance();
            }
        };

        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Singleton4> f1 = service.submit(c);
        Future<Singleton4> f2 = service.submit(c);
        try {
            Singleton4 s1 = f1.get();
            Singleton4 s2 = f2.get();
            System.out.println(s1==s2);
            System.out.println(s1);
            System.out.println(s2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        service.shutdown();

    }



    @Test
    public void test6() {
        Integer demo1 = 100;
        int demo2 = 100;

        Integer demo3 = 10000;
        int demo4 = 10000;

        Integer demo5 = 100;
        Integer demo6 = 100;

        Integer demo7 = 10000;
        Integer demo8 = 10000;

        int demo10=10000;
        int demo9=10000;
        System.out.println(demo1==demo2);
        System.out.println(demo3==demo4);
        System.out.println(demo5==demo6);
        System.out.println(demo7==demo8);
        System.out.println(demo9==demo10);
    }

    @Test
    public void test7() {
        Thread t1 = new Thread(){
            @Override
            public void run() {
//            super.run();
                System.out.println("创建新线程t1");
            }
        };

        t1.start();



        Thread t2 = new Thread(new Runnable() {
            public void run() {
                System.out.println("创建线程t2");
            }
        });
        t2.start();




        Callable<Integer> oneCallable = new Callable<Integer>() {
            public Integer call() throws Exception {
                return 10;
            }
        };
//由Callable<Integer>创建一个FutureTask<Integer>对象：
        FutureTask<Integer> oneTask = new FutureTask<Integer>(oneCallable);
//注释：FutureTask<Integer>是一个包装器，它通过接受Callable<Integer>来创建，它同时实现了Future和Runnable接口。
        //由FutureTask<Integer>创建一个Thread对象：
        Thread oneThread = new Thread(oneTask);
        oneThread.start();
//至此，一个线程就创建完成了。


    }
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Test
    public void test8 () throws ParseException {
//        在2017年10月11日晚上10点执行任务。
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("定时任务执行了....");
            }
        }, format.parse("2017-10-11 22:00:00"));
    }


    @Test
    public void test9() {
        Timer timer = new Timer();
//        每隔5s执行一次
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                System.out.println("Hello");
            }
        }, new Date(), 5000);

    }


    @Test
    public void test10 () {
        Instant i = Instant.now();
        System.out.println(i);
        OffsetDateTime offsetDateTime = i.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        long l = i.toEpochMilli();
        System.out.println(l);

        Instant instant = Instant.ofEpochMilli(1554220737119L);
        System.out.println(instant);

    }
    @Test
    public void test11() {

        Instant instant1 = Instant.now();




        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant instant2 = Instant.now();

        Duration duration = Duration.between(instant1,instant2);
        System.out.println(duration.toMillis());
    }

    @Test
    public void test12() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime time = LocalDateTime.now();

        String format1 = time.format(formatter);
        String format2 = formatter.format(time);

        System.out.println(format1);
        System.out.println(format2);


    }

    /**
     * 将long类型的timestamp转为LocalDateTime
     */
    @Test
    public void test13() {

        Long time =1554220737119L;
        Instant instant = Instant.ofEpochMilli(time);
        ZoneId zoneId = ZoneId.systemDefault();

        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        System.out.println(localDateTime);
    }

    /**
     * 将LocalDateTime转为long类型的timestamp
     */
    @Test
    public void test14() {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
//        LocalDate localDate = zonedDateTime.toLocalDate();
//        LocalDateTime localDateTime1 = zonedDateTime.toLocalDateTime();
        Instant instant = zonedDateTime.toInstant();
        long l = instant.toEpochMilli();
        System.out.println(l);
    }

    /**
     * Date转换为LocalDatetime，我们可以使用以下方法：
     */
    @Test
    public void test15() {
        Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        System.out.println("Date = " + date);

        System.out.println("LocalDateTime = " + localDateTime);


        System.out.println("___________________________________");

        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(date.toInstant(), zoneId);
        System.out.println(localDateTime1);
    }

    /**
     * Java 8中将LocalDateTime转换为Date
     */
    @Test
    public void test16() {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);

        Date date = Date.from(zdt.toInstant());

        System.out.println("LocalDateTime = " + localDateTime);
        System.out.println("Date = " + date);
    }


}
