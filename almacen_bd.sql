DROP DATABASE IF EXISTS almacen_db;
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

-- 1. unidad_medida
INSERT INTO unidad_medida (codigo, descripcion) VALUES 
('UND', 'Unidades'), ('KG', 'Kilogramos'), ('LT', 'Litros'), 
('PQ', 'Paquete'), ('CJ', 'Caja'), ('MT', 'Metros');

-- 2. categoria
INSERT INTO categoria (codigo, nombre) VALUES 
('CAT01', 'Lácteos'), ('CAT02', 'Abarrotes'), ('CAT03', 'Limpieza'), 
('CAT04', 'Cuidado Personal'), ('CAT05', 'Bebidas'), ('CAT06', 'Carnes');

-- 3. proveedor
INSERT INTO proveedor (codigo, nombre) VALUES 
('PROV01', 'Distribuidora Global'), ('PROV02', 'Alimentos Norte'), ('PROV03', 'Suministros SAC'), 
('PROV04', 'Comercial Lima'), ('PROV05', 'Import Rápido'), ('PROV06', 'Logística Expres');

-- 4. usuario
INSERT INTO usuario (username, password) VALUES 
('admin', 'pass123'), ('sbolivar', 'sol2026'), ('sise', '12345');

-- 5. producto (Asumiendo que los IDs generados arriba son 1, 2, 3...)
INSERT INTO producto (codigo, nombre, id_unidad, precio_unitario, id_categoria, id_proveedor, stock) VALUES 
('P001', 'Leche 1L', 3, 3.50, 1, 1, 100),
('P002', 'Arroz 5kg', 2, 18.20, 2, 2, 50),
('P003', 'Detergente', 1, 12.00, 3, 3, 30),
('P004', 'Shampoo', 1, 15.50, 4, 4, 45),
('P005', 'Agua 600ml', 1, 2.00, 5, 5, 200),
('P006', 'Pollo 1kg', 2, 14.80, 6, 6, 25);

-- 6. movimiento
INSERT INTO movimiento (fecha, tipo_movimiento, numero_documento, id_usuario) VALUES 
('2026-04-01', 'ENTRADA', 'DOC-001', 1),
('2026-04-02', 'SALIDA', 'DOC-002', 2),
('2026-04-03', 'ENTRADA', 'DOC-003', 1),
('2026-04-04', 'SALIDA', 'DOC-004', 3),
('2026-04-05', 'ENTRADA', 'DOC-005', 2),
('2026-04-06', 'SALIDA', 'DOC-006', 2);

-- 7. detalle_movimiento
INSERT INTO detalle_movimiento (id_movimiento, id_producto, cantidad, precio_historico, saldo_momento) VALUES 
(1, 1, 50, 3.50, 150),
(2, 2, 5, 18.20, 45),
(3, 3, 20, 12.00, 50),
(4, 4, 10, 15.50, 35),
(5, 5, 100, 2.00, 300),
(6, 6, 5, 14.80, 20);

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
    IN p_codigo VARCHAR(10),
    IN p_nombre VARCHAR(100)
)
BEGIN
    INSERT INTO proveedor(codigo, nombre) VALUES (p_codigo, p_nombre);
END //

CREATE PROCEDURE sp_buscar_proveedor(
    IN p_codigo VARCHAR(10)
)
BEGIN
    SELECT id_proveedor, codigo, nombre FROM proveedor WHERE codigo = p_codigo;
END //

