package com.itheima.service;

import com.itheima.domain.Account;

public interface AccountService {
    public Account findAccountByEmail(String email);
    public int addAccount(Account account);

}