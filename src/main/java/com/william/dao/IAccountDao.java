package com.william.dao;

import com.william.domain.Account;

import java.util.List;

/**
 * 账户的 持久层
 */
public interface IAccountDao {

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
     * 根据名称查询账户信息
     * @param accountName
     */
    Account findByName(String accountName);

}
