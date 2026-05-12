package dao;

import java.sql.Connection;

import conexionBBDD.ConexionBBDD;

public class ClienteDAO {
    private Connection conexion;
    private String query;
    
    public ClienteDAO(ConexionBBDD conexion){
        this.conexion = conexion.getConexion();
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    
}
