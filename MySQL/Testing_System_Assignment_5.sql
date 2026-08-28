DROP DATABASE IF EXISTS Testing_System_Assignment_5;
CREATE DATABASE Testing_System_Assignment_5;
USE Testing_System_Assignment_5;

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

-- Question 1: Tạo store để người dùng nhập vào tên phòng ban và in ra tất cả các account thuộc phòng ban đó 
DELIMITER $$
CREATE PROCEDURE q1 (IN p_dep_name VARCHAR(100))
BEGIN
	SELECT acc.*, de.department_name
    FROM department de
    JOIN account acc ON de.department_id = acc.department_id
    WHERE de.department_name = p_dep_name;
END $$
DELIMITER ;

CALL q1('Sale');

-- Question 2: Tạo store để in ra số lượng account trong mỗi group  
DELIMITER $$
CREATE PROCEDURE q2()
BEGIN 
	SELECT g.group_name, count(ga.account_id) AS 'SL account'
    FROM  `group` g
    LEFT JOIN group_account ga ON g.group_id = ga.group_id
    GROUP BY g.group_id;
END $$
DELIMITER ;

CALL q2();

-- Question 3: Tạo store để thống kê mỗi type question có bao nhiêu question được tạo trong tháng hiện tại 
DROP PROCEDURE IF EXISTS q3;
DELIMITER $$
CREATE PROCEDURE q3()
BEGIN
	SELECT tq.type_name, count(q.question_id) AS 'SL question'
    FROM type_question tq 
    LEFT JOIN question q ON tq.type_id = q.type_id AND month(q.create_date) = month(curdate()) AND year(q.create_date) = year(curdate())
    GROUP BY tq.type_id;
END $$
DELIMITER ;

CALL q3();

-- Question 4: Tạo store để trả ra id của type question có nhiều câu hỏi nhất 
DROP PROCEDURE IF EXISTS q4;
DELIMITER $$
CREATE PROCEDURE q4(OUT p_type_id varchar(100))
BEGIN
	WITH cte_q4 AS (
		SELECT tq.type_id, tq.type_name, count(q.question_id) AS questions
        FROM type_question tq 
        JOIN question q ON tq.type_id = q.type_id
        GROUP BY tq.type_id
    )
    SELECT GROUP_CONCAT(cte_q4.type_id) INTO p_type_id
    FROM cte_q4 
    WHERE questions = (
		SELECT max(questions)
        FROM cte_q4
	);
END $$
DELIMITER ;

CALL q4(@type_id);
SELECT @type_id;

-- Question 5: Sử dụng store ở question 4 để tìm ra tên của type question 
DROP PROCEDURE IF EXISTS q5;
DELIMITER $$
CREATE PROCEDURE q5()
BEGIN
	CALL q4(@type_id);
	SELECT tq.type_name AS type_name
    FROM type_question tq
    WHERE find_in_set(tq.type_id, @type_id);
END $$
DELIMITER ;

CALL q5();

-- Question 6: Viết 1 store cho phép người dùng nhập vào 1 chuỗi và trả về group có tên chứa chuỗi của người dùng nhập vào hoặc trả về user có username chứa chuỗi của người dùng nhập vào 
DROP PROCEDURE IF EXISTS q6;
DELIMITER $$
CREATE PROCEDURE q6 (IN p_keyword VARCHAR(100))
BEGIN
	SELECT g.group_name AS result, 'GROUP' AS type
	FROM `group` g
	WHERE g.group_name LIKE CONCAT('%',p_keyword,'%')
	UNION
	SELECT acc.user_name, 'ACCOUNT' 
	FROM account acc
	WHERE acc.user_name LIKE CONCAT('%',p_keyword,'%');
END $$
DELIMITER ;

CALL q6('o');

