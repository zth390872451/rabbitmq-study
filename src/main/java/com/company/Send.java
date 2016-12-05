package com.company;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 *参考链接： http://blog.csdn.net/zhu_tianwei/article/details/40835555
 */
public class Send {

    private final static String QUEUE_NAME="hello_queue";

    public static void main(String[] args)throws Exception {
        //创建连接到 RabbitMq
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setPort(AMQP.PROTOCOL.PORT);//默认端口
        //创建一个连接
        Connection connection = factory.newConnection();
        //创建一个频道
        Channel channel = connection.createChannel();
        //指定一个队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //发送的消息
        String message = "hello world!";
        //往队列中发出一条消息
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("Sent Message：'" + message + "'");
        //关闭频道和连接
        channel.close();
        connection.close();


    }

}
