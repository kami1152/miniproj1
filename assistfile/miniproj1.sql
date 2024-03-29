CREATE TABLE user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE SEQUENCE bno_seq;

CREATE TABLE servletss.board (
    bno INT PRIMARY KEY,
    btitle VARCHAR(255) NOT NULL,
    bcontent TEXT NOT NULL,
    bwriter VARCHAR(50) NOT NULL,
    bdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    views INT DEFAULT 0,
    recommend_count INT DEFAULT 0
);

CREATE TRIGGER board_before_insert
BEFORE INSERT ON servletss.board
FOR EACH ROW
BEGIN
    IF NEW.bno IS NULL THEN
        SET NEW.bno = NEXTVAL('bno_seq');
    END IF;
END;

drop table board;
drop table user;

CREATE TABLE servletss.board (
    bno INT AUTO_INCREMENT PRIMARY KEY,
    btitle VARCHAR(255) NOT NULL,
    bcontent TEXT NOT NULL,
    bwriter VARCHAR(50) NOT NULL,
    bdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    views INT DEFAULT 0,
    recommend_count INT DEFAULT 0
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

select username from user;

INSERT INTO board (btitle, bcontent, bwriter)
VALUES 
('제목1', '내용1', '작성자1'),
('제목2', '내용2', '작성자2'),
('제목3', '내용3', '작성자3'),
('제목4', '내용4', '작성자4'),
('제목5', '내용5', '작성자5');

ALTER TABLE servletss.board
MODIFY COLUMN btitle VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL;



CREATE TABLE servletss.user (
    user_id VARCHAR(50) PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO servletss.user (user_id, username, email, password) VALUES
('1', 'user1', 'user1@example.com', 'password1'),
('2', 'user2', 'user2@example.com', 'password2'),
('3', 'user3', 'user3@example.com', 'password3'),
('4', 'user4', 'user4@example.com', 'password4'),
('5', 'user5', 'user5@example.com', 'password5');

ALTER TABLE user
ADD COLUMN hobby VARCHAR(100);

ALTER TABLE user
ADD COLUMN uuid VARCHAR(100);

SELECT * FROM board;
select bno, btitle, bcontent,bwriter,bdate,views,recommend_count from board


INSERT INTO board (btitle, bcontent, bwriter)
VALUES 
('제목1', '내용1', '작성자1'),
('제목2', '내용2', '작성자2'),
('제목3', '내용3', '작성자3'),
('제목4', '내용4', '작성자4'),
('제목5', '내용5', '작성자5');

SELECT * FROM user WHERE user_id ='1234' AND password = '1234';



ALTER TABLE `user` DROP COLUMN `hobby`;

CREATE TABLE userhobby (
  user_id varchar(50) NOT NULL,
  hobby varchar(100) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

insert into userhobby (user_id, hobby) values('1234','ab');
drop table userhobby ;

SELECT u.user_id, u.username, u.email, u.password, u.created_at, u.age, uh.hobby
FROM `user` u
LEFT JOIN userhobby uh ON u.user_id = uh.user_id;

SELECT u.user_id, u.username, u.email, u.password, u.created_at, u.age, GROUP_CONCAT(uh.hobby) AS hobbies
FROM `user` u
LEFT JOIN userhobby uh ON u.user_id = uh.user_id
GROUP BY u.user_id;

			SELECT u.user_id, u.username, u.email, u.password, u.created_at, u.age, GROUP_CONCAT(uh.hobby) AS hobbies FROM `user` u  LEFT JOIN userhobby uh ON u.user_id = uh.user_id where u.user_id = '1234' GROUP BY u.user_id;

		update user set uuid='456' where user_id = '1234';
		

	
	
CREATE TABLE tbhobby (
  hobbyid int AUTO_INCREMENT PRIMARY KEY,
  hobbyname varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb3_general_ci;

insert into tbhobby  (hobbyname) values('음악');



INSERT INTO user (user_id, username, email, password) VALUES
('1234', 'user1', 'user1@example.com', 'password1');


DELETE FROM userhobby
WHERE user_id = '54321'
AND hobby IN ('-1', '-2');
DELETE FROM userhobby WHERE user_id = 1234;
delete from user where user_id = '123@321';



CREATE TABLE boardlog (
  bno INT ,
  user_id varchar(100) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table boardlog;