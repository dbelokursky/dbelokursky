INSERT INTO role (name) VALUES ('USER');
INSERT INTO role (name) VALUES ('MODERATOR');
INSERT INTO role (name) VALUES ('ADMIN');

INSERT INTO address (country, city, street, unit, zip) VALUES ('USA', 'Boston', 'Arborway', '111', 111111);
INSERT INTO address (country, city, street, unit, zip) VALUES ('Russia', 'Moscow', 'Lenin st.', '22', 222222);
INSERT INTO address (country, city, street, unit, zip) VALUES ('Russia', 'Rostov', 'Bartholomew st.', '33', 333333);

INSERT INTO mv_user (login, password, role_id, address_id) VALUES ('login1', 'password1', 1, 1);
INSERT INTO mv_user (login, password, role_id, address_id) VALUES ('login2', 'password2', 2, 2);
INSERT INTO mv_user (login, password, role_id, address_id) VALUES ('login3', 'password3', 3, 3);

INSERT INTO music_type (name) VALUES ('FOLK');
INSERT INTO music_type (name) VALUES ('LATIN');
INSERT INTO music_type (name) VALUES ('POP');
INSERT INTO music_type (name) VALUES ('ROCK');
INSERT INTO music_type (name) VALUES ('JAZZ');

INSERT INTO user_music (user_id, music_id) VALUES (1, 1);
INSERT INTO user_music (user_id, music_id) VALUES (1, 2);
INSERT INTO user_music (user_id, music_id) VALUES (2, 3);
INSERT INTO user_music (user_id, music_id) VALUES (3, 4);
INSERT INTO user_music (user_id, music_id) VALUES (3, 5);

