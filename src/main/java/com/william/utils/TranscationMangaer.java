package com.william.utils;


import java.sql.SQLException;

/**
 * 和事务相关的工具类，  包含了：开启事务，提交事务，回滚事务，释放连接
 */
public class TranscationMangaer {

    private ConnectionUtil connectionUtil;

    public void setConnectionUtil(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    /**
     * 开启事务
     */
    public void beginTranscation(){
        try {
            connectionUtil.getThreadConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
    public void commit(){
        try {
            connectionUtil.getThreadConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
    public void rollback(){
        try {
            connectionUtil.getThreadConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放资源
     */
    public void release(){
        try {
            connectionUtil.getThreadConnection().close();
            connectionUtil.removeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
