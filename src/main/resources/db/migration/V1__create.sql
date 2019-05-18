CREATE TABLE genres_tbl(
    id   INT            NOT NULL AUTO_INCREMENT,
    name VARCHAR(100)   NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY UK_genres_name (name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO genres_tbl(id,name) VALUES
    (1,'POP'),(2,'MPB'),(3,'CLASSIC'),(4,'ROCK');

CREATE TABLE genres_cashback_tbl(
    id       INT        NOT NULL AUTO_INCREMENT,
    weekday  INT        NOT NULL,
    cashback INT        NOT NULL COMMENT 'value in percentage (0-100)',
    id_genre INT        NOT NULL,
    enable   TINYINT    default 1 NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_genres_cashback_id_genre FOREIGN KEY (id_genre) REFERENCES genres_tbl(id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO genres_cashback_tbl(weekday, cashback, id_genre) VALUES
    (1, 25,1),(1, 30,2),(1, 35,3),(1, 40,4),
    (2, 7,1),(2, 5,2),(2, 3,3),(2, 10,4),
    (3, 6,1),(3, 10,2), (3, 5,3),(3, 15,4),
    (4, 2,1),(4, 15,2), (4, 8,3),(4, 15,4),
    (5, 10,1),(5, 20,2), (5, 13,3),(5, 15,4),
    (6, 15,1),(6, 25,2), (6, 18,3),(6, 20,4),
    (7, 20,1),(7, 30,2), (7, 25,3),(7, 40,4);

CREATE TABLE discs_tbl(
    id              INT     NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE sales_tbl(
    id              INT     NOT NULL AUTO_INCREMENT,
    uuid            varchar(255)    NOT NULL,
    price           DOUBLE  NOT NULL,
    price_cashback  DOUBLE  NOT NULL,
    date_time       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY UK_sales_name (uuid)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE discs_sales_tbl(
    id              INT     NOT NULL AUTO_INCREMENT,
    id_sale         INT     NOT NULL,
    id_disc         INT     NOT NULL,
    price           DOUBLE  NOT NULL,
    price_cashback  DOUBLE  NOT NULL,
    cashback        INT     NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_discs_sales_id_sale FOREIGN KEY (id_sale) REFERENCES sales_tbl(id),
    CONSTRAINT FK_discs_sales_id_disk FOREIGN KEY (id_disc) REFERENCES discs_tbl(id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
