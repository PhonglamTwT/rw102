package backend.controller;

import backend.service.IAccountService;
import backend.service.impl.AccountServiceImpl;
import entity.Account;

import java.util.List;

public class AccountController {
    private IAccountService accountService;

    public AccountController() {
        accountService = new AccountServiceImpl();
    }

    public List<Account> findAll() {
        return accountService.findAll();
    }

    public List<Account> findByUsername(String username) {
        return accountService.findByUsername(username);
    }

    public boolean create(Account account) {
        return accountService.create(account);
    }

    public boolean deleteById(int id) {
        return accountService.deleteById(id);
    }

    public boolean updateUsernameById(int id, String newUsername) {
        return accountService.updateUsernameById(id, newUsername);
    }

    public boolean existsById(int id) {
        return accountService.existsById(id);
    }

    public boolean existsByUsername(String username) {
        return accountService.existsByUsername(username);
    }

    public boolean existsByUsernameAndIdNot(String username, int id) {
        return accountService.existsByUsernameAndIdNot(username, id);
    }

    public boolean existsByEmail(String email) {
        return accountService.existsByEmail(email);
    }

    public String importCSV(String path) {
        return accountService.importCSV(path);
    }
}