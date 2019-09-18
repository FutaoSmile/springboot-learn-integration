package com.futao.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author futao
 * Created on 2019/9/18.
 */
public class Client {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            try {
                Socket socket = new Socket("127.0.0.1", 8888);
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(String.format("我是【%s】号admin客户，我现在要登陆了", i).getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
                socket.shutdownOutput();

                outputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
