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
       (109, 'Illiterati', 'CMYK', 10, 1, 4, 60, 120, 'BORD', 'COOP');

insert into roles(user_role) values ('USER'), ('ADMIN');

insert into users(userid, username, password)
VALUES  (101, 'user', '$2a$10$i6s5SvuBt6ALf9zCAmIRt.EzRwkxzLJ7dSL493a7xhuatTkinwb4C'),
        (102, 'admin', '$2a$10$i6s5SvuBt6ALf9zCAmIRt.EzRwkxzLJ7dSL493a7xhuatTkinwb4C');

insert into users_roles(users_userid, roles_user_role)
VALUES  (101, 'USER'),
        (102, 'ADMIN'),
        (102, 'USER');