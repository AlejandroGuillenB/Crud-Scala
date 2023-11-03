-- !Ups
CREATE TABLE IF NOT EXISTS public.PERSON
(
	id INT NOT NULL,
    "name" VARCHAR(45) NOT NULL,
    email VARCHAR(45),
    englishLevel VARCHAR(2),
    technicalKnows VARCHAR(500),
    linkCV VARCHAR(500),
    CONSTRAINT chk_person_email_length CHECK (length(email) > 7),
    CONSTRAINT chk_person_email_regex CHECK (email ~ '^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$'),
    CONSTRAINT chk_person_english_level CHECK ((englishlevel = 'A1') OR (englishlevel = 'A2') OR (englishlevel = 'B1') OR (englishlevel = 'B2') OR (englishlevel = 'C1')),
    CONSTRAINT chk_person_name_length CHECK (length(name) > 3),
    CONSTRAINT unq_person_email UNIQUE (email)
    PRIMARY KEY (id),
);

-- !Downs
DROP TABLE public.PERSON;
