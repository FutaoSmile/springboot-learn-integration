package com.futao.socket.udp;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

/**
 * 基于UDP的Socket通信-服务器端
 *
 * @author futao
 * Created on 2019/9/19.
 */
@Slf4j
public class Server {
    public static void main(String[] args) {
        try {
            //设置检定的端口
            DatagramSocket datagramSocket = new DatagramSocket(8888);
            byte[] data = new byte[24];
            //构造数据包用于接收客户端发送的信息
            DatagramPacket p = new DatagramPacket(data, data.length);
            log.info("*****开始监听客户端的请求*****");
            //开始监听
            datagramSocket.receive(p);
            log.info("接收到消息[{}]", new String(data, StandardCharsets.UTF_8));

            byte[] toSend = "欢迎使用本系统".getBytes(StandardCharsets.UTF_8);
            //构造响应数据包，此时需要指定发到的地址和端口
            DatagramPacket sendData = new DatagramPacket(toSend, toSend.length, p.getAddress(), p.getPort());
            //发送响应
            datagramSocket.send(sendData);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
