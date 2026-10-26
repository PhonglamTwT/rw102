package backend.repository;

import entity.Account;

import java.util.List;

public interface IAccountRepository {
    List<Account> findAll();
    List<Account> findByUsername(String username);
    boolean create(Account account);
    boolean deleteById(int id);
    boolean updateUsernameById(int id, String newUsername);
    boolean existsById(int id);
    boolean existsByUsername(String username);
    boolean existsByUsernameAndIdNot(String username, int id);
    boolean existsByEmail(String email);
    void createAll(List<Account> accounts);
}