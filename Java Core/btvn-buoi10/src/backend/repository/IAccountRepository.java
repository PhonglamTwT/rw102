package backend.repository;

import entity.Account;
import entity.Department;
import entity.Position;

import java.util.List;

public interface IAccountRepository {
    List<Account> findAll();
    List<Account> findByUsername(String username);
    boolean create(String email, String username, String fullName, int depId, int posId);
    boolean deleteByUsername(String username);
    boolean updateFullNameByUsername(String username, String newFullName);
    List<Department> findAllDepartments();
    List<Position> findAllPositions();
}