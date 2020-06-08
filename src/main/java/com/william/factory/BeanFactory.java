package com.william.factory;

import com.william.domain.Account;
import com.william.service.IAccountService;
import com.william.utils.TranscationMangaer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用于创建Service的代理对象的工厂
 */
public class BeanFactory {

    private IAccountService accountService ;

    private TranscationMangaer transcationMangaer;

    public void setTranscationMangaer(TranscationMangaer transcationMangaer) {
        this.transcationMangaer = transcationMangaer;
    }

    public void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 获取service的代理对象
     * @return
     */
    public IAccountService getAccountService() {
        IAccountService proxyAccountService = (IAccountService)Proxy.newProxyInstance(accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(), new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object returnValue = null;
                try{
                    //1.开启事务
                    transcationMangaer.beginTranscation();
                    //2.执行操作
                    returnValue = method.invoke(accountService,args);
                    //3.提交事务
                    transcationMangaer.commit();
                    //4.返回结果
                    return returnValue;
                }catch (Exception e){
                    //5.回滚事务
                    transcationMangaer.rollback();
                    throw new RuntimeException(e);
                }finally {
                    //6.释放连接
                    transcationMangaer.release();
                }
            }
        });
        return proxyAccountService;
    }
}
