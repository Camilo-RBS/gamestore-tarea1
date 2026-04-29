INSERT INTO video_games (id, name, genre, classification, game_developer)
VALUES ('a97869ad-eac3-4d32-9dcc-bb4d146e7f33', 'The Legend of Zelda', 'Action-Adventure', 'E10+', 'Nintendo');

INSERT INTO video_games (id, name, genre, classification, game_developer)
VALUES ('0b83f29a-5e25-485c-816d-eca0a5cc1d78', 'God of War', 'Action', 'M', 'Santa Monica Studio');

INSERT INTO video_games (id, name, genre, classification, game_developer)
VALUES ('39aa703a-6184-455d-9d0e-296e646c8a89', 'Minecraft', 'Sandbox', 'E10+', 'Mojang');

INSERT INTO platforms (id, name, company)
VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Nintendo Switch', 'Nintendo');

INSERT INTO platforms (id, name, company)
VALUES ('b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'PlayStation 5', 'Sony');

INSERT INTO platforms (id, name, company)
VALUES ('c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13', 'PC', 'Various');

INSERT INTO game_platforms (game_id, platform_id)
VALUES ('a97869ad-eac3-4d32-9dcc-bb4d146e7f33', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');

INSERT INTO game_platforms (game_id, platform_id)
VALUES ('0b83f29a-5e25-485c-816d-eca0a5cc1d78', 'b0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12');

INSERT INTO game_platforms (game_id, platform_id)
VALUES ('39aa703a-6184-455d-9d0e-296e646c8a89', 'c0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13');

INSERT INTO game_platforms (game_id, platform_id)
VALUES ('39aa703a-6184-455d-9d0e-296e646c8a89', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');

-- Insertar reviews
INSERT INTO reviews (id, player, rating, comment, game_id)
VALUES ('d0eebc99-9c0b-4ef8-bb6d-6bb9bd380a14', 'GamerPro', 5, 'Obra maestra!', 'a97869ad-eac3-4d32-9dcc-bb4d146e7f33');

INSERT INTO reviews (id, player, rating, comment, game_id)
VALUES ('e0eebc99-9c0b-4ef8-bb6d-6bb9bd380a15', 'CriticBoy', 4, 'Muy bueno', 'a97869ad-eac3-4d32-9dcc-bb4d146e7f33');

INSERT INTO reviews (id, player, rating, comment, game_id)
VALUES ('f0eebc99-9c0b-4ef8-bb6d-6bb9bd380a16', 'BuilderFan', 5, 'Creatividad infinita', '39aa703a-6184-455d-9d0e-296e646c8a89');