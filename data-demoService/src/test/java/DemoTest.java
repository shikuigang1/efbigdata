import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
public class DemoTest {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring/spring-dubbo.xml"});
        context.start();

        System.in.read(); // 为保证服务一直开着，利用输入流的阻塞来模拟
    }
}
