package dao;

import java.sql.Connection;
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
		query = "select * " + "from servicio " + "where id_servicio = ?";

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
		String query = "delete from Servicio where id_servicio = ?";
		try (PreparedStatement sentencia = conexion.prepareStatement(query)) {
			sentencia.setInt(1, idServicio);
			sentencia.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	public ArrayList<Servicio> readInactivos() {
		query = "select * " + "from servicio " + "where estado = false;";

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

	public ArrayList<Servicio> readActivos() {
		query = "select * " + "from servicio " + "where estado = true;";

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

	public boolean desactivarServicio(int idServicio) {
		String query = "update servicio "
				+ "set estado = false "
				+ "where id_servicio = ?";
		try (PreparedStatement sentencia = conexion.prepareStatement(query)) {
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
