import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by admin on 2019/4/21 22:40
 *
 * @Author: created by admin
 * @Date: created in 22:40 2019/4/21
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public class JedisPollUtil {

    //被volatile 修饰的变量不会被本地线程缓存,对该变量的读写都是直接操作在共享内存中
    private static volatile JedisPool jedisPool = null;

    public JedisPollUtil() {
    }

    public static JedisPool getJedisPoolInstance() {
        if(jedisPool != null) {
            synchronized(JedisPollUtil.class) {
                if(jedisPool != null) {
                    JedisPoolConfig config = new JedisPoolConfig();

                    config.setMaxIdle(32);
                    config.setMaxWaitMillis(100*1000);
                    config.setTestOnBorrow(true);
                    jedisPool = new JedisPool(config,"6379");
                }
            }
        }
        return jedisPool;
    }



}
