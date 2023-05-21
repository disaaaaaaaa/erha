package com.itheima.dao;
import com.itheima.domain.Account;

public interface accountMapper {
    public Account findAccountByEmail(String email);
    public int addAccount(Account account);
}