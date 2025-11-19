CREATE TABLE department (
    id SERIAL PRIMARY KEY,
    name VARCHAR(60)
);

CREATE TABLE seller (
    id SERIAL PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    email VARCHAR(100) NOT NULL,
    birthdate TIMESTAMP NOT NULL,
    basesalary NUMERIC(10,2) NOT NULL,
    departmentid INTEGER NOT NULL,
    FOREIGN KEY (departmentid) REFERENCES department(id)
);

INSERT INTO department (name) VALUES 
  ('Computers'),
  ('Electronics'),
  ('Fashion'),
  ('Books');

INSERT INTO seller (name, email, birthdate, basesalary, departmentid) VALUES 
  ('Bob Brown','bob@gmail.com','1998-04-21 00:00:00',1000,1),
  ('Maria Green','maria@gmail.com','1979-12-31 00:00:00',3500,2),
  ('Alex Grey','alex@gmail.com','1988-01-15 00:00:00',2200,1),
  ('Martha Red','martha@gmail.com','1993-11-30 00:00:00',3000,4),
  ('Donald Blue','donald@gmail.com','2000-01-09 00:00:00',4000,3),
  ('Alex Pink','alexpink@gmail.com','1997-03-04 00:00:00',3000,2);
