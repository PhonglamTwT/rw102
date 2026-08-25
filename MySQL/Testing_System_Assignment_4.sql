DROP DATABASE IF EXISTS Testing_System_Assignment_4;
CREATE DATABASE Testing_System_Assignment_4;
USE Testing_System_Assignment_4;

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
    department_id SMALLINT UNSIGNED NOT NULL,
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
		REFERENCES account(account_id)
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
		REFERENCES account(account_id)
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
        REFERENCES account (account_id)
);

-- Table 9: Answer  
DROP TABLE IF EXISTS answer;
CREATE TABLE answer (
    answer_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(1000) NOT NULL,
    question_id INT UNSIGNED NOT NULL,
    is_correct BIT DEFAULT 1,
    FOREIGN KEY (question_id)
        REFERENCES question (question_id)
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
        REFERENCES account (account_id)
);

-- Table 11: ExamQuestion  
DROP TABLE IF EXISTS exam_question;
CREATE TABLE exam_question (
    exam_id INT UNSIGNED NOT NULL,
    question_id INT UNSIGNED NOT NULL,
    PRIMARY KEY (exam_id , question_id),
    FOREIGN KEY (exam_id)
        REFERENCES exam (exam_id),
    FOREIGN KEY (question_id)
        REFERENCES question (question_id)
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
('Bán hàng');

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

-- Xem dữ liệu
SELECT * FROM department;
SELECT * FROM `position`;
SELECT * FROM account;
SELECT * FROM `group`;
SELECT * FROM group_account;
SELECT * FROM type_question;
SELECT * FROM category_question;
SELECT * FROM question;
SELECT * FROM answer;
SELECT * FROM exam;
SELECT * FROM exam_question;

-- Question 1: Tạo view có chứa danh sách nhân viên thuộc phòng ban sale
CREATE VIEW v_acc_sale AS
SELECT acc.*, dep.department_name
FROM account acc 
JOIN department dep ON acc.department_id = dep.department_id
WHERE dep.department_name = 'Sale';

-- Question 2: Tạo view có chứa thông tin các account tham gia vào nhiều group nhất
CREATE VIEW v_acc_most_group AS
WITH cte_acc_group AS (
	SELECT acc.*, count(ga.group_id) AS group_count
	FROM group_account ga
	JOIN account acc ON ga.account_id = acc.account_id
	GROUP BY ga.account_id
)
SELECT * 
FROM cte_acc_group
WHERE group_count = (
	SELECT max(group_count)
    FROM cte_acc_group
);

-- Question 3: Tạo view có chứa câu hỏi có những content quá dài (content quá 300 từ được coi là quá dài) và xóa nó đi
CREATE VIEW v_long_question_content AS 
SELECT que.*
FROM question que 
WHERE char_length(que.content) > 10;

SET SQL_SAFE_UPDATES = 0;
DELETE FROM exam_question eq WHERE question_id IN (
	SELECT question_id 
    FROM v_long_question_content
);

DELETE FROM answer ans WHERE ans.question_id IN (
	SELECT question_id 
    FROM v_long_question_content
);

DELETE FROM v_long_question_content;

-- Question 4: Tạo view có chứa danh sách các phòng ban có nhiều nhân viên nhất
CREATE VIEW v_department_most_acc AS
WITH cte_department_count_acc AS (
	SELECT dep.*, count(acc.account_id) AS SLNV
    FROM department dep 
    JOIN account acc ON dep.department_id = acc.department_id
    GROUP BY dep.department_id
)
SELECT *
FROM cte_department_count_acc WHERE SLNV = (
	SELECT max(SLNV)
    FROM cte_department_count_acc
);

-- Question 5: Tạo view có chứa tất các các câu hỏi do user họ Nguyễn tạo
CREATE VIEW v_question_nguyen AS
SELECT que.*
FROM question que 
JOIN account acc ON que.creator_id = acc.account_id
WHERE acc.full_name LIKE 'Nguyễn%';


