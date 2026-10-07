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

    public boolean deleteByUsername(String username) {
        return accountService.deleteByUsername(username);
    }

    public boolean updateFullNameByUsername(String username, String newFullName) {
        return accountService.updateFullNameByUsername(username, newFullName);
    }

    public List<Department> findAllDepartments() {
        return accountService.findAllDepartments();
    }

    public List<Position> findAllPositions() {
        return accountService.findAllPositions();
    }
}