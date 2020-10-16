insert into provinces (name) values
('Azuay'),
('Bolívar'),
('Cañar'),
('Carchi'),
('Chimborazo'),
('Cotopaxi'),
('El Oro'),
('Esmeraldas'),
('Galápagos'),
('Guayas'),
('Imbabura'),
('Loja'),
('Los Ríos'),
('Manabí'),
('Morona Santiago'),
('Napo'),
('Orellana'),
('Pastaza'),
('Pichincha'),
('Santa Elena'),
('Santo Domingo de los Tsáchilas'),
('Sucumbíos'),
('Tungurahua'),
('Zamora Chinchipe');

update provinces
set enabled = 1
where id < 25;