-- 1. Insertar Dueños
INSERT INTO dueno (nombre, apellidos, dni, email, telefono, fecha_nacimiento, foto_url) 
VALUES ('Carlos', 'Gómez Pérez', '12345678A', 'carlos.gomez@email.com', '600112233', '1985-04-12', NULL);

INSERT INTO dueno (nombre, apellidos, dni, email, telefono, fecha_nacimiento, foto_url) 
VALUES ('Laura', 'Martínez Ruiz', '87654321B', 'laura.martinez@email.com', '611223344', '1992-09-25', NULL);


-- 2. Insertar Mascotas (dueno_dni hace referencia al id del dueño en tu relación JPA)
INSERT INTO mascota (nombre, especie, raza, edad, peso, foto_url, dueno_dni) 
VALUES ('Max', 'Perro', 'Labrador', '3 años', '28 kg', NULL, 1);

INSERT INTO mascota (nombre, especie, raza, edad, peso, foto_url, dueno_dni) 
VALUES ('Luna', 'Gato', 'Siamés', '2 años', '4 kg', NULL, 1);

INSERT INTO mascota (nombre, especie, raza, edad, peso, foto_url, dueno_dni) 
VALUES ('Rocky', 'Perro', 'Bulldog', '5 años', '15 kg', NULL, 2);


-- 3. Insertar Veterinarios
INSERT INTO veterinarios (nombre, apellidos, numero_colegiado, email, telefono, password, rol, foto_url) 
VALUES ('Ana', 'Fernández Silva', 'COL-41001', 'ana.vet@veterinaria.com', '622334455', '1234', 'VETERINARIO', NULL);


-- 4. Insertar Consultas (mascota_id=1 es Max, veterinario_id=1 es Ana)
INSERT INTO consultas (motivo, fecha_hora, estado, observaciones, mascota_id, veterinario_id) 
VALUES ('Revisión anual y vacunación', '2026-09-10 10:30:00', 'PENDIENTE', 'Mascota en buen estado general.', 1, 1);