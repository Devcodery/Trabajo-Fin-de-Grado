package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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

	public ArrayList<Servicio> readAll() {
		query = "select * from servicio;";

		ArrayList<Servicio> servicios = new ArrayList<>();

		try (Statement sentencia = conexion.createStatement()) {
			ResultSet r = sentencia.executeQuery(query);

			while (r.next()) {
				servicios.add(new Servicio(r.getInt("id_servicio"), r.getString("nombre"), r.getString("descripcion"),
						r.getString("tecnologias_implicadas"), r.getString("objetivos"), r.getString("alcance"),
						r.getString("beneficios"), r.getString("categoria"), r.getString("sede"),
						r.getDate("fecha_creacion"), r.getBoolean("estado")));

			}
		} catch (SQLException e) {
			System.err.println("Error al consultar en la tabla de servicio: " + e.getMessage());
			return null;
		}
		return servicios;
	}

	public Servicio read(int id_servicio) {
		Servicio servicio = null;
		query = "select * "
				+ "from servicio "
				+ "where id_servicio = ?";

		try (PreparedStatement sentencia = conexion.prepareStatement(query)) {
			sentencia.setInt(1, id_servicio);
			ResultSet r = sentencia.executeQuery();

			if (r.next()) {
				servicio = new Servicio(r.getInt("id_servicio"), r.getString("nombre"), r.getString("descripcion"),
						r.getString("tecnologias_implicadas"), r.getString("objetivos"), r.getString("alcance"),
						r.getString("beneficios"), r.getString("categoria"), r.getString("sede"),
						r.getDate("fecha_creacion"), r.getBoolean("estado"));

			}
		} catch (SQLException e) {
			System.err.println("Error al consultar en la tabla de servicio: " + e.getMessage());
			return null;
		}
		return servicio;
	}

	public boolean borrarServicio(int idServicio) {
		query = "delete from Servicio where id_servicio = ?";
		try (PreparedStatement sentencia = conexion.prepareStatement(query)) {
			sentencia.setInt(1, idServicio);
			sentencia.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean cambiarEstadoServicio(int idServicio, boolean estado) {
		query = "update servicio "
				+ "set estado = ? "
				+ "where id_servicio = ?";
		try (PreparedStatement sentencia = conexion.prepareStatement(query)) {
			sentencia.setBoolean(1, estado);
			sentencia.setInt(2, idServicio);
			sentencia.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<Servicio> filtrar(String cat, Boolean est, String sede, Date fechaInicio, Date fechaFin ) {
		ArrayList<Servicio> servicios = new ArrayList<>();
		query = "SELECT * FROM servicio WHERE 1=1";


		if (cat != null && !cat.isEmpty()){
			query += " AND id_categoria = ?";
		}
		if (est != null){
			query += " AND estado = ?";
		}	
		if (sede != null && !sede.isEmpty()){
			query += " AND id_sede = ?";
		}
		if (fechaInicio != null){
			query += " AND fecha_creacion >= ?";
		}
		if (fechaFin != null){
			query += " AND fecha_creacion <= ?";
		}
			
		query += ";";

		try (PreparedStatement sentenciaPreparada = conexion.prepareStatement(query)) {
			int aux = 1;

			if (cat != null && !cat.isEmpty()){
				sentenciaPreparada.setString(aux++, cat);
			}	
			if (est != null){
				sentenciaPreparada.setBoolean(aux++, est);
			}	
			if (sede != null && !sede.isEmpty()){
				sentenciaPreparada.setString(aux++, sede);
			}
			if(fechaInicio != null){
				sentenciaPreparada.setDate(aux++, fechaInicio);
			}
			if (fechaFin != null) {
				sentenciaPreparada.setDate(aux++, fechaFin);
			}

			ResultSet rs = sentenciaPreparada.executeQuery();
			while (rs.next()) {
				servicios.add(new Servicio(rs.getInt("id_servicio"),
											rs.getString("nombre"),
											rs.getString("descripcion"),
											rs.getString("tecnologias_implicadas"),
											rs.getString("objetivos"),
											rs.getString("alcance"),
											rs.getString("beneficios"),
											rs.getString("categoria"),
											rs.getString("sede"),
											rs.getDate("fecha_creacion"),
											rs.getBoolean("estado")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return servicios;
	}
}
