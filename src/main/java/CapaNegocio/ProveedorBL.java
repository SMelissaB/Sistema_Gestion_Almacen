/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocio;

import CapaDatos.ProveedorDAL;
import CapaRecursos.Proveedor;
import java.util.List;

/**
 *
 * @author melis
 */
public class ProveedorBL {
    ProveedorDAL oProveedorDAL = new ProveedorDAL();

    public List listarProveedor() {
        return oProveedorDAL.listar();
    }

    public int agregarProveedor(Proveedor oProveedor) {
        return oProveedorDAL.agregar(oProveedor); 
    }

    public int actualizarProveedor(Proveedor oProveedor) {
        return oProveedorDAL.actualizar(oProveedor);
    }

    public int eliminarProveedor(int id) {
        return oProveedorDAL.eliminar(id);
    }

    public Proveedor buscarProveedor(String codigo) {
        return oProveedorDAL.buscar(codigo);
    }
}
