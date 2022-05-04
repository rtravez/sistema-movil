
INSERT INTO usuarios (username, password, enabled) VALUES ('rene','$2a$10$d3rZ6nZbwvJ1P5q3zVZkju1IZEjrHWi/Uv7r949pIKC4LMHEIAUvy',true);
INSERT INTO usuarios (username, password, enabled) VALUES ('admin','$2a$10$qTme6hMTZUgHKA0Wytbs7./PT3QHNj.kX8X9Jzn2riRiCouxEFEoq',true);

INSERT INTO roles (nombre, estado) VALUES ('ROLE_USER', true);
INSERT INTO roles (nombre, estado) VALUES ('ROLE_ADMIN', true);

INSERT INTO roles_usuarios (usuario_id, rol_id, estado) VALUES (1, 1, true);
INSERT INTO roles_usuarios (usuario_id, rol_id, estado) VALUES (2, 2, true);