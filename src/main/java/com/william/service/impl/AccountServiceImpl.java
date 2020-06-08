package com.william.service.impl;

import com.william.dao.IAccountDao;
import com.william.domain.Account;
import com.william.service.IAccountService;
import com.william.utils.TranscationMangaer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 账户业务层的实现类
 */
public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;


    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();

    }

    public Account findById(Integer id) {

       return accountDao.findById(id);

    }

    public void saveAccount(Account account) {
            accountDao.saveAccount(account);
    }

    public void updateAccount(Account account) {
            accountDao.updateAccount(account);
    }

    public void deteleAccount(Integer id) {
            accountDao.deteleAccount(id);
    }

    public void tranferMoney(String sourceName, String targetName, Float transMoney) {

            //1.根据名称查询转出账户
            Account sourceAccount = accountDao.findByName(sourceName);
            //2.根据名称查询转入账户
            Account targetAccount = accountDao.findByName(targetName);
            //3.转出账户减钱
            sourceAccount.setMoney(sourceAccount.getMoney()-transMoney);
            //4.转入账户加钱
            targetAccount.setMoney(targetAccount.getMoney()+transMoney);
            //5.更新转出账户
            accountDao.updateAccount(sourceAccount);
            //int i = 1/0;
            //6.更新转入账户
            accountDao.updateAccount(targetAccount);

    }
}
