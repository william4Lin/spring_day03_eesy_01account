package com.william.service;

import com.william.domain.Account;

import java.util.List;

/**
 * 账户的业务层
 */
public interface IAccountService {

    /**
     * 查询所有
     * @return
     */
    List<Account> findAllAccount();

    /**
     * 通过id查询一条数据
     * @return
     */
    Account findById(Integer id);

    /**
     * 保存
     * @param account
     */
    void saveAccount(Account account);

    /**
     * 更新账户信息
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 通过id删除一条数据
     * @param id
     */
    void deteleAccount(Integer id);


    /**
     * 转账
     * @param sourceName 转出账户名称
     * @param targetName 转入账户名称
     * @param transMoney 转账金额
     */
    void tranferMoney(String sourceName,String targetName,Float transMoney);

}
