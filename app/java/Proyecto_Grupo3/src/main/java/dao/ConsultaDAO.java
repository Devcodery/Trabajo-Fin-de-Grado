package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexionBBDD.ConexionBBDD;

public class ConsultaDAO {
	 private Connection conexion;
	    private String query;

	    public ConsultaDAO(ConexionBBDD conexion) {
	        this.conexion = conexion.getConexion();
	    }
	
	
	public boolean borrarServicioConsulta(int idServicio) {
    	String query = "delete from consulta where id_servicio = ?"; 
    	try(PreparedStatement sentencia = conexion.prepareStatement(query)){
    		sentencia.setInt(1, idServicio);
    		sentencia.executeUpdate();
    		return true;
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    }

}
