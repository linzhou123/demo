package com.example.demo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class HexUtil extends Thread {

    //定义一个Socket对象
    Socket socket = null;

    public HexUtil(String host, int port) {
        try {
            //需要服务器的IP地址和端口号，才能获得正确的Socket对象
            socket = new Socket(host, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        //客户端一连接就可以写数据给服务器了
        new sendMessThread().start();
        super.run();
        try {
            // 读Sock里面的数据
            InputStream s = socket.getInputStream();
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = s.read(buf)) != -1) {
                System.out.println(getdate() + "  服务器返回：  "+byteArrayToHex2(buf));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //往Socket里面写数据，需要新开一个线程
    class sendMessThread extends Thread{
        @Override
        public void run() {
            super.run();
            //写操作
            Scanner scanner=null;
            OutputStream os= null;
            try {
                scanner=new Scanner(System.in);

                os= socket.getOutputStream();
                String in="";
                do {
                    in=scanner.next();
                    System.out.println(in);
                    byte[] bytes = hexStringToByteArray(in);
                    os.write(bytes);
                    os.flush();
                } while (!in.equals("bye"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            scanner.close();
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getdate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String result = format.format(date);
        return result;
    }


    /**
     * 16进制表示的字符串转换为字节数组
     *
     * @param hexString 16进制表示的字符串
     * @return byte[] 字节数组
     */
    public static byte[] hexStringToByteArray(String hexString) {
        hexString = hexString.replaceAll(" ", "");
        int len = hexString.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
            bytes[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character
                    .digit(hexString.charAt(i + 1), 16));
        }
        return bytes;
    }
    public static String byteArrayToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (int index = 0, len = bytes.length; index <= len - 1; index += 1) {
            int char1 = ((bytes[index] >> 4) & 0xF);
            char chara1 = Character.forDigit(char1, 16);
            int char2 = ((bytes[index]) & 0xF);
            char chara2 = Character.forDigit(char2, 16);
            result.append(chara1);
            result.append(chara2);
        }
        int index=result.indexOf("2323");
        String result1=result.toString();
        result1=result.substring(0,index+4);
        return result.toString();
    }
    //将获取的bytes格式转换为16进制
   public static String byteArrayToHex2(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (int index = 0, len = bytes.length; index <= len - 1; index += 1) {
            String invalue1 = Integer.toHexString((bytes[index] >> 4) & 0xF);
            String intValue2 = Integer.toHexString(bytes[index] & 0xF);
            result.append(invalue1);
            result.append(intValue2);
        }
        int index=result.indexOf("2323");
        String result1=result.toString();
        result1=result.substring(0,index+4);
        System.out.println("msgLength:"+(index+4));
        return result1;
    }

        //函数入口
    public static void main(String[] args) {
        //需要服务器的正确的IP地址和端口号
        HexUtil clientTest=new HexUtil("14.116.192.19", 7893);
        clientTest.start();
        clientTest.notify();
        byte[] S=hexStringToByteArray("8503C6FA9EA2058372EA266DF780E7E4");
        System.out.println(S);

    }
}