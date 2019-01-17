package com.nxs.socket;

import java.io.*;
import java.net.Socket;

/**
 * @Description: 向服务器一端口发送数据
 * @Author: 刘亚楠
 * @Date: 2019/1/17 15:06
 * @Version: 1.0
 */
public class SendDataToServerPort {

    private static Socket socket = null;
    static {
        if(socket == null){
            try {
                socket = new Socket("192.168.33.151", 9999);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try{
            new TelnetWriter(socket.getOutputStream()).start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

//写线程
class TelnetWriter extends Thread{
    private PrintStream out;
    public TelnetWriter(OutputStream out){
        this.out = new PrintStream(out);
    }

    public void run(){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true){
                out.println(reader.readLine());
                Thread.sleep(1500);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

//读线程
class TelnetReader extends Thread{
    private InputStreamReader in;
    public  TelnetReader(InputStream in){
        this.in = new InputStreamReader(in);
    }

    public void run(){
        try {
            while (true){
                int ch = in.read();
                if(ch == -1){
                    System.exit(0);
                }
                System.out.println((char)ch);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}