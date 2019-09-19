package com.futao.socket.udp;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 * 基于UDP的Socket通信-客户端
 *
 * @author futao
 * Created on 2019/9/19.
 */
@Slf4j
public class Client {
    public static void main(String[] args) {
        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            byte[] data = "你好，我是客户端12312312".getBytes(StandardCharsets.UTF_8);
            //构造数据包，指定地址与端口
            DatagramPacket datagramPacket = new DatagramPacket(data, data.length, InetAddress.getByName("127.0.0.1"), 8888);
            //发送数据
            datagramSocket.send(datagramPacket);

            byte[] bytes = new byte[1024];
            //构造数据包，用于接收数据
            DatagramPacket p = new DatagramPacket(bytes, bytes.length);
            //接收数据
            datagramSocket.receive(p);
            log.info("接收到服务器的响应[{}]", new String(bytes, StandardCharsets.UTF_8));
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
