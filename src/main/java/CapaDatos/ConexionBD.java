/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author melis
 */
public class ConexionBD {
    
    Connection cn = null;
    
    public Connection abrirConexion(){
        try{
            /*String url = "jdbc:mysql://127.0.0.1:3306/almacen_db?user=root&password=root"
                                   + "&allowPublicKeyRetrieval=true&useSSL=false";
            */
            String url = "jdbc:mysql://localhost:3306/almacen_db?user=root&password=12345"
                                   + "&allowPublicKeyRetrieval=true&useSSL=false";
          
            cn = DriverManager.getConnection(url);           
        }catch(Exception e1){
            System.out.println("Error Sql :"+e1.getMessage());
        }
        return cn;
    }
 
    public void cerrarConexion(){
        try{
            cn.close();
        }catch(Exception e1){
            System.out.print("Error al Cerrar Conexion : "+e1.getMessage());
        }
    }
}
