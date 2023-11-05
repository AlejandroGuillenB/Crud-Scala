--- !Ups
CREATE TABLE "user"
(
	id INT NOT NULL,
	username VARCHAR(45) NOT NULL,
	password VARCHAR(128) NOT NULL,
	rol INT NOT NULL
);

--- !Downs
DROP TABLE "user";