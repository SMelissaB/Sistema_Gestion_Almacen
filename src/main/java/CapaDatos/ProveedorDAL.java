/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import CapaRecursos.Proveedor;
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
            Connection cn = new ConexionBD().abrirConexion();
            PreparedStatement ps = cn.prepareStatement("Select * from proveedor");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new Proveedor(rs.getInt(1),
                        rs.getString(2), rs.getString(3)
                ));
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("listarProducto: " + e.getMessage());
        }
        return lista;
    }
        
    public int agregar(Proveedor unProveedor) {
        int r;
        try {
            PreparedStatement pst = cn.prepareStatement("insert into proveedor(codigo, nombre) values (?, ?)");

            pst.setString(1, unProveedor.getCodigo());
            pst.setString(2, unProveedor.getNombre());

            int f = pst.executeUpdate();
            if (f > 0) {
                r = 1;
            } else {
                r = 0;
            }
            pst.close();
        } catch (Exception ex) {
            r = 0;
        }
        return r;
    }
            
     public Proveedor buscar(String codigo) {
        Proveedor oProveedor = null;
        try {
            PreparedStatement pst = cn.prepareStatement("select * from proveedor where codigo = ?");
            pst.setString(1, codigo);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                oProveedor = new Proveedor();
                oProveedor.setIdProveedor(rs.getInt("id_proveedor")); 
                oProveedor.setCodigo(rs.getString("codigo"));
                oProveedor.setNombre(rs.getString("nombre"));
            }
            pst.close();
            rs.close();
        } catch (Exception ex) {
            System.out.println("Error al buscar: " + ex.getMessage());
        }
        return oProveedor;
    }

    public int actualizar(Proveedor unProveedor) {
        int r;
        try {
            PreparedStatement pst = cn.prepareStatement("update proveedor set codigo=?, nombre=? "
                    + " where id_proveedor=?");

            pst.setString(1, unProveedor.getCodigo());
            pst.setString(2, unProveedor.getNombre());
            pst.setInt(3, unProveedor.getIdProveedor()); 

            int f = pst.executeUpdate();
            r = (f > 0) ? 1 : 0;
            pst.close();
        } catch (Exception e) {
            r = 0;
        }
        return r;
    }

    public int eliminar(int id) {
        int r;
        try {
            PreparedStatement pst = cn.prepareStatement("delete from proveedor where id_proveedor=?");
            pst.setInt(1, id);
            int f = pst.executeUpdate();
            r = (f > 0) ? 1 : 0;
            pst.close();
        } catch (Exception e) {
            r = 0;
        }
        return r;
    }
}
