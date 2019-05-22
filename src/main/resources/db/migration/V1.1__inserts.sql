INSERT INTO genres_tbl(id, name)
VALUES (1, 'POP'),
       (2, 'MPB'),
       (3, 'CLASSIC'),
       (4, 'ROCK');

INSERT INTO genres_cashback_tbl(weekday, cashback, id_genre)
VALUES (1, 25, 1),
       (1, 30, 2),
       (1, 35, 3),
       (1, 40, 4),
       (2, 7, 1),
       (2, 5, 2),
       (2, 3, 3),
       (2, 10, 4),
       (3, 6, 1),
       (3, 10, 2),
       (3, 5, 3),
       (3, 15, 4),
       (4, 2, 1),
       (4, 15, 2),
       (4, 8, 3),
       (4, 15, 4),
       (5, 10, 1),
       (5, 20, 2),
       (5, 13, 3),
       (5, 15, 4),
       (6, 15, 1),
       (6, 25, 2),
       (6, 18, 3),
       (6, 20, 4),
       (7, 20, 1),
       (7, 30, 2),
       (7, 25, 3),
       (7, 40, 4);