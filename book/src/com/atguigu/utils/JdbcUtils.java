package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
        private static DruidDataSource dataSource;
        private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();


        static{
            try {
                Properties properties=new Properties();
                InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
                properties.load(inputStream);
                 dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    public static Connection getConnection(){
     Connection conn = conns.get();
     if(conn == null){
         try {
             conn = dataSource.getConnection();//从数据库连接池中获取连接
             conns.set(conn);//把连接保存到threadlocal中供后面的jdbc操作使用
             conn.setAutoCommit(false);
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }
        return conn;
    }

    /**
     * 提交和关闭连接
     */
    public static void comitAndClose(){
            Connection connection = conns.get();
            if(connection != null){
                try {
                    connection.commit();//提交事务
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {

                    try {
                        connection.close();//关闭连接
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            conns.remove();
    }

    public static void rollbackAndClose(){
        Connection connection = conns.get();
        if(connection != null){
            try {
                connection.rollback();//回滚事务
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                try {
                    connection.close();//关闭连接
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove();
    }

//    public static  void close(Connection conn){
//        if(conn != null){
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }


}
