package com.rabbitmq.rpc;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

/**
 * Created by admin on 2019/4/28 23:25
 *
 * @Author: created by admin
 * @Date: created in 23:25 2019/4/28
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:为了说明如何使用RPC服务，我们将创建一个简单的客户端类。它将公开一个名为call的方法，该方法 发送一个RPC请求并阻塞，直到收到答案为止：
 * @version:
 */

public class RPCClient implements AutoCloseable  {
    private Connection connection;
    private Channel channel;
    private String requestQueueName = "rpc_queue";

    public RPCClient() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.163.131");

        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    public static void main(String[] argv) {
        try (RPCClient fibonacciRpc = new RPCClient()) {
            for (int i = 0; i < 32; i++) {
                String i_str = Integer.toString(i);
                System.out.println(" [x] Requesting fib(" + i_str + ")");
                String response = fibonacciRpc.call(i_str);
                System.out.println(" [.] Got '" + response + "'");
            }
        } catch (IOException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String call(String message) throws IOException, InterruptedException {
        final String corrId = UUID.randomUUID().toString();
//一般来说，通过RabbitMQ进行RPC很容易。客户端发送请求消息，服务器回复响应消息。
// 为了接收响应，我们需要发送带有请求的“回调”队列地址。我们可以使用默认队列（在Java客户端中是独占的）
        String replyQueueName = channel.queueDeclare().getQueue();
        //预定义了一组带有消息的14个属性在BasicProperties中。大多数属性很少使用，但以下情况除外：
        //deliveryMode：将消息标记为持久性（值为2）或瞬态（任何其他值）。你可能还记得第二篇教程中的这个属性。
        //contentType：用于描述编码的mime类型。例如，对于经常使用的JSON编码，将此属性设置为：application / json是一种很好的做法。
        //replyTo：通常用于命名回调队列。
        //correlationId：用于将RPC响应与请求相关联。
        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();

        channel.basicPublish("", requestQueueName, props, message.getBytes("UTF-8"));

        final BlockingQueue<String> response = new ArrayBlockingQueue<>(1);

        String ctag = channel.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                response.offer(new String(delivery.getBody(), "UTF-8"));
            }
        }, consumerTag -> {
        });

        String result = response.take();
        channel.basicCancel(ctag);
        return result;
    }

    public void close() throws IOException {
        connection.close();
    }
}
