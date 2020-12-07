package com.example.demo.niopack.biopack;

import org.springframework.expression.spel.ast.NullLiteral;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * @Author freedom
 * @Description
 * @Date $ 2020/12/7 22:06
 */
public class TimeServerHandle implements Runnable {
    private Socket socket;
    public TimeServerHandle(Socket socket) {
        this.socket =socket;

    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(),true);
            String currentTime = null;
            String body = null;
            while (true){
                body = in.readLine();
                if (null == body){
                    break;
                }

                System.out.println("The timeServer receive order服务接收：" + body);
                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?
                        new Date(System.currentTimeMillis()).toString():"BAD ORDER";
                System.out.println("currentTime当前时间:" + currentTime);
            }
        }catch (Exception ex){
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (out != null){
                out.close();
                out = null;
            }
            if (this.socket != null){
                try {
                    this.socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.socket = null;
            }
        }
    }
}
