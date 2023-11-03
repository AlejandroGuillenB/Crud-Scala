-- !Ups
CREATE TABLE IF NOT EXISTS public."user"
(
	id INT NOT NULL,
	username VARCHAR(45) NOT NULL,
	"password" VARCHAR(128) NOT NULL,
	rol INT NOT NULL,
    CONSTRAINT chk_user_rol CHECK ((rol = 1) OR (rol = 2) OR (rol = 3)),
    CONSTRAINT unq_user_username UNIQUE (username),
    PRIMARY KEY (id)
    --FOREIGN KEY (id) REFERENCES public.person (id)
);

-- !Downs
DROP TABLE public"user";