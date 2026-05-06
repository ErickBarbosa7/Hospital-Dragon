-- Crear base de datos
CREATE DATABASE IF NOT EXISTS hospital_dragon;
USE hospital_dragon;

-- TABLA: AREA
CREATE TABLE tbl_area (
    id_area INT AUTO_INCREMENT PRIMARY KEY,
    area VARCHAR(50) NOT NULL
);

-- TABLA: ESTADO
CREATE TABLE tbl_estado (
    id_estado INT AUTO_INCREMENT PRIMARY KEY,
    estado VARCHAR(20) NOT NULL
);

-- TABLA: PISO
CREATE TABLE tbl_piso (
    id_piso INT AUTO_INCREMENT PRIMARY KEY,
    piso VARCHAR(50) NOT NULL
);

-- TABLA: HABITACIONES
CREATE TABLE tbl_habitaciones (
    id_habitacion INT AUTO_INCREMENT PRIMARY KEY,
    numero_cama INT NOT NULL,
    id_expediente_paciente VARCHAR(50) DEFAULT NULL,
    id_piso INT NOT NULL,
    id_area INT NOT NULL,
    id_estado INT NOT NULL,

    FOREIGN KEY (id_piso) REFERENCES tbl_piso(id_piso),
    FOREIGN KEY (id_area) REFERENCES tbl_area(id_area),
    FOREIGN KEY (id_estado) REFERENCES tbl_estado(id_estado)
);

-- INSERTS INICIALES

-- ESTADOS
INSERT INTO tbl_estado (estado) VALUES 
('Libre'), 
('Ocupada'), 
('Mantenimiento');

-- AREAS
INSERT INTO tbl_area (area) VALUES 
('Urgencias'), 
('Pediatria'), 
('UCI'), 
('Recuperacion');

-- PISOS
INSERT INTO tbl_piso (piso) VALUES 
('Planta baja'), 
('Primer piso');

-- Camas

-- Piso 1 (Planta baja = id 1)
INSERT INTO tbl_habitaciones (numero_cama, id_piso, id_area, id_estado) VALUES
(1,1,4,1),(2,1,4,1),(3,1,4,1),(4,1,4,1),
(5,1,4,1),(6,1,4,1),(7,1,4,1),(8,1,4,1),
(9,1,4,1),(10,1,4,1),(11,1,4,1),(12,1,4,1),
(13,1,4,1),(14,1,4,1),(15,1,4,1),(16,1,4,1);

-- Piso 2 (Primer piso = id 2)
INSERT INTO tbl_habitaciones (numero_cama, id_piso, id_area, id_estado) VALUES
(1,2,4,1),(2,2,4,1),(3,2,4,1),(4,2,4,1),
(5,2,4,1),(6,2,4,1),(7,2,4,1),(8,2,4,1),
(9,2,4,1),(10,2,4,1),(11,2,4,1),(12,2,4,1),
(13,2,4,1),(14,2,4,1),(15,2,4,1),(16,2,4,1);