-- Question 7: Viết 1 store cho phép người dùng nhập vào thông tin fullName, email và trong store sẽ tự động gán:  
-- 	username sẽ giống email nhưng bỏ phần @..mail đi  	
-- 	positionID: sẽ có default là developer 
-- 	departmentID: sẽ được cho vào 1 phòng chờ 
-- 	Sau đó in ra kết quả tạo thành công 
DROP PROCEDURE IF EXISTS q7;
DELIMITER $$
CREATE PROCEDURE q7 (IN p_fullname VARCHAR(50), IN p_email VARCHAR(100))
BEGIN
DECLARE v_username VARCHAR(50);
DECLARE v_position_id TINYINT;
DECLARE v_department_id SMALLINT;
SET v_username = LEFT(p_email, LOCATE('@',p_email)-1);

SELECT pos.position_id INTO v_position_id
FROM position pos 
WHERE pos.position_name = 'Dev';

SELECT dep.department_id INTO v_department_id
FROM department dep 
WHERE dep.department_name = 'Wait room';

INSERT INTO account (email, user_name, full_name, department_id, position_id) VALUES
(p_email, v_username, p_fullname, v_department_id, v_position_id);

SELECT 'Create Successfully' AS message;
END $$
DELIMITER ;

CALL q7 ('lgp','lgp123@gmail.com');

-- Question 8: Viết 1 store cho phép người dùng nhập vào Essay hoặc Multiple-Choice để thống kê câu hỏi essay hoặc multiple-choice nào có content dài nhất 
DROP PROCEDURE IF EXISTS q8;
DELIMITER $$
CREATE PROCEDURE q8(IN p_type VARCHAR(50))
BEGIN
    WITH cte_q8 AS (
		SELECT q.question_id, q.content, tq.type_name,
            CHAR_LENGTH(TRIM(REGEXP_REPLACE(q.content, '[[:space:]]+', ' '))) - CHAR_LENGTH(REPLACE(TRIM(REGEXP_REPLACE(q.content, '[[:space:]]+', ' ')),' ','')) + 1 AS word_length
        FROM question q
        JOIN type_question tq ON q.type_id = tq.type_id
        WHERE tq.type_name = p_type
    )

    SELECT *
    FROM cte_q8
    WHERE word_length = (
        SELECT MAX(word_length)
        FROM cte_q8
    );
END $$
DELIMITER ;

CALL q8('Essay');

-- Question 9: Viết 1 store cho phép người dùng xóa exam dựa vào ID 
DROP PROCEDURE IF EXISTS q9;
DELIMITER $$
CREATE PROCEDURE q9 (IN p_exam_id INT)
BEGIN
DELETE FROM exam_question
WHERE exam_id = p_exam_id;

DELETE FROM exam
WHERE exam_id = p_exam_id;

SELECT 'Delete Successfully';
END $$
DELIMITER ;

CALL q9 (1);

-- Question 10: Tìm ra các exam được tạo từ 3 năm trước và xóa các exam đó đi (sử dụng store ở câu 9 để xóa) 
-- 	Sau đó in số lượng record đã remove từ các table liên quan trong khi removing 
DROP PROCEDURE IF EXISTS q10;
DELIMITER $$
CREATE PROCEDURE q10()
BEGIN
    DECLARE v_exam_id INT;
    DECLARE done INT DEFAULT FALSE;
    DECLARE exam_cursor CURSOR FOR
        SELECT exam_id
        FROM exam
        WHERE create_date < DATE_SUB(CURDATE(), INTERVAL 3 YEAR);
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    CREATE TEMPORARY TABLE temp_delete_exam (
        exam_id INT
    );

    INSERT INTO temp_delete_exam
    SELECT exam_id
    FROM exam
    WHERE create_date < DATE_SUB(CURDATE(), INTERVAL 3 YEAR);

    SELECT COUNT(*) INTO @count_exam_question
    FROM exam_question
    WHERE exam_id IN (
        SELECT exam_id FROM temp_delete_exam
    );

    SELECT COUNT(*) INTO @count_exam
    FROM temp_delete_exam;
    
    OPEN exam_cursor;
    read_loop: LOOP
        FETCH exam_cursor INTO v_exam_id;
        IF done THEN
            LEAVE read_loop;
        END IF;
        CALL q9 (v_exam_id);
    END LOOP;
    CLOSE exam_cursor;
    
    SELECT 
        @count_exam AS 'Exam deleted',
        @count_exam_question AS 'Exam_question deleted';
    DROP TEMPORARY TABLE temp_delete_exam;
