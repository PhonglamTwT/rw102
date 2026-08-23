DROP DATABASE IF EXISTS Testing_System_Assignment_3;
CREATE DATABASE Testing_System_Assignment_3;
USE Testing_System_Assignment_3;

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

-- Question 1: Viết lệnh để lấy ra danh sách nhân viên và thông tin phòng ban của họ 
SELECT acc.account_id, acc.full_name, dep.department_name
FROM account acc
LEFT JOIN department dep ON acc.department_id = dep.department_id;

-- Question 2: Viết lệnh để lấy ra thông tin các account được tạo sau ngày 20/12/2010  
SELECT *
FROM account acc
WHERE acc.create_date > '2010-12-20';

-- Question 3: Viết lệnh để lấy ra tất cả các developer  
SELECT acc.account_id, acc.full_name, pos.position_name
FROM account acc
JOIN position pos ON acc.position_id = pos.position_id
WHERE pos.position_name = 'Dev';

-- Question 4: Viết lệnh để lấy ra danh sách các phòng ban có >3 nhân viên 
SELECT dep.department_name, count(acc.account_id) AS 'SLNV'
FROM account acc
JOIN department dep ON acc.department_id = dep.department_id
GROUP BY dep.department_id
HAVING count(acc.account_id) > 3;

-- Question 5: Viết lệnh để lấy ra danh sách câu hỏi được sử dụng trong đề thi nhiều nhất 
SELECT que.question_id, que.content, count(exa.exam_id) AS 'So luong su dung'
FROM exam_question exa
JOIN question que ON exa.question_id = que.question_id
GROUP BY que.question_id
HAVING count(exa.exam_id) = (
	SELECT count(exa.exam_id)
    FROM exam_question exa
    GROUP BY exa.question_id
	ORDER BY count(exa.exam_id) DESC
    LIMIT 1
);

-- Question 6: Thông kê mỗi category Question được sử dụng trong bao nhiêu Question 
SELECT cat.category_id, cat.category_name, count(que.question_id) AS 'So luong question su dung'
FROM category_question cat 
LEFT JOIN question que ON cat.category_id = que.category_id
GROUP BY cat.category_id;

-- Question 7: Thông kê mỗi Question được sử dụng trong bao nhiêu Exam 
SELECT que.question_id, que.content, count(exa.exam_id) AS 'So luong duoc su dung trong exam'
FROM question que
LEFT JOIN exam_question exa ON que.question_id = exa.question_id
GROUP BY que.question_id;

-- Question 8: Lấy ra Question có nhiều câu trả lời nhất 
SELECT que.question_id, que.content, count(ans.answer_id) AS 'So luong cau tra loi'
FROM question que 
JOIN answer ans ON que.question_id = ans.question_id
GROUP BY que.question_id
HAVING count(ans.answer_id) = (
	SELECT count(ans.answer_id)
    FROM answer ans 
    GROUP BY ans.question_id
    ORDER BY count(ans.answer_id) DESC
    LIMIT 1
);

-- Question 9: Thống kê số lượng account trong mỗi group  
SELECT gro.group_id, gro.group_name, count(gro_acc.account_id) AS 'So luong account trong group'
FROM `group` gro 
LEFT JOIN group_account gro_acc ON gro.group_id = gro_acc.group_id
GROUP BY gro.group_id;

-- Question 10: Tìm chức vụ có ít người nhất 
SELECT pos.position_name, count(acc.account_id) AS 'So luong nguoi'
FROM position pos 
LEFT JOIN account acc ON pos.position_id = acc.position_id
GROUP BY pos.position_id
HAVING count(acc.account_id) = (
	SELECT count(acc.account_id)
	FROM position pos 
	LEFT JOIN account acc ON pos.position_id = acc.position_id
	GROUP BY pos.position_id 
	ORDER BY count(acc.account_id) ASC
	LIMIT 1
);

-- Question 11: Thống kê mỗi phòng ban có bao nhiêu dev, test, scrum master, PM  
SELECT 	dep.department_name, 
		count(CASE WHEN pos.position_name = 'Dev' THEN 1 END) AS 'Dev',
        count(CASE WHEN pos.position_name = 'Test' THEN 1 END) AS 'Test',
        count(CASE WHEN pos.position_name = 'Scrum Master' THEN 1 END) AS 'Scrum Master',
        count(CASE WHEN pos.position_name = 'PM' THEN 1 END) AS 'PM'
FROM department dep
LEFT JOIN account acc ON dep.department_id = acc.department_id
LEFT JOIN position pos ON pos.position_id = acc.position_id
GROUP BY dep.department_id;
 
