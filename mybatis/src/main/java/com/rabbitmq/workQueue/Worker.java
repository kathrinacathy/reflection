package com.rabbitmq.workQueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 * Created by admin on 2019/4/28 14:27
 *
 * @Author: created by admin
 * @Date: created in 14:27 2019/4/28
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public class Worker {
    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.163.131");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
//可以使用basicQos方法和  prefetchCount = 1设置。这告诉RabbitMQ一次不向一个worker发送一条消息。
// 或者，换句话说，在处理并确认前一个消息之前，不要向工作人员发送新消息。相反，它会将它发送给下一个仍然很忙的工人。
        channel.basicQos(1);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");

            System.out.println(" [x] Received '" + message + "'");
            try {
                doWork(message);
            } finally {
                System.out.println(" [x] Done");
                //手动确认消息ack：默认情况下，手动消息确认已打开。在前面的示例中，我们通过autoAck = true 标志明确地将它们关闭。
                // 一旦我们完成任务，就应该将此标志设置为false并从工作人员发送正确的确认。
                //multiple第二个参数，true表示确认全部消息 x < deliverytag 的所有消息都会确认，false表示只确认当前这条消息
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                //用于传递确认的API方法通常作为客户端库中的通道上的操作公开。
                // Java客户端用户将分别使用Channel＃basicAck和Channel＃basicNack 来执行basic.ack和basic.nack。
                // 这是一个Java客户端示例，它表明了一个积极的认可：

                /**
                 * channel.basicRecover(); 补发  里面有个参数如果是true表示会发给不同的consumer消费者，false表示会发给同一个consumer
                 *
                 * basic.ack用于肯定确认
                 * basic.nack用于否定确认（注意：这是AMQP 0-9-1的RabbitMQ扩展）  可以批量拒绝也可以单个
                 * basic.reject用于否定确认，但与basic.nack相比有一个限制
                 */
            }
        };
        //消费端的确认：1 自动确认 2 手动确认
        channel.basicConsume(TASK_QUEUE_NAME, false, deliverCallback, consumerTag -> { });
    }

    /**
     * 发布端确认:
     * confirm机制
     *开启确认
     * channel.confirmSelect
     * 执行发布
     *    channel.basicPublish
     * 等待确认是否成功
     * channel.waitForConfirms
     *
     * tx事务机制
     * channel.txSelect();
     * channel.txCommit();
     * channel.txRollback();
     *
     */




    private static void doWork(String task) {
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException _ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