END $$
DELIMITER ;

CALL q10();

-- Question 11: Viết store cho phép người dùng xóa phòng ban bằng cách người dùng nhập vào tên phòng ban và các account thuộc phòng ban đó sẽ được chuyển về phòng ban default là phòng ban chờ việc 
DROP PROCEDURE IF EXISTS q11;
DELIMITER $$
CREATE PROCEDURE q11(IN p_department_name VARCHAR(50))
BEGIN
    DECLARE v_department_id SMALLINT;
    DECLARE v_wait_room_id SMALLINT;

    SELECT dep.department_id INTO v_department_id
    FROM department dep
    WHERE dep.department_name = p_department_name;
    
    SELECT dep2.department_id INTO v_wait_room_id
    FROM department dep2
    WHERE dep2.department_name = 'Wait room';

    UPDATE account
    SET department_id = v_wait_room_id
    WHERE department_id = v_department_id;

    DELETE FROM department
    WHERE department_id = v_department_id;

    SELECT 'Delete Department Successfully' AS message;
END $$
DELIMITER ;

CALL q11('Sale');

-- Question 12: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong năm nay
DROP PROCEDURE IF EXISTS q12;
DELIMITER $$
CREATE PROCEDURE q12()
BEGIN
    SELECT m.month, COUNT(q.question_id) AS total_question
    FROM (
        SELECT 1 AS month UNION ALL
        SELECT 2 UNION ALL
        SELECT 3 UNION ALL
        SELECT 4 UNION ALL
        SELECT 5 UNION ALL
        SELECT 6 UNION ALL
        SELECT 7 UNION ALL
        SELECT 8 UNION ALL
        SELECT 9 UNION ALL
        SELECT 10 UNION ALL
        SELECT 11 UNION ALL
        SELECT 12
    ) m
    LEFT JOIN question q ON MONTH(q.create_date) = m.month AND YEAR(q.create_date) = YEAR(CURDATE())
    GROUP BY m.month
    ORDER BY m.month ASC;
END $$
DELIMITER ;

CALL q12();

-- Question 13: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong 6 tháng gần đây nhất  
-- (Nếu tháng nào không có thì sẽ in ra là "không có câu hỏi nào trong  tháng") 
DROP PROCEDURE IF EXISTS q13;
DELIMITER $$
CREATE PROCEDURE q13()
BEGIN
WITH RECURSIVE months AS (
    SELECT 
        DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 5 MONTH),'%Y-%m') AS month_year
    UNION ALL
	SELECT 
        DATE_FORMAT(DATE_ADD(STR_TO_DATE(CONCAT(month_year,'-01'),'%Y-%m-%d'),INTERVAL 1 MONTH),'%Y-%m')
    FROM months
    WHERE month_year < DATE_FORMAT(CURDATE(),'%Y-%m')
),
question_count AS (
    SELECT 
        DATE_FORMAT(create_date,'%Y-%m') AS month_year,
        COUNT(question_id) AS total_question
    FROM question
    WHERE create_date >= DATE_SUB(CURDATE(), INTERVAL 6 MONTH)
    GROUP BY DATE_FORMAT(create_date,'%Y-%m')
)
SELECT 
    m.month_year,
    CASE
        WHEN qc.total_question IS NULL 
			THEN 'Không có câu hỏi nào trong tháng'
        ELSE CAST(qc.total_question AS CHAR)
    END AS result
FROM months m
LEFT JOIN question_count qc ON m.month_year = qc.month_year;
END $$
DELIMITER ;

CALL q13();