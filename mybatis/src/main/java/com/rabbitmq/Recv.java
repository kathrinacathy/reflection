package com.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 * Created by admin on 2019/4/28 11:07
 *
 * @Author: created by admin
 * @Date: created in 11:07 2019/4/28
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:消息接收方，创建连接，根据连接创建信道，声明一个队列，设置好接收方确认机制，接收消息
 * 为什么我们不使用try-with-resource语句来自动关闭通道和连接？通过这样做，我们只需让程序继续运行，关闭所有内容，然后退出！
 * 这将是尴尬的，因为我们希望在消费者异步监听消息到达时，该进程保持活动状态。
 * @version:
 */

public class Recv {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.163.131");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
//请注意，我们也在这里声明了队列。因为我们可能在发布者之前启动消费者，所以我们希望在尝试使用消息之前确保队列存在。
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
//我们即将告诉服务器从队列中传递消息。因为它会异步地向我们发送消息，
// 所以我们以对象的形式提供一个回调，它将缓冲消息，直到我们准备好使用它们。这就是DeliverCallback子类的作用。
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
