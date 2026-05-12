package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionBBDD.ConexionBBDD;
import modelo.Consulta;

public class ConsultaDAO {
	private Connection conexion;
	private String query;

	public ConsultaDAO(ConexionBBDD conexion) {
		this.conexion = conexion.getConexion();
	}

	public boolean borrarServicioConsulta(int idServicio) {
		String query = "delete from consulta where id_servicio = ?";
		try (PreparedStatement sentencia = conexion.prepareStatement(query)) {
			sentencia.setInt(1, idServicio);
			sentencia.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<Consulta> readCliente(int id){
		ArrayList<Consulta> consultas = new ArrayList<Consulta>();
		String query = "select * "
				+ "from consulta "
				+ "where id_cliente = ?";
		
		try(PreparedStatement sentencia = conexion.prepareStatement(query)){
			sentencia.setInt(1, id);
			ResultSet rs = sentencia.executeQuery();
			while(rs.next()) {
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return consultas;
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
