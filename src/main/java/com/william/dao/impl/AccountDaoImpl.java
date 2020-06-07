package com.william.dao.impl;

import com.william.dao.IAccountDao;
import com.william.domain.Account;
import com.william.utils.ConnectionUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * 账户持久层的实现类
 */
public class AccountDaoImpl implements IAccountDao {

    private QueryRunner runner;
    private ConnectionUtil connectionUtil;

    public void setConnectionUtil(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    public List<Account> findAllAccount() {

        try {
            return runner.query(connectionUtil.getThreadConnection(),"select * from account",new BeanListHandler<Account>(Account.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Account findById(Integer id) {
        try {
            return runner.query(connectionUtil.getThreadConnection(),"select * from account where id = ?",new BeanHandler<Account>(Account.class),id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveAccount(Account account) {
        try {
            runner.update(connectionUtil.getThreadConnection(),"insert into account(id,name,money) values (?,?,?)",account.getId(),account.getName(),account.getMoney());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAccount(Account account) {
        try {
            runner.update(connectionUtil.getThreadConnection(),"update account set name=?,money=? where id=?",account.getName(),account.getMoney(),account.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deteleAccount(Integer id) {
        try {
            runner.update(connectionUtil.getThreadConnection(),"delete from account where id=?",id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Account findByName(String accountName) {
        try {
            List<Account> accounts = runner.query(connectionUtil.getThreadConnection(),"select * from account where name = ?",new BeanListHandler<Account>(Account.class),accountName);
            if(accounts == null || accounts.size() ==0){
                return null;
            }else if(accounts.size() > 1){
                throw new RuntimeException("账户不唯一");
            }else{
                return accounts.get(0);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
