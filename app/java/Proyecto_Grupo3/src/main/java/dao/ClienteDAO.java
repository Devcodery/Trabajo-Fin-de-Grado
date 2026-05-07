package dao;

import java.sql.Connection;

import conexionBBDD.ConexionBBDD;

public class ClienteDAO {
    private Connection conexion;
    private String query;
    
    public ClienteDAO(ConexionBBDD conexion){
        this.conexion = conexion.getConexion();
    }
}
