--- !Ups
ALTER TABLE PERSON ADD CONSTRAINT PK_PERSON PRIMARY KEY (id);
ALTER TABLE "user" ADD CONSTRAINT PK_USER PRIMARY KEY (id);
ALTER TABLE "user" ADD CONSTRAINT FK_PERSON FOREIGN KEY (id) REFERENCES PERSON (id);

ALTER TABLE PERSON ADD CONSTRAINT CHK_PERSON_NAME_LENGTH CHECK(LENGTH("name") > 3);
ALTER TABLE PERSON ADD CONSTRAINT CHK_PERSON_EMAIL_LENGTH CHECK(LENGTH(email) > 7);
ALTER TABLE PERSON ADD CONSTRAINT CHK_PERSON_EMAIL_REGEX CHECK(email ~ '^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$');
ALTER TABLE PERSON ADD CONSTRAINT UNQ_PERSON_EMAIL UNIQUE(email);
ALTER TABLE PERSON ADD CONSTRAINT CHK_PERSON_ENGLISH_LEVEL CHECK((englishlevel = 'A1') OR (englishlevel = 'A2') OR (englishlevel = 'B1') OR (englishlevel = 'B2') OR (englishlevel = 'C1'));
ALTER TABLE "user" ADD CONSTRAINT CHK_USER_ROL CHECK((rol = 1) OR (rol = 2) OR (rol = 3));

ALTER TABLE "user" ADD CONSTRAINT UNQ_USER_USERNAME UNIQUE(username);

--- !Downs
ALTER TABLE PERSON DROP CONSTRAINT PK_PERSON;
ALTER TABLE "user" DROP CONSTRAINT PK_USER;
ALTER TABLE "user" DROP CONSTRAINT FK_PERSON;

ALTER TABLE PERSON DROP CONSTRAINT CHK_PERSON_NAME_LENGTH;
ALTER TABLE PERSON DROP CONSTRAINT CHK_PERSON_EMAIL_LENGTH;
ALTER TABLE PERSON DROP CONSTRAINT CHK_PERSON_EMAIL_REGEX;
ALTER TABLE PERSON DROP CONSTRAINT UNQ_PERSON_EMAIL;
ALTER TABLE PERSON DROP CONSTRAINT CHK_PERSON_ENGLISH_LEVEL;
ALTER TABLE "user" DROP CONSTRAINT CHK_USER_ROL;

ALTER TABLE "user" DROP CONSTRAINT UNQ_USER_USERNAME;