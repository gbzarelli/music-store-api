CREATE TABLE genres_tbl
(
    id   INT          NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY UK_genres_name (name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE genres_cashback_tbl
(
    id       INT                  NOT NULL AUTO_INCREMENT,
    weekday  INT                  NOT NULL,
    cashback INT                  NOT NULL COMMENT 'value in percentage (0-100)',
    id_genre INT                  NOT NULL,
    enable   BOOLEAN default TRUE NOT NULL,
    date_time    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT FK_genres_cashback_id_genre FOREIGN KEY (id_genre) REFERENCES genres_tbl (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE discs_tbl
(
    id           INT          NOT NULL AUTO_INCREMENT,
    name         VARCHAR(255) NOT NULL,
    artist       VARCHAR(255) NOT NULL,
    spotify_href VARCHAR(255) NOT NULL,
    id_genre     INT          NOT NULL,
    price        DOUBLE       NOT NULL,
    date_time    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT FK_discs_id_genre FOREIGN KEY (id_genre) REFERENCES genres_tbl (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE sales_tbl
(
    id             INT          NOT NULL AUTO_INCREMENT,
    uuid           varchar(255) NOT NULL,
    total_price    DOUBLE       NOT NULL,
    cashback_price DOUBLE       NOT NULL,
    sale_date_time      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY UK_sales_name (uuid)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE discs_sales_tbl
(
    id             INT    NOT NULL AUTO_INCREMENT,
    id_sale        INT    NOT NULL,
    id_disc        INT    NOT NULL,
    price          DOUBLE NOT NULL,
    price_cashback DOUBLE NOT NULL,
    cashback       INT    NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_discs_sales_id_sale FOREIGN KEY (id_sale) REFERENCES sales_tbl (id),
    CONSTRAINT FK_discs_sales_id_disk FOREIGN KEY (id_disc) REFERENCES discs_tbl (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;