-- Question 12: Lấy thông tin chi tiết của câu hỏi bao gồm: thông tin cơ bản của question, loại câu hỏi, ai là người tạo ra câu hỏi, câu trả lời là gì, …
SELECT ques.question_id, ques.content, cat.category_name, typ.type_name, ans.content, acc.full_name, ques.create_date
FROM question ques 
LEFT JOIN answer ans ON ans.question_id = ques.question_id
LEFT JOIN category_question cat ON cat.category_id = ques.category_id
LEFT JOIN type_question typ ON typ.type_id = ques.type_id
LEFT JOIN account acc ON acc.account_id = ques.creator_id;

-- Question 13: Lấy ra số lượng câu hỏi của mỗi loại tự luận hay trắc nghiệm 
SELECT typ.type_name, count(que.question_id) AS 'SL cau hoi'
FROM type_question typ 
LEFT JOIN question que ON typ.type_id = que.type_id
GROUP BY typ.type_id;

-- Question 14:Lấy ra group không có account nào 
SELECT gro.group_name, count(grp_acc.account_id) AS 'SL account'
FROM `group` gro
LEFT JOIN group_account grp_acc ON gro.group_id = grp_acc.group_id
GROUP BY gro.group_id
HAVING count(grp_acc.account_id) = 0;

-- Question 15: Lấy ra group không có account nào 
SELECT gro.group_name, count(grp_acc.account_id) AS 'SL account'
FROM `group` gro
LEFT JOIN group_account grp_acc ON gro.group_id = grp_acc.group_id
GROUP BY gro.group_id
HAVING count(grp_acc.account_id) = 0;

-- Question 16: Lấy ra question không có answer nào 
SELECT ques.question_id, ques.content
FROM question ques
LEFT JOIN answer ans ON ques.question_id = ans.question_id
GROUP BY ques.question_id
HAVING count(ans.answer_id) = 0;

-- Question 17:  
-- a) Lấy các account thuộc nhóm thứ 1 
SELECT grp.group_name, acc.account_id, acc.email, acc.user_name, acc.full_name
FROM `group` grp
LEFT JOIN group_account grp_acc ON grp.group_id = grp_acc.group_id
LEFT JOIN account acc ON grp_acc.account_id = acc.account_id
WHERE grp.group_id = 1;

-- b) Lấy các account thuộc nhóm thứ 2
SELECT grp.group_name, acc.account_id, acc.email, acc.user_name, acc.full_name
FROM `group` grp
LEFT JOIN group_account grp_acc ON grp.group_id = grp_acc.group_id
LEFT JOIN account acc ON grp_acc.account_id = acc.account_id
WHERE grp.group_id = 2;

-- c) Ghép 2 kết quả từ câu a) và câu b) sao cho không có record nào trùng nhau 
SELECT grp.group_name, acc.account_id, acc.email, acc.user_name, acc.full_name
FROM `group` grp
LEFT JOIN group_account grp_acc ON grp.group_id = grp_acc.group_id
LEFT JOIN account acc ON grp_acc.account_id = acc.account_id
WHERE grp.group_id = 1
UNION
SELECT grp.group_name, acc.account_id, acc.email, acc.user_name, acc.full_name
FROM `group` grp
LEFT JOIN group_account grp_acc ON grp.group_id = grp_acc.group_id
LEFT JOIN account acc ON grp_acc.account_id = acc.account_id
WHERE grp.group_id = 2;

-- Question 18:  
-- a) Lấy các group có lớn hơn 5 thành viên 
SELECT grp.group_id, grp.group_name, count(grp_acc.account_id) AS 'SL thanh vien'
FROM group_account grp_acc 
JOIN `group` grp ON grp_acc.group_id = grp.group_id
GROUP BY grp_acc.group_id 
HAVING count(grp_acc.account_id) > 5;

-- b) Lấy các group có nhỏ hơn 7 thành viên 
SELECT grp.group_id, grp.group_name, count(grp_acc.account_id) AS 'SL thanh vien'
FROM group_account grp_acc 
JOIN `group` grp ON grp_acc.group_id = grp.group_id
GROUP BY grp_acc.group_id 
HAVING count(grp_acc.account_id) < 7;

-- c) Ghép 2 kết quả từ câu a) và câu b)
SELECT grp.group_id, grp.group_name, count(grp_acc.account_id) AS 'SL thanh vien'
FROM group_account grp_acc 
JOIN `group` grp ON grp_acc.group_id = grp.group_id
GROUP BY grp_acc.group_id 
HAVING count(grp_acc.account_id) > 5
UNION ALL
SELECT grp.group_id, grp.group_name, count(grp_acc.account_id)
FROM group_account grp_acc 
JOIN `group` grp ON grp_acc.group_id = grp.group_id
GROUP BY grp_acc.group_id 
HAVING count(grp_acc.account_id) < 7;

