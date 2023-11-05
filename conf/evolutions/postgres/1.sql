# --- !Ups
CREATE TABLE person
(
	id INT NOT NULL,
    name VARCHAR(45) NOT NULL,
    email VARCHAR(45),
    englishlevel VARCHAR(2),
    technicalknows VARCHAR(500),
    linkcv VARCHAR(500)
);

# --- !Downs
DROP TABLE person;
