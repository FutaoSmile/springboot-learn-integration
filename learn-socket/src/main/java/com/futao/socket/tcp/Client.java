package com.futao.socket.tcp;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 基于TCP的Socket通信-客户端
 *
 * @author futao
 * Created on 2019/9/18.
 */
@Slf4j
public class Client {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            try {
                Socket socket = new Socket("127.0.0.1", 8888);
                OutputStream outputStream = socket.getOutputStream();
                String format = String.format("我是【%s】号admin客户，我现在要登陆了", i);
                log.info("客户端发送请求{}", format);
                outputStream.write(format.getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
                socket.shutdownOutput();

                //接收服务器的响应
                InputStream inputStream = socket.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String s;
                while ((s = bufferedReader.readLine()) != null) {
                    log.info("接收到响应{}", s);
                }
                socket.shutdownInput();

                //资源都放在最后关闭
                outputStream.close();
                bufferedReader.close();
                reader.close();
                inputStream.close();

                //断开socket
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
