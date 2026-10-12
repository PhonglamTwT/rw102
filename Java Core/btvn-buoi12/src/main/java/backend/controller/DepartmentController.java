package backend.controller;

import backend.service.IDepartmentService;
import backend.service.impl.DepartmentServiceImpl;
import entity.Department;

import java.util.List;

public class DepartmentController {
    private IDepartmentService departmentService;

    public DepartmentController() {
        this.departmentService = new DepartmentServiceImpl();
    }

    public List<Department> findAll() {
        return departmentService.findAll();
    }

    public List<Department> findByName(String name) {
        return departmentService.findByName(name);
    }

    public boolean create(String name) {
        return departmentService.create(name);
    }

    public boolean update(int id, String newName) {
        return departmentService.update(id, newName);
    }

    public boolean deleteById(int id) {
        return departmentService.deleteById(id);
    }
}