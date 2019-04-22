import java.io.IOException;
import java.util.Properties;

/**
 * Created by admin on 2019/3/12 22:17
 *
 * @Author: created by admin
 * @Date: created in 22:17 2019/3/12
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public class Singleton3 {

    public static final Singleton3 INSTANCE;
    private String info;
    static {
        Properties pro = new Properties();
        try {
            pro.load(Singleton3.class.getResourceAsStream("single.properties"));
        } catch (IOException e) {
           throw new RuntimeException(e);
        }

        INSTANCE = new Singleton3(pro.getProperty("info"));
    }
    private Singleton3(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Singleton3{" +
                "info='" + info + '\'' +
                '}';
    }
}
