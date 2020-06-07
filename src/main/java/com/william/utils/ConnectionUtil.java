package com.william.utils;


import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 连接工具类，他用于从数据源中获取一个连接，并且实现与线程的绑定
 */
public class ConnectionUtil {

    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    public DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取当前线程上的连接
     * @return
     */
    public Connection getThreadConnection(){

        //从ThreadLocal上获取一个连接
        Connection conn = tl.get();
        try {
            //判断当前线程上是否有连接
            if (conn == null) {
                //从数据源中获取一个连接
                conn = dataSource.getConnection();
                tl.set(conn);
            }
            //返回当前线程上的连接
            return conn;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 把连接和线程解绑
     */
    public void removeConnection(){
        tl.remove();
    }

}
