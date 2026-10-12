package backend.repository;

import entity.Department;
import java.util.List;

public interface IDepartmentRepository {
    List<Department> findAll();
    List<Department> findByName(String name);
    boolean create(String name);
    boolean update(int id, String newName);
    boolean deleteById(int id);
}