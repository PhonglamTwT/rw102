package backend.service.impl;

import backend.repository.IAccountRepository;
import backend.repository.IDepartmentRepository;
import backend.repository.IPositionRepository;
import backend.repository.impl.AccountRepositoryImpl;
import backend.repository.impl.DepartmentRepositoryImpl;
import backend.repository.impl.PositionRepositoryImpl;
import backend.service.IAccountService;
import entity.Account;
import entity.Department;
import entity.Position;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountServiceImpl implements IAccountService {
    private IAccountRepository repository;
    private IDepartmentRepository departmentRepository;
    private IPositionRepository positionRepository;

    public AccountServiceImpl() {
        this.repository = new AccountRepositoryImpl();
        this.departmentRepository = new DepartmentRepositoryImpl();
        this.positionRepository = new PositionRepositoryImpl();
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
        if (!path.endsWith(".csv")) {
            return "File ko đúng định dạng !";
        }
        if (!file.exists()) {
            return "File ko tồn tại !";
        }

        String header = null;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            List<Account> accounts = new ArrayList<>();
            header = br.readLine();

            List<String> dataErrors = new ArrayList<>();

            List<Account> existingAccounts = repository.findAll();
            Set<String> existingEmails = existingAccounts.stream().map(Account::getEmail).collect(Collectors.toSet());
            Set<String> existingUsernames = existingAccounts.stream().map(Account::getUsername).collect(Collectors.toSet());

            Set<Integer> validDepIds = departmentRepository.findAll().stream()
                    .map(Department::getDepartmentId)
                    .collect(Collectors.toSet());
            Set<Integer> validPosIds = positionRepository.findAll().stream()
                    .map(Position::getPositionId)
                    .collect(Collectors.toSet());

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                List<String> errors = new ArrayList<>();
                String[] arrs = line.split(",");

                if (arrs.length < 5) {
                    dataErrors.add(line + ",Dữ liệu dòng không đủ cột cơ bản");
                    continue;
                }

                String email = arrs[0].trim();
                String username = arrs[1].trim();
                String fullName = arrs[2].trim();
                int depId = 0;
                int posId = 0;

                try {
                    depId = Integer.parseInt(arrs[3].trim());
                    posId = Integer.parseInt(arrs[4].trim());
                } catch (NumberFormatException e) {
                    errors.add("Department ID hoặc Position ID phải là số nguyên");
                }

                String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
                if (email.isEmpty() || email.length() < 5 || email.length() > 50 || !email.matches(emailRegex)) {
                    errors.add("Email không hợp lệ (5-50 ký tự, đúng định dạng)");
                } else if (existingEmails.contains(email)) {
                    errors.add("Email đã tồn tại");
                }

                if (username.isEmpty() || username.length() < 5 || username.length() > 50) {
                    errors.add("Username phải từ 5-50 ký tự");
                } else if (existingUsernames.contains(username)) {
                    errors.add("Username đã tồn tại");
                }

                if (fullName.isEmpty() || fullName.length() < 5 || fullName.length() > 50) {
                    errors.add("Full Name phải từ 5-50 ký tự");
                }

                if (!validDepIds.contains(depId)) {
                    errors.add("Department ID " + depId + " không tồn tại");
                }
                if (!validPosIds.contains(posId)) {
                    errors.add("Position ID " + posId + " không tồn tại");
                }

                LocalDate createDate = null;
                if (arrs.length >= 6 && !arrs[5].trim().isEmpty()) {
                    try {
                        createDate = LocalDate.parse(arrs[5].trim());
                    } catch (Exception e) {
                        errors.add("Ngày tạo không đúng định dạng (YYYY-MM-DD)");
                    }
                } else {
                    createDate = LocalDate.now();
                }

                if (errors.isEmpty()) {
                    Department department = new Department(depId, null);
                    Position position = new Position(posId, null);
                    Account account = new Account(email, username, fullName, department, position, createDate);
                    accounts.add(account);

                    existingEmails.add(email);
                    existingUsernames.add(username);
                } else {
                    String rowError = line + "," + String.join("-", errors);
                    dataErrors.add(rowError);
                }
            }

            if (!accounts.isEmpty()) {
                repository.createAll(accounts);
            }

            if (!dataErrors.isEmpty()) {
                String pathErr = "C:\\Users\\welcome\\OneDrive\\Desktop\\rw102\\Java Core\\csv\\error_account.csv";
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathErr))) {
                    bw.write(header + ",error_messages");
                    bw.newLine();
                    for (String error : dataErrors) {
                        bw.write(error);
                        bw.newLine();
                    }
                    bw.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return String.format("Import thành công %d, import thất bại %d, %s",
                    accounts.size(), dataErrors.size(), !dataErrors.isEmpty() ? "Đã xuất ra file lỗi error_account.csv" : "");

        } catch (Exception e) {
            e.printStackTrace();
            return "Import thất bại";
        }
    }
}