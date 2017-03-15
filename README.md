# lazy-loading-spring-demo

This is a Spring-based example application that demonstrates lazy loading with Vaadin Grid components.

## Running the app

1) Configure a database connection in the
[application.properties](https://github.com/alejandro-du/lazy-loading-spring-demo/blob/master/src/main/resources/application.properties)
file. By default, this example uses a MySQL database. You should include the driver dependency in the pom.xml file if you want
to use a diferent database.

2) Create a person table:
```
CREATE TABLE person(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email VARCHAR(255)
);
```

2) Insert some test rows in the person table. If you are using MySQL, you can run the following script:
```
DELIMITER //
CREATE PROCEDURE insert_people(IN amount INT)
BEGIN
  DECLARE i INT DEFAULT 1;
  WHILE i <= amount DO
    INSERT INTO person(first_name,last_name,email)
      VALUES (CONCAT("First", i), CONCAT("Last", i), CONCAT("email", i, "@test.com"));
    SET i = i + 1;
  END WHILE;
END //

CALL insert_people(1000);
```

3) Download, compile and run the app by running the following in a terminal:
```
git clone https://github.com/alejandro-du/lazy-loading-spring-demo.git
cd lazy-loading-spring-demo
mvn package spring-boot:run
```

4) Point your browser to <http://localhost:8080>.
