package com.william.service.impl;

import com.william.dao.IAccountDao;
import com.william.domain.Account;
import com.william.service.IAccountService;
import com.william.utils.TranscationMangaer;

import java.util.List;


/**
 * 账户业务层的实现类
 */
public class AccountServiceImpl_old implements IAccountService {

    private IAccountDao accountDao;
    private TranscationMangaer transcationMangaer;

    public void setTranscationMangaer(TranscationMangaer transcationMangaer) {
        this.transcationMangaer = transcationMangaer;
    }

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> findAllAccount() {
        try{
            //1.开启事务
            transcationMangaer.beginTranscation();
            //2.执行操作
            List<Account> accounts = accountDao.findAllAccount();
            //3.提交事务
            transcationMangaer.commit();
            //4.返回结果
            return accounts;
        }catch (Exception e){
            //5.回滚事务
            transcationMangaer.rollback();
            throw new RuntimeException(e);
        }finally {
            //6.释放连接
            transcationMangaer.release();
        }
    }

    public Account findById(Integer id) {

        try{
            //1.开启事务
            transcationMangaer.beginTranscation();
            //2.执行操作
            Account account = accountDao.findById(id);
            //3.提交事务
            transcationMangaer.commit();
            //4.返回结果
            return account;
        }catch (Exception e){
            //5.回滚事务
            transcationMangaer.rollback();
            throw new RuntimeException(e);
        }finally {
            //6.释放连接
            transcationMangaer.release();
        }
    }

    public void saveAccount(Account account) {

        try{
            //1.开启事务
            transcationMangaer.beginTranscation();
            //2.执行操作
            accountDao.saveAccount(account);
            //3.提交事务
            transcationMangaer.commit();
        }catch (Exception e){
            //4.回滚事务
            transcationMangaer.rollback();
            throw new RuntimeException(e);
        }finally {
            //5.释放连接
            transcationMangaer.release();
        }
    }

    public void updateAccount(Account account) {
        try{
            //1.开启事务
            transcationMangaer.beginTranscation();
            //2.执行操作
            accountDao.updateAccount(account);
            //3.提交事务
            transcationMangaer.commit();
        }catch (Exception e){
            //4.回滚事务
            transcationMangaer.rollback();
            throw new RuntimeException(e);
        }finally {
            //5.释放连接
            transcationMangaer.release();
        }
    }

    public void deteleAccount(Integer id) {
        try{
            //1.开启事务
            transcationMangaer.beginTranscation();
            //2.执行操作
            accountDao.deteleAccount(id);
            //3.提交事务
            transcationMangaer.commit();
        }catch (Exception e){
            //4.回滚事务
            transcationMangaer.rollback();
            throw new RuntimeException(e);
        }finally {
            //5.释放连接
            transcationMangaer.release();
        }

    }

    public void tranferMoney(String sourceName, String targetName, Float transMoney) {

        try{
            //1.开启事务
            transcationMangaer.beginTranscation();
            //2.执行操作
            //2.1.根据名称查询转出账户
            Account sourceAccount = accountDao.findByName(sourceName);
            //2.2.根据名称查询转入账户
            Account targetAccount = accountDao.findByName(targetName);
            //2.3.转出账户减钱
            sourceAccount.setMoney(sourceAccount.getMoney()-transMoney);
            //2.4.转入账户加钱
            targetAccount.setMoney(targetAccount.getMoney()+transMoney);
            //2.5.更新转出账户
            accountDao.updateAccount(sourceAccount);
            int i = 1/0;
            //2.6.更新转入账户
            accountDao.updateAccount(targetAccount);
            //3.提交事务
            transcationMangaer.commit();
        }catch (Exception e){
            //4.回滚事务
            transcationMangaer.rollback();
            throw new RuntimeException(e);
        }finally {
            //5.释放连接
            transcationMangaer.release();
        }




    }
}
