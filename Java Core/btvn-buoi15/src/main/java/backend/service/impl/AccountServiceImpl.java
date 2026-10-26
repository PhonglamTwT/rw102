package backend.service.impl;

import backend.common.StringCommon;
import backend.repository.IAccountRepository;
import backend.repository.IDepartmentRepository;
import backend.repository.IPositionRepository;
import backend.repository.impl.AccountRepositoryImpl;
import backend.repository.impl.DepartmentRepositoryImpl;
import backend.repository.impl.PositionRepositoryImpl;
import backend.service.IAccountService;
import backend.service.ImportCSV;
import context.AccountContext;
import entity.Account;
import entity.Department;
import entity.Position;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AccountServiceImpl implements IAccountService, ImportCSV<Account, AccountContext> {

    private IAccountRepository accountRepository;
    private IDepartmentRepository departmentRepository;
    private IPositionRepository positionRepository;

    public AccountServiceImpl() {
        this.accountRepository = new AccountRepositoryImpl();
        this.departmentRepository = new DepartmentRepositoryImpl();
        this.positionRepository = new PositionRepositoryImpl();
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public boolean create(Account account) {
        return accountRepository.create(account);
    }

    @Override
    public boolean updateUsernameById(int id, String username) {
        return accountRepository.updateUsernameById(id, username);
    }

    @Override
    public boolean deleteById(int id) {
        return accountRepository.deleteById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return accountRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByUsernameAndIdNot(String username, int id) {
        return accountRepository.existsByUsernameAndIdNot(username, id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }

    @Override
    public boolean existsById(int id) {
        return accountRepository.existsById(id);
    }

    @Override
    public void saveAll(List<Account> entities) {
        accountRepository.createAll(entities);
    }

    @Override
    public void exportFile(String header, List<String> dataErrors, String pathErr) {
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

    @Override
    public void validation(String line, List<Account> entities, List<String> dataErrors, AccountContext context) {
        if (line == null || line.trim().isEmpty()) {
            return;
        }

        List<String> errors = new ArrayList<>();
        String[] arrs = line.split(",", -1);
        if (arrs.length < 5) {
            dataErrors.add(line + ",Dữ liệu dòng không đủ cột cơ bản");
            return;
        }

        String email = arrs[0].trim();
        String username = arrs[1].trim();
        String fullName = arrs[2].trim();
        String depIdStr = arrs[3].trim();
        String posIdStr = arrs[4].trim();

        Map<String, Account> mapByEmail = context.getMapByEmail();
        Map<String, Account> mapByUsername = context.getMapByUsername();
        Map<Integer, Department> mapByDepID = context.getMapByDepID();
        Map<Integer, Position> mapByPosID = context.getMapByPosID();

        if (email.isEmpty() || email.length() < 5 || email.length() > 50) {
            errors.add("Email phải từ 5-50 kí tự");
        }
        if (!email.matches(StringCommon.EMAIL_REGEX)) {
            errors.add("Email ko đúng định dạng");
        }
        if (mapByEmail.get(email) != null) {
            errors.add("Email đã tồn tại");
        }

        if (username.isEmpty() || username.length() < 5 || username.length() > 50) {
            errors.add("Username phải từ 5-50 kí tự");
        }
        if (!username.matches(StringCommon.DEPARTMENT_NAME_REGEX)) {
            errors.add("Username chỉ được chứa số và chữ");
        }
        if (mapByUsername.get(username) != null) {
            errors.add("Username đã tồn tại");
        }

        if (fullName.isEmpty() || fullName.length() < 5 || fullName.length() > 50) {
            errors.add("Fullname phải từ 5-50 kí tự");
        }
        if (!fullName.matches(StringCommon.DEPARTMENT_NAME_REGEX)) {
            errors.add("Fullname chỉ được chứa số và chữ");
        }

        Department department = null;
        if (!depIdStr.matches(StringCommon.NUMBER_REGEX)) {
            errors.add("DepartmentID không đúng định dạng");
        } else {
            department = mapByDepID.get(Integer.parseInt(depIdStr));
            if (department == null) {
                errors.add("DepartmentID không tồn tại");
            }
        }

        Position position = null;
        if (!posIdStr.matches(StringCommon.NUMBER_REGEX)) {
            errors.add("PositionID không đúng định dạng");
        } else {
            position = mapByPosID.get(Integer.parseInt(posIdStr));
            if (position == null) {
                errors.add("PositionID không tồn tại");
            }
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
            Account acc = new Account(0, email, username, fullName, department, position, createDate);
            entities.add(acc);

            mapByEmail.put(email, acc);
            mapByUsername.put(username, acc);
        } else {
            dataErrors.add(line + "," + String.join("-", errors));
        }
    }

    @Override
    public String importCSV(String path) {
        List<Account> accounts = accountRepository.findAll();
        Map<String, Account> mapByUsername = accounts.stream()
                .collect(Collectors.toMap(Account::getUsername, acc -> acc, (e, r) -> e));
        Map<String, Account> mapByEmail = accounts.stream()
                .collect(Collectors.toMap(Account::getEmail, acc -> acc, (e, r) -> e));

        List<Department> departments = departmentRepository.findAll();
        Map<Integer, Department> mapByDepId = departments.stream()
                .collect(Collectors.toMap(Department::getDepartmentId, dep -> dep, (e, r) -> e));

        List<Position> positions = positionRepository.findAll();
        Map<Integer, Position> mapByPosId = positions.stream()
                .collect(Collectors.toMap(Position::getPositionId, pos -> pos, (e, r) -> e));

        AccountContext context = new AccountContext(mapByEmail, mapByUsername, mapByDepId, mapByPosId);
        String pathErr = "C:\\Users\\welcome\\OneDrive\\Desktop\\rw102\\Java Core\\csv\\error_account.csv";

        return importCSV(path, pathErr, context);
    }
}