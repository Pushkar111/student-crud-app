
-- SIGNUP [signup.jsp] [UserBean, UserDao]
CREATE TABLE st_crud_users (
    id INT NOT NULL AUTO_INCREMENT,
    full_name VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL,
    PRIMARY KEY (id)
);


-- ADD STUDENT [registration.jsp] [StudentBean, StudentDao]
CREATE TABLE st_crud (
    RNO INT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(50),
    STD INT,
    MARKS VARCHAR(50),
    PRIMARY KEY (RNO)
);