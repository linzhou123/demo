package com.example.demo.utils;

import com.example.demo.Dto.HexDto;
import com.example.demo.Enums.HexEnum;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Slf4j
public class HexUtil {

    private boolean flag = false;
    private Response response = null;
    private HexDto hexDto;
    private String realValue;

    public HexUtil(HexDto hexData) {
        this.hexDto = hexData;
    }

    public void getBackMsg(List<String> hexContent) throws IOException {
        //定义一个Socket对象
        Socket socket = null;
        socket = new Socket(hexDto.getHost(), hexDto.getPort());
        socket.setSoTimeout(5000);
        OutputStream os = socket.getOutputStream();
        InputStream s = socket.getInputStream();
        for (String h : hexContent) {
            byte[] bytes = hexStringToByteArray(h);
            os.write(bytes);
            os.flush();
            byte[] buf = new byte[1024];
            int len = 0;
//            System.out.println(byteArrayToHex2(buf));
            if ((len = s.read(buf)) != -1) {
                System.out.println(getDate() + "  服务器返回：  " + byteArrayToHex2(buf));
            }

        }
        os.close();
        s.close();
        socket.close();
    }

    public String getBackMsg(String hexContent) throws IOException {
        //定义一个Socket对象
        Socket socket = null;
        socket = new Socket(hexDto.getHost(), hexDto.getPort());
        socket.setSoTimeout(5000);
        OutputStream os = socket.getOutputStream();
        InputStream s = socket.getInputStream();
        byte[] bytes = hexStringToByteArray(hexContent);
        os.write(bytes);
        os.flush();
        byte[] buf = new byte[1024];
        int len = 0;
        System.out.println(byteArrayToHex2(buf));
        if ((len = s.read(buf)) != -1) {
            log.info("  服务器返回：  " + byteArrayToHex2(buf));
//                System.out.println(getDate() + "  服务器返回：  "+ byteArrayToHex2(buf));
        }
        os.close();
        s.close();
        socket.close();
        return byteArrayToHex2(buf);
    }
    public String getBackMsg1(String hexContent) throws IOException {
        //定义一个Socket对象
        Socket socket = null;
        socket = new Socket(hexDto.getHost(), hexDto.getPort());
//        socket.setSoTimeout(5000);
        OutputStream os = socket.getOutputStream();
        InputStream s = socket.getInputStream();
        byte[] bytes = hexStringToByteArray(hexContent);
        os.write(bytes);
        os.flush();
        byte[] buf = new byte[1024];
        int len = 0;
        System.out.println("1111111"+byteArrayToHex3(buf));
        while ((len = s.read(buf)) >0) {
            log.info("长度"+String.valueOf(len));
            log.info("  服务器返回：  " + hexStringToString(byteArrayToHex3(buf)));
//                System.out.println(getDate() + "  服务器返回：  "+ byteArrayToHex2(buf));
        }
        os.close();
        s.close();
        socket.close();
        return byteArrayToHex3(buf);
    }

    public static String getDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String result = format.format(date);
        return result;
    }

    /**
     * 获取接口中数据对比设备状态是否准确,写死数据
     */
    public boolean getHexAssert() {
        String url = null;
        if (hexDto.getHost().equals("14.18.73.163")) {
            url = "https://cloud.sendiag.cn/self_operator/device/page";
        } else {
            url = "http://" + hexDto.getHost().concat(":8884/self_operator/device/page");
        }
        log.info("url:" + url);
        Map<String, Object> params = new HashMap<>();
        params.put("code", hexDto.getDeviceName());
        params.put("pageNumber", 1);
        params.put("pageSize", 10);
        params.put("deviceType", hexDto.getDeviceType());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            response = given().headers(getCookie()).params(params).when().get(url);
            String data = response.getBody().prettyPrint();
            log.info("获取测试数据结果:" + data);
            log.info("JsonPath解析:" + JsonPath.parse(data).read("$.data.list.length()"));
            switch (HexEnum.valueOfType(hexDto.getDataType())) {
                case AlarmData:
                    realValue = JsonPath.parse(data).read("$.data.list[0].alarmStatus").toString();
                    break;
                case Fault:
                    realValue = JsonPath.parse(data).read("$.data.list[0].faultStatus").toString();
                    break;
                case RealTimeData:
                    realValue = JsonPath.parse(data).read("$.data.list[0].onlineStatus").toString();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + hexDto.getDataType());
            }
        } catch (PathNotFoundException e) {
            log.error("JsonPath解析报错:", e);
            return flag = false;
        }catch (Exception e){
            log.error(e.getMessage());
            return flag = false;
        }

        log.info("realValue:" + realValue);
        if (realValue.equals("true")) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    public Map<String, Object> getCookie() {
        Map<String, Object> cookie = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/x-www-form-urlencoded");
        params.put("username", hexDto.getUserName());
        params.put("password", hexDto.getPassword());
        params.put("submit", "login");
        String url = null;
        if (hexDto.getHost().equals("14.18.73.163")) {
            url = "https://cloud.sendiag.cn/self_operator/login";
        } else {
            url = "http://" + hexDto.getHost().concat(":8884/self_operator/login");
        }
        log.info("url:" + url);
//        response= given().headers(headerMap).params(params).when().post(url);
        response = given().headers(headerMap).params(params).when().post(url);
        Headers headers = response.getHeaders();
        cookie.put("Cookie", headers.getValue("Set-Cookie"));
        log.info("Set-Cookie:" + cookie);
        return cookie;
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
        int index = result.indexOf("2323");
        String result1 = result.toString();
        result1 = result.substring(0, index + 4);
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
        int index = result.indexOf("2323");
        String result1 = result.toString();
        result1 = result.substring(0, index + 4);
//        System.out.println("msgLength:"+(index+4));
        return result1;
    }
    //将获取的bytes格式转换为16进制
    public static String byteArrayToHex3(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (int index = 0, len = bytes.length; index <= len - 1; index += 1) {
            String invalue1 = Integer.toHexString((bytes[index] >> 4) & 0xF);
            String intValue2 = Integer.toHexString(bytes[index] & 0xF);
            result.append(invalue1);
            result.append(intValue2);
        }
        String result1 = result.toString();
//        System.out.println("msgLength:"+(index+4));
        return result1;
    }

    /**
     * 16进制转换成为string类型字符串
     * @param s
     * @return
     */
    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "UTF-8");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

}