CREATE TABLE IF NOT EXISTS users(
    id  integer NOT NULL AUTO_INCREMENT,
    username varchar(50) NOT NULL,
    password varchar(100) NOT NULL,
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
    bild BLOB,
    bildname varchar(50) NOT NULL,
    bildpfad varchar(50) NOT NULL,
    title varchar(50),
    benutzer varchar(20),
    beschreibung varchar(250),
    kategorie varchar(20),
    datum date,
    zeit int
    ) ;