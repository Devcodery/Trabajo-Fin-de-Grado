package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	public ArrayList<Consulta> readConsultaCliente(int id){
		ArrayList<Consulta> consultas = new ArrayList<Consulta>();
		String query = "select * "
				+ "from consulta "
				+ "where id_cliente = ?";
		
		try(PreparedStatement sentencia = conexion.prepareStatement(query)){
			sentencia.setInt(1, id);
			ResultSet rs = sentencia.executeQuery();
			while(rs.next()) {
				consultas.add(new Consulta(rs.getInt("id_consulta"), 
											rs.getString("estado"), 
											rs.getString("titulo"), 
											rs.getString("descripcion"), 
											rs.getDate("fecha_creacion"), 
											rs.getDate("fecha_fin"), 
											rs.getInt("id_servicio"), 
											rs.getInt("id_cliente"), 
											rs.getInt("id_consultor")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return consultas;
	}
	
	public ArrayList<Consulta> readConsultaConsultor(int id){
		ArrayList<Consulta> consultas = new ArrayList<Consulta>();
		String query = "select * "
				+ "from consulta "
				+ "where id_consultor = ?";
		
		try(PreparedStatement sentencia = conexion.prepareStatement(query)){
			sentencia.setInt(1, id);
			ResultSet rs = sentencia.executeQuery();
			while(rs.next()) {
				consultas.add(new Consulta(rs.getInt("id_consulta"), 
											rs.getString("estado"), 
											rs.getString("titulo"), 
											rs.getString("descripcion"), 
											rs.getDate("fecha_creacion"), 
											rs.getDate("fecha_fin"), 
											rs.getInt("id_servicio"), 
											rs.getInt("id_cliente"), 
											rs.getInt("id_consultor")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return consultas;
	}
	
	public ArrayList<Consulta> readAll(){
		ArrayList<Consulta> consultas = new ArrayList<Consulta>();
		String query = "select * "
				+ "from consulta;";
		
		try(Statement sentencia = conexion.createStatement()){
			ResultSet rs = sentencia.executeQuery(query);
			while(rs.next()) {
				consultas.add(new Consulta(rs.getInt("id_consulta"), 
											rs.getString("estado"), 
											rs.getString("titulo"), 
											rs.getString("descripcion"), 
											rs.getDate("fecha_creacion"), 
											rs.getDate("fecha_fin"), 
											rs.getInt("id_servicio"), 
											rs.getInt("id_cliente"), 
											rs.getInt("id_consultor")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return consultas;
	}

	public Consulta read(int idConsulta) {
		Consulta consulta = null;
		query = "select * "
				+ "from consulta "
				+ "where id_consulta = ?";
		try(PreparedStatement sentencia = conexion.prepareStatement(query)){
			sentencia.setInt(1, idConsulta);
			ResultSet rs = sentencia.executeQuery();
			if(rs.next()) {
				consulta = new Consulta(rs.getInt("id_consulta"), 
										rs.getString("estado"), 
										rs.getString("titulo"), 
										rs.getString("descripcion"), 
										rs.getDate("fecha_creacion"), 
										rs.getDate("fecha_fin"), 
										rs.getInt("id_servicio"), 
										rs.getInt("id_cliente"), 
										rs.getInt("id_consultor"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return consulta;
	}

	public ArrayList<Consulta> filtrar(Integer idCliente, Integer idConsultor, String estado, Integer idServicio, java.sql.Date fechaInicio, java.sql.Date fechaFin) {
		ArrayList<Consulta> consultas = new ArrayList<>();
		String sql = "SELECT * FROM consulta WHERE 1=1";

		if (idCliente != null){
			sql += " AND id_cliente = ?";
		}
		if (idConsultor != null){
			sql += " AND id_consultor = ?";
		} 
		if (estado != null && !estado.isEmpty()){
			sql += " AND estadoActual = ?";
		} 
		if (idServicio != null){
			sql += " AND id_servicio = ?";
		}
		if (fechaInicio != null){
			sql += " AND fecha_creacion >= ?";
		} 
		if (fechaFin != null){
			sql += " AND fecha_creacion <= ?";
		}

		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			int index = 1;
			if (idCliente != null){
				ps.setInt(index++, idCliente);
			}
			if (idConsultor != null){
				ps.setInt(index++, idConsultor);
			}
			if (estado != null && !estado.isEmpty()){
				ps.setString(index++, estado);
			} 
			if (idServicio != null){
				ps.setInt(index++, idServicio);
			} 
			if (fechaInicio != null){
				ps.setDate(index++, fechaInicio);
			} 
			if (fechaFin != null){
				ps.setDate(index++, fechaFin);
			} 

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				consultas.add(new Consulta(rs.getInt("id_consulta"), 
											rs.getString("estadoActual"), 
											rs.getString("titulo"), 
											rs.getString("descripcion"), 
											rs.getDate("fecha_creacion"), 
											rs.getDate("fecha_fin"), 
											rs.getInt("id_servicio"), 
											rs.getInt("id_cliente"), 
											rs.getInt("id_consultor")
											));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return consultas;
	}

	public boolean updateEstado(int idConsulta, String nuevoEstado) {
		String sql = "update consulta set estadoActual = ? where id_consulta = ?";
		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setString(1, nuevoEstado);
			ps.setInt(2, idConsulta);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateConsultor(int idConsulta, int idConsultor) {
		String sql = "update consulta set id_consultor = ? where id_consulta = ?";
		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setInt(1, idConsultor);
			ps.setInt(2, idConsulta);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
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
