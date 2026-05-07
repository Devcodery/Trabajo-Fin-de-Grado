package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexionBBDD.ConexionBBDD;
import modelo.Servicio;


public class ServicioDAO {
    private Connection conexion;
    private String query;

    public ServicioDAO(ConexionBBDD conexion) {
        this.conexion = conexion.getConexion();
    }
    
    public ArrayList<Servicio> readAll(){
    	query = "select * from servicio;";
    	
    	ArrayList<Servicio> servicios = new ArrayList<>();
    	
    	try(Statement sentencia = conexion.createStatement()){
    		ResultSet r = sentencia.executeQuery(query);
    		
    		while(r.next()) {
    			servicios.add(new Servicio(r.getInt("id_servicio"),
                                            r.getString("nombre"),
                                            r.getString("descripcion"),
                                            r.getString("tecnologias_implicadas"),
                                            r.getString("objetivos"),
                                            r.getString("alcance"), 
                                            r.getString("beneficios"),
                                            r.getString("categoria"),
                                            r.getString("sede"),
                                            r.getDate("fecha_creacion"),
                                            r.getBoolean("estado")));
                
    		}
    	}catch(SQLException e) {
    		System.err.println("Error al consultar en la tabla de servicio: " + e.getMessage());
    		return null;
    	}
    	return servicios;
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
