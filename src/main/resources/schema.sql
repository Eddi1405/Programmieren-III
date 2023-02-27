CREATE TABLE IF NOT EXISTS users(
    id  integer NOT NULL,
    username varchar(50) NOT NULL,
    email varchar(50) NOT NULL,
    role varchar(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Role(
    id  integer NOT NULL,
    name varchar(50) NOT NULL,
    users varchar(50) NOT NULL
);

CREATE TABLE data(
    id int NOT NULL AUTO_INCREMENT,
    bildpfad varchar(50) NOT NULL,
    title varchar(50),
    beschreibung varchar(250),
    kategorie varchar(20),
    zeit int
    ) ;