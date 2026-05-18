package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionBBDD.ConexionBBDD;
import modelo.Mensaje;

public class MensajeDAO {
	private Connection conn;
	private String query;

	public MensajeDAO(ConexionBBDD conexion) {
		this.conn = conexion.getConexion();
	}

	public boolean create(int idConsulta, int idUsuario, String descripcion, String asunto) {
		query = "insert into Mensajes(id_consulta, id_usuario, descripcion, asunto) values (?, ?, ?, ?);";

		try (PreparedStatement sentencia = conn.prepareStatement(query)) {
			sentencia.setInt(1, idConsulta);
			sentencia.setInt(2, idUsuario);
			sentencia.setString(3, descripcion);
			sentencia.setString(4, asunto);
			sentencia.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<Mensaje> readMensajesUsuario(int idUsuario, int idConsulta) {
		ArrayList <Mensaje> mensajes = new ArrayList<Mensaje>();
		query = "select * "
				+ "from mensajes "
				+ "where id_usuario = ? and id_consulta = ?";
		
		try(PreparedStatement sentencia = conn.prepareStatement(query)){
			sentencia.setInt(1, idUsuario);
			sentencia.setInt(2, idConsulta);
			ResultSet rs = sentencia.executeQuery();
			while(rs.next()) {
				mensajes.add(new Mensaje(rs.getInt("id_usuario"), rs.getInt("id_consulta"), rs.getString("asunto"), rs.getString("descripcion")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mensajes;
	}
	
	public Mensaje readMensaje(int idMensaje) {
		Mensaje mensaje = null;
		query = "select * "
				+ "from mensajes "
				+ "where id_mensaje = ?";
		
		try(PreparedStatement sentencia = conn.prepareStatement(query)){
			sentencia.setInt(1, idMensaje);
			ResultSet rs = sentencia.executeQuery();
			if(rs.next()) {
				mensaje = new Mensaje(rs.getInt("id_usuario"), rs.getInt("id_consulta"), rs.getString("asunto"), rs.getString("descripcion"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mensaje;
	}

}
