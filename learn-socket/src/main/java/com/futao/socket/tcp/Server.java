package com.futao.socket.tcp;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基于TCP的Socket通信-服务器端
 *
 * @author futao
 * Created on 2019/9/18.
 */
@Slf4j
public class Server {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 10, TimeUnit.DAYS, new LinkedBlockingDeque<>(1024), r -> new Thread(r, "thread-socket-" + atomicInteger.getAndAdd(1)));
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            log.info("客户端等待连接.....");
            while (true) {
                Socket socket = serverSocket.accept();
                executor.execute(() -> {
                    try {
                        InputStream inputStream = socket.getInputStream();
                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                        BufferedReader reader = new BufferedReader(inputStreamReader);
                        String s;
                        while ((s = reader.readLine()) != null) {
                            log.info("服务器收到请求{}", s);
                        }
                        socket.shutdownInput();

                        //响应
                        OutputStream outputStream = socket.getOutputStream();
                        outputStream.write("欢迎使用本系统".getBytes(StandardCharsets.UTF_8));
                        outputStream.flush();
                        socket.shutdownOutput();

                        //资源都放在最后关闭
                        reader.close();
                        inputStreamReader.close();
                        inputStream.close();
                        outputStream.close();

                        //断开socket
                        socket.close();
                        Thread.sleep(3000L);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
