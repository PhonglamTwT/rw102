package backend.controller;

import backend.service.IAccountService;
import backend.service.impl.AccountServiceImpl;
import entity.Account;
import entity.Department;
import entity.Position;

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

    public boolean create(String email, String username, String fullName, int depId, int posId) {
        return accountService.create(email, username, fullName, depId, posId);
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

    public boolean existsByEmail(String email) {
        return accountService.existsByEmail(email);
    }
}