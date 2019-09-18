package com.futao.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author futao
 * Created on 2019/9/18.
 */
public class Server {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 10, TimeUnit.DAYS, new LinkedBlockingDeque<>(1024), r -> new Thread(r, "thread-socket-" + atomicInteger.getAndAdd(1)));
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("客户端等待连接.....");
            while (true) {
                Socket socket = serverSocket.accept();
                executor.execute(() -> {
                    try {
                        InputStream inputStream = socket.getInputStream();
                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                        BufferedReader reader = new BufferedReader(inputStreamReader);
                        String s;
                        while ((s = reader.readLine()) != null) {
                            System.out.println(s);
                        }
                        socket.shutdownInput();
                        reader.close();
                        inputStreamReader.close();
                        inputStream.close();
                        socket.close();
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
