-- Eliminar la base de datos si ya existe
DROP DATABASE IF EXISTS `ReviewFilms`;

-- Desactivar temporalmente chequeos para evitar errores al crear relaciones
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- Crear el esquema
CREATE SCHEMA IF NOT EXISTS `ReviewFilms` DEFAULT CHARACTER SET utf16;
USE `ReviewFilms`;

-- Tabla de Actores
CREATE TABLE IF NOT EXISTS `actor` (
`ID` INT NOT NULL AUTO_INCREMENT,
`NAME` VARCHAR(255) NOT NULL,
`GENDER` INT NULL DEFAULT NULL,
PRIMARY KEY (`ID`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf16;

-- Tabla de Equipo de Producción
CREATE TABLE IF NOT EXISTS `crew` (
`ID` INT NOT NULL AUTO_INCREMENT,
`NAME` VARCHAR(255) NOT NULL,
`GENDER` INT NULL DEFAULT NULL,
PRIMARY KEY (`ID`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf16;

-- Tabla de Géneros
CREATE TABLE IF NOT EXISTS `genre` (
`ID` INT NOT NULL AUTO_INCREMENT,
`NAME` VARCHAR(100) NOT NULL UNIQUE,
PRIMARY KEY (`ID`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf16;

-- Tabla para relacionar películas con géneros (muchos a muchos)
CREATE TABLE IF NOT EXISTS `movie_genre` (
`MOVIE_ID` INT NOT NULL,
`GENRE_ID` INT NOT NULL,
PRIMARY KEY (`MOVIE_ID`, `GENRE_ID`),
FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie` (`ID`) ON DELETE CASCADE,
FOREIGN KEY (`GENRE_ID`) REFERENCES `genre` (`ID`) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf16;

-- Tabla de Palabras Clave
CREATE TABLE IF NOT EXISTS `keyword` (
`ID` INT NOT NULL AUTO_INCREMENT,
`NAME` VARCHAR(100) NOT NULL UNIQUE,
PRIMARY KEY (`ID`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf16;

-- Tabla para relacionar películas con palabras clave (muchos a muchos)
CREATE TABLE IF NOT EXISTS `movie_keyword` (
`MOVIE_ID` INT NOT NULL,
`KEYWORD_ID` INT NOT NULL,
PRIMARY KEY (`MOVIE_ID`, `KEYWORD_ID`),
FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie` (`ID`) ON DELETE CASCADE,
FOREIGN KEY (`KEYWORD_ID`) REFERENCES `keyword` (`ID`) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf16;

-- Tabla de Películas
CREATE TABLE IF NOT EXISTS `movie` (
`ID` INT NOT NULL AUTO_INCREMENT,
`BUDGET` DECIMAL(15,2) NULL DEFAULT NULL,
`HOMEPAGE` VARCHAR(255) NULL DEFAULT NULL,
`ORIGINAL_LANGUAGE` VARCHAR(50) NULL DEFAULT NULL,
`ORIGINAL_TITLE` VARCHAR(255) NOT NULL,
`OVERVIEW` TEXT NULL DEFAULT NULL,
`POPULARITY` DECIMAL(5,2) NULL DEFAULT NULL,
`RELEASE_DATE` DATE NULL DEFAULT NULL,
`REVENUE` DECIMAL(15,2) NULL DEFAULT NULL,
`RUNTIME` INT NULL DEFAULT NULL,
`STATUS` VARCHAR(50) NULL DEFAULT NULL,
`TAGLINE` VARCHAR(255) NULL DEFAULT NULL,
`TITLE` VARCHAR(255) NOT NULL,
`VOTE_AVERAGE` DECIMAL(3,1) NULL DEFAULT NULL,
`VOTE_COUNT` INT NULL DEFAULT NULL,
PRIMARY KEY (`ID`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf16;

-- Tabla para asociar películas con productoras (muchos a muchos)
CREATE TABLE IF NOT EXISTS `movie_production_company` (
`MOVIE_ID` INT NOT NULL,
`PRODUCTION_COMPANY_ID` INT NOT NULL,
PRIMARY KEY (`MOVIE_ID`, `PRODUCTION_COMPANY_ID`),
FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie` (`ID`) ON DELETE CASCADE,
FOREIGN KEY (`PRODUCTION_COMPANY_ID`) REFERENCES `production_company` (`ID`) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf16;

-- Tabla de Productoras
CREATE TABLE IF NOT EXISTS `production_company` (
`ID` INT NOT NULL AUTO_INCREMENT,
`NAME` VARCHAR(255) NOT NULL UNIQUE,
PRIMARY KEY (`ID`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf16;

-- Relaciones Películas-Actores
CREATE TABLE IF NOT EXISTS `movie_cast` (
`MOVIE_ID` INT NOT NULL,
`ACTOR_ID` INT NOT NULL,
`CHARACTER` VARCHAR(255) NULL DEFAULT NULL,
`CREDIT_ID` VARCHAR(60) NOT NULL,
`CREDIT_ORDER` INT NULL DEFAULT NULL,
PRIMARY KEY (`MOVIE_ID`, `ACTOR_ID`),
FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie` (`ID`) ON DELETE CASCADE,
FOREIGN KEY (`ACTOR_ID`) REFERENCES `actor` (`ID`) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf16;

-- Tabla de Usuarios
CREATE TABLE IF NOT EXISTS `user` (
`ID` INT NOT NULL AUTO_INCREMENT,
`EMAIL` VARCHAR(255) NOT NULL UNIQUE,
`PASSWORD_HASH` VARCHAR(255) NOT NULL,
`NAME` VARCHAR(255) NOT NULL,
PRIMARY KEY (`ID`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf16;

-- Tabla de Roles
CREATE TABLE IF NOT EXISTS `role` (
`ID` INT NOT NULL AUTO_INCREMENT,
`NAME` VARCHAR(50) NOT NULL UNIQUE,
PRIMARY KEY (`ID`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf16;

-- Tabla de Reseñas
CREATE TABLE IF NOT EXISTS `review` (
`ID` INT NOT NULL AUTO_INCREMENT,
`MOVIE_ID` INT NOT NULL,
`USER_ID` INT NOT NULL,
`DESCRIPTION` TEXT NULL DEFAULT NULL,
`RATING` INT NOT NULL CHECK (RATING >= 0 AND RATING <= 10),
`DATE` DATE NOT NULL,
PRIMARY KEY (`ID`),
FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE,
FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie` (`ID`) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf16;

-- Tabla de Favoritos
CREATE TABLE IF NOT EXISTS `favorites` (
`MOVIE_ID` INT NOT NULL,
`USER_ID` INT NOT NULL,
PRIMARY KEY (`MOVIE_ID`, `USER_ID`),
FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie` (`ID`) ON DELETE CASCADE,
FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf16;

-- Tabla de Watchlist
CREATE TABLE IF NOT EXISTS `watchlist` (
`MOVIE_ID` INT NOT NULL,
`USER_ID` INT NOT NULL,
PRIMARY KEY (`MOVIE_ID`, `USER_ID`),
FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie` (`ID`) ON DELETE CASCADE,
FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf16;

-- Tabla de Seenactoractor
CREATE TABLE IF NOT EXISTS `seen` (
`MOVIE_ID` INT NOT NULL,
`USER_ID` INT NOT NULL,
PRIMARY KEY (`MOVIE_ID`, `USER_ID`),
FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie` (`ID`) ON DELETE CASCADE,
FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf16;

-- Tabla de Asociación Usuarios-Roles
CREATE TABLE IF NOT EXISTS `user_role` (
`USER_ID` INT NOT NULL,
`ROLE_ID` INT NOT NULL,
PRIMARY KEY (`USER_ID`, `ROLE_ID`),
FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE,
FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf16;

-- Restaurar configuraciones originales
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- Insertar en tablas que no tienen dependencias de claves foráneas
INSERT INTO actor (NAME, GENDER)
VALUES
    ('Robert Downey Jr.', 1),
    ('Scarlett Johansson', 2),
    ('Chris Hemsworth', 1);

INSERT INTO crew (NAME, GENDER)
VALUES
    ('Jon Favreau', 1),
    ('Joss Whedon', 1),
    ('Kenneth Branagh', 1);

INSERT INTO production_company (NAME)
VALUES
    ('Marvel Studios'),
    ('Warner Bros'),
    ('Universal Pictures');

INSERT INTO genre (NAME)
VALUES
    ('Action'),
    ('Comedy'),
    ('Drama'),
    ('Science Fiction'),
    ('Fantasy');

INSERT INTO keyword (NAME)
VALUES
    ('Superhero'),
    ('Dinosaur'),
    ('Space'),
    ('Time Travel');

INSERT INTO role (NAME)
VALUES
    ('Admin'),
    ('User');

INSERT INTO user (EMAIL, PASSWORD_HASH, NAME)
VALUES
    ('john.doe@example.com', 'hashedpassword1', 'John Doe'),
    ('jane.smith@example.com', 'hashedpassword2', 'Jane Smith');

-- Insertar en la tabla `movie` después de las dependencias anteriores
INSERT INTO movie (BUDGET, HOMEPAGE, ORIGINAL_LANGUAGE, ORIGINAL_TITLE, OVERVIEW, POPULARITY, RELEASE_DATE, REVENUE, RUNTIME, STATUS, TAGLINE, TITLE, VOTE_AVERAGE, VOTE_COUNT)
VALUES
    (200000000, 'http://www.marvel.com', 'en', 'Avengers: Endgame', 'The Avengers assemble once more to defeat Thanos.', 85.0, '2019-04-26', 2797800564.00, 181, 'Released', 'The Last Avenger', 'Avengers: Endgame', 8.4, 10523),
    (150000000, 'http://www.dreamworks.com', 'en', 'Jurassic World', 'A new theme park is built on the original site of Jurassic Park.', 72.5, '2015-06-12', 1671713208.00, 124, 'Released', 'The Park is Open', 'Jurassic World', 7.0, 8920);

-- Insertar relaciones entre películas y géneros
INSERT INTO movie_genre (MOVIE_ID, GENRE_ID)
VALUES
    (1, 1),  -- Avengers: Endgame -> Action
    (1, 4),  -- Avengers: Endgame -> Science Fiction
    (2, 2),  -- Jurassic World -> Comedy
    (2, 3);  -- Jurassic World -> Drama

-- Insertar relaciones entre películas y palabras clave
INSERT INTO movie_keyword (MOVIE_ID, KEYWORD_ID)
VALUES
    (1, 1),  -- Avengers: Endgame -> Superhero
    (1, 3),  -- Avengers: Endgame -> Space
    (2, 2),  -- Jurassic World -> Dinosaur
    (2, 4);  -- Jurassic World -> Time Travel

-- Insertar relaciones entre películas y actores
INSERT INTO movie_cast (MOVIE_ID, ACTOR_ID, CHARACTER, CREDIT_ID, CREDIT_ORDER)
VALUES
    (1, 1, 'Iron Man', 'credit1', 1),
    (1, 2, 'Black Widow', 'credit2', 2),
    (1, 3, 'Thor', 'credit3', 3),
    (2, 1, 'Owen Grady', 'credit4', 1),
    (2, 2, 'Claire Dearing', 'credit5', 2);

-- Insertar relaciones entre películas y productoras
INSERT INTO movie_production_company (MOVIE_ID, PRODUCTION_COMPANY_ID)
VALUES
    (1, 1),  -- Avengers: Endgame -> Marvel Studios
    (2, 2);  -- Jurassic World -> Warner Bros

-- Insertar relaciones de usuario con roles
INSERT INTO user_role (USER_ID, ROLE_ID)
VALUES
    (1, 1),  -- John Doe -> Admin
    (2, 2);  -- Jane Smith -> User

-- Insertar reseñas de los usuarios
INSERT INTO review (MOVIE_ID, USER_ID, DESCRIPTION, RATING, DATE)
VALUES
    (1, 1, 'Great movie, loved the action and the storyline!', 10, '2019-04-26'),
    (2, 2, 'A fun and thrilling experience, but some parts felt slow.', 7, '2015-06-12');

-- Insertar en la tabla de favoritos
INSERT INTO favorites (MOVIE_ID, USER_ID)
VALUES
    (1, 1),  -- John Doe added Avengers: Endgame to favorites
    (2, 2);  -- Jane Smith added Jurassic World to favorites

-- Insertar en la tabla de watchlist
INSERT INTO watchlist (MOVIE_ID, USER_ID)
VALUES
    (2, 1),  -- John Doe added Jurassic World to watchlist
    (1, 2);  -- Jane Smith added Avengers: Endgame to watchlist

-- Insertar en la tabla de seen
INSERT INTO seen (MOVIE_ID, USER_ID)
VALUES
    (1, 1),  -- John Doe has seen Avengers: Endgame
    (2, 2);  -- Jane Smith has seen Jurassic World









