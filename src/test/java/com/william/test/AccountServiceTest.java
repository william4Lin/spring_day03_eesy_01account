package com.william.test;

import com.william.domain.Account;
import com.william.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.swing.*;
import java.util.List;

/**
 * 使用Junit测试我们的配置
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountServiceTest {

    @Autowired
    @Qualifier("proxyAccountService")
    private IAccountService accountService;

    /*
    @Test
    public void testFindAll() {
        List<Account> accounts = accountService.findAllAccount();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindById() {
        Account account = accountService.findById(1);
        System.out.println(account);
    }

    @Test
    public void testSave() {
        Account account = new Account();
        account.setId(4);
        account.setName("林作亮");
        account.setMoney(10000f);
        accountService.saveAccount(account);
        //System.out.println(account);
    }

    @Test
    public void testUpdate() {
        Account account = accountService.findById(4);
        account.setMoney(222222f);
        accountService.updateAccount(account);
    }

    @Test
    public void testDelete() {
        accountService.deteleAccount(4);
    }*/

    @Test
    public void testTransfer(){
        accountService.tranferMoney("aaa","bbb",100f);
    }
}
