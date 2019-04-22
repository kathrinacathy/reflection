import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by admin on 2019/4/21 0:37
 *
 * @Author: created by admin
 * @Date: created in 0:37 2019/4/21
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public class RedisTest {


    @Test
    public void test() {

        Jedis jedis = new Jedis("192.168.163.131",6379);
        Transaction transaction = jedis.multi();
        transaction.exec();
        System.out.println(jedis.ping());
    }

    @Test()
    public void test1() {
        Jedis jedis = new Jedis("192.168.163.131",6379);
        jedis.setnx("k1","v1");
        jedis.setnx("k2","v2");
        jedis.setnx("k3","v3");

        System.out.println(jedis.get("balance"));

        Set<String> keys = jedis.keys("*");
        Iterator<String> iterator = keys.iterator();
        while(iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }
    }

    @Test
    public void test2() {
        Jedis jedis = new Jedis("192.168.163.131",6379);

        //key
        Set<String> keys = jedis.keys("*");

        for(Iterator<String> iterator = keys.iterator();iterator.hasNext();){
            System.out.println(iterator.next());
        }

        Long size = jedis.dbSize();
        Boolean det = jedis.exists("det");
        System.out.println("size:"+size);
        System.out.println("det:"+det);
        //String
        Long aLong = jedis.append("k1", "jedis");
        System.out.println(aLong);

        String mset = jedis.mset("k4", "v4", "k5", "v5","k6","v6");
        System.out.println(jedis.mget("k4","k5","k6"));
        //List
        jedis.lpush("list01","v1","v2","v3","v4","v5");
        List<String> list01 = jedis.lrange("list01", 0, -1);
        for(String s :list01){
            System.out.println(s);
        }

        //Set
        jedis.sadd("set01","jd001","jd001");
        jedis.sadd("set01","jd003","jd004","jd005");
        Set<String> set01 = jedis.smembers("set01");
        for(Iterator<String> iterator=set01.iterator();iterator.hasNext();){
            System.out.println(iterator.next());
        }
        Long srem = jedis.srem("set01", "jd002");
        System.out.println(srem);

        //hash
        jedis.hset("hash01","userName","lisi");
        String hget = jedis.hget("hash01", "userName");
        System.out.println(hget);

        Map<String,String> map = new HashMap<>();
        map.put("age","22");

        map.put("address","atgugu" );
        map.put("telephone","23232");
        String hash02 = jedis.hmset("hash02", map);
        List<String> hmget = jedis.hmget("hash02", "age", "address", "telephone");
        for(String s:hmget){
            System.out.println(s);
        }

        //Zset
        jedis.zadd("zset01",60d,"v1");
        jedis.zadd("zset01",70d,"v2");
        jedis.zadd("zset01",80d,"v3");
        jedis.zadd("zset01",90d,"v4");
        jedis.zadd("zset01",100d,"v5");
        Set<String> zset01 = jedis.zrange("zset01", 0, -1);

        for(Iterator<String> iterator1 = zset01.iterator();iterator1.hasNext();){
            System.out.println(iterator1.next());
        }


    }

    //事务
    @Test
    public void test3(){
        Jedis jedis = new Jedis("192.168.163.131",6379);

        Transaction transaction = jedis.multi();
        Response<String> set = transaction.set("k44", "v9");
        Response<String> set1 = transaction.set("k55", "v10");
//        transaction.exec();
        String discard = transaction.discard();
        String k10 = jedis.get("k44");
        System.out.println(k10);
    }

    @Test
    public void test4 () {
        boolean b = transacMethod();
        System.out.println(b);
    }

    public boolean transacMethod(){
        Jedis jedis = new Jedis("192.168.163.131",6379);

        Integer balance;
        Integer det;
        //消费金额
        Integer mntToSubstract = 10;

        jedis.watch("balance");//类似于乐观锁,保证操作在一个事务中
        balance = Integer.parseInt(jedis.get("balance"));
        if(balance.compareTo(mntToSubstract) < 0){
            jedis.unwatch();
            return false;
        }else {
            Transaction transaction = jedis.multi();
            transaction.decrBy("balance",mntToSubstract);
            transaction.incrBy("det",mntToSubstract);
            transaction.exec();
            balance = Integer.parseInt(jedis.get("balance"));
            det = Integer.parseInt(jedis.get("det"));
            System.out.println("balance:"+balance+",det:"+det);
            return true;

        }
    }

    //主从复制
    @Test
    public void test5() {
        Jedis jedis_M = new Jedis("192.168.163.131",6379);
        Jedis jedis_S = new Jedis("192.168.163.131",6380);
        jedis_M.slaveofNoOne();
        jedis_S.slaveofNoOne();
        jedis_S.slaveof("192.168.163.131",6379);

        jedis_M.set("km","testMaster_Slave");

        String km = jedis_S.get("km");
        System.out.println(km);


    }

    @Test
    public void test6() {
        JedisPool jedisPool = JedisPollUtil.getJedisPoolInstance();
        Jedis jedis= jedisPool.getResource();
        jedis.set("k1","v1");
        String k1 = jedis.get("k1");
        System.out.println(k1);
    }
}
