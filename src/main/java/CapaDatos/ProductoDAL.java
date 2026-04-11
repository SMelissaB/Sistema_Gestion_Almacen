/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaDatos;

import CapaRecursos.Producto;
import CapaRecursos.ComboItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ProductoDAL {

    ConexionBD conexion = new ConexionBD(); 
    Connection cn = new ConexionBD().abrirConexion();

    
        public List listar() {
        List<Producto> lista = new ArrayList<>();
        try {
            CallableStatement cst = cn.prepareCall("{call sp_listar_productos()}");
            ResultSet rs = cst.executeQuery();

            while (rs.next()) {
                lista.add(new Producto(rs.getInt(1),
                        rs.getString(2), rs.getString(3),rs.getDouble(4), rs.getInt(5)
                ));
            }

            rs.close();
            cst.close();
        } catch (Exception e) {
            System.out.println("listarProducto: " + e.getMessage());
        }
        return lista;
    }
        
    public Producto buscarPorCodigo(String codigo) {
        Producto oProducto = null;
        try {
            CallableStatement cst = cn.prepareCall("{call sp_buscar_producto_codigo(?)}");
            cst.setString(1, codigo);
            ResultSet rs = cst.executeQuery();

            if (rs.next()) {
                oProducto = new Producto();
                oProducto.setIdProducto(rs.getInt("id_producto")); 
                oProducto.setCodigo(rs.getString("codigo"));
                oProducto.setNombre(rs.getString("nombre"));
                oProducto.setPrecio(rs.getDouble("precio_unitario"));
                oProducto.setStock(rs.getInt("stock"));
            }
            cst.close();
            rs.close();
        } catch (Exception ex) {
            System.out.println("Error al buscar: " + ex.getMessage());
        }
        return oProducto;
    }    
    
    public Producto buscarPorNombre(String nombre) {
        Producto oProducto = null;
        try {
            CallableStatement cst = cn.prepareCall("{call sp_buscar_producto_nombre(?)}");
            cst.setString(1, nombre);
            ResultSet rs = cst.executeQuery();

            if (rs.next()) {
                oProducto = new Producto();
                oProducto.setIdProducto(rs.getInt("id_producto")); 
                oProducto.setCodigo(rs.getString("codigo"));
                oProducto.setNombre(rs.getString("nombre"));
                oProducto.setPrecio(rs.getDouble("precio_unitario"));
                oProducto.setStock(rs.getInt("stock"));
            }
            cst.close();
            rs.close();
        } catch (Exception ex) {
            System.out.println("Error al buscar: " + ex.getMessage());
        }
        return oProducto;
    }   
        
        
    public void insertar(Producto p) {
        
        String sql = "{call sp_agregar_producto(?,?,?,?,?,?)}";
        try (Connection cn = conexion.abrirConexion(); CallableStatement cs = cn.prepareCall(sql)) {
            cs.setString(1, p.getCodigo());
            cs.setString(2, p.getNombre());
            cs.setInt(3, p.getIdUnidad());
            cs.setDouble(4, p.getPrecio());
            cs.setInt(5, p.getIdCategoria());
            cs.setInt(6, p.getIdProveedor());
            cs.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al insertar: " + e.getMessage());
        } finally {
            conexion.cerrarConexion();
        }
    }

    /*
    public DefaultTableModel listar() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("U.Medida");
        modelo.addColumn("Precio");
        modelo.addColumn("Categoría");
        modelo.addColumn("Proveedor");
        modelo.addColumn("Stock");

        String sql = "{call sp_listar_productos()}";
        try (Connection cn = conexion.abrirConexion(); CallableStatement cs = cn.prepareCall(sql); ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                Object[] fila = {rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getDouble(5), rs.getString(6), rs.getString(7), rs.getInt(8)};
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return modelo;
    }

    public void cargarComboUnidad(javax.swing.JComboBox<ComboItem> combo) {
        String sql = "SELECT id_unidad, descripcion FROM unidad_medida";
        try (Connection cn = conexion.abrirConexion(); Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                combo.addItem(new ComboItem(rs.getInt(1), rs.getString(2)));
            }
        } catch (Exception e) {
            System.out.println("Error combo unidad: " + e.getMessage());
        }
    }

    public void cargarComboCategoria(javax.swing.JComboBox<ComboItem> combo) {
        String sql = "SELECT id_categoria, nombre FROM categoria";
        try (Connection cn = conexion.abrirConexion(); Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                combo.addItem(new ComboItem(rs.getInt(1), rs.getString(2)));
            }
        } catch (Exception e) {
            System.out.println("Error combo categoria: " + e.getMessage());
        }
    }

    public void cargarComboProveedor(javax.swing.JComboBox<ComboItem> combo) {
        String sql = "SELECT id_proveedor, nombre FROM proveedor";
        try (Connection cn = conexion.abrirConexion(); Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                combo.addItem(new ComboItem(rs.getInt(1), rs.getString(2)));
            }
        } catch (Exception e) {
            System.out.println("Error combo proveedor: " + e.getMessage());
        }
    }

    public DefaultTableModel buscar(String texto) {
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("ID");
    modelo.addColumn("Código");
    modelo.addColumn("Nombre");
    modelo.addColumn("U.Medida");
    modelo.addColumn("Precio");
    modelo.addColumn("Categoría");
    modelo.addColumn("Proveedor");
    modelo.addColumn("Stock");

    
    String sql = "{call sp_buscar_producto(?)}"; 
    try (Connection cn = conexion.abrirConexion(); 
         CallableStatement cs = cn.prepareCall(sql)) {
        
        cs.setString(1, texto); 
        ResultSet rs = cs.executeQuery();
        
        while (rs.next()) {
            Object[] fila = {
                rs.getInt(1),      
                rs.getString(2),   
                rs.getString(3),   
                rs.getString(4),   
                rs.getDouble(5),   
                rs.getString(6),   
                rs.getString(7),   
                rs.getInt(8)       
            };
            modelo.addRow(fila);
        }
    } catch (Exception e) {
        System.out.println("Error al buscar: " + e.getMessage());
    }
    return modelo;
}*/
}
