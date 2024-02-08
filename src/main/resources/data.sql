INSERT INTO games (gameid, name, manufacturer, age, minimum_players, maximum_players, minimum_duration,
                   average_duration, category, type)


VALUES (101, 'Colonisten van Catan', '999 games', 12, 2, 5, 30, 90, 'BORD', 'Gezeldschap Spel'),
       (102, 'Carcazone', '999 games', 12, 1, 4, 25, 80, 'BORD', 'Gezeldschap Spel'),
       (103, 'Dorfromantik', 'Pegasus', 8, 1, 6, 30, 60, 'BORD', 'COOP'),
       (104, 'Everdell', 'Facade Games', 13, 2, 9, 20, 40, 'CARD', 'COOP BackStab'),
       (105, 'Flamecraft', 'CMYK', 10, 1, 4, 60, 120, 'BORD', 'COOP'),
       (106, 'Foundations', 'CMYK', 10, 1, 4, 60, 120, 'BORD', 'COOP'),
       (107, 'Guild of Merchant Explorers ', 'CMYK', 10, 1, 4, 60, 120, 'BORD', 'COOP'),
       (108, 'Harry Potter Cluedo', 'CMYK', 10, 1, 4, 60, 120, 'BORD', 'COOP'),
       (109, 'Illiterati', 'CMYK', 10, 1, 4, 60, 120, 'BORD', 'COOP'),
       (110, 'De Kolonisten van Catan: Het grote Kanaal', '999 games', 12, 2, 5, 30, 90, 'BORD', 'Gezeldschap Spel');

insert into roles(user_role)
values ('USER'),
       ('ADMIN');

insert into users(userid, username, password)
-- TODO DELETE text based password
-- DELETE! user = userwachtwoord
-- DELETE! admin = adminwachtwoord
VALUES (101, 'user', '$2a$10$CMXKhEA.yLjrVICpqZfmNeSKQA.YAuGNau//Mnx.VylNf3C9y5V1m'),
       (102, 'admin', '$2a$10$WVqk1XkQWMFA.drfO9ZiVeE6Idild1ZH59rYkpxyCQZnC/6cHUE8u'),
       (103, 'Randomuser', '$2a$10$WVqk1XkQWMFA.drfO9ZiVeE6Idild1ZH59rYkpxyCQZnC/6cHUE8u');
insert into users_roles(users_userid, roles_user_role)
VALUES (101, 'USER'),
       (102, 'ADMIN'),
       (102, 'USER'),
       (103, 'USER');

insert into reviews (reviewid, userid, gameid, star_rating, user_review)
VALUES (101, 103, 102, 4, 'Helemaal tegek'),
       (102, 102, 102, 3, 'Wordt heel gauw best wel zaai');

insert into expansions(expansionid, gameid)
VALUES (110, 101)


