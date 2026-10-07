package backend.service.impl;

import backend.repository.IAccountRepository;
import backend.repository.impl.AccountRepositoryImpl;
import backend.service.IAccountService;
import entity.Account;
import entity.Department;
import entity.Position;

import java.util.List;

public class AccountServiceImpl implements IAccountService {
    private IAccountRepository repository;

    public AccountServiceImpl() {
        repository = new AccountRepositoryImpl();
    }

    @Override
    public List<Account> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Account> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public boolean create(String email, String username, String fullName, int depId, int posId) {
        return repository.create(email, username, fullName, depId, posId);
    }

    @Override
    public boolean deleteByUsername(String username) {
        return repository.deleteByUsername(username);
    }

    @Override
    public boolean updateFullNameByUsername(String username, String newFullName) {
        return repository.updateFullNameByUsername(username, newFullName);
    }

    @Override
    public List<Department> findAllDepartments() {
        return repository.findAllDepartments();
    }

    @Override
    public List<Position> findAllPositions() {
        return repository.findAllPositions();
    }
}