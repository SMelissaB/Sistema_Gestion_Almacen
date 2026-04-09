CREATE DATABASE almacen_db;
USE almacen_db;

CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE unidad_medida (
    id_unidad INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(10) NOT NULL UNIQUE,
    descripcion VARCHAR(50) NOT NULL
);

CREATE TABLE categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(10) NOT NULL UNIQUE,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE proveedor (
    id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(10) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL
);

-- 2. Tablas dependientes
CREATE TABLE producto (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(10) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    id_unidad INT,
    precio_unitario DECIMAL(10,2),
    id_categoria INT,
    id_proveedor INT,
    stock INT DEFAULT 0,
    FOREIGN KEY (id_unidad) REFERENCES unidad_medida(id_unidad),
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria),
    FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor)
);

CREATE TABLE movimiento (
    id_movimiento INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    tipo_movimiento ENUM('ENTRADA', 'SALIDA') NOT NULL,
    numero_documento VARCHAR(20),
    id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE detalle_movimiento (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_movimiento INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio_historico DECIMAL(10,2),
    saldo_momento INT,
    FOREIGN KEY (id_movimiento) REFERENCES movimiento(id_movimiento),
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);


-- inserciones

INSERT INTO usuario (username, password) 
VALUES ('sise', '12345');

INSERT INTO proveedor (codigo, nombre)
VALUES
('P001', 'Distribuidora Global'),
('P002', 'Logística Express'),
('P003', 'Soluciones Tecnológicas S.A.'),
('P004', 'Suministros Industriales'),
('P005', 'Comercializadora del Norte');

-- Procedimientos Almacenados

-- -----------------------------------------------------
-- PROCEDIMIENTOS PARA PROVEEDOR
-- -----------------------------------------------------
DELIMITER //

CREATE PROCEDURE sp_listar_proveedores()
BEGIN
    SELECT id_proveedor, codigo, nombre FROM proveedor;
END //

CREATE PROCEDURE sp_agregar_proveedor(
    IN p_codigo VARCHAR(20),
    IN p_nombre VARCHAR(100)
)
BEGIN
    INSERT INTO proveedor(codigo, nombre) VALUES (p_codigo, p_nombre);
END //

CREATE PROCEDURE sp_buscar_proveedor(
    IN p_codigo VARCHAR(20)
)
BEGIN
    SELECT id_proveedor, codigo, nombre FROM proveedor WHERE codigo = p_codigo;
END //

CREATE PROCEDURE sp_actualizar_proveedor(
    IN p_id INT,
    IN p_codigo VARCHAR(20),
    IN p_nombre VARCHAR(100)
)
BEGIN
    UPDATE proveedor SET codigo = p_codigo, nombre = p_nombre WHERE id_proveedor = p_id;
END //

CREATE PROCEDURE sp_eliminar_proveedor(
    IN p_id INT
)
BEGIN
    DELETE FROM proveedor WHERE id_proveedor = p_id;
END //