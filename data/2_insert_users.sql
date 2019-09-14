insert into users (name, username, password, enabled, deleted) values
(' Administrador', 'admin', '$2a$10$5VtyQn5zI0eJaX6eYgTnoehqg9S.Ny3VxsQcXTTVBciAThBFA5Ly.', 1, 0 ),
(' Supervisor', 'supervisor', '$2a$10$vq0BeskdjXutmp9AzmPXjeNfHdxX8JjhaojXz7EA9PtDRbFYxoDNC', 1, 0 ),
(' Agente', 'agente', '$2a$10$.CFdonKM91ErymGV.XKzfOb.BwVJds938mpYNdNU4f7G.oebmWTNK', 1, 0 ),
(' Invitado', 'invitado', '$2a$10$qfKKdBpHeRikkDBX38lQz.MkyykcB3c72zSdPuH/tOjYc9QgeV2Mu', 1, 0 );

insert into authorities (name, alias) values
('ROLE_ADMIN', 'Administrador'), ('ROLE_SUPERVISOR', 'Supervisor'), ('ROLE_AGENT', 'Agente'), ('ROLE_GUEST', 'Invitado');

insert into users_authorities (users_id, authorities_id) values
(1, 1), (2, 2), (3, 3);