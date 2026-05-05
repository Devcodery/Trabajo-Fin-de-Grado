package conexionBBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

import io.github.cdimascio.dotenv.Dotenv;

public class ConexionBBDD {
	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://localhost:5432/proyecto-grupo3";
	private static final String USUARIO = "postgres";
	private static final String PASSWORD = "1234";
	private Connection conexion = null;


	private static final Dotenv DOTENV = Dotenv.configure().ignoreIfMissing().load();
    private static final String HOST = DOTENV.get("DB_HOST");
    private static final String PUERTO = DOTENV.get("DB_PORT");
    private static final String DB = DOTENV.get("DB_NAME");
    private static final String URL_DOTENV = "jdbc:postgresql://"+HOST+":"+PUERTO+"/"+DB;
    private static final String USER = DOTENV.get("DB_USER");
    private static final String PASS = DOTENV.get("DB_PASSWORD");

	public Connection conectarBDPostgreSQL() {
		try {
			Class.forName(DRIVER);
			this.conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
			System.out.println("Conexion a BBDD PostgresSQL OK");
		} catch (ClassNotFoundException var2) {
			var2.printStackTrace();
		} catch (SQLTimeoutException var3) {
			System.err.println("ERROR ha pasado el tiempo de conexion");
			var3.printStackTrace();
		} catch (SQLException var4) {
			System.err.println("ERROR en la conexion a BBDD PostgresSQL");
			var4.printStackTrace();
		}

		return this.conexion;
	}
	
	public Connection conectarBDDotenv() {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL_DOTENV, USER, PASS);
            System.out.println("Conexión a BDDD OK");
        } catch (SQLException e) {
            System.err.println("Error en la conexión a BBDD");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conexion;
    }

	public void cerrarConexion() {
		try {
			this.conexion.close();
		} catch (SQLException var2) {
			System.err.println("ERROR al cerrar la conexion a BBDD");
			var2.printStackTrace();
		}

	}

	public Connection getConexion() {
		return this.conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}
}
