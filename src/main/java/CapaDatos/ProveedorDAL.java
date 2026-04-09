/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import CapaRecursos.Proveedor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author melis
 */
public class ProveedorDAL {
    Connection cn = new ConexionBD().abrirConexion();
    
    public List listar() {
        List<Proveedor> lista = new ArrayList<>();
        try {
            CallableStatement cst = cn.prepareCall("{call sp_listar_proveedores()}");
            ResultSet rs = cst.executeQuery();

            while (rs.next()) {
                lista.add(new Proveedor(rs.getInt(1),
                        rs.getString(2), rs.getString(3)
                ));
            }

            rs.close();
            cst.close();
        } catch (Exception e) {
            System.out.println("listarProducto: " + e.getMessage());
        }
        return lista;
    }
        
    public int agregar(Proveedor unProveedor) {
        int r;
        try {
            CallableStatement cst = cn.prepareCall("{call sp_agregar_proveedor(?, ?)}");

            cst.setString(1, unProveedor.getCodigo());
            cst.setString(2, unProveedor.getNombre());

            int f = cst.executeUpdate();
            if (f > 0) {
                r = 1;
            } else {
                r = 0;
            }
            cst.close();
        } catch (Exception ex) {
            r = 0;
        }
        return r;
    }
            
     public Proveedor buscar(String codigo) {
        Proveedor oProveedor = null;
        try {
            CallableStatement cst = cn.prepareCall("{call sp_buscar_proveedor(?)}");
            cst.setString(1, codigo);
            ResultSet rs = cst.executeQuery();

            if (rs.next()) {
                oProveedor = new Proveedor();
                oProveedor.setIdProveedor(rs.getInt("id_proveedor")); 
                oProveedor.setCodigo(rs.getString("codigo"));
                oProveedor.setNombre(rs.getString("nombre"));
            }
            cst.close();
            rs.close();
        } catch (Exception ex) {
            System.out.println("Error al buscar: " + ex.getMessage());
        }
        return oProveedor;
    }

    public int actualizar(Proveedor unProveedor) {
        int r;
        try {
            CallableStatement cst = cn.prepareCall("{call sp_actualizar_proveedor(?, ?, ?)}");

            cst.setInt(1, unProveedor.getIdProveedor());
            cst.setString(2, unProveedor.getCodigo());
            cst.setString(3, unProveedor.getNombre());

            int f = cst.executeUpdate();
            r = (f > 0) ? 1 : 0;
            cst.close();
        } catch (Exception e) {
            r = 0;
        }
        return r;
    }

    public int eliminar(int id) {
        int r;
        try {
            CallableStatement cst = cn.prepareCall("{call sp_eliminar_proveedor(?)}");
            cst.setInt(1, id);
            int f = cst.executeUpdate();
            r = (f > 0) ? 1 : 0;
            cst.close();
        } catch (Exception e) {
            r = 0;
        }
        return r;
    }
}
