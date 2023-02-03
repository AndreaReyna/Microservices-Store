INSERT INTO `users` (username, password, enabled, name, last_name, email) VALUES ('juan32', '$2a$10$5ZbJIGDdbM632iLlTRv0juFcyKfFA6u7vHX21kQPNk68CmJhpAo2a', 1, 'Juan', 'Perez', 'juan@gmail.com');
INSERT INTO `users` (username, password, enabled, name, last_name, email) VALUES ('pablo324', '$2a$10$xw873Cl3CJiewfpgmbozluYl8BnzpGa2blekNqTCZ.9KZ.pIrDUve', 1, 'Pablo', 'Gonzalez', 'pablo@gmail.com');

INSERT INTO `roles` (name) VALUES ('ROLE_USER');
INSERT INTO `roles` (name) VALUES ('ROLE_ADMIN');

INSERT INTO `users_roles` (user_id, roles_id) VALUES (1,1);
INSERT INTO `users_roles` (user_id, roles_id) VALUES (2,2);
INSERT INTO `users_roles` (user_id, roles_id) VALUES (2,1);