DROP DATABASE IF EXISTS Testing_System_Assignment_6;
CREATE DATABASE Testing_System_Assignment_6;
USE Testing_System_Assignment_6;

-- Table 1:Department  
DROP TABLE IF EXISTS department;
CREATE TABLE department (
    department_id SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    department_name VARCHAR(50) NOT NULL UNIQUE
);

-- Table 2: Position  
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
    position_id TINYINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    position_name ENUM('Dev', 'Test', 'Scrum Master', 'PM') NOT NULL
);

-- Table 3: Account  
DROP TABLE IF EXISTS account;
CREATE TABLE account(
	account_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    user_name VARCHAR(50) NOT NULL UNIQUE,
    full_name VARCHAR(50) NOT NULL,
    gender VARCHAR(20),
    department_id SMALLINT UNSIGNED NULL,
    position_id TINYINT UNSIGNED NOT NULL,
    create_date DATE DEFAULT(CURRENT_DATE()),
    FOREIGN KEY(department_id) 
		REFERENCES department(department_id),
    FOREIGN KEY(position_id) 
		REFERENCES `position`(position_id)
);

-- Table 4: Group  
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`(
	group_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    group_name VARCHAR(100) NOT NULL,
    creator_id INT UNSIGNED NOT NULL,
    create_date DATE DEFAULT(CURRENT_DATE()),
    FOREIGN KEY(creator_id) 
		REFERENCES account(account_id) ON DELETE CASCADE
);

-- Table 5: GroupAccount  
DROP TABLE IF EXISTS group_account;
CREATE TABLE group_account(
	group_id INT UNSIGNED,
    account_id INT UNSIGNED,
    join_date DATE DEFAULT(CURRENT_DATE()),
    PRIMARY KEY(group_id, account_id),
    FOREIGN KEY(group_id) 
		REFERENCES `group`(group_id),
    FOREIGN KEY(account_id) 
		REFERENCES account(account_id) ON DELETE CASCADE
);

-- Table 6: TypeQuestion  
DROP TABLE IF EXISTS type_question;
CREATE TABLE type_question (
    type_id TINYINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    type_name ENUM('Essay', 'Multiple-Choice') NOT NULL
);

-- Table 7: CategoryQuestion  
DROP TABLE IF EXISTS category_question;
CREATE TABLE category_question (
    category_id SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(50) NOT NULL UNIQUE
);

-- Table 8: Question  
DROP TABLE IF EXISTS question;
CREATE TABLE question (
    question_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(1000) NOT NULL,
    category_id SMALLINT UNSIGNED NOT NULL,
    type_id TINYINT UNSIGNED NOT NULL,
    creator_id INT UNSIGNED NOT NULL,
    create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id)
        REFERENCES category_question (category_id),
    FOREIGN KEY (type_id)
        REFERENCES type_question (type_id),
    FOREIGN KEY (creator_id)
        REFERENCES account (account_id) ON DELETE CASCADE
);

-- Table 9: Answer  
DROP TABLE IF EXISTS answer;
CREATE TABLE answer (
    answer_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(1000) NOT NULL,
    question_id INT UNSIGNED NOT NULL,
    is_correct BIT DEFAULT 1,
    FOREIGN KEY (question_id)
        REFERENCES question (question_id) ON DELETE CASCADE
);

-- Table 10: Exam  
DROP TABLE IF EXISTS exam;
CREATE TABLE exam (
    exam_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(100) NOT NULL UNIQUE,
    title VARCHAR(50) NOT NULL,
    category_id SMALLINT UNSIGNED NOT NULL,
    duration TINYINT UNSIGNED NOT NULL,
    creator_id INT UNSIGNED NOT NULL,
    create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id)
        REFERENCES category_question (category_id),
    FOREIGN KEY (creator_id)
        REFERENCES account (account_id) ON DELETE CASCADE
);

-- Table 11: ExamQuestion  
DROP TABLE IF EXISTS exam_question;
CREATE TABLE exam_question (
    exam_id INT UNSIGNED NOT NULL,
    question_id INT UNSIGNED NOT NULL,
    PRIMARY KEY (exam_id , question_id),
    FOREIGN KEY (exam_id)
        REFERENCES exam (exam_id) ON DELETE CASCADE,
    FOREIGN KEY (question_id)
        REFERENCES question (question_id) ON DELETE CASCADE
);

-- Thêm dữ liệu
INSERT INTO department (department_name) VALUES
('Marketing'),
('Sale'),
('Bảo vệ'),
('Nhân sự'),
('Kỹ thuật'),
('Tài chính'),
('Phó giám đốc'),
('Giám đốc'),
('Thư kí'),
('Bán hàng'),
('Wait room');

INSERT INTO `position` (position_name) VALUES
('Dev'),
('Test'),
('Scrum Master'),
('PM');

-- TRUNCATE TABLE account;
INSERT INTO account (email, user_name, full_name, department_id, position_id, create_date) VALUES
('haidang29@gmail.com', 'dangblack',   'Nguyễn Hải Đăng', 5, 1, '2020-03-05'),
('account1@gmail.com', 'quangnguyen', 'Nguyễn Văn Quang', 1, 2, '2020-03-05'),
('account2@gmail.com', 'vanchien',    'Trần Văn Chiến',  2, 3, '2020-03-07'),
('account3@gmail.com', 'cocacoca',    'Phạm Văn Coca',   2, 4, '2020-03-08'),
('account4@gmail.com', 'duongdo',     'Đỗ Đình Do',   3, 1, '2020-03-10'),
('account5@gmail.com', 'hoangmai',    'Do Hoàng Mai',    6, 2, '2020-04-12'),
('account6@gmail.com', 'minhtuan',    'Di Minh Ho',  7, 4, '2020-04-15'),
('account7@gmail.com', 'ngoclan',     'Vũ Ngọc Lan',     8, 3, '2020-05-01'),
('account8@gmail.com', 'thanhtung',   'Trịnh Thanh Tùng',9, 1, '2020-05-10'),
('account9@gmail.com', 'phuongthao',  'Bùi Phương Thảo',10, 2, '2020-05-20'),
('dev1@gmail.com','dev1','Dev Test 1',5,1,'2020-06-01'),
('dev2@gmail.com','dev2','Dev Test 2',5,1,'2020-06-02'),
('test1@gmail.com','test1','Tester 1',5,2,'2020-06-03'),
('sm1@gmail.com','sm1','Scrum Master 1',5,3,'2020-06-04'),
('pm1@gmail.com','pm1','PM 1',5,4,'2020-06-05');

INSERT INTO `group` (group_name, creator_id, create_date) VALUES
('Testing System', 1, '2019-03-05'),
('Development',    2, '2020-03-07'),
('VTI Sale 01',    2, '2020-03-09'),
('VTI Sale 02',    3, '2020-03-10'),
('VTI Creator',    4, '2020-03-28'),
('Big Team',1,'2020-06-01'),
('Empty Group',1,'2020-06-01');

INSERT INTO group_account (group_id, account_id, join_date) VALUES
(1, 1, '2019-03-05'),
(1, 2, '2020-03-07'),
(2, 1, '2020-03-09'),
(3, 3, '2020-03-10'),
(4, 4, '2020-03-28'),
(5, 5, '2020-04-01'),
(6,1,'2020-06-01'),
(6,2,'2020-06-01'),
(6,3,'2020-06-01'),
(6,4,'2020-06-01'),
(6,5,'2020-06-01'),
(6,6,'2020-06-01');

INSERT INTO type_question (type_name) VALUES
('Essay'),
('Multiple-Choice');

INSERT INTO category_question (category_name) VALUES
('Java'),
('NET'),
('SQL'),
('Postman'),
('Ruby');

INSERT INTO question (content, category_id, type_id, creator_id, create_date) VALUES
('Hỏi về Java',    1, 1, 1, '2020-04-05 09:00:00'),
('Hỏi về NET',     2, 2, 2, '2020-04-05 10:30:00'),
('Hỏi về SQL',     3, 2, 2, '2020-04-06 11:15:00'),
('Hỏi về Postman', 4, 1, 3, '2020-04-06 14:20:00'),
('Hỏi về Ruby',    5, 1, 4, '2020-04-06 16:45:00'),
('Câu hỏi về C#',    5, 1, 4, '2020-04-06 16:45:00'),
('Question chưa có answer',1,1,1,'2020-04-06 16:45:00');

-- TRUNCATE TABLE answer;
INSERT INTO answer (content, question_id, is_correct) VALUES
('Trả lời 01 - Java',    1, 0),
('Trả lời 02 - Java',    1, 1),
('Trả lời 03 - NET',     2, 0),
('Trả lời 04 - SQL',     3, 1),
('Trả lời 05 - Postman', 4, 1),
('Trả lời 06 - Java bổ sung 1', 1, 0),
('Trả lời 07 - Java bổ sung 2', 1, 1),
('Trả lời 08 - NET bổ sung 1', 2, 1),
('Trả lời 09 - NET bổ sung 2', 2, 0),
('Trả lời 10 - NET bổ sung 3', 2, 1),
('Trả lời 11 - NET bổ sung 4', 2, 0),
('Trả lời 12 - SQL bổ sung 1', 3, 0),
('Trả lời 13 - SQL bổ sung 2', 3, 1),
('Trả lời 14 - SQL bổ sung 3', 3, 0),
('Trả lời 15 - Postman bổ sung 1', 4, 0),
('Answer thêm 1',2,1),
('Answer thêm 2',2,0),
('Answer thêm 3',2,1);

INSERT INTO exam (code, title, category_id, duration, creator_id, create_date) VALUES
('VTIQ001', 'Đề thi Java',    1, 60,  1, '2019-04-05 08:00:00'),
('VTIQ002', 'Đề thi NET',     2, 60,  1, '2019-04-05 09:00:00'),
('VTIQ003', 'Đề thi SQL',     3, 120, 2, '2019-04-07 10:00:00'),
('VTIQ004', 'Đề thi Postman', 4, 60,  3, '2020-04-08 13:30:00'),
('VTIQ005', 'Đề thi Ruby',    5, 120, 4, '2020-04-10 15:00:00');

INSERT INTO exam_question (exam_id, question_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(2,1),
(3,1),
(4,1);

-- Question 1: Tạo trigger không cho phép người dùng nhập vào Group có ngày tạo trước 1 năm trước 
DELIMITER $$
CREATE TRIGGER trg_group_before_insert
BEFORE INSERT ON `group`
FOR EACH ROW
BEGIN
    IF NEW.create_date < DATE_SUB(CURDATE(), INTERVAL 1 YEAR) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ngay tao khong duoc duoi 1 nam';
    END IF;
END$$
DELIMITER ;

-- Question 2: Tạo trigger Không cho phép người dùng thêm bất kỳ user nào vào department "Sale" nữa, khi thêm thì hiện ra thông báo "Department "Sale" cannot add more user" 
DELIMITER $$
CREATE TRIGGER trg_account_before_insert
BEFORE INSERT ON account
FOR EACH ROW
BEGIN
    DECLARE v_name VARCHAR(50);

    SELECT department_name
    INTO v_name
    FROM department
    WHERE department_id = NEW.department_id;

    IF v_name = 'Sale' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT='Department "Sale" cannot add more user';
    END IF;
END$$
DELIMITER ;

-- Question 3: Cấu hình 1 group có nhiều nhất là 5 user 
DELIMITER $$
CREATE TRIGGER trg_group_account_insert
BEFORE INSERT ON group_account
FOR EACH ROW
BEGIN
    DECLARE total INT;

    SELECT COUNT(*)
    INTO total
    FROM group_account
    WHERE group_id = NEW.group_id;

    IF total >= 5 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT='Khong duoc co hon 5 thanh vien trong 1 group';
    END IF;
END$$
DELIMITER ;

-- Question 4: Cấu hình 1 bài thi có nhiều nhất là 10 Question 
DELIMITER $$
CREATE TRIGGER trg_exam_question_insert
BEFORE INSERT ON exam_question
FOR EACH ROW
BEGIN
    DECLARE total INT;

    SELECT COUNT(*)
    INTO total
    FROM exam_question
    WHERE exam_id = NEW.exam_id;

    IF total >= 10 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT='Bai thi khong duoc nhieu hon 10 cau hoi';
    END IF;
END$$
DELIMITER ;

-- Question 5: Tạo trigger không cho phép người dùng xóa tài khoản có email là admin@gmail.com (đây là tài khoản admin, không cho phép user xóa), còn lại các tài khoản khác thì sẽ cho phép xóa và sẽ xóa tất cả các thông tin liên quan tới user đó 
DELIMITER $$
CREATE TRIGGER trg_account_before_delete
BEFORE DELETE ON account
FOR EACH ROW
BEGIN
    IF OLD.email='admin@gmail.com' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT='Khong duoc xoa tai khoan admin';
    END IF;
END$$
DELIMITER ;

-- Question 6: Không sử dụng cấu hình default cho field DepartmentID của table Account, hãy tạo trigger cho phép người dùng khi tạo account không điền vào departmentID thì sẽ được phân vào phòng ban "waiting Department"  
DELIMITER $$
CREATE TRIGGER trg_default_department
BEFORE INSERT ON account
FOR EACH ROW
BEGIN
    DECLARE v_department_id SMALLINT UNSIGNED;

    IF NEW.department_id IS NULL THEN
        SELECT department_id
        INTO v_department_id
        FROM department
        WHERE department_name = 'Wait room';
        SET NEW.department_id = v_department_id;
    END IF;
END$$
DELIMITER ;

-- Question 7: Cấu hình 1 bài thi chỉ cho phép user tạo tối đa 4 answers cho mỗi question, trong đó có tối đa 2 đáp án đúng. 
DELIMITER $$
CREATE TRIGGER trg_answer_insert
BEFORE INSERT ON answer
FOR EACH ROW
BEGIN
    DECLARE total_answer INT;
    DECLARE total_correct INT;

    SELECT COUNT(*)
    INTO total_answer
    FROM answer
    WHERE question_id = NEW.question_id;

    IF total_answer >=4 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT='toi da 4 cau tra loi';
    END IF;

    IF NEW.is_correct=1 THEN
        SELECT COUNT(*)
        INTO total_correct
        FROM answer
        WHERE question_id=NEW.question_id
        AND is_correct=1;

        IF total_correct>=2 THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT='toi da 2 cau tra loi dung';
        END IF;
    END IF;
END$$
DELIMITER ;

-- Question 8: Viết trigger sửa lại dữ liệu cho đúng: Nếu người dùng nhập vào gender của account là nam, nữ, chưa xác định thì sẽ đổi lại thành M, F, U cho giống với cấu hình ở database 
DELIMITER $$
CREATE TRIGGER trg_gender
BEFORE INSERT ON account
FOR EACH ROW
BEGIN
    IF NEW.gender='Nam' THEN
        SET NEW.gender='M';

    ELSEIF NEW.gender='Nữ' THEN
        SET NEW.gender='F';

    ELSEIF NEW.gender='Chưa xác định' THEN
        SET NEW.gender='U';
    END IF;
END$$
DELIMITER ;

-- Question 9: Viết trigger không cho phép người dùng xóa bài thi mới tạo được 2 ngày 
DELIMITER $$
CREATE TRIGGER trg_exam_delete
BEFORE DELETE ON exam
FOR EACH ROW
BEGIN
    IF TIMESTAMPDIFF(HOUR, OLD.create_date, NOW()) < 48 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT='Khong the xoa bai thi duoc tao trong vong 2 ngay';
    END IF;
END$$
DELIMITER ;

-- Question 10: Viết trigger chỉ cho phép người dùng chỉ được update, delete các question khi question đó chưa nằm trong exam nào 
DELIMITER $$
CREATE TRIGGER trg_question_update
BEFORE UPDATE ON question
FOR EACH ROW
BEGIN
    IF EXISTS(
        SELECT *
        FROM exam_question
        WHERE question_id=OLD.question_id
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT='Cau hoi dang ton tai trong bai thi';
    END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER trg_question_delete
BEFORE DELETE ON question
FOR EACH ROW
BEGIN
    IF EXISTS(
        SELECT *
        FROM exam_question
        WHERE question_id=OLD.question_id
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT='Question already exists in Exam';
    END IF;
END$$
DELIMITER ;

