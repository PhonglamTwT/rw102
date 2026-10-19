package backend.service.impl;

import backend.repository.IAccountRepository;
import backend.repository.IDepartmentRepository;
import backend.repository.IPositionRepository;
import backend.repository.impl.AccountRepositoryImpl;
import backend.service.IAccountService;
import entity.Account;
import entity.Department;
import entity.Position;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public boolean existsByUsernameAndIdNot(String username, int id) {
        return repository.existsByUsernameAndIdNot(username, id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public String importCSV(String path) {
        File file = new File(path);
        if (!path.endsWith(".csv") || !file.exists()) {
            return "File ko đúng định dạng hoặc ko tồn tại!";
        }

        String header = null;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            List<Account> accounts = new ArrayList<>();
            header = br.readLine();

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] arrs = line.split(",");

                String email = arrs[0].trim();
                String username = arrs[1].trim();
                String fullName = arrs[2].trim();
                int depId = Integer.parseInt(arrs[3].trim());
                int posId = Integer.parseInt(arrs[4].trim());

                LocalDate createDate = null;
                if (arrs.length >= 6 && !arrs[5].trim().isEmpty()) {
                    createDate = LocalDate.parse(arrs[5].trim());
                } else {
                    createDate = LocalDate.now();
                }

                Department department = new Department(depId, null);
                Position position = new Position(posId, null);

                Account account = new Account(email, username, fullName, department, position, createDate);
                accounts.add(account);
            }

            repository.createAll(accounts);
            return "Import thành công!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Import thất bại!";
        }
    }
}