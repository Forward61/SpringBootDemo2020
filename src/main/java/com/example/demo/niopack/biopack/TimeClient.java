package com.example.demo.niopack.biopack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.OffsetDateTime;

/**
 * @Author freedom
 * @Description
 * @Date $ 2020/12/7 22:16
 */
public class TimeClient {
    public static void main(String[] args) {
        int port = 8081;

        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket("127.0.0.1" ,port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            out.println("QUERY TIME ORDER");
            System.out.println("Send order 2 server succeed 发送成功");
            String resp = in.readLine();
            System.out.println("现在是：now is :" + resp);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if (out!= null){
                out.close();
                out = null;
            }
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}
