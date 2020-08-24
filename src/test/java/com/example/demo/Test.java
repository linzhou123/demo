package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Test {

        private static final String URL ="jdbc:mysql://localhost:3308/test?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";  //数据库地址
        private  static final String username="root";
        private  static final String password="123456";

        private Connection conn = null;
        private PreparedStatement pet =null;
        private ResultSet resultSet=null;
        public void mySQL(){
            try {
                conn = DriverManager.getConnection(URL,username,password);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        public List<String> getData (String sql ,String  targetName){
            List<String> result = new ArrayList<String>();
            try {
                pet=conn.prepareStatement(sql);
                resultSet = pet.executeQuery();
                while (resultSet.next()){
                    result.add(resultSet.getString(targetName));
                   System.out.println(result);
                }
                this.close();
            }catch (Exception e ){
                e.printStackTrace();
            }
            return result;
        }

        /* 关闭链接 */
        private void close() {
            try {
                this.conn.close();
                this.pet.close();
            } catch (Exception e) {
                System.out.println("关闭数据库连接失败！");
                e.printStackTrace();
            }
        }


    public static void main(String[] args) {
//        Test test = new Test();
//        test.mySQL();
//        String sql= "select  `mistake_name` from mistake ";
//        System.out.println(sql);
//        List<String> result = test.getData(sql,"mistake_name");
//        System.out.println(result.get(0));
//        ApiServiceImpl apiService = null;
//        Api api= apiService.findById(3);
//        List<Header> headerList= api.getRequestHeader();
//        System.out.println(headerList.getClass().toString());
//        String date =String.valueOf(new Date());
//        System.out.println(date);
    }
}
