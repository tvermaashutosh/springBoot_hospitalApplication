-- username 'admin'
-- password 'admin'
-- role ['A']

INSERT INTO user_tbl (id, creation, username, password)
VALUES ('f96e66f0-7e8f-4991-853b-865ef447870d', '2024-10-20', 'admin', '$2a$10$9y5rvY22TN5eURfJ2GyLnelietQTMT98EkAtw4W09nnquwKdUMeSK');

INSERT INTO user_role_tbl (user_id, role)
VALUES ('f96e66f0-7e8f-4991-853b-865ef447870d', 'A');
