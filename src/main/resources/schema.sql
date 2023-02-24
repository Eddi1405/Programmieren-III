CREATE TABLE IF NOT EXISTS users(
    id  integer NOT NULL,
    username varchar(50) NOT NULL,
    email varchar(50) NOT NULL
) ;

CREATE TABLE Data(
    Bild varchar(50) NOT NULL,
    Title varchar(50),
    Beschreibung varchar(50),
    Katiegorie varchar(20),
    Zeit int NOT NULL
    ) ;