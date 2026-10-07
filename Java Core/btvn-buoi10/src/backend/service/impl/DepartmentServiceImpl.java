package backend.service.impl;

import backend.repository.IDepartmentRepository;
import backend.repository.impl.DepartmentRepositoryImpl;
import backend.service.IDepartmentService;
import entity.Department;

import java.util.List;

public class DepartmentServiceImpl implements IDepartmentService {
    private IDepartmentRepository repository;

    public DepartmentServiceImpl() {
        this.repository = new DepartmentRepositoryImpl();
    }

    @Override
    public List<Department> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Department> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public boolean create(String name) {
        return repository.create(name);
    }

    @Override
    public boolean update(int id, String newName) {
        return repository.update(id, newName);
    }

    @Override
    public boolean deleteById(int id) {
        return repository.deleteById(id);
    }
}