CREATE PROCEDURE sp_actualizar_proveedor(
    IN p_id INT,
    IN p_codigo VARCHAR(10),
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
DELIMITER ;

-- -----------------------------------------------------
-- PROCEDIMIENTOS PARA MOVIMIENTO
-- -----------------------------------------------------
DELIMITER //
CREATE PROCEDURE sp_registrar_movimiento(
    IN p_fecha DATE,
    IN p_tipo ENUM('ENTRADA', 'SALIDA'),
    IN p_documento VARCHAR(20),
    IN p_id_usuario INT
)
BEGIN
    INSERT INTO movimiento(fecha, tipo_movimiento, numero_documento, id_usuario) 
    VALUES (p_fecha, p_tipo, p_documento, p_id_usuario);
    
    SELECT LAST_INSERT_ID() AS id_generado;
END //

CREATE PROCEDURE sp_listar_movimientos()
BEGIN
    SELECT 
        m.id_movimiento, 
        m.fecha, 
        m.tipo_movimiento, 
        m.numero_documento, 
        u.username AS nombre_usuario
    FROM movimiento m
    LEFT JOIN usuario u ON m.id_usuario = u.id_usuario;
END //
DELIMITER ;
-- -----------------------------------------------------
-- PROCEDIMIENTOS PARA DETALLE_MOVIMIENTO
-- -----------------------------------------------------
DELIMITER //
CREATE PROCEDURE sp_agregar_detalle(
    IN p_id_mov INT,
    IN p_id_prod INT,
    IN p_cant INT,
    IN p_precio DECIMAL(10,2),
    IN p_saldo INT
)
BEGIN
    INSERT INTO detalle_movimiento(id_movimiento, id_producto, cantidad, precio_historico, saldo_momento) 
    VALUES (p_id_mov, p_id_prod, p_cant, p_precio, p_saldo);
END //

CREATE PROCEDURE sp_listar_detalle_por_movimiento(IN p_id_mov INT)
BEGIN
    SELECT 
        d.id_detalle, 
        p.nombre AS nombre_producto, 
        d.cantidad, 
        d.precio_historico, 
        d.saldo_momento
    FROM detalle_movimiento d
    INNER JOIN producto p ON d.id_producto = p.id_producto
    WHERE d.id_movimiento = p_id_mov;
END //

DELIMITER ;


-- -----------------------------------------------------
-- TRIGGER PARA DETALLE_MOVIMIENTO
-- -----------------------------------------------------

DELIMITER //

CREATE TRIGGER tr_actualizar_stock_insert
AFTER INSERT ON detalle_movimiento
FOR EACH ROW
BEGIN
    DECLARE v_tipo VARCHAR(20);
    
    -- Obtenemos el tipo (ENTRADA/SALIDA) desde la cabecera
    SELECT tipo_movimiento INTO v_tipo 
    FROM movimiento 
    WHERE id_movimiento = NEW.id_movimiento;
    
    -- Aplicamos la lógica según el tipo
    IF v_tipo = 'ENTRADA' THEN
        UPDATE producto 
        SET stock = stock + NEW.cantidad 
        WHERE id_producto = NEW.id_producto;
    ELSEIF v_tipo = 'SALIDA' THEN
        UPDATE producto 
        SET stock = stock - NEW.cantidad 
        WHERE id_producto = NEW.id_producto;
    END IF;
END //

DELIMITER ;
-- -----------------------------------------------------
-- PROCEDIMIENTOS PARA BUSQUEDA DE MOVIMIENTOS POR PRODUCTO
-- -----------------------------------------------------

DELIMITER //

CREATE PROCEDURE sp_consulta_movimientos_por_producto(
    IN p_id_producto INT
)
BEGIN
    SET @saldo := 0; -- Variable para el cálculo acumulado

    SELECT 
        m.fecha AS Fecha,
        m.numero_documento AS Documento,
        IF(m.tipo_movimiento = 'ENTRADA', dm.cantidad, NULL) AS Entrada,
        IF(m.tipo_movimiento = 'SALIDA', dm.cantidad, NULL) AS Salida,
        (@saldo := IF(m.tipo_movimiento = 'ENTRADA', @saldo + dm.cantidad, @saldo - dm.cantidad)) AS Saldo
    FROM movimiento m
    INNER JOIN detalle_movimiento dm ON m.id_movimiento = dm.id_movimiento
    WHERE dm.id_producto = p_id_producto -- AND m.estado = 1
    ORDER BY m.fecha ASC, m.id_movimiento ASC;
END //

DELIMITER ;