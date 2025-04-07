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
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;sys_config

-- Película 1: Interstellar
INSERT INTO movie (
    BUDGET,
    HOMEPAGE,
    ORIGINAL_LANGUAGE,
    ORIGINAL_TITLE,
    OVERVIEW,
    POPULARITY,
    RELEASE_DATE,
    REVENUE,
    RUNTIME,
    STATUS,
    TAGLINE,
    TITLE,
    VOTE_AVERAGE,
    VOTE_COUNT
)
VALUES (
           165000000.00,
           'https://www.interstellarmovie.net/',
           'en',
           'Interstellar',
           'A team of explorers travel through a wormhole in space in an attempt to ensure humanity\'s survival.',
           92.45,
           '2014-11-07',
           701729206.00,
           169,
           'Released',
           'Mankind was born on Earth. It was never meant to die here.',
           'Interstellar',
           8.6,
           1750000
       );

-- Película 2: Parasite
INSERT INTO movie (
    BUDGET,
    HOMEPAGE,
    ORIGINAL_LANGUAGE,
    ORIGINAL_TITLE,
    OVERVIEW,
    POPULARITY,
    RELEASE_DATE,
    REVENUE,
    RUNTIME,
    STATUS,
    TAGLINE,
    TITLE,
    VOTE_AVERAGE,
    VOTE_COUNT
)
VALUES (
           11400000.00,
           'https://www.parasite-movie.com/',
           'ko',
           'Gisaengchung',
           'Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan.',
           78.21,
           '2019-05-30',
           257591776.00,
           132,
           'Released',
           'Act like you own the place.',
           'Parasite',
           8.5,
           1050000
       );

-- Película 3: The Dark Knight
INSERT INTO movie (
    BUDGET,
    HOMEPAGE,
    ORIGINAL_LANGUAGE,
    ORIGINAL_TITLE,
    OVERVIEW,
    POPULARITY,
    RELEASE_DATE,
    REVENUE,
    RUNTIME,
    STATUS,
    TAGLINE,
    TITLE,
    VOTE_AVERAGE,
    VOTE_COUNT
)
VALUES (
           185000000.00,
           'https://www.thedarkknightrises.com/',
           'en',
           'The Dark Knight',
           'When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.',
           97.63,
           '2008-07-18',
           1004558444.00,
           152,
           'Released',
           'Why So Serious?',
           'The Dark Knight',
           9.0,
           2400000
       );