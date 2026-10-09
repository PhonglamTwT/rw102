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
    public boolean deleteById(int id) {
        return repository.deleteById(id);
    }

    @Override
    public boolean updateUsernameById(int id, String newUsername) {
        return repository.updateUsernameById(id, newUsername);
    }

    @Override
    public boolean existsById(int id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}