package com.rabbitmq.workQueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * Created by admin on 2019/4/28 14:20
 *
 * @Author: created by admin
 * @Date: created in 14:20 2019/4/28
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:  发布消息
 *
 *
 *content_type
 * content_encoding
 * priority
 * correlation_id
 * reply_to
 * expiration
 * message_id
 * timestamp
 * type
 * user_id
 * app_id
 * cluster_id
 *
 *
 *
 *
 * @version:
 */





public class NewTask {
    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.163.131");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            //确保消息不会丢失需要做两件事：我们需要将队列和消息都标记为持久。
            //1需要声明队列是持久的
            //2现在我们需要将消息标记为持久性 - 通过将MessageProperties（实现BasicProperties）设置为值PERSISTENT_TEXT_PLAIN。
            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);//参数{"x-message-ttl", 1000*8 }

            String message = String.join(" ", argv);
            //此时我们确信即使RabbitMQ重新启动，task_queue队列也不会丢失。现在我们需要将消息标记为持久性 -
            // 通过将MessageProperties（实现BasicProperties）设置为值PERSISTENT_TEXT_PLAIN。

            //第一个参数是交换的名称。空字符串表示默认或无名交换：消息通过routingKey指定的名称路由到队列（如果存在）,routingkey指定名称跟队列名称相同找到是否存在,如果没有找到就可以传递消息到队列
            channel.basicPublish("", TASK_QUEUE_NAME,
                    MessageProperties.PERSISTENT_TEXT_PLAIN,        //String expiration 消息过期时间
                    message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }

}
