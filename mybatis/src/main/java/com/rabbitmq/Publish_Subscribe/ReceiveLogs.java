package com.rabbitmq.Publish_Subscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 * Created by admin on 2019/4/28 22:54
 *
 * @Author: created by admin
 * @Date: created in 22:54 2019/4/28
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public class ReceiveLogs {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.163.131");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        //每当我们连接到Rabbit时，我们都需要一个新的空队列。为此，我们可以使用随机名称创建队列，或者更好 - 让服务器为我们选择随机队列名称。其次，一旦我们断开消费者，就应该自动删除队列。
        //在Java客户端中，当我们没有向queueDeclare（）提供参数时，我们 使用生成的名称创建一个非持久的，独占的自动删除队列
        String queueName = channel.queueDeclare().getQueue();
        //现在我们需要告诉交换机将消息发送到我们的队列。交换和队列之间的关系称为绑定。
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }
}
