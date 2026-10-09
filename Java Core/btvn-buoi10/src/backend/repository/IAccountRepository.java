package backend.repository;

import entity.Account;
import entity.Department;
import entity.Position;

import java.util.List;

public interface IAccountRepository {
    List<Account> findAll();
    List<Account> findByUsername(String username);
    boolean create(String email, String username, String fullName, int depId, int posId);
    boolean deleteById(int id);
    boolean updateUsernameById(int id, String newUsername);
    boolean existsById(int id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}