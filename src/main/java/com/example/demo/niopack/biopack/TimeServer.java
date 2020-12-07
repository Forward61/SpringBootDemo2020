package com.example.demo.niopack.biopack;

import org.springframework.expression.spel.ast.NullLiteral;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.OffsetDateTime;
import java.util.stream.IntStream;

/**
 * @Author freedom
 * @Description
 * @Date $ 2020/12/7 22:01
 */
public class TimeServer {
    public static void main(String[] args) throws IOException {
        int port = 8081;
        if (args != null && args.length > 0){
            port = Integer.valueOf(args[0]);

        }
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("The time 服务 is start in port端口：" +port  );
            Socket socket = null;
            while (true){
                socket = serverSocket.accept();
                new Thread(new TimeServerHandle(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (serverSocket != null){
                System.out.println("The time server close服务关闭");
                serverSocket.close();
                serverSocket = null;
            }
        }
    }
}
